package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CustomQueueTest {

    private CustomQueue<Integer> queue;

    @Before
    public void setUp() {
        queue = new CustomQueue<>();
        for (int i = 0; i < 3; i++) {
            queue.push(i);
        }
    }

    @Test
    public void poll() {
        for (int i = 0; i < 3; i++) {
            assertThat(queue.poll(), is(i));
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void pollException() {
        for (int i = 0; i < 4; i++) {
            assertThat(queue.poll(), is(i));
        }
    }

    @Test
    public void pollPushPoll() {
        assertThat(queue.poll(), is(0));
        queue.push(3);
        assertThat(queue.poll(), is(1));
        queue.push(4);
        assertThat(queue.poll(), is(2));
        assertThat(queue.poll(), is(3));
        assertThat(queue.poll(), is(4));
    }
}