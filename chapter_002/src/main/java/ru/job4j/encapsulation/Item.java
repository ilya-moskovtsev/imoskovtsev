package ru.job4j.encapsulation;

/**
 * 2. Реализовать класс Tracker
 * @author imoskovtsev
 */
class Item {
    /**
     * id заявки.
     */
    private String id;

    /**
     * имя заявки.
     */
    private String key;

    /**
     * Конструктор.
     * @param id id заявки
     * @param key имя заявки
     */
    Item(String id, String key) {
        this.id = id;
        this.key = key;
    }

    /**
     * Возвращает id.
     * @return id
     */
    String getId() {
        return this.id;
    }

    /**
     * Возвращает key.
     * @return key
     */
    String getKey() {
        return this.key;
    }

    /**
     * Устанавливает key.
     * @param key имя заявки.
     */
    void setKey(String key) {
        this.key = key;
    }
}
