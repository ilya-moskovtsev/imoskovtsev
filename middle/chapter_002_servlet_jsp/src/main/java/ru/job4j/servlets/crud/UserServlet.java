package ru.job4j.servlets.crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Presentation layer.
 */
public class UserServlet extends HttpServlet {

    private final Validate logicLayer = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<User> users = logicLayer.findAll();

        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream(), true);
        writer.println(users);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");

        // Many if statements can be replaced with
        // https://github.com/peterarsentev/code_quality_principles#2-dispatch-pattern-instead-of-multiple-if-statements-and-switch-anti-pattern
        // or
        // https://www.baeldung.com/java-replace-if-statements
        Operation targetOperation = OperatorFactory
                .getOperation(action)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Action"));
        targetOperation.apply(logicLayer, req);
        doGet(req, resp);
    }
}
