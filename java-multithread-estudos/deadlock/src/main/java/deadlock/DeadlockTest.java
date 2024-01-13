package deadlock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeadlockTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeadlockTest.class);

    public static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();

        Runnable r1 = () -> {
            synchronized (lock1) {
                LOGGER.info("Thread 1: Segurando lock 1");
                LOGGER.info("Thread 1: Esperando lock 2");
                synchronized (lock2) {
                    LOGGER.info("Thread 1: Segurando lock 2");
                }
            }
        };
        Runnable r2 = () -> {
            synchronized (lock2) {
                LOGGER.info("Thread 2: Segurando lock 2");
                LOGGER.info("Thread 2: Esperando lock 1");
                synchronized (lock1) {
                    LOGGER.info("Thread 2: Segurando lock 1");
                }
            }
        };

        new Thread(r1).start();
        new Thread(r2).start();
    }
}
