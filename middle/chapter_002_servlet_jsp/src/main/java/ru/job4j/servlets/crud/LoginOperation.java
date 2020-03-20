package ru.job4j.servlets.crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginOperation implements Operation {
    @Override
    public void apply(Validate logicLayer, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (logicLayer.isValid(login, password)) {
            HttpSession session = req.getSession();
            session.setAttribute("login", login);
        } else {
            req.setAttribute("error", "invalid login or password");
            req.getRequestDispatcher("/WEB-INF/views/Login.jsp").forward(req, resp);
        }
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
