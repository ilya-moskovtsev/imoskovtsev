package ru.job4j.tree;

import java.util.Optional;

public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {

    /**
     * Finds parent element in a tree and adds a child element in the parent element.
     *
     * @param parent element
     * @param child  element
     * @return success
     */
    boolean add(E parent, E child);

    Optional<Node<E>> findBy(E value);
}
