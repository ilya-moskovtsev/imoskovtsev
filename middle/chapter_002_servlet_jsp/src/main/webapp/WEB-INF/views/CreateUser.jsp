<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.job4j.servlets.crud.Role" %>
<c:set var="roles" value="${Role.values()}"/>
<html>
<head>
    <title>Create User</title>
</head>
<body>
<div>
    Logged in as <c:out value="${login}"/> with role <c:out value="${role}"/>
</div>
<form action="${pageContext.servletContext.contextPath}/user" method="post">
    <input type="hidden" name="action" value="add">
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
    <label>
        password:
        <input type="password" name="password">
    </label><br>
    <c:if test="${Role.ADMIN.equals(role)}">
        <select name="role">
            <c:forEach var="role" items="${roles}">
                <option value="<c:out value="${role}"/>"><c:out value="${role}"/></option>
            </c:forEach>
        </select><br>
    </c:if>
    <input type="submit" value="Create">
</form>
</body>
</html>
