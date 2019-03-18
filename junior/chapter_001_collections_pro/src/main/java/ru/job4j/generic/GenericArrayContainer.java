package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class GenericArrayContainer<E> implements Iterable<E> {
    private E[] array;
    private int index = 0;

    @SuppressWarnings("unchecked")
    public GenericArrayContainer(int length) {
        array = (E[]) new Object[length];
    }

    public void add(E element) throws ArrayIndexOutOfBoundsException {
        array[index++] = element;
    }

    public void set(int index, E element) {
        if (index > this.index) {
            throw new ArrayIndexOutOfBoundsException();
        }
        array[index] = element;
    }

    public void remove(int index) {
        System.arraycopy(array, index + 1, array, index, array.length - index - 1);
        this.index--;
    }

    public E get(int index) {
        return array[index];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            private int iteratorIndex = 0;

            @Override
            public boolean hasNext() {
                return iteratorIndex < index;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return array[iteratorIndex++];
            }
        };
    }
}
