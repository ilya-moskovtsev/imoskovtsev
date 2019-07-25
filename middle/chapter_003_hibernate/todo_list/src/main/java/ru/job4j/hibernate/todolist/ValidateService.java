package ru.job4j.hibernate.todolist;

import java.util.List;

/**
 * Logic layer.
 */
public class ValidateService implements Validate {
    private static final ValidateService INSTANCE = new ValidateService();

    private final Store persistentLayer = HibernateStore.getInstance();

    public static Validate getInstance() {
        return INSTANCE;
    }

    @Override
    public void addItem(Item item) {
        persistentLayer.addItem(item);
    }

    @Override
    public List<Item> getItems() {
        return persistentLayer.getItems();
    }

    @Override
    public void done(Item item) {
        persistentLayer.done(item);
    }
}
