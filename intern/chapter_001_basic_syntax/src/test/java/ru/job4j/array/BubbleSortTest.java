package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Проверка 5.1. Создать программу для сортировки массива методом перестановки.
 *
 * @author imoskovtsev
 */
public class BubbleSortTest {
    /**
     * Проверка сортировки массива.
     * Массив {4, 1, 6, 2} после сортировки должен получиться {1, 2, 4, 6}
     */
    @Test
    public void whenEvenNumberOfElementsThenReverse() {
        BubbleSort bubbleSort = new BubbleSort();
        int[] resultArray = bubbleSort.sort(new int[]{4, 1, 6, 2});
        int[] expectedArray = new int[]{1, 2, 4, 6};
        assertThat(resultArray, is(expectedArray));
    }
}