package ru.job4j.hibernate.todolist;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetAllAction implements Action {
    @Override
    public void apply(Validate logicLayer, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Gson gson = new Gson();

        List<Item> items = logicLayer.getItems();
        gson.toJson(items, resp.getWriter());
    }
}
