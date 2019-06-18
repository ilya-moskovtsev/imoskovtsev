package ru.job4j.srp;

import ru.job4j.calculator.ICalculator;
import ru.job4j.tracker.Input;

import java.util.function.Consumer;

public interface UserAction {
    void execute(Input in, ICalculator calc, Consumer<String> out);

    String info();
}
