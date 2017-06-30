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
public class IntegerOut {
    private Integer number;

    public IntegerOut(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
    @Override
    public String toString() {
        return number.toString();
    }
}
