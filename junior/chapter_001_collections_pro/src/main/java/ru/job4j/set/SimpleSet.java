package ru.job4j.set;

import ru.job4j.list.DynamicArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<E> implements Iterable<E> {

    private final DynamicArrayList<E> list = new DynamicArrayList<>();

    public void add(E element) {
        if (!contains(element)) {
            list.add(element);
        }
    }

    public boolean contains(E element) {
        boolean contains = false;
        for (E e : list) {
            if (Objects.equals(e, element)) {
                contains = true;
                break;
            }
        }
        return contains;
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }
}
