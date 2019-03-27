package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

/**
 * Internal implementation must use an array.
 * Time to put and to retrieve must be fixed.
 * Hash table size must increase should there be no space for a new element.
 * <p>
 * There is no need to implement collision resolution methods.
 * Return false when you add a key that already exists.
 */
public class SimpleHashMap<K, V> implements Iterable<V> {

    @SuppressWarnings("unchecked")
    private V[] array = (V[]) new Object[16];
    private int size = 0;
    private int modifications = 0;

    public boolean put(K key, V value) {
        modifications++;
        enlargeIfNeeded();
        boolean success = false;
        if (array[index(key)] == null) {
            array[index(key)] = value;
            size++;
            success = true;
        }
        return success;
    }

    public V get(K key) throws NoSuchElementException {
        if (array[index(key)] == null) {
            throw new NoSuchElementException();
        }
        return array[index(key)];
    }

    public boolean remove(K key) {
        boolean success = false;
        if (array[index(key)] != null) {
            array[index(key)] = null;
            size--;
            success = true;
        }
        return success;
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<>() {

            private int expectedModifications = modifications;
            private int index = 0;

            @Override
            public boolean hasNext() {
                if (modifications != expectedModifications) {
                    throw new ConcurrentModificationException();
                }
                index += (int) IntStream.range(index, array.length)
                        .takeWhile(i -> array[i] == null)
                        .count();
                return index < array.length;
            }

            @Override
            public V next() {
                if (modifications != expectedModifications) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return array[index++];
            }
        };
    }

    private int index(K key) {
        return key.hashCode() % array.length;
    }

    private void enlargeIfNeeded() {
        if (array.length * 0.75 <= size) {
            @SuppressWarnings("unchecked")
            V[] longerArray = (V[]) new Object[(int) (array.length * 1.5)];
            System.arraycopy(array, 0, longerArray, 0, array.length);
            array = longerArray;
        }
    }
}
