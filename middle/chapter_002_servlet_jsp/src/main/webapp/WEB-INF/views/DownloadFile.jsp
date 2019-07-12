<%@ page import="java.nio.file.Path" %>
<%@ page import="java.nio.file.Paths" %>
<%@ page import="java.nio.file.Files" %>
<%@ page import="java.io.OutputStream" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String filename = request.getParameter("file");
    Path path = Paths.get(System.getProperty("java.io.tmpdir"), "uploads", filename);

    response.setContentType("APPLICATION/OCTET-STREAM");
    response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
    try (OutputStream output = response.getOutputStream()) {
        output.write(Files.readAllBytes(path));
    }
%>