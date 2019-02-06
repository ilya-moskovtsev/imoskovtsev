package ru.job4j.chess;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Check Pawn.
 *
 * @author imoskovtsev
 */
public class PawnTest extends TestBase {

    /**
     * Конструктор.
     *
     * @throws ImpossibleMoveException Координата за пределами шахматной доски. Фигура не может туда пойти.
     */
    public PawnTest() throws ImpossibleMoveException {
    }

    /**
     * Check copy constructor.
     */
    @Test
    public void copyConstructor() {
        Pawn pawn1 = new Pawn(a2);
        Pawn pawn2 = new Pawn(pawn1);
        assertThat(pawn2.getCurrentPosition(), is(a2));
    }
}
