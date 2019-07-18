package ru.job4j.hibernate.todolist;

import java.util.List;

public interface Validate {
    void addItem(Item item);

    List<Item> getItems();

    void done(Item item);
}
