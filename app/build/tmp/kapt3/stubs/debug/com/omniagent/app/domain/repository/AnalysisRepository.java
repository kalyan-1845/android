package com.omniagent.app.domain.repository;

/**
 * Domain Interface for Analysis Operations.
 * Isolates UI and Use Cases from concrete implementation details.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\bH\u00a6@\u00a2\u0006\u0002\u0010\tJ\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H&J\u0014\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\rH&J\u000e\u0010\u0010\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\tJ\u000e\u0010\u0011\u001a\u00020\u0012H\u00a6@\u00a2\u0006\u0002\u0010\tJ\u001c\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\r2\u0006\u0010\u0014\u001a\u00020\u0005H&J\u001e\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\r2\b\b\u0002\u0010\u0016\u001a\u00020\u0012H&J \u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0019\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u001aJ\u001c\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\r2\u0006\u0010\u001c\u001a\u00020\u0005H&\u00a8\u0006\u001d"}, d2 = {"Lcom/omniagent/app/domain/repository/AnalysisRepository;", "", "classifyInput", "Lcom/omniagent/app/core/model/ClassificationResult;", "userInput", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearAllLogs", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "decryptLogResult", "encryptedJson", "getAllLogs", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/omniagent/app/core/model/AnalysisLog;", "getKernelReasoningLog", "getLogCount", "", "getLogsByModule", "module", "getRecentLogs", "limit", "runFullPipeline", "Lcom/omniagent/app/core/model/AnalysisPipelineResult;", "userRole", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchLogs", "query", "app_debug"})
public abstract interface AnalysisRepository {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object classifyInput(@org.jetbrains.annotations.NotNull()
    java.lang.String userInput, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.omniagent.app.core.model.ClassificationResult> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object runFullPipeline(@org.jetbrains.annotations.NotNull()
    java.lang.String userInput, @org.jetbrains.annotations.NotNull()
    java.lang.String userRole, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.omniagent.app.core.model.AnalysisPipelineResult> $completion);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.omniagent.app.core.model.AnalysisLog>> getAllLogs();
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.omniagent.app.core.model.AnalysisLog>> getRecentLogs(int limit);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.omniagent.app.core.model.AnalysisLog>> getLogsByModule(@org.jetbrains.annotations.NotNull()
    java.lang.String module);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.omniagent.app.core.model.AnalysisLog>> searchLogs(@org.jetbrains.annotations.NotNull()
    java.lang.String query);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object clearAllLogs(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLogCount(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.lang.String decryptLogResult(@org.jetbrains.annotations.NotNull()
    java.lang.String encryptedJson);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getKernelReasoningLog(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion);
    
    /**
     * Domain Interface for Analysis Operations.
     * Isolates UI and Use Cases from concrete implementation details.
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}