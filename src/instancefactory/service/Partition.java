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

    public ArrayList<Integer> budgetOfSetUptoIndex;

    public void setBudgetOfSetUptoIndex(int index) {
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

        budget = budgetOfSetUptoIndex.get(index - 1) + sortedSells.get(index) - sumNewBoughts;
        budgetOfSetUptoIndex.set(index, budget);
    }

}
