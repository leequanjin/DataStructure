/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_test;

/**
 *
 * @author Asus
 */
public class money {
    private double amount;
    
    public money(){}
    
    public money(double amount){
        this.amount = amount;
    }
    
    public double getAmount(){
        return amount;
    }
    
    public void setAmount(double amount){
        this.amount = amount;
    }
    
    @Override
    public String toString(){
        return String.format("\n%-15s %-4s %5.2f" + "Amount" + ": RM" + amount);
    }
}
