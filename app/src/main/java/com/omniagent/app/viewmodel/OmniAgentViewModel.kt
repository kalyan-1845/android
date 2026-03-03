package com.omniagent.app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.omniagent.app.data.db.OmniAgentDatabase
import com.omniagent.app.data.model.*
import com.omniagent.app.data.repository.OmniAgentRepository
import com.omniagent.app.security.AccessControl
import com.omniagent.app.security.CryptoManager
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * Main ViewModel — manages all UI state and orchestrates the analysis pipeline.
 */
class OmniAgentViewModel(application: Application) : AndroidViewModel(application) {

    private val database = OmniAgentDatabase.getDatabase(application)
    private val repository = OmniAgentRepository(database.analysisLogDao())

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

    // === ACTIONS ===

    /**
     * Run the full analysis pipeline with user input.
     */
    fun analyzeInput(userInput: String) {
        if (userInput.isBlank()) return

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
            }
        }
    }

    /**
     * Switch active dashboard tab.
     */
    fun switchTab(tab: DashboardTab) {
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
    val currentRole: UserRole = UserRole.USER
)

/**
 * Dashboard navigation tabs.
 */
enum class DashboardTab(val title: String, val icon: String) {
    OUTPUT("Output", "workspace"),
    REASONING("Reasoning", "brain"),
    LOGS("Logs", "history"),
    SETTINGS("Settings", "settings")
}
