package org.example.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.context.AppContext;
import org.example.model.Medicine;
import org.example.service.MedicineService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@WebServlet("/medicines")
public class MedicineServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(MedicineServlet.class.getName());

    private MedicineService medicineService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            medicineService = AppContext.getInstance().getMedicineService();
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
            List<Medicine> medicines;
            if (req.getParameter("page") != null && req.getParameter("size") != null) {
                int page = Integer.parseInt(req.getParameter("page"));
                int size = Integer.parseInt(req.getParameter("size"));
                medicines = medicineService.findAll(page, size);
                int totalPages = medicineService.getTotalPages(size);

                req.setAttribute("currentPage", page);
                req.setAttribute("currentSize", size);
                req.setAttribute("totalPages", totalPages);

            } else if (search != null) {
                medicines = medicineService.filter(search);
            } else if (sort != null && comparator != null) {
                medicines = medicineService.sort(sort, comparator);
            } else {
                medicines = medicineService.findAll();
            }
            req.setAttribute("medicines", medicines);
            req.getRequestDispatcher("/medicine/list.jsp").forward(req, resp);
        } else if (action.equals("edit")) {
            Integer id = Integer.parseInt(req.getParameter("id"));
            Optional<Medicine> medicine = Optional.ofNullable(medicineService.findById(id));
            req.setAttribute("medicine", medicine.orElse(null));
            req.getRequestDispatcher("/medicine/form.jsp").forward(req, resp);
        } else if (action.equals("delete")) {
            Integer id = Integer.parseInt(req.getParameter("id"));
            medicineService.delete(id);
            resp.sendRedirect("medicines");
        } else if (action.equals("new")) {
            req.getRequestDispatcher("/medicine/form.jsp").forward(req, resp);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String idStr = req.getParameter("id");
        String tradeName = req.getParameter("tradeName");
        String inn = req.getParameter("inn");
        String dosage = req.getParameter("dosage");
        String form = req.getParameter("form");
        int producerId = Integer.parseInt(req.getParameter("producerId"));

        Medicine medicine = new Medicine(tradeName, inn, dosage, form, producerId);

        if (idStr == null || idStr.isBlank()) {
            medicineService.save(medicine);
        } else {
            medicine.setId(Integer.parseInt(idStr));
            medicineService.update(medicine);
        }

        resp.sendRedirect("medicines");
    }
}
