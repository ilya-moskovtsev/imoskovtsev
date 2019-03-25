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
    private int pairCounter = 0;
    private int modificationCounter = 0;

    public boolean put(K key, V value) {
        modificationCounter++;
        enlargeIfNeeded();
        if (array[index(key)] == null) {
            array[index(key)] = value;
            pairCounter++;
            return true;
        }
        return false;
    }

    public V get(K key) throws NoSuchElementException {
        if (array[index(key)] == null) {
            throw new NoSuchElementException();
        }
        return array[index(key)];
    }

    public boolean remove(K key) {
        if (array[index(key)] == null) {
            return false;
        } else {
            array[index(key)] = null;
            pairCounter--;
            return true;
        }
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<>() {

            private int expectedModificationCounter = modificationCounter;
            private int iteratorIndex = 0;

            @Override
            public boolean hasNext() {
                if (modificationCounter != expectedModificationCounter) {
                    throw new ConcurrentModificationException();
                }
                iteratorIndex += (int) IntStream.range(iteratorIndex, array.length)
                        .takeWhile(i -> array[i] == null)
                        .count();
                return iteratorIndex < array.length;
            }

            @Override
            public V next() {
                if (modificationCounter != expectedModificationCounter) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return array[iteratorIndex++];
            }
        };
    }

    private int index(K key) {
        return key.hashCode() % array.length;
    }

    private void enlargeIfNeeded() {
        if (array.length * 0.75 <= pairCounter) {
            @SuppressWarnings("unchecked")
            V[] longerArray = (V[]) new Object[(int) (array.length * 1.5)];
            System.arraycopy(array, 0, longerArray, 0, array.length);
            array = longerArray;
        }
    }
}
