package completableFuture;

import completableFuture.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest02 {
    private static final Logger LOGGER = LoggerFactory.getLogger(CompletableFutureTest02.class);

    public static void main(String[] args) {
        StoreService storeService = new StoreService();
        searchPricesAyncCompletableFuture(storeService);
    }

    private static void searchPricesAyncCompletableFuture(StoreService storeService) {
        long start = System.currentTimeMillis();
        List<String> stores = List.of("Store 1", "Store 2", "Store 3", "Store 4");

        List<CompletableFuture<Double>> completableFutures = stores.stream()
                .map(storeService::getPricesAsyncCompletableFuture)
                .toList();

        List<Double> prices = completableFutures.stream()
                .map(CompletableFuture::join)
                .toList();

        LOGGER.info(String.valueOf(prices));

        long end = System.currentTimeMillis();
        LOGGER.info("Time passed to searchPricesSync: {}", end - start);
    }
}
