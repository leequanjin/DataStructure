/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.DonationManagement;

import DonationList.*;
import java.util.Date;

/**
 *
 * @author Heng Pei Lin
 */
public class Essentials extends Food {
    
    public Essentials(){
    }
    
    public Essentials(String id, String donorID, String note, Date expiryDate, int weight, String status, String detail){
        super(id, donorID, note, expiryDate, weight, status, detail);
    }
    
    @Override
    public String getType() {
        return "Essentials";
    }
    
    @Override
    public String toString(){
        return super.toString();
    }
}
