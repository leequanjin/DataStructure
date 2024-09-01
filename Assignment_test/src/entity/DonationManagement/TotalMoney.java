/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.DonationManagement;

/**
 *
 * @author Asus
 */
public class TotalMoney {
    private double ttlBank;
    private double ttlCash;

    public TotalMoney(double ttlBank, double ttlCash){
        this.ttlBank = ttlBank;
        this.ttlCash = ttlCash;
    }
    
    public double getTtlBank() {
        return ttlBank;
    }

    public void setTtlBank(int ttlBank) {
        this.ttlBank = ttlBank;
    }
    
    public double getTtlCash() {
        return ttlCash;
    }

    public void setTtlCash(int ttlCash) {
        this.ttlCash = ttlCash;
    }
    
    @Override
    public String toString(){
        return String.format("%-20s %-4s %-5.2f /n%-20s %-4s %-5.2f ", 
                "Total bank amount", ": RM", ttlBank, 
                "Total cash amount", ": RM", ttlCash);
    }
}
