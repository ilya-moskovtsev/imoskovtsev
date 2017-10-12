package ru.job4j.chess;

/**
 * Фигура не может так двигаться.
 */
class ImpossibleMoveException extends Exception {
    /**
     * Конструктор.
     * @param message сообщение
     */
    ImpossibleMoveException(String message) {
        super(message);
    }
}
