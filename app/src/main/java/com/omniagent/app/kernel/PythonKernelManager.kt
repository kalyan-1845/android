package com.omniagent.app.kernel

import com.chaquo.python.Python
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Isolates all Chaquopy interactions from the rest of the application.
 */
class PythonKernelManager {

    private val python by lazy { Python.getInstance() }
    
    // Cached modules for faster execution
    private val intentClassifier by lazy { python.getModule("intent_classifier") }
    private val codingEngine by lazy { python.getModule("coding_engine") }
    private val cyberEngine by lazy { python.getModule("cyber_engine") }
    private val resumeEngine by lazy { python.getModule("resume_engine") }
    private val startupEngine by lazy { python.getModule("startup_engine") }
    private val generalEngine by lazy { python.getModule("general_engine") }

    suspend fun classify(sanitizedInput: String): String = withContext(Dispatchers.IO) {
        try {
            intentClassifier.callAttr("classify_input", sanitizedInput).toString()
        } catch (e: Exception) {
            "{\"status\": \"error\", \"message\": \"Classification failed: ${e.message}\"}"
        }
    }

    suspend fun getReasoningLog(): String = withContext(Dispatchers.IO) {
        intentClassifier.callAttr("get_reasoning_log").toString()
    }

    suspend fun runEngine(moduleName: String, sanitizedInput: String): String = withContext(Dispatchers.IO) {
        try {
            when (moduleName) {
                "coding" -> codingEngine.callAttr("analyze_code", sanitizedInput).toString()
                "cybersecurity" -> cyberEngine.callAttr("analyze_security", sanitizedInput).toString()
                "resume" -> resumeEngine.callAttr("analyze_resume", sanitizedInput).toString()
                "startup" -> startupEngine.callAttr("analyze_startup", sanitizedInput).toString()
                "general" -> generalEngine.callAttr("analyze_general", sanitizedInput).toString()
                else -> "{\"error\": \"Routing failed: Unknown module '$moduleName'\"}"
            }
        } catch (e: Exception) {
            "{\"error\": \"AI Engine Error\", \"details\": \"${e.message}\", \"module\": \"$moduleName\"}"
        }
    }
}
