package ru.job4j.srp;

import org.junit.jupiter.api.Test;
import ru.job4j.calculator.Calculator;
import ru.job4j.calculator.ICalculator;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.StubInput;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static ru.job4j.srp.Menu.ADD;
import static ru.job4j.srp.Menu.DIVIDE;
import static ru.job4j.srp.Menu.EXIT;
import static ru.job4j.srp.Menu.MULTIPLY;
import static ru.job4j.srp.Menu.SUBTRACT;

public class CalculatorInteractionTest {
    // input
    public static final String STRING_1 = "1";
    public static final String STRING_2 = "2";
    public static final String STRING_5 = "5";

    // result
    public static final double DOUBLE_0 = 0D;
    public static final double DOUBLE_2 = 2D;
    public static final double DOUBLE_2_POINT_5 = 2.5D;
    public static final double DOUBLE_10 = 10D;

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final Consumer<String> output = new Consumer<>() {
        private final PrintStream stdout = new PrintStream(out);

        @Override
        public void accept(String s) {
            stdout.println(s);
        }
    };

    @Test
    public void whenAddOnePlusOneThenTwo() {
        ICalculator calc = new Calculator();
        Input input = new StubInput(Arrays.asList(
                String.valueOf(ADD),
                STRING_1,
                STRING_1,
                String.valueOf(EXIT)));
        new CalculatorInteraction(input, calc, output);
        CalculatorMenu menu = new CalculatorMenu(input, calc, output);
        CalculatorInteraction.actions(menu);

        assertThat(calc.getResult(), is(DOUBLE_2));
    }

    @Test
    public void whenSubtractOneMinusOneThenZero() {
        ICalculator calc = new Calculator();
        Input input = new StubInput(Arrays.asList(
                String.valueOf(SUBTRACT),
                STRING_1,
                STRING_1,
                String.valueOf(EXIT)));
        new CalculatorInteraction(input, calc, output);
        CalculatorMenu menu = new CalculatorMenu(input, calc, output);
        CalculatorInteraction.actions(menu);

        assertThat(calc.getResult(), is(DOUBLE_0));
    }

    @Test
    public void whenDivideFiveByTwoThenTwoAndAHalf() {
        ICalculator calc = new Calculator();
        Input input = new StubInput(Arrays.asList(
                String.valueOf(DIVIDE),
                STRING_5,
                STRING_2,
                String.valueOf(EXIT)));
        new CalculatorInteraction(input, calc, output);
        CalculatorMenu menu = new CalculatorMenu(input, calc, output);
        CalculatorInteraction.actions(menu);

        assertThat(calc.getResult(), is(DOUBLE_2_POINT_5));
    }

    @Test
    public void whenMultiplyFiveByTwoThenTen() {
        ICalculator calc = new Calculator();
        Input input = new StubInput(Arrays.asList(
                String.valueOf(MULTIPLY),
                STRING_5,
                STRING_2,
                String.valueOf(EXIT)));
        new CalculatorInteraction(input, calc, output);
        CalculatorMenu menu = new CalculatorMenu(input, calc, output);
        CalculatorInteraction.actions(menu);

        assertThat(calc.getResult(), is(DOUBLE_10));
    }
}