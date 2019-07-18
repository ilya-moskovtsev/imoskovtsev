package ru.job4j.hibernate.todolist;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class AddAction implements Action {
    @Override
    public void apply(Validate logicLayer, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String json = req.getParameter("json");

        Gson gson = new Gson();

        Item item = gson.fromJson(json, Item.class);
        item.setCreated(LocalDate.now());
        logicLayer.addItem(item);

        List<Item> items = logicLayer.getItems();
        gson.toJson(items, resp.getWriter());
    }
}
