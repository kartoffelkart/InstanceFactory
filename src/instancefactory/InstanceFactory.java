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
        while (it.hasNext()) {
            System.out.println("elementare Partition: " + ((Partition) it.next()).toString() + "\n");
        }

        tool.buildInstance(partitions);
        instance = partitions.get(0);
        System.out.println("Balance: " + instance.balance);
        System.out.println("Budget: " + instance.budget);
        File file2 = new File("C:\\Users\\Soyo\\Desktop\\Bachelorarbeit\\Daten.txt");
        try {
//            file.mkdirs();
            file2.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        ArrayList<Integer> werte = new ArrayList<>();
        werte.add(0, 0);

        try {
            PrintWriter pr = new PrintWriter(file2);
            pr.println(0);
            Integer newValue;
            //temp weil ich nur die ersten einräge der adjazenslisten brauche um IndexOf zu machen
            ArrayList<MyInteger> temp = new ArrayList<>();
            for (int k = 0; k < instance.sortedSells.size(); k++) {
                temp.add((instance.arrayList.get(k)).get(0));
            }

            for (int i = 0; i < instance.sortedSells.size(); i++) {
                Integer currentIndexInArrayList = temp.indexOf(instance.sortedSells.get(i));//indexOf kann ich nicht verwenden ich suche in arraylists und will mitt den ersten einträgen vergleichen 
                for (int j = 1; j < (instance.arrayList.get(currentIndexInArrayList)).size(); j++) {
                    newValue = werte.get(werte.size() - 1) - (instance.arrayList.get(currentIndexInArrayList).get(j).i);
                    werte.add(newValue);
                    pr.println(newValue);
                }
                newValue = werte.get(werte.size() - 1) + instance.sortedSells.get(i).i;
                werte.add(newValue);
                pr.println(newValue);
            }
            pr.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No such file exists.");
        }

    }
}
