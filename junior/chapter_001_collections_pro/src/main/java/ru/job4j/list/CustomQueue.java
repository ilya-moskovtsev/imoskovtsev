package ru.job4j.list;

/**
 * Dynamic container.
 * The use of standard JDK collections (ArrayList, LinkedList, etc.) is prohibited in this task.
 * Must use two stacks.
 *
 * @param <E>
 */
public class CustomQueue<E> {

    private final CustomStack<E> write = new CustomStack<>();
    private final CustomStack<E> read = new CustomStack<>();
    private int writeSize;
    private int readSize;

    /**
     * Adds element to the beginning of the collection.
     *
     * @param element to be added
     */
    public void push(E element) {
        write.push(element);
        writeSize++;
    }

    /**
     * Removes and returns an element from the end of the collection.
     *
     * @return element
     */
    public E poll() {
        E result;
        if (readSize > 0) {
            result = read.poll();
        } else {
            for (int i = 0; i < writeSize; i++) {
                read.push(write.poll());
                readSize++;
            }
            writeSize = 0;
            result = read.poll();
        }
        readSize--;
        return result;
    }
}