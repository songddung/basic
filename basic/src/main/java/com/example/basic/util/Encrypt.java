package com.example.basic.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

@Component
public class Encrypt {
    public String encode(String raw) {
        String hex = null;
        try {
            // String raw = "password1234";
            // 양념 String rawAndSalt = "abcd1234";
            MessageDigest md;
            md = MessageDigest.getInstance("SHA-256");

            //양념안침
            md.update(raw.getBytes());
            hex = String.format("%064x", new BigInteger(1, md.digest()));
            // System.out.println("raw의 해시값 : " + hex);
            
            //양념침
            // md.update(rawAndSalt.getBytes());
            // hex = String.format("%064x", new BigInteger(1, md.digest()));
            // System.out.println("raw+saltㄴ의 해시값 : " + hex);
            
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hex;
    }
}
