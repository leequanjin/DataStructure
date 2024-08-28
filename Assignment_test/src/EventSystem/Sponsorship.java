/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EventSystem;

import java.io.Serializable;



/**
 *
 * @author Clarist Liew
 */
public class Sponsorship implements Serializable {
    private String eventID;
    private String sponsorID;
    private String sponsorName;
    private double sponsorAmount;

    public Sponsorship (String eventID,  String sponsorID, String sponsorName, double sponsorAmount ) {
        this.eventID = eventID;
        this.sponsorID = sponsorID;
        this.sponsorName =sponsorName;
        this.sponsorAmount = sponsorAmount;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }
    

    public String getSponsorID() {
        return sponsorID;
    }

    public void setSponsorID(String sponsorID) {
        this.sponsorID = sponsorID;
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
    
    
   @Override
    public String toString(){
        return "Sponsor ID: " +sponsorID + "\n" + "Sponsor Name: " +sponsorName + "\n" +"Sponsor Amount: RM" +sponsorAmount;
    } 
    
    
}
