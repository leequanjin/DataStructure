/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_test;

import java.util.Date;

/**
 *
 * @author Asus
 */
public class food extends item{
    private Date expiryDate;
    private int weight; // in gram
    
    public food(){}
    
    public food(int qty, Date expiryDate, int weight){
        super(qty);
        this.expiryDate = expiryDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return super.toString() + 
                String.format("\n%-15s %-2s %10f \n%-15s %-2s %-8d %5s " 
                        + "Expiry date" + ":" + expiryDate
                        + "Weight" + ":" + weight + " gram");
    }
    
}
