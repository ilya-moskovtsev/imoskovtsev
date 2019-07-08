package ru.job4j.gc;

public abstract class Cache {
    public abstract String get(String file);

    public abstract int size();
}
