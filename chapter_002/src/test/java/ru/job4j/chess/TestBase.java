package ru.job4j.chess;

/**
 * Базовый класс для тестов.
 *
 * @author imoskovtsev
 */
class TestBase {
    /**
     * Ожидаемое сообщение исключения.
     */
    static final String IMPOSSIBLE_MOVE = "Фигура не может туда пойти.";
    /**
     * буква A.
     */
    final int letterA = 0;
    /**
     * буква B.
     */
    final int letterB = 1;
    /**
     * буква C.
     */
    final int letterC = 2;
    /**
     * буква D.
     */
    final int letterD = 3;
    /**
     * буква E.
     */
    final int letterE = 4;
    /**
     * буква F.
     */
    final int letterF = 5;
    /**
     * буква G.
     */
    final int letterG = 6;
    /**
     * буква H.
     */
    final int letterH = 7;

    /**
     * цифра 1.
     */
    final int number1 = 0;
    /**
     * цифра 2.
     */
    final int number2 = 1;
    /**
     * цифра 3.
     */
    final int number3 = 2;
    /**
     * цифра 4.
     */
    final int number4 = 3;
    /**
     * цифра 4.
     */
    final int number5 = 4;
    /**
     * цифра 7.
     */
    final int number7 = 6;
    /**
     * цифра 8.
     */
    final int number8 = 7;

    /**
     * ячейка A1.
     */
    final ChessboardCell a1 = new ChessboardCell(letterA, number1);
    /**
     * ячейка A2.
     */
    final ChessboardCell a2 = new ChessboardCell(letterA, number2);
    /**
     * ячейка A3.
     */
    final ChessboardCell a3 = new ChessboardCell(letterA, number3);
    /**
     * ячейка A8.
     */
    final ChessboardCell a8 = new ChessboardCell(letterA, number8);
    /**
     * ячейка B2.
     */
    final ChessboardCell b2 = new ChessboardCell(letterB, number2);
    /**
     * ячейка B4.
     */
    final ChessboardCell b4 = new ChessboardCell(letterB, number4);
    /**
     * ячейка B7.
     */
    final ChessboardCell b7 = new ChessboardCell(letterB, number7);
    /**
     * ячейка C1.
     */
    final ChessboardCell c1 = new ChessboardCell(letterC, number1);
    /**
     * ячейка C2.
     */
    final ChessboardCell c2 = new ChessboardCell(letterC, number2);
    /**
     * ячейка C4.
     */
    final ChessboardCell c4 = new ChessboardCell(letterC, number4);
    /**
     * ячейка C5.
     */
    final ChessboardCell c5 = new ChessboardCell(letterC, number5);
    /**
     * ячейка C8.
     */
    final ChessboardCell c8 = new ChessboardCell(letterC, number8);
    /**
     * ячейка D1.
     */
    final ChessboardCell d1 = new ChessboardCell(letterD, number1);
    /**
     * ячейка D2.
     */
    final ChessboardCell d2 = new ChessboardCell(letterD, number2);
    /**
     * ячейка D4.
     */
    final ChessboardCell d4 = new ChessboardCell(letterD, number4);
    /**
     * ячейка D5.
     */
    final ChessboardCell d5 = new ChessboardCell(letterD, number5);
    /**
     * ячейка D7.
     */
    final ChessboardCell d7 = new ChessboardCell(letterD, number7);
    /**
     * ячейка G1.
     */
    final ChessboardCell g1 = new ChessboardCell(letterG, number1);
    /**
     * ячейка E3.
     */
    final ChessboardCell e3 = new ChessboardCell(letterE, number3);
    /**
     * ячейка E3.
     */
    final ChessboardCell f2 = new ChessboardCell(letterF, number2);
    /**
     * ячейка H1.
     */
    final ChessboardCell h1 = new ChessboardCell(letterH, number1);

    /**
     * Фигуры.
     */
    final Chesspiece[] chesspieces = new Chesspiece[1];
    /**
     * Доска.
     */
    Chessboard chessboard;

    /**
     * Ячейки.
     */
    ChessboardCell[][] cells = new ChessboardCell[8][8];

    /**
     * Конструктор.
     *
     * @throws ImpossibleMoveException Координата за пределами шахматной доски. Фигура не может туда пойти.
     */
    TestBase() throws ImpossibleMoveException {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            cells[i][j] = new ChessboardCell(i, j);
            }
        }
    }
}
