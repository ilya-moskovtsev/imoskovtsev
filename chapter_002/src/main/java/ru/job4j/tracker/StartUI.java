package ru.job4j.tracker;

/**
 * 1. Используя класс ConsoleInput в классе StartUI обеспечить полноценную работу всего приложения<br>
 * Обеспечить бесперебойную работу приложения Tracker.[#20170]<br>
 *
 * @author imoskovtsev
 */
public class StartUI {
    /**
     * допустимый диапазон ответов.
     */
    private static final int[] RANGES = new int[]{
            Menu.ADD,
            Menu.SHOW_ALL,
            Menu.EDIT,
            Menu.DELETE,
            Menu.FIND_BY_ID,
            Menu.FIND_BY_NAME,
            Menu.EXIT
    };
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
        StartUI.input = input;
        StartUI.tracker = tracker;
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
     * возвращаем значение флага выхода из программы.
     *
     * @return isDone значение флага выхода из программы
     */
    public static boolean getIsDone() {
        return isDone;
    }

    /**
     * точка входа.
     *
     * @param args параметры запуска
     */
    public static void main(String[] args) {
        Input input = new ValidateInput();
        Tracker tracker = new Tracker(10);
        new StartUI(input, tracker);
        init();
    }

    /**
     * Инициализация.
     */
    public static void init() {
        MenuTracker menuTracker = new MenuTracker(input, tracker);
        menuTracker.fillActions();
        isDone = false;
        while (!isDone) {
            menuTracker.show();
            menuTracker.select(input.ask("Select: ", RANGES));
        }
    }
}
