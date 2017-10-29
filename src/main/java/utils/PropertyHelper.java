package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyHelper {

    public static String getProperty(String propertyName) {
        Properties properties = new Properties();
        InputStream input =
                Thread.currentThread()
                        .getContextClassLoader()
                        .getResourceAsStream(getPropertyFileName());
        try {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties.getProperty(propertyName);
    }

    private static String getPropertyFileName() {
        return System.getProperty("env") + ".properties";
    }
}