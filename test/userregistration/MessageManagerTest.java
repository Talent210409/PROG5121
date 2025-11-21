/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package userregistration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author RC_Student_Lab
 */
public class MessageManagerTest {
    
    public MessageManagerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addToParallelArrays method, of class MessageManager.
     */
    @Test
    public void testAddToParallelArrays() {
        System.out.println("addToParallelArrays");
        MessageRecord msg = null;
        MessageManager instance = new MessageManager();
        instance.addToParallelArrays(msg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchParallelArrayById method, of class MessageManager.
     */
    @Test
    public void testSearchParallelArrayById() {
        System.out.println("searchParallelArrayById");
        String id = "";
        MessageManager instance = new MessageManager();
        instance.searchParallelArrayById(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeParallelByHash method, of class MessageManager.
     */
    @Test
    public void testRemoveParallelByHash() {
        System.out.println("removeParallelByHash");
        String hash = "";
        MessageManager instance = new MessageManager();
        instance.removeParallelByHash(hash);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayParallelMessagesUI method, of class MessageManager.
     */
    @Test
    public void testDisplayParallelMessagesUI() {
        System.out.println("displayParallelMessagesUI");
        MessageManager instance = new MessageManager();
        instance.displayParallelMessagesUI();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayStoredMessagesUI method, of class MessageManager.
     */
    @Test
    public void testDisplayStoredMessagesUI() {
        System.out.println("displayStoredMessagesUI");
        MessageManager instance = new MessageManager();
        instance.displayStoredMessagesUI();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayAllSentMessagesUI method, of class MessageManager.
     */
    @Test
    public void testDisplayAllSentMessagesUI() {
        System.out.println("displayAllSentMessagesUI");
        MessageManager instance = new MessageManager();
        instance.displayAllSentMessagesUI();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayLongestSentMessage method, of class MessageManager.
     */
    @Test
    public void testDisplayLongestSentMessage() {
        System.out.println("displayLongestSentMessage");
        MessageManager instance = new MessageManager();
        instance.displayLongestSentMessage();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displaySentMessagesReport method, of class MessageManager.
     */
    @Test
    public void testDisplaySentMessagesReport() {
        System.out.println("displaySentMessagesReport");
        MessageManager instance = new MessageManager();
        instance.displaySentMessagesReport();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sendAllStoredMessages method, of class MessageManager.
     */
    @Test
    public void testSendAllStoredMessages() {
        System.out.println("sendAllStoredMessages");
        MessageManager instance = new MessageManager();
        instance.sendAllStoredMessages();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addMessage method, of class MessageManager.
     */
    @Test
    public void testAddMessage() {
        System.out.println("addMessage");
        String recipient = "";
        String message = "";
        String flag = "";
        MessageManager instance = new MessageManager();
        instance.addMessage(recipient, message, flag);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of showAlert method, of class MessageManager.
     */
    @Test
    public void testShowAlert() {
        System.out.println("showAlert");
        String message = "";
        MessageManager instance = new MessageManager();
        instance.showAlert(message);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of askChoice method, of class MessageManager.
     */
    @Test
    public void testAskChoice() {
        System.out.println("askChoice");
        String title = "";
        String[] options = null;
        MessageManager instance = new MessageManager();
        int expResult = 0;
        int result = instance.askChoice(title, options);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addToSentMessages method, of class MessageManager.
     */
    @Test
    public void testAddToSentMessages() {
        System.out.println("addToSentMessages");
        String messageID = "";
        String recipient = "";
        String message = "";
        String hash = "";
        MessageManager instance = new MessageManager();
        instance.addToSentMessages(messageID, recipient, message, hash);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCurrentSender method, of class MessageManager.
     */
    @Test
    public void testSetCurrentSender() {
        System.out.println("setCurrentSender");
        String sender = "";
        MessageManager instance = new MessageManager();
        instance.setCurrentSender(sender);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentSender method, of class MessageManager.
     */
    @Test
    public void testGetCurrentSender() {
        System.out.println("getCurrentSender");
        MessageManager instance = new MessageManager();
        String expResult = "";
        String result = instance.getCurrentSender();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStoredMessageCount method, of class MessageManager.
     */
    @Test
    public void testGetStoredMessageCount() {
        System.out.println("getStoredMessageCount");
        MessageManager instance = new MessageManager();
        int expResult = 0;
        int result = instance.getStoredMessageCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSentMessageCount method, of class MessageManager.
     */
    @Test
    public void testGetSentMessageCount() {
        System.out.println("getSentMessageCount");
        MessageManager instance = new MessageManager();
        int expResult = 0;
        int result = instance.getSentMessageCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getParallelArrayCount method, of class MessageManager.
     */
    @Test
    public void testGetParallelArrayCount() {
        System.out.println("getParallelArrayCount");
        MessageManager instance = new MessageManager();
        int expResult = 0;
        int result = instance.getParallelArrayCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidRecipient method, of class MessageManager.
     */
    @Test
    public void testIsValidRecipient() {
        System.out.println("isValidRecipient");
        String recipient = "";
        MessageManager instance = new MessageManager();
        boolean expResult = false;
        boolean result = instance.isValidRecipient(recipient);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidMessage method, of class MessageManager.
     */
    @Test
    public void testIsValidMessage() {
        System.out.println("isValidMessage");
        String message = "";
        MessageManager instance = new MessageManager();
        boolean expResult = false;
        boolean result = instance.isValidMessage(message);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
