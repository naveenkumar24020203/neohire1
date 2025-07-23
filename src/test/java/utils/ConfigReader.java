package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties prop;

    public static String getProperty(String key) {
        if (prop == null) {
            try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
                prop = new Properties();
                prop.load(fis);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return prop.getProperty(key);
    }
}
