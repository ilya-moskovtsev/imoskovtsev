package ru.job4j.set;

import ru.job4j.list.DynamicArrayList;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {

    private DynamicArrayList<E> list = new DynamicArrayList<>();

    public void add(E element) {
        var iterator = list.iterator();
        boolean contains = false;
        while (iterator.hasNext()) {
            if (iterator.next().equals(element)) {
                contains = true;
                break;
            }
        }
        if (!contains) {
            list.add(element);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }
}
