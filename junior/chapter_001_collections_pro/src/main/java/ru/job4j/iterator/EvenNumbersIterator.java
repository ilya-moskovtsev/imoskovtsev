package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * Int array iterator that iterates over even numbers.
 */
public class EvenNumbersIterator implements Iterator {

    private int[] numbers;

    private int index = 0;

    public EvenNumbersIterator(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean hasNext() {
        return getFirstEven().isPresent();
    }

    @Override
    public Object next() throws NoSuchElementException {
        index = getFirstEven().orElseThrow();
        return numbers[index++];
    }

    private OptionalInt getFirstEven() {
        return IntStream.range(index, numbers.length)
                .filter(i -> numbers[i] % 2 == 0)
                .findFirst();
    }
}
