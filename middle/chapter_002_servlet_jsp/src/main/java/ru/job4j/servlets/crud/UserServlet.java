package ru.job4j.servlets.crud;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Presentation layer.
 */
public class UserServlet extends HttpServlet {
    private final Validate logicLayer = ValidateService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        User user;

        // Many if statements can be replaced with
        // https://github.com/peterarsentev/code_quality_principles#2-dispatch-pattern-instead-of-multiple-if-statements-and-switch-anti-pattern
        // or
        // https://www.baeldung.com/java-replace-if-statements
        Operation targetOperation = OperatorFactory
                .getOperation(action)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Action"));
        targetOperation.apply(logicLayer, req);
        resp.sendRedirect(String.format("%s/index.jsp", req.getContextPath()));
    }
}
