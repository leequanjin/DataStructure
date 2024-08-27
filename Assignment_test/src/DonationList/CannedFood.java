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
public class CannedFood extends Food {
    
    public CannedFood(){
    }
    
    public CannedFood(String id, String donorID, String note, Date expiryDate, int weight, String status, String detail){
        super(id, donorID, note, expiryDate, weight, status, detail);
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
