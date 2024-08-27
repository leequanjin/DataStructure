/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DonationList;

/**
 *
 * @author Heng Pei Lin
 */
public class Jacket extends Apparel {

    public Jacket(){}
    
    public Jacket(String id, String donorID, String note, String size, String color, String condition, String brand){
        super(id, donorID, note, size, color, condition, brand);
    }
    
    @Override
    public String getType() {
        return "Jacket";
    }
    
    @Override
    public String toString(){
        return super.toString();
    }
    
}
