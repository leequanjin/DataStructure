/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_test;

/**
 *
 * @author Asus
 */
public class item {
    private int qty;
    
    public item(){}
    
    public item(int qty){
        this.qty = qty;
    }
    
    public int getQty(){
        return qty;
    }
    
    public void setQty(int qty){
        this.qty = qty;
    }
    
    @Override
    public String toString(){
        return String.format("\n%-15s %-2s %-5d" + "Quantity" + ":" + qty);
    }
    
}
