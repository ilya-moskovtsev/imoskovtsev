package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
* Проверить Тестовое задание.
* @author imoskovtsev
*/
public class SubStringTest {
	/**
	* Cтрока sub является подстрокой origin.
	*/
	@Test
	public void whenSubIsSubstringOfOriginThenTrue() {
		SubString subString = new SubString();
		String origin = "0123456789";
		String sub = "456";
		boolean result = subString.contains(origin, sub);
		boolean expected = true;
		assertThat(result, is(expected));
	}
	/**
	* Cтрока sub не является подстрокой origin.
	*/
	@Test
	public void whenSubIsNotSubstringOfOriginThenFalse() {
		SubString subString = new SubString();
		String origin = "0123456789";
		String sub = "654";
		boolean result = subString.contains(origin, sub);
		boolean expected = false;
		assertThat(result, is(expected));
	}
}