package ru.job4j.generic;

public interface Storage<T extends Base> {
    void add(T model);

    boolean replace(String id, T model);

    boolean delete(String id);

    T findById(String id);
}
