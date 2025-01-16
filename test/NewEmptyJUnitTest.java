/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tool.TestClass;

/**
 *
 * @author sandeepk
 */
public class NewEmptyJUnitTest {

    TestClass testClass;

    public NewEmptyJUnitTest() {
    }

//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
    @Before
    public void setUp() {
        testClass = new TestClass();
    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //

    @Test
    public void hello() {
        testClass.print_msg("Hello");
        assertEquals("Test result ", "Hello", "Hello");
    }
}
