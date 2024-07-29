/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_test;

/**
 *
 * @author Asus
 */
public class apparel extends item {
    private String color;
    private String condition;
    private String brand;
    
    
    public apparel(){
    }
    
    public apparel(String type, int qty, String note, String color, String condition, String brand){
        super(type, qty, note);
        this.color = color;
        this.condition = condition;
        this.brand = brand;
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
    public String toString(){
        return super.toString()+ 
                String.format("\n%-15s %-2s %10s \n%-15s %-2s %10s \n%-15s %-2s %20s" 
                        + "color" + ":" + color
                        + "condition" + ":" + condition
                        + "brand" + ":" + brand);
    }
}
