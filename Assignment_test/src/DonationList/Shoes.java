/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DonationList;

/**
 *
 * @author Heng Pei Lin
 */
public class Shoes extends Apparel{
    public String detail;
    
    public Shoes(){}
    
    public Shoes(String id, int qty, String note, String color, String condition, String brand, String detail){
        super(id, qty, note, color, condition, brand);
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
                "\nDetails: " + detail;
    }
}
