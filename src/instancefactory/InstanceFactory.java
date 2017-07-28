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

//        Partition inst0;
//        Partition instA;
//        Partition instB;
//        Partition instC;
//        Partition instD;
//        Partition instE;
         Partition instF;
          Partition instG;

//        inst0 = tool.getBspPartition();
//
//        instA = tool.getBspA();
//        instB = tool.getBspB();
//        instC = tool.getBspC();
//        instD = tool.getBspD();
//        instE = tool.getBspE();
        instF = tool.getBspF();
        instG = tool.getBspG();

//        
//         Partition instance = new Partition();
//        instance = tool.getBspPartition();
//        tool.superFunctionStatistik(1, 100, 32);/*(1, 100, 32);*/ // todo: hier kann ich Spektrum der Eintraege vergrößern
        System.out.println("makeSortedSellsUnionAndBudgetAndBalance");
        Partition p = new Partition();
        Partition p1 = instF;
        Partition p2 = instG;

        tool.makeSortedSellsUnionAndBudgetAndBalance(p, p1, p2);

//        MyArrayList<MyInteger> expResult = instance.sortedSells;
//        MyArrayList<MyInteger> result = p.sortedSells;
//        Integer expResult = instance.getBudget();
//        Integer result = p.getBudget();
        Integer expResult = -8;
        Integer result = p.getBalance();

//        System.out.println("expResult : "+expResult);
//        System.out.println("result : "+result);
        System.out.println("expResult : " + expResult);
        System.out.println("result : " + result);

        System.out.println("FERTIG               !!!!!!!!!!!!!!!!!");

        int min = 1;
        int max = 100;
        int size = 10;

        tool.buildIstanceMakeHeuristicsAndOut(min, max, size);

        String dateiname = "test";
        min = 1;
        max = 30;
        Integer maxKnotenAnzahl = 15;
        Integer schrittlaenge = 2;
        Integer pool = 5;

        tool.outStatistikN(dateiname, min, max, maxKnotenAnzahl, schrittlaenge, pool);
    }

}
