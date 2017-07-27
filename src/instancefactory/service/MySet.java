/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instancefactory.service;


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
    private MyArrayList<Eintrag> eintraege;
    private Graph graph;
private MyArrayList<MyInteger> sortedSells;

   
    public MySet(Integer sumOfBoughts, Integer positiveSetPLength, Integer positiveSetBudget, Integer positiveSetBalance) {
       
        this.length = positiveSetPLength;
        this.sumBoughts = sumOfBoughts;
        this.budget = positiveSetBudget;
        this.balance =positiveSetBalance;
    }

   

  

   
  
    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getSumBoughts() {
        return sumBoughts;
    }

    public void setSumBoughts(Integer sumBoughts) {
        this.sumBoughts = sumBoughts;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public MyArrayList<Eintrag> getEintraege() {
        return eintraege;
    }

    public void setEintraege(MyArrayList<Eintrag> eintraege) {
        this.eintraege = eintraege;
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }
    public MyArrayList<MyInteger> getSortedSells() {
        return sortedSells;
    }

    public void setSortedSells(MyArrayList<MyInteger> sortedSells) {
        this.sortedSells = sortedSells;
    }
    

   @Override
    public String toString() {
        return "PositiveSet{" + "positiveSetPLength=" + length + ", positiveSetPLengthSumBoughts=" + sumBoughts + ", positiveSetBudget=" + budget + ", positiveSetBalance=" + balance + '}';
    }

}