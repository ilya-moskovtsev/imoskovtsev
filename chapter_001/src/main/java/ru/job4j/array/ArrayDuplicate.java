package ru.job4j.array;
import static java.util.Arrays.copyOf;
/**
* 5.3. Удаление дубликатов в массиве.
* @author imoskovtsev
*/
public class ArrayDuplicate {
	/**
	* Метод должен убрать все дубликаты строк из массива.
	* В задаче нельзя использовать дополнительные массивы.
	* Для обрезания массива надо использовать Arrays.copyOf метод.
	* @param array - исходный массив.
	* @return String[] - массив без дубликатов строк.
	*/
	public String[] remove(String[] array) {
		int countDuplicates = 0;
		for (int i = 0; i < array.length - 1 - countDuplicates; i++) {
			for (int j = i + 1; j < array.length - 1 - countDuplicates; j++) {
				if (array[i].equals(array[j])) {
					String temp = array[j];
					array[j] = array[array.length - 1 - countDuplicates];
					array[array.length - 1 - countDuplicates] = temp;
					countDuplicates++;
				}
			}
		}
		return copyOf(array, array.length - countDuplicates);
	}
}