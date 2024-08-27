/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EventSystem;


import java.util.Date;
/**
 *
 * @author Clarist Liew
 */
public class Sponsorship extends Event {
    
    private String sponsorID;
    private String sponsorName;
    private double sponsorAmount;

    public Sponsorship (String eventID, String eventName, Date date, String time,  String location, String sponsorID, String sponsorName, double sponsorAmount ) {
        super(eventID,eventName,date,time,location);
        this.sponsorID = sponsorID;
        this.sponsorName =sponsorName;
        this.sponsorAmount = sponsorAmount;
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
    
    public String getType() {
        return "Sponsorship";
    }
   @Override
    public String toString(){
        return super.toString() + "\n" + "Sponsor ID: " +sponsorID + "\n" + "Sponsor Name: " +sponsorName + "\n" +"Sponsor Amount: RM" +sponsorAmount;
    } 
    
    
}
