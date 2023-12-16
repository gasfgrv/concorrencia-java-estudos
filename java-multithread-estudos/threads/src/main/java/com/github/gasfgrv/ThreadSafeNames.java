package com.github.gasfgrv;

import java.util.ArrayList;
import java.util.List;

public class ThreadSafeNames {
    private final List<String> names = new ArrayList<>();

    public synchronized void add(String name) {
        names.add(name);
    }

    public synchronized void removeFist() {
        if (!names.isEmpty()) {
            System.out.println(Thread.currentThread().getName());
            System.out.println(names.remove(0));
        }
    }
}
