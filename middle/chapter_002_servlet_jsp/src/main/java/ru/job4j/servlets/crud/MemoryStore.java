package ru.job4j.servlets.crud;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Persistent layer
 */
public class MemoryStore implements Store {
    private static final MemoryStore INSTANCE = new MemoryStore();
    private final List<User> users = new CopyOnWriteArrayList<>();

    public static MemoryStore getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public void update(User user) {
        User oldUser = findById(user.getId());
        oldUser.setName(user.getName());
    }

    @Override
    public void delete(User user) {
        User toBeRemoved = findById(user.getId());
        users.remove(toBeRemoved);
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User findById(int id) {
        return users.stream().filter(user -> id == user.getId()).findFirst().orElse(null);
    }
}
