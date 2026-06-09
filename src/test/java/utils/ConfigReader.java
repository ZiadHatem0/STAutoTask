package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties props = new Properties();

    static {
        try (InputStream in = ConfigReader.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (in != null) {
                props.load(in);
            }
        } catch (IOException e) {
            System.err.println("[ConfigReader] Could not load config.properties: " + e.getMessage());
        }
    }

    public static String get(String key) {
        return props.getProperty(key, "");
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        String value = props.getProperty(key);
        return value != null ? Boolean.parseBoolean(value.trim()) : defaultValue;
    }
}