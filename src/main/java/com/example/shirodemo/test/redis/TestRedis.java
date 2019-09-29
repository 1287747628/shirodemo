package com.example.shirodemo.test.redis;

import java.util.Random;
import java.util.UUID;

public class TestRedis {

    public static void main(String[] args) {
        Random random = new Random(10);
        for (int i = 0; i < 100; i++) {
            int first = random.nextInt(8) + 1;
            int randNo = UUID.randomUUID().toString().hashCode();
            if (randNo < 0) {
                randNo = -randNo;
            }
            System.out.println( String.format("%16d", randNo));
        }
    }

}
