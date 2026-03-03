package com.omniagent.app.viewmodel;

/**
 * Represents the full UI state of the dashboard.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Bk\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\u0006\u0012\b\b\u0002\u0010\n\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0012J\t\u0010!\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0003H\u00c6\u0003J\t\u0010#\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\t\u0010%\u001a\u00020\bH\u00c6\u0003J\t\u0010&\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\'\u001a\u00020\u0006H\u00c6\u0003J\t\u0010(\u001a\u00020\fH\u00c6\u0003J\t\u0010)\u001a\u00020\u000eH\u00c6\u0003J\t\u0010*\u001a\u00020\u0010H\u00c6\u0003Jo\u0010+\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010,\u001a\u00020\u00032\b\u0010-\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010.\u001a\u00020/H\u00d6\u0001J\t\u00100\u001a\u00020\u0006H\u00d6\u0001R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0011\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u001aR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u001aR\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\t\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0018R\u0011\u0010\n\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0018R\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 \u00a8\u00061"}, d2 = {"Lcom/omniagent/app/viewmodel/OmniAgentUiState;", "", "isProcessing", "", "hasResult", "error", "", "activeTab", "Lcom/omniagent/app/viewmodel/DashboardTab;", "lastModule", "lastModuleName", "lastConfidence", "", "processingTimeMs", "", "currentRole", "Lcom/omniagent/app/core/model/UserRole;", "isDemoMode", "(ZZLjava/lang/String;Lcom/omniagent/app/viewmodel/DashboardTab;Ljava/lang/String;Ljava/lang/String;DJLcom/omniagent/app/core/model/UserRole;Z)V", "getActiveTab", "()Lcom/omniagent/app/viewmodel/DashboardTab;", "getCurrentRole", "()Lcom/omniagent/app/core/model/UserRole;", "getError", "()Ljava/lang/String;", "getHasResult", "()Z", "getLastConfidence", "()D", "getLastModule", "getLastModuleName", "getProcessingTimeMs", "()J", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
public final class OmniAgentUiState {
    private final boolean isProcessing = false;
    private final boolean hasResult = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String error = null;
    @org.jetbrains.annotations.NotNull()
    private final com.omniagent.app.viewmodel.DashboardTab activeTab = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String lastModule = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String lastModuleName = null;
    private final double lastConfidence = 0.0;
    private final long processingTimeMs = 0L;
    @org.jetbrains.annotations.NotNull()
    private final com.omniagent.app.core.model.UserRole currentRole = null;
    private final boolean isDemoMode = false;
    
    public OmniAgentUiState(boolean isProcessing, boolean hasResult, @org.jetbrains.annotations.Nullable()
    java.lang.String error, @org.jetbrains.annotations.NotNull()
    com.omniagent.app.viewmodel.DashboardTab activeTab, @org.jetbrains.annotations.NotNull()
    java.lang.String lastModule, @org.jetbrains.annotations.NotNull()
    java.lang.String lastModuleName, double lastConfidence, long processingTimeMs, @org.jetbrains.annotations.NotNull()
    com.omniagent.app.core.model.UserRole currentRole, boolean isDemoMode) {
        super();
    }
    
    public final boolean isProcessing() {
        return false;
    }
    
    public final boolean getHasResult() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getError() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.omniagent.app.viewmodel.DashboardTab getActiveTab() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLastModule() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLastModuleName() {
        return null;
    }
    
    public final double getLastConfidence() {
        return 0.0;
    }
    
    public final long getProcessingTimeMs() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.omniagent.app.core.model.UserRole getCurrentRole() {
        return null;
    }
    
    public final boolean isDemoMode() {
        return false;
    }
    
    public OmniAgentUiState() {
        super();
    }
    
    public final boolean component1() {
        return false;
    }
    
    public final boolean component10() {
        return false;
    }
    
    public final boolean component2() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.omniagent.app.viewmodel.DashboardTab component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    public final double component7() {
        return 0.0;
    }
    
    public final long component8() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.omniagent.app.core.model.UserRole component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.omniagent.app.viewmodel.OmniAgentUiState copy(boolean isProcessing, boolean hasResult, @org.jetbrains.annotations.Nullable()
    java.lang.String error, @org.jetbrains.annotations.NotNull()
    com.omniagent.app.viewmodel.DashboardTab activeTab, @org.jetbrains.annotations.NotNull()
    java.lang.String lastModule, @org.jetbrains.annotations.NotNull()
    java.lang.String lastModuleName, double lastConfidence, long processingTimeMs, @org.jetbrains.annotations.NotNull()
    com.omniagent.app.core.model.UserRole currentRole, boolean isDemoMode) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}