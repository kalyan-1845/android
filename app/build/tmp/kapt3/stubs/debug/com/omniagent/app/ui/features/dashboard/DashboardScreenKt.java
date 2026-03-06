package com.omniagent.app.ui.features.dashboard;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000Z\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007\u001aF\u0010\b\u001a\u00020\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\u00052\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000eH\u0007\u001a\b\u0010\u0010\u001a\u00020\u0001H\u0007\u001a\u00a2\u0001\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u000e2\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00010\u000e2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00010\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00010\u001c2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000e2\b\b\u0002\u0010\u001e\u001a\u00020\u001fH\u0007\u001a\u0016\u0010 \u001a\u00020\u00012\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00010\u001cH\u0007\u001a\b\u0010!\u001a\u00020\u0001H\u0007\u00a8\u0006\""}, d2 = {"ChatBubble", "", "message", "", "isUser", "", "timestamp", "", "ChatScreen", "logs", "", "Lcom/omniagent/app/core/model/AnalysisLog;", "isProcessing", "onAnalyze", "Lkotlin/Function1;", "onDecryptLog", "CommunitiesScreen", "DashboardScreen", "uiState", "Lcom/omniagent/app/viewmodel/OmniAgentUiState;", "classificationResult", "Lcom/omniagent/app/core/model/ClassificationResult;", "engineResult", "Lcom/omniagent/app/core/model/EngineResult;", "reasoningSteps", "onSwitchTab", "Lcom/omniagent/app/viewmodel/DashboardTab;", "onClearResults", "Lkotlin/Function0;", "onClearLogs", "modifier", "Landroidx/compose/ui/Modifier;", "SettingsScreen", "StatusScreen", "app_debug"})
public final class DashboardScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
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
    
    @androidx.compose.runtime.Composable()
    public static final void ChatScreen(@org.jetbrains.annotations.NotNull()
    java.util.List<com.omniagent.app.core.model.AnalysisLog> logs, boolean isProcessing, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onAnalyze, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, java.lang.String> onDecryptLog) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ChatBubble(@org.jetbrains.annotations.NotNull()
    java.lang.String message, boolean isUser, long timestamp) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void StatusScreen() {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void CommunitiesScreen() {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void SettingsScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClearResults) {
    }
}