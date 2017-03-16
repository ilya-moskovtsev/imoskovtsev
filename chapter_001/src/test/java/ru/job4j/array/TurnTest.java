package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Проверка 5.0 Перевернуть массив.
* @author imoskovtsev
*/
public class TurnTest {
	/**
	* Перевернуть массив с чётным количеством элементов.
	* Массив {4, 1, 6, 2} после переворота должен получиться {2, 6, 1, 4}
	*/
	@Test
	public void whenEvenNumberOfElementsThenReverse() {
		Turn turn = new Turn();
		int[] resultArray = turn.back(new int[] {4, 1, 6, 2});
		int[] expectArray = new int[] {2, 6, 1, 4};
		assertThat(resultArray, is(expectArray));
	}
	/**
	* Перевернуть массив с нечётным количеством элементов.
	* Массив {1, 2, 4, 3, 5} после переворота должен получиться {5, 3, 4, 2, 1}
	*/
	@Test
	public void whenOddNumberOfElementsThenReverse() {
		Turn turn = new Turn();
		int[] resultArray = turn.back(new int[] {1, 2, 4, 3, 5});
		int[] expectArray = new int[] {5, 3, 4, 2, 1};
		assertThat(resultArray, is(expectArray));
	}
}