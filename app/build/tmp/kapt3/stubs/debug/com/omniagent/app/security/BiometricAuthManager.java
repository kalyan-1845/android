package com.omniagent.app.security;

/**
 * Manages Biometric Authentication (Fingerprint/Face) for Admin Unlock.
 * Should be called from the Activity/Fragment level.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0006\u001a\u00020\u0007J<\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\t0\u0010R\u0010\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0005\u00a8\u0006\u0011"}, d2 = {"Lcom/omniagent/app/security/BiometricAuthManager;", "", "activity", "error/NonExistentClass", "(Lerror/NonExistentClass;)V", "Lerror/NonExistentClass;", "isBiometricAvailable", "", "showBiometricPrompt", "", "title", "", "subtitle", "onSuccess", "Lkotlin/Function0;", "onError", "Lkotlin/Function1;", "app_debug"})
public final class BiometricAuthManager {
    @org.jetbrains.annotations.NotNull()
    private final error.NonExistentClass activity = null;
    
    public BiometricAuthManager(@org.jetbrains.annotations.NotNull()
    error.NonExistentClass activity) {
        super();
    }
    
    /**
     * Checks if biometrics are available and enrolled on the device.
     */
    public final boolean isBiometricAvailable() {
        return false;
    }
    
    /**
     * Prompts the user for biometric authentication.
     */
    public final void showBiometricPrompt(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String subtitle, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSuccess, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onError) {
    }
}