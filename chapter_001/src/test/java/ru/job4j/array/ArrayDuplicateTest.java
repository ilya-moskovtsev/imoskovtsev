package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
* Проверить 5.3. Удаление дубликатов в массиве.
* @author imoskovtsev
*/
public class ArrayDuplicateTest {
	/**
	* Проверка удаления дубликатов строк из массива.
	* После удаления дубликатов в массиве {"Привет", "Мир", "Привет", "Супер", "Мир"} должно получиться {"Привет", "Мир", "Супер"}.
	*/
	@Test
	public void whenArrayHasDuplicatesThenRemoveDuplicates() {
		ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
		String[] resultArray = arrayDuplicate.remove(new String[] {"Привет", "Мир", "Привет", "Супер", "Мир"});
		String[] expectedArray = new String[] {"Привет", "Мир", "Супер"};
		assertThat(resultArray, is(expectedArray));
	}
}