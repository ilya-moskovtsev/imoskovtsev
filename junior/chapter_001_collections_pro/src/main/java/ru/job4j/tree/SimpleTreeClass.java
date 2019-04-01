package ru.job4j.tree;

import java.util.*;

public class SimpleTreeClass<E extends Comparable<E>> implements SimpleTree<E> {
    Node<E> root;

    public SimpleTreeClass(E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Optional<Node<E>> optional = findBy(parent);
        if (optional.isPresent()) {
            Node<E> parentNode = optional.get();
            List<Node<E>> leaves = parentNode.leaves();
            Node<E> childNode = new Node<>(child);
            if (leaves.stream().noneMatch(node -> node.eqValue(child))) {
                parentNode.add(childNode);
                result = true;
            }
        }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rs1 = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rs1 = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rs1;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            Queue<Node<E>> queue = new LinkedList<>();
            {
                queue.offer(root);
            }

            @Override
            public boolean hasNext() {
                return !queue.isEmpty();
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                var node = queue.poll();
                if (!Objects.requireNonNull(node).leaves().isEmpty()) {
                    for (var child : node.leaves()) {
                        queue.offer(child);
                    }
                }
                return node.getValue();
            }
        };
    }
}
