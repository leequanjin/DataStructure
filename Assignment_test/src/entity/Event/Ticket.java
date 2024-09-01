/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.Event;

import java.io.Serializable;



/**
 *
 * @author Clarist Liew
 */
public class Ticket implements Serializable {
    
    private String eventID;
    private String ticketID;
    private String ticketType;
    private double ticketPrice;
    private String ticketStatus;

    public Ticket(String eventID,String ticketID, String ticketType, double ticketPrice , String ticketStatus) {
        this.eventID = eventID;
        this.ticketID = ticketID;
        this.ticketType = ticketType;
        this.ticketPrice = ticketPrice;
        this.ticketStatus = ticketStatus;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
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

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    @Override
    public String toString(){
        return "Ticket ID: " + ticketID + "\n" + 
               "Ticket Type: " + ticketType + "\n" + 
               "Ticket Amount: RM" + String.format("%.2f", ticketPrice) + "\n" + 
               "Ticket Status: " + ticketStatus;
    }

    
    
    
}
