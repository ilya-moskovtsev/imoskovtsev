package ru.job4j.servlets.crud;

import java.nio.file.Path;
import java.util.List;

public interface Store {
    void add(User user);

    void add(JsonPerson person);

    void update(User user);

    void delete(User user);

    List<User> findAll();

    User findById(int id);

    List<Path> getFiles();

    boolean isValid(String login, String password);

    User findByLogin(String login);

    List<JsonPerson> getPeople();
}
