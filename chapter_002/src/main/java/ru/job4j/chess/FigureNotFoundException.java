package ru.job4j.chess;

/**
 * В начальной ячейке нет фигуры.
 */
class FigureNotFoundException extends Exception {
    /**
     * Конструктор.
     * @param message сообщение
     */
    FigureNotFoundException(String message) {
        super(message);
    }
}
