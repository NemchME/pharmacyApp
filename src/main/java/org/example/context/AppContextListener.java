package org.example.context;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.example.sql.config.DBConnection;

import java.util.logging.Logger;

@WebListener
public class AppContextListener implements ServletContextListener {

    private static final Logger LOGGER = Logger.getLogger(AppContextListener.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        AppContext.getInstance();
        LOGGER.info("AppContext инициализирован при старте приложения");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (AppContext.getInstance().getConnection() != null) {
            AppContext.getInstance().getConnection().close();
            LOGGER.info("Соединение с БД закрыто.");

        }
        ServletContextListener.super.contextDestroyed(sce);
        LOGGER.info("Завершение работы приложения...");
    }
}
