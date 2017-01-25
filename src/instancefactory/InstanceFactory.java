/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instancefactory;

import instancefactory.service.Tools;
import java.util.ArrayList;

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
        System.out.println(randomIntArrayList.toString());

        String choice = tool.getChoice(10, 10, 80);
        System.out.println(choice);

    }

}
