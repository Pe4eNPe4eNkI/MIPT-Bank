package com.example.mipt_bank_app.StringHash;

import androidx.annotation.Nullable;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class StringHash {

    public StringHash(){}
    private byte[] salt_ = new byte[16];

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

    public String getSalt(){
        return Base64.getEncoder().encodeToString(salt_);
    }

    public void setSalt(byte[] salt){
        salt_ = salt;
    }

    /**
     * This method generate new salt
     */
    public void generateSalt(){
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt_);
    }

    /**
     * This method generate hash to your password with salt
     * @param password Your String password
     * @return String hash password
     */
    public String generateHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt_, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        return Base64.getEncoder().encodeToString(factory.generateSecret(spec).getEncoded());
    }
    /**
     * This method compare entered password and db password with salt
     * @param passwordFromDb password from db
     * @param saltFromDb salt from db
     * @param enteredPassword password from input
     * @return True or false(equals or not)
     */
    public boolean comparePasswords(String passwordFromDb, String saltFromDb, String enteredPassword) throws NoSuchAlgorithmException, InvalidKeySpecException {
        salt_ = Base64.getDecoder().decode(saltFromDb);
        String hashedEnteredPassword = generateHash(enteredPassword);
        return passwordFromDb.equals(hashedEnteredPassword);
    }
}
