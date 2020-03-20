<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Upload File</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <form class="needs-validation" novalidate action="${pageContext.servletContext.contextPath}/" method="post"
          enctype="multipart/form-data">
        <input type="hidden" name="action" value="upload">
        <div class="form-group">
            <label for="inputFile">file:</label>
            <input type="file" class="form-control-file" id="inputFile" name="file" accept="image/*" required>
        </div>
        <button type="submit" class="btn btn-primary">Upload</button>
    </form>
    <table class="table table-sm">
        <thead class="thead-light">
        <tr>
            <th class="text-left">file</th>
            <th class="text-left">download</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${files}" var="file">
            <tr>
                <td><c:out value="${file.fileName}"></c:out></td>
                <td>
                    <form action="${pageContext.servletContext.contextPath}/download" method="get">
                        <input type="hidden" name="action" value="download">
                        <input type="hidden" name="file" value="<c:out value="${file.fileName}"></c:out>">
                        <button type="submit" class="btn btn-info">download</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
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
    <%@include file="/WEB-INF/js/form-validation.js" %>
</script>
</body>
</html>
