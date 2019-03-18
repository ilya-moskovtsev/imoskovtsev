package ru.job4j.list;

import java.util.NoSuchElementException;

/**
 * Dynamic container.
 * The use of standard JDK collections (ArrayList, LinkedList, etc.) is prohibited in this task.
 *
 * @param <E>
 */
public class CustomStack<E> {

    private final SimpleSinglyLinkedList<E> collection = new SimpleSinglyLinkedList<>();

    /**
     * Adds element to the beginning of the collection.
     *
     * @param element to be added
     */
    public void push(E element) {
        collection.add(element);
    }

    /**
     * Removes and returns an element from the beginning of the collection.
     *
     * @return element
     */
    public E poll() throws NoSuchElementException {
        if (collection.getSize() == 0) {
            throw new NoSuchElementException();
        }
        return collection.deleteFirst();
    }
}