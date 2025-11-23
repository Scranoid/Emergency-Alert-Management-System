
package com.eams.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class DBConnection {
    private static String url;
    private static String user;
    private static String password;

    static {
        try (InputStream is = DBConnection.class.getClassLoader().getResourceAsStream("application.properties")) {
            Properties props = new Properties();
            if (is != null) {
                props.load(is);
                url = props.getProperty("jdbc.url");
                user = props.getProperty("jdbc.username");
                password = props.getProperty("jdbc.password");
                Class.forName(props.getProperty("jdbc.driver"));
            } else {
                throw new RuntimeException("application.properties not found in classpath");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load DB properties", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
