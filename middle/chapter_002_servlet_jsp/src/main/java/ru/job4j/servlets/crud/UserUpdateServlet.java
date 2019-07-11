package ru.job4j.servlets.crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserUpdateServlet extends HttpServlet {
    private final Validate logicLayer = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var id = req.getParameter("id");
        var user = logicLayer.findById(Integer.parseInt(id));

        resp.setContentType("text/html");
        var writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>")
                .append("<html lang=\"en\">")
                .append("<head>")
                .append("<meta charset=\"UTF-8\">")
                .append("<title>Edit User</title>")
                .append("</head>")
                .append("<body>")

                .append("<form action='").append(req.getContextPath()).append("/edit' method='post'>")
                .append("id: <input type='text' name='id' value='").append(String.valueOf(user.getId())).append("'><br>")
                .append("name: <input type='text' name='name' value='").append(String.valueOf(user.getName())).append("'><br>")
                .append("login: <input type='text' name='login' value='").append(String.valueOf(user.getLogin())).append("'><br>")
                .append("email: <input type='text' name='email' value='").append(String.valueOf(user.getEmail())).append("'><br>")
                .append("<input type='submit' value='Edit'>")
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

        var user = logicLayer.findById(Integer.parseInt(id));

        user.setName(name);
        user.setLogin(login);
        user.setEmail(email);

        logicLayer.update(user);

        doGet(req, resp);
    }
}
