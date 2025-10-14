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

        // Ask for full name
        String firstName = JOptionPane.showInputDialog("Enter your first name:");
        String lastName = JOptionPane.showInputDialog("Enter your last name:");
        user.setUserFullName(firstName, lastName);

        //  Ask for registration details
        String username = JOptionPane.showInputDialog("Enter username:");
        String password = JOptionPane.showInputDialog("Enter password:");
        String cell = JOptionPane.showInputDialog("Enter SA cell phone number (+27...):");

        String regMsg = user.registerUser(username, password, cell);
        JOptionPane.showMessageDialog(null, regMsg);

        //  Login attempt
        String loginUsername = JOptionPane.showInputDialog("Login - Enter username:");
        String loginPassword = JOptionPane.showInputDialog("Login - Enter password:");
        String loginMsg = user.returnLoginStatus(loginUsername, loginPassword);

        JOptionPane.showMessageDialog(null, loginMsg);
         if (user.loginUser(loginUsername, loginPassword)) {
            JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");

            int messageLimit = Integer.parseInt(JOptionPane.showInputDialog("How many messages would you like to send?"));
            QuickChat chat = new QuickChat(messageLimit);

            boolean running = true;
            while (running) {
                String menu = JOptionPane.showInputDialog("""
                        Choose an option:
                        1 → Send Messages
                        2 → Show Recently Sent Messages
                        3 → Quit
                        """);

                switch (menu) {
                    case "1" -> chat.sendMessages();
                    case "2" -> JOptionPane.showMessageDialog(null, "Coming Soon.");
                    case "3" -> {
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
