package ru.job4j.loop;
/**
 * Подсчет суммы чётных чисел в диапазоне.
 */
public class Counter {
	/**
  	* @param start - начало диапазона.
  	* @param finish - конец диапазона.
  	* @return int - сумма четныx чисел в диапазоне от start до finish.
  	* Вычисляет сумму четныx чисел в диапазоне от start до finish.
  	*/
	public int add(int start, int finish) {
		int summ = 0;
		for (int i = start; i <= finish; i++) {
			if (i % 2 == 0) {
				summ += i;
			}
		}
		return summ;
	}
}