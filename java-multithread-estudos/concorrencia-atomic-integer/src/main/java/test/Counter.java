package test;

import java.util.concurrent.atomic.AtomicInteger;

class Counter {
    private int count;
    private AtomicInteger atomicInteger = new AtomicInteger();

    void incremet() {
        count++;
        atomicInteger.incrementAndGet();
    }

    public int getCount() {
        return count;
    }

    public AtomicInteger getAtomicInteger() {
        return atomicInteger;
    }
}
