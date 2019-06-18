package ru.job4j.srp;

import ru.job4j.calculator.ICalculator;
import ru.job4j.tracker.Input;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CalculatorMenu {

    // action names
    public static final String ADD = "Add";
    public static final String SUBTRACT = "Subtract";
    public static final String DIVIDE = "Divide";
    public static final String MULTIPLY = "Multiply";
    public static final String GET_RESULT = "Get result";
    public static final String EXIT_PROGRAM = "Exit Program";
    // ask
    public static final String PLEASE_ENTER_THE_FIRST_NUMBER = "Please, enter the first number: ";
    public static final String PLEASE_ENTER_THE_SECOND_NUMBER = "Please, enter the second number: ";

    private final Input in;
    private final ICalculator calc;
    private final Consumer<String> out;

    private final List<UserAction> actions = new ArrayList<>();

    public CalculatorMenu(Input in, ICalculator calc, Consumer<String> out) {
        this.in = in;
        this.calc = calc;
        this.out = out;
    }

    public Input getIn() {
        return in;
    }

    public void fillActions() {
        actions.add(new Add(Menu.ADD, ADD));
        actions.add(new Subtract(Menu.SUBTRACT, SUBTRACT));
        actions.add(new Divide(Menu.DIVIDE, DIVIDE));
        actions.add(new Multiply(Menu.MULTIPLY, MULTIPLY));
        actions.add(new GetResult(Menu.GET_RESULT, GET_RESULT));
        actions.add(new ExitProgram(Menu.EXIT, EXIT_PROGRAM));
    }

    public void select(int key) {
        actions.get(key).execute(in, calc, out);
    }

    public void show() {
        for (UserAction action : actions) {
            if (action != null) {
                out.accept(action.info());
            }
        }
    }

    private class Add extends BaseAction {
        public Add(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input in, ICalculator calc, Consumer<String> out) {
            calc.add(
                    Integer.parseInt(in.ask(PLEASE_ENTER_THE_FIRST_NUMBER)),
                    Integer.parseInt(in.ask(PLEASE_ENTER_THE_SECOND_NUMBER))
            );
        }
    }

    private class Subtract extends BaseAction {
        public Subtract(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input in, ICalculator calc, Consumer<String> out) {
            calc.subtract(
                    Integer.parseInt(in.ask(PLEASE_ENTER_THE_FIRST_NUMBER)),
                    Integer.parseInt(in.ask(PLEASE_ENTER_THE_SECOND_NUMBER))
            );
        }
    }

    private class Divide extends BaseAction {
        public Divide(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input in, ICalculator calc, Consumer<String> out) {
            calc.divide(
                    Integer.parseInt(in.ask(PLEASE_ENTER_THE_FIRST_NUMBER)),
                    Integer.parseInt(in.ask(PLEASE_ENTER_THE_SECOND_NUMBER))
            );
        }
    }

    private class Multiply extends BaseAction {
        public Multiply(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input in, ICalculator calc, Consumer<String> out) {
            calc.multiply(
                    Integer.parseInt(in.ask(PLEASE_ENTER_THE_FIRST_NUMBER)),
                    Integer.parseInt(in.ask(PLEASE_ENTER_THE_SECOND_NUMBER))
            );
        }
    }

    private class GetResult extends BaseAction {
        public GetResult(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input in, ICalculator calc, Consumer<String> out) {
            out.accept(Double.toString(calc.getResult()));
        }
    }

    private static class ExitProgram extends BaseAction {
        public ExitProgram(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input in, ICalculator calc, Consumer<String> output) {
            CalculatorInteraction.setIsDone(true);
        }
    }
}
