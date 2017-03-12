package ru.job4j.max;
/**
 * A maximum of two numbers.
 */
public class Max {
	/**
     * @param first first
     * @param second second
     * @return max
     * Returns a greater number
     */
	public int max(int first, int second) {
		return first > second ? first : second;
	}
	/**
     * @param first first
     * @param second second
     * @param third third
     * @return max
     * Returns a greater number
     */
	public int max(int first, int second, int third) {
		return max(first, max(second, third));
	}
}