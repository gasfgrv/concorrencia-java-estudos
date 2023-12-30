package executors;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

class RandomNumberCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        int num = ThreadLocalRandom.current().nextInt(1, 11);
        for (int i = 0; i < num; i++) {
            System.out.printf("%s executing callable task...%n", Thread.currentThread().getName());
        }
        return "%s finished and the random number is %d".formatted(Thread.currentThread().getName(), num);
    }
}
