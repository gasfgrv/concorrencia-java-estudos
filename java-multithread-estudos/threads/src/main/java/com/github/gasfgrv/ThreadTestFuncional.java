package com.github.gasfgrv;

public class ThreadTestFuncional {
    public static void main(String... args) {
       Runnable exemplo = () -> System.out.println(Thread.currentThread().getName());
       new Thread(exemplo).start();
    }
}
