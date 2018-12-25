package ru.job4j.tracker;

/**
 * Реализовать события на внутренних классах. [#15201]
 * Интерфейс UserAction, в котором определены методы, общие для всех событий.
 *
 * @author imoskovtsev
 */
public interface UserAction {
    /**
     * Выполняем действие.
     *
     * @param input   система ввода
     * @param tracker трекер задач
     */
    void execute(Input input, Tracker tracker);

    /**
     * Описание действия.
     *
     * @return String
     */
    String info();

}