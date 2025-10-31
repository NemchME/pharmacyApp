package org.example.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.context.AppContext;
import org.example.model.User;
import org.example.service.UserService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@WebServlet("/users")
public class UserServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(UserServlet.class.getName());

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            userService = AppContext.getInstance().getUserService();
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
            List<User> users = userService.findAll();
            req.setAttribute("users", users);
            req.getRequestDispatcher("/user/list.jsp").forward(req, resp);
        } else if (action.equals("edit")) {
            Integer id = Integer.parseInt(req.getParameter("id"));
            Optional<User> user = Optional.ofNullable(userService.findById(id));
            req.setAttribute("user", user.orElse(null));
            req.getRequestDispatcher("/user/form.jsp").forward(req, resp);
        } else if (action.equals("delete")) {
            Integer id = Integer.parseInt(req.getParameter("id"));
            userService.delete(id);
            resp.sendRedirect("users");
        } else if (action.equals("new")) {
            req.getRequestDispatcher("/user/form.jsp").forward(req, resp);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String idStr = req.getParameter("id");
        String username = req.getParameter("username");
        String passwordHash = req.getParameter("passwordHash");
        String email = req.getParameter("email");
        String role = req.getParameter("role");

        User user = new User(username, passwordHash, email, role);

        if (idStr == null || idStr.isBlank()) {
            userService.save(user);
        } else {
            user.setId(Integer.parseInt(idStr));
            userService.update(user);
        }

        resp.sendRedirect("users");
    }
}
