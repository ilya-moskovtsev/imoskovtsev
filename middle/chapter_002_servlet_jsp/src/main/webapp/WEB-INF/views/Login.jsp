<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Login</title>
</head>
<body>
<c:if test="${error != ''}">
    <div style="background-color: red"><c:out value="${error}"/></div>
</c:if>
<form action="${pageContext.servletContext.contextPath}/login" method="post">
    <input type="hidden" name="action" value="login">
    <label>
        login:
        <input type="text" name="login" value="">
    </label><br>
    <label>
        password:
        <input type="password" name="password" value="">
    </label><br>
    <input type="submit" value="Login">
</form>
</body>
</html>
