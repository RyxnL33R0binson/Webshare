/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatroomPackage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ryan1
 */
public class WebshareTestTest {
    
    private final InputStream DEFAULT_STD_IN = System.in;
    private final PrintStream DEFAULT_STD_OUT = System.out;
    
    public WebshareTestTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
        System.setIn(DEFAULT_STD_IN);
        System.setOut(DEFAULT_STD_OUT);
    }

    /**
     * Test of validEmailForSignup method, of class WebshareTest.
     */
    @Test
    public void testIfEmailForSignupIsInvalid() throws UnsupportedEncodingException {
        String input = "joshipusphotmail@.com";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes("UTF-8"));
        System.setIn(in);
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out, true, "UTF-8"));
        
        WebshareTest password = new WebshareTest();
        
        assertEquals(password.validEmailForSignup(input),("Invalid email for signup"));
    }
    
    @Test
    public void testIfEmailForSignupIsValid() throws UnsupportedEncodingException {
        String input = "joe_Nelson123@tt.com.com";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes("UTF-8"));
        System.setIn(in);
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out, true, "UTF-8"));
        
        WebshareTest password = new WebshareTest();
        assertEquals(password.validEmailForSignup(input),("Valid email for signup"));
    }


    /**
     * Test of validPasswordForSignup method, of class WebshareTest.
     */
    @Test
    public void testInvalidPasswordForSignup() throws UnsupportedEncodingException {
        String input = "Testpassword12";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes("UTF-8"));
        System.setIn(in);
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out, true, "UTF-8"));
        
        WebshareTest password = new WebshareTest();
        
        assertEquals(password.validPasswordForSignup(input),("Password does not match the requirements for signup"));
    }
    
    @Test
    public void testValidPasswordForSignup() throws UnsupportedEncodingException {
        String input = "Testpassword12#>";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes("UTF-8"));
        System.setIn(in);
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out, true, "UTF-8"));
        
        WebshareTest password = new WebshareTest();
        
        assertEquals(password.validPasswordForSignup(input),("Password is valid for signup"));
    }
    
    @Test
    public void testEmptyFieldForSignupForm() throws UnsupportedEncodingException {
        String input = "";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes("UTF-8"));
        System.setIn(in);
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out, true, "UTF-8"));
        
        WebshareTest password = new WebshareTest();
        
        assertEquals(password.emptyFieldForSignupForm(input),("Please fill in all signup fields"));
    }
    
    @Test
    public void testIfEmptyPasswordIsValidForSignup() throws UnsupportedEncodingException {
        String input = "   ";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes("UTF-8"));
        System.setIn(in);
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out, true, "UTF-8"));
        
        WebshareTest password = new WebshareTest();
        
        assertEquals(password.validPasswordForSignup(input),("Password does not match the requirements for signup"));
    }
    
    @Test
    public void testIfSpecialCharAndNumIsValidForSignup() throws UnsupportedEncodingException {
        String input = "331113?";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes("UTF-8"));
        System.setIn(in);
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out, true, "UTF-8"));
        
        WebshareTest password = new WebshareTest();
        
        assertEquals(password.validPasswordForSignup(input),("Password does not match the requirements for signup"));
    }
    
    @Test
    public void testIfEmptyGroupnameIsValidForJoinGroup() throws UnsupportedEncodingException {
        String input = "   ";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes("UTF-8"));
        System.setIn(in);
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out, true, "UTF-8"));
        
        WebshareTest password = new WebshareTest();
        
        assertEquals(password.validUserNameForJoinGroup(input),("Please enter a username"));
    }
    
}
