package ru.job4j.hibernate.todolist;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DoneAction implements Action {
    @Override
    public void apply(Validate logicLayer, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String taskId = req.getParameter("taskId");
        String done = req.getParameter("done");

        Gson gson = new Gson();

        Item item = new Item();
        item.setId(Integer.parseInt(taskId));
        item.setDone(Boolean.parseBoolean(done));
        logicLayer.done(item);

        List<Item> items = logicLayer.getItems();
        gson.toJson(items, resp.getWriter());
    }
}
