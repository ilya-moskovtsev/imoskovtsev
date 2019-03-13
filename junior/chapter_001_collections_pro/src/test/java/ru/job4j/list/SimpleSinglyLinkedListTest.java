package ru.job4j.list;

import org.junit.Test;
import org.junit.Before;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleSinglyLinkedListTest {

    private SimpleSinglyLinkedList<Integer> list;

    @Before
    public void setUp() {
        list = new SimpleSinglyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void get() {
        assertThat(list.get(1), is(2));
    }

    @Test
    public void getSize() {
        assertThat(list.getSize(), is(3));
    }

    @Test
    public void deleteFirst() {
        assertThat(list.get(0), is(3));
        assertThat(list.deleteFirst(), is(3));
        assertThat(list.get(0), is(2));
    }
}