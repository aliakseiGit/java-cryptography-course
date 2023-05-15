package com.crypto.course.hash;

import org.junit.jupiter.api.Test;

import javax.xml.bind.DatatypeConverter;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class HashUtilsTest {

    @Test
    void generateRandomSalt() {
        byte[] salt = HashUtils.generateRandomSalt();
        assertNotNull(salt);
        System.out.println(DatatypeConverter.printHexBinary(salt));
    }

    @Test
    void createSHA2Hash() throws Exception {
        byte[] salt = HashUtils.generateRandomSalt();
        String valueToHash = UUID.randomUUID().toString();
        System.out.println("Value to hash: " + valueToHash);
        byte[] hash = HashUtils.createSHA2Hash(valueToHash, salt);
        assertNotNull(hash);
        byte[] hash2 = HashUtils.createSHA2Hash(valueToHash, salt);
        assertEquals(DatatypeConverter.printHexBinary(hash), DatatypeConverter.printHexBinary(hash2));
    }

    @Test
    void verifyHashedPasswordRoutine() {
        String pass = "$$ome$^erComplEXp4$$word2#AS as an example of strong secret phrase";
        final String hashedPassword = HashUtils.hashPassword(pass);
        System.out.println(hashedPassword);
        assertTrue(HashUtils.verifyPassword(pass, hashedPassword));
    }
}