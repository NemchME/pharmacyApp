package org.example.sql.config;

import org.example.exception.DBException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;

public class DBConnection implements AutoCloseable {

    private Connection connection;

    public DBConnection() {
        try {
            Class.forName("org.h2.Driver");
            this.connection = DriverManager.getConnection(
                    DBConfig.URL,
                    DBConfig.USER,
                    DBConfig.PASSWORD
            );
            System.out.println("Успешное подключение к БД!");
            if (isDatabaseEmpty()) {
                executeSQLScripts();
                System.out.println("База данных инициализирована.");
            }

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

    private void executeSQLScripts() {
        try (Statement statement = getConnection().createStatement()) {
            System.out.println("Запуск SQL-скриптов...");
            String sql = Files.readString(Path.of("src/main/java/org/example/sql/schema.sql"));
            statement.execute(sql);
            sql = Files.readString(Path.of("src/main/java/org/example/sql/data.sql"));
            statement.execute(sql);
            System.out.println("SQL-скрипты успешно запущены!");
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isDatabaseEmpty() throws SQLException {
        String query = "SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='PUBLIC'";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            rs.next();
            return rs.getInt(1) == 0;
        }
    }
}
