package ru.job4j.tracker;

/**
 * 1. Используя класс ConsoleInput в классе StartUI обеспечить полноценную работу всего приложения
 *
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
     * Флаг выхода из программы.
     */
    private static boolean isDone = false;

    /**
     * Конструктор.
     *
     * @param input   задаем ввод
     * @param tracker задаем трекер
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * устанавливаем значение флага выхода из программы.
     *
     * @param isDone значение флага выхода из программы
     */
    public static void setIsDone(boolean isDone) {
        StartUI.isDone = isDone;
    }

    /**
     * точка входа.
     *
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

        while (!isDone) {
            menuTracker.show();
            menuTracker.select(Integer.valueOf(input.ask("Select: ")));
        }
    }
}
