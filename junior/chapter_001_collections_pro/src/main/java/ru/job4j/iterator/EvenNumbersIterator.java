package ru.job4j.iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Int array iterator that iterates over even numbers.
 */
public class EvenNumbersIterator implements Iterator {

    private int[] evenNumbers;

    private int index;

    public EvenNumbersIterator(int[] numbers) {
        evenNumbers = Arrays.stream(numbers)
                .filter(number -> number % 2 == 0)
                .toArray();
        index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < evenNumbers.length;
    }

    @Override
    public Object next() {
        try {
            return evenNumbers[index++];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoSuchElementException();
        }
    }
}
