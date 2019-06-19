package ru.job4j.srp;

import ru.job4j.calculator.ICalculator;
import ru.job4j.ocp.EngineeringCalculator;
import ru.job4j.tracker.Input;
import ru.job4j.tracker.ValidateInput;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class CalculatorInteraction {
    // ask
    public static final String SELECT = "Select:";
    private static Input in;
    private static ICalculator calc;
    private static Consumer<String> out;

    public CalculatorInteraction(Input in, ICalculator calc, Consumer<String> out) {
        CalculatorInteraction.in = in;
        CalculatorInteraction.calc = calc;
        CalculatorInteraction.out = out;
    }

    private static boolean isDone = false;

    public static void setIsDone(boolean isDone) {
        CalculatorInteraction.isDone = isDone;
    }

    private static final List<Integer> RANGES = Arrays.asList(
            Menu.ADD,
            Menu.SUBTRACT,
            Menu.DIVIDE,
            Menu.MULTIPLY,
            Menu.SIN,
            Menu.COS,
            Menu.TAN,
            Menu.COT,
            Menu.GET_RESULT,
            Menu.EXIT
    );

    public static void main(String[] args) {
        new CalculatorInteraction(new ValidateInput(), new EngineeringCalculator(), System.out::println);
        CalculatorMenu menu = new CalculatorMenu(in, calc, out);
        actions(menu);
    }

    public static void actions(CalculatorMenu menu) {
        menu.fillActions();
        isDone = false;
        while (!isDone) {
            menu.show();
            menu.select(menu.getIn().ask(SELECT, RANGES));
        }
    }
}
