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
public class BalanceBoughtsBudget {

    

    private Integer balance;
    private Integer boughts;
    private Integer budget;

    //KONSTRUKTOR-----------------------------------------
    public BalanceBoughtsBudget(Integer balance, Integer boughts, Integer budget) {
        this.balance = balance;
        this.boughts = boughts;
        this.budget = budget;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getBoughts() {
        return boughts;
    }

    public void setBoughts(Integer boughts) {
        this.boughts = boughts;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }
    @Override
    public String toString() {
        return "BalanceBoughtsBudget{" + "balance=" + balance + ", boughts=" + boughts + ", budget=" + budget + '}';
    }
}
