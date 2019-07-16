package ru.job4j.servlets.crud;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

public class AddOperation implements Operation {
    @Override
    public void apply(Validate logicLayer, HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = new User();

        user.setName(name);
        user.setLogin(login);
        user.setEmail(email);
        user.setDateCreated(LocalDate.now());
        user.setPassword(password);
        user.setRole(Role.USER);

        logicLayer.add(user);
    }
}
