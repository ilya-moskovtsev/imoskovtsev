package ru.job4j.hibernate.todolist;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Presentation layer.
 */
@WebServlet("/")
public class ToDoList extends HttpServlet {
    private final Validate logicLayer = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/ToDoList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();

        Item item = gson.fromJson(req.getReader(), Item.class);
        item.setCreated(LocalDateTime.now());
        logicLayer.addItem(item);

        List<Item> items = logicLayer.getItems();
        gson.toJson(items, resp.getWriter());
    }
}
