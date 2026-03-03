package com.omniagent.app.data.repository

import com.chaquo.python.Python
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.omniagent.app.data.db.AnalysisLogDao
import com.omniagent.app.data.model.*
import com.omniagent.app.security.CryptoManager
import com.omniagent.app.security.FileSandbox
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

/**
 * Repository — bridges Kotlin UI with Python AI Kernel and Domain Engines.
 * All Python calls are routed through Chaquopy.
 * Results are encrypted before database storage.
 */
class OmniAgentRepository(
    private val analysisLogDao: AnalysisLogDao
) {
    private val gson = Gson()
    private val python by lazy { Python.getInstance() }

    // === KERNEL OPERATIONS ===

    /**
     * Classify user input through the AI Kernel.
     * Returns which module should handle the task.
     */
    suspend fun classifyInput(userInput: String): ClassificationResult = withContext(Dispatchers.IO) {
        val sanitizedInput = FileSandbox.sanitizeInput(userInput)

        val classifier = python.getModule("intent_classifier")
        val resultJson = classifier.callAttr("classify_input", sanitizedInput).toString()

        parseClassificationResult(resultJson)
    }

    /**
     * Run the full analysis pipeline:
     * 1. Classify input → determine module
     * 2. Route to appropriate engine
     * 3. Get structured result
     * 4. Store encrypted log
     */
    suspend fun runFullPipeline(
        userInput: String,
        userRole: String = "user"
    ): AnalysisPipelineResult = withContext(Dispatchers.IO) {
        val startTime = System.currentTimeMillis()
        val sanitizedInput = FileSandbox.sanitizeInput(userInput)

        // Step 1: Classify
        val classification = classifyInput(sanitizedInput)

        // Step 2: Route to engine
        val engineResult = if (classification.module != null) {
            runEngine(classification.module, sanitizedInput)
        } else {
            null
        }

        // Step 3: Store encrypted log
        val log = AnalysisLog(
            userInput = sanitizedInput,
            classifiedModule = classification.module ?: "none",
            confidence = classification.confidence,
            confidenceLevel = classification.confidenceLevel,
            resultJson = CryptoManager.encrypt(engineResult?.rawJson ?: "{}"),
            reasoningJson = CryptoManager.encrypt(gson.toJson(classification.reasoning)),
            userRole = userRole
        )
        analysisLogDao.insertLog(log)

        val totalTime = System.currentTimeMillis() - startTime

        AnalysisPipelineResult(
            classification = classification,
            engineResult = engineResult,
            totalProcessingTimeMs = totalTime
        )
    }

    // === ENGINE OPERATIONS ===

    /**
     * Route to the correct engine based on classification.
     */
    private suspend fun runEngine(moduleName: String, input: String): EngineResult = withContext(Dispatchers.IO) {
        val resultJson = when (moduleName) {
            "coding" -> {
                val engine = python.getModule("coding_engine")
                engine.callAttr("analyze_code", input).toString()
            }
            "cybersecurity" -> {
                val engine = python.getModule("cyber_engine")
                engine.callAttr("analyze_security", input).toString()
            }
            "resume" -> {
                val engine = python.getModule("resume_engine")
                engine.callAttr("analyze_resume", input).toString()
            }
            "startup" -> {
                val engine = python.getModule("startup_engine")
                engine.callAttr("analyze_startup", input).toString()
            }
            else -> "{\"error\": \"Unknown module: $moduleName\"}"
        }

        EngineResult(
            engine = moduleName,
            timestamp = System.currentTimeMillis().toString(),
            status = "success",
            rawJson = resultJson
        )
    }

    // === LOG OPERATIONS ===

    fun getAllLogs(): Flow<List<AnalysisLog>> = analysisLogDao.getAllLogs()

    fun getRecentLogs(limit: Int = 20): Flow<List<AnalysisLog>> = analysisLogDao.getRecentLogs(limit)

    fun getLogsByModule(module: String): Flow<List<AnalysisLog>> = analysisLogDao.getLogsByModule(module)

    suspend fun clearAllLogs() = analysisLogDao.clearAllLogs()

    suspend fun getLogCount(): Int = analysisLogDao.getLogCount()

    /**
     * Decrypt a log's result JSON (admin only).
     */
    fun decryptLogResult(encryptedJson: String): String {
        return CryptoManager.decrypt(encryptedJson)
    }

    /**
     * Get reasoning log from Python kernel.
     */
    suspend fun getKernelReasoningLog(): String = withContext(Dispatchers.IO) {
        val classifier = python.getModule("intent_classifier")
        classifier.callAttr("get_reasoning_log").toString()
    }

    // === PARSING ===

    private fun parseClassificationResult(json: String): ClassificationResult {
        return try {
            val map: Map<String, Any> = gson.fromJson(
                json, object : TypeToken<Map<String, Any>>() {}.type
            )

            @Suppress("UNCHECKED_CAST")
            ClassificationResult(
                status = map["status"] as? String ?: "",
                module = map["module"] as? String,
                moduleName = map["module_name"] as? String ?: "",
                confidence = (map["confidence"] as? Number)?.toDouble() ?: 0.0,
                confidenceLevel = map["confidence_level"] as? String ?: "",
                allScores = (map["all_scores"] as? Map<String, Number>)
                    ?.mapValues { it.value.toDouble() } ?: emptyMap(),
                ranking = (map["ranking"] as? List<Map<String, Any>>)?.map { entry ->
                    ModuleScore(
                        module = entry["module"] as? String ?: "",
                        score = (entry["score"] as? Number)?.toDouble() ?: 0.0
                    )
                } ?: emptyList(),
                reasoning = (map["reasoning"] as? List<String>) ?: emptyList(),
                inputFeatures = (map["input_features"] as? Number)?.toInt() ?: 0,
                timestamp = map["timestamp"] as? String ?: ""
            )
        } catch (e: Exception) {
            ClassificationResult(
                status = "error",
                moduleName = "Parse Error: ${e.message}"
            )
        }
    }
}
