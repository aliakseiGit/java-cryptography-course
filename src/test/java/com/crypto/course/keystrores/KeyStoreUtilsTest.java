package com.crypto.course.keystrores;

import com.crypto.course.symmetric.SymmetricEncryptionUtils;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

import java.security.KeyStore;

import static org.junit.jupiter.api.Assertions.*;

class KeyStoreUtilsTest {
    @Test
    void createPrivateKeyJavaKeyStore() throws Exception {
        SecretKey secretKey = SymmetricEncryptionUtils.createAESKey();
        String secretKeyHex = DatatypeConverter.printHexBinary(secretKey.getEncoded());

        KeyStore keyStore = KeyStoreUtils.createPrivateKeyJavaKeyStore("password", "keyAlias",
                secretKey, "keyPassword");
        assertNotNull(keyStore);

        keyStore.load(null, "password".toCharArray());
        KeyStore.ProtectionParameter entryPassword = new KeyStore.PasswordProtection("keyPassword".toCharArray());
        KeyStore.SecretKeyEntry resultKeyEntry = (KeyStore.SecretKeyEntry) keyStore.getEntry("keyAlias", entryPassword);
        SecretKey result = resultKeyEntry.getSecretKey();
        String resultKeyHex = DatatypeConverter.printHexBinary(result.getEncoded());
        assertEquals(secretKeyHex, resultKeyHex);
    }
}