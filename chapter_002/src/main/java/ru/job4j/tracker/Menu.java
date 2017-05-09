package ru.job4j.tracker;

/**
 * 1. Используя класс ConsoleInput в классе StartUI обеспечить полноценную работу всего приложения.
 * @author imoskovtsev
 */
class Menu {
    /**
     * Add new Item.
     */
    static final int ADD = 0;
    /**
     * Show all items.
     */
    static final int SHOW_ALL = 1;
    /**
     * Edit item.
     */
    static final int EDIT = 2;
    /**
     * Delete item.
     */
    static final int DELETE = 3;
    /**
     * Find item by Id.
     */
    static final int FIND_BY_ID = 4;
    /**
     * Find items by name.
     */
    static final int FIND_BY_NAME = 5;
    /**
     * Exit Program.
     */
    static final int EXIT = 6;
}
