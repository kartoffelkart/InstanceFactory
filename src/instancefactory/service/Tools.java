/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instancefactory.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Sonja Schäfer <sonja_schaefer@gmx.de>
 */
public class Tools {

    public ArrayList<Partition> makeBasicPartitions() {

        ArrayList<Integer> randomIntArrayList = this.getRandomIntArray(1, 100, 10);
        System.out.println(randomIntArrayList.toString());

        ArrayList<Partition> partitions = new ArrayList<>();
//        String choice = this.getChoice(10, 20, 70);
//        System.out.println(choice);

        Partition partition1 = new Partition();
        ArrayList<Integer> l = new ArrayList<>();
        l.add(randomIntArrayList.get(0));
        randomIntArrayList.remove(0);
        partition1.arrayList.add(l);
        partitions.add(partition1);

        while (!randomIntArrayList.isEmpty()) {
            if (this.getCoin(50, 50).equals("sell")) {

                System.err.println("sell");

                Partition partition = new Partition();
                ArrayList<Integer> m = new ArrayList<>();
                m.add(randomIntArrayList.get(0));
                randomIntArrayList.remove(0);
                partition.arrayList.set(0, m);
                partitions.add(partition);

            } else {
                partitions.get(partitions.size() - 1).arrayList.get(0).add(randomIntArrayList.get(0));
                randomIntArrayList.remove(0);
            }
        }
        return partitions;
    }

    public void buildPartitionAndMerge(ArrayList<Partition> partitions) {

        while (partitions.size() > 1) {
            Partition partition = new Partition();
            Partition partitionA = new Partition();
            Partition partitionB = new Partition();

            partitionA = this.getRandomPartitionDueToProbality(partitions);

            partitionB = this.getRandomPartitionDueToProbality(partitions);
            while (partitionA == partitionB) {
                partitionB = this.getRandomPartitionDueToProbality(partitions);
            }
            partition = this.makePartition(partitionA, partitionB);
            partitions.set(partitions.indexOf(partitionA), partition);
            partitions.remove(partitionB);

            partitions.get(0).print();
        }
    }

    public ArrayList<Integer> getRandomIntArray(int min, int max, int size) {
        ArrayList<Integer> randomIntArrayList = new ArrayList<>();
        int it = 0;
        while (it < size) {

            randomIntArrayList.add(ThreadLocalRandom.current().nextInt(min, max + 1));
            it++;
        }
        return randomIntArrayList;
    }

    public String getChoice(int unionProbability, int leftJoinProbability, int rightJoinProbability) {

        ArrayList<Integer> numbers = getRandomIntArray(1, 100, 1);
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

    public String getCoin(int boughtProbability, int saleProbability) {

        ArrayList<Integer> numbers = getRandomIntArray(1, 100, 1);
        if (numbers.get(0) < boughtProbability) {
            return "bought";
        } else {
            return "sell";
        }
    }

    public Partition getRandomPartitionDueToProbality(ArrayList<Partition> partitions) {
        Partition partition = new Partition();

        int sum = 0;

        for (int y = 0; (y < partitions.size()); y++) {

            ArrayList<Integer> numbers = getRandomIntArray(1, 100, 1);
            if (numbers.get(0) < sum + partitions.get(y).probability) {
                partition = partitions.get(y);

            } else {
                y++;
                sum = sum + partitions.get(y).probability;

            }
        }
        return partition;
    }

    public Partition makePartition(Partition p1, Partition p2) {
        Partition partition = new Partition();
        String choice = this.getChoice(10, 20, 70);
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

    ArrayList<Integer> makeSortedSellsJoin(ArrayList<Integer> s1, ArrayList<Integer> s2) {
        ArrayList<Integer> s = new ArrayList<>();
        s.addAll(s2);
        s.addAll(s1);
        return s;

    }

    ArrayList<Integer> makeSortedSellsUnion(Partition p1, Partition p2) {
        ArrayList<Integer> s = new ArrayList<>();
        ArrayList<Integer> s1Rest = new ArrayList<>();
        ArrayList<Integer> s2Rest = new ArrayList<>();
        s1Rest.addAll(p1.sortedSells);
        s2Rest.addAll(p2.sortedSells);
        ArrayList<Integer> PositiveSetsP1 = new ArrayList<>();
        ArrayList<Integer> PositiveSetsP2 = new ArrayList<>();
        ArrayList<Integer> PositiveSetsBoughtsP1 = new ArrayList<>();
        ArrayList<Integer> PositiveSetsBoughtsP2 = new ArrayList<>();

        int i = 0;
        int j = 0;
        int x = 0;
        int y = 0;
        while (i < s1Rest.size()) {
            p1.setBudgetandBoughtsOfSetUptoIndex(i);
            ArrayList<Integer> budgetAndBoughts = p1.budgetandBoughtsOfSetUptoIndex.get(i);
            i++;
            if (budgetAndBoughts.get(0) > 0) {
                PositiveSetsP1.add(i);
                PositiveSetsBoughtsP1.add(budgetAndBoughts.get(1));
                for (int k = 0; !(k > i); k++) {
                    p1.budgetandBoughtsOfSetUptoIndex.get(k).set(0, 0);
                }
            }
        }
        while (j < s2Rest.size()) {
            p2.setBudgetandBoughtsOfSetUptoIndex(j);
            ArrayList<Integer> budgetAndBoughts = p2.budgetandBoughtsOfSetUptoIndex.get(i);

            j++;
            if (budgetAndBoughts.get(0) > 0) {
                PositiveSetsP2.add(j);
                PositiveSetsBoughtsP2.add(budgetAndBoughts.get(1));
                for (int k = 0; !(k > j); k++) {
                    p2.budgetandBoughtsOfSetUptoIndex.get(k).set(0, 0);
                }
            }
        }

//        
//        Iterator it1 = PositiveSetsP1.iterator();
//                 Iterator it2 = PositiveSetsP2.iterator();
//                 
        while (!PositiveSetsP1.isEmpty() && !PositiveSetsP2.isEmpty()) {

            int countP1 = 0;
            int countP2 = 0;
            if (PositiveSetsBoughtsP1.get(0) < PositiveSetsBoughtsP2.get(0)) {
                while (!(countP1 > PositiveSetsP1.get(0))) {
                    s.add(s1Rest.get(0));
                    s1Rest.remove(0);

                }
                PositiveSetsP1.remove(0);
            } else {
                while (!(countP2 > PositiveSetsP2.get(0))) {
                    s.add(s2Rest.get(0));
                    s2Rest.remove(0);

                }
                PositiveSetsP2.remove(0);
            }

        }
        s.addAll(s1Rest);
        s.addAll(s2Rest);

        return s;

    }

    ArrayList<ArrayList<Integer>> makeArrayListJoin(ArrayList<ArrayList<Integer>> a1, ArrayList<ArrayList<Integer>> a2) {
        ArrayList<ArrayList<Integer>> a = new ArrayList<>();
        a.addAll(a1);
        Iterator ita1 = a1.iterator();
        Iterator ita2 = a2.iterator();

        while (ita1.hasNext()) {
            ArrayList<Integer> allreadyJoinedBoughts = new ArrayList<>(); // kein Nullpinter oder?
            while (ita2.hasNext()) {

                ArrayList<Integer> toLink = new ArrayList<>();
                toLink.addAll((ArrayList<Integer>) ita2);
                toLink.remove(0); //erster Eintrag ist ein Sell
                toLink.removeAll(allreadyJoinedBoughts); //verändere ich hier die Originale ???
                allreadyJoinedBoughts.addAll(toLink);
                ((ArrayList<Integer>) ita1).addAll(toLink);

            }
        }

        return a;

    }

    ArrayList<ArrayList<Integer>> makeArrayListUnion(ArrayList<ArrayList<Integer>> a1, ArrayList<ArrayList<Integer>> a2) {
        ArrayList<ArrayList<Integer>> a = new ArrayList<>();
        a.addAll(a1);
        a.addAll(a2);
        return a;
    }

    Partition makePartitionUnion(Partition p1, Partition p2) {
        Partition p = new Partition();

        p.arrayList = makeArrayListUnion(p1.arrayList, p2.arrayList);
        p.sortedSells = makeSortedSellsUnion(p1, p2);
        return p;
    }

    Partition makePartitionJoin(Partition p1, Partition p2) {
        Partition p = new Partition();

        p.arrayList = makeArrayListJoin(p1.arrayList, p2.arrayList);
        p.sortedSells = makeSortedSellsJoin(p1.sortedSells, p2.sortedSells);
        return p;
    }
}
