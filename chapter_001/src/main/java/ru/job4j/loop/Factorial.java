package ru.job4j.loop;
/**
 * Вычисляет факториал.
 */
public class Factorial {
	/**
	* @param n - положительное целое число n.
	* @return int - рассчитанный факториал для числа n.
	* @throws Exception - n - не положительное целое число.
	* В качестве аргумента в метод приходит положительное целое число n, метод возвращает рассчитанный факториал для этого числа.
	*/
	public int calc(int n) throws Exception {
		if (n == 0) {
			return 1;
		} else if (n > 0) {
			int factorial = 1;
			for (int i = 1; i <= n; i++) {
				factorial *= i;
			}
			return factorial;
		} else {
			throw new Exception("n - не положительное целое число");
		}
	}
}