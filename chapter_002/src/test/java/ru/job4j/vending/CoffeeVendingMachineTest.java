package ru.job4j.vending;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CoffeeVendingMachineTest {

    @ParameterizedTest(name = "{index}. Value: {0}, Price: {1}, Change: {2}")
    @MethodSource("valuePriceProvider")
    public void giveChange(int value, int price, List<Integer> expected) {
        CoffeeVendingMachine coffeeVendingMachine = new CoffeeVendingMachine();
        int[] actual = coffeeVendingMachine.giveChange(value, price);
        assertThat(actual, is(expected.toArray()));
    }

    public static Stream<Arguments> valuePriceProvider() {
        return Stream.of(
                Arguments.of(50, 50, Collections.emptyList()),
                Arguments.of(50, 49, Collections.singletonList(1)),
                Arguments.of(50, 48, Collections.singletonList(2)),
                Arguments.of(50, 47, Arrays.asList(2, 1)),
                Arguments.of(50, 46, Arrays.asList(2, 2)),
                Arguments.of(50, 45, Collections.singletonList(5)),
                Arguments.of(50, 44, Arrays.asList(5, 1)),
                Arguments.of(50, 43, Arrays.asList(5, 2)),
                Arguments.of(50, 42, Arrays.asList(5, 2, 1)),
                Arguments.of(50, 41, Arrays.asList(5, 2, 2)),
                Arguments.of(50, 40, Collections.singletonList(10)),
                Arguments.of(50, 39, Arrays.asList(10, 1)),
                Arguments.of(50, 38, Arrays.asList(10, 2)),
                Arguments.of(50, 37, Arrays.asList(10, 2, 1)),
                Arguments.of(50, 36, Arrays.asList(10, 2, 2)),
                Arguments.of(50, 35, Arrays.asList(10, 5)),
                Arguments.of(50, 34, Arrays.asList(10, 5, 1)),
                Arguments.of(50, 33, Arrays.asList(10, 5, 2)),
                Arguments.of(50, 32, Arrays.asList(10, 5, 2, 1)),
                Arguments.of(50, 31, Arrays.asList(10, 5, 2, 2)),
                Arguments.of(50, 30, Arrays.asList(10, 10))
        );
    }
}