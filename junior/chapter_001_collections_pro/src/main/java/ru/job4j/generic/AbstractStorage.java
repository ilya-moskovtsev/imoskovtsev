package ru.job4j.generic;

import java.util.NoSuchElementException;

public abstract class AbstractStorage<T extends Base> implements Storage<T> {
    GenericArrayContainer<T> container;

    public AbstractStorage(int capacity) {
        this.container = new GenericArrayContainer<>(capacity);
    }

    public void add(T model) {
        container.add(model);
    }

    public boolean replace(String id, T model) {
        var iterator = container.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            if (id.equals(iterator.next().getId())) {
                container.set(index, model);
                return true;
            }
            index++;
        }
        return false;
    }

    public boolean delete(String id) {
        var iterator = container.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            if (id.equals(iterator.next().getId())) {
                container.remove(index);
                return true;
            }
            index++;
        }
        return false;
    }

    public T findById(String id) {
        var iterator = container.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            if (id.equals(iterator.next().getId())) {
                return container.get(index);
            }
            index++;
        }
        throw new NoSuchElementException();
    }
}
