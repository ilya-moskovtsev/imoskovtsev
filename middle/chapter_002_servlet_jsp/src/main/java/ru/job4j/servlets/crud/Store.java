package ru.job4j.servlets.crud;

import java.sql.SQLException;
import java.util.List;

public interface Store {
    void add(User user) throws SQLException;

    void update(User user) throws SQLException;

    void delete(User user);

    List<User> findAll();

    User findById(int id);
}
