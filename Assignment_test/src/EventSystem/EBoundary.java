/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EventSystem;

import adt.LinkedList;
import adt.LinkedListInterface;
import adt.Node;
import VolunteerSubsystem.EventVolunteer;
import VolunteerSubsystem.Volunteer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Clarist Liew
 */
public class EBoundary {
    
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String RESET = "\u001B[0m";
    
    
    public static int eventMainMenu() {

        System.out.println("Event Management Subsystem:");
        String[] volMenu = {
            "Add a new event",
            "Add a new ticket",
            "Add a new sponsorship",
            "Remove an event",
            "Search an event",
            "Amend event details",
            "List all events",
            "Remove an event from a volunteer",
            "List all events for a volunteer",
            "Generate summary reports",
            "Exit"
        };

        return EControl.menuIntReturn(volMenu);
        
    }
    
    public static void displayMenu(String[] selectionList){

        for (int i = 0; i < selectionList.length; i++) {
            System.out.println((i + 1) + ". " + selectionList[i]);
        }
        
        System.out.print("Enter your choice: ");
    }
    
    public static void nextLine(){
        System.out.println();
    }
    
    public static void inputChoice(){
        System.out.print("Enter your choice: ");
    }
    
    public static void reEnter(){
        System.out.print("Enter again: ");
    }
    
    public static void inputYN(String sentence){
        System.out.print("\n" + sentence + "\nPlease enter Y / N: ");
    }
    
    public static void inputEventName(){
        System.out.print("Event Name: ");
    }
    
    public static void inputDate(){
        System.out.print("Date (dd/MM/yyyy): ");
    }
    
    public static void inputTime(){
        System.out.print("Time (HH:mm) in 24-hour format: ");
    }
    
    public static void inputLocation(){
        System.out.print("Location: ");
    }
    
    public static void inputTicketEventID(){
        System.out.print("Enter Event ID for the Ticket: ");
    }
    
    public static void inputNumTicketType(){
        System.out.print("How many ticket types would you like to add?: ");
    }
    
    public static void inputTicketType(){
        System.out.print("Ticket Type: ");
    }
    
    
    public static void inputTicketPrice(){
        System.out.print("Ticket Price: RM");
    }
    
    public static void inputTicketAmt(){
        System.out.print("Amount of Tickets: ");
    }
    
    public static void inputSponsEventID(){
        System.out.print("Enter Event ID for the Sponsorship: ");
    }
    
    public static void inputSponsName(){
        System.out.print("Sponsor Name: ");
    }
    
    public static void inputSponsAmt(){
        System.out.print("Sponsor Amount: RM");
    }
    
    public static void displayEvent(){
        System.out.println("Available events:");
    }
    
    public static void inputRemoveEventID(){
        System.out.print("Enter the Event ID to remove: ");
    }
    
    public static int searchEventMenu(){
        System.out.println("Search Event by:");
        String[] searchEventMenu = {
            "Event ID",
            "Event Date"
        };

        return EControl.menuIntReturn(searchEventMenu);
        
    }
    
    public static void inputEventID(){
        System.out.print("Enter the Event ID: ");
    }
    
     public static void disEventDetails(Event event) {
         SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println( "\nEvent Found: " );
        System.out.printf("%-15s : %s\n", "Event ID", event.getEventID());
        System.out.printf("%-15s : %s\n", "Event Name", event.getEventName());
        System.out.printf("%-15s : %s\n", "Date", dateFormat.format(event.getDate()));
        System.out.printf("%-15s : %s\n", "Time", event.getTime());
        System.out.printf("%-15s : %s\n", "Location", event.getLocation());
    }

    public static void disEventByDate(int show, Event event) {
        System.out.printf("%-6s %-3s %-2s %-8s", "Event " , show , ": " , event.getEventID());
        System.out.printf("%-4s %-50s\n", " -> ", event.getEventName());
    }

    public static void disTicketDetails(int ticketCount, Ticket ticket) {
        System.out.printf("%-5d | %-10s | %-15s | %-10.2f\n",
                ticketCount,
                ticket.getTicketID(),
                ticket.getTicketType(),
                ticket.getTicketPrice());
    }

    public static void disSponsorshipDetails(int sponsorshipCount, Sponsorship sponsorship) {
        System.out.printf("%-5d | %-10s | %-20s | %-15.2f\n",
                sponsorshipCount,
                sponsorship.getSponsorID(),
                sponsorship.getSponsorName(),
                sponsorship.getSponsorAmount());
    }
    
    public static void inputUpdateEventID(){
        System.out.print("Enter the Event ID to update: ");
    }
    
    
     public static int displayUpdateMenu() {
        System.out.println("\nWhat would you like to update?");
        String[] updateMenu = {
            "Event Name",
            "Event Date",
            "Event Time",
            "Event Location",
            "Tickets",
            "Sponsorships",
            "Save Changes"
        };

        return EControl.menuIntReturn(updateMenu);
    }

    public static int displayTicketUpdateMenu() {
        System.out.println("\nWhat would you like to update?");
        String[] updateMenu = {
            "Ticket Type and Price",
            "Ticket Status"
        };

        return EControl.menuIntReturn(updateMenu);
    }

    public static int displaySponsorshipUpdateMenu() {
        System.out.println("\nWhat would you like to update?");
        String[] updateMenu = {
            "Sponsor Name",
            "Sponsor Amount",
            "Both Name and Amount"
        };

        return EControl.menuIntReturn(updateMenu);
    }

    public static void displayTicketDetails(int ticketCount, Ticket ticket) {
        System.out.printf("%-5d | %-10s | %-20s | %-10.2f | %-10s\n",
            ticketCount,
            ticket.getTicketID(),
            ticket.getTicketType(),
            ticket.getTicketPrice(),
            ticket.getTicketStatus());
    }

    public static void displaySponsorshipDetails(int sponsorshipIndex, Sponsorship sponsorship) {
        System.out.printf("%-5d | %-12s | %-30s | %-10.2f\n",
            sponsorshipIndex,
            sponsorship.getSponsorID(),
            sponsorship.getSponsorName(),
            sponsorship.getSponsorAmount());
    }

    

    public static void inputNewTicketType(String selectedTicketType) {
        System.out.print("Enter new Ticket Type (Current: " + selectedTicketType + "): ");
    }

    public static void inputNewTicketPrice() {
        System.out.print("Enter new Ticket Price: RM");
    }

    public static void inputNewTicketStatus(String currentStatus) {
        System.out.print("Enter new Ticket Status (Current: " + currentStatus + "): ");
    }

    public static void inputNewSponsorName(String currentSponsorName) {
        System.out.print("Enter new Sponsor Name (Current: " + currentSponsorName + "): ");
    }

    public static void inputNewSponsorAmount() {
        System.out.print("Enter new Sponsor Amount: RM");
    }

    public static void displayEventUpdatedDetails(Event event) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.printf("%-15s : %s\n", "Event ID", event.getEventID());
        System.out.printf("%-15s : %s\n", "Event Name", event.getEventName());
        System.out.printf("%-15s : %s\n", "Date", dateFormat.format(event.getDate()));
        System.out.printf("%-15s : %s\n", "Time", event.getTime());
        System.out.printf("%-15s : %s\n", "Location", event.getLocation());
    }

    public static void displayTicketUpdatedDetails(Ticket updatedTicket) {
        
        System.out.printf("%-15s : %s\n", "Ticket ID", updatedTicket.getTicketID());
        System.out.printf("%-15s : %s\n", "Ticket Type", updatedTicket.getTicketType());
        System.out.printf("%-15s : %.2f\n", "Ticket Price", updatedTicket.getTicketPrice());
        System.out.printf("%-15s : %s\n", "Ticket Status", updatedTicket.getTicketStatus());
    }

    public static void displaySponsorshipUpdatedDetails(Sponsorship updatedSponsorship) {
        
        System.out.printf("%-15s : %s\n", "Sponsor ID", updatedSponsorship.getSponsorID());
        System.out.printf("%-15s : %s\n", "Sponsor Name", updatedSponsorship.getSponsorName());
        System.out.printf("%-15s : %.2f\n", "Sponsor Amount", updatedSponsorship.getSponsorAmount());
    }

    

    



 

    

    

    

    

    

    
    
    
    
    
    
    
    

    

    

    
    

    

    
    
}
