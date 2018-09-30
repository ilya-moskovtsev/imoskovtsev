package ru.job4j.max;

/**
 * A maximum of two numbers.
 *
 * @author imoskovtsev
 */
public class Max {
    /**
     * Returns a greater number.
     *
     * @param first  first
     * @param second second
     * @return max
     */
    public int max(int first, int second) {
        return first > second ? first : second;
    }

    /**
     * Returns a greater number.
     *
     * @param first  first
     * @param second second
     * @param third  third
     * @return max
     */
    public int max(int first, int second, int third) {
        return max(first, max(second, third));
    }
}