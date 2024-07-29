/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_test;

/**
 *
 * @author Asus
 */
public class shoe extends apparel {
    private int size; // 3 4 5 6 7 8
    
    
    public shoe(){
    }
    
    public shoe(String type, int qty, String note, String color, String condition, String brand, int size){
        super(type, qty, note, color, condition, brand);
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    @Override
    public String toString(){
        return super.toString()+ 
                String.format("\n%-15s %-2s %10d" 
                        + "size" + ":" + size);
    }
}
