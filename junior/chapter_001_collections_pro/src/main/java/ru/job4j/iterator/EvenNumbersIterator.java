package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
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
        this.index += (int) IntStream.range(index, numbers.length)
                .takeWhile(i -> numbers[i] % 2 != 0)
                .count();
        return index < numbers.length;
    }

    @Override
    public Object next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return numbers[index++];
    }
}
