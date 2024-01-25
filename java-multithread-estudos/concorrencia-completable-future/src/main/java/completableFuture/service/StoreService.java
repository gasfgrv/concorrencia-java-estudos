package completableFuture.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class StoreService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StoreService.class);
    private static final ExecutorService SERVICE = Executors.newCachedThreadPool();

    public static void shutdown() {
        SERVICE.shutdown();
    }

    public double getPriceSync(String storeName) {
        LOGGER.info("Getting prices sync for store: {}", storeName);
        return priceGenerator();
    }

    public CompletableFuture<Double> getPricesAsyncCompletableFuture(String storeName) {
        LOGGER.info("Getting prices sync for store: {}", storeName);
        return CompletableFuture.supplyAsync(this::priceGenerator);
    }

    public Future<Double> getPricesAsyncFuture(String storeName) {
        LOGGER.info("Getting prices sync for store: {}", storeName);
        return SERVICE.submit(this::priceGenerator);
    }

    private double priceGenerator() {
        LOGGER.info("{} generating price", Thread.currentThread().getName());
        delay();
        return ThreadLocalRandom.current().nextInt(1, 500) * 10;
    }

    private void delay() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}
