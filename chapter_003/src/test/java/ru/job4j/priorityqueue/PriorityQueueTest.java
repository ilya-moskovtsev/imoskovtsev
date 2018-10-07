package ru.job4j.priorityqueue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class PriorityQueueTest {

    @Parameters()
    public static Collection<Object[][]> data() {
        return Arrays.asList(new Object[][][]{
                {{"urgent", 1}, {"urgent", 1}, {"urgent", 1}, {"urgent", "urgent", "urgent"}},
                {{"urgent", 1}, {"urgent", 1}, {"middle", 3}, {"urgent", "urgent", "middle"}},
                {{"urgent", 1}, {"urgent", 1}, {"low", 5}, {"urgent", "urgent", "low"}},

                {{"urgent", 1}, {"middle", 3}, {"urgent", 1}, {"urgent", "urgent", "middle"}},
                {{"urgent", 1}, {"middle", 3}, {"middle", 3}, {"urgent", "middle", "middle"}},
                {{"urgent", 1}, {"middle", 3}, {"low", 5}, {"urgent", "middle", "low"}},

                {{"urgent", 1}, {"low", 5}, {"urgent", 1}, {"urgent", "urgent", "low"}},
                {{"urgent", 1}, {"low", 5}, {"middle", 3}, {"urgent", "middle", "low"}},
                {{"urgent", 1}, {"low", 5}, {"low", 5}, {"urgent", "low", "low"}},

                {{"middle", 3}, {"urgent", 1}, {"urgent", 1}, {"urgent", "urgent", "middle"}},
                {{"middle", 3}, {"urgent", 1}, {"middle", 3}, {"urgent", "middle", "middle"}},
                {{"middle", 3}, {"urgent", 1}, {"low", 5}, {"urgent", "middle", "low"}},

                {{"middle", 3}, {"middle", 3}, {"urgent", 1}, {"urgent", "middle", "middle"}},
                {{"middle", 3}, {"middle", 3}, {"middle", 3}, {"middle", "middle", "middle"}},
                {{"middle", 3}, {"middle", 3}, {"low", 5}, {"middle", "middle", "low"}},

                {{"middle", 3}, {"low", 5}, {"urgent", 1}, {"urgent", "middle", "low"}},
                {{"middle", 3}, {"low", 5}, {"middle", 3}, {"middle", "middle", "low"}},
                {{"middle", 3}, {"low", 5}, {"low", 5}, {"middle", "low", "low"}},

                {{"low", 5}, {"urgent", 1}, {"urgent", 1}, {"urgent", "urgent", "low"}},
                {{"low", 5}, {"urgent", 1}, {"middle", 3}, {"urgent", "middle", "low"}},
                {{"low", 5}, {"urgent", 1}, {"low", 5}, {"urgent", "low", "low"}},

                {{"low", 5}, {"middle", 3}, {"urgent", 1}, {"urgent", "middle", "low"}},
                {{"low", 5}, {"middle", 3}, {"middle", 3}, {"middle", "middle", "low"}},
                {{"low", 5}, {"middle", 3}, {"low", 5}, {"middle", "low", "low"}},

                {{"low", 5}, {"low", 5}, {"urgent", 1}, {"urgent", "low", "low"}},
                {{"low", 5}, {"low", 5}, {"middle", 3}, {"middle", "low", "low"}},
                {{"low", 5}, {"low", 5}, {"low", 5}, {"low", "low", "low"}},
        });
    }

    private Task task1;
    private Task task2;
    private Task task3;
    private String[] expected;

    public PriorityQueueTest(Object[] input1, Object[] input2, Object[] input3, Object[] expected) {
        this.task1 = new Task((String) input1[0], (Integer) input1[1]);
        this.task2 = new Task((String) input2[0], (Integer) input2[1]);
        this.task3 = new Task((String) input3[0], (Integer) input3[1]);
        this.expected = Arrays.copyOf(expected, expected.length, String[].class);
    }

    @Test
    public void putViaOneLoop() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(task1);
        queue.put(task2);
        queue.put(task3);
        assertThat(queue.take().getDesc(), is(expected[0]));
        assertThat(queue.take().getDesc(), is(expected[1]));
        assertThat(queue.take().getDesc(), is(expected[2]));
    }
}