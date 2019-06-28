package ru.job4j.ocp;

import ru.job4j.calculator.Calculator;
import ru.job4j.calculator.ICalculator;

public class EngineeringCalculator implements ICalculator {
    public static final double DOUBLE_1 = 1D;
    private double result;
    private final ICalculator cal = new Calculator();

    public void add(double first, double second) {
        cal.add(first, second);
        result = cal.getResult();
    }

    public void subtract(double first, double second) {
        cal.subtract(first, second);
        result = cal.getResult();
    }

    public void divide(double first, double second) {
        cal.divide(first, second);
        result = cal.getResult();
    }

    public void multiply(double first, double second) {
        cal.multiply(first, second);
        result = cal.getResult();
    }

    public void sin(double a) {
        result = Math.sin(Math.toRadians(a));
    }

    public void cos(double a) {
        result = Math.cos(Math.toRadians(a));
    }

    public void tan(double a) {
        result = Math.tan(Math.toRadians(a));
    }

    public void cot(double a) {
        result = DOUBLE_1 / Math.tan(Math.toRadians(a));
    }

    public double getResult() {
        return result;
    }
}
