package org.example.sql.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection implements AutoCloseable {

    private Connection connection;

    public DBConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(
                    DBConfig.URL,
                    DBConfig.USER,
                    DBConfig.PASSWORD
            );
        } catch (Exception e) {
            throw new RuntimeException("Ошибка подключения к базе данных: " + e.getMessage(), e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Соединение с БД закрыто.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
