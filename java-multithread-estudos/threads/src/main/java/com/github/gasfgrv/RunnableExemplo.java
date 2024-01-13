package com.github.gasfgrv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunnableExemplo implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(RunnableExemplo.class);
    private final char c;

    public RunnableExemplo(char c) {
        this.c = c;
    }

    @Override
    public void run() {
        LOGGER.info(Thread.currentThread().getName());
        for (int i = 0; i < 500; i++) {
            LOGGER.info(String.valueOf(c));
            if (i % 100 == 0) {
                LOGGER.info("");
            }
        }
    }
}
