package ru.job4j.chess;

/**
 * Базовый класс для тестов.
 *
 * @author imoskovtsev
 */
public class TestBase {
    /**
     * Ожидаемое сообщение исключения.
     */
    public static final String IMPOSSIBLE_MOVE = "Фигура не может туда пойти.";
    /**
     * буква A.
     */
    public final int letterA = 0;
    /**
     * буква B.
     */
    public final int letterB = 1;
    /**
     * буква C.
     */
    public final int letterC = 2;
    /**
     * буква D.
     */
    public final int letterD = 3;
    /**
     * буква E.
     */
    public final int letterE = 4;
    /**
     * буква H.
     */
    public final int letterH = 7;

    /**
     * цифра 1.
     */
    public final int number1 = 0;
    /**
     * цифра 2.
     */
    public final int number2 = 1;
    /**
     * цифра 3.
     */
    public final int number3 = 2;
    /**
     * цифра 4.
     */
    public final int number4 = 3;
    /**
     * цифра 4.
     */
    public final int number5 = 4;
    /**
     * цифра 7.
     */
    public final int number7 = 6;
    /**
     * цифра 8.
     */
    public final int number8 = 7;

    /**
     * ячейка A1.
     */
    public final ChessboardCell a1 = new ChessboardCell(letterA, number1);
    /**
     * ячейка A2.
     */
    public final ChessboardCell a2 = new ChessboardCell(letterA, number2);
    /**
     * ячейка A3.
     */
    public final ChessboardCell a3 = new ChessboardCell(letterA, number3);
    /**
     * ячейка A8.
     */
    public final ChessboardCell a8 = new ChessboardCell(letterA, number8);
    /**
     * ячейка B2.
     */
    public final ChessboardCell b2 = new ChessboardCell(letterB, number2);
    /**
     * ячейка B4.
     */
    public final ChessboardCell b4 = new ChessboardCell(letterB, number4);
    /**
     * ячейка B7.
     */
    public final ChessboardCell b7 = new ChessboardCell(letterB, number7);
    /**
     * ячейка C1.
     */
    public final ChessboardCell c1 = new ChessboardCell(letterC, number1);
    /**
     * ячейка C2.
     */
    public final ChessboardCell c2 = new ChessboardCell(letterC, number2);
    /**
     * ячейка C5.
     */
    public final ChessboardCell c5 = new ChessboardCell(letterC, number5);
    /**
     * ячейка C8.
     */
    public final ChessboardCell c8 = new ChessboardCell(letterC, number8);
    /**
     * ячейка D2.
     */
    public final ChessboardCell d2 = new ChessboardCell(letterD, number2);
    /**
     * ячейка D7.
     */
    public final ChessboardCell d7 = new ChessboardCell(letterD, number7);
    /**
     * ячейка E3.
     */
    public final ChessboardCell e3 = new ChessboardCell(letterE, number3);
    /**
     * ячейка H1.
     */
    public final ChessboardCell h1 = new ChessboardCell(letterH, number1);

    /**
     * Фигуры.
     */
    public final ChessPiece[] chessPieces = new ChessPiece[1];
    /**
     * Доска.
     */
    public Chessboard chessboard;

    /**
     * Ячейки.
     */
    public final ChessboardCell[][] cells = new ChessboardCell[8][8];

    /**
     * Конструктор.
     *
     * @throws ImpossibleMoveException Координата за пределами шахматной доски. Фигура не может туда пойти.
     */
    public TestBase() throws ImpossibleMoveException {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cells[i][j] = new ChessboardCell(i, j);
            }
        }
    }
}
