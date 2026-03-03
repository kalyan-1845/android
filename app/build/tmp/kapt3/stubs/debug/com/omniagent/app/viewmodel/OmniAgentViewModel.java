package com.omniagent.app.viewmodel;

/**
 * Main ViewModel — manages all UI state and orchestrates the analysis pipeline.
 * Refactored for DI: accepts repository via constructor.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u0012J\u000e\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\u0012J\u0006\u00100\u001a\u00020+J\b\u00101\u001a\u00020+H\u0002J\u0006\u00102\u001a\u00020+J\u000e\u00103\u001a\u00020\u00122\u0006\u00104\u001a\u00020\u0012J\u0006\u00105\u001a\u00020+J\b\u00106\u001a\u00020+H\u0002J\b\u00107\u001a\u00020+H\u0002J\u000e\u00108\u001a\u00020+2\u0006\u00109\u001a\u00020\u0012J\u0010\u0010:\u001a\u00020+2\u0006\u0010;\u001a\u00020\u0012H\u0002J\u000e\u0010<\u001a\u00020+2\u0006\u0010=\u001a\u00020>J\u0006\u0010?\u001a\u00020+J\u000e\u0010@\u001a\u00020+2\u0006\u0010A\u001a\u00020.R\u000e\u0010\u0007\u001a\u00020\bX\u0082D\u00a2\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0019\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0018R\u001d\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0018R\u001d\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\u00110\"\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\'X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00140\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0018\u00a8\u0006B"}, d2 = {"Lcom/omniagent/app/viewmodel/OmniAgentViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "repository", "Lcom/omniagent/app/domain/repository/AnalysisRepository;", "application", "Landroid/app/Application;", "(Lcom/omniagent/app/domain/repository/AnalysisRepository;Landroid/app/Application;)V", "INACTIVITY_TIMEOUT_MS", "", "_classificationResult", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/omniagent/app/core/model/ClassificationResult;", "_engineResult", "Lcom/omniagent/app/core/model/EngineResult;", "_pipelineResult", "Lcom/omniagent/app/core/model/AnalysisPipelineResult;", "_reasoningSteps", "", "", "_uiState", "Lcom/omniagent/app/viewmodel/OmniAgentUiState;", "classificationResult", "Lkotlinx/coroutines/flow/StateFlow;", "getClassificationResult", "()Lkotlinx/coroutines/flow/StateFlow;", "engineResult", "getEngineResult", "inactivityJob", "Lkotlinx/coroutines/Job;", "pipelineResult", "getPipelineResult", "reasoningSteps", "getReasoningSteps", "recentLogs", "Lkotlinx/coroutines/flow/Flow;", "Lcom/omniagent/app/core/model/AnalysisLog;", "getRecentLogs", "()Lkotlinx/coroutines/flow/Flow;", "sharedPrefs", "Landroid/content/SharedPreferences;", "uiState", "getUiState", "analyzeInput", "", "userInput", "authenticateAdmin", "", "pin", "clearAllLogs", "clearPendingAnalysisState", "clearResults", "decryptLogResult", "encrypted", "dismissError", "resetInactivityTimer", "restorePendingAnalysisState", "runDemo", "type", "savePendingAnalysisState", "input", "switchTab", "tab", "Lcom/omniagent/app/viewmodel/DashboardTab;", "switchToUserRole", "toggleDemoMode", "enabled", "app_debug"})
public final class OmniAgentViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.omniagent.app.domain.repository.AnalysisRepository repository = null;
    private final long INACTIVITY_TIMEOUT_MS = 300000L;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job inactivityJob;
    @org.jetbrains.annotations.NotNull()
    private final android.content.SharedPreferences sharedPrefs = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.omniagent.app.viewmodel.OmniAgentUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.omniagent.app.viewmodel.OmniAgentUiState> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.omniagent.app.core.model.ClassificationResult> _classificationResult = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.omniagent.app.core.model.ClassificationResult> classificationResult = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.omniagent.app.core.model.EngineResult> _engineResult = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.omniagent.app.core.model.EngineResult> engineResult = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<java.lang.String>> _reasoningSteps = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<java.lang.String>> reasoningSteps = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.omniagent.app.core.model.AnalysisPipelineResult> _pipelineResult = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.omniagent.app.core.model.AnalysisPipelineResult> pipelineResult = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.omniagent.app.core.model.AnalysisLog>> recentLogs = null;
    
    public OmniAgentViewModel(@org.jetbrains.annotations.NotNull()
    com.omniagent.app.domain.repository.AnalysisRepository repository, @org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.omniagent.app.viewmodel.OmniAgentUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.omniagent.app.core.model.ClassificationResult> getClassificationResult() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.omniagent.app.core.model.EngineResult> getEngineResult() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<java.lang.String>> getReasoningSteps() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.omniagent.app.core.model.AnalysisPipelineResult> getPipelineResult() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.omniagent.app.core.model.AnalysisLog>> getRecentLogs() {
        return null;
    }
    
    /**
     * Run the full analysis pipeline with user input.
     */
    public final void analyzeInput(@org.jetbrains.annotations.NotNull()
    java.lang.String userInput) {
    }
    
    /**
     * Switch active dashboard tab.
     */
    public final void switchTab(@org.jetbrains.annotations.NotNull()
    com.omniagent.app.viewmodel.DashboardTab tab) {
    }
    
    /**
     * Clear current results.
     */
    public final void clearResults() {
    }
    
    /**
     * Clear all logs (admin only).
     */
    public final void clearAllLogs() {
    }
    
    /**
     * Authenticate as admin.
     */
    public final boolean authenticateAdmin(@org.jetbrains.annotations.NotNull()
    java.lang.String pin) {
        return false;
    }
    
    /**
     * Switch to user role.
     */
    public final void switchToUserRole() {
    }
    
    /**
     * Decrypt log result (admin only).
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String decryptLogResult(@org.jetbrains.annotations.NotNull()
    java.lang.String encrypted) {
        return null;
    }
    
    public final void dismissError() {
    }
    
    public final void toggleDemoMode(boolean enabled) {
    }
    
    public final void runDemo(@org.jetbrains.annotations.NotNull()
    java.lang.String type) {
    }
    
    /**
     * Resets the inactivity timer. If it expires, user is logged out of Admin mode.
     */
    private final void resetInactivityTimer() {
    }
    
    private final void savePendingAnalysisState(java.lang.String input) {
    }
    
    private final void clearPendingAnalysisState() {
    }
    
    private final void restorePendingAnalysisState() {
    }
}