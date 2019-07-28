<%@ page language="java" pageEncoding="UTF-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>List of Users</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<div class="container">
    <h2>List of Users</h2>
    <form action="${pageContext.servletContext.contextPath}/users.do" method="post">
        name: <input type="text" name="name"><br/>
        <input type="submit"><br/>
    </form>
    <table border="1">
        <tr>
            <th>Name</th>
        </tr>
        <c:forEach var="user" items="${users}" varStatus="status">
            <tr valign="top">
                <td>
                        ${user.name}
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>