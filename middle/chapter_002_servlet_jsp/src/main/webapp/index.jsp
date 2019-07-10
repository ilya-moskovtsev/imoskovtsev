<%@ page import="ru.job4j.servlets.crud.User" %>
<%@ page import="ru.job4j.servlets.crud.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>List of Users</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/create" method="get">
    <label>
        create:
        <input type="submit" value="create">
    </label>
</form>
<table>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>login</th>
        <th>email</th>
        <th>date created</th>
        <th>edit</th>
        <th>delete</th>
    </tr>
    <% for (User user : ValidateService.getInstance().findAll()) {%>
    <tr>
        <td><%=String.valueOf(user.getId())%>
        </td>
        <td><%=user.getName()%>
        </td>
        <td><%=user.getLogin()%>
        </td>
        <td><%=user.getEmail()%>
        </td>
        <td><%=user.getDateCreated().toString()%>
        </td>

        <td>
            <form action="<%=request.getContextPath()%>/edit" method="get">
                <input type="hidden" name="id" value="<%=String.valueOf(user.getId())%>">
                <input type="submit" value="edit">
            </form>
        </td>

        <td>
            <form action="<%=request.getContextPath()%>/user" method="post">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="id" value="<%=String.valueOf(user.getId())%>">
                <input type="submit" value="delete">
            </form>
        </td>
    </tr>

    <%}%>
</table>
</body>
</html>
