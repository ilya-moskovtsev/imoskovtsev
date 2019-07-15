<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List of Users</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/create" method="get">
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
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.id}"></c:out></td>
            <td><c:out value="${user.name}"></c:out></td>
            <td><c:out value="${user.login}"></c:out></td>
            <td><c:out value="${user.email}"></c:out></td>
            <td><c:out value="${user.dateCreated}"></c:out></td>

            <td>
                <form action="${pageContext.servletContext.contextPath}/edit" method="get">
                    <input type="hidden" name="id" value="<c:out value="${user.id}"></c:out>">
                    <input type="submit" value="edit">
                </form>
            </td>

            <td>
                <form action="${pageContext.servletContext.contextPath}/user" method="post">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" value="<c:out value="${user.id}"></c:out>">
                    <input type="submit" value="delete">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
