package ru.job4j.chess;

/**
 * Фигура не может так двигаться.
 */
public class ImpossibleMoveException extends Exception {
    /**
     * Конструктор.
     *
     * @param message сообщение
     */
    public ImpossibleMoveException(String message) {
        super(message);
    }
}
