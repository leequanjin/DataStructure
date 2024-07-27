/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_test;

/**
 *
 * @author Asus
 */
public class shoe extends item {
    private int size; // 3 4 5 6 7 8
    
    public shoe(){
    }
    
    public shoe(int qty, int size){
        super(qty);
        this.size = size;
    }
    
    @Override
    public String toString(){
        return super.toString()+ 
                String.format("\n%-15s %-2s %10d" 
                        + "Shoe size" + ":" + size);
    }
}
