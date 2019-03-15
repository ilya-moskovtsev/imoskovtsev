package ru.job4j.list;

/**
 * The use of standard JDK collections (ArrayList, LinkedList, etc.) is prohibited in this task.
 *
 * @param <E>
 */
public class DetectLinkedListLoop<E> {

    public boolean hasLoop(Node<E> first) {
        var slowPointer = first;
        var fastPointer = first;
        while (slowPointer != null && fastPointer != null && fastPointer.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
            if (slowPointer == fastPointer) {
                return true;
            }
        }
        return false;
    }

    public Node<E> createNode(E element) {
        return new Node<>(element);
    }

    /**
     * Stores data, points to neighbour.
     *
     * @param <E>
     */
    public static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }
}