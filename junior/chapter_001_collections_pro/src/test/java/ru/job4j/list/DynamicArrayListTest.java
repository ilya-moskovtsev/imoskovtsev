package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DynamicArrayListTest {

    private DynamicArrayList<Integer> container;

    @Before
    public void setUp() {
        container = new DynamicArrayList<>();
        container.add(0);
        container.add(1);
        container.add(2);
    }

    @Test
    public void add() {
        assertThat(container.get(0), is(0));
        assertThat(container.get(1), is(1));
        assertThat(container.get(2), is(2));
    }

    @Test
    public void whenAddMoreThan10TimesThenNoException() {
        for (int i = 3; i < 11; i++) {
            container.add(i);
        }
        for (int i = 0; i < 11; i++) {
            assertThat(container.get(i), is(i));
        }
    }

    @Test
    public void iteratorHasNextTrue() {
        Iterator<Integer> iterator = container.iterator();
        for (int i = 0; i < 4; i++) {
            assertThat(iterator.hasNext(), is(true));
        }
    }

    @Test
    public void iteratorHasNextFalse() {
        Iterator<Integer> iterator = container.iterator();
        for (int i = 0; i < 3; i++) {
            iterator.next();
        }
        assertThat(iterator.hasNext(), is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddAndIteratorHasNextThanConcurrentModificationException() {
        Iterator<Integer> iterator = container.iterator();
        container.add(0);
        assertThat(iterator.hasNext(), is(true));
    }

    @Test
    public void iteratorNext() {
        Iterator<Integer> iterator = container.iterator();
        for (int i = 0; i < 3; i++) {
            assertThat(iterator.next(), is(i));
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorNextThanNoSuchElementException() {
        Iterator<Integer> iterator = container.iterator();
        for (int i = 0; i < 4; i++) {
            iterator.next();
        }
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddAndIteratorNextThanConcurrentModificationException() {
        Iterator<Integer> iterator = container.iterator();
        container.add(0);
        iterator.next();
    }
}