/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DonationList;

import java.io.Serializable;

/**
 *
 * @author Heng Pei Lin
 */
public abstract class Item implements Serializable {
    String id;
    String donorID;
    String status;
    
    public Item(){}
    
    public Item(String id, String donorID){
        this.id = id;
        this.donorID = donorID;
        status = "Available";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDonorID() {
        return donorID;
    }

    public void setDonorID(String donorID) {
        this.donorID = donorID;
    }
    
    public abstract String getType();
    
    @Override
    public String toString() {
        return  String.format("| %-10s | %-10s | %-15s | %-15s |", id, donorID, getType(), status);
    }
}
