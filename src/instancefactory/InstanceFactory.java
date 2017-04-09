/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instancefactory;

import instancefactory.service.MyInteger;
import instancefactory.service.Partition;
import instancefactory.service.Tools;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Sonja Schäfer sonja_schaefer@gmx.de
 *
 */
public class InstanceFactory {

    /**
     * @param args the command line arguments
     */
    private static Tools tool = new Tools();

    public static void main(String[] args) {

        Integer balance = 0;
        ArrayList<Partition> partitions = new ArrayList<>();
        Partition instance;
//        ArrayList<Integer> balances = new ArrayList<>();
//        Integer balanceOfInstance ;

        partitions = tool.makeBasicPartitions();
        Iterator it = partitions.iterator();
        ArrayList<MyInteger> randomOrdering = new ArrayList<>();
        while (it.hasNext()) {
            //ich gehe die Elemanterpartitionen durch und hole mir aus der Adjazensliste den ersten-ersten eintrag
            randomOrdering.add(((Partition) it.next()).arrayList.get(0).get(0));
            System.out.println("elementare Partition: " + ((Partition) it.next()).toString() + "\n");
        }

        tool.buildInstance(partitions);
        instance = partitions.get(0);
        tool.out(instance, instance.sortedSells);
        tool.out(instance, randomOrdering);

        instance.sortedSellsOut();
        System.out.println("Balance: " + instance.balance);
        System.out.println("Budget: " + instance.budget);

    }
}
