package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 *  Тестирование подсчета суммы чётных чисел в диапазоне.
 */
 public class CounterTest {
 	/**
	* When Start One And Finish Ten Then Sum Of Even Numbers Is Thirty.
	*/
	@Test
	public void whenStartOneAndFinishTenThenSumOfEvenNumbersIsThirty() {
		Counter counter = new Counter();
		int result = counter.add(1, 10);
		int expected = 30;
		assertThat(result, is(expected));
	}
}