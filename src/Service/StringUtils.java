package Service;

public class StringUtils {

    public final static String EMPTY ="";

    public static boolean isEmpty(String s){
        return s==null || s.equals(EMPTY);
    }
}
