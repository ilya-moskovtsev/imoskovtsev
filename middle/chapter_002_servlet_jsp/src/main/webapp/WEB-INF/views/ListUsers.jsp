<%@ page import="ru.job4j.servlets.crud.Role" %>
<%@ page import="ru.job4j.servlets.crud.User" %>
<%@ page import="ru.job4j.servlets.crud.Validate" %>
<%@ page import="ru.job4j.servlets.crud.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div>
        Logged in as <b><c:out value="${login}"/></b> with role <b><c:out value="${role}"/></b>
    </div>
    <div>
        <form action="${pageContext.servletContext.contextPath}/" method="post">
            <input type="hidden" name="action" value="logout">
            <button type="submit" class="btn btn-secondary">logout</button>
        </form>
    </div>
    <c:if test="${Role.ADMIN.equals(role)}">
        <form action="${pageContext.servletContext.contextPath}/create" method="get">
            <button type="submit" class="btn btn-primary">create new user</button>
        </form>
    </c:if>
    <table class="table table-sm">
        <thead class="thead-light">
        <tr>
            <th scope="col" class="text-left">id</th>
            <th scope="col" class="text-left">name</th>
            <th scope="col" class="text-left">login</th>
            <th scope="col" class="text-left">email</th>
            <th scope="col" class="text-left">date created</th>
            <th scope="col" class="text-left">role</th>
            <th scope="col" class="text-left">country</th>
            <th scope="col" class="text-left">state</th>
            <th scope="col" class="text-left">city</th>
            <th scope="col" class="text-left">edit</th>
            <th scope="col" class="text-left">delete</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${Role.ADMIN.equals(role)}">
                <c:forEach items="${users}" var="user">
                    <tr>
                        <th scope="row"><c:out value="${user.id}"/></th>
                        <td><c:out value="${user.name}"/></td>
                        <td><c:out value="${user.login}"/></td>
                        <td><c:out value="${user.email}"/></td>
                        <td><c:out value="${user.dateCreated}"/></td>
                        <td><c:out value="${user.role}"/></td>
                        <td><c:out value="${user.country}"/></td>
                        <td><c:out value="${user.state}"/></td>
                        <td><c:out value="${user.city}"/></td>

                        <td>
                            <form action="${pageContext.servletContext.contextPath}/edit" method="get">
                                <input type="hidden" name="id" value="<c:out value="${user.id}"/>">
                                <button type="submit" class="btn btn-warning">edit</button>
                            </form>
                        </td>

                        <td>
                            <form class="delete" action="${pageContext.servletContext.contextPath}/user" method="post"
                                  data-user-id="<c:out value="${user.id}"/>">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="id" value="<c:out value="${user.id}"/>">
                                <button type="submit" class="btn btn-danger">delete</button>
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
                    <td><c:out value="${loggedInUser.country}"/></td>
                    <td><c:out value="${loggedInUser.state}"/></td>
                    <td><c:out value="${loggedInUser.city}"/></td>

                    <td>
                        <form action="${pageContext.servletContext.contextPath}/edit" method="get">
                            <input type="hidden" name="id" value="<c:out value="${loggedInUser.id}"/>">
                            <button type="submit" class="btn btn-warning">edit</button>
                        </form>
                    </td>

                    <td></td>
                </tr>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>
</div>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script type="text/javascript">
    <%@include file="/WEB-INF/js/delete.js" %>
</script>
</body>
</html>
