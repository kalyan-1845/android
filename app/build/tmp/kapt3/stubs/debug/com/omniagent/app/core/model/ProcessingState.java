package com.omniagent.app.core.model;

/**
 * Simple enum for processing state.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/omniagent/app/core/model/ProcessingState;", "", "(Ljava/lang/String;I)V", "IDLE", "PROCESSING", "SUCCESS", "ERROR", "app_debug"})
public enum ProcessingState {
    /*public static final*/ IDLE /* = new IDLE() */,
    /*public static final*/ PROCESSING /* = new PROCESSING() */,
    /*public static final*/ SUCCESS /* = new SUCCESS() */,
    /*public static final*/ ERROR /* = new ERROR() */;
    
    ProcessingState() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.omniagent.app.core.model.ProcessingState> getEntries() {
        return null;
    }
}