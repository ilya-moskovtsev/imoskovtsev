package ru.job4j.calculator;
/**
 * Basic calculator.
 */
public class Calculator {
    /**
     * Calculation result.
     */
    private double result;

    /**
     * @param first first
     * @param second
     * adds second to first
     */
    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * @param first first
     * @param second
     * subtracts second from first
     */
    public void subtract(double first, double second) {
        this.result = first - second;
    }

    /**
     * @param first first
     * @param second
     * devides first by second
     */
    public void devide(double first, double second) {
        this.result = first / second;
    }

    /**
     * @param first first
     * @param second
     * multiplies first by second
     */
    public void multiply(double first, double second) {
        this.result = first * second;
    }

    /**
     * @return result
     */
    public double getResult() {
        return this.result;
    }
}