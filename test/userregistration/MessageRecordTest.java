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
public class MessageRecordTest {
    
    public MessageRecordTest() {
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
     * Test of getSender method, of class MessageRecord.
     */
    @Test
    public void testGetSender() {
        System.out.println("getSender");
        MessageRecord instance = null;
        String expResult = "";
        String result = instance.getSender();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRecipient method, of class MessageRecord.
     */
    @Test
    public void testGetRecipient() {
        System.out.println("getRecipient");
        MessageRecord instance = null;
        String expResult = "";
        String result = instance.getRecipient();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMessage method, of class MessageRecord.
     */
    @Test
    public void testGetMessage() {
        System.out.println("getMessage");
        MessageRecord instance = null;
        String expResult = "";
        String result = instance.getMessage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFlag method, of class MessageRecord.
     */
    @Test
    public void testGetFlag() {
        System.out.println("getFlag");
        MessageRecord instance = null;
        String expResult = "";
        String result = instance.getFlag();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMessageId method, of class MessageRecord.
     */
    @Test
    public void testGetMessageId() {
        System.out.println("getMessageId");
        MessageRecord instance = null;
        String expResult = "";
        String result = instance.getMessageId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMessageHash method, of class MessageRecord.
     */
    @Test
    public void testGetMessageHash() {
        System.out.println("getMessageHash");
        MessageRecord instance = null;
        String expResult = "";
        String result = instance.getMessageHash();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class MessageRecord.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        MessageRecord instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
