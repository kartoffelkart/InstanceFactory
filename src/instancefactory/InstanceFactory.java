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
 * @author Sonja Schäfer <sonja_schaefer@gmx.de>
 */
public class InstanceFactory {

    /**
     * @param args the command line arguments
     */
    private static Tools tool = new Tools();

    public static void main(String[] args) {

        ArrayList<Partition> partitions = new ArrayList<>();
        partitions = tool.makeBasicPartitions();
        Iterator it = partitions.iterator();
        while (it.hasNext()) {
            System.out.println("elementare Partition: "+((Partition)it.next()).toString()+"\n");
        }
    //    System.out.println(partitions.toString());
  //      tool.buildPartitionAndMerge(partitions);

    }
}
