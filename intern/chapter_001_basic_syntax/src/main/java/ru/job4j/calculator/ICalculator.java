package ru.job4j.calculator;

public interface ICalculator {
    void add(double first, double second);

    void subtract(double first, double second);

    void divide(double first, double second);

    void multiply(double first, double second);

    double getResult();
}
