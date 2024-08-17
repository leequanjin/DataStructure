/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EventSystem;


import CommonResources.LinkedList;
import CommonResources.Node;


/**
 *
 * @author Clarist Liew
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


//private static YourADT events = new YourADT();


public class Test {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";
    
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static final String FILE_NAME = "C:\\Users\\Clarist Liew\\Downloads\\Practical 1\\event.txt";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in); 
        int menuChoice;

            // Display menu and get valid choice
            menuChoice = getValidMenuChoice(scan);

            // Execute the corresponding function based on the user's choice
            switch (menuChoice) {
                case 1:
                    addEvent();
                    break;
                case 2:
                    removeEvent();
                    break;
                case 3:
                    searchEvent();
                    break;
                case 4:
                    amendEvent();
                    break;
                case 5:
                    listAllEvents();
                    break;
                case 6:
                    filterEvents();
                    break;
                case 7:
                    generateSummaryReports();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    break;
            }

        
    }

    private static int getValidMenuChoice(Scanner scanner) {
        int choice = -1;
        boolean validInput = false;

        while (!validInput) {
            // Display the menu options
            System.out.println("Event Management Subsystem:");
            System.out.println("1. Add a new event");
            System.out.println("2. Remove an event");
            System.out.println("3. Search an event");
            System.out.println("4. Amend event details");
            System.out.println("5. List all events");
            System.out.println("6. Filter events based on criteria");
            System.out.println("7. Generate summary reports");
            System.out.println("8. Exit");
            System.out.print("\nEnter your choice: ");

            String menuInput = scanner.nextLine().trim();

            if (menuInput.isEmpty()) {
                System.out.println(ANSI_RED + "Input cannot be empty. Please try again." + ANSI_RESET);
            } else {
                try {
                    choice = Integer.parseInt(menuInput);
                    if (choice >= 1 && choice <= 8) {
                        validInput = true;
                    } else {
                        System.out.println(ANSI_RED + "Invalid choice. Please choose a number between 1 and 8." + ANSI_RESET);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(ANSI_RED + "Invalid input. Please enter a valid integer number." + ANSI_RESET);
                }
            }
        }
        return choice;
    }
    

private static void addEvent() {
    LinkedList<Event> events = new LinkedList<>();
    Scanner scan = new Scanner(System.in);

    String ID = generateEventID(events);

    String name;
    do {
        System.out.print("Enter event name: ");
        name = scan.nextLine();
        if (name.isEmpty()) {
            System.out.println("Event name cannot be empty!");
        }
    } while (name.isEmpty());

    Date eventDate = null;
    while (eventDate == null) {
        System.out.print("Enter event date (dd/MM/yyyy): ");
        String dateString = scan.nextLine();
        if (dateString.isEmpty()) {
            System.out.println("Event date cannot be empty!");
        } else {
            try {
                eventDate = dateFormat.parse(dateString);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use dd/MM/yyyy.");
            }
        }
    }

    String eventTime ;
    do {
        System.out.print("Enter event time (HH:mm): ");
        eventTime = scan.nextLine();
        if (eventTime.isEmpty()) {
            System.out.println("Event time cannot be empty!");
        }
    } while (eventTime.isEmpty());

    String eventLocation;
    do {
        System.out.print("Enter event location: ");
        eventLocation = scan.nextLine();
        if (eventLocation.isEmpty()) {
            System.out.println("Event location cannot be empty!");
        }
    } while (eventLocation.isEmpty());

    Event event = new Event(ID, name, eventDate, eventTime, eventLocation);
    events.insert(event);

    // Save the linked list to the file
    events.saveToFile(FILE_NAME);
    System.out.println("Event added successfully!");
}

private static String generateEventID(LinkedList<Event> eventList) {
    String prefix = "EVT";
    int maxId = 0;

    Node<Event> current = eventList.head; // Access the head of the linked list

    while (current != null) {
        String currentId = current.data.getEventID().substring(prefix.length());
        int idNumber = Integer.parseInt(currentId);
        if (idNumber > maxId) {
            maxId = idNumber;
        }
        current = current.next;
    }

    return prefix + String.format("%05d", maxId + 1);
}
//validation check file exist
//validation check data exist 
//add time validation

 private static void removeEvent() {
//        Scanner scanner = new Scanner(System.in);
//        String eventID;
//
//        // Prompt user for eventID
//        do {
//            System.out.print("Enter the event ID to remove: ");
//            eventID = scanner.nextLine();
//            if (eventID.isEmpty()) {
//                System.out.println("Event ID cannot be empty. Please enter a valid ID.");
//            }
//        } while (eventID.isEmpty());
//
//        // Remove the event and save changes to the file
//        //events.removeIf(event -> event.getEventID().equals(eventID));
//
//        // Save the updated list to file
//        events.saveToFile(FILE_NAME);
//        System.out.println("Event with ID " + eventID + " removed successfully, if it existed.");
    }

    
    private static void searchEvent() {
        
    }

    private static void amendEvent() {
//        String name;
//        do {
//            System.out.print("Enter the name of the event to amend: ");
//            name = scanner.nextLine();
//            if (name.isEmpty()) {
//                System.out.println("Event name cannot be empty!");
//            }
//        } while (name.isEmpty());
//
//        Event event = events.search(name);  // Replace with your ADT method
//        if (event != null) {
//            System.out.print("Enter new event name: ");
//            String newName = scanner.nextLine();
//            if (!newName.isEmpty()) {
//                event.setEventName(newName);
//            }
//
//            Date newDate = null;
//            while (newDate == null) {
//                System.out.print("Enter new event date (dd/MM/yyyy): ");
//                String dateString = scanner.nextLine();
//                if (!dateString.isEmpty()) {
//                    try {
//                        newDate = dateFormat.parse(dateString);
//                        event.setDate(newDate);
//                    } catch (ParseException e) {
//                        System.out.println("Invalid date format. Please use dd/MM/yyyy.");
//                    }
//                } else {
//                    break;
//                }
//            }
//
//            System.out.print("Enter new event time (HH:mm): ");
//            String newTime = scanner.nextLine();
//            if (!newTime.isEmpty()) {
//                event.setTime(newTime);
//            }
//
//            System.out.print("Enter new event location: ");
//            String newLocation = scanner.nextLine();
//            if (!newLocation.isEmpty()) {
//                event.setLocation(newLocation);
//            }
//
//            saveAllEventsToFile();
//            System.out.println("Event updated successfully!");
//        } else {
//            System.out.println("Event not found!");
//        }
    }

    private static void listAllEvents() {
        
    }

    private static void filterEvents() {
        
    }

    private static void generateSummaryReports() {
        
    }
}
    



