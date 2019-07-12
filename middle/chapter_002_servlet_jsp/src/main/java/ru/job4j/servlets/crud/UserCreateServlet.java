package ru.job4j.servlets.crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

public class UserCreateServlet extends HttpServlet {
    private final Validate logicLayer = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        var writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>")
                .append("<html lang=\"en\">")
                .append("<head>")
                .append("<meta charset=\"UTF-8\">")
                .append("<title>Create User</title>")
                .append("</head>")
                .append("<body>")

                .append("<form action='").append(req.getContextPath()).append("/create' method='post'>")
                .append("id: <input type='text' name='id'><br>")
                .append("name: <input type='text' name='name'><br>")
                .append("login: <input type='text' name='login'><br>")
                .append("email: <input type='text' name='email'><br>")
                .append("<input type='submit' value='Create'>")
                .append("</form>")

                .append("</body>")
                .append("</html>")
                .flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var id = req.getParameter("id");
        var name = req.getParameter("name");
        var login = req.getParameter("login");
        var email = req.getParameter("email");

        var user = new User();

        user.setId(Integer.parseInt(id));
        user.setName(name);
        user.setLogin(login);
        user.setEmail(email);
        user.setDateCreated(LocalDate.now());

        logicLayer.add(user);

        doGet(req, resp);
    }
}
