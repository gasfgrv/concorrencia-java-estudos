package sincronize;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sincronize.domain.Account;

public class ThreadAccountTest implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadAccountTest.class);
    private final Account account = new Account();

    public static void main(String... args) {
        ThreadAccountTest teste = new ThreadAccountTest();
        Thread t1 = new Thread(teste, "T1");
        Thread t2 = new Thread(teste, "T2");

        t1.start();
        t2.start();
    }

    private synchronized void withdrawal(int amount) {
        if (account.getBalance() >= amount) {
            LOGGER.info("{}: Success", Thread.currentThread().getName());
            account.withdrawal(amount);
            LOGGER.info("{}: Current value: {}", Thread.currentThread().getName(), account.getBalance());
        } else {
            LOGGER.info("{}: error", Thread.currentThread().getName());
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            withdrawal(10);
            if (account.getBalance() < 0) {
                LOGGER.info("Balance is negative");
            }
        }
    }
}
