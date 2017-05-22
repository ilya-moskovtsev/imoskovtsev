package ru.job4j.tracker;

import static ru.job4j.tracker.Menu.ADD;
import static ru.job4j.tracker.Menu.SHOW_ALL;
import static ru.job4j.tracker.Menu.EDIT;
import static ru.job4j.tracker.Menu.DELETE;
import static ru.job4j.tracker.Menu.FIND_BY_ID;
import static ru.job4j.tracker.Menu.FIND_BY_NAME;
import static ru.job4j.tracker.Menu.EXIT;


/**
 * 1. Используя класс ConsoleInput в классе StartUI обеспечить полноценную работу всего приложения
 * @author imoskovtsev
 */
public class StartUI {
    /**
     * выбранный ввод.
     */
    private static Input input;
    /**
     * выбранный трекер.
     */
    private static Tracker tracker;

    /**
     * Конструктор.
     * @param input задаем ввод
     * @param tracker задаем трекер
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * точка входа.
     * @param args параметры запуска
     */
    public static void main(String[] args) {
        init();
    }

    /**
     * Инициализация.
     */
    static void init() {
        MenuTracker menuTracker = new MenuTracker(input, tracker);
        menuTracker.fillActions();

        boolean isDone = false;
        while (!isDone) {
            menuTracker.show();
            menuTracker.select(Integer.valueOf(input.ask("Select: ")));
        }
    }
}
