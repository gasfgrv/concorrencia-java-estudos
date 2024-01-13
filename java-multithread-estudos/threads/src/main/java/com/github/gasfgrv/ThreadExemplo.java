package com.github.gasfgrv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadExemplo extends Thread {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadExemplo.class);
    private final char c;

    public ThreadExemplo(char c) {
        this.c = c;
    }

    @Override
    public void run() {
        LOGGER.info(Thread.currentThread().getName());
        for (int i = 0; i < 500; i++) {
            System.out.print(c);
            quebraLinha(i);
            porThreadParaDormir();
        }
    }

    private static void porThreadParaDormir() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    private static void quebraLinha(int i) {
        if (i % 100 == 0) {
            LOGGER.info("");
        }
    }
}
