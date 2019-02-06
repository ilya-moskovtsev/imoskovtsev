package ru.job4j.tracker;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

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
    private static final List<Integer> RANGES = Arrays.asList(
            Menu.ADD,
            Menu.SHOW_ALL,
            Menu.EDIT,
            Menu.DELETE,
            Menu.FIND_BY_ID,
            Menu.FIND_BY_NAME,
            Menu.EXIT
    );

    /**
     * выбранный ввод.
     */
    private static Input input;
    /**
     * выбранный трекер.
     */
    private static Tracker tracker;
    private static Consumer<String> output;

    /**
     * Флаг выхода из программы.
     */
    private static boolean isDone = false;

    /**
     * Конструктор.
     *
     * @param input   задаем ввод
     * @param tracker задаем трекер
     * @param output  задаем вывод
     */
    public StartUI(Input input, Tracker tracker, Consumer<String> output) {
        StartUI.input = input;
        StartUI.tracker = tracker;
        StartUI.output = output;
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
        Tracker tracker = new Tracker();
        new StartUI(input, tracker, System.out::println);
        init();
    }

    /**
     * Инициализация.
     */
    public static void init() {
        MenuTracker menuTracker = new MenuTracker(input, tracker, output);
        menuTracker.fillActions();
        isDone = false;
        while (!isDone) {
            menuTracker.show();
            menuTracker.select(input.ask("Select: ", RANGES));
        }
    }
}
