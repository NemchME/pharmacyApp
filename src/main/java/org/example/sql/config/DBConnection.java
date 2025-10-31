package org.example.sql.config;

import org.example.exception.DBException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection implements AutoCloseable {

    private static final Logger LOGGER = Logger.getLogger(DBConnection.class.getName());
    private Connection connection;

    public DBConnection() {
        try {
            Class.forName("org.h2.Driver");
            this.connection = DriverManager.getConnection(
                    DBConfig.URL,
                    DBConfig.USER,
                    DBConfig.PASSWORD
            );
            LOGGER.info("Успешное подключение к БД!");

            if (isDatabaseEmpty()) {
                executeSQLScripts();
                LOGGER.info("База данных инициализирована.");
            }

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Ошибка подключения к базе данных: " + e.getMessage(), e);
            throw new RuntimeException("Ошибка подключения к базе данных", e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void close() {

            try {
                if (connection != null && !connection.isClosed()) {
                    try (Statement stmt = connection.createStatement()) {
                        stmt.execute("SHUTDOWN");
                    } catch (Exception e) {
                        LOGGER.warning(e.getMessage());
                    }
                    connection.close();
                    LOGGER.info("Соединение с БД закрыто.");
                }
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "Ошибка при закрытии соединения", e);
            }


        deregisterDrivers();
    }


    private void executeSQLScripts() {
        try (Statement statement = getConnection().createStatement()) {
            LOGGER.info("Запуск SQL-скриптов...");
            String schemaSql = Files.readString(Path.of("src/main/java/org/example/sql/schema.sql"));
            statement.execute(schemaSql);

            String dataSql = Files.readString(Path.of("src/main/java/org/example/sql/data.sql"));
            statement.execute(dataSql);

            LOGGER.info("SQL-скрипты успешно выполнены!");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Ошибка при выполнении SQL-скриптов", e);
            throw new DBException(e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Ошибка при чтении SQL-файлов", e);
        }
    }

    private boolean isDatabaseEmpty() throws SQLException {
        String query = "SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='PUBLIC'";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            rs.next();
            boolean empty = rs.getInt(1) == 0;
            return empty;
        }
    }

    private void deregisterDrivers() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                LOGGER.info("Драйвер отменен: " + driver.getClass().getName());
            } catch (SQLException e) {
                LOGGER.severe("Ошибка при отмене регистрации драйвера: " + driver.getClass().getName() + " - " + e.getMessage());
            }
        }
    }
}
