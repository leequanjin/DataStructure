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
public class Ticket extends Event {
    
    private String ticketID;
    private String ticketType;
    private double ticketPrice;

    public Ticket(String eventID, String eventName, Date date, String time,  String location,String ticketID, String ticketType, double ticketPrice) {
        super(eventID,eventName,date,time,location);
        this.ticketID = ticketID;
        this.ticketType = ticketType;
        this.ticketPrice = ticketPrice;
    }

    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
    
    @Override
    public String getType() {
        return "Ticket";
    }
    
    @Override
    public String toString(){
        return super.toString() + "\n" + "Ticket ID: " +ticketID + "\n" + "Ticket Type: " +ticketType + "\n" +"Ticket Amount: RM" +ticketPrice;
    } 
    
    
    
}
