package utils;

import lombok.extern.java.Log;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

@Log
public class ResourceUtil {

    private static String language = "english";

    private static HashMap<String, Properties> allProperties = new HashMap<>();

    public static String get(String key, String name, String... args) {
        String path = "./resources/language/" + language + "/" + name + ".properties";
        Properties properties = allProperties.get(path);

        if (properties == null) {

            File file = new File(path);
            if (!file.exists()) {
                log.warning("No properties file found for " + name + " at " + path);
                return key;
            }
            try {
                properties = new Properties();
                properties.load(new FileReader(file));
                allProperties.put(path, properties);
            } catch (IOException e) {
                e.printStackTrace();
                return key;
            }
        }
        String value = properties.getProperty(key);
        if (value == null) {
            return key;
        }
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                value = value.replace("{" + i + "}", args[i]);
            }
        }
        return value;
    }

    public static String get(String key, Class c, String... args) {
        String name = c.getSimpleName().toLowerCase();
        return get(key, name, args);

    }

}
