package com.omniagent.app.kernel

import com.chaquo.python.Python
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Isolates all Chaquopy interactions from the rest of the application.
 */
class PythonKernelManager {

    private val python by lazy { Python.getInstance() }

    suspend fun classify(sanitizedInput: String): String = withContext(Dispatchers.IO) {
        try {
            val classifier = python.getModule("intent_classifier")
            classifier.callAttr("classify_input", sanitizedInput).toString()
        } catch (e: Exception) {
            "{\"status\": \"error\", \"message\": \"Classification failed: ${e.message}\"}"
        }
    }

    suspend fun getReasoningLog(): String = withContext(Dispatchers.IO) {
        val classifier = python.getModule("intent_classifier")
        classifier.callAttr("get_reasoning_log").toString()
    }

    suspend fun runEngine(moduleName: String, sanitizedInput: String): String = withContext(Dispatchers.IO) {
        try {
            when (moduleName) {
                "coding" -> python.getModule("coding_engine").callAttr("analyze_code", sanitizedInput).toString()
                "cybersecurity" -> python.getModule("cyber_engine").callAttr("analyze_security", sanitizedInput).toString()
                "resume" -> python.getModule("resume_engine").callAttr("analyze_resume", sanitizedInput).toString()
                "startup" -> python.getModule("startup_engine").callAttr("analyze_startup", sanitizedInput).toString()
                "general" -> python.getModule("general_engine").callAttr("analyze_general", sanitizedInput).toString()
                else -> "{\"error\": \"Routing failed: Unknown module '$moduleName'\"}"
            }
        } catch (e: Exception) {
            "{\"error\": \"AI Engine Error\", \"details\": \"${e.message}\", \"module\": \"$moduleName\"}"
        }
    }
}
