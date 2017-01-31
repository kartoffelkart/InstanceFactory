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
    public ArrayList<Integer> allreadyBought;

    public ArrayList<ArrayList<Integer>> budgetandBoughtsOfSetUptoIndex;

    public int probability;
    //_________________________________________________________________________

    //KONSTRUKTOR
    public Partition() {
        ArrayList<Integer> array = new ArrayList<>();
        arrayList = new ArrayList<ArrayList<Integer>>();
    }
//_____________________________________________________________________________

    public void setBudgetandBoughtsOfSetUptoIndex(int index) {
        int budget = 0;

        ArrayList<Integer> newBought;

        int position = arrayList.indexOf(sortedSells.get(index));
        newBought = arrayList.get(position);
        newBought.remove(0);
        newBought.removeAll(allreadyBought);
        int sumNewBoughts = 0;
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

        budgetandBoughtsOfSetUptoIndex.set(index, newEintrag);

    }

    @Override
    public String toString() {
        String ret = new String();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ret = ret.concat(" nächster Sell mit seinen Boughts: ");
            ret = ret.concat(((ArrayList<Integer>) it.next()).toString()+"\n");

        }
        return ret;
    }
}
