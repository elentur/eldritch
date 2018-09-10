package utils;

public class MathUtils {
    public static int clamp(int value, int max, int min) {
        return Math.min(max, Math.max(min, value));
    }
}
