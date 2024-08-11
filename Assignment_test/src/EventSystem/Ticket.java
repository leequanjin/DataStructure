/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EventSystem;

/**
 *
 * @author Clarist Liew
 */
public class Ticket {
    
    private String ticketID;
    private String ticketType;
    private double ticketPrice;

    public Ticket(String ticketID, String ticketType, double ticketPrice) {
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
    
    
    
}
