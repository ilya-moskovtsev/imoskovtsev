package ru.job4j.array;
/**
* Слияние отсортированных целочисленных массивов.
* @author imoskovtsev
*/
public class MergeSortedArrays {
	/**
	* Метод должен слить два отсортированных целочисленных массива в один.
	* @param first - первый массив.
	* @param second - второй массив.
	* @return int[] - результирующий массив.
	*/
	public int[] merge(int[] first, int[] second) {
		int[] merged = new int[first.length + second.length];
		int firstCurrent = 0;
		int secondCurrent = 0;
		for (int i = 0; i < merged.length; i++) {
			if (secondCurrent == second.length && firstCurrent < first.length) {
				merged[i] = first[firstCurrent];
				firstCurrent++;
			}
			if (firstCurrent == first.length && secondCurrent < second.length) {
				merged[i] = second[secondCurrent];
				secondCurrent++;
			}
			if (firstCurrent < first.length && secondCurrent < second.length) {
				if (first[firstCurrent] < second[secondCurrent]) {
					merged[i] = first[firstCurrent];
					firstCurrent++;
				} else {
					merged[i] = second[secondCurrent];
					secondCurrent++;
				}
			}
		}
		return merged;
	}
}