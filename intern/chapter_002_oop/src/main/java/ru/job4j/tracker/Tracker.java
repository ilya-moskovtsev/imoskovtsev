package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 2. Реализовать класс Tracker
 *
 * @author imoskovtsev
 */
public class Tracker {
    /**
     * Класс трекер - это обертка над массивом заявок.
     */
    private List<Item> items;

    /**
     * Конструктор.
     */
    public Tracker() {
        items = new ArrayList<>();
    }

    /**
     * Возвращает items.
     *
     * @return items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Возвращает itemCounter.
     *
     * @return int
     */
    public int getItemCounter() {
        return items.size();
    }

    /**
     * добавление заявок.
     *
     * @param item заявка
     */
    public void add(Item item) {
        items.add(item);
    }

    /**
     * редактирование заявок.
     *
     * @param item заявка
     */
    public void update(Item item) {
        items.set(
                items.indexOf(item),
                item
        );
    }

    /**
     * удаление заявок.
     *
     * @param item заявка
     */
    public void delete(Item item) {
        items.remove(item);
    }

    /**
     * получение списка всех не null заявок.
     *
     * @return items
     */
    public List<Item> findAll() {
        return items;
    }

    /**
     * получение списка по имени.
     *
     * @param key имя
     * @return Items
     */
    public List<Item> findByName(String key) {
        return items.stream().filter(item -> item != null && item.getKey().equals(key)).collect(Collectors.toList());
    }

    /**
     * получение заявки по id.
     *
     * @param id id
     * @return Item
     */
    public Item findById(String id) {
        return items.stream().filter(item -> item != null && item.getId().equals(id)).findFirst().get();
    }
}
