package ru.job4j.hibernate.todolist;

import java.util.List;

public interface Store {
    void addItem(Item item);

    List<Item> getItems();

    void done(Item item);
}
