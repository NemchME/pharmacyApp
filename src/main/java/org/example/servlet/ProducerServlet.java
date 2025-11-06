package org.example.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.context.AppContext;
import org.example.model.Producer;
import org.example.service.ProducerService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@WebServlet("/producers")
public class ProducerServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ProducerServlet.class.getName());

    private ProducerService producerService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            producerService = AppContext.getInstance().getProducerService();
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

        int page = 1;
        int size = 10;

        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
            req.setAttribute("currentPage", page);
        }
        if (req.getParameter("size") != null) {
            size = Integer.parseInt(req.getParameter("size"));
            int totalPages = producerService.getTotalPages(size);
            req.setAttribute("currentSize", size);
            req.setAttribute("totalPages", totalPages);
        }

        if (action == null || action.equals("list")) {
            String search = req.getParameter("search");
            String sort = req.getParameter("sort");
            String comparator = req.getParameter("comparator");
            List<Producer> producers;

            if (search != null) {
                producers = producerService.filter(search);
                req.setAttribute("search", search);
            } else if (sort != null && comparator != null) {
                producers = producerService.sort(sort, comparator);
            } else {
                producers = producerService.findAll(page, size);
            }

            req.setAttribute("producers", producers);
            req.getRequestDispatcher("/producer/list.jsp").forward(req, resp);
        } else if (action.equals("edit")) {
            Integer id = Integer.parseInt(req.getParameter("id"));
            Optional<Producer> producer = Optional.ofNullable(producerService.findById(id));
            req.setAttribute("producer", producer.orElse(null));
            req.getRequestDispatcher("/producer/form.jsp").forward(req, resp);
        } else if (action.equals("delete")) {
            Integer id = Integer.parseInt(req.getParameter("id"));
            producerService.delete(id);
            resp.sendRedirect("producers?page=1&size=5");
        } else if (action.equals("new")) {
            req.getRequestDispatcher("/producer/form.jsp").forward(req, resp);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String idStr = req.getParameter("id");
        String name = req.getParameter("name");
        String country = req.getParameter("country");

        Producer producer = new Producer(name, country);

        if (idStr == null || idStr.isBlank()) {
            producerService.save(producer);
        } else {
            producer.setId(Integer.parseInt(idStr));
            producerService.update(producer);
        }

        resp.sendRedirect("producers?page=1&size=5");
    }
}
