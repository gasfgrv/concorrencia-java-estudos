package executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class FutureTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(FutureTest.class);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Double> dollarRequest = executorService.submit(() -> {
            TimeUnit.SECONDS.sleep(2);
            return 4.35D;
        });
        LOGGER.info(doSomething());
        Double dollarResponse = null;
        try {
            dollarResponse = dollarRequest.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            executorService.shutdown();
        }
        LOGGER.info("Dollar: {}", dollarResponse);
    }

    private static String doSomething() {
        LOGGER.info(Thread.currentThread().getName());
        long sum = 0;
        for (int i = 0; i < 1_000_000; i++) {
            sum += i;
        }
        return String.valueOf(sum);
    }
}
