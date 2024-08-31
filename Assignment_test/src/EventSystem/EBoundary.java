/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EventSystem;

import CommonResources.LinkedList;
import CommonResources.LinkedListInterface;
import CommonResources.Node;
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
        
        System.out.print("Enter your selection: ");
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

    

    

    
    

    

    
    
}
