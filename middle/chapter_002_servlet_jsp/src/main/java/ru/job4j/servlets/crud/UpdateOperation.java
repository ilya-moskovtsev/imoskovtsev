package ru.job4j.servlets.crud;

import javax.servlet.http.HttpServletRequest;

public class UpdateOperation implements Operation {
    @Override
    public void apply(Validate logicLayer, HttpServletRequest req) {
        String id = req.getParameter("id");
        String newName = req.getParameter("name");

        User user = logicLayer.findById(Integer.parseInt(id));

        user.setName(newName);

        logicLayer.update(user);
    }
}
