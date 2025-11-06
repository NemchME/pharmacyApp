package org.example.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.context.AppContext;
import org.example.service.StatsService;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

@WebServlet("/stats")
public class StatsServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(StatsServlet.class.getName());

    private StatsService statsService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            statsService = AppContext.getInstance().getIndexService();
            LOGGER.info("Запуск сервлета: " + this.getClass().getName());
        } catch (Exception e) {
            throw new ServletException("Не удалось инициализировать " + this.getClass().getName() + ": " +
                    e.getMessage(), e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        LOGGER.info("IndexServlet DO GET STARTED");
        Map<String, Integer> tableCounts = statsService.getTableCounts();
        LOGGER.info(tableCounts.toString());
        req.setAttribute("tableCounts", tableCounts);
        req.getRequestDispatcher("stats/list.jsp").forward(req, resp);
    }
}