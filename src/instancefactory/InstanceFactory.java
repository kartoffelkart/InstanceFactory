/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instancefactory;

import instancefactory.service.Partition;
import instancefactory.service.Tools;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Sonja Schäfer <sonja_schaefer@gmx.de>
 */
public class InstanceFactory {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Tools tool = new Tools();

        ArrayList<Integer> randomIntArrayList = tool.getRandomIntArray(1, 100, 10);
//        System.out.println(randomIntArrayList.toString());
        ArrayList<Partition> partitions = new ArrayList<>();
//        String choice = tool.getChoice(10, 20, 70);
//        System.out.println(choice);

       //MAKE PARTITIONS
        Partition partition1 = new Partition();
        ArrayList<Integer> l = new ArrayList<>();
        l.add(randomIntArrayList.get(0));
        randomIntArrayList.remove(0);
        partition1.arrayList.set(0, l);
        partitions.add(partition1);

        while (!randomIntArrayList.isEmpty()) {
            if (tool.getCoin(50, 50).equals("sell")) {

                System.err.println("sell");

                Partition partition = new Partition();
                ArrayList<Integer> m = new ArrayList<>();
                m.add(randomIntArrayList.get(0));
                randomIntArrayList.remove(0);
                partition.arrayList.set(0, m);
                partitions.add(partition);

            } else {
                partitions.get(partitions.size() - 1).arrayList.get(0).add(randomIntArrayList.get(0));
                randomIntArrayList.remove(0);
            }
        }
        while (partitions.size() > 1) {
            Partition partition = new Partition();
            Partition partitionA = new Partition();
            Partition partitionB = new Partition();

            partitionA = tool.getRandomPartitionDueToProbality(partitions);

            partitionB = tool.getRandomPartitionDueToProbality(partitions);
            while (partitionA == partitionB) {
                partitionB = tool.getRandomPartitionDueToProbality(partitions);
            }
            partition = tool.makePartition(partitionA, partitionB);
            partitions.set(partitions.indexOf(partitionA), partition);
            partitions.remove(partitionB);

            partitions.get(0).print();
        }
    }

}
