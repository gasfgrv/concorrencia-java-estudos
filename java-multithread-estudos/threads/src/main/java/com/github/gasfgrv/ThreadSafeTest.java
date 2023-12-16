package com.github.gasfgrv;

public class ThreadSafeTest {
    public static void main(String... args) {
        ThreadSafeNames threadSafeNames = new ThreadSafeNames();
        threadSafeNames.add("teste");
        Runnable r = threadSafeNames::removeFist;
        new Thread(r).start();
        new Thread(r).start();
    }
}
