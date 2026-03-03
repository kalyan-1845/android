package com.omniagent.app.ui.features.dashboard;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000h\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0002\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0003\u001a\u00ec\u0001\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u000e2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\u00122\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\u00162\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00190\u00122\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00010\u00162\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00122\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u00122\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00122\b\b\u0002\u0010\u001e\u001a\u00020\u001fH\u0007\u001a\u0010\u0010 \u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\fH\u0003\u001a,\u0010!\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020\u00102\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00122\u0006\u0010$\u001a\u00020\u0019H\u0003\u001a@\u0010%\u001a\u00020\u00012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u000e2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\u00162\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00122\u0006\u0010$\u001a\u00020\u0019H\u0003\u001aF\u0010&\u001a\u00020\u00012\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0007\u001a\u00020\b2\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00122\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u0016H\u0003\u001a \u0010\'\u001a\u00020\u00012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0003\u001a6\u0010(\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010)\u001a\u00020*2\b\b\u0002\u0010+\u001a\u00020\u0019H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b,\u0010-\u001aZ\u0010.\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00190\u00122\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00010\u00162\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u00122\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0012H\u0003\u001a\u0010\u0010/\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\bH\u0003\u001a\u0010\u00100\u001a\u00020\u00032\u0006\u00101\u001a\u00020\u0003H\u0002\u001a\u0015\u00102\u001a\u00020*2\u0006\u00103\u001a\u000204H\u0002\u00a2\u0006\u0002\u00105\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u00066"}, d2 = {"AnalysisEntry", "", "label", "", "value", "", "DashboardScreen", "uiState", "Lcom/omniagent/app/viewmodel/OmniAgentUiState;", "classificationResult", "Lcom/omniagent/app/core/model/ClassificationResult;", "engineResult", "Lcom/omniagent/app/core/model/EngineResult;", "reasoningSteps", "", "logs", "Lcom/omniagent/app/core/model/AnalysisLog;", "onAnalyze", "Lkotlin/Function1;", "onSwitchTab", "Lcom/omniagent/app/viewmodel/DashboardTab;", "onClearResults", "Lkotlin/Function0;", "onClearLogs", "onAuthenticateAdmin", "", "onSwitchToUser", "onDecryptLog", "onToggleDemo", "onRunDemo", "modifier", "Landroidx/compose/ui/Modifier;", "EngineResultContent", "LogEntryCard", "log", "onDecrypt", "isAdmin", "LogsTab", "OutputTab", "ReasoningTab", "ResultRow", "valueColor", "Landroidx/compose/ui/graphics/Color;", "indent", "ResultRow-9LQNqLg", "(Ljava/lang/String;Ljava/lang/String;JZ)V", "SettingsTab", "TopBar", "formatKey", "key", "scoreColor", "score", "", "(F)J", "app_debug"})
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
    kotlin.jvm.functions.Function1<? super java.lang.String, java.lang.Boolean> onAuthenticateAdmin, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSwitchToUser, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, java.lang.String> onDecryptLog, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onToggleDemo, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onRunDemo, @org.jetbrains.annotations.NotNull()
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
    private static final void OutputTab(com.omniagent.app.core.model.ClassificationResult classificationResult, com.omniagent.app.core.model.EngineResult engineResult, com.omniagent.app.viewmodel.OmniAgentUiState uiState, kotlin.jvm.functions.Function1<? super java.lang.String, java.lang.String> onDecryptLog, kotlin.jvm.functions.Function0<kotlin.Unit> onClearResults) {
    }
    
    /**
     * Engine Result Content — Parses and displays engine JSON output.
     */
    @androidx.compose.runtime.Composable()
    private static final void EngineResultContent(com.omniagent.app.core.model.EngineResult engineResult) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void AnalysisEntry(java.lang.String label, java.lang.Object value) {
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
    private static final void LogsTab(java.util.List<com.omniagent.app.core.model.AnalysisLog> logs, kotlin.jvm.functions.Function0<kotlin.Unit> onClearLogs, kotlin.jvm.functions.Function1<? super java.lang.String, java.lang.String> onDecryptLog, boolean isAdmin) {
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