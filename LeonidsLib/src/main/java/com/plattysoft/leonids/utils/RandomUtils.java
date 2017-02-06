package com.plattysoft.leonids.utils;

import java.util.Random;

/**
 * Created by artsiomkaliaha on 2/6/17.
 */

public class RandomUtils {

    private static Random sRnd;

    static {
        sRnd = new Random();
    }

    public static float nextFloat(float min, float max) {
        return sRnd.nextFloat() * (min - max) + min;
    }
}