package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CustomLinkedListTest {

    private CustomLinkedList<Integer> list;

    @Before
    public void setUp() {
        list = new CustomLinkedList<>();
        for (int i = 0; i < 3; i++) {
            list.add(i);
        }
    }

    @Test
    public void get() {
        for (int i = 0; i < 3; i++) {
            assertThat(list.get(i), is(i));
        }
    }

    @Test
    public void getSize() {
        assertThat(list.getSize(), is(3));
        list.add(0);
        assertThat(list.getSize(), is(4));
    }

    @Test
    public void iteratorHasNextTrue() {
        var iterator = list.iterator();
        for (int i = 0; i < 4; i++) {
            assertThat(iterator.hasNext(), is(true));
        }
    }

    @Test
    public void iteratorHasNextFalse() {
        var iterator = list.iterator();
        for (int i = 0; i < 3; i++) {
            iterator.next();
        }
        assertThat(iterator.hasNext(), is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddAndIteratorHasNextThanConcurrentModificationException() {
        var iterator = list.iterator();
        list.add(0);
        assertThat(iterator.hasNext(), is(true));
    }

    @Test
    public void iteratorNext() {
        var iterator = list.iterator();
        for (int i = 0; i < 3; i++) {
            assertThat(iterator.next(), is(i));
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorNextThanNoSuchElementException() {
        var iterator = list.iterator();
        for (int i = 0; i < 4; i++) {
            iterator.next();
        }
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddAndIteratorNextThanConcurrentModificationException() {
        var iterator = list.iterator();
        list.add(0);
        iterator.next();
    }
}