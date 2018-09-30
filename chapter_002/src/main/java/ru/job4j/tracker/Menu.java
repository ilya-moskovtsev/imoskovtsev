package ru.job4j.tracker;

/**
 * 1. Используя класс ConsoleInput в классе StartUI обеспечить полноценную работу всего приложения.
 *
 * @author imoskovtsev
 */
public class Menu {
    /**
     * Add new Item.
     */
    public static final int ADD = 0;
    /**
     * Show all items.
     */
    public static final int SHOW_ALL = 1;
    /**
     * Edit item.
     */
    public static final int EDIT = 2;
    /**
     * Delete item.
     */
    public static final int DELETE = 3;
    /**
     * Find item by Id.
     */
    public static final int FIND_BY_ID = 4;
    /**
     * Find items by name.
     */
    public static final int FIND_BY_NAME = 5;
    /**
     * Exit Program.
     */
    public static final int EXIT = 6;
}
