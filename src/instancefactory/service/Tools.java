/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instancefactory.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
     * @return ArrayList of Partition
     */
    public ArrayList<MyInteger> randomOrdering;

    public ArrayList<Partition> makeBasicPartitions(int min, int max, int size) {
//        ArrayList<MyInteger> randomMyIntArrayList = this.getDeterministicMyIntArray();
//        System.out.println("");
        ArrayList<MyInteger> randomMyIntArrayList = this.getRandomMyIntArray(min, max, size);
        ArrayList<Partition> partitions = new ArrayList<>();
        MyInteger currentRandom;
        int k = 0;
        while (k < randomMyIntArrayList.size() - 1) { //der letzte wird eventuel abgeschnitten
            Partition partition1 = new Partition();
            currentRandom = randomMyIntArrayList.get(k);

            System.out.println("Aktueller Sell :" + currentRandom);
            k++;
            ArrayList<MyInteger> l = new ArrayList<>();
            l.add(currentRandom);

            partition1.arrayList.add(l);
            partition1.sortedSells.add(currentRandom);

            partition1.balance = currentRandom.i;

            partitions.add(partition1);
// todo: testen
            currentRandom = randomMyIntArrayList.get(k);

            System.out.println("Aktueller Bought : " + currentRandom);
            Partition currentPartition = partitions.get(partitions.size() - 1);
            currentPartition.arrayList.get(0).add(currentRandom);
            currentPartition.balance = partitions.get(partitions.size() - 1).balance - currentRandom.i;
            currentPartition.budget = partitions.get(partitions.size() - 1).budget - currentRandom.i;

// kann das weg?
            Graph calculatedGraphOfSortedSells = new Graph(currentPartition, currentPartition.sortedSells);

            currentPartition.werte = calculatedGraphOfSortedSells.getWerte();

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
    public void buildInstanceOnBasicPartitions(ArrayList<Partition> partitions, int unionProbability, int leftJoinProbability, int rightJoinProbability) {

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
            System.out.println("Sorted SellsOfMergedPartition: " + partition.sortedSells.toString() + "\n");
//ASSERTION
            if (!partition.orderingFitsBudget()) {
                System.err.println("In makePartition wurde Budget oder SortedSells falsch berechnet. Passt nicht zusammen");
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
    ArrayList<Integer> getRandomIntArrayList(int min, int max, int size) {
        ArrayList<Integer> randomIntArrayList = new ArrayList<>();

        int toogle = 0;
        Integer te;

        for (int it = 0; it < size; it++) {
            te = new Integer(ThreadLocalRandom.current().nextInt(min, max + 1));
            randomIntArrayList.add(te);

//           
        }
        System.out.println(" Random IntArray :" + randomIntArrayList);
        return randomIntArrayList;
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
    public ArrayList<MyInteger> getRandomMyIntArray(int min, int max, int size) {

        ArrayList<MyInteger> randomMyIntArrayList = new ArrayList<>();

        ArrayList<Integer> randomIntArrayList = getRandomIntArrayList(min, max, size);
//        randomIntArrayList.set((size / 4) * 2 + 1, 100);/*((size/4)*2+1,max);*/ //todo: ich mache hier einen lokal großen Gewinn
        int toogle = 0;
        for (int it = 0; it < randomIntArrayList.size(); it++) {
            if (toogle == 0) {

                MyInteger te = new MyInteger(randomIntArrayList.get(it));//todo: so werden die geraden, also die Sells größer
                randomMyIntArrayList.add(te);

                toogle++;
            } else {
                MyInteger te = new MyInteger(randomIntArrayList.get(it));//todo: so werden die ungeraden, also die Boughts größer
                randomMyIntArrayList.add(te);

                toogle--;
            }

        }
        return randomMyIntArrayList;
    }

    public ArrayList<MyInteger> getDeterministicMyIntArray() {
//        int[] deterministicIntArrayList = {2, 4, 1, 3, 5, 2};;

        int[] deterministicIntArrayList = {15, 28, 60, 38, 49, 49, 16, 49, 16, 40, 19, 25};;

        ArrayList<MyInteger> deterministicMyIntArrayList = new ArrayList<>();

        int it = 0;

        while (it < deterministicIntArrayList.length) {
            MyInteger te = new MyInteger(deterministicIntArrayList[it]);
            deterministicMyIntArrayList.add(te);
            it++;
        }

        return deterministicMyIntArrayList;
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

        ArrayList<Integer> numbers = getRandomIntArrayList(1, 100, 1);
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

        ArrayList<Integer> numbers = getRandomIntArrayList(1, 100, 1);
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
    public Partition getRandomPartitionDueToProbality(ArrayList<Partition> partitions) {
        Partition partition = new Partition();
        int sumProb = 0;
        for (int y = 0; (y < partitions.size()); y++) {
            sumProb = sumProb + partitions.get(y).probability;
        }
        Integer sum = 0;

        for (int y = 0; (y < partitions.size()); y++) {

            ArrayList<Integer> numbers = getRandomIntArrayList(1, sumProb, 1);//Wieso geht kein einfacher MyInteger
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
                    System.err.println("error");

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
    public ArrayList<MyInteger> makeSortedSellsJoin(ArrayList<MyInteger> s1, ArrayList<MyInteger> s2) {
        ArrayList<MyInteger> s = new ArrayList<>();
        s.addAll(s2);
        s.addAll(s1);
        return s;

    }

    public Integer makeBudgetJoin(Partition b1, Partition b2) {
        Integer budget = Integer.min(b2.budget, b2.balance + b1.budget);

        return budget;

    }

    public Integer makeBalanceJoin(Partition b1, Partition b2) {
        Integer balance = b1.balance + b2.balance;

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
        ArrayList<MyInteger> sRest = new ArrayList<>();
        sRest.addAll(p.sortedSells);
        System.out.println("SRest: " + sRest.toString());
        Integer count = 0;
        for (int i = 0; i < sRest.size(); i++) {
            ArrayList<MyInteger> SellsForCurrentPositiveSet = new ArrayList<>();
            // berechne balance bis i
            //Eigentlich sollte man schon aufhören wenn es positiv ist oder?
            p.setValueOfBalanceBoughtsBudgetOfSet(i);
            count++;
            BalanceBoughtsBudget currentBalanceBoughtsBudget;
            currentBalanceBoughtsBudget = p.balanceBoughtsBudgetOfSetUpToIndex.get(i);// Index 0???
            int balance = currentBalanceBoughtsBudget.getBalance();
            int sumBoughts = currentBalanceBoughtsBudget.getBoughts();
            int budget = currentBalanceBoughtsBudget.getBudget();
            //sobald es größer als Null ist wird der Index und dieSumme der Boughts in PositiveSetsPLengths und PositiveSetsPLengthsSumBoughts gespeichert
            if (balance > 0) {
                MySet newPositiveSet = new MySet(sumBoughts, count, budget, balance);
                p.positiveSets.add(newPositiveSet);
                count = 0;

                System.out.println("PositiveSetsPLengths: " + "PositiveSetsPLengthsSumBoughts: " + p.balanceBoughtsBudgetOfSetUpToIndex.toString());//[] ist richtig
//                System.out.println("PositiveSetsPLengthsSumBoughts: " + p.positiveSetsPLengthsSumBoughts.toString());//[0]ist richtig

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
    public void makeSortedSellsUnionAndBudgetAndBalance(Partition p, Partition p1, Partition p2) {

        //hier musst du mit i Lists of PositiveSets und Resten arbeiten
        Integer budget = 0;
        Integer balance = 0;

        ArrayList<MyInteger> newSortedSells = new ArrayList<>();
        ArrayList<Eintrag> currentWerteP1 = new ArrayList<>();
        currentWerteP1 = (ArrayList<Eintrag>) p1.getWerte().clone();

        ArrayList<Eintrag> currentWerteP2 = new ArrayList<>();
        currentWerteP2 = (ArrayList<Eintrag>) p2.getWerte().clone();

        ArrayList<MyInteger> s1Rest = new ArrayList<>();
        s1Rest.addAll(p1.sortedSells);

        ArrayList<MyInteger> s2Rest = new ArrayList<>();
        s2Rest.addAll(p2.sortedSells);

        fillPositiveSets(p1);
        System.out.println("PositiveSets 1 : " + p1.positiveSets.toString());//[] ist richtig
//        System.out.println( p1.positiveSetsPLengthsSumBoughts.toString());//[]ist richtig

        fillPositiveSets(p2);

        System.out.println("PositiveSets 2 : " + p2.positiveSets.toString());//[] ist richtig

//        
//        Iterator it1 = PositiveSetsPLengths1Lengths.iterator();
//                 Iterator it2 = PositiveSetsPLengths2Lengths.iterator();
//          
        //jetzt wird gemergt
        while ((!p1.positiveSets.isEmpty()) && (!p2.positiveSets.isEmpty())) {
//ArrayList<MyInteger> testSortedSells= new ArrayList<>();
            if (p1.positiveSets.get(0).getLength() < p2.positiveSets.get(0).getLength()) {

                for (int countP1 = 0; countP1 < p1.positiveSets.get(0).getLength(); countP1++) {
//                    testSortedSells.add(s1Rest.get(0));
                    System.out.println("newSortedSells.add: " + s1Rest.get(0));

                    newSortedSells.add(s1Rest.get(0));//index 0 size 0
                    currentWerteP1 = shift(currentWerteP1, p1.positiveSets.get(0).getBalance());

                    s1Rest.remove(0);
//                    System.out.println("S1Rest: " + s1Rest.toString());

                }

                //ASSERTION
                //------------------------------------
                budget = Integer.min(budget, balance + ((p1.positiveSets).get(0)).getLength());//balanceBoughtsBudgetOfSetUpToIndex.get(p1.positiveSetsPLengths.get(0)).get(2));
                System.out.println("budget " + budget);
                balance = balance + ((p1.positiveSets).get(0)).getBalance();//p1.balanceBoughtsBudgetOfSetUpToIndex.get(p1.positiveSetsPLengths.get(0)).get(0);
                System.out.println("balance " + balance);

                (p1.positiveSets).remove(0);

            } else {
                for (int countP2 = 0; countP2 < p2.positiveSets.get(0).getLength(); countP2++) {
                    System.out.println("newSortedSells.add: " + s2Rest.get(0));

                    newSortedSells.add(s2Rest.get(0));//s2Rest schon leer, warum?
                    currentWerteP2 = shift(currentWerteP2, p2.positiveSets.get(0).getBalance());
                    s2Rest.remove(0);
                    System.out.println("S2Rest: " + s2Rest.toString());

                }
                budget = Integer.min(budget, balance + ((p2.positiveSets).get(0)).getBudget());//balanceBoughtsBudgetOfSetUpToIndex.get(p1.positiveSetsPLengths.get(0)).get(2));
                System.out.println("budget " + budget);
                balance = balance + ((p2.positiveSets).get(0)).getBalance();//p2.balanceBoughtsBudgetOfSetUpToIndex.get(p2.positiveSetsPLengths.get(0)).get(0);
                System.out.println("balance " + balance);

                (p2.positiveSets).remove(0);

            }

        }
        while (!(p1.positiveSets.isEmpty())) {

            for (int countP1 = 0; countP1 < p1.positiveSets.get(0).getLength(); countP1++) {
//                    testSortedSells.add(s1Rest.get(0));
                System.out.println("newSortedSells.add: " + s1Rest.get(0));

                newSortedSells.add(s1Rest.get(0));//index 0 size 0
                currentWerteP1 = shift(currentWerteP1, p1.positiveSets.get(0).getBalance());

                s1Rest.remove(0);
//                    System.out.println("S1Rest: " + s1Rest.toString());

            }

            //ASSERTION
            //------------------------------------
            budget = Integer.min(budget, balance + ((p1.positiveSets).get(0)).getBudget());//balanceBoughtsBudgetOfSetUpToIndex.get(p1.positiveSetsPLengths.get(0)).get(2));
            System.out.println("budget " + budget);
            balance = balance + ((p1.positiveSets).get(0)).getBalance();//p1.balanceBoughtsBudgetOfSetUpToIndex.get(p1.positiveSetsPLengths.get(0)).get(0);
            System.out.println("balance " + balance);

            (p1.positiveSets).remove(0);
        }
        while (!(p2.positiveSets.isEmpty())) {
            for (int countP2 = 0; countP2 < p2.positiveSets.get(0).getLength(); countP2++) {
                System.out.println("newSortedSells.add: " + s2Rest.get(0));

                newSortedSells.add(s2Rest.get(0));//s2Rest schon leer, warum?
                currentWerteP2 = shift(currentWerteP2, p2.positiveSets.get(0).getBalance());

                s2Rest.remove(0);
//                System.out.println("S2Rest: " + s2Rest.toString());

            }
            budget = Integer.min(budget, balance + ((p2.positiveSets).get(0)).getBudget());//balanceBoughtsBudgetOfSetUpToIndex.get(p1.positiveSetsPLengths.get(0)).get(2));
            System.out.println("budget " + budget);
            balance = balance + ((p2.positiveSets).get(0)).getBalance();//p2.balanceBoughtsBudgetOfSetUpToIndex.get(p2.positiveSetsPLengths.get(0)).get(0);
            System.out.println("balance " + balance);

            (p2.positiveSets).remove(0);
        }

        // TODO: Eigentlich wird jetzt noch unterschieden ob max von min von...
        Integer budget1 = p1.balanceBoughtsBudgetOfSetUpToIndex.get(p1.balanceBoughtsBudgetOfSetUpToIndex.size() - 1).getBudget();
        Integer budget2 = p2.balanceBoughtsBudgetOfSetUpToIndex.get(p2.balanceBoughtsBudgetOfSetUpToIndex.size() - 1).getBudget();
        Integer balance1 = p1.balanceBoughtsBudgetOfSetUpToIndex.get(p1.balanceBoughtsBudgetOfSetUpToIndex.size() - 1).getBalance();
        Integer balance2 = p2.balanceBoughtsBudgetOfSetUpToIndex.get(p2.balanceBoughtsBudgetOfSetUpToIndex.size() - 1).getBalance();

        if (Integer.min(budget2, balance2 + budget1) < Integer.min(budget1, balance1 + budget2)) {
            newSortedSells.addAll(s1Rest);
            budget = Integer.min(balance + budget1, budget);
            balance = balance + balance1;
            newSortedSells.addAll(s2Rest);
            budget = Integer.min(balance + budget2, budget);
            balance = balance + balance2;
        } else {
            newSortedSells.addAll(s2Rest);
            budget = Integer.min(balance + budget2, budget);
            balance = balance + balance2;
            newSortedSells.addAll(s1Rest);
            budget = Integer.min(balance + budget1, budget);
            balance = balance + balance1;
        }

//        System.out.println("newSortedSells: " + newSortedSells.toString());
        //--------------
        p.sortedSells = newSortedSells;
        p.balance = balance;
        System.out.println("BUDGET:               ,       " + budget);
        p.budget = budget;

    }

    static java.util.List<java.util.List<MyInteger>> permute(java.util.List<MyInteger> arr, int k) {
        java.util.List<java.util.List<MyInteger>> returnList = new ArrayList<>();
        for (int i = k; i < arr.size(); i++) {
            java.util.Collections.swap(arr, i, k);
            permute(arr, k + 1);
            java.util.Collections.swap(arr, k, i);
        }
        if (k == arr.size() - 1) {
//            System.out.println(java.util.Arrays.toString(arr.toArray()));
            returnList.add(arr);
        }
        return returnList;
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
    public ArrayList<ArrayList<MyInteger>> makeArrayListJoin(Partition p1, Partition p2) {

        ArrayList<ArrayList<MyInteger>> a = new ArrayList<>();
        a.addAll(p1.arrayList);
        ArrayList<MyInteger> toLink = new ArrayList<>();
        for (int k = 0; k < p2.arrayList.size(); k++) {
            toLink.addAll(p2.getBoughtsOfSell(p2.arrayList.get(k).get(0)));
        }
//        Iterator ita2 = a2.iterator();
//
//        while (ita2.hasNext()) {
////die Boughts vom nächsten Eintrag aus A2 die noch nicht verlinkt wurden
//            ArrayList<MyInteger> tempo = (ArrayList<MyInteger>) ita2.next();
//            MyInteger zero = tempo.remove(0);
//
//            toLink.addAll(tempo);
//            tempo.add(0, zero);
        Set<MyInteger> hs = new HashSet<>();
        hs.addAll(toLink);
        toLink.clear();
        toLink.addAll(hs);
//
//        }
        for (int k = 0; k < a.size(); k++) {
            a.get(k).addAll(toLink);
        }

        a.addAll(p2.arrayList);
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

        partition.arrayList = makeArrayListUnion(p1.arrayList, p2.arrayList);//What ich übergebe was size 2 und danach hat es size 0????

        makeSortedSellsUnionAndBudgetAndBalance(partition, p1, p2);

        //ASSERTION
        if (!partition.orderingFitsBudget()) {
            System.err.println("In PartitionUnion wurde das Budget (oder die SortedSells) nicht richtig berechnet.");
        }
//        if (! partition.isBestOrdering())
//        {
//            System.err.println("In PartitionUnion wurden die SortedSells nicht richtig berechnet.");}
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
    public ArrayList<ArrayList<MyInteger>> makeArrayListUnion(ArrayList<ArrayList<MyInteger>> a1, ArrayList<ArrayList<MyInteger>> a2) {
        ArrayList<ArrayList<MyInteger>> a = new ArrayList<>();
        a.addAll(a1);
        a.addAll(a2);
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
        ArrayList<ArrayList<MyInteger>> newArrayList = makeArrayListJoin(p1, p2);
        ArrayList<MyInteger> newSortedSells = makeSortedSellsJoin(p1.sortedSells, p2.sortedSells);//p2.sorted sells null
        Integer newBudget = makeBudgetJoin(p1, p2);
        Integer newBalance = makeBalanceJoin(p1, p2);
        partition = new Partition(newArrayList, newSortedSells);
        partition.budget = newBudget;
        partition.balance = newBalance;
//        System.out.println(p.toString());
// todo:  Fehler hier?
        //        partition.getCalculatedGraphOfSortedSells().addWerte(p2.getCalculatedGraphOfSortedSells().getWerte());
//        ArrayList<Eintrag> shiftList = shift(p1.getCalculatedGraphOfSortedSells().getWerte(),p2.balance);
//        partition.getCalculatedGraphOfSortedSells().addWerte(shiftList);

        partition.werte = new ArrayList<>();
        partition.werte.addAll(p2.getWerte());
        ArrayList<Eintrag> shiftList = shift(p1.getWerte(), p2.balance);
        partition.werte.addAll(shiftList);
        //ASSERTION
        if (!partition.orderingFitsBudget()) {
            System.err.println("In PartitionUnion wurde das Budget (oder die SortedSells) nicht richtig berechnet.");
        }
//        if (! partition.isBestOrdering())
//        {
//            System.err.println("In PartitionUnion wurden die SortedSells nicht richtig berechnet.");}
//--------------------------------
        return partition;
    }

    public Integer getSum(ArrayList<MyInteger> newB) {

        Integer newValue = 0;
        for (int j = 0; j < newB.size(); j++) {
            newValue = newValue + newB.get(j).i;

        }
        return newValue;
    }

    ArrayList<Eintrag> shift(ArrayList<Eintrag> list, Integer shiftValue) {
        ArrayList<Eintrag> newList = (ArrayList<Eintrag>) list.clone();

        for (int i = 0; i < newList.size(); i++) {
            newList.get(i).value = newList.get(i).value + shiftValue;
        }

        return newList;
    }

    public void outStatistikN(String dateiname) {

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
            int pool = 10;
            for (int i = 2; i < 50; i += 10) {// todo: kein +10 ??????
                double mittelwertSwap = 0;
                double mittelwertSortedSells = 0;
                prX.println(i);

                for (int j = 0; j < pool; j++) {

                    instance = buildInstance(1, 50, i);

                    Graph newGraph = new Graph(instance, randomOrdering);
                    newGraph = getGraphHeuristik(newGraph, "swap");

//                prY.println(sumOfBoughts/instance.minBudgetSwap);
                    mittelwertSortedSells = mittelwertSortedSells + instance.budget;
                    mittelwertSwap = mittelwertSwap + instance.minBudgetSwap;
//                    System.err.println("test" + instance.minBudgetSwap);
                }
                mittelwertSwap = mittelwertSwap / pool;
                mittelwertSortedSells = mittelwertSortedSells / pool;
                prY.println(mittelwertSortedSells / mittelwertSwap);// todo: hier können wir Wert für Statistik ändern
//                System.out.println("yEintrag : " + sumOfBoughts/instance.minBudgetSwap);

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

            for (int j = 0; j < currentGraph.getWerte().size(); j++) {

                pr.println(currentGraph.getWerte().get(j).value);
            }

            pr.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No such file exists.");
        }

    }

    public Graph getGraphHeuristik(Graph currentGraph, String update) {
        System.out.println("nochmal der Graph nach Heuristik : Budget " + currentGraph.getMinBudget());

        ArrayList<MyInteger> ordering = new ArrayList<>();
        ordering.addAll(currentGraph.getOrdering());
        System.out.println("Graph mit SortedSells : Budget " + currentGraph.getPartition().budget);
        for (int i = 0; i < ordering.size(); i++) {
            for (int j = i + 1; j < ordering.size(); j++) {
                ArrayList<MyInteger> newOrdering = new ArrayList<>();

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

    public ArrayList<MyInteger> swap(int i, int j, ArrayList<MyInteger> ordering) {
        ArrayList<MyInteger> newOrdering = new ArrayList<>();
        newOrdering.addAll(ordering);

        newOrdering.set(i, ordering.get(j));
        newOrdering.set(j, ordering.get(i));
        return newOrdering;
    }

    public Partition buildInstance(int min, int max, int size) {

        ArrayList<Partition> partitions = makeBasicPartitions(min, max, size);
        buildInstanceOnBasicPartitions(partitions, 33, 33, 34);

        Partition instance = partitions.get(0);
        System.out.println("Instance is build.");
        return instance;
    }

    public ArrayList<MyInteger> changeOrder(int i, int j, ArrayList<MyInteger> ordering) {
        ArrayList<MyInteger> newOrdering = new ArrayList<>();
        newOrdering.addAll(ordering);
        System.out.println("newOrdering:" + newOrdering);
        List<MyInteger> part = new ArrayList<>();
        for (int k = i; k < j + 1; k++) {
            part.add(newOrdering.get(k));
        }
        System.out.println("part:" + part);
//ArrayList.reverse(part);
        Collections.reverse(part);
        System.out.println("part:" + part);

        for (int k = 0; k < part.size(); k++) {
            newOrdering.set(k + i, part.get(k));
        }
        System.out.println("newOrdering:" + newOrdering);

        return newOrdering;
    }

    public ArrayList<MyInteger> getOrderingOfBasicPartitions(ArrayList<Partition> basicPartitions) {

        Iterator it = basicPartitions.iterator();
        ArrayList<MyInteger> randomOrdering = new ArrayList<>();
        for (int k = 0; k < basicPartitions.size(); k++) {
            randomOrdering.add((basicPartitions.get(k).arrayList.get(0)).get(0));
        }
        return randomOrdering;
    }

    public Integer getSumOfBoughts(ArrayList<Partition> basicPartitions) {
        Iterator it = basicPartitions.iterator();
        Integer sum = 0;
        for (int k = 0; k < basicPartitions.size(); k++) {
            sum = sum + ((basicPartitions.get(k).arrayList.get(0)).get(1)).i;
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

    public void superFunctionStatistik(int min, int max, int size) {
        // todo: min, max, size muss noch übergeben werden an outStatitikN

        outStatistikN("test");

    }

    public void addPositiveSet() {
    }

    ArrayList getArrayAbschnitt(ArrayList list, int i, int j) {

        ArrayList abschnitt = new ArrayList<>();
        for (int k = i; k < j + 1; k++) {

            abschnitt.add(list.get(k));

        }
        return abschnitt;
    }
}
