package test.domain;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Members implements AutoCloseable {
    private final Queue<String> emails = new ArrayBlockingQueue<>(10);
    private boolean open = true;

    public boolean isOpen() {
        return open;
    }

    public int pendingEmails() {
        synchronized (emails) {
            return emails.size();
        }
    }

    public void addMemberEmail(String email) {
        synchronized (emails) {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " add email in queue");
            emails.add(email);
            emails.notifyAll();
        }
    }

    public String retrieveEmail() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " checking if there are emails");
        synchronized (emails) {
            while (emails.isEmpty()) {
                if (!open) return null;
                System.out.println(Thread.currentThread().getName() + " no email available, entering in waiting mode");
                emails.wait();
            }
            return emails.poll();
        }
    }

    @Override
    public void close() {
        open = false;
        synchronized (emails) {
            System.out.println(Thread.currentThread().getName() + " closing");
        }
    }
}
