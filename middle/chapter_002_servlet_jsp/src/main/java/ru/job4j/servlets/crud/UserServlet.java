package ru.job4j.servlets.crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

/**
 * Presentation layer.
 */
public class UserServlet extends HttpServlet {

    private final ValidateService logicLayer = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = logicLayer.findAll();

        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream(), true);
        writer.println(users);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        // Many if statements can be replaced with
        // https://github.com/peterarsentev/code_quality_principles#2-dispatch-pattern-instead-of-multiple-if-statements-and-switch-anti-pattern
        // or
        // https://www.baeldung.com/java-replace-if-statements
        if ("add".equals(action)) {
            String id = req.getParameter("id");
            String name = req.getParameter("name");
            String login = req.getParameter("login");
            String email = req.getParameter("email");

            User user = new User();

            user.setId(Integer.parseInt(id));
            user.setName(name);
            user.setLogin(login);
            user.setEmail(email);
            user.setDateCreated(LocalDate.now());

            logicLayer.add(user);
        } else if ("update".equals(action)) {
            String id = req.getParameter("id");
            String newName = req.getParameter("name");

            User user = logicLayer.findById(Integer.parseInt(id));

            user.setName(newName);

            logicLayer.update(user);
        } else if ("delete".equals(action)) {
            String id = req.getParameter("id");

            User user = new User();
            user.setId(Integer.parseInt(id));

            logicLayer.delete(user);
        }
        doGet(req, resp);
    }
}
