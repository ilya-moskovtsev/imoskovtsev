package ru.job4j.tracker;

import java.util.List;

public interface ITracker {
    List<Item> getItems();

    int getItemCounter();

    void add(Item item);

    void update(Item item);

    void delete(Item item);

    List<Item> findAll();

    List<Item> findByName(String key);

    Item findById(String id);
}
