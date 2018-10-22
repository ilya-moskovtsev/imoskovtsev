package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

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
        for (Item i : items) {
            if (i.getId().equals(item.getId())) {
                i.setKey(item.getKey());
                i.setName(item.getName());
                i.setDesc(item.getDesc());
                i.setCreateDate(item.getCreateDate());
                i.setUpdateDate(item.getUpdateDate());
                break;
            }
        }
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
     * @return Item
     */
    public Item findByName(String key) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getKey().equals(key)) {
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * получение заявки по id.
     *
     * @param id id
     * @return Item
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }
}
