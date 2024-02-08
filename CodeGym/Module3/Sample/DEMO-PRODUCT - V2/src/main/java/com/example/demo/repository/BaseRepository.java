package com.example.demo.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseRepository {
    private static String jdbcUrl = "jdbc:mysql://localhost:3306/test";
    private static String jdbcUsername = "root";
    private static String jdbcPassword = "levanquy";
    private static Connection connection;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BaseRepository() {
    }

    public static Connection getConnectionJavaToDB() {
        return connection;
    }
}
