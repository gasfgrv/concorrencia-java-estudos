package executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

class Printer implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(Printer.class);
    private final int num;

    public Printer(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        LOGGER.info("{} inicio: {}", Thread.currentThread().getName(), num);

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        LOGGER.info("{} finalizou", Thread.currentThread().getName());
    }
}
