package com.example.loadtestdb.util;

import com.mongodb.assertions.Assertions;

import java.security.SecureRandom;
import java.util.Random;

public class RandomString {
    private final static Random random = new SecureRandom();
    private final static String alphabet;

    static {
        int u1 = 65, u2 = 90;
        int l1 = 97, l2 = 122;
        int n1 = 48, n2 = 57;

        var builder = new StringBuilder(u2 - u1 + l2 - l1 + n1 - n2);

        for (int i = u1; i <= u2; i++) {
            builder.append((char) i);
        }
        for (int i = l1; i <= l2; i++) {
            builder.append((char) i);
        }
        for (int i = n1; i <= n2; i++) {
            builder.append((char) i);
        }


        alphabet = builder.toString();
    }

    public static String random(int length) {
        Assertions.isTrue("length > 0", length > 0);
        var builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int rnd = random.nextInt(alphabet.length());
            builder.append(alphabet.charAt(rnd));
        }
        return builder.toString();
    }
}
