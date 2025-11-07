package org.example.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.context.AppContext;
import org.example.model.AvailabilityInfo;
import org.example.service.AvailabilityInfoService;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/availabilityInfo")
public class AvailabilityInfoServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(AvailabilityInfoServlet.class.getName());

    private AvailabilityInfoService availabilityInfoService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            availabilityInfoService = AppContext.getInstance().getAvailabilityInfoService();
            LOGGER.info("Запуск сервлета: " + this.getClass().getName());
        } catch (Exception e) {
            throw new ServletException("Не удалось инициализировать " + this.getClass().getName() + ": " +
                    e.getMessage(), e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String medicineIdParam = req.getParameter("id");
        if (medicineIdParam != null) {
            int medicineId = Integer.parseInt(medicineIdParam);
            List<AvailabilityInfo> availabilityInfoList = availabilityInfoService.getPharmaciesByMedicineId(medicineId);
            req.setAttribute("availabilityList", availabilityInfoList);
        }
        req.setAttribute("id", medicineIdParam);
        req.getRequestDispatcher("/availabilityInfo/list.jsp").forward(req, resp);
    }
}
