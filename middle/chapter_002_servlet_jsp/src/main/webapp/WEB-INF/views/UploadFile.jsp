<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Upload File</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/" method="post" enctype="multipart/form-data">
    <input type="hidden" name="action" value="upload">
    <label>
        name:
        <input type="text" name="name">
    </label><br>
    <label>
        file:
        <input type="file" name="file" accept="image/*">
    </label><br>
    <input type="submit" value="Upload">
</form>
<table>
    <tr>
        <th>file</th>
        <th>download</th>
    </tr>
    <c:forEach items="${files}" var="file">
        <tr>
            <td><c:out value="${file.fileName}"></c:out></td>
            <td>
                <form action="${pageContext.servletContext.contextPath}/download" method="get">
                    <input type="hidden" name="action" value="download">
                    <input type="hidden" name="file" value="<c:out value="${file.fileName}"></c:out>">
                    <input type="submit" value="download">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
