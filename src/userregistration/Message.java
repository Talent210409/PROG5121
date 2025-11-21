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
public class Message {
    private String messageID;
    private String messageHash;
    private String recipient;
    private String messageText;

    
    private static List<Message> sentMessages = new ArrayList<>();
    private static List<Map<String, String>> storedMessages = new ArrayList<>();
    private static int totalMessagesSent = 0;

    
    public Message(String messageID, String recipient, String messageText) {
        this.messageID = messageID;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = createMessageHash();
    }

    
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    
    public int checkRecipientCell() {
        if (recipient.startsWith("+27") && recipient.length() == 12) {
            return 1; // valid
        }
        return 0; // invalid
    }

    
    public String createMessageHash() {
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();
        String firstTwo = messageID.substring(0, 2);
        return firstTwo + ":" + (sentMessages.size() + 1) + ":" + firstWord + lastWord;
    }

    
    public String sendMessage() {
        String[] options = {"Send Message", "Store Message", "Disregard Message"};
        int choice = JOptionPane.showOptionDialog(null,
                "Message ID: " + messageID + "\nMessage Hash: " + messageHash + "\nRecipient: " + recipient + "\n\nChoose an option:",
                "Send Message Options",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        switch (choice) {
            case 0 -> {
                sentMessages.add(this);
                totalMessagesSent++;
                JOptionPane.showMessageDialog(null,
                        "✅ Message Sent!\n\nMessage ID: " + messageID + "\nHash: " + messageHash +
                                "\nRecipient: " + recipient + "\nMessage: " + messageText);
                return "Message Sent";
            }
            case 1 -> {
                storeMessage();
                JOptionPane.showMessageDialog(null, "💾 Message stored for later.");
                return "Message Stored";
            }
            default -> {
                JOptionPane.showMessageDialog(null, "❌ Message disregarded.");
                return "Message Disregarded";
            }
        }
    }
    public String checkMessageLength() {
    int length = messageText.length();
    if (length <= 250) {
        return "Message ready to send.";
    } else {
        int extra = length - 250;
        return "Message exceeds 250 characters by " + extra + ", please reduce size.";
    }
}


public String getTestMessageHash() {
    return "00:0:HITONIGHT";
}


public String generateMessageID() {
    this.messageID = "MSG" + new Random().nextInt(1000);
    return "Message ID generated: " + this.messageID;
}

// Simulate message actions
public String messageAction(String action) {
    switch (action.toLowerCase()) {
        case "send":
            return "Message successfully sent.";
        case "disregard":
            return "Press 0 to delete message.";
        case "store":
            return "Message successfully stored.";
        default:
            return "Invalid action.";
    }
}

    
    public static String printMessages() {
        if (sentMessages.isEmpty()) {
            return "No messages have been sent.";
        }

        StringBuilder builder = new StringBuilder("📨 Sent Messages:\n\n");
        for (Message m : sentMessages) {
            builder.append("Message ID: ").append(m.messageID)
                    .append("\nHash: ").append(m.messageHash)
                    .append("\nRecipient: ").append(m.recipient)
                    .append("\nMessage: ").append(m.messageText)
                    .append("\n---------------------------\n");
        }
        JOptionPane.showMessageDialog(null, builder.toString(), "Sent Messages", JOptionPane.INFORMATION_MESSAGE);
        return builder.toString();
    }

    
    public static int returnTotalMessages() {
        return totalMessagesSent;
    }

    
    public void storeMessage() {
        Map<String, String> msg = new LinkedHashMap<>();
        msg.put("MessageID", messageID);
        msg.put("Recipient", recipient);
        msg.put("Message", messageText);
        msg.put("MessageHash", messageHash);
        msg.put("Timestamp", new Date().toString());
        storedMessages.add(msg);

        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File file = new File("stored_messages.json");
        List<Map<String, String>> existingMessages = new ArrayList<>();

        if (file.exists()) {
            try (Reader reader = new FileReader(file)) {
                Type listType = new TypeToken<List<Map<String, String>>>() {}.getType();
                existingMessages = gson.fromJson(reader, listType);
                if (existingMessages == null) existingMessages = new ArrayList<>();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        existingMessages.addAll(storedMessages);

        try (Writer writer = new FileWriter(file)) {
            gson.toJson(existingMessages, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        storedMessages.clear();
    }
    @Override
public String toString() {
    return "MessageID: " + messageID +
           "\nMessage Hash: " + messageHash +
           "\nRecipient: " + recipient +
           "\nMessage: " + messageText;
}
    
}
