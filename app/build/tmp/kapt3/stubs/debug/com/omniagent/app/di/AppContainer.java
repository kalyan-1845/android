package com.omniagent.app.di;

/**
 * Manual Dependency Injection Container.
 * Centralizes creation and management of core dependencies,
 * making the architecture "DI-Ready" without needing Hilt/Dagger yet.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0010\u001a\u00020\u00118FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0014\u0010\n\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006\u0015"}, d2 = {"Lcom/omniagent/app/di/AppContainer;", "", "applicationContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "analysisRepository", "Lcom/omniagent/app/domain/repository/AnalysisRepository;", "getAnalysisRepository", "()Lcom/omniagent/app/domain/repository/AnalysisRepository;", "analysisRepository$delegate", "Lkotlin/Lazy;", "database", "Lcom/omniagent/app/data/local/OmniAgentDatabase;", "getDatabase", "()Lcom/omniagent/app/data/local/OmniAgentDatabase;", "database$delegate", "pythonKernelManager", "Lcom/omniagent/app/kernel/PythonKernelManager;", "getPythonKernelManager", "()Lcom/omniagent/app/kernel/PythonKernelManager;", "pythonKernelManager$delegate", "app_debug"})
public final class AppContainer {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context applicationContext = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy database$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy pythonKernelManager$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy analysisRepository$delegate = null;
    
    public AppContainer(@org.jetbrains.annotations.NotNull()
    android.content.Context applicationContext) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.omniagent.app.data.local.OmniAgentDatabase getDatabase() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.omniagent.app.kernel.PythonKernelManager getPythonKernelManager() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.omniagent.app.domain.repository.AnalysisRepository getAnalysisRepository() {
        return null;
    }
}