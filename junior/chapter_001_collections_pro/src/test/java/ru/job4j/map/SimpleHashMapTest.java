package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleHashMapTest {

    private SimpleHashMap<Integer, Integer> map;

    @Before
    public void setUp() {
        map = new SimpleHashMap<>();
    }

    @Test
    public void stringKey() {
        var map = new SimpleHashMap<String, Integer>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        assertThat(map.get("one"), is(1));
        assertThat(map.get("two"), is(2));
        assertThat(map.get("three"), is(3));
    }

    @Test
    public void putAndGet() {
        assertThat(map.put(1, 101), is(true));
        assertThat(map.put(1, 102), is(false));
        assertThat(map.get(1), is(101));
    }

    @Test(expected = NoSuchElementException.class)
    public void getException() {
        map.get(1);
    }


    @Test
    public void remove() {
        map.put(1, 101);
        assertThat(map.remove(1), is(true));
        assertThat(map.remove(1), is(false));
    }

    @Test
    public void iteratorHasNextTrue() {
        map.put(1, 101);
        assertThat(map.iterator().hasNext(), is(true));
    }

    @Test
    public void iteratorHasNextFalse() {
        map.put(1, 101);
        var iterator = map.iterator();
        iterator.next();
        assertThat(iterator.hasNext(), is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void iteratorHasNextException() {
        map.put(1, 101);
        var iterator = map.iterator();
        map.put(2, 202);
        @SuppressWarnings("unused")
        boolean result = iterator.hasNext();
    }

    @Test
    public void iteratorNext() {
        map.put(1, 101);
        map.put(3, 303);
        var iterator = map.iterator();
        assertThat(iterator.next(), is(101));
        assertThat(iterator.next(), is(303));
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorNextNoSuchElementException() {
        var iterator = map.iterator();
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void iteratorNextConcurrentModificationException() {
        Iterator<Integer> iterator = map.iterator();
        map.put(1, 101);
        iterator.next();
    }
}