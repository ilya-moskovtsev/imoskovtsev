package ru.job4j.srp;

import com.google.common.base.Joiner;
import ru.job4j.calculator.ICalculator;
import ru.job4j.tracker.Input;

import java.util.function.Consumer;

public abstract class BaseAction implements UserAction {

    public static final String SEPARATOR = ". ";
    private final int key;

    private final String name;

    public BaseAction(int key, String name) {
        this.key = key;
        this.name = name;
    }

    @Override
    public abstract void execute(Input in, ICalculator calc, Consumer<String> out);

    @Override
    public String info() {
        return Joiner.on(SEPARATOR).join(this.key, this.name);
    }
}
