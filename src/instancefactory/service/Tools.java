/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instancefactory.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Sonja Schäfer <sonja_schaefer@gmx.de>
 */
public class Tools {

    public ArrayList<Partition> makeBasicPartitions() {

        ArrayList<MyInteger> randomIntArrayList = this.getRandomIntArray(1, 100, 10);
        System.out.println(randomIntArrayList.toString());
//        ArrayList<MyInteger> randomIntArrayList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        ArrayList<Partition> partitions = new ArrayList<>();

        Partition partition1 = new Partition();
        MyInteger rand = randomIntArrayList.get(0);
        randomIntArrayList.remove(0);
        ArrayList<MyInteger> l = new ArrayList<>();
        l.add(rand);

        partition1.arrayList.add(l);
        partition1.sortedSells.add(rand);
        partitions.add(partition1);
        Integer toogle = 0;
        while (!randomIntArrayList.isEmpty()) {

//            if (this.getCoin(50, 50).equals("sell")) {
            if (toogle.equals(1)) {
                System.out.println("sell");

                Partition partition = new Partition();

                MyInteger rando = randomIntArrayList.get(0);
                randomIntArrayList.remove(0);
                ArrayList<MyInteger> m = new ArrayList<>();
                m.add(rando);

                partition.arrayList.add(m);
                partition.sortedSells.add(rando);

                partitions.add(partition);
                toogle--;
            } else {
                partitions.get(partitions.size() - 1).arrayList.get(0).add(randomIntArrayList.get(0));
                randomIntArrayList.remove(0);
                toogle++;
            }
        }
        return partitions;
    }

    public void buildPartitionAndMerge(ArrayList<Partition> partitions) {

        while (partitions.size() > 1) {
            Partition partition = new Partition();
            Integer indi = 0;
            Partition partitionA = new Partition();
//            partitionA = this.getRandomPartitionDueToProbality(partitions);
            partitionA = partitions.get(indi);
            indi++;
            System.out.println("partitionA: " + partitionA.toString() + "\n");

            Partition partitionB = new Partition();
//            partitionB = this.getRandomPartitionDueToProbality(partitions);
            partitionB = partitions.get(indi);
            System.out.println("partitionB: " + partitionB.toString() + "\n");

            while (partitionA == partitionB) {
                partitionB = this.getRandomPartitionDueToProbality(partitions);
            }
            partition = this.makePartition(partitionA, partitionB);//size 0 ???
            System.out.println("MergedPartition: " + partition.toString() + "\n");
            System.out.println("Sorted SellsOfMergedPartition: " + partition.sortedSells.toString() + "\n");

            int ind = partitions.indexOf(partitionA);
            partitions.set(ind, partition);//ind -1
            partitions.remove(partitionB);

        }
    }

    public ArrayList<MyInteger> getRandomIntArray(int min, int max, int size) {
        ArrayList<MyInteger> randomIntArrayList = new ArrayList<>();
        int it = 0;
        while (it < size) {
            MyInteger te = new MyInteger(ThreadLocalRandom.current().nextInt(min, max + 1));
            randomIntArrayList.add(te);
            it++;
        }
        return randomIntArrayList;
    }

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

    public String getCoin(int boughtProbability, int saleProbability) {

        ArrayList<MyInteger> numbers = getRandomIntArray(1, 100, 1);
        if (numbers.get(0).i < boughtProbability) {
            return "bought";
        } else {
            return "sell";
        }
    }

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

    public Partition makePartition(Partition p1, Partition p2) {
        Partition partition = new Partition();
//        String choice = this.getChoice(33, 33, 34);
        String choice = new String("");

        if (toogle.equals(0)) {
            choice = "rightJoin";
            toogle++;
        } else {
            if (toogle.equals(1)) {
                choice = "leftJoin";
                toogle++;
            } else {
                if (toogle.equals(2)) {
                    choice = "union";
                    toogle = toogle - 2;
                }
            }
        }
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

    ArrayList<MyInteger> makeSortedSellsJoin(ArrayList<MyInteger> s1, ArrayList<MyInteger> s2) {
        ArrayList<MyInteger> s = new ArrayList<>();
        s.addAll(s2);
        s.addAll(s1);
        return s;

    }

    void fillPositiveSetsAndPositiveSetsBoughts(Partition p, ArrayList<Integer> PositiveSetsP, ArrayList<Integer> PositiveSetsBoughtsP) {
        ArrayList<MyInteger> s1Rest = new ArrayList<>();
        s1Rest.addAll(p.sortedSells);
        System.out.println("S1Rest: " + s1Rest.toString());
        for (int i = 0; i < s1Rest.size(); i++) {
            // berechne budget bis i
            p.setBudgetandBoughtsOfSetUptoIndex(i);

            //drucken
            System.out.println("Budget and Bought: " + p.budgetandBoughtsOfSetUptoIndex.get(i).toString());
            //sobald es größer als Null ist
            if (p.budgetandBoughtsOfSetUptoIndex.get(i).get(0) > 0) {
                PositiveSetsP.add(i);
                PositiveSetsBoughtsP.add(p.budgetandBoughtsOfSetUptoIndex.get(i).get(1));

                System.out.println("PositiveSetsP: " + PositiveSetsP.toString());//[] ist richtig
                System.out.println("PositiveSetsBoughtsP: " + PositiveSetsBoughtsP.toString());//[0]ist richtig

                //hier will ich alle vorher zurücksetzten budget aber eigentlich auch bought,oder?????
                for (int k = 0; k < i + 1; k++) {

                    p.budgetandBoughtsOfSetUptoIndex.get(k).set(0, 0);//index 1 size 1!!!!!!!!!!!!!!!!!!!
                    p.budgetandBoughtsOfSetUptoIndex.get(k).set(1, 0);
                }
            }

        }
    }

    ArrayList<MyInteger> makeSortedSellsUnion(Partition p1, Partition p2) {

        ArrayList<MyInteger> newSortedSells = new ArrayList<>();

        ArrayList<MyInteger> s1Rest = new ArrayList<>();
        s1Rest.addAll(p1.sortedSells);

        ArrayList<MyInteger> s2Rest = new ArrayList<>();
        s2Rest.addAll(p2.sortedSells);

        ArrayList<Integer> PositiveSetsP1 = new ArrayList<>();

        ArrayList<Integer> PositiveSetsP2 = new ArrayList<>();

        ArrayList<Integer> PositiveSetsBoughtsP1 = new ArrayList<>();

        ArrayList<Integer> PositiveSetsBoughtsP2 = new ArrayList<>();

        fillPositiveSetsAndPositiveSetsBoughts(p1, PositiveSetsP1, PositiveSetsBoughtsP1);
        System.out.println("PositiveSetsP1: " + PositiveSetsP1.toString());//[] ist richtig
        System.out.println("PositiveSetsBoughtsP1: " + PositiveSetsBoughtsP1.toString());//[]ist richtig

        fillPositiveSetsAndPositiveSetsBoughts(p2, PositiveSetsP2, PositiveSetsBoughtsP2);

        System.out.println("PositiveSetsP2: " + PositiveSetsP2.toString());//[0]ist richtig
        System.out.println("PositiveSetsBoughtsP2: " + PositiveSetsBoughtsP2.toString());//[0]ist richtig

//        
//        Iterator it1 = PositiveSetsP1.iterator();
//                 Iterator it2 = PositiveSetsP2.iterator();
//                 
        while ((!PositiveSetsP1.isEmpty()) && (!PositiveSetsP2.isEmpty())) {

            if (PositiveSetsBoughtsP1.get(0) < PositiveSetsBoughtsP2.get(0)) {
                for (int countP1 = 0; countP1 < PositiveSetsP1.get(0) + 1; countP1++) {
                    newSortedSells.add(s1Rest.get(0));//index 0 size 0
                    s1Rest.remove(0);
                    System.out.println("S1Rest: " + s1Rest.toString());

                }
                PositiveSetsP1.remove(0);
                PositiveSetsBoughtsP1.remove(0);
            } else {
                for (int countP2 = 0; countP2 < PositiveSetsP2.get(0) + 1; countP2++) {
                    newSortedSells.add(s2Rest.get(0));//s2Rest schon leer, warum?
                    s2Rest.remove(0);
                    System.out.println("S2Rest: " + s2Rest.toString());

                }
                PositiveSetsP2.remove(0);
                PositiveSetsBoughtsP2.remove(0);

            }

        }

        //-----------Eigentlich wird jetzt noch unterschieden ob max von min von...
        newSortedSells.addAll(s1Rest);
        newSortedSells.addAll(s2Rest);
        System.out.println("newSortedSells: " + newSortedSells.toString());
        //--------------

        return newSortedSells;

    }

    ArrayList<ArrayList<MyInteger>> makeArrayListJoin(ArrayList<ArrayList<MyInteger>> a1, ArrayList<ArrayList<MyInteger>> a2) {

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

    ArrayList<ArrayList<MyInteger>> makeArrayListUnion(ArrayList<ArrayList<MyInteger>> a1, ArrayList<ArrayList<MyInteger>> a2) {
        ArrayList<ArrayList<MyInteger>> a = new ArrayList<>();
        a.addAll(a1);
        a.addAll(a2);
        return a;
    }

    Partition makePartitionUnion(Partition p1, Partition p2) {
        Partition p = new Partition();

        p.arrayList = makeArrayListUnion(p1.arrayList, p2.arrayList);//What ich übergebe was size 2 und danach hat es size 0????
        p.sortedSells = makeSortedSellsUnion(p1, p2);
        return p;
    }

    Partition makePartitionJoin(Partition p1, Partition p2) {
        Partition p = new Partition();

        p.arrayList = makeArrayListJoin(p1.arrayList, p2.arrayList);
        p.sortedSells = makeSortedSellsJoin(p1.sortedSells, p2.sortedSells);//p2.sorted sells null
//        System.out.println(p.toString());
        return p;
    }
}
