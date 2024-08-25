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
public class BoxedGoods extends Food {
    
    public BoxedGoods(){
    }
    
    public BoxedGoods(String id, String donorID, int qty, String note, Date expiryDate, int weight, String status, String detail){
        super(id, donorID, qty, note, expiryDate, weight, status, detail);
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
