/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instancefactory.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Sonja Schäfer sonja_schaefer@gmx.de
 */
public class Graph {
    
    private Partition partition;
    private List<MyInteger> ordering;
    public ArrayList<Eintrag> werte;
    
    private Integer minBudget;
    
    public Graph(Partition partition, List<MyInteger> ordering) {
        this.partition = partition;
        this.ordering = ordering;
//nächste Zeile kann weg
        
    }
    
    public Partition getPartition() {
        return partition;
    }
    
    public List<MyInteger> getOrdering() {
        return ordering;
    }
    
    public Integer getMinBudget() {
        if (this.minBudget == null) {
            ArrayList<Integer> values = new ArrayList<>();
            for (int i = 0; i < this.getWerte().size(); i++) {
                
                values.add(werte.get(i).value);
            }
            this.minBudget = Collections.min(values);
        }
        return minBudget;
    }
    
    public ArrayList<Eintrag> getWerte() {
        if (this.werte == null) {
            werte = new ArrayList<>();
            Eintrag eintrag = new Eintrag();
            eintrag.node = null;
            eintrag.value = 0;
            werte.add(eintrag);
            calculateValues(partition, ordering);
           werte.remove(0);
        }
        return werte;
    }
//todo: hier Partition rausnehmen, stattdessen globales Partition nehmen

    private void calculateValues(Partition p, List<MyInteger> ordering) {
        
        ArrayList<MyInteger> allready = new ArrayList<>();
        Integer newValue;
        
        for (int i = 0; i < ordering.size(); i++) {
            ArrayList<MyInteger> newB = p.getBoughtsOfSell(ordering.get(i));
//                System.out.println("newB    :   " + newB);
            newB.removeAll(allready);//hier entfernen wir alle, die schon gekauft waren
            allready.addAll(newB);
//                System.out.println("newB ohne Allready   :   " + newB);
            
            for (int j = 0; j < newB.size(); j++) {
                
                newValue = werte.get(werte.size() - 1).value - (newB.get(j).i);
                
                Eintrag eintrag = new Eintrag();
                eintrag.node = newB.get(j);
                
                eintrag.value = newValue;
                werte.add(eintrag);
                
            }
            newValue = werte.get(werte.size() - 1).value + ordering.get(i).i;
            Eintrag eintrag = new Eintrag();
            eintrag.node = ordering.get(i);
            
            eintrag.value = newValue;
            werte.add(eintrag);
            
        }

//        Integer minBudget = Collections.min(werte);
    }
}
