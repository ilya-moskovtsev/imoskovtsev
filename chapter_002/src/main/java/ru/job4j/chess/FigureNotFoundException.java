package ru.job4j.chess;

/**
 * В начальной ячейке нет фигуры.
 */
public class FigureNotFoundException extends Exception {
    public FigureNotFoundException() {
        super("В начальной ячейке нет фигуры.");
    }
}
