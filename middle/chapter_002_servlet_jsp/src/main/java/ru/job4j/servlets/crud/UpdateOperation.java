package ru.job4j.servlets.crud;

import javax.servlet.http.HttpServletRequest;

public class UpdateOperation implements Operation {
    @Override
    public void apply(Validate logicLayer, HttpServletRequest req) {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");

        User user = logicLayer.findById(Integer.parseInt(id));

        user.setName(name);
        user.setLogin(login);
        user.setEmail(email);

        logicLayer.update(user);
    }
}
