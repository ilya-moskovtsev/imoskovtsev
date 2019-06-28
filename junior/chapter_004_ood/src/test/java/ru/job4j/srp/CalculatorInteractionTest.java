package ru.job4j.srp;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import ru.job4j.calculator.Calculator;
import ru.job4j.calculator.ICalculator;
import ru.job4j.ocp.EngineeringCalculator;
import ru.job4j.tracker.Input;
import ru.job4j.tracker.StubInput;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.util.Arrays;
import java.util.function.Consumer;

import static ru.job4j.srp.Menu.ADD;
import static ru.job4j.srp.Menu.COS;
import static ru.job4j.srp.Menu.COT;
import static ru.job4j.srp.Menu.DIVIDE;
import static ru.job4j.srp.Menu.EXIT;
import static ru.job4j.srp.Menu.MULTIPLY;
import static ru.job4j.srp.Menu.SIN;
import static ru.job4j.srp.Menu.SUBTRACT;
import static ru.job4j.srp.Menu.TAN;

public class CalculatorInteractionTest {
    // input
    public static final String STRING_1 = "1";
    public static final String STRING_2 = "2";
    public static final String STRING_5 = "5";
    public static final String STRING_30 = "30";
    public static final String STRING_60 = "60";

    // result
    public static final double DOUBLE_0 = 0D;
    public static final double DOUBLE_2 = 2D;
    public static final double DOUBLE_2_POINT_5 = 2.5;
    public static final double DOUBLE_10 = 10D;
    public static final double DOUBLE_SIN_30 = 0.49999999999999994;
    public static final double DOUBLE_COS_30 = 0.5000000000000001;
    public static final double DOUBLE_TAN_60 = 1.7320508075688767;
    public static final double DOUBLE_COT_30 = 1.7320508075688774;

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

    @Test
    public void whenSine30ThenOneHalf() {
        ICalculator calc = new EngineeringCalculator();
        Input input = new StubInput(Arrays.asList(
                String.valueOf(SIN),
                STRING_30,
                String.valueOf(EXIT)));
        new CalculatorInteraction(input, calc, output);
        CalculatorMenu menu = new CalculatorMenu(input, calc, output);
        CalculatorInteraction.actions(menu);

        assertThat(calc.getResult(), is(DOUBLE_SIN_30));
    }

    @Test
    public void whenCosine60ThenOneHalf() {
        ICalculator calc = new EngineeringCalculator();
        Input input = new StubInput(Arrays.asList(
                String.valueOf(COS),
                STRING_60,
                String.valueOf(EXIT)));
        new CalculatorInteraction(input, calc, output);
        CalculatorMenu menu = new CalculatorMenu(input, calc, output);
        CalculatorInteraction.actions(menu);

        assertThat(calc.getResult(), is(DOUBLE_COS_30));
    }

    @Test
    public void whenTangent60ThenSquareRootOf3() {
        ICalculator calc = new EngineeringCalculator();
        Input input = new StubInput(Arrays.asList(
                String.valueOf(TAN),
                STRING_60,
                String.valueOf(EXIT)));
        new CalculatorInteraction(input, calc, output);
        CalculatorMenu menu = new CalculatorMenu(input, calc, output);
        CalculatorInteraction.actions(menu);

        assertThat(calc.getResult(), is(DOUBLE_TAN_60));
    }

    @Test
    public void whenCotangent30ThenSquareRootOf3() {
        ICalculator calc = new EngineeringCalculator();
        Input input = new StubInput(Arrays.asList(
                String.valueOf(COT),
                STRING_30,
                String.valueOf(EXIT)));
        new CalculatorInteraction(input, calc, output);
        CalculatorMenu menu = new CalculatorMenu(input, calc, output);
        CalculatorInteraction.actions(menu);

        assertThat(calc.getResult(), is(DOUBLE_COT_30));
    }
}