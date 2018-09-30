package ru.job4j.chess;

/**
 * В начальной ячейке нет фигуры.
 */
public class FigureNotFoundException extends Exception {
    /**
     * Конструктор.
     *
     * @param message сообщение
     */
    public FigureNotFoundException(String message) {
        super(message);
    }
}
