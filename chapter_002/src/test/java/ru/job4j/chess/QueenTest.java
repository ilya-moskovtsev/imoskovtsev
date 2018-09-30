package ru.job4j.chess;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Проверка Ферзя.
 *
 * @author imoskovtsev
 */
public class QueenTest extends TestBase {

    /**
     * Конструктор.
     *
     * @throws ImpossibleMoveException Координата за пределами шахматной доски. Фигура не может туда пойти.
     */
    public QueenTest() throws ImpossibleMoveException {
    }

    @ParameterizedTest(name = "{index}. Queen {0}->{1}")
    @MethodSource("queenPossibleMoveProvider")
    public void shouldMoveQueenFromTo(ChessboardCell from, ChessboardCell to) throws FigureNotFoundException, ImpossibleMoveException, OccupiedWayException {
        chessPieces[0] = new Queen(from);
        chessboard = new Chessboard(chessPieces);

        chessboard.move(from, to);
        int resultLetter = chessboard.getChessPieces()[0].getCurrentPosition().getLetter();
        int resultNumber = chessboard.getChessPieces()[0].getCurrentPosition().getNumber();

        assertThat(resultLetter, is(to.getLetter()));
        assertThat(resultNumber, is(to.getNumber()));
    }

    @ParameterizedTest(name = "{index}. Queen {0}->{1}")
    @MethodSource("queenImpossibleMoveProvider")
    public void shouldNotMoveQueenFromTo(ChessboardCell from, ChessboardCell to) {
        chessPieces[0] = new Queen(from);
        chessboard = new Chessboard(chessPieces);

        Throwable exception = assertThrows(ImpossibleMoveException.class, () -> chessboard.move(from, to));
        assertEquals("Фигура не может туда пойти.", exception.getMessage());
    }

    public static Stream<Arguments> queenPossibleMoveProvider() throws ImpossibleMoveException {
        TestBase testBase = new TestBase();
        return Stream.of(
                //https://ru.wikipedia.org/wiki/Ферзь
                Arguments.of(testBase.cells[3][3], testBase.cells[0][0]),
                Arguments.of(testBase.cells[3][3], testBase.cells[0][3]),
                Arguments.of(testBase.cells[3][3], testBase.cells[0][6]),
                Arguments.of(testBase.cells[3][3], testBase.cells[1][1]),
                Arguments.of(testBase.cells[3][3], testBase.cells[1][3]),
                Arguments.of(testBase.cells[3][3], testBase.cells[1][5]),
                Arguments.of(testBase.cells[3][3], testBase.cells[2][2]),
                Arguments.of(testBase.cells[3][3], testBase.cells[2][3]),
                Arguments.of(testBase.cells[3][3], testBase.cells[2][4]),
                Arguments.of(testBase.cells[3][3], testBase.cells[3][0]),
                Arguments.of(testBase.cells[3][3], testBase.cells[3][1]),
                Arguments.of(testBase.cells[3][3], testBase.cells[3][2]),
                Arguments.of(testBase.cells[3][3], testBase.cells[3][4]),
                Arguments.of(testBase.cells[3][3], testBase.cells[3][5]),
                Arguments.of(testBase.cells[3][3], testBase.cells[3][6]),
                Arguments.of(testBase.cells[3][3], testBase.cells[3][7]),
                Arguments.of(testBase.cells[3][3], testBase.cells[4][2]),
                Arguments.of(testBase.cells[3][3], testBase.cells[4][3]),
                Arguments.of(testBase.cells[3][3], testBase.cells[4][4]),
                Arguments.of(testBase.cells[3][3], testBase.cells[5][1]),
                Arguments.of(testBase.cells[3][3], testBase.cells[5][3]),
                Arguments.of(testBase.cells[3][3], testBase.cells[5][5]),
                Arguments.of(testBase.cells[3][3], testBase.cells[6][0]),
                Arguments.of(testBase.cells[3][3], testBase.cells[6][3]),
                Arguments.of(testBase.cells[3][3], testBase.cells[6][6]),
                Arguments.of(testBase.cells[3][3], testBase.cells[7][3]),
                Arguments.of(testBase.cells[3][3], testBase.cells[7][7])
        );
    }

    public static Stream<Arguments> queenImpossibleMoveProvider() throws ImpossibleMoveException {
        TestBase testBase = new TestBase();
        return Stream.of(
                //https://ru.wikipedia.org/wiki/Ферзь
                Arguments.of(testBase.cells[3][3], testBase.cells[0][1]),
                Arguments.of(testBase.cells[3][3], testBase.cells[0][2]),
                Arguments.of(testBase.cells[3][3], testBase.cells[0][4]),
                Arguments.of(testBase.cells[3][3], testBase.cells[0][5]),
                Arguments.of(testBase.cells[3][3], testBase.cells[0][7]),
                Arguments.of(testBase.cells[3][3], testBase.cells[1][0]),
                Arguments.of(testBase.cells[3][3], testBase.cells[1][2]),
                Arguments.of(testBase.cells[3][3], testBase.cells[1][4]),
                Arguments.of(testBase.cells[3][3], testBase.cells[1][6]),
                Arguments.of(testBase.cells[3][3], testBase.cells[1][7]),
                Arguments.of(testBase.cells[3][3], testBase.cells[2][0]),
                Arguments.of(testBase.cells[3][3], testBase.cells[2][1]),
                Arguments.of(testBase.cells[3][3], testBase.cells[2][5]),
                Arguments.of(testBase.cells[3][3], testBase.cells[2][6]),
                Arguments.of(testBase.cells[3][3], testBase.cells[2][7]),
                Arguments.of(testBase.cells[3][3], testBase.cells[4][0]),
                Arguments.of(testBase.cells[3][3], testBase.cells[4][1]),
                Arguments.of(testBase.cells[3][3], testBase.cells[4][5]),
                Arguments.of(testBase.cells[3][3], testBase.cells[4][6]),
                Arguments.of(testBase.cells[3][3], testBase.cells[4][7]),
                Arguments.of(testBase.cells[3][3], testBase.cells[5][0]),
                Arguments.of(testBase.cells[3][3], testBase.cells[5][2]),
                Arguments.of(testBase.cells[3][3], testBase.cells[5][4]),
                Arguments.of(testBase.cells[3][3], testBase.cells[5][6]),
                Arguments.of(testBase.cells[3][3], testBase.cells[5][7]),
                Arguments.of(testBase.cells[3][3], testBase.cells[6][1]),
                Arguments.of(testBase.cells[3][3], testBase.cells[6][2]),
                Arguments.of(testBase.cells[3][3], testBase.cells[6][4]),
                Arguments.of(testBase.cells[3][3], testBase.cells[6][5]),
                Arguments.of(testBase.cells[3][3], testBase.cells[6][7]),
                Arguments.of(testBase.cells[3][3], testBase.cells[7][0]),
                Arguments.of(testBase.cells[3][3], testBase.cells[7][1]),
                Arguments.of(testBase.cells[3][3], testBase.cells[7][2]),
                Arguments.of(testBase.cells[3][3], testBase.cells[7][4]),
                Arguments.of(testBase.cells[3][3], testBase.cells[7][5]),
                Arguments.of(testBase.cells[3][3], testBase.cells[7][6])
        );
    }
}
