package ru.job4j.calculator;

/**
 * Basic calculator.
 *
 * @author imoskovtsev
 */
public class Calculator {
    /**
     * Calculation result.
     */
    private double result;

    /**
     * adds second to first.
     *
     * @param first  - first value
     * @param second - second value
     */
    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * subtracts second from first.
     *
     * @param first  - first value
     * @param second - second value
     */
    public void subtract(double first, double second) {
        this.result = first - second;
    }

    /**
     * divides first by second.
     *
     * @param first  - first value
     * @param second - second value
     */
    public void divide(double first, double second) {
        this.result = first / second;
    }

    /**
     * multiplies first by second.
     *
     * @param first  - first value
     * @param second - second value
     */
    public void multiply(double first, double second) {
        this.result = first * second;
    }

    /**
     * Returns calculation result.
     *
     * @return result
     */
    public double getResult() {
        return this.result;
    }
}