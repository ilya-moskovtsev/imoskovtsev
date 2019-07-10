package ru.job4j.concurrencyissues;

import java.util.ArrayList;

/**
 * If two or more threads share an object, and more than one thread updates variables in that shared object, race conditions may occur.
 * <p>
 * http://tutorials.jenkov.com/java-concurrency/java-memory-model.html#race-conditions
 */
public class RaceConditions {
    private static class SharedObject {
        private int number;

        public SharedObject(int number) {
            this.number = number;
        }

        public int getNumber() {
            return number;
        }

        /**
         * Synchronized blocks synchronized on the same object can only have one thread executing inside them at the same time.
         * All other threads attempting to enter the synchronized block are blocked until the thread inside the synchronized block exits the block.
         * <p>
         * http://tutorials.jenkov.com/java-concurrency/synchronized.html
         *
         * @param number to add
         */
        public void addNumber(int number) {
            this.number += number;
        }
    }

    public static void main(String[] args) {
        SharedObject sharedObject = new SharedObject(1);

        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(() -> sharedObject.addNumber(1)));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        System.out.println(sharedObject.getNumber());
    }
}
