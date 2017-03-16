package ru.job4j.calculator;
/**
 * Basic calculator.
 * @author imoskovtsev
 */
public class Calculator {
    /**
     * Calculation result.
     */
    private double result;

    /**
     * adds second to first
     * @param first first
     * @param second
     */
    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * subtracts second from first
     * @param first first
     * @param second
     */
    public void subtract(double first, double second) {
        this.result = first - second;
    }

    /**
     * devides first by second
     * @param first first
     * @param second
     */
    public void devide(double first, double second) {
        this.result = first / second;
    }

    /**
     * multiplies first by second
     * @param first first
     * @param second
     */
    public void multiply(double first, double second) {
        this.result = first * second;
    }

    /**
     * Returns calculation result
     * @return result
     */
    public double getResult() {
        return this.result;
    }
}