/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DonationList;

/**
 *
 * @author Asus
 */
public class Money {
    private double amount;
    
    public Money(){}
    
    public Money(double amount){
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
