package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Тестирование вычисления факториала.
 *
 * @author imoskovtsev
 */
public class FactorialTest {
    /**
     * Факториал для числа 5 должен быть равен 120 (1 * 2 * 3 * 4 * 5).
     * When Factorial 5 Then 120.
     *
     * @throws Exception - n - не положительное целое число.
     */
    @Test
    public void whenFactorial5Then120() throws Exception {
        Factorial factorial = new Factorial();
        int result = factorial.calc(5);
        int expected = 120;
        assertThat(result, is(expected));
    }

    /**
     * Факториал для числа 0 равен 1.
     * When Factorial 0 Then 1.
     *
     * @throws Exception - n - не положительное целое число.
     */
    @Test
    public void whenFactorial0Then1() throws Exception {
        Factorial factorial = new Factorial();
        int result = factorial.calc(0);
        int expected = 1;
        assertThat(result, is(expected));
    }

    /**
     * When Factorial Less Than Zero Then Exception.
     *
     */
    @Test
    public void whenFactorialLessThanZeroThenException() {
        Factorial factorial = new Factorial();
        try {
            factorial.calc(-1);
            fail("Expected an Exception to be thrown");
        } catch (Exception e) {
            String result = e.getMessage();
            String expected = "n - не положительное целое число";
            assertThat(result, is(expected));
        }
    }
}