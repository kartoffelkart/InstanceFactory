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
        for (int k = 0; k < partitions.size(); k++) {
            randomOrdering.add((partitions.get(k).arrayList.get(0)).get(0));
        }

        tool.buildInstance(partitions);
        instance = partitions.get(0);
        System.out.println("Balance: " + instance.balance);
        System.out.println("Budget: " + instance.budget);

        System.out.println("Sorted Sells: ");
        instance.sortedSellsOut("sortedSells");
        System.out.println("Random: ");

        tool.out(instance, randomOrdering, "random");
        System.out.println("order: "+ randomOrdering.toString());

        System.out.println("AfterSwap: ");
        tool.out(instance, tool.function(instance, randomOrdering), "afterSwap");
//        System.out.println(tool.function(instance, randomOrdering).toString());
    }
//    clc; clear all; close all;
// format short g;
//% dbstop if error;
//
//%Graph Random
//
//fileID = fopen('randomDaten.txt');
//
//formatSpec = '%d';
//
//A = fscanf(fileID,formatSpec);
//
//figure('name','Random');
//plot(A);
//grid on;
//title('Random');
//
//%Graph Sorted Sells
//
//fileID = fopen('sortedSellsDaten.txt');
//
//formatSpec = '%d';
//
//A = fscanf(fileID,formatSpec);
//
//figure('name','Sorted Sells');
//plot(A);
//grid on;
//% xPlot=-2:0.01:50;
//% xticks(0:1:50);
//% xticklabels({'x = 0','x = 5','x = 10'})
//% set(gca,'xLim',[-2 50])
//% x = linspace(1,1000);
//% set(gca,'xTick',1 2 3 4 5 6 7 );
//% set(gca,'XtickLabel');
//%set(AX(1),'xtick',[-2 -1 -1 0 1 2]); 
//title('Sorted Sells');
//
//% Graph AfterSwap
//
//fileID = fopen('afterSwapDaten.txt');
//
//
//formatSpec = '%d';
//
//A = fscanf(fileID,formatSpec);
//
//figure('name','AfterSwap');
//plot(A);
//grid on;
//title('AfterSwap');
}
