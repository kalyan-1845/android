package com.omniagent.app.ui.features.dashboard;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000n\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0007\n\u0002\b\u0002\u001a$\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0003\u001a\u00a2\u0001\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00102\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00142\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u00142\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00010\u00182\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00142\b\b\u0002\u0010\u001b\u001a\u00020\u001cH\u0007\u001a\u0010\u0010\u001d\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0003\u001a,\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u001f\u001a\u00020\u00122\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00142\u0006\u0010!\u001a\u00020\"H\u0003\u001aJ\u0010#\u001a\u00020\u00012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00102\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00010\u00182\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00142\u0006\u0010!\u001a\u00020\"2\b\b\u0002\u0010\u001b\u001a\u00020\u001cH\u0003\u001a\u0098\u0001\u0010$\u001a\u00020\u00012\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00102\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00142\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00010\u00182\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00142\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0014H\u0003\u001a \u0010&\u001a\u00020\u00012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u00102\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0003\u001a6\u0010\'\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010(\u001a\u00020)2\b\b\u0002\u0010*\u001a\u00020\"H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b+\u0010,\u001aZ\u0010-\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\"0\u00142\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00010\u00182\u0012\u00100\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u00010\u00142\u0012\u00101\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0014H\u0003\u001a\u0010\u00102\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\nH\u0003\u001a\u0010\u00103\u001a\u00020\u00032\u0006\u00104\u001a\u00020\u0003H\u0002\u001a\u0015\u00105\u001a\u00020)2\u0006\u00106\u001a\u000207H\u0002\u00a2\u0006\u0002\u00108\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u00069"}, d2 = {"AnalysisEntry", "", "label", "", "value", "", "depth", "", "DashboardScreen", "uiState", "Lcom/omniagent/app/viewmodel/OmniAgentUiState;", "classificationResult", "Lcom/omniagent/app/core/model/ClassificationResult;", "engineResult", "Lcom/omniagent/app/core/model/EngineResult;", "reasoningSteps", "", "logs", "Lcom/omniagent/app/core/model/AnalysisLog;", "onAnalyze", "Lkotlin/Function1;", "onSwitchTab", "Lcom/omniagent/app/viewmodel/DashboardTab;", "onClearResults", "Lkotlin/Function0;", "onClearLogs", "onDecryptLog", "modifier", "Landroidx/compose/ui/Modifier;", "EngineResultContent", "LogEntryCard", "log", "onDecrypt", "isAdmin", "", "LogsTab", "OutputTab", "onUpdateInput", "ReasoningTab", "ResultRow", "valueColor", "Landroidx/compose/ui/graphics/Color;", "indent", "ResultRow-9LQNqLg", "(Ljava/lang/String;Ljava/lang/String;JZ)V", "SettingsTab", "onAuthenticateAdmin", "onSwitchToUser", "onToggleDemo", "onRunDemo", "TopBar", "formatKey", "key", "scoreColor", "score", "", "(F)J", "app_debug"})
public final class DashboardScreenKt {
    
    /**
     * Main Dashboard Screen — The OS-style control center.
     */
    @androidx.compose.runtime.Composable()
    public static final void DashboardScreen(@org.jetbrains.annotations.NotNull()
    com.omniagent.app.viewmodel.OmniAgentUiState uiState, @org.jetbrains.annotations.Nullable()
    com.omniagent.app.core.model.ClassificationResult classificationResult, @org.jetbrains.annotations.Nullable()
    com.omniagent.app.core.model.EngineResult engineResult, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> reasoningSteps, @org.jetbrains.annotations.NotNull()
    java.util.List<com.omniagent.app.core.model.AnalysisLog> logs, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onAnalyze, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.omniagent.app.viewmodel.DashboardTab, kotlin.Unit> onSwitchTab, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClearResults, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClearLogs, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, java.lang.String> onDecryptLog, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    /**
     * Top Bar with app branding and status indicators.
     */
    @androidx.compose.runtime.Composable()
    private static final void TopBar(com.omniagent.app.viewmodel.OmniAgentUiState uiState) {
    }
    
    /**
     * Output Tab — Displays structured analysis results.
     */
    @androidx.compose.runtime.Composable()
    private static final void OutputTab(com.omniagent.app.core.model.ClassificationResult classificationResult, com.omniagent.app.core.model.EngineResult engineResult, com.omniagent.app.viewmodel.OmniAgentUiState uiState, java.util.List<java.lang.String> reasoningSteps, java.util.List<com.omniagent.app.core.model.AnalysisLog> logs, kotlin.jvm.functions.Function1<? super java.lang.String, java.lang.String> onDecryptLog, kotlin.jvm.functions.Function0<kotlin.Unit> onClearResults, kotlin.jvm.functions.Function0<kotlin.Unit> onClearLogs, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onAnalyze, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onUpdateInput) {
    }
    
    /**
     * Engine Result Content — Parses and displays engine JSON output.
     */
    @androidx.compose.runtime.Composable()
    private static final void EngineResultContent(com.omniagent.app.core.model.EngineResult engineResult) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void AnalysisEntry(java.lang.String label, java.lang.Object value, int depth) {
    }
    
    /**
     * Reasoning Tab — Shows transparent decision-making process.
     */
    @androidx.compose.runtime.Composable()
    private static final void ReasoningTab(java.util.List<java.lang.String> reasoningSteps, com.omniagent.app.core.model.ClassificationResult classificationResult) {
    }
    
    /**
     * Logs Tab — Activity history viewer.
     */
    @androidx.compose.runtime.Composable()
    private static final void LogsTab(java.util.List<com.omniagent.app.core.model.AnalysisLog> logs, kotlin.jvm.functions.Function0<kotlin.Unit> onClearLogs, kotlin.jvm.functions.Function1<? super java.lang.String, java.lang.String> onDecryptLog, boolean isAdmin, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void LogEntryCard(com.omniagent.app.core.model.AnalysisLog log, kotlin.jvm.functions.Function1<? super java.lang.String, java.lang.String> onDecrypt, boolean isAdmin) {
    }
    
    /**
     * Settings Tab — Role management and app configuration.
     */
    @androidx.compose.runtime.Composable()
    private static final void SettingsTab(com.omniagent.app.viewmodel.OmniAgentUiState uiState, kotlin.jvm.functions.Function1<? super java.lang.String, java.lang.Boolean> onAuthenticateAdmin, kotlin.jvm.functions.Function0<kotlin.Unit> onSwitchToUser, kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onToggleDemo, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onRunDemo) {
    }
    
    private static final java.lang.String formatKey(java.lang.String key) {
        return null;
    }
    
    private static final long scoreColor(float score) {
        return 0L;
    }
}