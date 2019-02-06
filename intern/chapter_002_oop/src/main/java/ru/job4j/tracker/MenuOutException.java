package ru.job4j.tracker;

/**
 * Обеспечить бесперебойную работу приложения Tracker.[#20170]<br>
 * 2. Создать класс MenuOutException, наследующий RuntimeException,<br>
 * который должен принимать сообщение об ошибке в конструктор<br>
 * и передавать его в конструктор родителя.<br>
 *
 * @author imoskovtsev
 */
public class MenuOutException extends RuntimeException {
    /**
     * Конструктор, принимающий сообщение об ошибкеи его причину
     * и передающий их в конструктор родителя.
     *
     * @param message сообщение об ошибке
     */
    public MenuOutException(String message) {
        super(message);
    }
}
