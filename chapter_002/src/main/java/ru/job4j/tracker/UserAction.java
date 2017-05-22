package ru.job4j.tracker;

/**
 * Реализовать события на внутренних классах. [#15201]
 * Интерфейс UserAction, в котором определены методы, общие для всех событий.
 * @author imoskovtsev
 */
public interface UserAction {
    /**
     * Запршиваем номер действия у пользователя.
     * @return int номер действия
     */
    int key();

    /**
     * Выполняем действие.
     */
    void execute(Input input, Tracker tracker);

    /**
     * Описание действия.
     * @return String
     */
    String info();

}
