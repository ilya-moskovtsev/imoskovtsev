package ru.job4j.servlets.crud;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
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
        persistentLayer.add(user);
    }

    @Override
    public void add(JsonPerson person) {
        persistentLayer.add(person);
    }

    @Override
    public void update(User user) {
        User existingUser = persistentLayer.findById(user.getId());
        if (Objects.nonNull(existingUser)) {
            List<User> users = persistentLayer.findAll();
            Optional<User> any = users.stream().filter(u -> u.getLogin().equals(user.getLogin()) || u.getEmail().equals(user.getEmail())).findAny();
            if (any.isPresent() && any.get().getId() == user.getId()) {
                persistentLayer.update(user);
            }
            if (any.isEmpty()) {
                persistentLayer.update(user);
            }
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
