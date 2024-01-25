package completableFuture;

import completableFuture.service.StoreServiceOld;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureTest03 {
    private static final Logger LOGGER = LoggerFactory.getLogger(CompletableFutureTest03.class);

    public static void main(String[] args) {
        StoreServiceOld storeService = new StoreServiceOld();
        searchPricesAyncCompletableFuture(storeService);
    }

    private static void searchPricesAyncCompletableFuture(StoreServiceOld storeService) {
        long start = System.currentTimeMillis();
        List<String> stores = List.of("Store 1", "Store 2", "Store 3", "Store 4");
        ExecutorService executorService = Executors.newFixedThreadPool(10, r -> {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        });

        List<CompletableFuture<Double>> completableFutures = stores.stream()
                .map(s -> CompletableFuture.supplyAsync(() -> storeService.getPriceSync(s), executorService))
                .toList();

        List<Double> prices = completableFutures.stream()
                .map(CompletableFuture::join)
                .toList();

        LOGGER.info(prices.toString());
        long end = System.currentTimeMillis();
        executorService.shutdown();
        LOGGER.info("Time passed to searchPricesSync: {}", end - start);
    }
}
