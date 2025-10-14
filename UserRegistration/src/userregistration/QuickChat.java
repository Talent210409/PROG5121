/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userregistration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javax.swing.*;
import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
/**
 *
 * @author RC_Student_Lab
 */
public class QuickChat {
    private int messageCount;
    private int messagesSent = 0;
    private List<Map<String, String>> storedMessages = new ArrayList<>();

    public QuickChat(int messageCount) {
        this.messageCount = messageCount;
    }

    // Generate a random 10-digit message ID
    public String generateMessageID() {
        long id = (long) (Math.random() * 1_000_000_0000L);
        return String.format("%010d", id);
    }

    // Create the unique message hash
    public String generateMessageHash(String messageID, int messageNumber, String message) {
        String[] words = message.trim().split("\\s+");
        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();
        String firstTwoDigits = messageID.substring(0, 2);
        return firstTwoDigits + ":" + messageNumber + ":" + firstWord + lastWord;
    }

    // Validate phone number format
    public boolean isValidRecipient(String recipient) {
        return recipient.matches("\\+27\\d{9}");
    }

    // Main sending logic
    public void sendMessages() {
        for (int i = 0; i < messageCount; i++) {
            String recipient = JOptionPane.showInputDialog("Enter recipient phone number (+27...):");

            if (!isValidRecipient(recipient)) {
                JOptionPane.showMessageDialog(null, "Invalid recipient number format. Must start with +27 and have 9 digits after.");
                i--;
                continue;
            }

            String message = JOptionPane.showInputDialog("Enter your message (max 250 chars):");

            if (message.length() > 250) {
                JOptionPane.showMessageDialog(null, "Please enter a message of less than 250 characters.");
                i--;
                continue;
            }

            messagesSent++;
            String messageID = generateMessageID();
            String hash = generateMessageHash(messageID, messagesSent, message);

            String[] options = {"Send Message", "Disregard Message", "Store Message to send later"};
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Message ID: " + messageID + "\nMessage Hash: " + hash,
                    "Message Options",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (choice == 0) {
                JOptionPane.showMessageDialog(null, "Message sent!");
            } else if (choice == 1) {
                JOptionPane.showMessageDialog(null, "Message disregarded.");
            } else {
                storeMessage(messageID, recipient, message, hash);
                JOptionPane.showMessageDialog(null, "Message stored for later.");
            }
        }

        // Save stored messages to JSON file at the end
        saveMessagesToJSON();
    }

    // Store message in memory before writing to file
    private void storeMessage(String messageID, String recipient, String message, String hash) {
        Map<String, String> msg = new LinkedHashMap<>();
        msg.put("MessageID", messageID);
        msg.put("Recipient", recipient);
        msg.put("Message", message);
        msg.put("MessageHash", hash);
        msg.put("Timestamp", new Date().toString());
        storedMessages.add(msg);
    }

    // Save all stored messages to JSON file
    private void saveMessagesToJSON() {
        if (storedMessages.isEmpty()) return;

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File file = new File("stored_messages.json");

        List<Map<String, String>> existingMessages = new ArrayList<>();

        // Read existing messages if file exists
        if (file.exists()) {
            try (Reader reader = new FileReader(file)) {
                Type listType = new TypeToken<List<Map<String, String>>>(){}.getType();
                existingMessages = gson.fromJson(reader, listType);
                if (existingMessages == null) existingMessages = new ArrayList<>();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Add new stored messages
        existingMessages.addAll(storedMessages);

        
        try (Writer writer = new FileWriter(file)) {
            gson.toJson(existingMessages, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JOptionPane.showMessageDialog(null, "All stored messages saved to stored_messages.json");
        storedMessages.clear();
    }
}
