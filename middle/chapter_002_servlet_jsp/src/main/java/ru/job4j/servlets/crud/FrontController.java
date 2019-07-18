package ru.job4j.servlets.crud;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Presentation layer.
 */
@MultipartConfig
public class FrontController extends HttpServlet {
    private final Validate logicLayer = ValidateService.getInstance();

    private static Map<String, String> pathsToViews = new HashMap<>();

    static {
        pathsToViews.put("/", "/ListUsers.jsp");
        pathsToViews.put("/create", "/CreateUser.jsp");
        pathsToViews.put("/edit", "/EditUser.jsp");
        pathsToViews.put("/upload", "/UploadFile.jsp");
        pathsToViews.put("/download", "/DownloadFile.jsp");
        pathsToViews.put("/login", "/Login.jsp");
        pathsToViews.put("/json", "/JsonPerson.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", logicLayer.findAll());
        req.setAttribute("files", logicLayer.getFiles());

        String servletPath = req.getServletPath();
        String view = pathsToViews.getOrDefault(servletPath, "/ListUsers.jsp");
        String path = String.format("/WEB-INF/views%s", view);
        req.getRequestDispatcher(path).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String action = req.getParameter("action");

        // Many if statements can be replaced with
        // https://github.com/peterarsentev/code_quality_principles#2-dispatch-pattern-instead-of-multiple-if-statements-and-switch-anti-pattern
        // or
        // https://www.baeldung.com/java-replace-if-statements
        Operation targetOperation = OperatorFactory
                .getOperation(action)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Action"));
        targetOperation.apply(logicLayer, req, resp);
//        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
