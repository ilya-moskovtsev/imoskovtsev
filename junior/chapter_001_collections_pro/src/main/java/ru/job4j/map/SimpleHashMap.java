package ru.job4j.map;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
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
    private Node<K, V>[] array = new Node[16];
    private int size = 0;
    private int modifications = 0;

    public boolean put(K key, V value) {
        modifications++;
        enlargeIfNeeded();
        boolean success = false;
        Node<K, V> node = new Node<>();
        node.key = key;
        node.value = value;
        int index = index(key);

        if (array[index] == null) {
            array[index] = node;
            size++;
            success = true;
        }
        return success;
    }

    public V get(K key) {
        V result = null;
        if (array[index(key)] != null && array[index(key)].key.equals(key)) {
            result = array[index(key)].value;
        }
        return result;
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
                return array[index++].value;
            }
        };
    }

    private int index(K key) {
        return key.hashCode() % (array.length - 1);
    }

    private void enlargeIfNeeded() {
        if (array.length * 0.75 <= size) {
            @SuppressWarnings("unchecked")
            Node<K, V>[] longerArray = new Node[(int) (array.length * 1.5)];
            Arrays.stream(array)
                    .filter(Objects::nonNull)
                    .forEach(node -> longerArray[node.key.hashCode() % (longerArray.length - 1)] = node);
            array = longerArray;
        }
    }

    private static class Node<K, V> {
        K key;
        V value;
    }
}
