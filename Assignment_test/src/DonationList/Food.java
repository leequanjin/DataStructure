/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DonationList;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Heng Pei Lin
 */
public class Food extends PhysicalItem {
    private Date expiryDate;
    private int weight; // in gram
    private String status;
    private String detail;
    
    public Food(){}
    
    public Food(String id, String donorID, String note, Date expiryDate, int weight, String status, String detail){
        super(id, donorID, note);
        this.expiryDate = expiryDate;
        this.weight = weight;
        this.status = status;
        this.detail = detail;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String getType() {
        return "Food";
    }
    
    @Override
    public String toString() {
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        
        // Convert the date to string
        String dateString = formatter.format(expiryDate);
        
        return super.toString() + 
                String.format(" %-11s | %-8d | %-8s | %-20s |", dateString, weight, status, detail);
    }
}
