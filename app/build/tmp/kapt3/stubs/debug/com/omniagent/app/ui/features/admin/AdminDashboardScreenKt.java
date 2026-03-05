package com.omniagent.app.ui.features.admin;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0016\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0007\u001a8\u0010\u0005\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\nH\u0007\u001aF\u0010\u000b\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\nH\u0007\u001a\b\u0010\r\u001a\u00020\u0001H\u0007\u00a8\u0006\u000e"}, d2 = {"AdminAnalyticsTab", "", "logs", "", "Lcom/omniagent/app/core/model/AnalysisLog;", "AdminAuditLogsTab", "onDecryptLog", "Lkotlin/Function1;", "", "onClearLogs", "Lkotlin/Function0;", "AdminDashboardScreen", "onExitAdmin", "AdminDiagnosticsTab", "app_debug"})
public final class AdminDashboardScreenKt {
    
    /**
     * Admin Dashboard Screen
     * Displays analytics, system diagnostics, and audit logs exclusively to Admins.
     */
    @androidx.compose.runtime.Composable()
    public static final void AdminDashboardScreen(@org.jetbrains.annotations.NotNull()
    java.util.List<com.omniagent.app.core.model.AnalysisLog> logs, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClearLogs, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, java.lang.String> onDecryptLog, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onExitAdmin) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void AdminAnalyticsTab(@org.jetbrains.annotations.NotNull()
    java.util.List<com.omniagent.app.core.model.AnalysisLog> logs) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void AdminAuditLogsTab(@org.jetbrains.annotations.NotNull()
    java.util.List<com.omniagent.app.core.model.AnalysisLog> logs, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, java.lang.String> onDecryptLog, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClearLogs) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void AdminDiagnosticsTab() {
    }
}