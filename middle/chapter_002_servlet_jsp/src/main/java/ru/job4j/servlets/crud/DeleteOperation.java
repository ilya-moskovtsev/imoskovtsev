package ru.job4j.servlets.crud;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteOperation implements Operation {
    @Override
    public void apply(Validate logicLayer, HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");

        User user = new User();
        user.setId(Integer.parseInt(id));

        logicLayer.delete(user);
    }
}
