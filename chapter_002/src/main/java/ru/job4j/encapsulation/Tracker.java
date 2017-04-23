package ru.job4j.encapsulation;

/**
 * 2. Реализовать класс Tracker
 * @author imoskovtsev
 */
class Tracker {
    /**
     * Класс трекер - это обертка над массивом заявок.
     */
    private Item[] items;
    /**
     * Счетчик заявок.
     */
    private int itemCounter;

    /**
     * Конструктор.
     * @param numberOfItems количество заявок
     */
    Tracker(int numberOfItems) {
        this.items = new Item[numberOfItems];
        itemCounter = 0;
    }

    /**
     * Возвращает items.
     * @return Item[]
     */
    Item[] getItems() {
        return items;
    }

    /**
     * Возвращает itemCounter.
     * @return int
     */
    int getItemCounter() {
        return itemCounter;
    }

    /**
     * добавление заявок.
     * @param item заявка
     */
    void add(Item item) {
        if (itemCounter < items.length) {
            items[itemCounter] = item;
            itemCounter++;
        }
    }

    /**
     * редактирование заявок.
     * @param item заявка
     */
    void update(Item item) {
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
     * @param item заявка
     */
    void delete(Item item) {
        for (Item i : items) {
            if (i.getId().equals(item.getId())) {
                i = null;
            }
        }
    }

    /**
     * получение списка всех не null заявок.
     * @return Item[]
     */
    Item[] findAll() {
        int notNullItemsCounter = 0;
        for (Item item : items) {
            if (item != null) {
                notNullItemsCounter++;
            }
        }
        Item[] resultArray = new Item[notNullItemsCounter];
        int resultArrayIndex = 0;
        for (Item item : items) {
            if (item != null) {
                resultArray[resultArrayIndex] = item;
                resultArrayIndex++;
            }
        }
        return resultArray;
    }

    /**
     * получение списка по имени.
     * @param key имя
     * @return Item
     */
    Item findByName(String key) {
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
     * @param id id
     * @return Item
     */
    Item findById(String id) {
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
