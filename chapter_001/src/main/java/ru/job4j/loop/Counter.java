package ru.job4j.loop;

/**
 * Подсчет суммы чётных чисел в диапазоне.
 *
 * @author imoskovtsev
 */
public class Counter {
    /**
     * Вычисляет сумму четных чисел в диапазоне от start до finish.
     *
     * @param start  - начало диапазона.
     * @param finish - конец диапазона.
     * @return int - сумма четных чисел в диапазоне от start до finish.
     */
    public int add(int start, int finish) {
        int sum = 0;
        for (int i = start; i <= finish; i++) {
            if (i % 2 == 0) {
                sum += i;
            }
        }
        return sum;
    }
}