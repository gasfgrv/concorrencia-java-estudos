package com.github.gasfgrv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ThreadSafeNames {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadSafeNames.class);
    private final List<String> names = new ArrayList<>();

    public synchronized void add(String name) {
        names.add(name);
    }

    public synchronized void removeFist() {
        if (!names.isEmpty()) {
            LOGGER.info(Thread.currentThread().getName());
            LOGGER.info(names.remove(0));
        }
    }
}
