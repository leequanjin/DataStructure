/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.DonationManagement;

import DonationList.*;

/**
 *
 * @author Heng Pei Lin
 */
public class Shoes extends Apparel{
    public String detail;
    
    public Shoes(){}
    
    public Shoes(String id, String donorID, String note, String size, String color, String condition, String brand, String detail){
        super(id, donorID, note, size, color, condition, brand);
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
    
    @Override
    public String getType() {
        return "Shoes";
    }
    
    @Override
    public String toString(){
        return super.toString() +
                String.format(" %-15s |", detail);
    }
}
