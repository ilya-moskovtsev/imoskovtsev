package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class GenericArrayContainer<E> implements Iterable<E> {
    private E[] array;
    private int index = 0;

    public GenericArrayContainer(int length) {
        array = (E[]) new Object[length];
    }

    public void add(E element) throws ArrayIndexOutOfBoundsException {
        array[index++] = element;
    }

    public void set(int index, E element) {
        array[index] = element;
    }

    public void remove(int index) {
        E[] newArray = (E[]) new Object[array.length - 1];
        System.arraycopy(array, 0, newArray, 0, index); // copy left side
        System.arraycopy(array, index + 1, newArray, index, array.length - index - 1); // copy right side
        array = newArray;
    }

    public E get(int index) {
        return array[index];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < array.length;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return array[index++];
            }
        };
    }
}
