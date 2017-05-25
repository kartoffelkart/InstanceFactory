/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instancefactory.service;

import static instancefactory.service.Tools.permute;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Sonja Schäfer sonja_schaefer@gmx.de
 */
public class Partition {

//Partition leftPartition;
//Partition rightPartition;
//String mergeStep; 
    Tools newTool = new Tools();

    public ArrayList<ArrayList<MyInteger>> arrayList;

    ArrayList<MySet> positiveSets;

    public ArrayList<MyInteger> sortedSells;

    private Integer minBudgetCompare;

    public Integer minBudgetSwap;
    public Integer minBudgetChangeOrder;

    public Integer minBudgetRandomOrder;

    public ArrayList<MyInteger> allreadyBought = new ArrayList<>();

    public ArrayList<BalanceBoughtsBudget> balanceBoughtsBudgetOfSetUpToIndex = new ArrayList();

    public int probability;
    public int budget;
    public int balance = 0;
//    private Graph calculatedGraphOfSortedSells;
    public ArrayList<Eintrag> werte;
    public ArrayList<Integer> sumBoughts;

    //_________________________________________________________________________
    //KONSTRUKTOR
    public Partition() {

        arrayList = new ArrayList<ArrayList<MyInteger>>();
        probability = 1;
        sortedSells = new ArrayList<>();

        // todo: nächste Zeile weg
        positiveSets = new ArrayList<MySet>();
//    calculatedGraphOfSortedSells= new Graph(this, sortedSells);

    }
//_____________________________________________________________________________
    //KONSTRUKTOR

    public Partition(ArrayList<ArrayList<MyInteger>> newArrayList, ArrayList<MyInteger> newSortedSells) {

        arrayList = newArrayList;
        probability = 1;
        sortedSells = newSortedSells;
        // todo: nächste Zeile weg
        positiveSets = new ArrayList<MySet>();
//   calculatedGraphOfSortedSells= new Graph(this, sortedSells);

    }
//_____________________________________________________________________________

    public Integer getMinBudgetCompare() {
        return minBudgetCompare;
    }

    public void setMinBudgetCompare(Integer minBudgetCompare) {
        //ASSERTION
//        Graph calculatedGraphOfSortedSells= new Graph(this, sortedSells);
//        if (!(calculatedGraphOfSortedSells.getMinBudget().intValue() > budget)) {
//            this.minBudgetCompare = minBudgetCompare;
//             System.err.println("Klaro budget" + budget + "ist größer oder gleich budget vom neuen HeuristikGraphen" + minBudgetCompare.intValue());
//        } else {
//            System.err.println("Ohje budget" + budget + " ist kleiner budget vom neuen HeuristikGraphen" + minBudgetCompare.intValue());
//
//        }

    }

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

    public Integer getPositionOfSellInSortedSells(MyInteger sell) {

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

    /**
     *
     * testet ob in dieser Patition die SortedSells zum Budget passt
     */
    public boolean orderingFitsBudget() {

        Graph newTestGraph = new Graph(this, sortedSells);
        System.out.println("newTestGraph.getMinBudget()" + newTestGraph.getMinBudget());
        System.out.println("budget" + budget);
        if ((newTestGraph.getMinBudget().equals(budget))) {
            System.out.println("Gut ! Budget von Partition passt zu den SortedSells");

            return true;
        } else {
            System.out.println("newTestGraph.getMinBudget()" + newTestGraph.getMinBudget());
            System.out.println("newTestGraph.getMinBudget()" + newTestGraph.getMinBudget()+"budget" + budget);
            System.err.println("newTestGraph.getMinBudget()" + newTestGraph.getMinBudget()+"budget" + budget+"Fehler ! Budget von Partition passt nicht zu den SortedSells");
            return false;
        }

    }

    /**
     *
     * testet ob in dieser Patition die SortedSells wirklich die Reihenfolge
     * sind, die zum MinimalBudget führt
     */
    public boolean isBestOrdering() {
//        Graph newTestGraph = new Graph(this, sortedSells);
//        Integer probablyBest = newTestGraph.getMinBudget();
////warum ist die liste leer?
//        ArrayList<ArrayList<MyInteger>> returnList = new ArrayList<>();
//        permute(sortedSells, 0, returnList);
//
//        for (int i = 0; i < returnList.size(); i++) {
//            newTestGraph = new Graph(this, returnList.get(i));
//            if (probablyBest < newTestGraph.getMinBudget()) {
//                System.err.println("Fehler ! SortedSells ist in der Partition nicht die optimale Reihenfolge");
//                System.err.println("mit neuer Permutation newTestGraph.getMinBudget()" + newTestGraph.getMinBudget());
//                System.err.println("probablyBest" + probablyBest);
//
//                return false;
//            }
//        }
//        System.out.println("Gut ! SortedSells ist in der Partition die optimale Reihenfolge");
        return true;

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

//    public Graph getCalculatedGraphOfSortedSells() {
//        if (calculatedGraphOfSortedSells == null) {
//            calculatedGraphOfSortedSells = new Graph(this, sortedSells);
//        }
//
//        return calculatedGraphOfSortedSells;
//    }
//
//    public void setCalculatedGraphOfSortedSells() {
//        this.calculatedGraphOfSortedSells = new Graph(this, sortedSells);
//}
    public ArrayList<Eintrag> getWerte() {
        if (this.werte == null) {
            Graph calculatedGraphOfSortedSells = new Graph(this, sortedSells);
            werte = calculatedGraphOfSortedSells.getWerte();
        }
        return werte;
    }

    public ArrayList<Integer> getSumBoughts() {
        if (this.sumBoughts == null) {

            sumBoughts = calculateSumOfBoughts();
        }

        return sumBoughts;
    }

    private ArrayList<Integer> calculateSumOfBoughts() {
        ArrayList<Integer> newListSumOfBoughts = new ArrayList<>();
        ArrayList<MyInteger> allreadyBought = new ArrayList<>();

        for (int i = 0; i < sortedSells.size(); i++) {

            int sumNewBoughts = 0;
            /**
             * Variable für die neu getätigten Boughts
             */
            ArrayList<MyInteger> newBought = new ArrayList<>();

            //--------------------------------------------------------------------------------------------------------------------
//if (position==null) würde ja gerne prüfen ob das initialisiert wurde
            newBought = getBoughtsOfSell(sortedSells.get(i)); //hier holen wir alle für den Sell benötigten Boughts

            newBought.removeAll(allreadyBought);//hier entfernen wir alle, die schon gekauft waren

            allreadyBought.addAll(newBought);//hier vermerken wir die neu gekauften boughts als gekauft
// berechnet die Summe von Boughts------------------------------

            sumNewBoughts = newTool.getSum(newBought);

            newListSumOfBoughts.add(sumNewBoughts);
        }
        return newListSumOfBoughts;
    }

//    public ArrayList<Partition> getPositiveSetsList() {
//
//        if (this.positiveSetsList == null) {
//
//            positiveSetsList = calculatePositiveSetsList();
//        }
//
//        return positiveSetsList;
//    }
//
//    private ArrayList<Partition> calculatePositiveSetsList() {
//        ArrayList<Partition> newPositiveSetsList = new ArrayList<>();
//for(int i = 0;i<werte.size();i++){
//if(werte.get(i).value >0){
// 
//Partition positiveMinimalSet = new Partition();
//// todo: die AllreadyBought müssen entfernt werden
// ArrayList<MyInteger> newBoughts = new ArrayList<>();
//int m = 0; m++) {
//      while( ! werte.get(i).node==sortedSells.get(m-1)){
//     newBoughts.addAll(sortedSells.get(m).g)
//      m++;    
// }
//        //--------------------------------------------------------------------------------------------------------------------
////if (position==null) würde ja gerne prüfen ob das initialisiert wurde
//        newBought = getBoughtsOfSell(sortedSells.get(index)); //hier holen wir alle für den Sell benötigten Boughts
//
//        newBought.removeAll(allreadyBought);//hier entfernen wir alle, die schon gekauft waren
//
//        allreadyBought.addAll(newBought);//hier vermerken wir die neu gekauften boughts als gekauft
//// berechnet die Summe von Boughts------------------------------
//
//
////-----------
//positiveMinimalSet.arrayList= (ArrayList<ArrayList<MyInteger>>)getArrayAbschnitt(arrayList, 0, i);
//positiveMinimalSet.werte=getArrayAbschnitt(werte, i, j)
//}
//
//
//
//}
//        
//    }
}
