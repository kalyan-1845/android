package com.omniagent.app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.omniagent.app.data.db.OmniAgentDatabase
import com.omniagent.app.core.model.*
import com.omniagent.app.domain.repository.AnalysisRepository
import com.omniagent.app.security.AccessControl
import com.omniagent.app.security.CryptoManager
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModelProvider
import android.content.Context
import android.content.SharedPreferences

/**
 * Main ViewModel — manages all UI state and orchestrates the analysis pipeline.
 * Refactored for DI: accepts repository via constructor.
 */
class OmniAgentViewModel(
    private val repository: AnalysisRepository,
    application: Application
) : AndroidViewModel(application) {

    // Auto-lock inactivity timer (5 minutes)
    private val INACTIVITY_TIMEOUT_MS = 5 * 60 * 1000L
    private var inactivityJob: kotlinx.coroutines.Job? = null
    
    // Persistent State Handle
    private val sharedPrefs: SharedPreferences = application.getSharedPreferences(
        "omniagent_prefs", Context.MODE_PRIVATE
    )

    // === UI STATE ===

    private val _uiState = MutableStateFlow(OmniAgentUiState())
    val uiState: StateFlow<OmniAgentUiState> = _uiState.asStateFlow()

    private val _classificationResult = MutableStateFlow<ClassificationResult?>(null)
    val classificationResult: StateFlow<ClassificationResult?> = _classificationResult.asStateFlow()

    private val _engineResult = MutableStateFlow<EngineResult?>(null)
    val engineResult: StateFlow<EngineResult?> = _engineResult.asStateFlow()

    private val _reasoningSteps = MutableStateFlow<List<String>>(emptyList())
    val reasoningSteps: StateFlow<List<String>> = _reasoningSteps.asStateFlow()

    private val _pipelineResult = MutableStateFlow<AnalysisPipelineResult?>(null)
    val pipelineResult: StateFlow<AnalysisPipelineResult?> = _pipelineResult.asStateFlow()

    val recentLogs = repository.getRecentLogs(50)

    init {
        restorePendingAnalysisState()
    }

    // === ACTIONS ===

    /**
     * Run the full analysis pipeline with user input.
     */
    fun analyzeInput(userInput: String) {
        if (userInput.isBlank()) return
        resetInactivityTimer()

        // Save state persistently in case process dies
        savePendingAnalysisState(userInput)

        viewModelScope.launch {
            _uiState.update { it.copy(isProcessing = true, error = null, activeTab = DashboardTab.OUTPUT) }

            try {
                val role = AccessControl.getCurrentRole().name.lowercase()
                val result = repository.runFullPipeline(userInput, role)

                _classificationResult.value = result.classification
                _engineResult.value = result.engineResult
                _reasoningSteps.value = result.classification.reasoning
                _pipelineResult.value = result

                _uiState.update {
                    it.copy(
                        isProcessing = false,
                        lastModule = result.classification.module ?: "none",
                        lastModuleName = result.classification.moduleName,
                        lastConfidence = result.classification.confidence,
                        processingTimeMs = result.totalProcessingTimeMs,
                        hasResult = true
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isProcessing = false,
                        error = "Analysis failed: ${e.message}"
                    )
                }
            } finally {
                clearPendingAnalysisState()
            }
        }
    }

    /**
     * Switch active dashboard tab.
     */
    fun switchTab(tab: DashboardTab) {
        resetInactivityTimer()
        _uiState.update { it.copy(activeTab = tab) }
    }

    /**
     * Clear current results.
     */
    fun clearResults() {
        _classificationResult.value = null
        _engineResult.value = null
        _reasoningSteps.value = emptyList()
        _pipelineResult.value = null
        _uiState.update { it.copy(hasResult = false, lastModule = "", lastModuleName = "") }
    }

    /**
     * Clear all logs (admin only).
     */
    fun clearAllLogs() {
        if (!AccessControl.canClearLogs()) return
        viewModelScope.launch {
            repository.clearAllLogs()
        }
    }

    /**
     * Authenticate as admin.
     */
    fun authenticateAdmin(pin: String): Boolean {
        val success = AccessControl.authenticateAdmin(pin)
        if (success) {
            _uiState.update { it.copy(currentRole = UserRole.ADMIN) }
            resetInactivityTimer()
        }
        return success
    }

    /**
     * Switch to user role.
     */
    fun switchToUserRole() {
        AccessControl.switchToUser()
        _uiState.update { it.copy(currentRole = UserRole.USER) }
    }

    /**
     * Decrypt log result (admin only).
     */
    fun decryptLogResult(encrypted: String): String {
        return if (AccessControl.canViewDecryptedData()) {
            repository.decryptLogResult(encrypted)
        } else {
            "[Encrypted — Admin access required]"
        }
    }

    fun dismissError() {
        _uiState.update { it.copy(error = null) }
    }

    // === DEMO MODE & PRESENTATION ===

    fun toggleDemoMode(enabled: Boolean) {
        _uiState.update { it.copy(isDemoMode = enabled) }
    }

    fun runDemo(type: String) {
        val input = when(type) {
            "coding" -> "Check this Python code for SQL injection vulnerabilities: cursor.execute('SELECT * FROM users WHERE name = ' + user_input)"
            "cyber" -> "Analyze these logs for suspicious patterns: 192.168.1.1 accessed /admin/config 50 times in 2 seconds"
            "resume" -> "Review this resume snippet: Senior Java Developer with 10 years experience in banking systems and microservices."
            "startup" -> "Evaluate my startup idea: An AI-powered personal chef app that suggests recipes based on fridge contents and nutrition goals."
            else -> ""
        }
        if (input.isNotEmpty()) {
            analyzeInput(input)
        }
    }

    /**
     * Resets the inactivity timer. If it expires, user is logged out of Admin mode.
     */
    private fun resetInactivityTimer() {
        inactivityJob?.cancel()
        if (_uiState.value.currentRole == UserRole.ADMIN) {
            inactivityJob = viewModelScope.launch {
                kotlinx.coroutines.delay(INACTIVITY_TIMEOUT_MS)
                switchToUserRole()
                _uiState.update { it.copy(error = "Session auto-locked due to inactivity.") }
            }
        }
    }

    // === PERSISTENCE LOGIC ===
    private fun savePendingAnalysisState(input: String) {
        sharedPrefs.edit().putString("pending_analysis_input", input).apply()
    }

    private fun clearPendingAnalysisState() {
        sharedPrefs.edit().remove("pending_analysis_input").apply()
    }

    private fun restorePendingAnalysisState() {
        val pendingInput = sharedPrefs.getString("pending_analysis_input", null)
        if (!pendingInput.isNullOrBlank()) {
            _uiState.update { it.copy(error = "Resuming incomplete background analysis...") }
            analyzeInput(pendingInput)
        }
    }
}

/**
 * Factory to inject dependencies into the ViewModel.
 */
class OmniAgentViewModelFactory(
    private val repository: AnalysisRepository,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OmniAgentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return OmniAgentViewModel(repository, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

/**
 * Represents the full UI state of the dashboard.
 */
data class OmniAgentUiState(
    val isProcessing: Boolean = false,
    val hasResult: Boolean = false,
    val error: String? = null,
    val activeTab: DashboardTab = DashboardTab.OUTPUT,
    val lastModule: String = "",
    val lastModuleName: String = "",
    val lastConfidence: Double = 0.0,
    val processingTimeMs: Long = 0,
    val currentRole: UserRole = UserRole.USER,
    val isDemoMode: Boolean = false
)

/**
 * Dashboard navigation tabs.
 */
enum class DashboardTab(val title: String, val icon: String) {
    OUTPUT("Output", "workspace"),
    REASONING("Reasoning", "brain"),
    GROWTH("Growth", "trending_up"),
    LOGS("Logs", "history"),
    SETTINGS("Settings", "settings")
}
