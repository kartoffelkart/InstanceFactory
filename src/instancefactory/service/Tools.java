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

    ArrayList<Integer> makeSortedSellsJoin(ArrayList<Integer> s1, ArrayList<Integer> s2) {
        ArrayList<Integer> s = new ArrayList<>();
        s.addAll(s2);
        s.addAll(s1);
        return s;

    }

    ArrayList<Integer> makeSortedSellsUnion(Partition p1, Partition p2) {
        ArrayList<Integer> s = new ArrayList<>();
        for (int i = 0; i < p1.sortedSells.size(); i++) {
            if (p1.setBudgetOfSetUptoIndex(i) > 0) {
                for (int j = 0; j < p1.sortedSells.size(); j++) {
                    if (p2.setBudgetOfSetUptoIndex(j) > 0) {//if boughts von Set kleiner als Boughts von anderem dann Set hinzufügen}}}
                    }
                }
            }
        }
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
