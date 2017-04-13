/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instancefactory.service;

import instancefactory.service.MyInteger;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Sonja Schäfer sonja_schaefer@gmx.de
 */
public class Partition {

    Tools newTool = new Tools();

    public ArrayList<ArrayList<MyInteger>> arrayList;

    public ArrayList<MyInteger> sortedSells;

    /**
     * Liste der Anzahl an Sells PositiveSets
     */
    public ArrayList<Integer> positiveSetsPLengths;
    /**
     * Liste der aufsummierten Boughts für die PositiveSets
     */
    public ArrayList<Integer> positiveSetsPLengthsSumBoughts;
    /**
     * Liste der Budgets für die PositiveSets
     */
    public ArrayList<Integer> positiveSetsBudgets;
    /**
     * Liste der Balances für die PositiveSets
     */
    public ArrayList<Integer> positiveSetsBalances;

    public ArrayList<MyInteger> allreadyBought = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> balanceBoughtsBudgetOfSetUpToIndex = new ArrayList();

    public int probability;
    public int budget = 0;
    public int balance = 0;
    //_________________________________________________________________________

    //KONSTRUKTOR
    public Partition() {
        ArrayList<MyInteger> array = new ArrayList<>();
        arrayList = new ArrayList<ArrayList<MyInteger>>();
        probability = 1;
        sortedSells = new ArrayList<>();
        positiveSetsPLengths = new ArrayList<>();
        positiveSetsPLengthsSumBoughts = new ArrayList<>();
        positiveSetsBudgets = new ArrayList<>();
        positiveSetsBalances = new ArrayList<>();
    }
//_____________________________________________________________________________

    /**
     * füllt die lange Liste dert balanceBoughtsBudgetOfSetUpToIndex rekursiv
     * aus den Werten von Index-1
     *
     *
     * @param index Index für den die Werte gerade dynamisch in die Tabelle
     * eingetragen werden
     */
    public Integer getPositionMyIntObjektInAdjazensliste(MyInteger objekt) {

        Integer position = 0;

        for (int i = 0; i < arrayList.size(); i++) {
            //für jeden sell 
            if (arrayList.get(i).get(0) == objekt) {//irgendwann ist hier Nullpointer, bei index?

                position = i;
            }
        }
        return position;
    }

    public ArrayList<MyInteger> getBoughtsOfSell(MyInteger sell) {
        ArrayList<MyInteger> newBought = new ArrayList<>();
        newBought.addAll(arrayList.get(getPositionMyIntObjektInAdjazensliste(sell))); //hier holen wir alle für den Sell benötigten Boughts
        System.out.println("PositionMyIntObjektInAdjazensliste : " + arrayList.get(getPositionMyIntObjektInAdjazensliste(sell)));
        newBought.remove(0);
        System.out.println("Boughts of Sell " + sell + " : " + newBought);
        return newBought;
    }

    public void setBalanceBoughtsBudgetOfSetUpToIndex(int index) {
        /**
         * balance Variable um sie in balanceBoughtsBudgetOfSetUpToIndex zu
         * speichern
         */
        int balance = 0;
        int budget = 0;
        /**
         * Summe Boughts Variable um sie in balanceBoughtsBudgetOfSetUpToIndex
         * zu speichern
         */
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

        Iterator it = newBought.iterator();
        while (it.hasNext()) {

            sumNewBoughts = sumNewBoughts + ((MyInteger) it.next()).i;

        }
//-----------------------------------------------------hier wird balance rekursiv aus den Werten von Index-1 berechnet
        if (index > 0) {
            balance = balanceBoughtsBudgetOfSetUpToIndex.get(index - 1).get(0)
                    + sortedSells.get(index).i - sumNewBoughts;

        } else {
            balance = sortedSells.get(index).i - sumNewBoughts;
        }
        //-----------------------------------------------------hier wird budget  rekursiv aus den Werten von Index-1 berechnet
        if (index > 0) {
            budget = Integer.min(balanceBoughtsBudgetOfSetUpToIndex.get(index - 1).get(2), balanceBoughtsBudgetOfSetUpToIndex.get(index - 1).get(0)
                    - sumNewBoughts);

        } else {
            budget = -sumNewBoughts;
        }
        //hier wird ein neuer Eintrag daraus generiert
        ArrayList<Integer> newEintrag = new ArrayList<>();

        newEintrag.add(balance);
        newEintrag.add(sumNewBoughts);
        newEintrag.add(budget);

        if (balanceBoughtsBudgetOfSetUpToIndex.size() == index) {
            balanceBoughtsBudgetOfSetUpToIndex.add(index, newEintrag);
            //drucken

            System.out.println("Balance, Bought, Budget: " + balanceBoughtsBudgetOfSetUpToIndex.get(index).toString());
        } else {
            System.err.println("Error");
        }

    }

    public void sortedSellsOut(String dateiname) {
        newTool.out(this, sortedSells, dateiname);
//        ArrayList<MyInteger> allready = new ArrayList<>();
//      //  File file2 = new File("C:\\Users\\Soyo\\Desktop\\Bachelorarbeit\\Daten.txt");
//        File file2 = new File("X:\\speedee\\mitarbeiter\\sonja_schäfer\\Bachelorarbeit\\SortedSellsInstance.txt");
//        try {
////            file.mkdirs();
//            file2.createNewFile();
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }
//        ArrayList<Integer> werte = new ArrayList<>();
//        werte.add(0, 0);
//
//        try {
//            PrintWriter pr = new PrintWriter(file2);
//            pr.println(0);
//            Integer newValue;
//            //temp weil ich nur die ersten einräge der adjazenslisten brauche um IndexOf zu machen
//            ArrayList<MyInteger> temp = new ArrayList<>();
//            for (int k = 0; k < this.sortedSells.size(); k++) {
//                temp.add((this.arrayList.get(k)).get(0));
//            }
//
//            for (int i = 0; i < this.sortedSells.size(); i++) {
//                Integer currentIndexInArrayList = temp.indexOf(this.sortedSells.get(i));//indexOf kann ich nicht verwenden ich suche in arraylists und will mitt den ersten einträgen vergleichen 
//                ArrayList<MyInteger> newB = new ArrayList<>();
//                newB.addAll(arrayList.get(currentIndexInArrayList)); //hier holen wir alle für den Sell benötigten Boughts
//                newB.remove(0);
//                System.out.println("newB    :   "+newB);
//                newB.removeAll(allready);//hier entfernen wir alle, die schon gekauft waren
//                allready.addAll(newB);
//                 System.out.println("newB ohne Allready   :   "+newB);
//                for (int j = 0; j < newB.size(); j++) {
//                    newValue = werte.get(werte.size() - 1) - (newB.get(j).i);
//                    werte.add(newValue);
//                    pr.println(newValue);
//                }
//                newValue = werte.get(werte.size() - 1) + this.sortedSells.get(i).i;
//                werte.add(newValue);
//                pr.println(newValue);
//            }
//            pr.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("No such file exists.");
//        }
//        System.out.println("Werte: " + werte);

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
