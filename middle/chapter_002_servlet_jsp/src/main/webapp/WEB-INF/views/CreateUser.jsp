<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Create User</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/user" method="post">
    <input type="hidden" name="action" value="add">
    <label>
        id:
        <input type="text" name="id">
    </label><br>
    <label>
        name:
        <input type="text" name="name">
    </label><br>
    <label>
        login:
        <input type="text" name="login">
    </label><br>
    <label>
        email:
        <input type="email" name="email">
    </label><br>
    <input type="submit" value="Create">
</form>
</body>
</html>
