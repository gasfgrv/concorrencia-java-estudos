package sincronize;

import sincronize.domain.Account;

public class ThreadAccountTest implements Runnable {
    private Account account = new Account();

    public static void main(String... args) {
        ThreadAccountTest teste = new ThreadAccountTest();
        new Thread(teste, "T1").start();
        new Thread(teste, "T2").start();

    }

    private void withdrawal(int amount) {
        if (account.getBalance() >= amount) {
            System.out.println(Thread.currentThread().getName() + "Success");
            account.withdrawal(amount);
            System.out.println(Thread.currentThread().getName() + "Current value: " + account.getBalance());
        } else {
            System.out.println(Thread.currentThread().getName() + "error");
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            withdrawal(10);
            if (account.getBalance() < 0) {
                System.out.println("ERROR!!!!!!!!!!!!!!!");
            }
        }
    }
}
