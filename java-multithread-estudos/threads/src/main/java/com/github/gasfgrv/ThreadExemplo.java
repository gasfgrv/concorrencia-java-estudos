package com.github.gasfgrv;

public class ThreadExemplo extends Thread {
    private final char c;

    public ThreadExemplo(char c) {
        this.c = c;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
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
            System.out.println();
        }
    }
}
