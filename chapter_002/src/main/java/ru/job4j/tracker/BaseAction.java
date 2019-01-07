package ru.job4j.tracker;

import java.util.function.Consumer;

/**
 * Нужно сделать абстрактный класс BaseAction с конструктором [#23232]<br>
 * 1. Реализовать абстрактный класс BaseAction, наследующий интерфейс UserAction.<br>
 * 2. Конструктор BaseAction должен принимать параметры name и key.<br>
 * 3. Реализовать метод info(), остальные методы оставить абстрактыми.<br>
 * 4. Все события наследовать от BaseAction.<br>
 *
 * @author imoskovtsev
 */
public abstract class BaseAction implements UserAction {
    /**
     * Номер пункта меню.
     */
    private final int key;

    /**
     * Название пунка меню.
     */
    private final String name;

    /**
     * Конструктор.
     *
     * @param key  Номер пункта меню.
     * @param name Название пунка меню.
     */
    public BaseAction(int key, String name) {
        this.key = key;
        this.name = name;
    }

    @Override
    public abstract void execute(Input input, Tracker tracker, Consumer<String> output);

    @Override
    public String info() {
        return String.format("%s. %s", this.key, this.name);
    }
}
