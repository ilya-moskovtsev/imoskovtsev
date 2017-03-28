package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
* Проверка слияния отсортированных целочисленных массивов.
* @author imoskovtsev
*/
public class MergeSortedArraysTest {
	/**
	* Проверка слияния двух отсортированных целочисленных массивов в один.
	* {1, 3} merged with {2, 4}
	*/
	@Test
	public void whenMergeTwoSortedArraysThenOneSortedArray1() {
		MergeSortedArrays mergeSortedArrays = new MergeSortedArrays();
		int[] resultArray = mergeSortedArrays.merge(new int[] {1, 3}, new int[] {2, 4});
		int[] expectedArray = new int[] {1, 2, 3, 4};
		assertThat(resultArray, is(expectedArray));
	}
	/**
	* Проверка слияния двух отсортированных целочисленных массивов в один.
	* {7, 9} merged with {2, 4}
	*/
	@Test
	public void whenMergeTwoSortedArraysThenOneSortedArray2() {
		MergeSortedArrays mergeSortedArrays = new MergeSortedArrays();
		int[] resultArray = mergeSortedArrays.merge(new int[] {7, 9}, new int[] {2, 4});
		int[] expectedArray = new int[] {2, 4, 7, 9};
		assertThat(resultArray, is(expectedArray));
	}
	/**
	* Проверка слияния двух отсортированных целочисленных массивов в один.
	* {2, 3} merged with {1, 9}
	*/
	@Test
	public void whenMergeTwoSortedArraysThenOneSortedArray3() {
		MergeSortedArrays mergeSortedArrays = new MergeSortedArrays();
		int[] resultArray = mergeSortedArrays.merge(new int[] {2, 3}, new int[] {1, 9});
		int[] expectedArray = new int[] {1, 2, 3, 9};
		assertThat(resultArray, is(expectedArray));
	}
}