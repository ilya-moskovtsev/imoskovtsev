package ru.job4j.set;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class SimpleSetTest {

    @Test
    public void add() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        set.add(2);
        set.add(3);

        var iterator = set.iterator();
        assertThat(iterator.next(), is(1));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.next(), is(3));
    }

    @Test
    public void addNull() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(null);
        set.add(1);

        var iterator = set.iterator();
        assertNull(iterator.next());
        assertThat(iterator.next(), is(1));
    }

    @Test
    public void addNullTwice() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(null);
        set.add(null);
        set.add(1);

        var iterator = set.iterator();
        assertNull(iterator.next());
        assertThat(iterator.next(), is(1));
    }
}
