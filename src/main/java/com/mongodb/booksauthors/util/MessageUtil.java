package com.mongodb.booksauthors.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MessageUtil {

    private static final String MESSAGE_FILE_PATH = "src/main/resources/messages.properties";

    private static final Properties properties = load();

    private static Properties load() {
        Properties props = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream(MESSAGE_FILE_PATH)) {
            props.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException("Error on message file!", e);
        }

        return props;
    }

    public static String getMessage(String code) {
        return properties.getProperty(code);
    }
}
