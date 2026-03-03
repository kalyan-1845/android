package com.omniagent.app.security;

/**
 * AES-256-GCM encryption using Android Keystore.
 * All sensitive logs are encrypted before database storage.
 * Zero external dependencies — uses Android's built-in crypto.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004J\u000e\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0004J\b\u0010\u000e\u001a\u00020\u000fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/omniagent/app/security/CryptoManager;", "", "()V", "ANDROID_KEYSTORE", "", "GCM_TAG_LENGTH", "", "KEY_ALIAS", "SEPARATOR", "TRANSFORMATION", "decrypt", "encryptedData", "encrypt", "plaintext", "getOrCreateSecretKey", "Ljavax/crypto/SecretKey;", "app_debug"})
public final class CryptoManager {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_ALIAS = "omniagent_master_key";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String ANDROID_KEYSTORE = "AndroidKeyStore";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TRANSFORMATION = "AES/GCM/NoPadding";
    private static final int GCM_TAG_LENGTH = 128;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String SEPARATOR = "|";
    @org.jetbrains.annotations.NotNull()
    public static final com.omniagent.app.security.CryptoManager INSTANCE = null;
    
    private CryptoManager() {
        super();
    }
    
    /**
     * Get or create the AES key in Android Keystore.
     */
    private final javax.crypto.SecretKey getOrCreateSecretKey() {
        return null;
    }
    
    /**
     * Encrypt plaintext string using AES-256-GCM.
     * Returns Base64-encoded string: "IV|CIPHERTEXT"
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String encrypt(@org.jetbrains.annotations.NotNull()
    java.lang.String plaintext) {
        return null;
    }
    
    /**
     * Decrypt AES-256-GCM encrypted string.
     * Expects Base64-encoded string: "IV|CIPHERTEXT"
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String decrypt(@org.jetbrains.annotations.NotNull()
    java.lang.String encryptedData) {
        return null;
    }
}