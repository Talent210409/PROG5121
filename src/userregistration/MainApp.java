/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userregistration;

import javax.swing.JOptionPane;
import javax.swing.*;
/**
 *
 * @author RC_Student_Lab
 */
public class MainApp {
    public static void main(String[] args) {

        UserRegistration user = new UserRegistration();
        MessageManager manager = new MessageManager();

        String firstName = JOptionPane.showInputDialog("Enter your first name:");
        String lastName = JOptionPane.showInputDialog("Enter your last name:");
        user.setUserFullName(firstName, lastName);

        String username = JOptionPane.showInputDialog("Enter username:");
        String password = JOptionPane.showInputDialog("Enter password:");
        String cell = JOptionPane.showInputDialog("Enter SA cell phone number (+27...)");

        JOptionPane.showMessageDialog(null, user.registerUser(username, password, cell));

        String loginUsername = JOptionPane.showInputDialog("Login - Enter username:");
        String loginPassword = JOptionPane.showInputDialog("Login - Enter password:");
        JOptionPane.showMessageDialog(null, user.returnLoginStatus(loginUsername, loginPassword));

        if (user.loginUser(loginUsername, loginPassword)) {

            JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");

            int messageLimit = Integer.parseInt(JOptionPane.showInputDialog("How many messages would you like to send?"));
            QuickChat chat = new QuickChat(messageLimit, manager);

            boolean running = true;
            while (running) {

                String menu = JOptionPane.showInputDialog("""
                        ===== QuickChat Menu =====
                        1 → Send Message
                        2 → View Stored Messages
                        3 → Send All Stored Messages
                        4 → View All Sent Messages
                        5 → View Longest Sent Message
                        6 → Search Message by ID
                        7 → Delete Message by Hash
                        8 → View Parallel Array Records
                        9 → Display Sent Message Report
                        10 → Quit
                        """);

                switch (menu) {
                    case "1" -> {
                        String recipient = JOptionPane.showInputDialog("Enter recipient phone number (+27...):");
                        String message = JOptionPane.showInputDialog("Enter your message (max 250 chars):");
                        chat.sendFromUI(recipient, message);
                    }
                    case "2" -> manager.displayStoredMessagesUI();
                    case "3" -> manager.sendAllStoredMessages();
                    case "4" -> manager.displayAllSentMessagesUI();
                    case "5" -> manager.displayLongestSentMessage();
                    case "6" -> manager.searchParallelArrayById(JOptionPane.showInputDialog("Enter Message ID:"));
                    case "7" -> manager.removeParallelByHash(JOptionPane.showInputDialog("Enter Message Hash:"));
                    case "8" -> manager.displayParallelMessagesUI();
                    case "9" -> manager.displaySentMessagesReport();
                    case "10" -> {
                        JOptionPane.showMessageDialog(null, "Goodbye!");
                        running = false;
                    }
                    default -> JOptionPane.showMessageDialog(null, "Invalid option, please try again.");
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Username or password incorrect, please try again.");
        }
    }
}
