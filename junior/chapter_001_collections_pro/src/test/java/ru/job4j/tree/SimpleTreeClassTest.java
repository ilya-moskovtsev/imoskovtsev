package ru.job4j.tree;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleTreeClassTest {
    SimpleTree<Integer> tree;

    @Before
    public void setUp() {
        tree = new SimpleTreeClass<>(1);
    }

    @Test
    public void addTrue() {
        assertThat(tree.add(1, 2), is(true));
        assertThat(tree.add(2, 3), is(true));
    }

    @Test
    public void whenAddToNonExistingParentThanFalse() {
        assertThat(tree.add(2, 3), is(false));
    }

    @Test
    public void whenAddToParentThatHasSameChildThanFalse() {
        assertThat(tree.add(1, 1), is(true));
        assertThat("adding same child", tree.add(1, 1), is(false));
    }

    @Test
    public void when6ElFindLastThen6() {
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void iteratorHasNextTrue() {
        var iterator = tree.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
    }

    @Test
    public void iteratorHasNextFalse() {
        var iterator = tree.iterator();
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test
    public void iteratorNext() {
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(3, 6);
        tree.add(3, 7);

        var iterator = tree.iterator();
        assertThat(iterator.next(), is(1));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.next(), is(4));
        assertThat(iterator.next(), is(5));
        assertThat(iterator.next(), is(6));
        assertThat(iterator.next(), is(7));
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorNextException() {
        var iterator = tree.iterator();
        iterator.next();
        iterator.next();
    }

    @Test
    public void isBinaryTrue() {
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(3, 6);
        tree.add(3, 7);

        assertThat(tree.isBinary(), is(true));
    }

    @Test
    public void isBinaryFalse() {
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(3, 6);
        tree.add(3, 7);
        tree.add(3, 8);

        assertThat(tree.isBinary(), is(false));
    }
}