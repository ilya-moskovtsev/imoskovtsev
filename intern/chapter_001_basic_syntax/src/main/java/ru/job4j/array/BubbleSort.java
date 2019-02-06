package ru.job4j.array;

/**
 * 5.1. Создать программу для сортировки массива методом перестановки.
 *
 * @author imoskovtsev
 */
public class BubbleSort {
    /**
     * Метод должен сортировать массив целых чисел, используя алгоритм сортировки пузырьком.
     *
     * @param array - массив, который необходимо перевернуть.
     * @return int[] - перевернутый массив.
     */
    public int[] sort(int[] array) {
        for (int i = array.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }
}