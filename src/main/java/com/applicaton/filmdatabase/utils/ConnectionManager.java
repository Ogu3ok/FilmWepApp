package com.applicaton.filmdatabase.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {
    private final static String url = "jdbc:mysql://localhost:3306/films";
    private final static String user = "root";
    private final static String password = "my-secret-pw";
    private ConnectionManager() {
    }

    public static Connection open() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new SQLException("Connection failed");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
