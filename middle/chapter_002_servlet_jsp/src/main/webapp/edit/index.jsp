<%@ page import="ru.job4j.servlets.crud.ValidateService" %>
<%@ page import="ru.job4j.servlets.crud.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String id = request.getParameter("id");
    User user = ValidateService.getInstance().findById(Integer.parseInt(id));
%>
<html>
<head>
    <title>Edit User</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/user" method="post">
    <input type="hidden" name="action" value="update">
    <label>
        id:
        <input type="text" name="id" value="<%=String.valueOf(user.getId())%>">
    </label><br>
    <label>
        name:
        <input type="text" name="name" value="<%=user.getName()%>">
    </label><br>
    <label>
        login:
        <input type="text" name="login" value="<%=user.getLogin()%>">
    </label><br>
    <label>
        email:
        <input type="email" name="email" value="<%=user.getEmail()%>">
    </label><br>
    <input type="submit" value="Edit">
</form>
</body>
</html>
