package utils;

import java.util.Random;

public class RNG {
    private static Random random = new Random();
    public static int getInt(int bound){
        return random.nextInt(bound);
    }

    public static double getDouble(){
        return random.nextDouble();
    }
}
