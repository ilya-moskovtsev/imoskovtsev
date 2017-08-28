package ru.job4j.tracker;

/**
 * 1. Используя класс ConsoleInput в классе StartUI обеспечить полноценную работу всего приложения<br>
 * Обеспечить бесперебойную работу приложения Tracker.[#20170]<br>
 * @author imoskovtsev
 */
public class StartUI {
    /**
     * допустимый диапазон ответов.
     */
    private static int[] ranges = new int[] {0, 1, 2, 3, 4, 5, 6};
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
    static void setIsDone(boolean isDone) {
        StartUI.isDone = isDone;
    }

    /**
     * возвращаем значение флага выхода из программы.
     *
     * @return isDone значение флага выхода из программы
     */
    static boolean getIsDone() {
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
    static void init() {
        MenuTracker menuTracker = new MenuTracker(input, tracker);
        menuTracker.fillActions();
        isDone = false;
        while (!isDone) {
            menuTracker.show();
            menuTracker.select(Integer.valueOf(input.ask("Select: ", ranges)));
        }
    }
}
