package ru.job4j.stringcomparator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.MatcherAssert.assertThat;

public class StringComparatorTest {

    @Test
    public void whenStringsAreEqualThenZero() {
        StringComparator comparator = new StringComparator();
        int rst = comparator.compare(
                "Ivanov",
                "Ivanov"
        );
        assertThat(rst, is(0));
    }

    @Test
    public void whenLeftLessThanRightResultShouldBeNegative() {
        StringComparator comparator = new StringComparator();
        int rst = comparator.compare(
                "Ivanov",
                "Ivanova"
        );
        assertThat(rst, lessThan(0));
    }

    @Test
    public void whenLeftGreaterThanRightResultShouldBePositive() {
        StringComparator comparator = new StringComparator();
        int rst = comparator.compare(
                "Petrov",
                "Ivanova"
        );
        assertThat(rst, greaterThan(0));
    }

    @Test
    public void secondCharOfLeftGreaterThanRightShouldBePositive() {
        StringComparator comparator = new StringComparator();
        int rst = comparator.compare(
                "Petrov",
                "Patrov"
        );
        assertThat(rst, greaterThan(0));
    }

    @Test
    public void secondCharOfLeftLessThanRightShouldBeNegative() {
        StringComparator comparator = new StringComparator();
        int rst = comparator.compare(
                "Patrov",
                "Petrov"
        );
        assertThat(rst, lessThan(0));
    }
}