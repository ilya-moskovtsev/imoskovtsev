package ru.job4j.list;

public class SimpleSinglyLinkedList<E> {

    private int size;
    private Node<E> first;

    /**
     * Adds data to the beginning of the list.
     *
     * @param data to be added
     */
    public void add(E data) {
        Node<E> newLink = new Node<>(data);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * Deletes the first element of the list.
     *
     * @return deleted element
     */
    public E deleteFirst() {
        E result = this.first.data;
        this.first = this.first.next;
        return result;
    }

    /**
     * Gets element by index.
     *
     * @param index of element
     * @return element
     */
    public E get(int index) {
        Node<E> result = this.first;
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

    /**
     * Stores data, points to neighbour.
     *
     * @param <E>
     */
    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }
}