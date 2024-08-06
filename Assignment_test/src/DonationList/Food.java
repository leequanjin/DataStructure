/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DonationList;

import java.util.Date;

/**
 *
 * @author Asus
 */
public class Food extends Item {
    private Date expiryDate;
    private int weight; // in gram
    private String status;
    
    public Food(){}
    
    public Food(String type, int qty, String note, Date expiryDate, int weight, String status){
        super(type, qty, note);
        this.expiryDate = expiryDate;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return super.toString() + 
                String.format("\n%-15s %-2s %10f \n%-15s %-2s %-8d %5s \n%-15s %-2s %-8d %10s" 
                        + "Expiry date" + ":" + expiryDate
                        + "Weight" + ":" + weight + " gram"
                        + "Status" + ":" + status);
    }
}
