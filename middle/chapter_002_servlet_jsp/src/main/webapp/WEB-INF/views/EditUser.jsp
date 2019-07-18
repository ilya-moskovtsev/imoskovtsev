<%@ page import="ru.job4j.servlets.crud.Role" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="userId" value="${param.id}"/>
<c:set var="user" value="${users[userId]}"/>
<c:set var="roles" value="${Role.values()}"/>

<html>
<head>
    <title>Edit User</title>
</head>
<body>
<div>
    Logged in as <c:out value="${login}"/> with role <c:out value="${role}"/>
</div>
<form action="${pageContext.servletContext.contextPath}/user" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="id" value="<c:out value="${user.id}"/>">
    <label>
        name:
        <input type="text" name="name" value="<c:out value="${user.name}"/>">
    </label><br>
    <label>
        login:
        <input type="text" name="login" value="<c:out value="${user.login}"/>">
    </label><br>
    <label>
        email:
        <input type="email" name="email" value="<c:out value="${user.email}"/>">
    </label><br>
    <label>
        password:
        <input type="password" name="password" value="<c:out value="${user.password}"/>">
    </label><br>
    <c:choose>
        <c:when test="${Role.ADMIN.equals(role)}">
            <label>
                role:
                <select name="role">
                    <c:forEach var="role" items="${roles}">
                        <option value="<c:out value="${role}"/>"><c:out value="${role}"/></option>
                    </c:forEach>
                </select>
            </label><br>
        </c:when>
        <c:otherwise>
            <input type="hidden" name="role" value="${user.role}">
        </c:otherwise>
    </c:choose>
    <input type="submit" value="Edit">
</form>
</body>
</html>
