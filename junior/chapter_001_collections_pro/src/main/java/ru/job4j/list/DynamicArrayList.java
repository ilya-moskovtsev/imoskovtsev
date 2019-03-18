package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicArrayList<E> implements Iterable<E> {

    @SuppressWarnings("unchecked")
    private E[] array = (E[]) new Object[10];
    private int index = 0;
    private int modificationCounter = 0;

    public void add(E element) {
        modificationCounter++;
        enlargeIfNeeded();
        array[index++] = element;
    }

    public E get(int index) {
        return array[index];
    }

    public Iterator<E> iterator() {
        return new Iterator<>() {

            private int expectedModificationCounter = modificationCounter;
            private int iteratorIndex = 0;

            @Override
            public boolean hasNext() {
                if (modificationCounter != expectedModificationCounter) {
                    throw new ConcurrentModificationException();
                }
                return iteratorIndex < index;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (modificationCounter != expectedModificationCounter) {
                    throw new ConcurrentModificationException();
                }
                return array[iteratorIndex++];
            }
        };
    }

    private void enlargeIfNeeded() {
        if (array.length <= index) {
            @SuppressWarnings("unchecked")
            E[] longerArray = (E[]) new Object[(int) (array.length * 1.5)];
            System.arraycopy(array, 0, longerArray, 0, array.length);
            this.array = longerArray;
        }
    }
}