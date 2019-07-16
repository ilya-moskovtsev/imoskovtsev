package ru.job4j.servlets.crud;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.time.LocalDate;

/**
 * Persistent layer
 */
@ThreadSafe
public class MemoryStore implements Store {
    private static final MemoryStore INSTANCE = new MemoryStore();
    private final List<User> users = new CopyOnWriteArrayList<>();
    private final List<JsonPerson> people = new CopyOnWriteArrayList<>();
    @GuardedBy("this")
    private int id;

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
        synchronized (this) {
            user.setId(this.id++);
        }
        users.add(user);
    }

    @Override
    public void add(JsonPerson person) {
        people.add(person);
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
    public List<Path> getFiles() {
        List<Path> files = null;
        Path uploads = Paths.get(System.getProperty("java.io.tmpdir"), "uploads");
        try (Stream<Path> list = Files.list(uploads)) {
            files = list.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files;
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

    @Override
    public List<JsonPerson> getPeople() {
        return people;
    }
}
