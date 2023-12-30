package com.example.mipt_bank_app.StringHash;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class StringHash {
    private byte[] salt_ = new byte[16];

    public byte[] getSalt(){
        return salt_;
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

        return Arrays.toString(factory.generateSecret(spec).getEncoded());
    }
}
