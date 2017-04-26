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
        Integer catchMe;

        ArrayList<Partition> partitions = new ArrayList<>();
        Partition instance;
//        ArrayList<Integer> balances = new ArrayList<>();
//        Integer balanceOfInstance ;

        partitions = tool.makeBasicPartitions(1, 50, 32);/*(1, 100, 32);*/ // todo: hier kann ich Spektrum der Werte vergrößern

        ArrayList<MyInteger> randomOrdering = tool.getOrderingOfBasicPartitions(partitions);

        tool.buildInstanceOnBasicPartitions(partitions, 33, 33, 34);

        instance = partitions.get(0);

        //-------------------------------------------------------------------------------------------------------------------------
        System.out.println("Balance: " + instance.balance);
        System.out.println("Budget: " + instance.budget);

        System.out.println("Sorted Sells: ");
//        instance.sortedSellsOut("sortedSells");
        catchMe = tool.out(instance, instance.sortedSells, "sortedSells");

        System.out.println("Random: ");
        catchMe = tool.out(instance, randomOrdering, "random");
//        System.out.println("order: "+ randomOrdering.toString());

        System.out.println("AfterSwap: ");
        ArrayList<MyInteger> orderingHeuristik = new ArrayList();
        orderingHeuristik=tool.getOrderingHeuristik(instance, randomOrdering, "swap");
        catchMe = tool.out(instance,orderingHeuristik , "afterSwap");
//        System.out.println(tool.function(instance, randomOrdering).toString());

        System.out.println("AfterChangeOrder: ");
        orderingHeuristik=tool.getOrderingHeuristik(instance, randomOrdering, "changeOrder");
        catchMe = tool.out(instance,orderingHeuristik , "afterChangeOrder");

       tool.outStatistikN("test");

    }

}
