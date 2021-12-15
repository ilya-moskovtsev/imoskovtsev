<%@ page import="java.nio.file.Path" %>
<%@ page import="java.nio.file.Paths" %>
<%@ page import="java.nio.file.Files" %>
<%@ page import="java.io.OutputStream" %>
<%@ page import="java.util.Objects" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String filename = request.getParameter("file");
    if (Objects.nonNull(filename)) {
        Path path = Paths.get(System.getProperty("java.io.tmpdir"), "uploads", filename);
        if (Files.exists(path)) {
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
            try (OutputStream output = response.getOutputStream()) {
                output.write(Files.readAllBytes(path));
            }
        }
    }
%>