package executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

class RandomNumberCallable implements Callable<String> {
    private static final Logger LOGGER = LoggerFactory.getLogger(RandomNumberCallable.class);

    @Override
    public String call() throws Exception {
        int num = ThreadLocalRandom.current().nextInt(1, 11);
        for (int i = 0; i < num; i++) {
            LOGGER.info("{} executing callable task...", Thread.currentThread().getName());
        }
        return "%s finished and the random number is %d".formatted(Thread.currentThread().getName(), num);
    }
}
