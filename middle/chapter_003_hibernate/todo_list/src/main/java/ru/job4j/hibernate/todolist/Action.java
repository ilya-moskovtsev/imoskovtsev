package ru.job4j.hibernate.todolist;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Action {
    void apply(Validate logicLayer, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException;
}
