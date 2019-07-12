package ru.job4j.synccounter;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class SyncCounter {
    @GuardedBy("this")
    private int value;

    public void increment() {
        synchronized (this) {
            this.value++;
        }
    }

    public int getValue() {
        synchronized (this) {
            return this.value;
        }
    }
}
