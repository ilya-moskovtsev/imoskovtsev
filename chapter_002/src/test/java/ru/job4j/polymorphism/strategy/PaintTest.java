package ru.job4j.polymorphism.strategy;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * 4. Шаблон проектирования - стратегия
 * @author imoskovtsev
 */
public class PaintTest {
    /**
     * Поток вывода.
     */
    private ByteArrayOutputStream out;
    /**
     * setUp.
     */
    @Before
    public void setUp() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }
    /**
     * Нарисовать фигуру квадрат.
     */
    @Test
    public void drawSquare() {
        new Paint().draw(new Square());
        String expected = new StringBuilder()
                .append("* * * * *").append(System.lineSeparator())
                .append("* * * * *").append(System.lineSeparator())
                .append("* * * * *").append(System.lineSeparator())
                .append("* * * * *").append(System.lineSeparator())
                .append("* * * * *").append(System.lineSeparator())
                .toString();
        assertThat(out.toString(), is(expected));
    }

    /**
     * Нарисовать фигуру треугольник.
     */
    @Test
    public void drawTriangle() {
        String expected = new StringBuilder()
                .append("* ").append(System.lineSeparator())
                .append("* * ").append(System.lineSeparator())
                .append("* * * ").append(System.lineSeparator())
                .append("* * * * ").append(System.lineSeparator())
                .append("* * * * * ").append(System.lineSeparator())
                .toString();
        new Paint().draw(new Triangle());
        assertThat(out.toString(), is(expected));
    }
}