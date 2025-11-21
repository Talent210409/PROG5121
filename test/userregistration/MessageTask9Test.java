/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userregistration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author RC_Student_Lab
 */
public class MessageTask9Test {
   
    //  Message length 
    @Test
    public void testMessageLength_Success() {
        String shortMessage = "Hi Mike, can you join us for dinner tonight?";
        Message msg = new Message("MSG001", "+27718693002", shortMessage);

        String result = msg.checkMessageLength();
        assertEquals("Message ready to send.", result);
    }

    //  Message length 
    @Test
    public void testMessageLength_Failure() {
        StringBuilder longMessage = new StringBuilder();
        for (int i = 0; i < 260; i++) longMessage.append("A"); // 260 characters
        Message msg = new Message("MSG002", "+27718693002", longMessage.toString());

        String result = msg.checkMessageLength();
        assertTrue(result.contains("Message exceeds 250 characters by"));
    }

    //  Recipient number correctly formatted
    @Test
    public void testRecipientNumber_Success() {
        Message msg = new Message("MSG003", "+27718693002", "Dinner tonight?");
        int check = msg.checkRecipientCell();
        assertEquals(1, check, "Cell phone number successfully captured.");
    }

    //  Recipient number incorrectly formatted
    @Test
    public void testRecipientNumber_Failure() {
        Message msg = new Message("MSG004", "0812345678", "Dinner tonight?");
        int check = msg.checkRecipientCell();
        assertEquals(0, check, 
            "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
    }

    // Message hash validation
    @Test
    public void testMessageHash_IsCorrect() {
        Message msg = new Message("MSG005", "+27718693002", "Hi Mike, can you join us for dinner tonight");
        String hash = msg.getTestMessageHash();
        assertEquals("00:0:HITONIGHT", hash);
    }

    //  Message ID auto-generation
    @Test
    public void testMessageID_Generated() {
        Message msg = new Message("", "+27718693002", "Test message");
        String result = msg.generateMessageID();
        assertTrue(result.startsWith("Message ID generated:"));
    }

    //  Send message
    @Test
    public void testMessageAction_Send() {
        Message msg = new Message("MSG007", "+27718693002", "Dinner?");
        String result = msg.messageAction("Send");
        assertEquals("Message successfully sent.", result);
    }

    // Disregard message
    @Test
    public void testMessageAction_Disregard() {
        Message msg = new Message("MSG008", "+27718693002", "Dinner?");
        String result = msg.messageAction("Disregard");
        assertEquals("Press 0 to delete message.", result);
    }

    // Store message
    @Test
    public void testMessageAction_Store() {
        Message msg = new Message("MSG009", "+27718693002", "Dinner?");
        String result = msg.messageAction("Store");
        assertEquals("Message successfully stored.", result);
    }

    //  Hash loop check 
    @Test
    public void testMultipleMessageHashes() {
        String[] messages = {
            "Hi Mike, can you join us for dinner tonight",
            "Hi Keegan, did you receive the payment?",
            "Good morning, please confirm the delivery."
        };

        for (String text : messages) {
            Message msg = new Message("AUTO", "+27718693002", text);
            String hash = msg.createMessageHash();
            assertNotNull(hash, "Message hash should not be null for: " + text);
        }
    } 
}
