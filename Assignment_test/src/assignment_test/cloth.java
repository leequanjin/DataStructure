/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_test;

/**
 *
 * @author Asus
 */
public class cloth extends item {
    private String size; // S M L

    public cloth(){}
    
    public cloth(int qty, String size){
        super(qty);
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
        return super.toString() + 
                String.format("%-15s %-2s %10f" + "Size" + ":" + size);
    }
    
}
