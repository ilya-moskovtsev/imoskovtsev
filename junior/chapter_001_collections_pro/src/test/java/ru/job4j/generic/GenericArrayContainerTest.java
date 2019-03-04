package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GenericArrayContainerTest {

    private GenericArrayContainer<Integer> container;

    @Before
    public void setUp() {
        container = new GenericArrayContainer<>(3);
        container.add(1);
        container.add(2);
        container.add(3);
    }

    @Test
    public void add() {
        assertThat(container.get(0), is(1));
        assertThat(container.get(1), is(2));
        assertThat(container.get(2), is(3));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void addException() {
        container.add(4);
    }

    @Test
    public void set() {
        container.set(1, 4);
        assertThat(container.get(0), is(1));
        assertThat(container.get(1), is(4));
        assertThat(container.get(2), is(3));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void setException() {
        container.set(3, 4);
    }

    @Test
    public void remove() {
        container.remove(1);
        assertThat(container.get(0), is(1));
        assertThat(container.get(1), is(3));
    }

    @Test
    public void iteratorHasNextTrue() {
        Iterator<Integer> iterator = container.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
    }

    @Test
    public void iteratorHasNextFalse() {
        Iterator<Integer> iterator = container.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        assertThat(iterator.hasNext(), is(false));
    }

    @Test
    public void iteratorNext() {
        Iterator<Integer> iterator = container.iterator();
        assertThat(iterator.next(), is(1));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.next(), is(3));
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorNextException() {
        Iterator<Integer> iterator = container.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
    }
}