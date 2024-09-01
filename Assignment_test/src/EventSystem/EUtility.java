/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EventSystem;

/**
 *
 * @author Clarist Liew
 */
public class EUtility {
    
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String RESET = "\u001B[0m";
    
    
    public static void intNotInRange(int initial, int end){
        System.out.println(RED + "Invalid choice. Please enter between " + initial + " to " + end + ".\n" + RESET);
    }
    
    public static void invalidIntInput(){
        System.out.println(RED + "Invalid input. Please enter valid integer number.\n" + RESET);
    }
    
    public static void emptyInputErrorMsg(){
        System.out.println(RED +  "Input cannot be empty. Please try again.\n" + RESET);
    }
    
    public static void invalidMenuChoice(){
        System.out.println(RED +  "Invalid choice. Please try again.\n" + RESET);
    }
    
    public static void enterYNOnly(){
        System.out.println(RED + "Please enter Y or N only.\n" + RESET);
    }
    
    public static void invalidNegativeValue(){
        System.out.println(RED + "Please enter a positive number." + RESET);
    }
    
    public static void invalidDateMsg() {
        System.out.println(RED + "Invalid date format. Please use dd/MM/yyyy." + RESET);
    }
    
    public static void invalidMonth() {
        System.out.println(RED +"Invalid month. Please enter a month between 01 and 12." + RESET);
    }
    
    public static void invalidDay() {
        System.out.println(RED +"Invalid day for the given month. Please enter a valid day." + RESET);
    }
    
    public static void invalidTime() {
       System.out.println(RED + "Invalid time format. Please use HH:mm (24-hour format)." + RESET); 
    }
    
    public static void invalidTimeMsg() {
        System.out.println(RED +"Invalid time format. Please use HH:mm (24-hour format)." + RESET);
    }
    
    public static void invalidEventID() {
       System.out.println(RED + "Event ID not found. Please enter a valid Event ID." + RESET); 
    }
    
    public static void emptyTicketType() {
       System.out.println(RED + "Ticket Type cannot be empty. Please enter a valid Ticket Type." + RESET);
    }
    
    public static void emptySponsName() {
       System.out.println(RED + "Sponsor Name cannot be empty. Please enter a valid Sponsor Name." + RESET);
    }
    
    public static void eventNotExistToRemove() {
       System.out.println("No events available to remove.");
    }
    
    public static void eventNotExist() {
       System.out.println( "Event not available yet." );
    }
    
    public static void searchEventNotExist() {
       System.out.println( "No event found matching the search criteria." );
    }
    
    public static void noTicketsFound() {
       System.out.println( "No tickets found for this event." + RESET);
    }
    
    public static void noSponsorshipsFound() {
       System.out.println( "No sponsorships found for this event." + RESET);
    }
    
    public static void eventNotExistToUpdate() {
       System.out.println( "No events available to update." );
    }
    
    public static void invalidSelection(int max) {
        System.out.println(RED + "Invalid selection. Please enter a number between 1 and " + max + "." + RESET);
    }
    
    
    
    
    
    
    // Successful Message
    public static void eventAddedMsg(){
        System.out.println(GREEN + "Event added successfully!" + RESET);
    }
    
    public static void ticketAddedMsg(){
        System.out.println(GREEN + "Ticket added successfully!" + RESET);
    }
    
    public static void SponsAddedMsg(){
        System.out.println(GREEN + "Sponsorship added successfully!" + RESET);
    }
    
    public static void eventRemovedMsg(){
        System.out.println(GREEN + "Event selected and all associated tickets and sponsorships have been removed successfully. "+ RESET);
    }
    
    public static void eventUpdatedMsg(){
        System.out.println(GREEN + "Event updated successfully!" + RESET);
    }
    
    
    
    
}
