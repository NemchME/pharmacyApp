package org.example.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.context.AppContext;
import org.example.model.AvailabilityOfMedicine;
import org.example.service.AvailabilityOfMedicineService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@WebServlet("/availabilityOfMedicines")
public class AvailabilityOfMedicineServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(AvailabilityOfMedicineServlet.class.getName());

    private AvailabilityOfMedicineService availabilityOfMedicineService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            availabilityOfMedicineService = AppContext.getInstance().getAvailabilityOfMedicineService();
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
            List<AvailabilityOfMedicine> availabilityOfMedicines;
            if (req.getParameter("page") != null && req.getParameter("size") != null) {
                int page = Integer.parseInt(req.getParameter("page"));
                int size = Integer.parseInt(req.getParameter("size"));
                availabilityOfMedicines = availabilityOfMedicineService.findAll(page, size);
                int totalPages = availabilityOfMedicineService.getTotalPages(size);

                req.setAttribute("currentPage", page);
                req.setAttribute("currentSize", size);
                req.setAttribute("totalPages", totalPages);

            } else if (search != null) {
                availabilityOfMedicines = availabilityOfMedicineService.filter(search);
            } else if (sort != null && comparator != null) {
                availabilityOfMedicines = availabilityOfMedicineService.sort(sort, comparator);
            } else {
                availabilityOfMedicines = availabilityOfMedicineService.findAll();
            }
            req.setAttribute("availabilityOfMedicines", availabilityOfMedicines);
            req.getRequestDispatcher("/availabilityOfMedicine/list.jsp").forward(req, resp);
        } else if (action.equals("edit")) {
            Integer id = Integer.parseInt(req.getParameter("id"));
            Optional<AvailabilityOfMedicine> availabilityOfMedicine = Optional
                    .ofNullable(availabilityOfMedicineService.findById(id));
            req.setAttribute("availabilityOfMedicine", availabilityOfMedicine.orElse(null));
            req.getRequestDispatcher("/availabilityOfMedicine/form.jsp").forward(req, resp);
        } else if (action.equals("delete")) {
            Integer id = Integer.parseInt(req.getParameter("id"));
            availabilityOfMedicineService.delete(id);
            resp.sendRedirect("availabilityOfMedicines");
        } else if (action.equals("new")) {
            req.getRequestDispatcher("/availabilityOfMedicine/form.jsp").forward(req, resp);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String idStr = req.getParameter("id");
        Integer pharmacyId = Integer.parseInt(req.getParameter("pharmacyId"));
        Integer medicineId = Integer.parseInt(req.getParameter("medicineId"));
        Float price = Float.parseFloat(req.getParameter("price"));
        Integer quantity = Integer.parseInt(req.getParameter("quantity"));

        AvailabilityOfMedicine availabilityOfMedicine =
                new AvailabilityOfMedicine(pharmacyId, medicineId, price, quantity);

        if (idStr == null || idStr.isBlank()) {
            availabilityOfMedicineService.save(availabilityOfMedicine);
        } else {
            availabilityOfMedicine.setId(Integer.parseInt(idStr));
            availabilityOfMedicineService.update(availabilityOfMedicine);
        }

        resp.sendRedirect("availabilityOfMedicines");
    }
}
