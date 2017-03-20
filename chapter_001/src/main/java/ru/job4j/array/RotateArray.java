package ru.job4j.array;
/**
* 5.2. Создание программы поворота квадратного массива.
* @author imoskovtsev
*/
public class RotateArray {
	/**
	* Метод должен повернуть двумерный массив по часовой стрелке.
	* Задачу можно решить с использованием дополнительного массива, так и без него.
	* @param array - исходный массив.
	* @return int[][] - массив повернутый по часовой стрелке на 90 градусов.
	*/
	public int[][] rotate(int[][] array) {
		int stop1 = array.length / 2;
		int stop2 = array.length / 2;
		if (array.length % 2 != 0) {
			stop2++;
		}
		int temp = 0;
		for (int i = 0; i < stop1; i++) {
			for (int j = 0; j < stop2; j++) {
				temp = array[j][i];
				array[j][i] = array[array.length - 1 - i][j];
				array[array.length - 1 - i][j] = array[array.length - 1 - j][array.length - 1 - i];
				array[array.length - 1 - j][array.length - 1 - i] = array[i][array.length - 1 - j];
				array[i][array.length - 1 - j] = temp;
			}
		}
		return array;
	}
}