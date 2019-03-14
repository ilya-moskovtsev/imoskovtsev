package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CustomStackTest {

    CustomStack<Integer> stack;

    @Before
    public void setUp() {
        stack = new CustomStack<>();
        for (int i = 0; i < 3; i++) {
            stack.push(i);
        }
    }

    @Test
    public void poll() {
        for (int i = 2; i >= 0; i--) {
            assertThat(stack.poll(), is(i));
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void pollException() {
        for (int i = 0; i < 4; i++) {
            stack.poll();
        }
    }
}