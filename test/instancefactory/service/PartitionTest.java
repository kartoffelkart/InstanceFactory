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

    public PartitionTest() {
    }

    @BeforeClass
    public static void setUpClass() {

        Tools tool = new Tools();

        Partition instance = new Partition();

        MyInteger myInt5nr1 = new MyInteger(5);
        MyInteger myInt46 = new MyInteger(46);
        MyInteger myInt21nr1 = new MyInteger(21);
        MyInteger myInt29 = new MyInteger(29);
        MyInteger myInt21nr2 = new MyInteger(21);
        MyInteger myInt47 = new MyInteger(47);

        MyInteger myInt19 = new MyInteger(19);
        MyInteger myInt40 = new MyInteger(40);
        MyInteger myInt5nr2 = new MyInteger(5);
        MyInteger myInt5nr3 = new MyInteger(5);
        MyInteger myInt33 = new MyInteger(33);
        MyInteger myInt7 = new MyInteger(7);

        MyArrayList<MyInteger> nextList1 = new MyArrayList<>();
        nextList1.addAll(Arrays.asList(myInt19, myInt5nr1));
        instance.adjacencyList.add(nextList1);

        MyArrayList<MyInteger> nextList2 = new MyArrayList<>();
        nextList2.addAll(Arrays.asList(myInt40, myInt5nr1, myInt46));
        instance.adjacencyList.add(nextList2);

        MyArrayList<MyInteger> nextList3 = new MyArrayList<>();
        nextList3.addAll(Arrays.asList(myInt5nr2, myInt5nr1, myInt21nr1));
        instance.adjacencyList.add(nextList3);

        MyArrayList<MyInteger> nextList4 = new MyArrayList<>();
        nextList4.addAll(Arrays.asList(myInt5nr3, myInt5nr1, myInt29));
        instance.adjacencyList.add(nextList4);

        MyArrayList<MyInteger> nextList5 = new MyArrayList<>();
        nextList5.addAll(Arrays.asList(myInt33, myInt5nr1, myInt29, myInt21nr2));
        instance.adjacencyList.add(nextList5);

        MyArrayList<MyInteger> nextList6 = new MyArrayList<>();
        nextList6.addAll(Arrays.asList(myInt7, myInt5nr1, myInt29, myInt21nr2, myInt47));
        instance.adjacencyList.add(nextList6);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testGetPositionOfSellInAdjazenslist() {

        System.out.println("getPositionOfSellInAdjazenslist");
        MyInteger sell = null;
        Partition instance = new Partition();
        Integer expResult = null;
        Integer result = instance.getPositionOfSellInAdjazenslist(sell);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPositionOfSellInSortedSells method, of class Partition.
     */
    @Test
    public void testGetPositionOfSellInSortedSells() {
        System.out.println("getPositionOfSellInSortedSells");
        MyInteger sell = null;
        Partition instance = new Partition();
        Integer expResult = null;
        Integer result = instance.getPositionOfSellInSortedSells(sell);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
