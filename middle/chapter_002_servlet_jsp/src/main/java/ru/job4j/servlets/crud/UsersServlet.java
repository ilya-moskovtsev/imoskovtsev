package ru.job4j.servlets.crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UsersServlet extends HttpServlet {
    private final Validate logicLayer = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var users = logicLayer.findAll();

        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>")
                .append("<html lang=\"en\">")
                .append("<head>")
                .append("<meta charset=\"UTF-8\">")
                .append("<title>List of Users</title>")
                .append("</head>")
                .append("<body>")
                .append("<table>")
                .append("<tr>")
                .append("<th>").append("id").append("</th>")
                .append("<th>").append("name").append("</th>")
                .append("<th>").append("login").append("</th>")
                .append("<th>").append("email").append("</th>")
                .append("<th>").append("date created").append("</th>")
                .append("<th>").append("edit").append("</th>")
                .append("<th>").append("delete").append("</th>")
                .append("</tr>");
        for (User user : users) {
            writer.append("<tr>")
                    .append("<td>").append(String.valueOf(user.getId())).append("</td>")
                    .append("<td>").append(user.getName()).append("</td>")
                    .append("<td>").append(user.getLogin()).append("</td>")
                    .append("<td>").append(user.getEmail()).append("</td>")
                    .append("<td>").append(user.getDateCreated().toString()).append("</td>")

                    .append("<td>")
                    .append("<form action='").append(req.getContextPath()).append("/edit' method='get'>")
                    .append("<input type='hidden' name='id' value='").append(String.valueOf(user.getId())).append("'>")
                    .append("<input type='submit' value='edit'>")
                    .append("</form>")
                    .append("</td>")

                    .append("<td>")
                    .append("<form action='").append(req.getContextPath()).append("/user' method='post'>")
                    .append("<input type='hidden' name='action' value='delete'>")
                    .append("<input type='hidden' name='id' value='").append(String.valueOf(user.getId())).append("'>")
                    .append("<input type='submit' value='delete'>")
                    .append("</form>")
                    .append("</td>")

                    .append("</tr>");
        }
        writer.append("</table>")
                .append("</body>")
                .append("</html>")
                .flush();
    }
}
