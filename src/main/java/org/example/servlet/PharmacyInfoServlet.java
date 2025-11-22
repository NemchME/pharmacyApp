package org.example.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.context.AppContext;
import org.example.model.PharmacyInfo;
import org.example.service.AvailabilityOfMedicineService;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/pharmacyInfo")
public class PharmacyInfoServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(PharmacyInfoServlet.class.getName());

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String pharmacyIdParam = req.getParameter("pharmacyId");
        if (pharmacyIdParam != null) {
            int pharmacyId = Integer.parseInt(pharmacyIdParam);
            List<PharmacyInfo> pharmacyInfoList = availabilityOfMedicineService.findByPharmacyId(pharmacyId);
            req.setAttribute("pharmacyInfoList", pharmacyInfoList);
        }
        req.setAttribute("pharmacyId", pharmacyIdParam);
        req.getRequestDispatcher("/pharmacyInfo/list.jsp").forward(req, resp);
    }
}
