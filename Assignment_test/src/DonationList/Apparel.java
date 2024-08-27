/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DonationList;

/**
 *
 * @author Heng Pei Lin
 */
public class Apparel extends PhysicalItem {
    private String size;
    private String color;
    private String condition;
    private String brand;
    
    public Apparel(){
    }
    
    public Apparel(String id, String donorID, String note, String size, String color, String condition, String brand){
        super(id, donorID, note);
        this.size = size;
        this.color = color;
        this.condition = condition;
        this.brand = brand;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    @Override
    public String getType() {
        return "Apparel";
    }
    
    @Override
    public String toString(){
        return super.toString() +
                String.format(" %-10s | %-10s | %-10s | %-10s |", size, color, condition, brand);
    }
}
