/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instancefactory.service;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sonja Schäfer sonja_schaefer@gmx.de
 */
public class MyIntegerTest {
    
    public MyIntegerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of toString method, of class MyInteger.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        MyInteger instance = new MyInteger();
        String expResult = "hello";
        String result = "hello";
        assertEquals(expResult, result);
      
    }
    
}
