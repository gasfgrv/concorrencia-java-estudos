package test.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Members implements AutoCloseable {
    private static final Logger LOGGER = LoggerFactory.getLogger(Members.class);
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
            LOGGER.info("{} add email in queue", threadName);
            emails.add(email);
            emails.notifyAll();
        }
    }

    public String retrieveEmail() throws InterruptedException {
        LOGGER.info("{} checking if there are emails", Thread.currentThread().getName());
        synchronized (emails) {
            while (emails.isEmpty()) {
                if (!open) return null;
                LOGGER.info("{} no email available, entering in waiting mode", Thread.currentThread().getName());
                emails.wait();
            }
            return emails.poll();
        }
    }

    @Override
    public void close() {
        open = false;
        synchronized (emails) {
            LOGGER.info("{} closing", Thread.currentThread().getName());
            emails.notifyAll();
        }
    }
}
