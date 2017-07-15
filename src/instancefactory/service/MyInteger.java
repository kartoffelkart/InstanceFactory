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
public class MyInteger {

    /**
     *
     */
    public final Integer i;
//    public Integer balance;

    public MyInteger() {
        this.i = null;
        System.out.println("MyInteger wurde nicht gesetzt");
    }

    public MyInteger(Integer i) {
        this.i = i;
    }

    @Override
    public String toString() {
        return i.toString();
    }

}
