package test.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.domain.Members;

public class EmailDeliveryService implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailDeliveryService.class);
    private final Members members;

    public EmailDeliveryService(Members members) {
        this.members = members;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        LOGGER.info("{} starting to delivery emails...", threadName);
        while (members.isOpen() || members.pendingEmails() > 0) {
            try {
                String email = members.retrieveEmail();
                if (email == null) continue;
                LOGGER.info("{} sending email to {}", threadName, email);
                Thread.sleep(2000);
                LOGGER.info("{} successfully sent email to {}", threadName, email);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
        LOGGER.info("All emails are sent successfully");
    }
}
