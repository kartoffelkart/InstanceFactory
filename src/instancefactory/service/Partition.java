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

    public ArrayList<ArrayList<Integer>> balanceandBoughtsOfSetOfIndex = new ArrayList();

    public int probability;
    public int budget;
    //_________________________________________________________________________

    //KONSTRUKTOR
    public Partition() {
        ArrayList<MyInteger> array = new ArrayList<>();
        arrayList = new ArrayList<ArrayList<MyInteger>>();
        probability = 1;
        sortedSells = new ArrayList<>();
    }
//_____________________________________________________________________________

    /**
     * füllt die lange Liste dert balanceandBoughtsOfSetOfIndex rekursiv aus den Werten von Index-1
     * 
     *
     * @param index Index für den die Werte gerade dynamisch in die Tabelle
     * eingetragen werden
     */
    public void setbalanceandBoughtsOfSetOfIndex(int index) {
        /**
         * balance Variable um sie in balanceandBoughtsOfSetOfIndex zu speichern
         */
        int balance = 0;
        /**
         * Summe Boughts Variable um sie in balanceandBoughtsOfSetOfIndex zu
         * speichern
         */
        int sumBoughts = 0;
        /**
         * Variable für die neu getätigten Boughts
         */
        ArrayList<MyInteger> newBought = new ArrayList<>();

        // --------------hier finden wir die position des Integerobjekts, das in SortedSells bei Index steht in der Adjazensliste
        Integer position = 0;

        for (int i = 0; i < arrayList.size(); i++) {
            //für jeden sell 
            if (arrayList.get(i).get(0) == sortedSells.get(index)) {//irgendwann ist hier Nullpointer, bei index?

                position = i;
            }
        }
        //--------------------------------------------------------------------------------------------------------------------
//if (position==null) würde ja gerne prüfen ob das initialisiert wurde
        newBought.addAll(arrayList.get(position)); //hier holen wir alle für den Sell benötigten Boughts
        newBought.remove(0);
        newBought.removeAll(allreadyBought);//hier entfernen wir alle, die schon gekauft waren
        allreadyBought.addAll(newBought);//hier vermerken wir die neu gekauften boughts als gekauft
// berechnet die Summe von Boughts------------------------------

        if (index > 0) {
            sumBoughts = balanceandBoughtsOfSetOfIndex.get(index - 1).get(1);
            Iterator it = newBought.iterator();
            while (it.hasNext()) {

                sumBoughts = sumBoughts + ((MyInteger) it.next()).i;

            }
        } else {

            Iterator it = newBought.iterator();
            while (it.hasNext()) {

                sumBoughts = sumBoughts + ((MyInteger) it.next()).i;
            }
        }
//-----------------------------------------------------hier wird balance und SumBoughts rekursiv aus den Werten von Index-1 berechnet
        if (index > 0) {
            balance = balanceandBoughtsOfSetOfIndex.get(index - 1).get(0)
                    + sortedSells.get(index).i - sumBoughts;

        } else {
            balance = sortedSells.get(index).i - sumBoughts;//balance???????????????????
        }
        //hier wird ein neuer Eintrag daraus generiert
        ArrayList<Integer> newEintrag = new ArrayList<>();

        newEintrag.add(balance);
        newEintrag.add(sumBoughts);
        if (balanceandBoughtsOfSetOfIndex.size() == index) {
            balanceandBoughtsOfSetOfIndex.add(index, newEintrag);
             //drucken
            
            System.out.println("balance and Bought: " + balanceandBoughtsOfSetOfIndex.get(index).toString());
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
