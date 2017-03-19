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
	* @return int[][] - массив повернутый по часовой стрелке.
	*/
	public int[][] rotate(int[][] array) {
		if (array.length == 2) {
			int temp = array[0][0];
			array[0][0] = array[1][0];
			array[1][0] = array[1][1];
			array[1][1] = array[0][1];
			array[0][1] = temp;
		} else if (array.length > 2) {
			int temp = 0;
			for (int i = 0; i < array.length - 1; i++) {
				temp = array[0][i];
				array[0][i] = array[array.length - 1 - i][0];
				array[array.length - 1 - i][0] = array[array.length - 1][array.length - 1 - i];
				array[array.length - 1][array.length - 1 - i] = array[i][array.length - 1];
				array[i][array.length - 1] = temp;
			}
		}
		return array;
	}
}