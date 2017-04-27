/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instancefactory;

import instancefactory.service.Graph;
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
         Graph newGraph = new Graph(instance, instance.sortedSells);
       tool.out(newGraph);

        System.out.println("Random: ");
        tool.out(instance, randomOrdering, "random");
//        System.out.println("order: "+ randomOrdering.toString());

        System.out.println("AfterSwap: ");
         Graph currentGraph = new Graph(instance, randomOrdering);
         
        tool.out(instance, tool.getOrderingHeuristik(instance, randomOrdering, "swap",currentGraph), "afterSwap");
//        System.out.println(tool.function(instance, randomOrdering).toString());

        System.out.println("AfterChangeOrder: ");
        tool.out(instance, tool.getOrderingHeuristik(instance, randomOrdering, "changeOrder",currentGraph), "afterChangeOrder");

        tool.outStatistikN("test");

    }

}
