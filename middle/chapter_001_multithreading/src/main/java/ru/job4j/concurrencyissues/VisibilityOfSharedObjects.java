package ru.job4j.concurrencyissues;

/**
 * Updates to the shared object made by one thread may not be visible to other threads.
 * <p>
 * http://tutorials.jenkov.com/java-concurrency/java-memory-model.html#visibility-of-shared-objects
 */
public class VisibilityOfSharedObjects {
    private static class SharedObject {
        /**
         * By declaring the variable volatile all writes to the variable will be written back to main memory immediately.
         * Also, all reads of the variable will be read directly from main memory.
         * <p>
         * http://tutorials.jenkov.com/java-concurrency/volatile.html
         */
        private String name;

        public SharedObject(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        SharedObject sharedObject = new SharedObject("name1");

        new Thread(() -> sharedObject.setName("name2")).start();

        new Thread(() -> System.out.println(sharedObject.getName())).start();
    }
}
