package com.crypto.course.signature;

import com.crypto.course.asymmetric.AsymmetricEncryptionUtils;
import org.junit.jupiter.api.Test;

import javax.xml.bind.DatatypeConverter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyPair;

import static org.junit.jupiter.api.Assertions.*;

class DigitalSignatureUtilsTest {

    @Test
    void signatureRoutine() throws Exception {
        URL uri = this.getClass().getClassLoader().getResource("demo.txt");
        Path path = Paths.get(uri.toURI());
        byte[] input = Files.readAllBytes(path);

        KeyPair keyPair = AsymmetricEncryptionUtils.generateRSAKeyPair();
        byte[] signature = DigitalSignatureUtils.createDigitalSignature(input, keyPair.getPrivate());
        System.out.println(DatatypeConverter.printHexBinary(signature));

        assertTrue(DigitalSignatureUtils.verifyDigitalSignature(input, signature, keyPair.getPublic()));
    }
}