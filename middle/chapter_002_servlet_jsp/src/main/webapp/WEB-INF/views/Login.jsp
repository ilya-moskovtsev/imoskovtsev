<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="alert alert-danger d-none" role="alert">
        <c:out value="${error}"/>
    </div>
    <form class="needs-validation" novalidate action="${pageContext.servletContext.contextPath}/login" method="post">
        <input type="hidden" name="action" value="login">
        <div class="form-group">
            <label for="inputLogin">Login:</label>
            <input type="text" class="form-control" id="inputLogin" name="login" placeholder="Enter login" required>
            <div class="valid-feedback">
                Looks good!
            </div>
            <div class="invalid-feedback">
                Login is required.
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword">Password:</label>
            <input type="password" class="form-control" id="inputPassword" name="password" placeholder="Enter password"
                   required>
            <div class="valid-feedback">
                Looks good!
            </div>
            <div class="invalid-feedback">
                Password is required.
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Login</button>
    </form>
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
    <%@include file="/WEB-INF/js/form-validation.js" %>
</script>
<script type="text/javascript">
    <%@include file="/WEB-INF/js/alert.js" %>
</script>
</body>
</html>
