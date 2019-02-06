package ru.job4j.chess;

/**
 * Путь занят фигурами.
 */
public class OccupiedWayException extends Exception {
    /**
     * Конструктор.
     *
     * @param message сообщение
     */
    public OccupiedWayException(String message) {
        super(message);
    }
}
