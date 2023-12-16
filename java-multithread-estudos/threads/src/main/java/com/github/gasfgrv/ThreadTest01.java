package com.github.gasfgrv;

public class ThreadTest01 {
    public static void main(String... args) {
        Thread t1 = new Thread(new RunnableExemplo('A'));
        Thread t2 = new Thread(new RunnableExemplo('B'));
        Thread t3 = new Thread(new RunnableExemplo('C'));
        Thread t4 = new Thread(new RunnableExemplo('D'));
        Thread t5 = new Thread(new RunnableExemplo('E'));

        t4.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.NORM_PRIORITY);
        t1.setPriority(Thread.MIN_PRIORITY);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
