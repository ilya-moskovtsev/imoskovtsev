package ru.job4j.servlets.crud;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateOperation implements Operation {
    @Override
    public void apply(Validate logicLayer, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        String country = req.getParameter("country");
        String state = req.getParameter("state");
        String city = req.getParameter("city");

        User user = logicLayer.findById(Integer.parseInt(id));

        if (user.getLogin().equals(req.getSession().getAttribute("login"))) {
            req.getSession().setAttribute("login", login);
        }

        user.setName(name);
        user.setLogin(login);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(Role.valueOf(role));
        user.setCountry(country);
        user.setState(state);
        user.setCity(city);

        logicLayer.update(user);

        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
