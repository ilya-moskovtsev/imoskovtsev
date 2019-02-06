package ru.job4j.lambda;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Calculates function in range.
 */
public class ValueRange {
    public void calculate(int start, int finish, Function<Double, Double> function, Consumer<Double> consumer) {
        for (int i = start; i <= finish; i++) {
            consumer.accept(function.apply((double) i));
        }
    }
}
