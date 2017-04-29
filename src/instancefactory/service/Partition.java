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

    Tools newTool = new Tools();

    public ArrayList<ArrayList<MyInteger>> arrayList;

    ArrayList<PositiveSet> positiveSets;

    public ArrayList<MyInteger> sortedSells;

    private Integer minBudgetCompare;

    public Integer getMinBudgetCompare() {
        return minBudgetCompare;
    }

    public void setMinBudgetCompare(Integer minBudgetCompare) {
        //ASSERTION
        if (!(minBudgetCompare.intValue() > budget)) {
            this.minBudgetCompare = minBudgetCompare;
             System.err.println("Klaro budget" + budget + "ist größer oder gleich budget vom neuen HeuristikGraphen" + minBudgetCompare.intValue());
        } else {
            System.err.println("Ohje budget" + budget + " ist kleiner budget vom neuen HeuristikGraphen" + minBudgetCompare.intValue());

 
        }

    }
    public Integer minBudgetSwap;
    public Integer minBudgetChangeOrder;

    public Integer minBudgetRandomOrder;

    public ArrayList<MyInteger> allreadyBought = new ArrayList<>();

    public ArrayList<BalanceBoughtsBudget> balanceBoughtsBudgetOfSetUpToIndex = new ArrayList();

    public int probability;
    public int budget;// todo: test = 0;
    public int balance = 0;
    //_________________________________________________________________________

    //KONSTRUKTOR
    public Partition() {
        ArrayList<MyInteger> array = new ArrayList<>();
        arrayList = new ArrayList<ArrayList<MyInteger>>();
        probability = 1;
        sortedSells = new ArrayList<>();
        positiveSets = new ArrayList<PositiveSet>();

    }
//_____________________________________________________________________________
    //KONSTRUKTOR

    public Partition(ArrayList<ArrayList<MyInteger>> newArrayList, ArrayList<MyInteger> newSortedSells) {
        ArrayList<MyInteger> array = new ArrayList<>();
        arrayList = newArrayList;
        probability = 1;
        sortedSells = newSortedSells;
        positiveSets = new ArrayList<PositiveSet>();

    }
//_____________________________________________________________________________

    /**
     * Gibt den Index zurück an dem ein Sell sich in der Adjazensliste befindet
     *
     *
     * @param MyInt Objekt (Sell)
     * @return position of Sell in der Adjazensliste
     */
    public Integer getPositionOfSellInAdjazenslist(MyInteger sell) {

        Integer position = null;

        for (int i = 0; i < arrayList.size(); i++) {
            //für jeden sell 
            if (arrayList.get(i).get(0) == sell) {//irgendwann ist hier Nullpointer, bei index? == Objektidentität

                position = i;
            }
        }

        return position;
    }

    public ArrayList<MyInteger> getBoughtsOfSell(MyInteger sell) {
        ArrayList<MyInteger> newBought = new ArrayList<>();
        newBought.addAll(arrayList.get(getPositionOfSellInAdjazenslist(sell))); //hier holen wir alle für den Sell benötigten Boughts
        newBought.remove(0);

        return newBought;
    }

//public Integer getBalance( int index) {
//   return balanceBoughtsBudgetOfSetUpToIndex.get(index).get(0);
//    
//}
//public Integer getSumNewBoughts( int index) {
//   return balanceBoughtsBudgetOfSetUpToIndex.get(index).get(1);
//    
//}
//public Integer getMinBudget( int index) {
//   return balanceBoughtsBudgetOfSetUpToIndex.get(index).get(2);
//    
//}
    /**
     * füllt die lange Liste dert balanceBoughtsBudgetOfSetUpToIndex rekursiv
     * aus den Werten von Index-1
     *
     *
     * @param index Index für den die Werte gerade dynamisch in die Tabelle
     * eingetragen werden
     */
    public void setValueOfBalanceBoughtsBudgetOfSet(int index) {

        int balance = 0;
        int budget = 0;
        int sumNewBoughts = 0;
        /**
         * Variable für die neu getätigten Boughts
         */
        ArrayList<MyInteger> newBought = new ArrayList<>();

        //--------------------------------------------------------------------------------------------------------------------
//if (position==null) würde ja gerne prüfen ob das initialisiert wurde
        newBought = getBoughtsOfSell(sortedSells.get(index)); //hier holen wir alle für den Sell benötigten Boughts

        newBought.removeAll(allreadyBought);//hier entfernen wir alle, die schon gekauft waren

        allreadyBought.addAll(newBought);//hier vermerken wir die neu gekauften boughts als gekauft
// berechnet die Summe von Boughts------------------------------

        sumNewBoughts = newTool.getSum(newBought);
        System.out.println("sumNewBoughts :" + sumNewBoughts);

        if (index > 0) {
            int lastBalance = (balanceBoughtsBudgetOfSetUpToIndex.get(index - 1)).getBalance();
            int lastBoughts = (balanceBoughtsBudgetOfSetUpToIndex.get(index - 1)).getBoughts();
            int lastBudget = (balanceBoughtsBudgetOfSetUpToIndex.get(index - 1)).getBudget();
            //-----------------------------------------------------hier wird balance rekursiv aus den Werten von Index-1 berechnet

            balance = lastBalance
                    - sumNewBoughts + sortedSells.get(index).i;
            //-----------------------------------------------------hier wird budget  rekursiv aus den Werten von Index-1 berechnet

            budget = Integer.min(lastBudget, lastBalance - sumNewBoughts);

        } else {
            System.out.println("Das ist der Anfang. Hier war der Index 0.");
            balance = -sumNewBoughts + sortedSells.get(index).i;
            budget = -sumNewBoughts;
        }

        //hier wird ein neuer Eintrag daraus generiert
        BalanceBoughtsBudget newEintrag = new BalanceBoughtsBudget(balance, sumNewBoughts, budget);

//hier checken wir nochmal ob Index der richtige Indize ist
        if (balanceBoughtsBudgetOfSetUpToIndex.size() == index) {
            balanceBoughtsBudgetOfSetUpToIndex.add(index, newEintrag);
            //drucken

            System.out.println("Balance, Bought, Budget: " + balanceBoughtsBudgetOfSetUpToIndex.get(index).toString());
        } else {
            System.err.println("Error falscher Index");
        }

    }

    public ArrayList<ArrayList<MyInteger>> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<ArrayList<MyInteger>> arrayList) {
        this.arrayList = arrayList;
    }

    public ArrayList<BalanceBoughtsBudget> getBalanceBoughtsBudgetOfSetUpToIndex() {
        return balanceBoughtsBudgetOfSetUpToIndex;
    }

    public void setBalanceBoughtsBudgetOfSetUpToIndex(ArrayList<BalanceBoughtsBudget> balanceBoughtsBudgetOfSetUpToIndex) {
        this.balanceBoughtsBudgetOfSetUpToIndex = balanceBoughtsBudgetOfSetUpToIndex;
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
