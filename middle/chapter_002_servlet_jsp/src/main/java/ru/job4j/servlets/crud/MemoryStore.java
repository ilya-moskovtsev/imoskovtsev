package ru.job4j.servlets.crud;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Persistent layer
 */
public class MemoryStore implements Store {
    private static final MemoryStore INSTANCE = new MemoryStore();
    private final List<User> users = new CopyOnWriteArrayList<>();
    private int id = 0;

    public MemoryStore() {
        User user = new User();
        user.setName("root");
        user.setLogin("root");
        user.setEmail("root@root.root");
        user.setDateCreated(LocalDate.now());
        user.setPassword("root");
        user.setRole(Role.ADMIN);
        add(user);
    }

    public static MemoryStore getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(User user) {
        user.setId(this.id++);
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

    @Override
    public boolean isValid(String login, String password) {
        boolean isValid = false;
        for (User user : users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                isValid = true;
                break;
            }
        }
        return isValid;
    }

    @Override
    public User findByLogin(String login) {
        return users.stream().filter(user -> login.equals(user.getLogin())).findFirst().orElse(null);
    }
}
