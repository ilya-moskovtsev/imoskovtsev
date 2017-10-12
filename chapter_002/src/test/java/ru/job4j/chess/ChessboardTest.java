package ru.job4j.chess;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Проверка.
 *
 * @author imoskovtsev
 */
public class ChessboardTest extends TestBase {
    /**
     * Правило для проверки исключений.
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Конструктор.
     *
     * @throws ImpossibleMoveException Координата за пределами шахматной доски. Фигура не может туда пойти.
     */
    public ChessboardTest() throws ImpossibleMoveException {
    }

    /**
     * Слон C1->D2.
     *
     * @throws FigureNotFoundException В начальной ячейке нет фигуры.
     * @throws ImpossibleMoveException Координата за пределами шахматной доски. Фигура не может туда пойти.
     * @throws OccupiedWayException    Путь занят фигурами.
     */
    @Test
    public void shouldMoveBishopC1toD2() throws FigureNotFoundException, ImpossibleMoveException, OccupiedWayException {
        chesspieces[0] = new Bishop(c1);
        chessboard = new Chessboard(chesspieces);

        chessboard.move(c1, d2);
        int resultLetter = chessboard.getChesspieces()[0].getCurrentPosition().getLetter();
        int resultNumber = chessboard.getChesspieces()[0].getCurrentPosition().getNumber();

        assertThat(resultLetter, is(letterD));
        assertThat(resultNumber, is(number2));
    }

    /**
     * Слон C1->E3.
     *
     * @throws FigureNotFoundException В начальной ячейке нет фигуры.
     * @throws ImpossibleMoveException Координата за пределами шахматной доски. Фигура не может туда пойти.
     * @throws OccupiedWayException    Путь занят фигурами.
     */
    @Test
    public void shouldMoveBishopC1toE3() throws FigureNotFoundException, ImpossibleMoveException, OccupiedWayException {
        chesspieces[0] = new Bishop(c1);
        chessboard = new Chessboard(chesspieces);

        chessboard.move(c1, e3);
        int resultLetter = chessboard.getChesspieces()[0].getCurrentPosition().getLetter();
        int resultNumber = chessboard.getChesspieces()[0].getCurrentPosition().getNumber();

        assertThat(resultLetter, is(letterE));
        assertThat(resultNumber, is(number3));
    }

    /**
     * Слон C8->B7.
     *
     * @throws FigureNotFoundException В начальной ячейке нет фигуры.
     * @throws ImpossibleMoveException Координата за пределами шахматной доски. Фигура не может туда пойти.
     * @throws OccupiedWayException    Путь занят фигурами.
     */
    @Test
    public void shouldMoveBishopC8toB7() throws FigureNotFoundException, ImpossibleMoveException, OccupiedWayException {
        chesspieces[0] = new Bishop(c8);
        chessboard = new Chessboard(chesspieces);

        chessboard.move(c8, b7);
        int resultLetter = chessboard.getChesspieces()[0].getCurrentPosition().getLetter();
        int resultNumber = chessboard.getChesspieces()[0].getCurrentPosition().getNumber();

        assertThat(resultLetter, is(letterB));
        assertThat(resultNumber, is(number7));
    }

    /**
     * Слон C8->B7.
     *
     * @throws FigureNotFoundException В начальной ячейке нет фигуры.
     * @throws ImpossibleMoveException Координата за пределами шахматной доски. Фигура не может туда пойти.
     * @throws OccupiedWayException    Путь занят фигурами.
     */
    @Test
    public void shouldMoveBishopC8toD7() throws FigureNotFoundException, ImpossibleMoveException, OccupiedWayException {
        chesspieces[0] = new Bishop(c8);
        chessboard = new Chessboard(chesspieces);

        chessboard.move(c8, d7);
        int resultLetter = chessboard.getChesspieces()[0].getCurrentPosition().getLetter();
        int resultNumber = chessboard.getChesspieces()[0].getCurrentPosition().getNumber();

        assertThat(resultLetter, is(letterD));
        assertThat(resultNumber, is(number7));
    }

    /**
     * Слон. В начальной ячейке нет фигуры.
     *
     * @throws FigureNotFoundException В начальной ячейке нет фигуры.
     * @throws ImpossibleMoveException Координата за пределами шахматной доски. Фигура не может туда пойти.
     * @throws OccupiedWayException    Путь занят фигурами.
     */
    @Test
    public void shouldMoveBishopFigureNotFound() throws FigureNotFoundException, ImpossibleMoveException, OccupiedWayException {
        chesspieces[0] = new Bishop(c1);
        chessboard = new Chessboard(chesspieces);

        thrown.expect(FigureNotFoundException.class);
        thrown.expectMessage("В начальной ячейке нет фигуры.");
        chessboard.move(c8, d2);
    }

    /**
     * Слон. Фигура не может туда пойти.
     *
     * @throws FigureNotFoundException В начальной ячейке нет фигуры.
     * @throws ImpossibleMoveException Координата за пределами шахматной доски. Фигура не может туда пойти.
     * @throws OccupiedWayException    Путь занят фигурами.
     */
    @Test
    public void shouldMoveBishopImpossibleMoveException() throws FigureNotFoundException, ImpossibleMoveException, OccupiedWayException {
        chesspieces[0] = new Bishop(c1);
        chessboard = new Chessboard(chesspieces);

        thrown.expect(ImpossibleMoveException.class);
        thrown.expectMessage(IMPOSSIBLE_MOVE);
        chessboard.move(c1, c2);
    }

    /**
     * Слон. Путь занят фигурами.
     *
     * @throws FigureNotFoundException В начальной ячейке нет фигуры.
     * @throws ImpossibleMoveException Координата за пределами шахматной доски. Фигура не может туда пойти.
     * @throws OccupiedWayException    Путь занят фигурами.
     */
    @Test
    public void shouldMoveBishopOccupiedWayException() throws FigureNotFoundException, ImpossibleMoveException, OccupiedWayException {
        Chesspiece[] chesspieces = new Chesspiece[2];
        chesspieces[0] = new Bishop(c1);
        chesspieces[1] = new Bishop(d2);
        chessboard = new Chessboard(chesspieces);

        thrown.expect(OccupiedWayException.class);
        thrown.expectMessage("Путь занят фигурами.");
        chessboard.move(c1, d2);
    }

    /**
     * Пешка А2->А3.
     *
     * @throws FigureNotFoundException В начальной ячейке нет фигуры.
     * @throws ImpossibleMoveException Координата за пределами шахматной доски. Фигура не может туда пойти.
     * @throws OccupiedWayException    Путь занят фигурами.
     */
    @Test
    public void shouldMovePawnA2toA3() throws FigureNotFoundException, ImpossibleMoveException, OccupiedWayException {
        chesspieces[0] = new Pawn(a2);
        chessboard = new Chessboard(chesspieces);

        chessboard.move(a2, a3);
        int resultLetter = chessboard.getChesspieces()[0].getCurrentPosition().getLetter();
        int resultNumber = chessboard.getChesspieces()[0].getCurrentPosition().getNumber();

        assertThat(resultLetter, is(letterA));
        assertThat(resultNumber, is(number3));
    }

    /**
     * Пешка B2->B4.
     *
     * @throws FigureNotFoundException В начальной ячейке нет фигуры.
     * @throws ImpossibleMoveException Координата за пределами шахматной доски. Фигура не может туда пойти.
     * @throws OccupiedWayException    Путь занят фигурами.
     */
    @Test
    public void shouldMovePawnB2toB4() throws FigureNotFoundException, ImpossibleMoveException, OccupiedWayException {
        chesspieces[0] = new Pawn(b2);
        chessboard = new Chessboard(chesspieces);

        chessboard.move(b2, b4);
        int resultLetter = chessboard.getChesspieces()[0].getCurrentPosition().getLetter();
        int resultNumber = chessboard.getChesspieces()[0].getCurrentPosition().getNumber();

        assertThat(resultLetter, is(letterB));
        assertThat(resultNumber, is(number4));
    }

    /**
     * Пешка C2->C5. Фигура не может туда пойти.
     *
     * @throws FigureNotFoundException В начальной ячейке нет фигуры.
     * @throws ImpossibleMoveException Координата за пределами шахматной доски. Фигура не может туда пойти.
     * @throws OccupiedWayException    Путь занят фигурами.
     */
    @Test
    public void shouldMovePawnImpossibleMoveException() throws FigureNotFoundException, ImpossibleMoveException, OccupiedWayException {
        chesspieces[0] = new Pawn(c2);
        chessboard = new Chessboard(chesspieces);

        thrown.expect(ImpossibleMoveException.class);
        thrown.expectMessage(IMPOSSIBLE_MOVE);
        chessboard.move(c2, c5);
    }

    /**
     * Ладья А1->А8.
     *
     * @throws FigureNotFoundException В начальной ячейке нет фигуры.
     * @throws ImpossibleMoveException Координата за пределами шахматной доски. Фигура не может туда пойти.
     * @throws OccupiedWayException    Путь занят фигурами.
     */
    @Test
    public void shouldMoveCastleA1toA8() throws FigureNotFoundException, ImpossibleMoveException, OccupiedWayException {
        chesspieces[0] = new Castle(a1);
        chessboard = new Chessboard(chesspieces);

        chessboard.move(a1, a8);
        int resultLetter = chessboard.getChesspieces()[0].getCurrentPosition().getLetter();
        int resultNumber = chessboard.getChesspieces()[0].getCurrentPosition().getNumber();

        assertThat(resultLetter, is(letterA));
        assertThat(resultNumber, is(number8));
    }

    /**
     * Ладья А1->H1.
     *
     * @throws FigureNotFoundException В начальной ячейке нет фигуры.
     * @throws ImpossibleMoveException Координата за пределами шахматной доски. Фигура не может туда пойти.
     * @throws OccupiedWayException    Путь занят фигурами.
     */
    @Test
    public void shouldMoveCastleA1toH1() throws FigureNotFoundException, ImpossibleMoveException, OccupiedWayException {
        chesspieces[0] = new Castle(a1);
        chessboard = new Chessboard(chesspieces);

        chessboard.move(a1, h1);
        int resultLetter = chessboard.getChesspieces()[0].getCurrentPosition().getLetter();
        int resultNumber = chessboard.getChesspieces()[0].getCurrentPosition().getNumber();

        assertThat(resultLetter, is(letterH));
        assertThat(resultNumber, is(number1));
    }

    /**
     * Ладья A1->B2. Фигура не может туда пойти.
     *
     * @throws FigureNotFoundException В начальной ячейке нет фигуры.
     * @throws ImpossibleMoveException Координата за пределами шахматной доски. Фигура не может туда пойти.
     * @throws OccupiedWayException    Путь занят фигурами.
     */
    @Test
    public void shouldMoveCastleImpossibleMoveException() throws FigureNotFoundException, ImpossibleMoveException, OccupiedWayException {
        chesspieces[0] = new Castle(a1);
        chessboard = new Chessboard(chesspieces);

        thrown.expect(ImpossibleMoveException.class);
        thrown.expectMessage(IMPOSSIBLE_MOVE);
        chessboard.move(a1, b2);
    }
}