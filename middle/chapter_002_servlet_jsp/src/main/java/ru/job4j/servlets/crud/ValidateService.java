package ru.job4j.servlets.crud;

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
    public void add(User user) {
        int id = user.getId();
        User existingUser = persistentLayer.findById(id);
        if (existingUser == null) {
            persistentLayer.add(user);
        }
    }

    @Override
    public void update(User user) {
        int id = user.getId();
        User existingUser = persistentLayer.findById(id);
        if (existingUser != null) {
            persistentLayer.update(user);
        }
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
}
