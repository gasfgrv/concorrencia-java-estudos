package completableFuture;

import completableFuture.model.Quote;
import completableFuture.service.StoreServiceWithDiscount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest04 {
    private static final Logger LOGGER = LoggerFactory.getLogger(CompletableFutureTest04.class);

    public static void main(String[] args) {
        StoreServiceWithDiscount storeServiceWithDiscount = new StoreServiceWithDiscount();
//        searchPricesWithDiscount(storeServiceWithDiscount);
        searchPricesWithDiscountAsync(storeServiceWithDiscount);
    }

    private static void searchPricesWithDiscount(StoreServiceWithDiscount service) {
        long start = System.currentTimeMillis();
        List<String> stores = List.of("Store 1", "Store 2", "Store 3", "Store 4");
        stores.stream()
                .map(service::getPriceSync)
                .map(Quote::newQuote)
                .map(service::applyDiscount)
                .forEach(LOGGER::info);
        long end = System.currentTimeMillis();
        LOGGER.info("Time passed to searchPricesSync: {}", end - start);
    }

    private static void searchPricesWithDiscountAsync(StoreServiceWithDiscount service) {
        long start = System.currentTimeMillis();
        List<String> stores = List.of("Store 1", "Store 2", "Store 3", "Store 4");

        List<CompletableFuture<String>> completableFutures = stores.stream()
                .map(s -> CompletableFuture.supplyAsync(() -> service.getPriceSync(s)))
                .map(cf -> cf.thenApply(Quote::newQuote))
                .map(cf -> cf.thenCompose(quote -> CompletableFuture.supplyAsync(() -> service.applyDiscount(quote))))
                .toList();

        completableFutures.stream()
                .map(CompletableFuture::join)
                .forEach(LOGGER::info);

        long end = System.currentTimeMillis();
        LOGGER.info("Time passed to searchPricesSync: {}", end - start);
    }
}
