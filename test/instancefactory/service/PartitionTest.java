/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instancefactory.service;

import java.util.Arrays;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sonja Schäfer sonja_schaefer@gmx.de
 */
public class PartitionTest {

    private Partition instance;

    public PartitionTest() {
        Tools tool = new Tools();
        instance = tool.getBspPartition();
    }

    @BeforeClass

    public static void setUpClass() {

//        Tools tool = new Tools();
//
//        Partition instance = new Partition();
//        instance = tool.getBspPartition();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testGetPositionOfSellInAdjazenslist() {

        System.out.println("getPositionOfSellInAdjazenslist");
        MyInteger sell = (instance.adjacencyList.get(5)).get(0);

        Integer expResult = 5;
        Integer result = instance.getPositionOfSellInAdjazenslist(sell);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPositionOfSellInSortedSells method, of class Partition.
     */
    @Test
    public void testGetPositionOfSellInSortedSells() {
        System.out.println("getPositionOfSellInSortedSells");
        MyInteger sell = (instance.adjacencyList.get(5)).get(0);

        Integer expResult = 5;
        Integer result = instance.getPositionOfSellInSortedSells(sell);
        assertEquals(expResult, result);
    }

    /**
     * Test of getBoughtsOfSell method, of class Partition.
     */
    @Test
    public void testGetBoughtsOfSell() {
        System.out.println("getBoughtsOfSell");
        MyInteger sell = null;
        Partition instance = new Partition();
        MyArrayList<MyInteger> expResult = null;
        MyArrayList<MyInteger> result = instance.getBoughtsOfSell(sell);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setValueOfBalanceBoughtsBudgetOfSet method, of class Partition.
     */
    @Test
    public void testSetValueOfBalanceBoughtsBudgetOfSet() {
        System.out.println("setValueOfBalanceBoughtsBudgetOfSet");
        int index = 0;
        Partition instance = new Partition();
        instance.setValueOfBalanceBoughtsBudgetOfSet(index);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBalanceBoughtsBudgetOfSetUpToIndex method, of class Partition.
     */
    @Test
    public void testGetBalanceBoughtsBudgetOfSetUpToIndex() {
        System.out.println("getBalanceBoughtsBudgetOfSetUpToIndex");
        Partition instance = new Partition();
        MyArrayList<BalanceBoughtsBudget> expResult = null;
        MyArrayList<BalanceBoughtsBudget> result = instance.getBalanceBoughtsBudgetOfSetUpToIndex();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBalanceBoughtsBudgetOfSetUpToIndex method, of class Partition.
     */
    @Test
    public void testSetBalanceBoughtsBudgetOfSetUpToIndex() {
        System.out.println("setBalanceBoughtsBudgetOfSetUpToIndex");
        MyArrayList<BalanceBoughtsBudget> balanceBoughtsBudgetOfSetUpToIndex = null;
        Partition instance = new Partition();
        instance.setBalanceBoughtsBudgetOfSetUpToIndex(balanceBoughtsBudgetOfSetUpToIndex);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of orderingFitsBudget method, of class Partition.
     */
    @Test
    public void testOrderingFitsBudget() {
        System.out.println("orderingFitsBudget");
        Partition instance = new Partition();
        boolean expResult = false;
        boolean result = instance.orderingFitsBudget();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isBestOrdering method, of class Partition.
     */
    @Test
    public void testIsBestOrdering() {
        System.out.println("isBestOrdering");
        Partition instance = new Partition();
        boolean expResult = false;
        boolean result = instance.isBestOrdering();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWerte method, of class Partition.
     */
    @Test
    public void testGetWerte() {
        System.out.println("getWerte");
        Partition instance = new Partition();
        MyArrayList<Eintrag> expResult = null;
        MyArrayList<Eintrag> result = instance.getWerte();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSumBoughts method, of class Partition.
     */
    @Test
    public void testGetSumBoughts() {
        System.out.println("getSumBoughts");
        Partition instance = new Partition();
        MyArrayList<Integer> expResult = null;
        MyArrayList<Integer> result = instance.getSumBoughts();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
