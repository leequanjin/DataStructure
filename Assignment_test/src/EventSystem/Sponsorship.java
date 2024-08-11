/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EventSystem;

/**
 *
 * @author Clarist Liew
 */
public class Sponsorship {
    
    private String sponsorName;
    private double sponsorAmount;

    public Sponsorship (String sponsorName, double sponsorAmount ) {
        this.sponsorName =sponsorName;
        this.sponsorAmount = sponsorAmount;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }

    public double getSponsorAmount() {
        return sponsorAmount;
    }

    public void setSponsorAmount(double sponsorAmount) {
        this.sponsorAmount = sponsorAmount;
    }
    
    
    
    
    
}
