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
public class Eintrag {
  private Integer value;
  private MyInteger node;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public MyInteger getNode() {
        return node;
    }

    public void setNode(MyInteger node) {
        this.node = node;
    }
}
