package ru.job4j.servlets.crud;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
}
