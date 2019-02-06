package ru.job4j.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ValueRangeTest {

    @Test
    public void calculateLinearFunctionInRange() {
        ValueRange valueRange = new ValueRange();
        List<Double> results = new ArrayList<>();
        valueRange.calculate(1, 3,
                x -> (double) 2 * x + 1,
                results::add
        );
        assertThat(results, is(Arrays.asList(3d, 5d, 7d)));
    }

    @Test
    public void calculateQuadraticFunctionInRange() {
        ValueRange valueRange = new ValueRange();
        List<Double> results = new ArrayList<>();
        valueRange.calculate(1, 3,
                x -> Math.pow(x, 2),
                results::add
        );
        assertThat(results, is(Arrays.asList(1d, 4d, 9d)));
    }

    @Test
    public void calculateLogarithmInRange() {
        ValueRange valueRange = new ValueRange();
        List<Double> results = new ArrayList<>();
        valueRange.calculate(1, 3,
                Math::log,
                results::add
        );
        assertThat(results, is(Arrays.asList(0d, 0.6931471805599453, 1.0986122886681098)));
    }
}