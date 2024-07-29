/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_test;

/**
 *
 * @author Asus
 */
public class cloth extends apparel {
    private String size; // S M L

    public cloth(){}
    
    public cloth(String type, int qty, String note, String color, String condition, String brand, String size){
        super(type, qty, note, color, condition, brand);
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
    
    @Override
    public String toString(){
        return super.toString()+ 
                String.format("\n%-15s %-2s %5s" 
                        + "size" + ":" + size);
    }
    
}
