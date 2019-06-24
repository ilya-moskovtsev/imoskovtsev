package ru.job4j.isp;

import java.util.function.Consumer;

public interface Print {
    void print(String prefix, Consumer<String> output);
}
