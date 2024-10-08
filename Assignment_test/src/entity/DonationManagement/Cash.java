/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.DonationManagement;


/**
 *
 * @author Heng Pei Lin
 */
public class Cash extends Money {
    public Cash(){
    }
    
    public Cash(String id, String donorID, double amount){
        super(id, donorID, amount);
    }
    
    @Override
    public String getType() {
        return "Cash";
    }
    
    @Override
    public String toString(){
        return super.toString();
    }
}
