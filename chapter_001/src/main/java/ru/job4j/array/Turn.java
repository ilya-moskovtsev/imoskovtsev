package ru.job4j.array;

/**
 * 5.0 Перевернуть массив.
 *
 * @author imoskovtsev
 */
public class Turn {
    /**
     * Метод должен перевернуть массив задом наперёд.
     *
     * @param array - массив, который необходимо перевернуть.
     * @return int[] - перевернутый массив.
     */
    public int[] back(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
        return array;
    }
}