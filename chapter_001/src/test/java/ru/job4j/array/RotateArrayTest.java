package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
* Проверить 5.2. Создание программы поворота квадратного массива.
* @author imoskovtsev
*/
public class RotateArrayTest {
	/**
	* Проверка поворота двумерного массив 2 X 2 по часовой стрелке.
	*/
	@Test
	public void whenRotate2X2ArrayThenResult() {
		RotateArray rotateArray = new RotateArray();
		int[][] resultArray = rotateArray.rotate(new int[][] {{1, 2}, {3, 4}});
		int[][] expectedArray = new int[][] {{3, 1}, {4, 2}};
		assertThat(resultArray, is(expectedArray));
	}
	/**
	* Проверка поворота двумерного массив 3 X 3 по часовой стрелке.
	*/
	@Test
	public void whenRotate3X3ArrayThenResult() {
		RotateArray rotateArray = new RotateArray();
		int[][] resultArray = rotateArray.rotate(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
		int[][] expectedArray = new int[][] {{7, 4, 1}, {8, 5, 2}, {9, 6, 3}};
		assertThat(resultArray, is(expectedArray));
	}
}