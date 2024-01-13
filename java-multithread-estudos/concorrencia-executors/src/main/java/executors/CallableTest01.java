package executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableTest01 {
    private static final Logger LOGGER = LoggerFactory.getLogger(CallableTest01.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        RandomNumberCallable randomNumberCallable = new RandomNumberCallable();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<String> submit = executorService.submit(randomNumberCallable);
        String s = submit.get();
        LOGGER.info("Program finished: {}", s);
        executorService.shutdown();
    }
}
