package test;

import java.util.concurrent.atomic.AtomicInteger;

class Counter {
    private int count;
    private final AtomicInteger atomicInteger = new AtomicInteger();

    void incremet() {
        count++;
        atomicInteger.incrementAndGet();
    }

    int getCount() {
        return count;
    }

    AtomicInteger getAtomicInteger() {
        return atomicInteger;
    }
}
