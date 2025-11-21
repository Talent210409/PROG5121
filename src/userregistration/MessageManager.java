/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userregistration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import javax.swing.*;
/**
 *
 * @author RC_Student_Lab
 */
public class MessageManager {
    private String currentSender = "Developer"; 

    private ArrayList<MessageRecord> sentMessages = new ArrayList<>();
    private ArrayList<MessageRecord> disregardedMessages = new ArrayList<>();
    private ArrayList<MessageRecord> storedMessages = new ArrayList<>();
    private ArrayList<String> messageHashes = new ArrayList<>();
    private ArrayList<String> messageIds = new ArrayList<>();

    // ======== Parallel Arrays Section ========
    private String[] arrMessageIds = new String[100];
    private String[] arrRecipients = new String[100];
    private String[] arrMessages = new String[100];
    private String[] arrHashes = new String[100];
    private int arrayCount = 0;

    // ======== Parallel Arrays Methods ========
    public void addToParallelArrays(MessageRecord msg) {
        if (arrayCount >= arrMessageIds.length) {
            JOptionPane.showMessageDialog(null, "Parallel arrays are full. Cannot add more messages.");
            return;
        }
        
        arrMessageIds[arrayCount] = msg.getMessageId();
        arrRecipients[arrayCount] = msg.getRecipient();
        arrMessages[arrayCount] = msg.getMessage();
        arrHashes[arrayCount] = msg.getMessageHash();
        arrayCount++;
    }

    public void searchParallelArrayById(String id) {
        if (id == null || id.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a valid message ID.");
            return;
        }
        
        for (int i = 0; i < arrayCount; i++) {
            if (arrMessageIds[i] != null && arrMessageIds[i].equals(id)) {
                JOptionPane.showMessageDialog(null,
                    "✅ Message Found:\n\n" +
                    "ID: " + arrMessageIds[i] + "\n" +
                    "Recipient: " + arrRecipients[i] + "\n" +
                    "Message: " + arrMessages[i] + "\n" +
                    "Hash: " + arrHashes[i]);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "❌ No message found with ID: " + id);
    }

    public void removeParallelByHash(String hash) {
        if (hash == null || hash.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a valid message hash.");
            return;
        }
        
        for (int i = 0; i < arrayCount; i++) {
            if (arrHashes[i] != null && arrHashes[i].equals(hash)) {
                // Shift elements left
                for (int j = i; j < arrayCount - 1; j++) {
                    arrMessageIds[j] = arrMessageIds[j + 1];
                    arrRecipients[j] = arrRecipients[j + 1];
                    arrMessages[j] = arrMessages[j + 1];
                    arrHashes[j] = arrHashes[j + 1];
                }
                
                // Clear last element
                arrMessageIds[arrayCount - 1] = null;
                arrRecipients[arrayCount - 1] = null;
                arrMessages[arrayCount - 1] = null;
                arrHashes[arrayCount - 1] = null;
                
                arrayCount--;
                JOptionPane.showMessageDialog(null, "✅ Message successfully removed from Parallel Arrays.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "❌ Message hash not found: " + hash);
    }

    public void displayParallelMessagesUI() {
        if (arrayCount == 0) {
            JOptionPane.showMessageDialog(null, "📭 No messages in parallel arrays to display.");
            return;
        }

        StringBuilder sb = new StringBuilder("=== Parallel Array Message Report ===\n\n");
        sb.append("Total Messages: ").append(arrayCount).append("\n\n");

        for (int i = 0; i < arrayCount; i++) {
            sb.append("Message #").append(i + 1).append("\n")
              .append("ID: ").append(arrMessageIds[i]).append("\n")
              .append("Recipient: ").append(arrRecipients[i]).append("\n")
              .append("Message: ").append(truncateMessage(arrMessages[i])).append("\n")
              .append("Hash: ").append(arrHashes[i]).append("\n")
              .append("----------------------------------\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString());
    }

    // ======== Message Display Methods ========
    public void displayStoredMessagesUI() {
        if (storedMessages.isEmpty()) {
            JOptionPane.showMessageDialog(null, "📭 No stored messages available.");
            return;
        }

        StringBuilder sb = new StringBuilder("=== STORED MESSAGES ===\n\n");
        sb.append("Total Stored: ").append(storedMessages.size()).append("\n\n");

        for (int i = 0; i < storedMessages.size(); i++) {
            MessageRecord msg = storedMessages.get(i);
            sb.append("Stored Message #").append(i + 1).append("\n")
              .append("To: ").append(msg.getRecipient()).append("\n")
              .append("Message: ").append(truncateMessage(msg.getMessage())).append("\n")
              .append("ID: ").append(msg.getMessageId()).append("\n")
              .append("----------------------------------\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public void displayAllSentMessagesUI() {
        if (sentMessages.isEmpty()) {
            JOptionPane.showMessageDialog(null, "📭 No sent messages available.");
            return;
        }

        StringBuilder sb = new StringBuilder("=== ALL SENT MESSAGES ===\n\n");
        sb.append("Total Sent: ").append(sentMessages.size()).append("\n\n");

        for (int i = 0; i < sentMessages.size(); i++) {
            MessageRecord msg = sentMessages.get(i);
            sb.append("Sent Message #").append(i + 1).append("\n")
              .append("To: ").append(msg.getRecipient()).append("\n")
              .append("Message: ").append(truncateMessage(msg.getMessage())).append("\n")
              .append("ID: ").append(msg.getMessageId()).append("\n")
              .append("----------------------------------\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString());
    }

    // ======== Core Message Management ========
    public void displayLongestSentMessage() {
        if (sentMessages.isEmpty()) {
            JOptionPane.showMessageDialog(null, "📭 No sent messages available.");
            return;
        }
        
        MessageRecord longest = sentMessages.get(0);
        for (MessageRecord msg : sentMessages) {
            if (msg.getMessage().length() > longest.getMessage().length()) {
                longest = msg;
            }
        }

        String output = """
                📌 Longest Sent Message

                Recipient: %s
                Length: %d characters
                Message ID: %s
                Message:
                "%s"
                """.formatted(
                longest.getRecipient(),
                longest.getMessage().length(),
                longest.getMessageId(),
                longest.getMessage()
        );

        JOptionPane.showMessageDialog(null, output, "Longest Sent Message", JOptionPane.INFORMATION_MESSAGE);
    }

    public void displaySentMessagesReport() {
        if (sentMessages.isEmpty()) {
            JOptionPane.showMessageDialog(null, "📭 No sent messages available to report.");
            return;
        }

        StringBuilder report = new StringBuilder("=== SENT MESSAGE REPORT ===\n\n");
        report.append("Total Messages Sent: ").append(sentMessages.size()).append("\n\n");

        int totalChars = 0;
        for (MessageRecord msg : sentMessages) {
            report.append(msg.toString()).append("\n----------------------------------\n");
            totalChars += msg.getMessage().length();
        }

        report.append("\n📊 Statistics:\n");
        report.append("Average Message Length: ").append(totalChars / sentMessages.size()).append(" characters\n");
        report.append("Total Characters Sent: ").append(totalChars);

        JOptionPane.showMessageDialog(null, report.toString());
    }

    public void sendAllStoredMessages() {
        if (storedMessages.isEmpty()) {
            JOptionPane.showMessageDialog(null, "📭 There are no messages stored to send.");
            return;
        }

        int count = storedMessages.size();
        for (MessageRecord msg : storedMessages) {
            sentMessages.add(msg);
            addToParallelArrays(msg);
        }

        storedMessages.clear();
        JOptionPane.showMessageDialog(null, 
            "✅ Successfully sent " + count + " stored messages.");
    }

    public void addMessage(String recipient, String message, String flag) {
        MessageRecord record = new MessageRecord(currentSender, recipient, message, flag);

        switch (flag) {
            case "Sent" -> {
                sentMessages.add(record);
                addToParallelArrays(record);
            }
            case "Stored" -> storedMessages.add(record);
            case "Disregard" -> disregardedMessages.add(record);
        }

        messageHashes.add(record.getMessageHash());
        messageIds.add(record.getMessageId());
    }

    // ======== Missing Method Implementations ========
    public void showAlert(String message) {
        JOptionPane.showMessageDialog(null, message, "Alert", JOptionPane.WARNING_MESSAGE);
    }

    public int askChoice(String title, String[] options) {
        if (options == null || options.length == 0) {
            return -1;
        }
        
        Object choice = JOptionPane.showInputDialog(
            null,
            title,
            "QuickChat - Choice",
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );
        
        if (choice == null) {
            return -1; // User cancelled
        }
        
        for (int i = 0; i < options.length; i++) {
            if (options[i].equals(choice)) {
                return i;
            }
        }
        return -1;
    }

    public void addToSentMessages(String messageID, String recipient, String message, String hash) {
        MessageRecord record = new MessageRecord(currentSender, recipient, message, "Sent");
        sentMessages.add(record);
        addToParallelArrays(record);
    }

    // ======== Utility Methods ========
    public void setCurrentSender(String sender) {
        if (sender != null && !sender.trim().isEmpty()) {
            this.currentSender = sender.trim();
        }
    }

    public String getCurrentSender() {
        return currentSender;
    }

    public int getStoredMessageCount() {
        return storedMessages.size();
    }

    public int getSentMessageCount() {
        return sentMessages.size();
    }

    public int getParallelArrayCount() {
        return arrayCount;
    }

    private String truncateMessage(String message) {
        if (message == null) return "";
        if (message.length() <= 50) return message;
        return message.substring(0, 47) + "...";
    }

    // ======== Data Validation ========
    public boolean isValidRecipient(String recipient) {
        return recipient != null && 
               recipient.startsWith("+27") && 
               recipient.length() >= 12 &&
               recipient.matches("^\\+27\\d{9}$");
    }

    public boolean isValidMessage(String message) {
        return message != null && 
               !message.trim().isEmpty() && 
               message.length() <= 250;
    }
}
