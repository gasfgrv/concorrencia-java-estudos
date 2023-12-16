package test;

import test.domain.Members;
import test.service.EmailDeliveryService;

import javax.swing.*;

public class EmailDeliveryTest {
    public static void main(String... args) {
        Members members = new Members();
        Thread thread = new Thread(new EmailDeliveryService(members), "Teste");
        Thread thread1 = new Thread(new EmailDeliveryService(members), "Teste2");

        thread.start();
        thread1.start();

        while (true) {
            String email = JOptionPane.showInputDialog("Insert your email");
            if (email == null || email.isEmpty()) {
                members.close();
                break;
            }
            members.addMemberEmail(email);
        }
    }
}
