/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instancefactory.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Sonja Schäfer sonja_schaefer@gmx.de
 */
public class Tools {

    /**
     * hier wird ein Array von BasisPartitionen erstellt
     *
     * @return adjacencyList of Partition
     */
    public MyArrayList<MyInteger> randomOrdering;
//    public MyArrayList<MyInteger> s1Rest;

    public MyArrayList<Partition> makeBasicPartitions(int min, int max, int size) {
//        MyArrayList<MyInteger> randomMyIntMyArrayList = this.getDeterministicMyIntArray();
       MyArrayList<MyInteger> randomMyIntMyArrayList = this.getRandomMyIntArray(min, max, size);

        MyArrayList<Partition> partitions = new MyArrayList<>();
        MyInteger currentRandom;
        int k = 0;
        while (k < randomMyIntMyArrayList.size() - 1) { //der letzte wird eventuel abgeschnitten
            Partition partition1 = new Partition();
            currentRandom = randomMyIntMyArrayList.get(k);

            System.out.println("Aktueller Sell :" + currentRandom);
            k++;
            MyArrayList<MyInteger> l = new MyArrayList<>();
            l.add(currentRandom);

            partition1.adjacencyList.add(l);
            partition1.sortedSells.add(currentRandom);

            partition1.setBalance(currentRandom.i); // also der Sell alleine macht aber nicht sie Balance!!!!!!!!!

            partitions.add(partition1);

            currentRandom = randomMyIntMyArrayList.get(k);

            System.out.println("Aktueller Bought : " + currentRandom);
            Partition currentPartition = partitions.get(partitions.size() - 1);
            currentPartition.adjacencyList.get(0).add(currentRandom);
            currentPartition.setBalance(partitions.get(partitions.size() - 1).getBalance() - currentRandom.i);
            currentPartition.setBudget( /* partitions.get(partitions.size() - 1).getBudget()*/-currentRandom.i);;

// kann das weg?
            Graph calculatedGraphOfSortedSells = new Graph(currentPartition, currentPartition.sortedSells);

            currentPartition.setEintraege(calculatedGraphOfSortedSells.getEintraege());

            //----------------  
            k++;

            //-------------------------------------
//                toogle--;
        }
        randomOrdering = getOrderingOfBasicPartitions(partitions);
        return partitions;
    }

    /**
     *
     * Mergt zwei zufällig gewählte Partitionen aus dem ParameterArray, ersetzt
     * die erste Partition mit dem Merge und löscht die zweite
     *
     * @param partitions Die aktuellen Partitionen vor dem nächsten Merge
     * Schritt zweier daraus zufällig gewählter Partitioen
     *
     */
    public void buildInstanceOnBasicPartitions(MyArrayList<Partition> partitions, int unionProbability, int leftJoinProbability, int rightJoinProbability) {

// BUILD DETERMINISTIC INSTANCE ----------------------------------------------------
//        while ((partitions.size()) > 1) {
//            Integer indi = 0;//das muss hier rein wir betrachten immer die Partitions an Indize 0 und 1 in Partitions
//
//            Partition partition = new Partition();
//
//            Partition partitionA = partitions.get(indi);
//            indi++;
//
//            Partition partitionB = partitions.get(indi);
//-----------------------------------------------------------------------------------------
            //   BUILD RANDOM INSTANCE ----------------------------------------------------       
        while (partitions.size() > 1) {
            Partition partition = new Partition();
            Partition partitionA = new Partition();
            partitionA = this.getRandomPartitionDueToProbality(partitions);
            System.out.println("partitionA: " + partitionA.toString() + "\n");

            Partition partitionB = new Partition();
            partitionB = this.getRandomPartitionDueToProbality(partitions);
            System.out.println("partitionB: " + partitionB.toString() + "\n");

            while (partitionA == partitionB) {
                partitionB = this.getRandomPartitionDueToProbality(partitions);
                System.out.println("partitionB: " + partitionB.toString() + "\n");

            }
            //---------------------------------------------------------------------------------
            partition = this.makePartition(partitionA, partitionB, unionProbability, leftJoinProbability, rightJoinProbability);//size 0 ???
            System.out.println("MergedPartition: " + partition.toString() + "\n");
            System.out.println("Sorted SellsOfMergedPa\n"
                    + "            //--------------------------------rtition: " + partition.sortedSells.toString() + "\n");
//ASSERTION
            if (!partition.orderingFitsBudget()) {
                boolean test = partition.isBestOrdering();
            }

            //-------------
            int ind = partitions.indexOf(partitionA);
            partitions.set(ind, partition);//ind -1
            partitions.remove(partitionB);

        }
    }

    /**
     * hier wird ein zufälliger Array von Integer Objekten erzeugt
     *
     * @param min linkes Intervallende der Integer
     * @param max rechtes Intervallende der Integer
     * @param size Größe des Arrays
     * @return zufälliger Array der Länge size von Integer Objekten zwischen min
     * und max
     */
    MyArrayList<Integer> getRandomIntMyArrayList(int min, int max, int size) {
        MyArrayList<Integer> randomIntMyArrayList = new MyArrayList<>();

        int toogle = 0;
        Integer te;

        for (int it = 0; it < size; it++) {
            te = new Integer(ThreadLocalRandom.current().nextInt(min, max + 1));
            randomIntMyArrayList.add(te);

//           
        }
        System.out.println(" Random IntArray :" + randomIntMyArrayList);
        return randomIntMyArrayList;
    }

    /**
     * hier wird ein zufälliger Array von MyInteger Objekten erzeugt
     *
     * @param min linkes Intervallende der MyInteger
     * @param max rechtes Intervallende der MyInteger
     * @param size Größe des Arrays
     * @return zufälliger Array der Länge size von MyInteger Objekten zwischen
     * min und max
     */
    public MyArrayList<MyInteger> getRandomMyIntArray(int min, int max, int size) {

        MyArrayList<MyInteger> randomMyIntMyArrayList = new MyArrayList<>();

        MyArrayList<Integer> randomIntMyArrayList = getRandomIntMyArrayList(min, max, size);
//        randomIntMyArrayList.set((size / 4) * 2 + 1, 100);/*((size/4)*2+1,max);*/ //todo: ich mache hier einen lokal großen Gewinn
        int toogle = 0;
        for (int it = 0; it < randomIntMyArrayList.size(); it++) {
            if (toogle == 0) {

                MyInteger te = new MyInteger(randomIntMyArrayList.get(it));//todo: so werden die geraden, also die Sells größer
                randomMyIntMyArrayList.add(te);

                toogle++;
            } else {
                MyInteger te = new MyInteger(randomIntMyArrayList.get(it));//todo: so werden die ungeraden, also die Boughts größer
                randomMyIntMyArrayList.add(te);

                toogle--;
            }

        }
        return randomMyIntMyArrayList;
    }

    public MyArrayList<MyInteger> getDeterministicMyIntArray() {
//        int[] deterministicIntMyArrayList = {77,16,89,5,34,27,7};

        int[] deterministicIntMyArrayList = {15, 28, 60, 38, 49, 49, 16, 49, 16, 40, 19, 25};

        MyArrayList<MyInteger> deterministicMyIntMyArrayList = new MyArrayList<>();

        int it = 0;

        while (it < deterministicIntMyArrayList.length) {
            MyInteger te = new MyInteger(deterministicIntMyArrayList[it]);
            deterministicMyIntMyArrayList.add(te);
            it++;
        }

        return deterministicMyIntMyArrayList;
    }

    /**
     * hier wird nach bestimmeten Kriterien der Warscheinlichkeit union oder
     * leftJoin oder rightJoin gewählt
     *
     * @param unionProbability Warscheinlichkeit von union in Prozent
     * @param leftJoinProbability Warscheinlichkeit von leftJoin in Prozent
     * @param rightJoinProbability Warscheinlichkeit von rightJoin in Prozent
     * @return union oder leftJoin oder rightJoin
     */
    public String getChoice(int unionProbability, int leftJoinProbability, int rightJoinProbability) {

        MyArrayList<Integer> numbers = getRandomIntMyArrayList(1, 100, 1);
        if (numbers.get(0) < unionProbability) {
            return "union";
        } else {
            if (numbers.get(0) < (unionProbability + leftJoinProbability)) {
                return "leftJoin";
            } else {
                return "rightJoin";
            }
        }
    }

    /**
     * hier wird nach bestimmeten Kriterien der Warscheinlichkeit bought oder
     * sell gewählt
     *
     * @param boughtProbability Warscheinlichkeit von Bought in Prozent
     * @param saleProbability Warscheinlichkeit von Sell in Prozent
     * @return Bought oder Sell
     */
    public String getCoin(int boughtProbability, int saleProbability) {

        MyArrayList<Integer> numbers = getRandomIntMyArrayList(1, 100, 1);
        if (numbers.get(0) < boughtProbability) {
            return "bought";
        } else {
            return "sell";
        }
    }

    /**
     * @see buildInstanceOnBasicPartitions
     * @param partitions Die aktuellen Partitionen aus denen jetzt eine nach
     * bestimmten Kriterien der Warscheinlichkeit ausgewählt wird
     * @return 'random' Partition, die nach bestimmten Kriterien der
     * Warscheinlichkeit zufällige ausgewählt wurde
     */
    public Partition getRandomPartitionDueToProbality(MyArrayList<Partition> partitions) {
        Partition partition = new Partition();
        int sumProb = 0;
        for (int y = 0; (y < partitions.size()); y++) {
            sumProb = sumProb + partitions.get(y).probability;
        }
        Integer sum = 0;

        for (int y = 0; (y < partitions.size()); y++) {

            MyArrayList<Integer> numbers = getRandomIntMyArrayList(1, sumProb, 1);//Wieso geht kein einfacher MyInteger
            Integer number = numbers.get(0);
            System.out.println(number.toString() + "<");
            sum = sum + partitions.get(y).probability;
            System.out.println(sum.toString());

            if (number < sum + 1) {
                partition = partitions.get(y);
                break;
            }
        }

        return partition;
    }
    Integer toogle = 0;

    /**
     * wählt nach einer bestimmten Warscheinlichkeitsverteilung rihtJoin,
     * leftJoin oder union und mergt die Paritionen
     *
     * @param p1 Zufällige Partition die jetzt mit einer anderen gemergt wird
     * @param p2 Zufällige Partition die jetzt mit einer anderen gemergt wird
     * @return Die Partition, die aus dem Merge der Eingabe Partitionen
     * entstanden ist
     */
    public Partition makePartition(Partition p1, Partition p2, int unionProbability, int leftJoinProbability, int rightJoinProbability) {
        Partition partition = new Partition();

// // //        partition.probability=p1.probability+p2.probability;
        // BUILD DETERMINISTIC INSTANCE ----------------------------------------------------
//        String choice = new String("");
//        Integer toogle = 0;
//        if (toogle.equals(0)) {
//            choice = "rightJoin";
//            toogle++;
//        } else {
//            if (toogle.equals(1)) {
//                choice = "union";
//                toogle++;
//            } else {
//                if (toogle.equals(2)) {
//                    choice = "union";
//                    toogle = toogle - 2;
//                }
//            }
//        }
        //-------------------------------------------------------------------------------
        //   BUILD RANDOM INSTANCE ----------------------------------------------------       
        String choice = this.getChoice(unionProbability, leftJoinProbability, rightJoinProbability);

//---------------------------------------------------------------------------------------------
        System.out.println(choice);
        if (choice.equals("union")) {
            partition = makePartitionUnion(p1, p2);

        } else {
            if (choice.equals("rightJoin")) {
                partition = makePartitionJoin(p1, p2);

            } else {
                if (choice.equals("leftJoin")) {
                    partition = makePartitionJoin(p2, p1);

                } else {
                    System.out.println("error");

                }
            }
        }

        return partition;
    }

    /**
     * hier werden die SortedSells s1 und s2 gejoint, s1 wird in s2 reingejoint,
     * das heißt s2 und dann wird s1 angehängt
     *
     * @param s1 Sorted Sells der Partition 1
     * @param s2 Sorted Sells der Partition 2
     * @return konkatenierte SortedSells die aus dem Join hervorgegangen sind
     */
    public MyArrayList<MyInteger> makeSortedSellsJoin(MyArrayList<MyInteger> s1, MyArrayList<MyInteger> s2) {
        MyArrayList<MyInteger> s = new MyArrayList<>();
        s.addAll(s2);
        s.addAll(s1);
        return s;

    }

    public Integer makeBudgetJoin(Partition b1, Partition b2) {
        Integer budget = Integer.min(b2.getBudget(), b2.getBalance() + b1.getBudget());
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!! Hier " + budget + "b2.getBudget() : " + b2.getBudget() + "b1.getBudget() : " + b1.getBudget());
        return budget;

    }

    public Integer makeBalanceJoin(Partition b1, Partition b2) {
        Integer balance = b1.getBalance() + b2.getBalance();

        return balance;

    }

    /**
     * füllt die kurze Liste von Lengths von rechten Intervallgrenzen von
     * PositiveSets und die kurze Liste der aufsummierten Boughts für die
     * PositiveSets, wenn ein positive minimal MySet gefunden ist, werden alle
     * Einträge von balanceBoughtsBudgetOfSetUpToIndex bis zu diesem Index auf 0
     * gesetzt,und
     *
     * @param p Partition
     * @param PositiveSetsPLengths PositiveSets die wir für diesen UNION Merge
     * der SortedSells bracuhen
     * @param PositiveSetsPLengthsSumBoughts Summe der Boughts die wir für die
     * PositiveSets brauchen
     */
    public void fillPositiveSets(Partition p) {
        /**
         * wir wollen die PositiveSets Lengths und die benötigten Einkaufssummen
         */

        /**
         *
         *
         * noch nicht abgearbeiteter Rest der Liste SortedSells
         */
        MyArrayList<MyInteger> sRest = new MyArrayList<>();
        sRest.addAll(p.sortedSells);
        System.out.println("SRest: " + sRest.toString());

        Integer count = 0;
        MyArrayList<MyInteger> sellsForCurrentPositiveSet = new MyArrayList<>();
        for (int i = 0; i < sRest.size(); i++) {

            // berechne balance bis laufindexBisMaxKnotenAnzahl
            //Eigentlich sollte man schon aufhören wenn es positiv ist oder?
            p.setValueOfBalanceBoughtsBudgetOfSet(i);
            count++;
            sellsForCurrentPositiveSet.add(sRest.get(i));
            BalanceBoughtsBudget currentBalanceBoughtsBudget;
            currentBalanceBoughtsBudget = p.balanceBoughtsBudgetOfSetUpToIndex.get(i);// Index 0???
            int balance = currentBalanceBoughtsBudget.getBalance();
            int sumBoughts = currentBalanceBoughtsBudget.getBoughts();
            int budget = currentBalanceBoughtsBudget.getBudget();

            //sobald es größer als Null ist wird der Index und dieSumme der Boughts in PositiveSetsPLengths und PositiveSetsPLengthsSumBoughts gespeichert
            if (balance > 0) {
                MySet newPositiveSet = new MySet(sumBoughts, count, budget, balance);
                newPositiveSet.setSortedSells(sellsForCurrentPositiveSet);
                p.positiveSets.add(newPositiveSet);

                //Alles zurücksetzen
                count = 0;
                sellsForCurrentPositiveSet = new MyArrayList<>();
                System.out.println("PositiveSetsPLengths: " + "PositiveSetsPLengthsSumBoughts: " + p.balanceBoughtsBudgetOfSetUpToIndex.toString());//[] ist richtig
//                System.sumQuotuienten.println("PositiveSetsPLengthsSumBoughts: " + p.positiveSetsPLengthsSumBoughts.toString());//[0]ist richtig

                //hier will ich alle vorher zurücksetzten balance aber eigentlich auch bought
                for (int k = 0; k < i + 1; k++) {

                    p.balanceBoughtsBudgetOfSetUpToIndex.get(k).setBalance(0);//index 1 size 1!!!!!!!!!!!!!!!!!!!
                    p.balanceBoughtsBudgetOfSetUpToIndex.get(k).setBoughts(0);
                    p.balanceBoughtsBudgetOfSetUpToIndex.get(k).setBudget(0);
                }
            }

        }
    }

    /**
     * macht neue SortedSells (Reihenfolge der Boughts) indem es die SortedSells
     * der Partitionen UNION mergt
     *
     * @param p1 Zufällige Partition,deren SortedSells UNION gemergt wird
     * @param p2 Zufällige Partition,deren SortedSells UNION gemergt wird
     * @return SortedSells, die aus dem UNION Merge der SortedSells der Eingabe
     * Partitionen entstanden ist
     */
    public void makeSortedSellsUnionAndBudgetAndBalance(Partition partitio, Partition p1, Partition p2) {

        //hier musst du mit laufindexBisMaxKnotenAnzahl Lists of PositiveSets und Resten arbeiten
        Integer budget = 0;
        Integer balance = 0;

        MyArrayList<MyInteger> newSortedSells = new MyArrayList<>();
        MyArrayList<Eintrag> currentEintraegeP1 = new MyArrayList<>();
        System.out.println("p1.getEintraege()" + p1.getEintraege());
        Graph calculatedGraphOfSortedSells = new Graph(p1, p1.sortedSells);
        currentEintraegeP1 = (MyArrayList<Eintrag>) calculatedGraphOfSortedSells.getEintraege().clone();
//        currentEintraegeP1 = (MyArrayList<Eintrag>) p1.getEintraege().clone();// todo: getEinträge
//        if (currentEintraegeP1.size() < 1) {
//            System.sumQuotuienten.println("currentEintraegeP1 nach clone" + currentEintraegeP1);
//
//        }
        System.out.println("currentEintraegeP1 nach clone" + currentEintraegeP1);

        MyArrayList<Eintrag> currentEintraegeP2 = new MyArrayList<>();
        System.out.println("p2.getEintraege()" + p2.getEintraege());
        calculatedGraphOfSortedSells = new Graph(p2, p2.sortedSells);
        currentEintraegeP2 = (MyArrayList<Eintrag>) calculatedGraphOfSortedSells.getEintraege().clone();
//        currentEintraegeP2 = (MyArrayList<Eintrag>) p2.getEintraege().clone();
//        if (currentEintraegeP2.size() < 1) {
//            System.sumQuotuienten.println("currentEintraegeP2 nach clone" + currentEintraegeP1);
//
//        }
        System.out.println("currentEintraegeP2 nach clone" + currentEintraegeP2);

        MyArrayList<MyInteger> s1Rest = new MyArrayList<>();
//        s1Rest = new MyArrayList<>();
        s1Rest.addAll(p1.sortedSells);

        MyArrayList<MyInteger> s2Rest = new MyArrayList<>();
        s2Rest.addAll(p2.sortedSells);
//----------test
        System.out.println("currentEintraegeP1:" + currentEintraegeP1);
        System.out.println("currentEintraegeP2:" + currentEintraegeP2);
        System.out.println("s1Rest:" + s1Rest);
        System.out.println("s2Rest:" + s2Rest);
//-------------------

        fillPositiveSets(p1);
        System.out.println("PositiveSets 1 : " + p1.positiveSets.toString());//[] ist richtig

        fillPositiveSets(p2);
        System.out.println("PositiveSets 2 : " + p2.positiveSets.toString());//[] ist richtig

//----------test
        System.out.println("Nach fillPositiveSets:");

        System.out.println("currentEintraegeP1:" + currentEintraegeP1);
        System.out.println("currentEintraegeP2:" + currentEintraegeP2);
        System.out.println("s1Rest:" + s1Rest);
        System.out.println("s2Rest:" + s2Rest);
//-------------------
//        
//        Iterator it1 = PositiveSetsPLengths1Lengths.iterator();
//                 Iterator it2 = PositiveSetsPLengths2Lengths.iterator();
//          /////////////////////////////////////////////////////////////////////////
        //jetzt wird gemergt
        IntegerOut budgetHelp = new IntegerOut(budget);
        IntegerOut balanceHelp = new IntegerOut(balance);

        while ((!p1.positiveSets.isEmpty()) && (!p2.positiveSets.isEmpty())) {
//MyArrayList<MyInteger> testSortedSells= new adjacencyList<>();
            ///////////////////////////////////////////////////////////////////////
            //wenn der negative Budgetwert größer ist, ist das Budget ja das Kleinere
            if (p1.positiveSets.get(0).getBudget() > p2.positiveSets.get(0).getBudget()) {//

                positiveSetAbarbeiten(partitio, currentEintraegeP1, newSortedSells, p1, s1Rest, budgetHelp, balanceHelp, "s1");

            } else ////////////////////////////////////////////////////////////////////////////////
            {
                positiveSetAbarbeiten(partitio, currentEintraegeP2, newSortedSells, p2, s2Rest, budgetHelp, balanceHelp, "s2");
                System.out.println("Balance : " + balanceHelp.toString());
            }
//----------test
            System.out.println("Nach positiveSets abarbeiten beide nicht leer:");
            System.out.println("currentEintraegeP1:" + currentEintraegeP1);
            System.out.println("currentEintraegeP2:" + currentEintraegeP2);
            System.out.println("s1Rest:" + s1Rest);
            System.out.println("s2Rest:" + s2Rest);
//-------------------
        }
        //////////////////////////////////////////////////////////////////////////////
        while (!(p1.positiveSets.isEmpty())) {

            positiveSetAbarbeiten(partitio, currentEintraegeP1, newSortedSells, p1, s1Rest, budgetHelp, balanceHelp, "s1");
//----------test
            System.out.println("Balance : " + balanceHelp.toString());
            System.out.println("Nach positiveSets abarbeiten p1 nicht leer:");
            System.out.println("currentEintraegeP1:" + currentEintraegeP1);
            System.out.println("currentEintraegeP2:" + currentEintraegeP2);
            System.out.println("s1Rest:" + s1Rest);
            System.out.println("s2Rest:" + s2Rest);
//-------------------
        }
        //////////////////////////////////////////////////////////////////////////
        while (!(p2.positiveSets.isEmpty())) {
            positiveSetAbarbeiten(partitio, currentEintraegeP2, newSortedSells, p2, s2Rest, budgetHelp, balanceHelp, "s2");
//----------test
            System.out.println("Balance : " + balanceHelp.toString());
            System.out.println("Nach positiveSets abarbeiten p2 nicht leer:");
            System.out.println("currentEintraegeP1:" + currentEintraegeP1);
            System.out.println("currentEintraegeP2:" + currentEintraegeP2);
            System.out.println("s1Rest:" + s1Rest);
            System.out.println("s2Rest:" + s2Rest);
//-------------------
        }
        budget = budgetHelp.getNumber();
        balance = balanceHelp.getNumber();
        /////////////////////////////////////////////////////////////////////////////////////////////////
        while ((!s1Rest.isEmpty()) && (!s2Rest.isEmpty())) {
            // TODO: Das muss jetzt noch in 3 Schleifen solange bis beide SortedSells aufgebraucht sind 
            int minIndexP1 = getIndexOfMin(currentEintraegeP1);
            int minIndexP2 = getIndexOfMin(currentEintraegeP2);

            Integer minWertP1 = currentEintraegeP1.get(minIndexP1).value;
            Integer minWertP2 = currentEintraegeP2.get(minIndexP2).value;
            MyArrayList<Eintrag> abschnitt1 = getArrayAbschnitt(currentEintraegeP1, minIndexP1, currentEintraegeP1.size() - 1);
            int help = getIndexOfMax(abschnitt1);
            System.out.println("help : " + help);
            int maxIndexP1 = help + minIndexP1;//Indizes werden addiert                                     

            MyArrayList<Eintrag> abschnitt2 = getArrayAbschnitt(currentEintraegeP2, minIndexP2, currentEintraegeP2.size() - 1);
            int maxIndexP2 = getIndexOfMax(abschnitt2) + minIndexP2;//warum hier 1 addiert werden muss ist schleierhaft, aber der wird ist immer ienen Index höher

            if (!p2.sortedSells.contains(currentEintraegeP2.get(maxIndexP2).node)) {
                System.out.println("p2.sortedSells" + p2.sortedSells);
                System.out.println("node" + currentEintraegeP2.get(maxIndexP2).node);
                System.out.println("index" + maxIndexP2);

                System.out.println("nein!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            }
//            if (!s1Rest.contains(currentEintraegeP1.get(maxIndexP1).node)) {
//
//                System.sumQuotuienten.println("nein!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//            }
            Integer maxWertP1 = currentEintraegeP1.get(maxIndexP1).value;
            Integer maxWertP2 = currentEintraegeP2.get(maxIndexP2).value;

            //----------test
            System.out.println("Nach min und max:");
            System.out.println("currentEintraegeP1:" + currentEintraegeP1);
            System.out.println("currentEintraegeP2:" + currentEintraegeP2);
            System.out.println("s1Rest:" + s1Rest);
            System.out.println("s2Rest:" + s2Rest);
//-------------------

/////////////////////////////////////////////
//            if (Integer.min(minWertP2, maxWertP2 + minWertP1) < Integer.min(minWertP1, maxWertP1 + minWertP2)) {
            if (Integer.min(minWertP2, maxWertP2 + minWertP1) < Integer.min(minWertP1, maxWertP1 + minWertP2)) {

                System.out.println("zuerst die 1. Partition");
                MyArrayList<MyInteger> test = new MyArrayList<>();
                for (int i = 0; i < currentEintraegeP1.size(); i++) {
                    test.add(currentEintraegeP1.get(i).node);
                }
                //TODO: ändern
                if (!(test.containsAll(s1Rest))) {
                    System.out.println("da stimmts nicht mehr");
                }
                System.out.println("currentEintraegeP1 : " + currentEintraegeP1);
                //hier wird es nicht richtig berechnet, der node ist gar nicht drinin s1Rest
                MyArrayList<Eintrag> abschnitt = getEintraegeBisNodeInclusive(currentEintraegeP1, currentEintraegeP1.get(maxIndexP1).node);
                if (partitio.getEintraege() == null) {
                    MyArrayList<Eintrag> neuerabschnitt = new MyArrayList<>();
                    neuerabschnitt.addAll(abschnitt);
                    partitio.setEintraege(neuerabschnitt);
                } else {
                    MyArrayList<Eintrag> neuerabschnitt = partitio.getEintraege();
                    neuerabschnitt.addAll(abschnitt);
                    partitio.setEintraege(neuerabschnitt);

                }
                MyArrayList<MyInteger> anfangsAbschnittS1 = getAbschnittBisNode(s1Rest, currentEintraegeP1.get(maxIndexP1).node, "s1");
                newSortedSells.addAll(anfangsAbschnittS1);
                s1Rest.removeAll(anfangsAbschnittS1);

                budget = Integer.min(balance + minWertP1, budget);
                balance = balance + maxWertP1;

                System.out.println("Balance                           !!!!!!!!!!!!!!!                               :  " + balance);
                System.out.println("Budget                           !!!!!!!!!!!!!!!                               :  " + budget);

                System.out.println("currentEintraegeP1 nach Shift um " + maxWertP1 + "wegen Teil vom Rest verarbeitet ist" + currentEintraegeP1);
                currentEintraegeP1 = shift(currentEintraegeP1, maxWertP1, " p1 ");//Ausgleichsshift
                System.out.println("currentEintraegeP1 nach Ausgleichsshift um " + maxWertP1 + "ist" + currentEintraegeP1);

                currentEintraegeP1 = getArrayAbschnitt(currentEintraegeP1, maxIndexP1 + 1, currentEintraegeP1.size() - 1);
            } ///////////////////////////////////////////////////////////
            else {
                System.out.println("zuerst die 2. Partition");

                System.out.println("currentEintraegeP2" + currentEintraegeP2);
                //ASSERTION

                MyArrayList<MyInteger> test = new MyArrayList<>();
                for (int i = 0; i < currentEintraegeP2.size(); i++) {
                    test.add(currentEintraegeP2.get(i).node);
                }
                //TODO: ändern
                if (!(test.containsAll(s2Rest))) {
                    System.out.println("da stimmts nicht mehr");
                }

                MyArrayList<Eintrag> abschnitt = getEintraegeBisNodeInclusive(currentEintraegeP2, currentEintraegeP2.get(maxIndexP2).node);
                if (partitio.getEintraege() == null) {
                    MyArrayList<Eintrag> neuerabschnitt = new MyArrayList<>();
                    neuerabschnitt.addAll(abschnitt);
                    partitio.setEintraege(neuerabschnitt);
                } else {
                    MyArrayList<Eintrag> neuerabschnitt = partitio.getEintraege();
                    neuerabschnitt.addAll(abschnitt);
                    partitio.setEintraege(neuerabschnitt);

                }

                MyArrayList<MyInteger> anfangsAbschnittS2 = getAbschnittBisNode(s2Rest, currentEintraegeP2.get(maxIndexP2).node, "s2");
                newSortedSells.addAll(anfangsAbschnittS2);
                s2Rest.removeAll(anfangsAbschnittS2);

                budget = Integer.min(balance + minWertP2, budget);
                balance = balance + maxWertP2;

                System.out.println("Balance                           !!!!!!!!!!!!!!!                               :  " + balance);
                System.out.println("Budget                           !!!!!!!!!!!!!!!                               :  " + budget);

                currentEintraegeP2 = getArrayAbschnitt(currentEintraegeP2, maxIndexP2 + 1, currentEintraegeP2.size() - 1);
                currentEintraegeP2 = shift(currentEintraegeP2, maxWertP2, " p2 ");//Ausgleichsshift
                System.out.println("currentEintraegeP2 nach Ausgleichsshift um " + maxWertP2 + "ist" + currentEintraegeP2);
            }
        }
/////////////////////////////////////////////////////////////////////
        while (!(s1Rest.isEmpty())) {
            System.out.println("Jetzt noch der Rest non S 1 ");

            int minIndexP1 = getIndexOfMin(currentEintraegeP1);
            Integer minWertP1 = currentEintraegeP1.get(minIndexP1).value;
            Integer endWertP1 = currentEintraegeP1.get(currentEintraegeP1.size() - 1).value;
            budget = Integer.min(balance + minWertP1, budget);
            balance = balance + endWertP1;

            System.out.println("Balance                           !!!!!!!!!!!!!!!                               :  " + balance);
            System.out.println("Budget                           !!!!!!!!!!!!!!!         "
                    + "                      :  " + budget);
            if (partitio.getEintraege() == null) {
                MyArrayList<Eintrag> neuerabschnitt = new MyArrayList<>();
                neuerabschnitt.addAll(currentEintraegeP1);
                partitio.setEintraege(neuerabschnitt);

            } else {
                MyArrayList<Eintrag> neuerabschnitt = partitio.getEintraege();
                neuerabschnitt.addAll(currentEintraegeP1);
                partitio.setEintraege(neuerabschnitt);
            }
            newSortedSells.addAll(s1Rest);

            s1Rest.removeAll(s1Rest);
        }
//////////////////////////////////////////////////////////////////////////
        while (!(s2Rest.isEmpty())) {
            System.out.println("Jetzt noch der Rest non S 2 ");
            int minIndexP2 = getIndexOfMin(currentEintraegeP2);
            Integer minWertP2 = currentEintraegeP2.get(minIndexP2).value;
            Integer endWertP2 = currentEintraegeP2.get(currentEintraegeP2.size() - 1).value;
            budget = Integer.min(balance + minWertP2, budget);
            balance = balance + endWertP2;

            System.out.println("Balance                           !!!!!!!!!!!!!!!                               :  " + balance);
            System.out.println("Budget                           !!!!!!!!!!!!!!!                               :  " + budget);
            if (partitio.getEintraege() == null) {

                MyArrayList<Eintrag> neuerabschnitt = new MyArrayList<>();
                neuerabschnitt.addAll(currentEintraegeP2);
                partitio.setEintraege(neuerabschnitt);
            } else {
                MyArrayList<Eintrag> neuerabschnitt = partitio.getEintraege();
                neuerabschnitt.addAll(currentEintraegeP1);
                partitio.setEintraege(neuerabschnitt);
            }
            newSortedSells.addAll(s2Rest);
            s2Rest.removeAll(s2Rest);
        }

        System.out.println("newSortedSells: " + newSortedSells.toString());
        partitio.sortedSells = newSortedSells;

        partitio.setBalance(balance);
        partitio.setBudget(budget);

        System.out.println("Balance                           !!!!!!!!!!!!!!!                               :  " + balance);
        System.out.println("Budget                           !!!!!!!!!!!!!!!                               :  " + budget);

    }

    static void permute(MyArrayList<MyInteger> arr, int k, MyArrayList<MyArrayList<MyInteger>> returnList) {

        for (int i = k; i < arr.size(); i++) {
            java.util.Collections.swap(arr, i, k);

            permute(arr, k + 1, returnList);
//            java.util.Collections.swap(arr, k, laufindexBisMaxKnotenAnzahl);
        }
        if (k == arr.size() - 1) {
            System.out.println("Permutation : " + arr);
            returnList.add(arr);
        }

    }

    /**
     * hier werden die Adjazenslisten a1 und a2 zu einer neue Adjazensliste
     * gejoint, a1 wird in a2 reingejoint, das heißt a2 muss vor a1 abgearbeitet
     * serden
     *
     * @param a1 Adjazensliste a1
     * @param a2 Adjazensliste a2
     * @return gejointe Adjazensliste a1 in a2
     */
    public MyArrayList<MyArrayList<MyInteger>> makeMyArrayListJoin(Partition p1, Partition p2) {

        MyArrayList<MyArrayList<MyInteger>> a = new MyArrayList<>();
        for (int m = 0; m < p1.adjacencyList.size(); m++) {

            a.add((MyArrayList<MyInteger>) p1.adjacencyList.get(m).clone());

        }

        MyArrayList<MyInteger> toLink = new MyArrayList<>();
        for (int k = 0; k < p2.adjacencyList.size(); k++) {
            toLink.addAll(p2.getBoughtsOfSell(p2.adjacencyList.get(k).get(0)));
        }
//        
        Set<MyInteger> hs = new HashSet<>();
        hs.addAll(toLink);
        toLink.clear();
        toLink.addAll(hs);
//
//        }
        for (int k = 0; k < a.size(); k++) {
            a.get(k).addAll(toLink);
        }
        for (int m = 0; m < p2.adjacencyList.size(); m++) {

            a.add((MyArrayList<MyInteger>) p2.adjacencyList.get(m).clone());

        }

        return a;

    }

    /**
     * macht eine neue Partition, die aus dem UNION Merge der Eingabe
     * Partitionen entstanden ist
     *
     * @param p1 Zufällige Partition die jetzt mit einer anderen UNION gemergt
     * wird
     * @param p2 Zufällige Partition die jetzt mit einer anderen UNION gemergt
     * wird
     * @return Die Partition, die aus dem UNION Merge der Eingabe Partitionen
     * entstanden ist
     */
    public Partition makePartitionUnion(Partition p1, Partition p2) {
        Partition partition = new Partition();

        partition.adjacencyList = makeMyArrayListUnion(p1.adjacencyList, p2.adjacencyList);//What ich übergebe was size 2 und danach hat es size 0????

        makeSortedSellsUnionAndBudgetAndBalance(partition, p1, p2);

        //ASSERTION
        if (!partition.orderingFitsBudget()) {
            boolean test = partition.isBestOrdering();
        }

//--------------------------------
        return partition;
    }

    /**
     * macht eine neue Adjazensliste die, indem es die Adjazenslisten a1 und a2
     * konkateniert
     *
     * @param a1 Adjazensliste (graph) wird
     * @param a2 Adjazensliste (graph) wird
     * @return Adjazensliste (Graph), die aus dem UNION Merge (der
     * Konkatenation) der Eingabe Adjazenslisten (Graphen) entstanden ist
     */
    public MyArrayList<MyArrayList<MyInteger>> makeMyArrayListUnion(MyArrayList<MyArrayList<MyInteger>> a1, MyArrayList<MyArrayList<MyInteger>> a2) {
        MyArrayList<MyArrayList<MyInteger>> a = new MyArrayList<>();
        for (int m = 0; m < a1.size(); m++) {

            a.add((MyArrayList<MyInteger>) a1.get(m).clone());

        }
        for (int m = 0; m < a2.size(); m++) {

            a.add((MyArrayList<MyInteger>) a2.get(m).clone());

        }

        return a;
    }

    /**
     * macht eine neue Partition die, die JOIN gemergte Adjazensliste und die
     * JOIN gemergte Reihenfolge der Sells enthält alle Boughts von p2 werden
     * mit allen Sells von p1 verbunden
     *
     *
     *
     * @param p1 Zufällige Partition, die (deren Sells) jetzt mit einer anderen
     * JOIN gemergt wird
     * @param p2 Zufällige Partition, die (deren Boughts) jetzt mit einer
     * anderen JOIN gemergt wird
     * @return Die Partition, die aus dem UNION Merge der Eingabe Partitionen
     * entstanden ist
     */
    public Partition makePartitionJoin(Partition p1, Partition p2) {
        Partition partition;

        MyArrayList<MyArrayList<MyInteger>> newMyArrayList = makeMyArrayListJoin(p1, p2);
        MyArrayList<MyInteger> newSortedSells = makeSortedSellsJoin(p1.sortedSells, p2.sortedSells);//p2.sorted sells null
        Integer newBudget = makeBudgetJoin(p1, p2);
        Integer newBalance = makeBalanceJoin(p1, p2);
        partition = new Partition(newMyArrayList, newSortedSells);
        partition.setBudget(newBudget);
        partition.setBalance(newBalance);
//        System.sumQuotuienten.println(p.toString());
// todo:  Fehler hier?
        //        partition.getCalculatedGraphOfSortedSells().addEintraege(p2.getCalculatedGraphOfSortedSells().getEintraege());
//        adjacencyList<Eintrag> shiftList = shift(p1.getCalculatedGraphOfSortedSells().getEintraege(),p2.balance);
//        partition.getCalculatedGraphOfSortedSells().addEintraege(shiftList);

        MyArrayList neueEintraege = new MyArrayList<>();
        neueEintraege.addAll(p2.getEintraege());
        System.out.println("shiftList vor shift um - balance" + -p2.getBalance() + "wegen Join ist" + p1.getEintraege());
        MyArrayList<Eintrag> shiftList = shift(p1.getEintraege(), -p2.getBalance(), " p1 ");//Addiershift
        System.out.println("shiftList nach shift um - balance" + -p2.getBalance() + "wegen Join ist" + shiftList);

        neueEintraege.addAll(shiftList);
        partition.setEintraege(neueEintraege);

        //ASSERTION
        if (!partition.orderingFitsBudget()) {
            boolean test = partition.isBestOrdering();
        }

//--------------------------------
        return partition;
    }

    public Integer getSum(MyArrayList<MyInteger> newB) {

        Integer newValue = 0;
        for (int j = 0; j < newB.size(); j++) {
            newValue = newValue + newB.get(j).i;

        }
        return newValue;
    }

    MyArrayList<Eintrag> shift(MyArrayList<Eintrag> list, Integer shiftValue, String id) {
        System.out.println("Haaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaalo");
        MyArrayList<Eintrag> newList = (MyArrayList<Eintrag>) list.clone();

        for (int i = 0; i < newList.size(); i++) {
            newList.get(i).value = newList.get(i).value - shiftValue;
        }

        return newList;
    }

    public void outStatistikN(String dateiname, int min, int max, Integer maxKnotenAnzahl, Integer schrittlaenge, Integer pool) {

        File fileX = new File("C:\\Users\\Soyo\\Desktop\\Bachelorarbeit\\Daten\\" + dateiname + "DatenX.txt");
        File fileY = new File("C:\\Users\\Soyo\\Desktop\\Bachelorarbeit\\Daten\\" + dateiname + "DatenY.txt");

        try {
//            file.mkdirs();
            fileX.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
//            file.mkdirs();
            fileY.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        Partition instance;
        try {
            PrintWriter prX = new PrintWriter(fileX);
            PrintWriter prY = new PrintWriter(fileY);
            prX.println(0);
            prY.println(0);

            for (int laufindexBisMaxKnotenAnzahl = 2;
                    laufindexBisMaxKnotenAnzahl < maxKnotenAnzahl; laufindexBisMaxKnotenAnzahl += schrittlaenge) {// todo: kein +10 ??????
                double mittelwertSwap = 0;
                double mittelwertSortedSells = 0;
                double sumQuotuienten = 0;
                prX.println(laufindexBisMaxKnotenAnzahl);
//-----------------------------------------------------------------------------
//                for (int count = 0; count < pool; count++) {
//
//                    instance = buildInstance(min, max, laufindexBisMaxKnotenAnzahl);
//
//                    Graph newGraph = new Graph(instance, randomOrdering);
//                    newGraph = getGraphHeuristik(newGraph, "swap");
//
////                prY.println(sumOfBoughts/instance.minBudgetSwap);
//                    mittelwertSortedSells = mittelwertSortedSells + instance.getBudget();
//                    mittelwertSwap = mittelwertSwap + instance.minBudgetSwap;
//                    System.out.println("test" + instance.minBudgetSwap);
//                }
//                mittelwertSwap = mittelwertSwap / pool;
//                mittelwertSortedSells = mittelwertSortedSells / pool;
//                prY.println(mittelwertSortedSells / mittelwertSwap);// todo: hier können wir Wert für Statistik ändern
//               

    //-------------------------------------------------------------------------------------            
                for (int count = 0; count < pool; count++) {
                    instance = buildInstance(min, max, laufindexBisMaxKnotenAnzahl);
                    Graph newGraph = new Graph(instance, randomOrdering);
                    newGraph = getGraphHeuristik(newGraph, "swap");
                    System.out.println("instance.getBudget() : //////////////////////////////////////////////////////!!!                      " + instance.getBudget());
                    System.out.println("instance.minBudgetSwap: //////////////////////////////////////////////////////!!!                      " + instance.minBudgetSwap);

                    double currentOut = (double)instance.getBudget() / instance.minBudgetSwap;
                    System.out.println("currentOut : //////////////////////////////////////////////////////!!!                      " + currentOut);
                    if (currentOut > 1) {
                        System.err.println("falsch berechnet: sorted sells schlechter als swap");
                    }
                    sumQuotuienten = sumQuotuienten + currentOut;
                }
                prY.println(sumQuotuienten / pool);// todo: hier können wir Wert für Statistik ändern
//                System.sumQuotuienten.println("yEintrag : " + sumOfBoughts/instance.minBudgetSwap);

            }
            prX.close();
            prY.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No such file exists.");
        }

//        tool.function(instance, randomOrdering, "swap")
//                tool.function(instance, randomOrdering, "changeOrder"
    }
// todo: Hier gebe ich Integer minBudget zurück

    public void out(Graph currentGraph, String dateiname) {

        File file2 = new File("C:\\Users\\Soyo\\Desktop\\Bachelorarbeit\\Daten\\" + dateiname + "Daten.txt");
//        File file2 = new File("X:\\speedee\\mitarbeiter\\sonja_schäfer\\Bachelorarbeit\\SortedSellsInstance.txt");
        try {
//            file.mkdirs();
            file2.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        try {
            PrintWriter pr = new PrintWriter(file2);
            pr.println(0);

            for (int j = 0; j < currentGraph.getEintraege().size(); j++) {

                pr.println(currentGraph.getEintraege().get(j).value);
            }

            pr.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No such file exists.");
        }

    }

    public Graph getGraphHeuristik(Graph currentGraph, String update) {
        System.out.println("nochmal der Graph nach Heuristik : Budget " + currentGraph.getMinBudget());

        MyArrayList<MyInteger> ordering = new MyArrayList<>();
        ordering.addAll(currentGraph.getOrdering());
        System.out.println("Graph mit SortedSells : Budget " + currentGraph.getPartition().getBudget());
        for (int i = 0; i < ordering.size(); i++) {
            for (int j = i + 1; j < ordering.size(); j++) {
                MyArrayList<MyInteger> newOrdering = new MyArrayList<>();

                if (update.equals("swap")) {
                    newOrdering = swap(i, j, ordering);

                }

                if (update.equals("changeOrder")) {
                    newOrdering = changeOrder(i, j, ordering);

                }
                Graph newGraph = new Graph(currentGraph.getPartition(), newOrdering);

                if (newGraph.getMinBudget() > currentGraph.getMinBudget()) {
                    System.out.println("Graph nach Heuristik : Budget " + newGraph.getMinBudget());
                    System.out.println("Besser");

                    return getGraphHeuristik(newGraph, update);
                } else {
                    System.out.println("nicht besser");
                }

            }
        }

        if (update.equals("swap")) {
            currentGraph.getPartition().minBudgetSwap = currentGraph.getMinBudget();
        }

        if (update.equals("changeOrder")) {
            currentGraph.getPartition().minBudgetChangeOrder = currentGraph.getMinBudget();
        }
        return currentGraph;
    }

    public MyArrayList<MyInteger> swap(int i, int j, MyArrayList<MyInteger> ordering) {
        MyArrayList<MyInteger> newOrdering = new MyArrayList<>();
        newOrdering.addAll(ordering);

        newOrdering.set(i, ordering.get(j));
        newOrdering.set(j, ordering.get(i));
        return newOrdering;
    }

    public Partition buildInstance(int min, int max, int size) {

        MyArrayList<Partition> partitions = makeBasicPartitions(min, max, size);
        buildInstanceOnBasicPartitions(partitions, 33, 33, 34);

        Partition instance = partitions.get(0);
        System.out.println("Instance is build.");
        return instance;
    }

    public MyArrayList<MyInteger> changeOrder(int i, int j, MyArrayList<MyInteger> ordering) {
        MyArrayList<MyInteger> newOrdering = new MyArrayList<>();
        newOrdering.addAll(ordering);
        System.out.println("newOrdering:" + newOrdering);
        List<MyInteger> part = new MyArrayList<>();
        for (int k = i; k < j + 1; k++) {
            part.add(newOrdering.get(k));
        }
        System.out.println("part:" + part);
//MyArrayList.reverse(part);
        Collections.reverse(part);
        System.out.println("part:" + part);

        for (int k = 0; k < part.size(); k++) {
            newOrdering.set(k + i, part.get(k));
        }
        System.out.println("newOrdering:" + newOrdering);

        return newOrdering;
    }

    public MyArrayList<MyInteger> getOrderingOfBasicPartitions(MyArrayList<Partition> basicPartitions) {

        Iterator it = basicPartitions.iterator();
        MyArrayList<MyInteger> randomOrdering = new MyArrayList<>();
        for (int k = 0; k < basicPartitions.size(); k++) {
            randomOrdering.add((basicPartitions.get(k).adjacencyList.get(0)).get(0));
        }
        return randomOrdering;
    }

    public Integer getSumOfBoughts(MyArrayList<Partition> basicPartitions) {
        Iterator it = basicPartitions.iterator();
        Integer sum = 0;
        for (int k = 0; k < basicPartitions.size(); k++) {
            sum = sum + ((basicPartitions.get(k).adjacencyList.get(0)).get(1)).i;
        }
        return sum;
    }

    public void buildIstanceMakeHeuristicsAndOut(int min, int max, int size) {
        Partition instance = buildInstance(min, max, size);

        Graph newGraph;

        newGraph = new Graph(instance, instance.sortedSells);
        System.out.println("Sorted Sells: ");
        out(newGraph, "sortedSells");

        newGraph = new Graph(instance, randomOrdering);
        System.out.println("currentGraph also ersmal Random : Budget " + newGraph.getMinBudget());

        System.out.println("Random: ");
        out(newGraph, "random");

        System.out.println("AfterSwap: ");
        out(getGraphHeuristik(newGraph, "swap"), "afterSwap");

        System.out.println("AfterChangeOrder: ");
        out(getGraphHeuristik(newGraph, "changeOrder"), "afterChangeOrder");
    }

//    public void superFunctionStatistik(int min, int max, int size) {
//        // todo: min, max, size muss noch übergeben werden an outStatitikN
//
//        outStatistikN("test",min,max,10,2,5);
//
//    }
    MyArrayList getArrayAbschnitt(MyArrayList list, int i, int j) {
        System.out.println("list vor Abschnitt " + list);

        MyArrayList abschnitt = new MyArrayList<>();
        for (int k = i; k < j + 1; k++) {

            abschnitt.add(list.get(k));

        }
        System.out.println("list nach Abschnitt von" + i + "bis" + j + "ist" + abschnitt);

        return abschnitt;
    }

    int getIndexOfMin(MyArrayList<Eintrag> list) {
        int index = 0;
        int minWert = list.get(0).value;

        for (int k = 0; k < list.size(); k++) {
            if (list.get(k).value < minWert) {
                minWert = list.get(k).value;
                index = k;
            }

        }
        return index;

    }

    int getIndexOfMax(MyArrayList<Eintrag> list) {
        int index = 0;
        int maxWert = list.get(0).value;

        for (int k = 0; k < list.size(); k++) {
            if (list.get(k).value > maxWert) {
                maxWert = list.get(k).value;
                index = k;
            }

        }
        return index;

    }

    MyArrayList<Eintrag> getEintraegeBisNodeExclusive(MyArrayList<Eintrag> list, MyInteger node) {

        MyArrayList<Eintrag> newList = new MyArrayList<>();
        int k = 0;
        while (!(list.get(k).node == node)) {
            newList.add(list.get(k));

            k++;
        }
//        newList.add(list.get(k));

        return newList;
    }

    MyArrayList<Eintrag> getEintraegeBisNodeInclusive(MyArrayList<Eintrag> list, MyInteger node) {

        MyArrayList<Eintrag> newList = new MyArrayList<>();
        int k = 0;
        while (!(list.get(k).node == node)) {
            newList.add(list.get(k));

            k++;
        }
        newList.add(list.get(k));

        return newList;
    }

    MyArrayList<MyInteger> getAbschnittBisNode(MyArrayList<MyInteger> list, MyInteger node, String id) {
        System.out.println("list" + id + ":");
        for (int i = 0; i < list.size(); i++) {

            System.out.println(list.get(i));
        }
        System.out.println("node:" + node);
        MyArrayList<MyInteger> newList = new MyArrayList<>();
        int k = 0;
        while (!(list.get(k) == node)) {
            newList.add(list.get(k));

            k++;
        }
        newList.add(list.get(k));

        return newList;
    }

    public void positiveSetAbarbeiten(Partition partitio, MyArrayList<Eintrag> currentEintraegePx,
            MyArrayList<MyInteger> newSortedSells, Partition p, MyArrayList<MyInteger> sRest, IntegerOut budget,
            IntegerOut balance, String id) {
//        adjacencyList<MyInteger> test = new adjacencyList<>();
//        for (int laufindexBisMaxKnotenAnzahl = 0; laufindexBisMaxKnotenAnzahl < currentEintraege.size(); laufindexBisMaxKnotenAnzahl++) {
//            test.add(currentEintraege.get(laufindexBisMaxKnotenAnzahl).node);
//        }
//        if (!(test.containsAll(sRest))) {
//            System.sumQuotuienten.println("da stimmts schon am Anfang nicht ");
//        }
        MyInteger currentSell;
        MySet currentPositiveSet = p.positiveSets.get(0);
        for (int countP1 = 0; countP1 < currentPositiveSet.getLength(); countP1++) {
//                    testSortedSells.add(s1Rest.get(0));
            currentSell = sRest.get(0);

            newSortedSells.add(currentSell);//index 0 size 0
            sRest.remove(currentSell);
//                    System.sumQuotuienten.println("S1Rest: " + s1Rest.toString());

            MyArrayList<Eintrag> abschnitt = getEintraegeBisNodeInclusive(currentEintraegePx, currentSell);
            if (partitio.getEintraege() == null) {
                MyArrayList<Eintrag> neuerabschnitt = new MyArrayList<>();
                neuerabschnitt.addAll(abschnitt);
                partitio.setEintraege(neuerabschnitt);
            } else {
                MyArrayList<Eintrag> neuerabschnitt = partitio.getEintraege();
                neuerabschnitt.addAll(abschnitt);
                partitio.setEintraege(neuerabschnitt);

            }
            currentEintraegePx.removeAll(getEintraegeBisNodeInclusive(currentEintraegePx, currentSell));

        }

        // Macht das das was es soll? JA
        currentEintraegePx = shift(currentEintraegePx, currentPositiveSet.getBalance(), " p ");
        System.out.println("currentEintraege " + id + " nach shift um" + currentPositiveSet.getBalance() + "wegen positive set abarbeiten ist" + currentEintraegePx);
        // Macht das das was es soll?
        //ASSERTION
//        test = new adjacencyList<>();
//        for (int laufindexBisMaxKnotenAnzahl = 0; laufindexBisMaxKnotenAnzahl < currentEintraege.size(); laufindexBisMaxKnotenAnzahl++) {
//            test.add(currentEintraege.get(laufindexBisMaxKnotenAnzahl).node);
//        }
//        if (!(test.containsAll(sRest))) {
//            System.sumQuotuienten.println("da stimmts nicht mehr. Manche ");
//        }
        //ASSERTION
        //------------------------------------
        int budgetHelp = budget.getNumber();
        int balanceHelp = balance.getNumber();

        int help = Integer.min(budgetHelp, balanceHelp + currentPositiveSet.getBudget());
        budget.setNumber(help);//balanceBoughtsBudgetOfSetUpToIndex.get(p1.positiveSetsPLengths.get(0)).get(2));
        System.out.println("budget  " + budget);

        balance.setNumber(balanceHelp + currentPositiveSet.getBalance()); //p1.balanceBoughtsBudgetOfSetUpToIndex.get(p1.positiveSetsPLengths.get(0)).get(0);
        System.out.println("balance in Positive Set abarbeiten:" + balance.toString());

        (p.positiveSets).remove(0);

        System.out.println(id + "Rest : " + sRest.toString());

        System.out.println("Balance                           !!!!!!!!!!!!!!!                               :  " + balance.toString());
        System.out.println("Budget                           !!!!!!!!!!!!!!!                               :  " + budget.toString());
    }

    //ASSERTION
    public MyArrayList<MyInteger> makeMyArrayList(int[] integerList) {
        MyArrayList<MyInteger> returnList = new MyArrayList<>();
        for (int i = 0; i < integerList.length; i++) {
            MyInteger newMyInteger = new MyInteger(integerList[i]);
            returnList.add(newMyInteger);
        }
        return returnList;
    }

    public Partition getBspPartition() {

        Partition instance = new Partition();

        MyInteger myInt46 = new MyInteger(46);
        MyInteger myInt21nr1 = new MyInteger(21);
        MyInteger myInt29 = new MyInteger(29);
        MyInteger myInt21nr2 = new MyInteger(21);
        MyInteger myInt47 = new MyInteger(47);

        MyInteger myInt40 = new MyInteger(40);
        MyInteger myInt5nr2 = new MyInteger(5);
        MyInteger myInt5nr3 = new MyInteger(5);
        MyInteger myInt33 = new MyInteger(33);
        MyInteger myInt7 = new MyInteger(7);

        MyArrayList<MyInteger> nextList2 = new MyArrayList<>();
        nextList2.addAll(Arrays.asList(myInt40, myInt46));
        instance.adjacencyList.add(nextList2);

        MyArrayList<MyInteger> nextList3 = new MyArrayList<>();
        nextList3.addAll(Arrays.asList(myInt5nr2, myInt21nr1));
        instance.adjacencyList.add(nextList3);

        MyArrayList<MyInteger> nextList4 = new MyArrayList<>();
        nextList4.addAll(Arrays.asList(myInt5nr3, myInt29));
        instance.adjacencyList.add(nextList4);

        MyArrayList<MyInteger> nextList5 = new MyArrayList<>();
        nextList5.addAll(Arrays.asList(myInt33, myInt29, myInt21nr2));
        instance.adjacencyList.add(nextList5);

        MyArrayList<MyInteger> nextList6 = new MyArrayList<>();
        nextList6.addAll(Arrays.asList(myInt7, myInt29, myInt21nr2, myInt47));
        instance.adjacencyList.add(nextList6);

        instance.sortedSells.addAll(Arrays.asList(myInt40, myInt5nr3, myInt33, myInt7, myInt5nr2));
        System.out.println("BspPertition: " + instance.toString());
        return instance;
    }

    public Partition getBspA() {

        Partition instance = new Partition();

        MyInteger myInt46 = new MyInteger(46);
        MyInteger myInt21nr1 = new MyInteger(21);

        MyInteger myInt40 = new MyInteger(40);
        MyInteger myInt5nr2 = new MyInteger(5);

        MyArrayList<MyInteger> nextList2 = new MyArrayList<>();
        nextList2.addAll(Arrays.asList(myInt40, myInt46));
        instance.adjacencyList.add(nextList2);

        MyArrayList<MyInteger> nextList3 = new MyArrayList<>();
        nextList3.addAll(Arrays.asList(myInt5nr2, myInt21nr1));
        instance.adjacencyList.add(nextList3);

        instance.sortedSells.addAll(Arrays.asList(myInt40, myInt5nr2));
//        instance.setBudget(-46);
//        instance.setBalance(-22);
        System.out.println("BspPertition A: " + instance.toString());
        return instance;
    }

    public Partition getBspB() {

        Partition instance = new Partition();

        MyInteger myInt29 = new MyInteger(29);
        MyInteger myInt21nr2 = new MyInteger(21);
        MyInteger myInt47 = new MyInteger(47);

        MyInteger myInt5nr3 = new MyInteger(5);
        MyInteger myInt33 = new MyInteger(33);
        MyInteger myInt7 = new MyInteger(7);

        MyArrayList<MyInteger> nextList4 = new MyArrayList<>();
        nextList4.addAll(Arrays.asList(myInt5nr3, myInt29));
        instance.adjacencyList.add(nextList4);

        MyArrayList<MyInteger> nextList5 = new MyArrayList<>();
        nextList5.addAll(Arrays.asList(myInt33, myInt29, myInt21nr2));
        instance.adjacencyList.add(nextList5);

        MyArrayList<MyInteger> nextList6 = new MyArrayList<>();
        nextList6.addAll(Arrays.asList(myInt7, myInt29, myInt21nr2, myInt47));
        instance.adjacencyList.add(nextList6);

        instance.sortedSells.addAll(Arrays.asList(myInt5nr3, myInt33, myInt7));
//        instance.setBudget(-49);
//        instance.setBalance(-42);
        System.out.println("BspPertition B: " + instance.toString());
        return instance;
    }

    public Partition getBspC() {

        Partition instance = new Partition();

        MyInteger myInt29 = new MyInteger(3);
        MyInteger myInt21nr2 = new MyInteger(21);
        MyInteger myInt47 = new MyInteger(1);

        MyInteger myInt5nr3 = new MyInteger(5);
        MyInteger myInt33 = new MyInteger(33);
        MyInteger myInt7 = new MyInteger(7);

        MyArrayList<MyInteger> nextList4 = new MyArrayList<>();
        nextList4.addAll(Arrays.asList(myInt5nr3, myInt29));
        instance.adjacencyList.add(nextList4);

        MyArrayList<MyInteger> nextList5 = new MyArrayList<>();
        nextList5.addAll(Arrays.asList(myInt33, myInt29, myInt21nr2));
        instance.adjacencyList.add(nextList5);

        MyArrayList<MyInteger> nextList6 = new MyArrayList<>();
        nextList6.addAll(Arrays.asList(myInt7, myInt29, myInt21nr2, myInt47));
        instance.adjacencyList.add(nextList6);

        instance.sortedSells.addAll(Arrays.asList(myInt5nr3, myInt33, myInt7));
//        instance.setBudget(-49);
//        instance.setBalance(-42);
        System.out.println("BspPertition C: " + instance.toString());
        return instance;
    }

    public Partition getBspD() {

        Partition instance = new Partition();

        MyInteger myInt5und1 = new MyInteger(5);

        MyInteger myInt19 = new MyInteger(19);

        MyArrayList<MyInteger> nextList2 = new MyArrayList<>();
        nextList2.addAll(Arrays.asList(myInt19, myInt5und1));
        instance.adjacencyList.add(nextList2);

        instance.sortedSells.addAll(Arrays.asList(myInt19));
//        instance.setBudget(-46);
//        instance.setBalance(-22);
        System.out.println("BspPertition D: " + instance.toString());
        return instance;
    }

    public Partition getBspE() {

        Partition instance = new Partition();

        MyInteger myInt29 = new MyInteger(29);
        MyInteger myInt21nr2 = new MyInteger(21);
        MyInteger myInt47 = new MyInteger(18);

        MyInteger myInt5nr3 = new MyInteger(5);
        MyInteger myInt33 = new MyInteger(33);
        MyInteger myInt7 = new MyInteger(7);

        MyArrayList<MyInteger> nextList4 = new MyArrayList<>();
        nextList4.addAll(Arrays.asList(myInt5nr3, myInt29));
        instance.adjacencyList.add(nextList4);

        MyArrayList<MyInteger> nextList5 = new MyArrayList<>();
        nextList5.addAll(Arrays.asList(myInt33, myInt29, myInt21nr2));
        instance.adjacencyList.add(nextList5);

        MyArrayList<MyInteger> nextList6 = new MyArrayList<>();
        nextList6.addAll(Arrays.asList(myInt7, myInt29, myInt21nr2, myInt47));
        instance.adjacencyList.add(nextList6);

        instance.sortedSells.addAll(Arrays.asList(myInt5nr3, myInt33, myInt7));
//        instance.setBudget(-49);
//        instance.setBalance(-42);
        System.out.println("BspPertition B: " + instance.toString());
        return instance;
    }

    public Partition getBspF() {

        Partition instance = new Partition();

        MyInteger myInt40 = new MyInteger(40);
        MyInteger myInt25 = new MyInteger(25);
        MyInteger myInt38 = new MyInteger(38);

        MyInteger myInt16 = new MyInteger(16);
        MyInteger myInt60 = new MyInteger(60);
        MyInteger myInt19 = new MyInteger(19);

        MyArrayList<MyInteger> nextList4 = new MyArrayList<>();
        nextList4.addAll(Arrays.asList(myInt16, myInt40, myInt25));
        instance.adjacencyList.add(nextList4);

        MyArrayList<MyInteger> nextList5 = new MyArrayList<>();
        nextList5.addAll(Arrays.asList(myInt60, myInt25, myInt38));
        instance.adjacencyList.add(nextList5);

        MyArrayList<MyInteger> nextList6 = new MyArrayList<>();
        nextList6.addAll(Arrays.asList(myInt19, myInt25));
        instance.adjacencyList.add(nextList6);

        instance.sortedSells.addAll(Arrays.asList(myInt19, myInt60, myInt16));
//        instance.setBudget(-49);
//        instance.setBalance(-42);
        System.out.println("BspPertition F: " + instance.toString());
        return instance;
    }

    public Partition getBspG() {

        Partition instance = new Partition();

        MyInteger myInt49nr1 = new MyInteger(49);
        MyInteger myInt49nr2 = new MyInteger(49);

        MyArrayList<MyInteger> nextList4 = new MyArrayList<>();
        nextList4.addAll(Arrays.asList(myInt49nr2, myInt49nr1));
        instance.adjacencyList.add(nextList4);

        instance.sortedSells.addAll(Arrays.asList(myInt49nr2));
//        instance.setBudget(-49);
//        instance.setBalance(-42);
        System.out.println("BspPertition G: " + instance.toString());
        return instance;
    }

}
