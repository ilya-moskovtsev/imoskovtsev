package ru.job4j.servlets.crud;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public class AddOperation implements Operation {
    @Override
    public void apply(Validate logicLayer, HttpServletRequest req) {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");

        User user = new User();

        user.setName(name);
        user.setLogin(login);
        user.setEmail(email);
        user.setDateCreated(LocalDate.now());

        logicLayer.add(user);
    }
}
