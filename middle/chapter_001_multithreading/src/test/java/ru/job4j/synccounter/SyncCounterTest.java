package ru.job4j.synccounter;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

class SyncCounterTest {
    private static class ThreadCounter extends Thread {
        private final SyncCounter counter;

        private ThreadCounter(SyncCounter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            this.counter.increment();
        }
    }

    @Test
    void whenTwoThreadsIncrementOneCounterWithInitialZeroValueThenCounterValueIs2() throws InterruptedException {
        var counter = new SyncCounter();
        var threadA = new ThreadCounter(counter);
        var threadB = new ThreadCounter(counter);

        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();

        assertThat(counter.getValue(), is(2));
    }
}