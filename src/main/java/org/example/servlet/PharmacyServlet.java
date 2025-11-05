package org.example.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.context.AppContext;
import org.example.model.Pharmacy;
import org.example.service.PharmacyService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@WebServlet("/pharmacies")
public class PharmacyServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(PharmacyServlet.class.getName());

    private PharmacyService pharmacyService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            pharmacyService = AppContext.getInstance().getPharmacyService();
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
            List<Pharmacy> pharmacies;
            if (search != null) {
                pharmacies = pharmacyService.filter(search);
            } else if (sort != null && comparator != null) {
                pharmacies = pharmacyService.sort(sort, comparator);
            } else {
                pharmacies = pharmacyService.findAll();
            }
            req.setAttribute("pharmacies", pharmacies);
            req.getRequestDispatcher("/pharmacy/list.jsp").forward(req, resp);
        } else if (action.equals("edit")) {
            Integer id = Integer.parseInt(req.getParameter("id"));
            Optional<Pharmacy> pharmacy = Optional.ofNullable(pharmacyService.findById(id));
            req.setAttribute("pharmacy", pharmacy.orElse(null));
            req.getRequestDispatcher("/pharmacy/form.jsp").forward(req, resp);
        } else if (action.equals("delete")) {
            Integer id = Integer.parseInt(req.getParameter("id"));
            pharmacyService.delete(id);
            resp.sendRedirect("pharmacies");
        } else if (action.equals("new")) {
            req.getRequestDispatcher("/pharmacy/form.jsp").forward(req, resp);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String idStr = req.getParameter("id");
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        String workingHours = req.getParameter("workingHours");
        String wayFromCenter = req.getParameter("wayFromCenter");

        Pharmacy pharmacy = new Pharmacy(name, address, phone, workingHours, wayFromCenter);

        if (idStr == null || idStr.isBlank()) {
            pharmacyService.save(pharmacy);
        } else {
            pharmacy.setId(Integer.parseInt(idStr));
            pharmacyService.update(pharmacy);
        }

        resp.sendRedirect("pharmacies");
    }
}