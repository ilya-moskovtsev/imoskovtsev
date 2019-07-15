<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="userId" value="${param.id}"></c:set>
<c:set var="user" value="${users[userId]}"></c:set>

<html>
<head>
    <title>Edit User</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/user" method="post">
    <input type="hidden" name="action" value="update">
    <label>
        id:
        <input type="text" name="id" value="<c:out value="${user.id}"></c:out>">
    </label><br>
    <label>
        name:
        <input type="text" name="name" value="<c:out value="${user.name}"></c:out>">
    </label><br>
    <label>
        login:
        <input type="text" name="login" value="<c:out value="${user.login}"></c:out>">
    </label><br>
    <label>
        email:
        <input type="email" name="email" value="<c:out value="${user.email}"></c:out>">
    </label><br>
    <input type="submit" value="Edit">
</form>
</body>
</html>
