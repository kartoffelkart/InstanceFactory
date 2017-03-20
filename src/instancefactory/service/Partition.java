/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instancefactory.service;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Sonja Schäfer sonja_schaefer@gmx.de
 */
public class Partition {

    public ArrayList<ArrayList<MyInteger>> arrayList;

    public ArrayList<MyInteger> sortedSells;
    public ArrayList<MyInteger> allreadyBought = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> budgetandBoughtsOfSetUptoIndex = new ArrayList();

    public int probability;
    //_________________________________________________________________________

    //KONSTRUKTOR
    public Partition() {
        ArrayList<MyInteger> array = new ArrayList<>();
        arrayList = new ArrayList<ArrayList<MyInteger>>();
        probability = 1;
        sortedSells = new ArrayList<>();
    }
//_____________________________________________________________________________
  
    public void setBudgetandBoughtsOfSetUptoIndex(int index) {
        int budget = 0;
        int sumNewBoughts = 0;
        ArrayList<MyInteger> newBought = new ArrayList<>();
        Integer position = 0;

        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).get(0) == sortedSells.get(index)) {//irgendwann ist hier Nullpointer, bei index?

                position = i;
            }
        }
//if (position==null) würde ja gerne prüfen ob das initialisiert wurde
        newBought.addAll(arrayList.get(position));
        newBought.remove(0);
        newBought.removeAll(allreadyBought);
        allreadyBought.addAll(newBought);

        Iterator it = newBought.iterator();
        while (it.hasNext()) {

            sumNewBoughts = sumNewBoughts + ((MyInteger) it.next()).i;
        }

        if (index > 0) {
            budget = budgetandBoughtsOfSetUptoIndex.get(index - 1).get(0)
                    + sortedSells.get(index).i - sumNewBoughts;

        } else {
            budget = sortedSells.get(index).i - sumNewBoughts;
        }
        ArrayList<Integer> newEintrag = new ArrayList<>();

        newEintrag.add(budget);
        newEintrag.add(sumNewBoughts);
        if (budgetandBoughtsOfSetUptoIndex.size() == index) {
            budgetandBoughtsOfSetUptoIndex.add(index, newEintrag);
        } else {
            System.err.println("Error");
        }

    }

    @Override
    public String toString() {
        String ret = new String();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ret = ret.concat(" nächster Sell mit seinen Boughts: ");
            ret = ret.concat(((ArrayList<MyInteger>) it.next()).toString() + "\n");

        }
        return ret;
    }
}
