package ru.job4j.servlets.crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Operation {
    void apply(Validate logicLayer, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException;
}
