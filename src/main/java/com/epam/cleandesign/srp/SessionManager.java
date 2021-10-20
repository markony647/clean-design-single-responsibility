package com.epam.cleandesign.srp;

import javax.mail.Session;
import java.util.Properties;

public class SessionManager {

    private static final String host = "localhost";

    public Session prepareSession() {
        Properties properties = prepareProperties();
        return Session.getDefaultInstance(properties);
    }

    private Properties prepareProperties() {
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        return properties;
    }
}
