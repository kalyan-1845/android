package com.omniagent.app.viewmodel;

/**
 * Main ViewModel — manages all UI state and orchestrates the analysis pipeline.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010&\u001a\u00020\'2\u0006\u0010(\u001a\u00020\u000eJ\u000e\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u000eJ\u0006\u0010,\u001a\u00020\'J\u0006\u0010-\u001a\u00020\'J\u000e\u0010.\u001a\u00020\u000e2\u0006\u0010/\u001a\u00020\u000eJ\u0006\u00100\u001a\u00020\'J\u000e\u00101\u001a\u00020\'2\u0006\u00102\u001a\u000203J\u0006\u00104\u001a\u00020\'R\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0019\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u001d\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u001d\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0\r0\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u000e\u0010\"\u001a\u00020#X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00100\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0014\u00a8\u00065"}, d2 = {"Lcom/omniagent/app/viewmodel/OmniAgentViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_classificationResult", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/omniagent/app/data/model/ClassificationResult;", "_engineResult", "Lcom/omniagent/app/data/model/EngineResult;", "_pipelineResult", "Lcom/omniagent/app/data/model/AnalysisPipelineResult;", "_reasoningSteps", "", "", "_uiState", "Lcom/omniagent/app/viewmodel/OmniAgentUiState;", "classificationResult", "Lkotlinx/coroutines/flow/StateFlow;", "getClassificationResult", "()Lkotlinx/coroutines/flow/StateFlow;", "database", "Lcom/omniagent/app/data/db/OmniAgentDatabase;", "engineResult", "getEngineResult", "pipelineResult", "getPipelineResult", "reasoningSteps", "getReasoningSteps", "recentLogs", "Lkotlinx/coroutines/flow/Flow;", "Lcom/omniagent/app/data/model/AnalysisLog;", "getRecentLogs", "()Lkotlinx/coroutines/flow/Flow;", "repository", "Lcom/omniagent/app/data/repository/OmniAgentRepository;", "uiState", "getUiState", "analyzeInput", "", "userInput", "authenticateAdmin", "", "pin", "clearAllLogs", "clearResults", "decryptLogResult", "encrypted", "dismissError", "switchTab", "tab", "Lcom/omniagent/app/viewmodel/DashboardTab;", "switchToUserRole", "app_debug"})
public final class OmniAgentViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.omniagent.app.data.db.OmniAgentDatabase database = null;
    @org.jetbrains.annotations.NotNull()
    private final com.omniagent.app.data.repository.OmniAgentRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.omniagent.app.viewmodel.OmniAgentUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.omniagent.app.viewmodel.OmniAgentUiState> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.omniagent.app.data.model.ClassificationResult> _classificationResult = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.omniagent.app.data.model.ClassificationResult> classificationResult = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.omniagent.app.data.model.EngineResult> _engineResult = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.omniagent.app.data.model.EngineResult> engineResult = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<java.lang.String>> _reasoningSteps = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<java.lang.String>> reasoningSteps = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.omniagent.app.data.model.AnalysisPipelineResult> _pipelineResult = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.omniagent.app.data.model.AnalysisPipelineResult> pipelineResult = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.omniagent.app.data.model.AnalysisLog>> recentLogs = null;
    
    public OmniAgentViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.omniagent.app.viewmodel.OmniAgentUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.omniagent.app.data.model.ClassificationResult> getClassificationResult() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.omniagent.app.data.model.EngineResult> getEngineResult() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<java.lang.String>> getReasoningSteps() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.omniagent.app.data.model.AnalysisPipelineResult> getPipelineResult() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.omniagent.app.data.model.AnalysisLog>> getRecentLogs() {
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
}