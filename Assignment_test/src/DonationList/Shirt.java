/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DonationList;

/**
 *
 * @author Heng Pei Lin
 */
public class Shirt extends Apparel{
    
    public Shirt(){}
    
    public Shirt(String id, int qty, String note, String color, String condition, String brand){
        super(id, qty, note, color, condition, brand);
    }
    
    @Override
    public String getType() {
        return "Shirt";
    }
    
    @Override
    public String toString(){
        return super.toString();
    }
}
