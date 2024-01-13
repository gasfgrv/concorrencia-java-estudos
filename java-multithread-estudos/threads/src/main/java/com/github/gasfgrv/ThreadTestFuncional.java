package com.github.gasfgrv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadTestFuncional {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadTestFuncional.class);
    
    public static void main(String... args) {
       Runnable exemplo = () -> LOGGER.info(Thread.currentThread().getName());
       new Thread(exemplo).start();
    }
}
