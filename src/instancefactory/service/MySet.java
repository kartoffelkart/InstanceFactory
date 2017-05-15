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
public class MySet {
  
    /**
     * Anzahl an Sells PositiveSets
     */
    private Integer length;
    /**
     * aufsummierte Boughts für das MySet
     */
    private Integer sumBoughts;
    /**
     * Budgets für das MySet
     */
    private Integer budget;
    /**
     * Balance für das MySet
     */
    private Integer balance;
    private ArrayList<Eintrag> werte;
    private Graph graph;

    

    public MySet(Integer sumOfBoughts, Integer positiveSetPLength, Integer positiveSetBudget, Integer positiveSetBalance) {
       
        this.length = positiveSetPLength;
        this.sumBoughts = sumOfBoughts;
        this.budget = positiveSetBudget;
        this.balance =positiveSetBalance;
    }

    
    public Integer getPositiveSetPLength() {
        return length;
    }

    public void setPositiveSetPLength(Integer positiveSetPLength) {
        this.length = positiveSetPLength;
    }

    public Integer getPositiveSetPLengthSumBoughts() {
        return sumBoughts;
    }

    public void setPositiveSetPLengthSumBoughts(Integer positiveSetPLengthSumBoughts) {
        this.sumBoughts = positiveSetPLengthSumBoughts;
    }

    public Integer getPositiveSetBudget() {
        return budget;
    }

    public void setPositiveSetBudget(Integer positiveSetBudget) {
        this.budget = positiveSetBudget;
    }

    public Integer getPositiveSetBalance() {
        return balance;
    }

    public void setPositiveSetBalance(Integer positiveSetBalance) {
        this.balance = positiveSetBalance;
    }

    @Override
    public String toString() {
        return "PositiveSet{" + "positiveSetPLength=" + length + ", positiveSetPLengthSumBoughts=" + sumBoughts + ", positiveSetBudget=" + budget + ", positiveSetBalance=" + balance + '}';
    }
    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }
}
