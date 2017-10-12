package ru.job4j.chess;

/**
 * Путь занят фигурами.
 */
class OccupiedWayException extends Exception {
    /**
     * Конструктор.
     * @param message сообщение
     */
    OccupiedWayException(String message) {
        super(message);
    }
}
