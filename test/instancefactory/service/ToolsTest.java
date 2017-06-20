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
public class ToolsTest {

    private Partition instance;
    private Partition instA;
    private Partition instB;
    private Tools tool;

    public ToolsTest() {
        tool = new Tools();

        instance = tool.getBspPartition();

        instA = tool.getBspA();
        instB = tool.getBspB();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of makeSortedSellsUnionAndBudgetAndBalance method, of class Tools.
     */
    @Test
    public void testMakeSortedSellsUnionAndBudgetAndBalance() {
        System.out.println("makeSortedSellsUnionAndBudgetAndBalance");
        Partition p = null;
        Partition p1 = null;
        Partition p2 = null;
        Tools instance = new Tools();
        instance.makeSortedSellsUnionAndBudgetAndBalance(p, p1, p2);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

//   
    /**
     * Test of makePartitionUnion method, of class Tools.
     */
    @Test
    public void testMakePartitionUnion() {
        System.out.println("makePartitionUnion");
        Partition p1 = instA;
        Partition p2 = instB;

        Partition expResult = null;
        Partition result = .makePartitionUnion(p1, p2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of makeMyArrayListUnion method, of class Tools.
     */
    @Test
    public void testMakeMyArrayListUnion() {
        System.out.println("makeMyArrayListUnion");
        MyArrayList<MyArrayList<MyInteger>> a1 = null;
        MyArrayList<MyArrayList<MyInteger>> a2 = null;
        Tools instance = new Tools();
        MyArrayList<MyArrayList<MyInteger>> expResult = null;
        MyArrayList<MyArrayList<MyInteger>> result = instance.makeMyArrayListUnion(a1, a2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
