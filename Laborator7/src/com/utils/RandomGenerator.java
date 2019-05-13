package com.utils;

import java.util.Random;

public class RandomGenerator {
    private static RandomGenerator instance;

    private Random random;

    private RandomGenerator() {
        random = new Random();
    }

    public static RandomGenerator getInstance(){
        if (instance == null){
            instance = new RandomGenerator();
        }
        return instance;
    }

    public static int getRandomInt(int i) {
        return getInstance().random.nextInt(i);
    }
}
