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

    // stored messages held locally (parallel array assignment)
    private List<Map<String, String>> storedMessages = new ArrayList<>();

    private MessageManager manager;  // UI & controller connection

    public QuickChat(int messageCount, MessageManager manager) {
        this.messageCount = messageCount;
        this.manager = manager;
    }

    // Generate a random 10-digit message ID
    public String generateMessageID() {
        Random random = new Random();
        long id = 1_000_000_000L + (long)(random.nextDouble() * 9_000_000_000L);
        return String.format("%010d", id);
    }

    // Create the unique message hash
    public String generateMessageHash(String messageID, int messageNumber, String message) {
        if (message == null || message.trim().isEmpty()) {
            return "00:" + messageNumber + ":EMPTY";
        }
        
        String[] words = message.trim().split("\\s+");
        String firstWord = words.length > 0 ? words[0].toUpperCase() : "EMPTY";
        String lastWord = words.length > 0 ? words[words.length - 1].toUpperCase() : "EMPTY";
        
        // Handle single word messages
        if (words.length == 1) {
            lastWord = firstWord;
        }
        
        String firstTwoDigits = messageID.length() >= 2 ? messageID.substring(0, 2) : "00";
        return firstTwoDigits + ":" + messageNumber + ":" + firstWord + "_" + lastWord;
    }

    // Validate phone number format
    public boolean isValidRecipient(String recipient) {
        return recipient != null && recipient.matches("\\+27\\d{9}");
    }

    // MAIN sending logic triggered by UI
    public void sendFromUI(String recipient, String message) {
        // Check if message limit reached
        if (messagesSent >= messageCount) {
            manager.showAlert("Message limit reached! You have sent " + messagesSent + " out of " + messageCount + " allowed messages.");
            return;
        }

        if (!isValidRecipient(recipient)) {
            manager.showAlert("Invalid recipient number format. Must start with +27 and contain exactly 9 digits after (e.g., +27123456789).");
            return;
        }

        if (message == null || message.trim().isEmpty()) {
            manager.showAlert("Message cannot be empty.");
            return;
        }

        if (message.length() > 250) {
            manager.showAlert("Message too long. Maximum length is 250 characters. Your message has " + message.length() + " characters.");
            return;
        }

        messagesSent++;
        String messageID = generateMessageID();
        String hash = generateMessageHash(messageID, messagesSent, message);

        String[] options = {"Send Message", "Disregard Message", "Store Message"};
        int choice = manager.askChoice(
                "Message Preview:\n" +
                "To: " + recipient + "\n" +
                "Message: " + (message.length() > 50 ? message.substring(0, 47) + "..." : message) + "\n" +
                "Message ID: " + messageID + "\n" +
                "Message Hash: " + hash + "\n\n" +
                "Choose an action:",
                options
        );

        if (choice == 0) {
            // Send Message
            manager.addToSentMessages(messageID, recipient, message, hash);
            manager.addMessage(recipient, message, "Sent");
            manager.showAlert("✅ Message sent successfully!\nMessage ID: " + messageID + "\nRemaining messages: " + (messageCount - messagesSent));
        } else if (choice == 1) {
            // Disregard Message
            manager.addMessage(recipient, message, "Disregard");
            messagesSent--; // Don't count disregarded messages toward limit
            manager.showAlert("❌ Message disregarded.\nRemaining messages: " + (messageCount - messagesSent));
        } else if (choice == 2) {
            // Store Message
            storeMessage(messageID, recipient, message, hash);
            manager.addMessage(recipient, message, "Stored");
            messagesSent--; // Don't count stored messages toward limit
            manager.showAlert("💾 Message stored for later.\nStored messages: " + storedMessages.size() + "\nRemaining messages: " + (messageCount - messagesSent));
        } else {
            // User cancelled
            messagesSent--;
        }
    }

    // Store message in memory before sending
    private void storeMessage(String messageID, String recipient, String message, String hash) {
        Map<String, String> msg = new LinkedHashMap<>();
        msg.put("MessageID", messageID);
        msg.put("Recipient", recipient);
        msg.put("Message", message);
        msg.put("MessageHash", hash);
        msg.put("Timestamp", new Date().toString());
        storedMessages.add(msg);
    }

    // ======== Additional Utility Methods ========
    
    public int getRemainingMessageCount() {
        return messageCount - messagesSent;
    }
    
    public int getMessagesSent() {
        return messagesSent;
    }
    
    public int getStoredMessageCount() {
        return storedMessages.size();
    }
    
    public boolean hasMessageLimitReached() {
        return messagesSent >= messageCount;
    }
    
    public void displayStoredMessages() {
        if (storedMessages.isEmpty()) {
            manager.showAlert("No stored messages available.");
            return;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("=== STORED MESSAGES ===\n\n");
        sb.append("Total Stored: ").append(storedMessages.size()).append("\n\n");
        
        for (int i = 0; i < storedMessages.size(); i++) {
            Map<String, String> msg = storedMessages.get(i);
            sb.append("Stored Message #").append(i + 1).append("\n");
            sb.append("ID: ").append(msg.get("MessageID")).append("\n");
            sb.append("To: ").append(msg.get("Recipient")).append("\n");
            sb.append("Message: ").append(truncateMessage(msg.get("Message"))).append("\n");
            sb.append("Hash: ").append(msg.get("MessageHash")).append("\n");
            sb.append("Stored: ").append(msg.get("Timestamp")).append("\n");
            sb.append("----------------------------------\n");
        }
        
        JOptionPane.showMessageDialog(null, sb.toString());
    }
    
    public void sendStoredMessage(int index) {
        if (index < 0 || index >= storedMessages.size()) {
            manager.showAlert("Invalid stored message index.");
            return;
        }
        
        if (hasMessageLimitReached()) {
            manager.showAlert("Cannot send stored message. Message limit reached.");
            return;
        }
        
        Map<String, String> msg = storedMessages.get(index);
        manager.addToSentMessages(
            msg.get("MessageID"),
            msg.get("Recipient"), 
            msg.get("Message"),
            msg.get("MessageHash")
        );
        manager.addMessage(msg.get("Recipient"), msg.get("Message"), "Sent");
        storedMessages.remove(index);
        messagesSent++;
        
        manager.showAlert("✅ Stored message sent successfully!\nRemaining messages: " + getRemainingMessageCount());
    }
    
    public void clearStoredMessages() {
        int count = storedMessages.size();
        storedMessages.clear();
        manager.showAlert("🗑️ Cleared " + count + " stored messages.");
    }

    // ======== Getters ========
    public List<Map<String, String>> getStoredMessages() {
        return new ArrayList<>(storedMessages); // Return copy to prevent external modification
    }

    public int getMessageCount() {
        return messageCount;
    }

    // ======== Private Helper Methods ========
    private String truncateMessage(String message) {
        if (message == null) return "";
        if (message.length() <= 50) return message;
        return message.substring(0, 47) + "...";
    }
}
