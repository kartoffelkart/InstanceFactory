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
        Partition p = new Partition();
        Partition p1 = instA;
        Partition p2 = instB;

        tool.makeSortedSellsUnionAndBudgetAndBalance(p, p1, p2);

//        MyArrayList<MyInteger> expResult = instance.sortedSells;
//        MyArrayList<MyInteger> result = p.sortedSells;
        
//        Integer expResult = instance.getBudget();
//        Integer result = p.getBudget();
        
        Integer expResult = instance.getBalance();
        Integer result = p.getBalance();

        assertEquals(expResult, result);

    }

//   
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
