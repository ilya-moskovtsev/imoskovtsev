package ru.job4j.max;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests maximum of two numbers.
 */
public class MaxTest {
	/**
 	* When One And Two Are Compared Then Max Is Two.
 	*/
    @Test
    public void whenOneAndTwoAreComparedThenMaxIsTwo() {
        Max max = new Max();
        int result = max.max(1, 2);
        int expected = 2;
        assertThat(result, is(expected));
    }
}
