/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instancefactory.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Sonja Schäfer <sonja_schaefer@gmx.de>
 */
public class Tools {

    int getBudgetOfTheFirstNodes(ArrayList<Boolean> boughtList) {
        int budget = 0;
        return budget;
    }

    ArrayList<Integer> getRandomIntArray(int min, int max, Integer size) {
        ArrayList<Integer> randomIntArrayList = new ArrayList<>();
        int it = 0;
        while (it < size) {

            randomIntArrayList.add(ThreadLocalRandom.current().nextInt(min, max + 1));
            it++;
        }
        return randomIntArrayList;
    }
}
