package com.github.gasfgrv;

public class ThreadTest02 {
    public static void main(String... args) throws InterruptedException {
        Thread t1 = new Thread(new RunnableExemplo('A'));
        Thread t2 = new Thread(new RunnableExemplo('B'));
        t1.start();
        t1.join();
        t2.start();
    }
}
