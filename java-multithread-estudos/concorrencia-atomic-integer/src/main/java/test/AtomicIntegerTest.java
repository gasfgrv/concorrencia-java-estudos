package test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AtomicIntegerTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AtomicIntegerTest.class);

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Runnable r = () -> {
            for (int i = 0; i < 10000; i++) {
                counter.incremet();

            }
        };

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        LOGGER.info("Counter: {}", counter.getCount());
        LOGGER.info("AtomicInteger Counter: {}", counter.getAtomicInteger());
    }
}
