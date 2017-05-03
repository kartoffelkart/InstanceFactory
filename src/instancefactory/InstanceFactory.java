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
//        tool.superFunctionStatistik(1, 100, 32);/*(1, 100, 32);*/ // todo: hier kann ich Spektrum der Werte vergrößern

        tool.buildIstanceMakeHeuristicsAndOut(1, 50, 8);
        tool.superFunctionStatistik(1, 20, 8);
    }

}
