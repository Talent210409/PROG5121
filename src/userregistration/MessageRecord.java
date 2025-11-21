/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userregistration;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
/**
 *
 * @author RC_Student_Lab
 */
public class MessageRecord {
    private String sender;
    private String recipient;
    private String message;
    private String flag; // Sent, Stored, Disregarded
    private String messageId;
    private String messageHash;

    public MessageRecord(String sender, String recipient, String message, String flag) {
        this.sender = sender;
        this.recipient = recipient;
        this.message = message;
        this.flag = flag;
        this.messageId = UUID.randomUUID().toString();
        this.messageHash = generateHash(message);
    }

    private String generateHash(String message) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(message.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating hash", e);
        }
    }

    public String getSender() { return sender; }
    public String getRecipient() { return recipient; }
    public String getMessage() { return message; }
    public String getFlag() { return flag; }
    public String getMessageId() { return messageId; }
    public String getMessageHash() { return messageHash; }

    @Override
    public String toString() {
        return "Sender: " + sender +
               ", Recipient: " + recipient +
               ", Message: " + message +
               ", Flag: " + flag +
               ", ID: " + messageId +
               ", Hash: " + messageHash;
    }

    AbstractStringBuilder get(String messageID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static class AbstractStringBuilder {

        public AbstractStringBuilder() {
        }

        boolean length() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
}
