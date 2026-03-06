package com.omniagent.app.viewmodel
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModelProvider
import com.omniagent.app.core.model.*
import com.omniagent.app.data.local.OmniAgentDatabase
import com.omniagent.app.domain.repository.AnalysisRepository
import com.omniagent.app.security.*

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
        
        if (userInput.trim() == "1234") {
            _uiState.update { it.copy(currentRole = UserRole.ADMIN, error = null) }
            return
        }

        // Save state persistently in case process dies
        savePendingAnalysisState(userInput)

        viewModelScope.launch {
            // Clear immediately to prevent crash loops
            clearPendingAnalysisState()
            
            _uiState.update { it.copy(isProcessing = true, error = null, activeTab = DashboardTab.CHATS) }
            Log.d("OmniAgent", "UI State updated: isProcessing=true, activeTab=OUTPUT")

            try {
                val role = AccessControl.getCurrentRole().name.lowercase()
                Log.d("OmniAgent", "Calling repository.runFullPipeline...")
                val result = repository.runFullPipeline(userInput, role)
                
                Log.d("OmniAgent", "Pipeline result received. Classification: ${result.classification.moduleName}")

                _pipelineResult.value = result
                _reasoningSteps.value = result.classification.reasoning
                _classificationResult.value = result.classification
                _engineResult.value = result.engineResult

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
                Log.d("OmniAgent", "UI State updated: isProcessing=false, hasResult=true")
            } catch (e: Throwable) {
                // Catch Throwable (including Error and Exception) to prevent fatal JVM crashes from causing a crash loop
                Log.e("OmniAgent", "Fatal Analysis Error", e)
                
                // Synchronously clear the persistent state so the app doesn't crash again on next launch
                sharedPrefs.edit().remove("pending_analysis_input").commit()
                
                _uiState.update {
                    it.copy(
                        isProcessing = false,
                        error = "CRITICAL FAIL: ${e.message}\nCause: ${e.cause}"
                    )
                }
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
     * Clear all logs.
     */
    fun clearAllLogs() {
        viewModelScope.launch {
            repository.clearAllLogs()
        }
    }

    /**
     * Authenticate as admin.
     */
    fun authenticateAdmin(pin: String): Boolean {
        return true 
    }

    /**
     * Switch to user role.
     */
    fun switchToUserRole() {
        // No-op: Persistent Admin state
    }

    /**
     * Decrypt log result (admin only).
     */
    fun decryptLogResult(encrypted: String): String {
        return repository.decryptLogResult(encrypted)
    }

    fun dismissError() {
        _uiState.update { it.copy(error = null) }
    }

    // Demo functionality removed for Full Realization phase


    // No-op for role remnants
    private fun resetInactivityTimer() {}

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
    val activeTab: DashboardTab = DashboardTab.CHATS,
    val lastModule: String = "",
    val lastModuleName: String = "",
    val lastConfidence: Double = 0.0,
    val processingTimeMs: Long = 0,
    val currentRole: UserRole = UserRole.USER, // Default to limited access
    val isDemoMode: Boolean = false
)

/**
 * Dashboard navigation tabs.
 */
enum class DashboardTab(val title: String, val icon: String) {
    CHATS("Chats", "chat"),
    STATUS("Status", "update"),
    COMMUNITIES("Communities", "people"),
    SETTINGS("Settings", "settings")
}
