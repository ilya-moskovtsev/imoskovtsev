package ru.job4j.servlets.crud;

import java.util.List;

public interface Validate {
    boolean add(User user);

    boolean update(User user);

    void delete(User user);

    List<User> findAll();

    User findById(int id);

}
