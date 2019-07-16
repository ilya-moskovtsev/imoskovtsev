<%@ page import="ru.job4j.servlets.crud.Role" %>
<%@ page import="ru.job4j.servlets.crud.User" %>
<%@ page import="ru.job4j.servlets.crud.Validate" %>
<%@ page import="ru.job4j.servlets.crud.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- распечатать залогинен USER или ADMIN --%>
<%
    Validate logicLayer = ValidateService.getInstance();
    String login = (String) request.getSession().getAttribute("login");
    User loggedInUser = logicLayer.findByLogin(login);
    request.getSession().setAttribute("loggedInUser", loggedInUser);
    Role role = loggedInUser.getRole();
    request.getSession().setAttribute("role", role);
%>
<html>
<head>
    <title>List of Users</title>
</head>
<body>
<div>
    Logged in as <c:out value="${login}"/> with role <c:out value="${role}"/>
</div>
<div>
    <form action="${pageContext.servletContext.contextPath}/" method="post">
        <input type="hidden" name="action" value="logout">
        <input type="submit" value="logout">
    </form>
</div>
<c:if test="${Role.ADMIN.equals(role)}">
    <form action="${pageContext.servletContext.contextPath}/create" method="get">
        <label>
            create:
            <input type="submit" value="create">
        </label>
    </form>
</c:if>
<table>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>login</th>
        <th>email</th>
        <th>date created</th>
        <th>role</th>
        <th>edit</th>
        <th>delete</th>
    </tr>
    <c:choose>
        <c:when test="${Role.ADMIN.equals(role)}">
            <c:forEach items="${users}" var="user">
                <tr>
                    <td><c:out value="${user.id}"/></td>
                    <td><c:out value="${user.name}"/></td>
                    <td><c:out value="${user.login}"/></td>
                    <td><c:out value="${user.email}"/></td>
                    <td><c:out value="${user.dateCreated}"/></td>
                    <td><c:out value="${user.role}"/></td>

                    <td>
                        <form action="${pageContext.servletContext.contextPath}/edit" method="get">
                            <input type="hidden" name="id" value="<c:out value="${user.id}"/>">
                            <input type="submit" value="edit">
                        </form>
                    </td>

                    <td>
                        <form action="${pageContext.servletContext.contextPath}/user" method="post">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="<c:out value="${user.id}"/>">
                            <input type="submit" value="delete">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <tr>
                <td><c:out value="${loggedInUser.id}"/></td>
                <td><c:out value="${loggedInUser.name}"/></td>
                <td><c:out value="${loggedInUser.login}"/></td>
                <td><c:out value="${loggedInUser.email}"/></td>
                <td><c:out value="${loggedInUser.dateCreated}"/></td>
                <td><c:out value="${loggedInUser.role}"/></td>

                <td>
                    <form action="${pageContext.servletContext.contextPath}/edit" method="get">
                        <input type="hidden" name="id" value="<c:out value="${loggedInUser.id}"/>">
                        <input type="submit" value="edit">
                    </form>
                </td>

                <td></td>
            </tr>
        </c:otherwise>
    </c:choose>
</table>
</body>
</html>
