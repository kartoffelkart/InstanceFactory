/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instancefactory.service;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Sonja Schäfer sonja_schaefer@gmx.de
 */
public class Graph {

    private Partition partition;
    private ArrayList<MyInteger> ordering;
    private ArrayList<MyInteger> orderingWithBoughts;
    public ArrayList<Integer> werte;

    private Integer minBudget;

    public Graph(Partition partition, ArrayList<MyInteger> ordering) {
        this.partition = partition;
        this.ordering = ordering;
        this.orderingWithBoughts = new ArrayList<>();
        this.werte = new ArrayList<>();
        calculateValues(partition, ordering);
        this.minBudget = Collections.min(werte);
    }

    private void calculateValues(Partition p, ArrayList<MyInteger> ordering) {

        ArrayList<MyInteger> allready = new ArrayList<>();
        werte = new ArrayList<>();
        werte.add(0, 0);

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
                orderingWithBoughts.add(newB.get(j));
            }
            newValue = werte.get(werte.size() - 1) + ordering.get(i).i;
            werte.add(newValue);
            orderingWithBoughts.add(ordering.get(i));

        }

//        Integer minBudget = Collections.min(werte);
    }

    public Partition getPartition() {
        return partition;
    }

    public ArrayList<MyInteger> getOrdering() {
        return ordering;
    }

    public ArrayList<MyInteger> getOrderingWithBoughts() {
        return orderingWithBoughts;
    }

    public Integer getMinBudget() {
        return minBudget;
    }

    public ArrayList<Integer> getWerte() {
        return werte;
    }

}
