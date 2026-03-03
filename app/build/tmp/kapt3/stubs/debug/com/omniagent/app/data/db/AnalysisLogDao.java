package com.omniagent.app.data.db;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000b0\nH\'J\u0018\u0010\f\u001a\u0004\u0018\u00010\u00072\u0006\u0010\r\u001a\u00020\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u000e\u0010\u0010\u001a\u00020\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000b0\n2\u0006\u0010\u0013\u001a\u00020\u0014H\'J\u001c\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000b0\n2\u0006\u0010\u0016\u001a\u00020\u0011H\'J\u0016\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\b\u00a8\u0006\u0018"}, d2 = {"Lcom/omniagent/app/data/db/AnalysisLogDao;", "", "clearAllLogs", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteLog", "log", "Lcom/omniagent/app/data/model/AnalysisLog;", "(Lcom/omniagent/app/data/model/AnalysisLog;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllLogs", "Lkotlinx/coroutines/flow/Flow;", "", "getLogById", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLogCount", "", "getLogsByModule", "module", "", "getRecentLogs", "limit", "insertLog", "app_debug"})
@androidx.room.Dao()
public abstract interface AnalysisLogDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertLog(@org.jetbrains.annotations.NotNull()
    com.omniagent.app.data.model.AnalysisLog log, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM analysis_logs ORDER BY timestamp DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.omniagent.app.data.model.AnalysisLog>> getAllLogs();
    
    @androidx.room.Query(value = "SELECT * FROM analysis_logs ORDER BY timestamp DESC LIMIT :limit")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.omniagent.app.data.model.AnalysisLog>> getRecentLogs(int limit);
    
    @androidx.room.Query(value = "SELECT * FROM analysis_logs WHERE classifiedModule = :module ORDER BY timestamp DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.omniagent.app.data.model.AnalysisLog>> getLogsByModule(@org.jetbrains.annotations.NotNull()
    java.lang.String module);
    
    @androidx.room.Query(value = "SELECT * FROM analysis_logs WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLogById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.omniagent.app.data.model.AnalysisLog> $completion);
    
    @androidx.room.Query(value = "DELETE FROM analysis_logs")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object clearAllLogs(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM analysis_logs")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLogCount(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteLog(@org.jetbrains.annotations.NotNull()
    com.omniagent.app.data.model.AnalysisLog log, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}