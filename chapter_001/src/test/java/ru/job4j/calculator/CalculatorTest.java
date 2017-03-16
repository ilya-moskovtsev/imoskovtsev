package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests covering basic calculator.
 * @author imoskovtsev
 */
public class CalculatorTest {
	/**
 	* When Add One Plus One Then Two.
 	*/
    @Test
    public void whenAddOnePlusOneThenTwo() {
        Calculator calc = new Calculator();
        calc.add(1D, 1D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }
    /**
 	* When Subtract One Minus One Then Zero.
 	*/
    @Test
    public void whenSubtractOneMinusOneThenZero() {
        Calculator calc = new Calculator();
        calc.subtract(1D, 1D);
        double result = calc.getResult();
        double expected = 0D;
        assertThat(result, is(expected));
    }
    /**
 	* When Devide Five By Two Then Two And A Half.
 	*/
    @Test
    public void whenDevideFiveByTwoThenTwoAndAHalf() {
        Calculator calc = new Calculator();
        calc.devide(5D, 2D);
        double result = calc.getResult();
        double expected = 2.5D;
        assertThat(result, is(expected));
    }
    /**
 	* When Multiply Five By Two Then Ten.
 	*/
    @Test
    public void whenMultiplyFiveByTwoThenTen() {
        Calculator calc = new Calculator();
        calc.multiply(5D, 2D);
        double result = calc.getResult();
        double expected = 10D;
        assertThat(result, is(expected));
    }
}