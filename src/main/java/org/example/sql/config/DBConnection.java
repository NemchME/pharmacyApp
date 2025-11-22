package org.example.sql.config;

import org.example.exception.DBException;

import java.io.IOException;
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

            executeSQLScripts();
            LOGGER.info("База данных инициализирована.");

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

            statement.execute("DROP ALL OBJECTS");
            LOGGER.info("База данных очищена (DROP ALL OBJECTS).");
            LOGGER.info("Запуск SQL-скриптов...");

            String schemaSql = readResourceFile("schema.sql");
            statement.execute(schemaSql);

            String dataSql = readResourceFile("data.sql");
            statement.execute(dataSql);

            LOGGER.info("SQL-скрипты успешно выполнены!");
        } catch (SQLException | IOException e) {
            LOGGER.log(Level.SEVERE, "Ошибка при выполнении SQL-скриптов", e);
            throw new DBException(e.getMessage(), e);
        }
    }

    private String readResourceFile(String resourcePath) throws IOException {
        try (var inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new IOException("Файл не найден в ресурсах: " + resourcePath);
            }
            return new String(inputStream.readAllBytes(), java.nio.charset.StandardCharsets.UTF_8);
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
