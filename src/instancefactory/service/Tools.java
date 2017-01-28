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
        ArrayList<Integer> s1Rest = new ArrayList<>();
        ArrayList<Integer> s2Rest = new ArrayList<>();
        s1Rest.addAll(p1.sortedSells);
        s2Rest.addAll(p2.sortedSells);
        ArrayList<Integer> PositiveSetsP1= new ArrayList<>();
        ArrayList<Integer> PositiveSetsP2= new ArrayList<>();
        ArrayList<Integer> PositiveSetsBoughtsP1= new ArrayList<>();
        ArrayList<Integer> PositiveSetsBoughtsP2= new ArrayList<>();

        int i = 0;
        int j = 0;
        int x =0;
        int y =0;
        while (i < s1Rest.size()) {
            p1.setBudgetandBoughtsOfSetUptoIndex(i);
            i++;
            if (p1.budgetandBoughtsOfSetUptoIndex.get(i).get(0) > 0) {
                PositiveSetsP1.add(i);
                for(int k =0;!(k>i);k++){p1.budgetandBoughtsOfSetUptoIndex.get(k).set(0, 0);}
            }}
        while (j < s2Rest.size()) {
            p2.setBudgetandBoughtsOfSetUptoIndex(j);
            j++;
            if (p2.budgetandBoughtsOfSetUptoIndex.get(j).get(0) > 0) {
                PositiveSetsP2.add(j);
                for(int k =0;!(k>j);k++){p2.budgetandBoughtsOfSetUptoIndex.get(k).set(0, 0);}
            }}
                //if boughts von Set1 kleiner als Boughts von anderem dann Set hinzufügen}}}

                        //alle in budget von p1 of up to index wird 0 gesetzt
//                        sonst 
//                                alle in budget von p1 of up to index wird 0 gesetzt
//                                nicht in nächste while Schleife springen
                while (x < PositiveSetsP1.size()) {
                    while (y < PositiveSetsP2.size()) {
                        
                        
                        
                        
                        
                        
                        
                    p2.setBudgetOfSetUptoIndex(j);
                    j++;
                    if (p2.budgetOfSetUptoIndex.get(j) > 0) {
                    //if boughts von Set2 kleiner als Boughts von anderem dann Set hinzufügen}}}

                        //alle in budget von p2 of up to index wird 0 gesetzt
//                        sonst 
//                                alle in budget von p1 of up to index wird 0 gesetzt
//                                raus aus dieser for schleife 
                        {

                        }
                    }
                }
            }
        }

        //hier passiert jetzt noch was mit den RestArraysSells
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
