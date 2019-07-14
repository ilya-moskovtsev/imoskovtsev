package ru.job4j.servlets.crud;

import java.nio.file.Path;
import java.sql.SQLException;
import java.util.List;

/**
 * Logic layer.
 */
public class ValidateService implements Validate {
    private static final ValidateService INSTANCE = new ValidateService();

    private final Store persistentLayer = DbStore.getInstance();

    public static Validate getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean add(User user) {
        boolean result;
        try {
            persistentLayer.add(user);
            result = true;
        } catch (SQLException e) {
            result = false;
        }
        return result;
    }

    @Override
    public void add(JsonPerson person) {
        persistentLayer.add(person);
    }

    @Override
    public boolean update(User user) {
        boolean result;
        try {
            persistentLayer.update(user);
            result = true;
        } catch (SQLException e) {
            result = false;
        }
        return result;
    }

    @Override
    public void delete(User user) {
        int id = user.getId();
        User existingUser = persistentLayer.findById(id);
        if (existingUser != null) {
            persistentLayer.delete(user);
        }
    }

    @Override
    public List<User> findAll() {
        return persistentLayer.findAll();
    }

    @Override
    public User findById(int id) {
        return persistentLayer.findById(id);
    }

    @Override
    public List<Path> getFiles() {
        return persistentLayer.getFiles();
    }

    @Override
    public boolean isValid(String login, String password) {
        return persistentLayer.isValid(login, password);
    }

    @Override
    public User findByLogin(String login) {
        return persistentLayer.findByLogin(login);
    }

    @Override
    public List<JsonPerson> getPeople() {
        return persistentLayer.getPeople();
    }
}
