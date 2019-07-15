package ru.job4j.servlets.crud;

import javax.servlet.http.HttpServletRequest;

public interface Operation {
    void apply(Validate logicLayer, HttpServletRequest req);
}
