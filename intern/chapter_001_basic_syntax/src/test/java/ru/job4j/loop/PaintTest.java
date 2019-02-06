package ru.job4j.loop;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Тестирование построения пирамиды в псевдографике.
 *
 * @author imoskovtsev
 */
public class PaintTest {
    /**
     * Для высоты 2 пирамида будет иметь вид " ^ %n^^^%n".
     */
    @Test
    public void whenPyramidHeightTwoThenPyramid() {
        Paint paint = new Paint();
        String result = paint.pyramid(2);
        String expected = String.format(" ^ %s^^^", System.getProperty("line.separator"));
        assertThat(result, is(expected));
    }

    /**
     * Для высоты 3 пирамида будет иметь вид "  ^  %n ^^^ %n^^^^^%n".
     */
    @Test
    public void whenPyramidHeightThreeThenPyramid() {
        Paint paint = new Paint();
        String result = paint.pyramid(3);
        String expected = String.format("  ^  %1$s ^^^ %1$s^^^^^", System.getProperty("line.separator"));
        assertThat(result, is(expected));
    }
}