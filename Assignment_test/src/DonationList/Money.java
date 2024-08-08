/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DonationList;

/**
 *
 * @author Asus
 */
public class Money extends Item{
    private double amount;
    
    public Money(){}
    
    public Money(String id, double amount){
        super(id);
        this.amount = amount;
    }
    
    public double getAmount(){
        return amount;
    }
    
    public void setAmount(double amount){
        this.amount = amount;
    }
    
    @Override
    public String getType() {
        return "Money";
    }
    
    @Override
    public String toString(){
        return super.toString() + 
                "\nAmount: RM" + amount;
    } 
}
