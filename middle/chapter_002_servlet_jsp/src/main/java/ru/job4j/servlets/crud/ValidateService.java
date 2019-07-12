package ru.job4j.servlets.crud;

import java.util.List;
import java.util.Optional;

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
        List<User> users = persistentLayer.findAll();
        Optional<User> any = users.stream().filter(u -> u.getLogin().equals(user.getLogin()) || u.getEmail().equals(user.getEmail())).findAny();
        if (any.isEmpty()) {
            persistentLayer.add(user);
        }
    }

    @Override
    public void update(User user) {
        List<User> users = persistentLayer.findAll();
        User existingUser = persistentLayer.findById(user.getId());
        Optional<User> any = users.stream().filter(u -> u.getLogin().equals(user.getLogin()) || u.getEmail().equals(user.getEmail())).findAny();
        if (any.isPresent() && any.get().getId() == user.getId()) {
            persistentLayer.update(user);
        } else if (any.isEmpty() && existingUser != null) {
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
