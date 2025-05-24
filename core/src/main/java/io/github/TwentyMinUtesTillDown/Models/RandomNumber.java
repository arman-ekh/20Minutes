package io.github.TwentyMinUtesTillDown.Models;

import java.util.Random;

public class RandomNumber {
    private static Random random = new Random();


    public static int getRandomNumberWithBoundaries(int origin, int bound) {
        return random.nextInt(bound - origin) + origin;
    }

    public static float getRandomFloatWithBoundaries(float origin , float bound){
        return random.nextFloat(origin,bound);
    }

    public static int getRandomNumber() {
        return random.nextInt();
    }

    public static Random getRandom() {
        return random;
    }
}
