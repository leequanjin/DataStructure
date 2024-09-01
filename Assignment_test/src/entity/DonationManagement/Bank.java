/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.DonationManagement;

import DonationList.*;

/**
 *
 * @author Heng Pei Lin
 */
public class Bank extends Money {
    private String bankName;
    
    public Bank(){
    }
    
    public Bank(String id, String donorID, double amount, String bankName){
        super(id, donorID, amount);
        this.bankName = bankName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    
    @Override
    public String getType() {
        return "Bank";
    }
    
    @Override
    public String toString(){
        return super.toString() + 
                String.format(" %-15s |", bankName);
    }
}
