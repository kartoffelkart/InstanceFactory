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

    ArrayList<Integer> makeSortedSellsUnion(ArrayList<Integer> s1, ArrayList<Integer> s2) {
        ArrayList<Integer> s = new ArrayList<>();

    }

    ArrayList<ArrayList<Integer>> makeArrayListJoin(ArrayList<ArrayList<Integer>> a1, ArrayList<ArrayList<Integer>> a2) {
        ArrayList<ArrayList<Integer>> a = new ArrayList<>();
        a.addAll(a1);
        Iterator ita1 = a1.iterator();
        Iterator ita2 = a2.iterator();

        while (ita1.hasNext()) {
            while (ita2.hasNext())  {
                ((ArrayList<Integer>) ita1).add(((ArrayList<Integer>)ita2).get(0));

            }
        }
        
        return a;

    }

    Partition makePartitionUnion(Partition p1, Partition p2) {
        Partition p = new Partition();

        p.arrayList = p1.arrayList;
        p.arrayList.addAll(p2.arrayList);

        p.sortedSells =;
        return p;
    }

}
