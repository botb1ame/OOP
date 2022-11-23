/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package week11part2.newpackage;


import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private int ID;
    private int money;

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setID(int iD) {
        ID = iD;
    }
    public int getID() {
        return ID;
    }
    public void setMoney(int money) {
        this.money = money;
    }
    public int getMoney() {
        return money;
    }

    public Student(){
        this("", 0, 0);
    }
    public Student(String name, int ID, int money){
        this.setName(name);
        this.setID(ID);
        this.setMoney(money);
    }
}
