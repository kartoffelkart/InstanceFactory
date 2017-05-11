/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instancefactory.service;

import java.util.ArrayList;

/**
 *
 * @author Sonja Schäfer sonja_schaefer@gmx.de
 */
public class PositiveSet {
  
    /**
     * Anzahl an Sells PositiveSets
     */
    private Integer positiveSetPLength;
    /**
     * aufsummierte Boughts für das PositiveSet
     */
    private Integer positiveSetPLengthSumBoughts;
    /**
     * Budgets für das PositiveSet
     */
    private Integer positiveSetBudget;
    /**
     * Balance für das PositiveSet
     */
    private Integer positiveSetBalance;
    private Graph graph;

    

    public PositiveSet(Integer sumOfBoughts, Integer positiveSetPLength, Integer positiveSetBudget, Integer positiveSetBalance) {
       
        this.positiveSetPLength = positiveSetPLength;
        this.positiveSetPLengthSumBoughts = sumOfBoughts;
        this.positiveSetBudget = positiveSetBudget;
        this.positiveSetBalance =positiveSetBalance;
    }

    
    public Integer getPositiveSetPLength() {
        return positiveSetPLength;
    }

    public void setPositiveSetPLength(Integer positiveSetPLength) {
        this.positiveSetPLength = positiveSetPLength;
    }

    public Integer getPositiveSetPLengthSumBoughts() {
        return positiveSetPLengthSumBoughts;
    }

    public void setPositiveSetPLengthSumBoughts(Integer positiveSetPLengthSumBoughts) {
        this.positiveSetPLengthSumBoughts = positiveSetPLengthSumBoughts;
    }

    public Integer getPositiveSetBudget() {
        return positiveSetBudget;
    }

    public void setPositiveSetBudget(Integer positiveSetBudget) {
        this.positiveSetBudget = positiveSetBudget;
    }

    public Integer getPositiveSetBalance() {
        return positiveSetBalance;
    }

    public void setPositiveSetBalance(Integer positiveSetBalance) {
        this.positiveSetBalance = positiveSetBalance;
    }

    @Override
    public String toString() {
        return "PositiveSet{" + "positiveSetPLength=" + positiveSetPLength + ", positiveSetPLengthSumBoughts=" + positiveSetPLengthSumBoughts + ", positiveSetBudget=" + positiveSetBudget + ", positiveSetBalance=" + positiveSetBalance + '}';
    }
    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }
}
