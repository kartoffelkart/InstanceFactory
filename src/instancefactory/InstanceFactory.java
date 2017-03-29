/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instancefactory;

import instancefactory.service.Partition;
import instancefactory.service.Tools;
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

        Integer budget = 0;
        ArrayList<Partition> partitions = new ArrayList<>();
        Partition instance;
//        ArrayList<Integer> budgets = new ArrayList<>();
//        Integer budgetOfInstance ;

        partitions = tool.makeBasicPartitions();
        Iterator it = partitions.iterator();
        while (it.hasNext()) {
            System.out.println("elementare Partition: " + ((Partition) it.next()).toString() + "\n");
        }

        tool.buildPartitionAndMerge(partitions);
        instance = partitions.get(0);
//        for (int j = 0; j < instance.arrayList.size(); j++) {
//
//            for (int i = 0; i < instance.arrayList.get(0).size(); i++) {
//                budget = budget + instance.arrayList.get(0).get(i).i;
//            }
//            budgets.set(j, budget);
//        }
//        budgetOfInstance= budgets.get(budgets.size()-1);
    }
}
