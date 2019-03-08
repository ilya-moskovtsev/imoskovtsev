package ru.job4j.generic;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public abstract class AbstractStorage<T> {
    GenericArrayContainer<T> container;

    public AbstractStorage(int capacity) {
        this.container = new GenericArrayContainer<>(capacity);
    }

    public void add(T model) {
        container.add(model);
    }

    public boolean replace(String id, T model) {
        var index = new AtomicInteger();
        boolean isPresent = isPresent(id, index);
        if (isPresent) {
            container.set(index.get(), model);
        }
        return isPresent;
    }

    public boolean delete(String id) {
        var index = new AtomicInteger();
        boolean isPresent = isPresent(id, index);
        if (isPresent) {
            container.remove(index.get());
        }
        return isPresent;
    }

    public T findById(String id) {
        return getStream().filter(element -> id.equals(((Base) element).getId())).findFirst().get();
    }

    protected Stream<T> getStream() {
        Iterator<T> iterator = container.iterator();
        Iterable<T> iterable = () -> iterator;
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    protected boolean isPresent(String id, AtomicInteger index) {
        return getStream()
                .anyMatch(element -> {
                    boolean result;
                    if (id.equals(((Base) element).getId())) {
                        result = true;
                    } else {
                        index.incrementAndGet();
                        result = false;
                    }
                    return result;
                });
    }
}
