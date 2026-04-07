package com.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {

    private static final String URL = "jdbc:postgresql://localhost:5432/test_jdbc";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Hugo099!";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
