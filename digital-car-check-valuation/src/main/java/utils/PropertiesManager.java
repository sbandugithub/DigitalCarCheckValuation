package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {
    private static Properties properties = new Properties();

    static {
        try (FileInputStream file = new FileInputStream("src/test/resources/config.properties")) {
            properties.load(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not load config.properties file.");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}