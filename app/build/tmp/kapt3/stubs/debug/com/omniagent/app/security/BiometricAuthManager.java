package com.omniagent.app.security;

/**
 * Manages Biometric Authentication (Fingerprint/Face) for Admin Unlock.
 * Should be called from the Activity/Fragment level.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J<\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\b0\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/omniagent/app/security/BiometricAuthManager;", "", "activity", "Landroidx/fragment/app/FragmentActivity;", "(Landroidx/fragment/app/FragmentActivity;)V", "isBiometricAvailable", "", "showBiometricPrompt", "", "title", "", "subtitle", "onSuccess", "Lkotlin/Function0;", "onError", "Lkotlin/Function1;", "app_debug"})
public final class BiometricAuthManager {
    @org.jetbrains.annotations.NotNull()
    private final androidx.fragment.app.FragmentActivity activity = null;
    
    public BiometricAuthManager(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.FragmentActivity activity) {
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