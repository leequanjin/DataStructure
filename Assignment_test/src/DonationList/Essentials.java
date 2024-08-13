/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DonationList;

import java.util.Date;

/**
 *
 * @author Heng Pei Lin
 */
public class Essentials extends Food {
    
    public Essentials(){
    }
    
    public Essentials(String id, int qty, String note, Date expiryDate, int weight, String status, String detail){
        super(id, qty, note, expiryDate, weight, status, detail);
    }
    
    @Override
    public String getType() {
        return "Canned Food";
    }
    
    @Override
    public String toString(){
        return super.toString();
    }
}
