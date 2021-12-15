<%@ page import="ru.job4j.servlets.crud.Role" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="roles" value="${Role.values()}"/>

<html>
<head>
    <title>Edit User</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div>
        Logged in as <b><c:out value="${login}"/></b> with role <b><c:out value="${role}"/></b>
    </div>
    <form class="needs-validation" novalidate action="${pageContext.servletContext.contextPath}/user" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<c:out value="${user.id}"/>">
        <div class="form-group">
            <label for="nameInput">name:</label>
            <input type="text" class="form-control" id="nameInput" name="name" value="<c:out value="${user.name}"/>"
                   placeholder="Enter name"
                   required>
            <div class="valid-feedback">
                Looks good!
            </div>
            <div class="invalid-feedback">
                Name is required.
            </div>
        </div>
        <div class="form-group">
            <label for="loginInput">login:</label>
            <input type="text" class="form-control" id="loginInput" name="login" value="<c:out value="${user.login}"/>"
                   placeholder="Enter login"
                   required>
            <div class="valid-feedback">
                Looks good!
            </div>
            <div class="invalid-feedback">
                Login is required.
            </div>
        </div>
        <div class="form-group">
            <label for="emailInput">email:</label>
            <input type="email" class="form-control" id="emailInput" name="email" value="<c:out value="${user.email}"/>"
                   placeholder="Enter email"
                   required>
            <div class="valid-feedback">
                Looks good!
            </div>
            <div class="invalid-feedback">
                Email is required.
            </div>
        </div>
        <div class="form-group">
            <label for="passwordInput">password:</label>
            <input type="password" class="form-control" id="passwordInput" name="password"
                   value="<c:out value="${user.password}"/>"
                   placeholder="Enter password"
                   required>
            <div class="valid-feedback">
                Looks good!
            </div>
            <div class="invalid-feedback">
                Password is required.
            </div>
        </div>
        <c:choose>
            <c:when test="${Role.ADMIN.equals(role)}">
                <div class="form-group">
                    <label>role:</label>
                    <select class="custom-select" id="roleSelect" name="role" required>
                        <option value="">Select role</option>
                        <c:forEach var="role" items="${roles}">
                            <c:choose>
                                <c:when test="${user.role == role}">
                                    <option value="<c:out value="${role}"/>" selected><c:out value="${role}"/></option>
                                </c:when>
                                <c:otherwise>
                                    <option value="<c:out value="${role}"/>"><c:out value="${role}"/></option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                    <div class="valid-feedback">
                        Looks good!
                    </div>
                    <div class="invalid-feedback">
                        Role is required.
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <input type="hidden" name="role" value="${user.role}">
            </c:otherwise>
        </c:choose>
        <div class="form-group">
            <label for="countryId">country:</label>
            <select name="country" class="custom-select countries order-alpha" id="countryId" required>
                <option value="">Select Country</option>
            </select>
            <div class="valid-feedback">
                Looks good!
            </div>
            <div class="invalid-feedback">
                Country is required.
            </div>
        </div>
        <div class="form-group">
            <label for="stateId">state:</label>
            <select name="state" class="custom-select states order-alpha" id="stateId" required>
                <option value="">Select State</option>
            </select>
            <div class="valid-feedback">
                Looks good!
            </div>
            <div class="invalid-feedback">
                State is required.
            </div>
        </div>
        <div class="form-group">
            <label for="cityId">city:</label>
            <select name="city" class="custom-select cities order-alpha" id="cityId" required>
                <option value="">Select City</option>
            </select>
            <div class="valid-feedback">
                Looks good!
            </div>
            <div class="invalid-feedback">
                City is required.
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Edit</button>
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
<script src="//geodata.solutions/includes/countrystatecity.js"></script>
<script type="text/javascript">
    <%@include file="/WEB-INF/js/form-validation.js"%>
</script>
<script>
    <%@include file="/WEB-INF/js/geodata.js"%>
</script>
</body>
</html>
