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
    
    public ToolsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of makeBasicPartitions method, of class Tools.
     */
    @Test
    public void testMakeBasicPartitions() {
        System.out.println("makeBasicPartitions");
        int min = 0;
        int max = 0;
        int size = 0;
        Tools instance = new Tools();
        MyArrayList<Partition> expResult = null;
        MyArrayList<Partition> result = instance.makeBasicPartitions(min, max, size);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buildInstanceOnBasicPartitions method, of class Tools.
     */
    @Test
    public void testBuildInstanceOnBasicPartitions() {
        System.out.println("buildInstanceOnBasicPartitions");
        MyArrayList<Partition> partitions = null;
        int unionProbability = 0;
        int leftJoinProbability = 0;
        int rightJoinProbability = 0;
        Tools instance = new Tools();
        instance.buildInstanceOnBasicPartitions(partitions, unionProbability, leftJoinProbability, rightJoinProbability);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRandomIntMyArrayList method, of class Tools.
     */
    @Test
    public void testGetRandomIntMyArrayList() {
        System.out.println("getRandomIntMyArrayList");
        int min = 0;
        int max = 0;
        int size = 0;
        Tools instance = new Tools();
        MyArrayList<Integer> expResult = null;
        MyArrayList<Integer> result = instance.getRandomIntMyArrayList(min, max, size);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRandomMyIntArray method, of class Tools.
     */
    @Test
    public void testGetRandomMyIntArray() {
        System.out.println("getRandomMyIntArray");
        int min = 0;
        int max = 0;
        int size = 0;
        Tools instance = new Tools();
        MyArrayList<MyInteger> expResult = null;
        MyArrayList<MyInteger> result = instance.getRandomMyIntArray(min, max, size);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDeterministicMyIntArray method, of class Tools.
     */
    @Test
    public void testGetDeterministicMyIntArray() {
        System.out.println("getDeterministicMyIntArray");
        Tools instance = new Tools();
        MyArrayList<MyInteger> expResult = null;
        MyArrayList<MyInteger> result = instance.getDeterministicMyIntArray();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getChoice method, of class Tools.
     */
    @Test
    public void testGetChoice() {
        System.out.println("getChoice");
        int unionProbability = 0;
        int leftJoinProbability = 0;
        int rightJoinProbability = 0;
        Tools instance = new Tools();
        String expResult = "";
        String result = instance.getChoice(unionProbability, leftJoinProbability, rightJoinProbability);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCoin method, of class Tools.
     */
    @Test
    public void testGetCoin() {
        System.out.println("getCoin");
        int boughtProbability = 0;
        int saleProbability = 0;
        Tools instance = new Tools();
        String expResult = "";
        String result = instance.getCoin(boughtProbability, saleProbability);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRandomPartitionDueToProbality method, of class Tools.
     */
    @Test
    public void testGetRandomPartitionDueToProbality() {
        System.out.println("getRandomPartitionDueToProbality");
        MyArrayList<Partition> partitions = null;
        Tools instance = new Tools();
        Partition expResult = null;
        Partition result = instance.getRandomPartitionDueToProbality(partitions);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of makePartition method, of class Tools.
     */
    @Test
    public void testMakePartition() {
        System.out.println("makePartition");
        Partition p1 = null;
        Partition p2 = null;
        int unionProbability = 0;
        int leftJoinProbability = 0;
        int rightJoinProbability = 0;
        Tools instance = new Tools();
        Partition expResult = null;
        Partition result = instance.makePartition(p1, p2, unionProbability, leftJoinProbability, rightJoinProbability);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of makeSortedSellsJoin method, of class Tools.
     */
    @Test
    public void testMakeSortedSellsJoin() {
        System.out.println("makeSortedSellsJoin");
        MyArrayList<MyInteger> s1 = null;
        MyArrayList<MyInteger> s2 = null;
        Tools instance = new Tools();
        MyArrayList<MyInteger> expResult = null;
        MyArrayList<MyInteger> result = instance.makeSortedSellsJoin(s1, s2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of makeBudgetJoin method, of class Tools.
     */
    @Test
    public void testMakeBudgetJoin() {
        System.out.println("makeBudgetJoin");
        Partition b1 = null;
        Partition b2 = null;
        Tools instance = new Tools();
        Integer expResult = null;
        Integer result = instance.makeBudgetJoin(b1, b2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of makeBalanceJoin method, of class Tools.
     */
    @Test
    public void testMakeBalanceJoin() {
        System.out.println("makeBalanceJoin");
        Partition b1 = null;
        Partition b2 = null;
        Tools instance = new Tools();
        Integer expResult = null;
        Integer result = instance.makeBalanceJoin(b1, b2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fillPositiveSets method, of class Tools.
     */
    @Test
    public void testFillPositiveSets() {
        System.out.println("fillPositiveSets");
        Partition p = null;
        Tools instance = new Tools();
        instance.fillPositiveSets(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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

    /**
     * Test of permute method, of class Tools.
     */
    @Test
    public void testPermute() {
        System.out.println("permute");
        MyArrayList<MyInteger> arr = null;
        int k = 0;
        MyArrayList<MyArrayList<MyInteger>> returnList = null;
        Tools.permute(arr, k, returnList);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of makeMyArrayListJoin method, of class Tools.
     */
    @Test
    public void testMakeMyArrayListJoin() {
        System.out.println("makeMyArrayListJoin");
        Partition p1 = null;
        Partition p2 = null;
        Tools instance = new Tools();
        MyArrayList<MyArrayList<MyInteger>> expResult = null;
        MyArrayList<MyArrayList<MyInteger>> result = instance.makeMyArrayListJoin(p1, p2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of makePartitionUnion method, of class Tools.
     */
    @Test
    public void testMakePartitionUnion() {
        System.out.println("makePartitionUnion");
        Partition p1 = null;
        Partition p2 = null;
        Tools instance = new Tools();
        Partition expResult = null;
        Partition result = instance.makePartitionUnion(p1, p2);
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

    /**
     * Test of makePartitionJoin method, of class Tools.
     */
    @Test
    public void testMakePartitionJoin() {
        System.out.println("makePartitionJoin");
        Partition p1 = null;
        Partition p2 = null;
        Tools instance = new Tools();
        Partition expResult = null;
        Partition result = instance.makePartitionJoin(p1, p2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSum method, of class Tools.
     */
    @Test
    public void testGetSum() {
        System.out.println("getSum");
        MyArrayList<MyInteger> newB = null;
        Tools instance = new Tools();
        Integer expResult = null;
        Integer result = instance.getSum(newB);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of shift method, of class Tools.
     */
    @Test
    public void testShift() {
        System.out.println("shift");
        MyArrayList<Eintrag> list = null;
        Integer shiftValue = null;
        String id = "";
        Tools instance = new Tools();
        MyArrayList<Eintrag> expResult = null;
        MyArrayList<Eintrag> result = instance.shift(list, shiftValue, id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of outStatistikN method, of class Tools.
     */
    @Test
    public void testOutStatistikN() {
        System.out.println("outStatistikN");
        String dateiname = "";
        int min = 0;
        int max = 0;
        Integer maxKnotenAnzahl = null;
        Integer schrittlaenge = null;
        Integer pool = null;
        Tools instance = new Tools();
        instance.outStatistikN(dateiname, min, max, maxKnotenAnzahl, schrittlaenge, pool);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of out method, of class Tools.
     */
    @Test
    public void testOut() {
        System.out.println("out");
        Graph currentGraph = null;
        String dateiname = "";
        Tools instance = new Tools();
        instance.out(currentGraph, dateiname);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGraphHeuristik method, of class Tools.
     */
    @Test
    public void testGetGraphHeuristik() {
        System.out.println("getGraphHeuristik");
        Graph currentGraph = null;
        String update = "";
        Tools instance = new Tools();
        Graph expResult = null;
        Graph result = instance.getGraphHeuristik(currentGraph, update);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of swap method, of class Tools.
     */
    @Test
    public void testSwap() {
        System.out.println("swap");
        int i = 0;
        int j = 0;
        MyArrayList<MyInteger> ordering = null;
        Tools instance = new Tools();
        MyArrayList<MyInteger> expResult = null;
        MyArrayList<MyInteger> result = instance.swap(i, j, ordering);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buildInstance method, of class Tools.
     */
    @Test
    public void testBuildInstance() {
        System.out.println("buildInstance");
        int min = 0;
        int max = 0;
        int size = 0;
        Tools instance = new Tools();
        Partition expResult = null;
        Partition result = instance.buildInstance(min, max, size);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changeOrder method, of class Tools.
     */
    @Test
    public void testChangeOrder() {
        System.out.println("changeOrder");
        int i = 0;
        int j = 0;
        MyArrayList<MyInteger> ordering = null;
        Tools instance = new Tools();
        MyArrayList<MyInteger> expResult = null;
        MyArrayList<MyInteger> result = instance.changeOrder(i, j, ordering);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOrderingOfBasicPartitions method, of class Tools.
     */
    @Test
    public void testGetOrderingOfBasicPartitions() {
        System.out.println("getOrderingOfBasicPartitions");
        MyArrayList<Partition> basicPartitions = null;
        Tools instance = new Tools();
        MyArrayList<MyInteger> expResult = null;
        MyArrayList<MyInteger> result = instance.getOrderingOfBasicPartitions(basicPartitions);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSumOfBoughts method, of class Tools.
     */
    @Test
    public void testGetSumOfBoughts() {
        System.out.println("getSumOfBoughts");
        MyArrayList<Partition> basicPartitions = null;
        Tools instance = new Tools();
        Integer expResult = null;
        Integer result = instance.getSumOfBoughts(basicPartitions);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buildIstanceMakeHeuristicsAndOut method, of class Tools.
     */
    @Test
    public void testBuildIstanceMakeHeuristicsAndOut() {
        System.out.println("buildIstanceMakeHeuristicsAndOut");
        int min = 0;
        int max = 0;
        int size = 0;
        Tools instance = new Tools();
        instance.buildIstanceMakeHeuristicsAndOut(min, max, size);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArrayAbschnitt method, of class Tools.
     */
    @Test
    public void testGetArrayAbschnitt() {
        System.out.println("getArrayAbschnitt");
        MyArrayList list = null;
        int i = 0;
        int j = 0;
        Tools instance = new Tools();
        MyArrayList expResult = null;
        MyArrayList result = instance.getArrayAbschnitt(list, i, j);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIndexOfMin method, of class Tools.
     */
    @Test
    public void testGetIndexOfMin() {
        System.out.println("getIndexOfMin");
        MyArrayList<Eintrag> list = null;
        Tools instance = new Tools();
        int expResult = 0;
        int result = instance.getIndexOfMin(list);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIndexOfMax method, of class Tools.
     */
    @Test
    public void testGetIndexOfMax() {
        System.out.println("getIndexOfMax");
        MyArrayList<Eintrag> list = null;
        Tools instance = new Tools();
        int expResult = 0;
        int result = instance.getIndexOfMax(list);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWerteBisNodeExclusive method, of class Tools.
     */
    @Test
    public void testGetWerteBisNodeExclusive() {
        System.out.println("getWerteBisNodeExclusive");
        MyArrayList<Eintrag> list = null;
        MyInteger node = null;
        Tools instance = new Tools();
        MyArrayList<Eintrag> expResult = null;
        MyArrayList<Eintrag> result = instance.getWerteBisNodeExclusive(list, node);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWerteBisNodeInclusive method, of class Tools.
     */
    @Test
    public void testGetWerteBisNodeInclusive() {
        System.out.println("getWerteBisNodeInclusive");
        MyArrayList<Eintrag> list = null;
        MyInteger node = null;
        Tools instance = new Tools();
        MyArrayList<Eintrag> expResult = null;
        MyArrayList<Eintrag> result = instance.getWerteBisNodeInclusive(list, node);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAbschnittBisNode method, of class Tools.
     */
    @Test
    public void testGetAbschnittBisNode() {
        System.out.println("getAbschnittBisNode");
        MyArrayList<MyInteger> list = null;
        MyInteger node = null;
        String id = "";
        Tools instance = new Tools();
        MyArrayList<MyInteger> expResult = null;
        MyArrayList<MyInteger> result = instance.getAbschnittBisNode(list, node, id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of positiveSetAbarbeiten method, of class Tools.
     */
    @Test
    public void testPositiveSetAbarbeiten() {
        System.out.println("positiveSetAbarbeiten");
        MyArrayList<Eintrag> currentWertePx = null;
        MyArrayList<MyInteger> newSortedSells = null;
        Partition p = null;
        MyArrayList<MyInteger> sRest = null;
        IntegerOut budget = null;
        IntegerOut balance = null;
        String id = "";
        Tools instance = new Tools();
        instance.positiveSetAbarbeiten(currentWertePx, newSortedSells, p, sRest, budget, balance, id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
