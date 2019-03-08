package ru.job4j.generic;

public class UserStorage extends AbstractStorage<User> implements Storage<User> {
    public UserStorage(int capacity) {
        super(capacity);
    }
}
