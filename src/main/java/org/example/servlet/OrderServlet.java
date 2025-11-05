package org.example.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.context.AppContext;
import org.example.model.Order;
import org.example.service.OrderService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@WebServlet("/orders")
public class OrderServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(OrderServlet.class.getName());

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
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action == null || action.equals("list")) {
            String search = req.getParameter("search");
            String sort = req.getParameter("sort");
            String comparator = req.getParameter("comparator");
            List<Order> orders;
            if (req.getParameter("page") != null && req.getParameter("size") != null) {
                int page = Integer.parseInt(req.getParameter("page"));
                int size = Integer.parseInt(req.getParameter("size"));
                orders = orderService.findAll(page, size);
                int totalPages = orderService.getTotalPages(size);

                req.setAttribute("currentPage", page);
                req.setAttribute("currentSize", size);
                req.setAttribute("totalPages", totalPages);

            } else if (search != null) {
                orders = orderService.filter(search);
            } else if (sort != null && comparator != null) {
                orders = orderService.sort(sort, comparator);
            } else {
                orders = orderService.findAll();
            }
            req.setAttribute("orders", orders);
            req.getRequestDispatcher("/order/list.jsp").forward(req, resp);
        } else if (action.equals("edit")) {
            Integer id = Integer.parseInt(req.getParameter("id"));
            Optional<Order> order = Optional.ofNullable(orderService.findById(id));
            req.setAttribute("order", order.orElse(null));
            req.getRequestDispatcher("/order/form.jsp").forward(req, resp);
        } else if (action.equals("delete")) {
            Integer id = Integer.parseInt(req.getParameter("id"));
            orderService.delete(id);
            resp.sendRedirect("orders");
        } else if (action.equals("new")) {
            req.getRequestDispatcher("/order/form.jsp").forward(req, resp);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String idStr = req.getParameter("id");
        Integer userId = Integer.parseInt(req.getParameter("userId"));
        Integer medicineId = Integer.parseInt(req.getParameter("medicineId"));
        Integer pharmacyId = Integer.parseInt(req.getParameter("pharmacyId"));
        Integer quantity = Integer.parseInt(req.getParameter("quantity"));
        String status = req.getParameter("status");

        Order order = new Order(userId, medicineId, pharmacyId, quantity, status);

        if (idStr == null || idStr.isBlank()) {
            orderService.save(order);
        } else {
            order.setId(Integer.parseInt(idStr));
            orderService.update(order);
        }

        resp.sendRedirect("orders");
    }
}
