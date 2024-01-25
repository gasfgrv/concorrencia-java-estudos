package completableFuture;

import completableFuture.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CompletableFutureTest01 {
    private static final Logger LOGGER = LoggerFactory.getLogger(CompletableFutureTest01.class);

    public static void main(String[] args) {
        StoreService storeService = new StoreService();
//        searchPricesSync(storeService);
//        searchPricesAyncFuture(storeService);
        searchPricesAyncCompletableFuture(storeService);
    }

    private static void searchPricesSync(StoreService storeService) {
        long start = System.currentTimeMillis();
        LOGGER.info(String.valueOf(storeService.getPriceSync("Store 1")));
        LOGGER.info(String.valueOf(storeService.getPriceSync("Store 2")));
        LOGGER.info(String.valueOf(storeService.getPriceSync("Store 3")));
        LOGGER.info(String.valueOf(storeService.getPriceSync("Store 4")));
        long end = System.currentTimeMillis();
        LOGGER.info("Time passed to searchPricesSync: {}", end - start);
    }

    private static void searchPricesAyncFuture(StoreService storeService) {
        long start = System.currentTimeMillis();
        Future<Double> pricesAsyncFuture1 = storeService.getPricesAsyncFuture("Store 1");
        Future<Double> pricesAsyncFuture2 = storeService.getPricesAsyncFuture("Store 2");
        Future<Double> pricesAsyncFuture3 = storeService.getPricesAsyncFuture("Store 3");
        Future<Double> pricesAsyncFuture4 = storeService.getPricesAsyncFuture("Store 4");
        try {
            LOGGER.info(String.valueOf(pricesAsyncFuture1.get()));
            LOGGER.info(String.valueOf(pricesAsyncFuture2.get()));
            LOGGER.info(String.valueOf(pricesAsyncFuture3.get()));
            LOGGER.info(String.valueOf(pricesAsyncFuture4.get()));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        LOGGER.info("Time passed to searchPricesSync: {}", end - start);
        StoreService.shutdown();
    }

    private static void searchPricesAyncCompletableFuture(StoreService storeService) {
        long start = System.currentTimeMillis();
        CompletableFuture<Double> pricesAsyncFuture1 = storeService.getPricesAsyncCompletableFuture("Store 1");
        CompletableFuture<Double> pricesAsyncFuture2 = storeService.getPricesAsyncCompletableFuture("Store 2");
        CompletableFuture<Double> pricesAsyncFuture3 = storeService.getPricesAsyncCompletableFuture("Store 3");
        CompletableFuture<Double> pricesAsyncFuture4 = storeService.getPricesAsyncCompletableFuture("Store 4");
        LOGGER.info(String.valueOf(pricesAsyncFuture1.join()));
        LOGGER.info(String.valueOf(pricesAsyncFuture2.join()));
        LOGGER.info(String.valueOf(pricesAsyncFuture3.join()));
        LOGGER.info(String.valueOf(pricesAsyncFuture4.join()));
        long end = System.currentTimeMillis();
        LOGGER.info("Time passed to searchPricesSync: {}", end - start);
    }
}
