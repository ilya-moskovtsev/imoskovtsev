package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Dynamic container.
 * The use of standard JDK collections (ArrayList, LinkedList, etc.) is prohibited in this task.
 *
 * @param <E>
 */
public class CustomLinkedList<E> implements Iterable<E> {

    private int modificationCounter = 0;
    private int size;
    private Node<E> head;

    /**
     * Adds element to the end of the list.
     *
     * @param element to be added
     */
    public void add(E element) {
        modificationCounter++;
        var newNode = new Node<>(element);
        if (head == null) {
            head = newNode;
        } else {
            var lastNode = head;
            while (lastNode.next != null) {
                lastNode = lastNode.next;
            }
            lastNode.next = newNode;
            newNode.previous = lastNode;
        }
        this.size++;
    }

    /**
     * Gets element by index.
     *
     * @param index of element
     * @return element
     */
    public E get(int index) {
        var result = this.head;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    /**
     * Gets the size of the collection.
     *
     * @return size
     */
    public int getSize() {
        return this.size;
    }

    public Iterator<E> iterator() {
        return new Iterator<>() {

            private int expectedModificationCounter = modificationCounter;
            private int iteratorIndex = 0;
            private Node<E> currentNode = head;

            @Override
            public boolean hasNext() {
                if (modificationCounter != expectedModificationCounter) {
                    throw new ConcurrentModificationException();
                }
                return iteratorIndex < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (modificationCounter != expectedModificationCounter) {
                    throw new ConcurrentModificationException();
                }
                var result = currentNode.data;
                currentNode = currentNode.next;
                iteratorIndex++;
                return result;
            }
        };
    }

    /**
     * Stores data, points to neighbours.
     *
     * @param <E>
     */
    private static class Node<E> {

        E data;
        Node<E> previous;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }
}