package org.example.servlet;

import org.example.model.Pharmacy;
import org.example.repository.impl.jdbc.PharmacyRepositoryImpl;
import org.example.service.PharmacyService;
import org.example.sql.config.DBConnection;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/pharmacies")
public class PharmacyServlet extends HttpServlet {

    private DBConnection connection;
    private PharmacyService pharmacyService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            connection = new DBConnection();
            pharmacyService = new PharmacyService(new PharmacyRepositoryImpl(connection));
        } catch (Exception e) {
            throw new ServletException("Не удалось инициализировать PharmacyServlet: " + e.getMessage(), e);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action == null || action.equals("list")) {
            List<Pharmacy> pharmacies = pharmacyService.findAll();
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
        Pharmacy pharmacy = new Pharmacy(
                req.getParameter("name"),
                req.getParameter("address"),
                req.getParameter("phone"),
                req.getParameter("workingHours"),
                req.getParameter("wayFromCenter")
        );

        if (idStr == null || idStr.isBlank()) {
            pharmacyService.save(pharmacy);
        } else {
            pharmacy.setId(Integer.parseInt(idStr));
            pharmacyService.update(pharmacy);
        }

        resp.sendRedirect("pharmacies");
    }
}