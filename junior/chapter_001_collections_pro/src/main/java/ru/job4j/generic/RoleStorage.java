package ru.job4j.generic;

public class RoleStorage extends AbstractStorage<Role> implements Storage<Role> {
    public RoleStorage(int capacity) {
        super(capacity);
    }
}
