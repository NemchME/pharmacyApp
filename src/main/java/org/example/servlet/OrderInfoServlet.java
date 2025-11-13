package org.example.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.context.AppContext;
import org.example.model.OrderInfo;
import org.example.service.OrderService;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/orderInfo")
public class OrderInfoServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(OrderInfoServlet.class.getName());

    private OrderService orderService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            orderService = AppContext.getInstance().getOrderService();
            LOGGER.info("Запуск сервлета: " + this.getClass().getName());
        } catch (Exception e) {
            throw new ServletException("Не удалось инициализировать " + this.getClass().getName() + ": " +
                    e.getMessage(), e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String userIdParam = req.getParameter("userId");
        if (userIdParam != null) {
            int userId = Integer.parseInt(userIdParam);
            List<OrderInfo> orderInfoList = orderService.findByUserId(userId);
            req.setAttribute("orderInfoList", orderInfoList);
        }
        req.setAttribute("userId", userIdParam);
        req.getRequestDispatcher("/orderInfo/list.jsp").forward(req, resp);
    }
}
