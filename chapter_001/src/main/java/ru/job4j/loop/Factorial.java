package ru.job4j.loop;

/**
 * Вычисляет факториал.
 *
 * @author imoskovtsev
 */
public class Factorial {
    /**
     * В качестве аргумента в метод приходит положительное целое число n, метод возвращает рассчитанный факториал для этого числа.
     *
     * @param n - положительное целое число n.
     * @return int - рассчитанный факториал для числа n.
     * @throws Exception - n - не положительное целое число.
     */
    public int calc(int n) throws Exception {
        if (n < 0) {
            throw new Exception("n - не положительное целое число");
        }
        int factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        return factorial;
    }
}