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
import java.util.HashSet;
import java.util.Iterator;
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
    public ArrayList<Partition> makeBasicPartitions() {
//        ArrayList<MyInteger> randomIntArrayList = this.getDeterministicIntArray();

        ArrayList<MyInteger> randomIntArrayList = this.getRandomIntArray(1, 20, 32);
        System.out.println(randomIntArrayList.toString());
        File file = new File("C:\\Users\\Soyo\\Desktop\\Bachelorarbeit\\Randoms.txt");
//        File file = new File("X:\\speedee\\mitarbeiter\\sonja_schäfer\\Bachelorarbeit\\Randoms.txt");

        try {
//            file.mkdirs();
            file.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            PrintWriter pr = new PrintWriter(file);

            for (int i = 0; i < randomIntArrayList.size(); i++) {
                pr.println(randomIntArrayList.get(i).i);
            }
            pr.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No such file exists.");
        }
//        ArrayList<MyInteger> randomIntArrayList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        ArrayList<Partition> partitions = new ArrayList<>();
        MyInteger currentRandom;
        while (randomIntArrayList.size() > 1) { //der letzte wird eventuel abgeschnitten
            Partition partition1 = new Partition();
            currentRandom = randomIntArrayList.get(0);

            System.out.println("Aktueller Sell :" + currentRandom);
            randomIntArrayList.remove(0);
            ArrayList<MyInteger> l = new ArrayList<>();
            l.add(currentRandom);

            partition1.arrayList.add(l);
            partition1.sortedSells.add(currentRandom);

            partition1.balance = currentRandom.i;

            partitions.add(partition1);
// todo: testen
            currentRandom = randomIntArrayList.get(0);

            System.out.println("Aktueller Bought : " + currentRandom);
            partitions.get(partitions.size() - 1).arrayList.get(0).add(currentRandom);
            partitions.get(partitions.size() - 1).balance = partitions.get(partitions.size() - 1).balance - currentRandom.i;
            partitions.get(partitions.size() - 1).budget = partitions.get(partitions.size() - 1).budget - currentRandom.i;
            randomIntArrayList.remove(0);

            //-------------------------------------
//                toogle--;
        }
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
    public void buildInstance(ArrayList<Partition> partitions, int unionProbability, int leftJoinProbability, int rightJoinProbability) {

        while (partitions.size() > 1) {
            Partition partition = new Partition();
//            Integer indi = 0;
            Partition partitionA = new Partition();
            partitionA = this.getRandomPartitionDueToProbality(partitions);
//            partitionA = partitions.get(indi);
//            indi++;
            System.out.println("partitionA: " + partitionA.toString() + "\n");

            Partition partitionB = new Partition();
            partitionB = this.getRandomPartitionDueToProbality(partitions);
//            partitionB = partitions.get(indi);
            System.out.println("partitionB: " + partitionB.toString() + "\n");

            while (partitionA == partitionB) {
                partitionB = this.getRandomPartitionDueToProbality(partitions);
            }
            partition = this.makePartition(partitionA, partitionB, unionProbability, leftJoinProbability, rightJoinProbability);//size 0 ???
            System.out.println("MergedPartition: " + partition.toString() + "\n");
            System.out.println("Sorted SellsOfMergedPartition: " + partition.sortedSells.toString() + "\n");

            int ind = partitions.indexOf(partitionA);
            partitions.set(ind, partition);//ind -1
            partitions.remove(partitionB);

        }
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
    public ArrayList<MyInteger> getRandomIntArray(int min, int max, int size) {
        ArrayList<Integer> randomIntArrayList = new ArrayList<>();
        ArrayList<MyInteger> randomMyIntArrayList = new ArrayList<>();

//ersmal Integer Array
        int it = 0;
        while (it < size) {
            Integer te = new Integer(ThreadLocalRandom.current().nextInt(min, max + 1));
            randomIntArrayList.add(te);
            it++;
        }
//dann MyInteger Array
        it = 0;
        while (it < randomIntArrayList.size()) {
            MyInteger te = new MyInteger(randomIntArrayList.get(it));
            randomMyIntArrayList.add(te);
            it++;
        }

        return randomMyIntArrayList;
    }

    public ArrayList<MyInteger> getDeterministicIntArray() {
//        int[] deterministicIntArrayList = {2, 4, 1, 3, 5, 2};;

        int[] deterministicIntArrayList = {30, 15, 80, 77, 81, 23, 31, 11, 93, 56, 14, 10, 54, 31, 1, 1, 45, 29, 100, 72, 88, 30, 16, 2, 53, 15, 62, 27, 68, 44};;
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

        ArrayList<MyInteger> numbers = getRandomIntArray(1, 100, 1);
        if (numbers.get(0).i < unionProbability) {
            return "union";
        } else {
            if (numbers.get(0).i < (unionProbability + leftJoinProbability)) {
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

        ArrayList<MyInteger> numbers = getRandomIntArray(1, 100, 1);
        if (numbers.get(0).i < boughtProbability) {
            return "bought";
        } else {
            return "sell";
        }
    }

    /**
     * @see buildInstance
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

            ArrayList<MyInteger> numbers = getRandomIntArray(1, sumProb, 1);//Wieso geht kein einfacher MyInteger
            MyInteger number = numbers.get(0);
            System.out.println(number.toString() + "<");
            sum = sum + partitions.get(y).probability;
            System.out.println(sum.toString());

            if (number.i < sum + 1) {
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
        partition.probability=p1.probability+p2.probability;
        String choice = this.getChoice(unionProbability, leftJoinProbability, rightJoinProbability);
//        String choice = new String("");
//        if (toogle.equals(0)) {
//            choice = "rightJoin";
//            toogle++;
//        } else {
//            if (toogle.equals(1)) {
//                choice = "leftJoin";
//                toogle++;
//            } else {
//                if (toogle.equals(2)) {
//                    choice = "union";
//                    toogle = toogle - 2;
//                }
//            }
//        }
        System.out.println(choice);
        if (choice.equals("union")) {
            partition = makePartitionUnion(p1, p2,partition);

        } else {
            if (choice.equals("rightJoin")) {
                partition = makePartitionJoin(p1, p2,partition);

            } else {
                if (choice.equals("leftJoin")) {
                    partition = makePartitionJoin(p2, p1,partition);

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
     * PositiveSets, wenn ein positive minimal Set gefunden ist, werden alle
     * Einträge von balanceBoughtsBudgetOfSetUpToIndex bis zu diesem Index auf 0
     * gesetzt,und
     *
     * @param p Partition
     * @param PositiveSetsPLengths PositiveSets die wir für diesen UNION Merge
     * der SortedSells bracuhen
     * @param PositiveSetsPLengthsSumBoughts Summe der Boughts die wir für die
     * PositiveSets brauchen
     */
    public void fillPositiveSetsAndPositiveSetsBoughts(Partition p) {
        /**
         * wir wollen die PositiveSets Lengths und die benötigten Einkaufssummen
         */

        /**
         *
         *
         * noch nicht abgearbeiteter Rest der Liste SortedSells
         */
        ArrayList<MyInteger> s1Rest = new ArrayList<>();
        s1Rest.addAll(p.sortedSells);
        System.out.println("S1Rest: " + s1Rest.toString());
        Integer count = 0;
        for (int i = 0; i < s1Rest.size(); i++) {
            // berechne balance bis i
            //Eigentlich sollte man schon aufhören wenn es positiv ist oder?
            p.setValueOfBalanceBoughtsBudgetOfSet(i);
            count++;
            int balance = p.balanceBoughtsBudgetOfSetUpToIndex.get(i).get(0);
            int sumBoughts = p.balanceBoughtsBudgetOfSetUpToIndex.get(i).get(1);
            int budget = p.balanceBoughtsBudgetOfSetUpToIndex.get(i).get(2);
            //sobald es größer als Null ist wird der Index und dieSumme der Boughts in PositiveSetsPLengths und PositiveSetsPLengthsSumBoughts gespeichert
            if (balance > 0) {

                p.positiveSetsPLengths.add(count);
                count = 0;
                p.positiveSetsPLengthsSumBoughts.add(sumBoughts);
                p.positiveSetsBudgets.add(budget);
                p.positiveSetsBalances.add(balance);
                System.out.println("PositiveSetsPLengths: " + p.positiveSetsPLengths.toString());//[] ist richtig
                System.out.println("PositiveSetsPLengthsSumBoughts: " + p.positiveSetsPLengthsSumBoughts.toString());//[0]ist richtig

                //hier will ich alle vorher zurücksetzten balance aber eigentlich auch bought
                for (int k = 0; k < i + 1; k++) {

                    p.balanceBoughtsBudgetOfSetUpToIndex.get(k).set(0, 0);//index 1 size 1!!!!!!!!!!!!!!!!!!!
                    p.balanceBoughtsBudgetOfSetUpToIndex.get(k).set(1, 0);
                    p.balanceBoughtsBudgetOfSetUpToIndex.get(k).set(2, 0);
                }
            }

        }
    }

    public void BudgetAndBalanceAfterNewPositiveSet(Partition p, Partition p1) {
        p.budget = Integer.min(p.budget, p.balance + p1.positiveSetsBudgets.get(0));//balanceBoughtsBudgetOfSetUpToIndex.get(p1.positiveSetsPLengths.get(0)).get(2));
        p.balance = p.balance + p1.positiveSetsBalances.get(0);//p1.balanceBoughtsBudgetOfSetUpToIndex.get(p1.positiveSetsPLengths.get(0)).get(0);
        p1.positiveSetsPLengths.remove(0);
        p1.positiveSetsBudgets.remove(0);
        p1.positiveSetsBalances.remove(0);
        p1.positiveSetsPLengthsSumBoughts.remove(0);

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
        ArrayList<Integer> budgets = new ArrayList<>();
        ArrayList<Integer> balances = new ArrayList<>();

        ArrayList<MyInteger> newSortedSells = new ArrayList<>();

        ArrayList<MyInteger> s1Rest = new ArrayList<>();
        s1Rest.addAll(p1.sortedSells);

        ArrayList<MyInteger> s2Rest = new ArrayList<>();
        s2Rest.addAll(p2.sortedSells);

        fillPositiveSetsAndPositiveSetsBoughts(p1);
        System.out.println("PositiveSetsPLengths1Lengths: " + p1.positiveSetsPLengths.toString());//[] ist richtig
        System.out.println("PositiveSetsPLengths1LengthsSumBoughts: " + p1.positiveSetsPLengthsSumBoughts.toString());//[]ist richtig

        fillPositiveSetsAndPositiveSetsBoughts(p2);

        System.out.println("PositiveSetsPLengths2Lengths: " + p2.positiveSetsPLengths.toString());//[0]ist richtig
        System.out.println("PositiveSetsPLengths2LengthsSumBoughts: " + p2.positiveSetsPLengthsSumBoughts.toString());//[0]ist richtig

//        
//        Iterator it1 = PositiveSetsPLengths1Lengths.iterator();
//                 Iterator it2 = PositiveSetsPLengths2Lengths.iterator();
//          
        //jetzt wird gemergt
        while ((!p1.positiveSetsPLengths.isEmpty()) && (!p2.positiveSetsPLengths.isEmpty())) {

            if (p1.positiveSetsPLengthsSumBoughts.get(0) < p2.positiveSetsPLengthsSumBoughts.get(0)) {
                for (int countP1 = 0; countP1 < p1.positiveSetsPLengths.get(0); countP1++) {
                    newSortedSells.add(s1Rest.get(0));//index 0 size 0
                    s1Rest.remove(0);
                    System.out.println("S1Rest: " + s1Rest.toString());

                }
                BudgetAndBalanceAfterNewPositiveSet(p, p1);

            } else {
                for (int countP2 = 0; countP2 < p2.positiveSetsPLengths.get(0); countP2++) {
                    newSortedSells.add(s2Rest.get(0));//s2Rest schon leer, warum?
                    s2Rest.remove(0);
                    System.out.println("S2Rest: " + s2Rest.toString());

                }
                BudgetAndBalanceAfterNewPositiveSet(p, p2);

            }

        }

        // TODO: Eigentlich wird jetzt noch unterschieden ob max von min von...
        Integer budget2 = p2.balanceBoughtsBudgetOfSetUpToIndex.get(p2.balanceBoughtsBudgetOfSetUpToIndex.size() - 1).get(2);
        Integer budget1 = p1.balanceBoughtsBudgetOfSetUpToIndex.get(p1.balanceBoughtsBudgetOfSetUpToIndex.size() - 1).get(2);
        Integer balance1 = p1.balanceBoughtsBudgetOfSetUpToIndex.get(p1.balanceBoughtsBudgetOfSetUpToIndex.size() - 1).get(0);;
        Integer balance2 = p2.balanceBoughtsBudgetOfSetUpToIndex.get(p2.balanceBoughtsBudgetOfSetUpToIndex.size() - 1).get(0);

        if (Integer.max(budget2, balance2 + budget1) > Integer.max(budget1, balance1 + budget2)) {
            newSortedSells.addAll(s1Rest);
            p.budget = Integer.min(p.balance + budget1, p.budget);
            p.balance = p.balance + balance1;
            newSortedSells.addAll(s2Rest);
            p.budget = Integer.min(p.balance + budget2, p.budget);
            p.balance = p.balance + balance2;
        } else {
            newSortedSells.addAll(s2Rest);
            p.budget = Integer.min(p.balance + budget2, p.budget);
            p.balance = p.balance + balance2;
            newSortedSells.addAll(s1Rest);
            p.budget = Integer.min(p.balance + budget1, p.budget);
            p.balance = p.balance + balance1;
        }

        System.out.println("newSortedSells: " + newSortedSells.toString());
        //--------------

        p.sortedSells = newSortedSells;

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
    public ArrayList<ArrayList<MyInteger>> makeArrayListJoin(ArrayList<ArrayList<MyInteger>> a1, ArrayList<ArrayList<MyInteger>> a2) {

        ArrayList<ArrayList<MyInteger>> a = new ArrayList<>();
        a.addAll(a1);
        ArrayList<MyInteger> toLink = new ArrayList<>();

        Iterator ita2 = a2.iterator();

        while (ita2.hasNext()) {
//die Boughts vom nächsten Eintrag aus A2 die noch nicht verlinkt wurden
            ArrayList<MyInteger> tempo = (ArrayList<MyInteger>) ita2.next();
            MyInteger zero = tempo.remove(0);

            toLink.addAll(tempo);
            tempo.add(0, zero);
            Set<MyInteger> hs = new HashSet<>();
            hs.addAll(toLink);
            toLink.clear();
            toLink.addAll(hs);

        }
        Iterator ita1 = a1.iterator();

        while (ita1.hasNext()) {
            ArrayList<MyInteger> nextitera1 = ((ArrayList<MyInteger>) ita1.next());
            nextitera1.addAll(toLink);

//
//        ArrayList<MyInteger> allreadyJoinedBoughts = new ArrayList<>(); // kein Nullpinter oder?
//        while (ita2.hasNext()) {
////die Boughts vom nächsten Eintrag aus A2 die noch nicht verlinkt wurden
//            ArrayList<MyInteger> toLink = new ArrayList<>();
//            toLink.addAll((ArrayList<MyInteger>) ita2.next());
//            toLink.remove(0);
//            toLink.removeAll(allreadyJoinedBoughts);
//            allreadyJoinedBoughts.addAll(toLink);
//
//        }
//        Iterator ita1 = a1.iterator();
//
//        while (ita1.hasNext()) {
//            ArrayList<MyInteger> itera1 = ((ArrayList<MyInteger>) ita1.next());
//            itera1.addAll(toLink);
        }
        a.addAll(a2);
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
    public Partition makePartitionUnion(Partition p1, Partition p2,Partition p) {
       

        p.arrayList = makeArrayListUnion(p1.arrayList, p2.arrayList);//What ich übergebe was size 2 und danach hat es size 0????
        makeSortedSellsUnionAndBudgetAndBalance(p, p1, p2);

        return p;
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
    public Partition makePartitionJoin(Partition p1, Partition p2,Partition p) {
       

        p.arrayList = makeArrayListJoin(p1.arrayList, p2.arrayList);
        p.sortedSells = makeSortedSellsJoin(p1.sortedSells, p2.sortedSells);//p2.sorted sells null
        p.budget = makeBudgetJoin(p1, p2);
        p.balance = makeBalanceJoin(p1, p2);
//        System.out.println(p.toString());
        return p;
    }

    public Integer getSum(ArrayList<MyInteger> newB) {

        Integer newValue = 0;
        for (int j = 0; j < newB.size(); j++) {
            newValue = newValue + newB.get(j).i;

        }
        return newValue;
    }

    public Integer getMinBudget(Partition p, ArrayList<MyInteger> ordering) {
        Integer budget = 0;
        Integer newValue;
        Integer bilanz = 0;
        ArrayList<MyInteger> allready = new ArrayList<>();

        for (int i = 0; i < ordering.size(); i++) {

            ArrayList<MyInteger> newB = new ArrayList<>();
            newB.addAll(p.getBoughtsOfSell(ordering.get(i)));

            newB.removeAll(allready);
            allready.addAll(newB);

            budget = Integer.min(budget, bilanz - getSum(newB));

            bilanz = bilanz - getSum(newB) + ordering.get(i).i;

        }
        return budget;
    }

    public void out(Partition p, ArrayList<MyInteger> ordering, String dateiname) {
        ArrayList<MyInteger> allready = new ArrayList<>();
        File file2 = new File("C:\\Users\\Soyo\\Desktop\\Bachelorarbeit\\Daten\\" + dateiname + "Daten.txt");
//        File file2 = new File("X:\\speedee\\mitarbeiter\\sonja_schäfer\\Bachelorarbeit\\SortedSellsInstance.txt");
        try {
//            file.mkdirs();
            file2.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        ArrayList<Integer> werte = new ArrayList<>();
        werte.add(0, 0);

        try {
            PrintWriter pr = new PrintWriter(file2);
            pr.println(0);
            Integer newValue;

            for (int i = 0; i < ordering.size(); i++) {
                ArrayList<MyInteger> newB = p.getBoughtsOfSell(ordering.get(i));
//                System.out.println("newB    :   " + newB);
                newB.removeAll(allready);//hier entfernen wir alle, die schon gekauft waren
                allready.addAll(newB);
//                System.out.println("newB ohne Allready   :   " + newB);
                for (int j = 0; j < newB.size(); j++) {
                    newValue = werte.get(werte.size() - 1) - (newB.get(j).i);
                    werte.add(newValue);
                    pr.println(newValue);
                }
                newValue = werte.get(werte.size() - 1) + ordering.get(i).i;
                werte.add(newValue);
                pr.println(newValue);
            }
            pr.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No such file exists.");
        }
        System.out.println("Werte: " + werte);
    }

    public ArrayList<MyInteger> function(Partition p, ArrayList<MyInteger> ordering) {
        ArrayList<MyInteger> allreadyBought = new ArrayList<>();
        Integer highestMinimalBudget = getMinBudget(p, ordering);
//        System.out.println("ordering nacg getMinBudget : " + ordering.toString());
        System.out.println("StartBudget: " + highestMinimalBudget);
        for (int i = 0; i < ordering.size(); i++) {
            for (int j = i + 1; i < ordering.size(); i++) {
                ArrayList<MyInteger> newOrdering = swap(i, j, ordering);
//                System.out.println("ordering nach swap : " + ordering.toString());
                Integer newBudget = getMinBudget(p, newOrdering);

                if (newBudget > highestMinimalBudget) {
                    ordering = newOrdering;
                    highestMinimalBudget = newBudget;
                    System.out.println("NewBudget: " + newBudget);

                    return function(p, ordering);

                }
            }
        }
        System.out.println("ordering: " + ordering);
        return ordering;
    }

    public ArrayList<MyInteger> swap(int i, int j, ArrayList<MyInteger> ordering) {
        ArrayList<MyInteger> newOrdering = new ArrayList<>();
        newOrdering.addAll(ordering);

        newOrdering.set(i, ordering.get(j));
        newOrdering.set(j, ordering.get(i));
        return newOrdering;
    }

}
