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
 * @author Sonja Schäfer <sonja_schaefer@gmx.de>
 */
public class Partition {

    public ArrayList<ArrayList<Integer>> arrayList;

    public ArrayList<Integer> sortedSells;
    public ArrayList<Integer> allreadyBought = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> budgetandBoughtsOfSetUptoIndex = new ArrayList();

    public int probability;
    //_________________________________________________________________________

    //KONSTRUKTOR
    public Partition() {
        ArrayList<Integer> array = new ArrayList<>();
        arrayList = new ArrayList<ArrayList<Integer>>();
        probability = 1;
        sortedSells = new ArrayList<>();
    }
//_____________________________________________________________________________

    public void setBudgetandBoughtsOfSetUptoIndex(int index) {
        int budget = 0;
        int sumNewBoughts = 0;
        ArrayList<Integer> newBought;
        Integer position = 0;

        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).get(0) == sortedSells.get(index)) {

                position = i;
            }
        }
//if (position==null) würde ja gerne prüfen ob das initialisiert wurde
        newBought = arrayList.get(position);
        newBought.remove(0);
        newBought.removeAll(allreadyBought);

        Iterator it = newBought.iterator();
        while (it.hasNext()) {

            sumNewBoughts = sumNewBoughts + ((Integer) it.next());
        }

        if (index > 0) {
            budget = budgetandBoughtsOfSetUptoIndex.get(index - 1).get(0)
                    + sortedSells.get(index) - sumNewBoughts;

        } else {
            budget = sortedSells.get(index) - sumNewBoughts;
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
            ret = ret.concat(((ArrayList<Integer>) it.next()).toString() + "\n");

        }
        return ret;
    }
}
