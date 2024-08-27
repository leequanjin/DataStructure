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



public class Test {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";
    
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static final String EVENT_FILE = "C:\\Users\\Clarist Liew\\Downloads\\DataStruc\\DataStructure\\Assignment_test\\event.txt";
    private static final String TICKET_FILE = "C:\\Users\\Clarist Liew\\Downloads\\DataStruc\\DataStructure\\Assignment_test\\ticket.txt";
    private static final String SPONSORSHIP_FILE = "C:\\Users\\Clarist Liew\\Downloads\\DataStruc\\DataStructure\\Assignment_test\\sponsorship.txt";

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
                    updateEvent();
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
    Scanner scanner = new Scanner(System.in);

    LinkedList<Event> eventList = new LinkedList<>();
    LinkedList<Ticket> ticketList = new LinkedList<>();
    LinkedList<Sponsorship> sponsorshipList = new LinkedList<>();

    // Display the menu options and get a valid choice
    int choice = -1;
    boolean validInput = false;

    while (!validInput) {
        System.out.println("Select the type of entry to add:");
        System.out.println("1. Event");
        System.out.println("2. Ticket");
        System.out.println("3. Sponsorship");
        System.out.print("\nEnter your choice: ");

        String menuInput = scanner.nextLine().trim();

        if (menuInput.isEmpty()) {
            System.out.println(ANSI_RED + "Input cannot be empty. Please try again." + ANSI_RESET);
        } else {
            try {
                choice = Integer.parseInt(menuInput);
                if (choice >= 1 && choice <= 3) {
                    validInput = true;
                } else {
                    System.out.println(ANSI_RED + "Invalid choice. Please choose a number between 1 and 3." + ANSI_RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(ANSI_RED + "Invalid input. Please enter a valid integer number." + ANSI_RESET);
            }
        }
    }

    switch (choice) {
        case 1:
            addEventDetails(scanner, eventList);
            break;
        case 2:
            addTicket(scanner, eventList, ticketList);
            break;
        case 3:
            addSponsorship(scanner, eventList, sponsorshipList);
            break;
        default:
            System.out.println(ANSI_RED + "Invalid choice. Returning to main menu." + ANSI_RESET);
    }

    // Save all data to files after processing
    saveData(eventList, ticketList, sponsorshipList);
}

private static void addEventDetails(Scanner scanner, LinkedList<Event> eventList) {

    String eventID = generateEventID(eventList);

    System.out.print("Event Name: ");
    String eventName = scanner.nextLine().trim();

    Date date = getValidDate(scanner);
    String time = getValidTime(scanner); 

    System.out.print("Location: ");
    String location = scanner.nextLine().trim();

    Event event = new Event(eventID, eventName, date, time, location);

    eventList.insert(event);
    System.out.println(ANSI_GREEN + "Event added successfully!" + ANSI_RESET);
    
}

private static void addTicket(Scanner scanner, LinkedList<Event> eventList, LinkedList<Ticket> ticketList) {
    String ID;
    Event event;

    while (true) {
        System.out.print("Enter Event ID for the Ticket: ");
        ID = scanner.nextLine().trim();

        event = findEventByID(ID, eventList);
        if (event != null) {
            break;
        } else {
            System.out.println(ANSI_RED + "Event ID not found. Please enter a valid Event ID." + ANSI_RESET);
        }
    }

    String ticketID = generateTicketID(ticketList);

    System.out.print("Ticket Type: ");
    String ticketType = scanner.nextLine().trim();

    System.out.print("Ticket Price: ");
    double ticketPrice = getValidDouble(scanner);

    Ticket ticket = new Ticket(ID, event.getEventName(), event.getDate(), event.getTime(), event.getLocation(), ticketID, ticketType, ticketPrice);
    ticketList.insert(ticket);
    System.out.println(ANSI_GREEN + "Ticket added successfully!" + ANSI_RESET);
}

private static void addSponsorship(Scanner scanner, LinkedList<Event> eventList, LinkedList<Sponsorship> sponsorshipList) {
    String ID;
    Event event;

    while (true) {
        System.out.print("Enter Event ID for the Sponsorship: ");
        ID = scanner.nextLine().trim();

        event = findEventByID(ID, eventList);
        if (event != null) {
            break;
        } else {
            System.out.println(ANSI_RED + "Event ID not found. Please enter a valid Event ID." + ANSI_RESET);
        }
    }

    String sponsorID = generateSponsorshipID(sponsorshipList);

    System.out.print("Sponsor Name: ");
    String sponsorName = scanner.nextLine().trim();

    System.out.print("Sponsor Amount: ");
    double sponsorAmount = getValidDouble(scanner);

    Sponsorship sponsorship = new Sponsorship(ID, event.getEventName(), event.getDate(), event.getTime(), event.getLocation(), sponsorID, sponsorName, sponsorAmount);
    sponsorshipList.insert(sponsorship);
    System.out.println(ANSI_GREEN + "Sponsorship added successfully!" + ANSI_RESET);
}

private static String generateEventID(LinkedList<Event> eventList) {
    String prefix = "EV";
    int maxId = 0;

    Node<Event> current = eventList.head;

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

private static String generateTicketID(LinkedList<Ticket> ticketList) {
    String prefix = "TK";
    int maxId = 0;

    Node<Ticket> current = ticketList.head;

    while (current != null) {
        String currentId = current.data.getTicketID().substring(prefix.length());
        int idNumber = Integer.parseInt(currentId);
        if (idNumber > maxId) {
            maxId = idNumber;
        }
        current = current.next;
    }

    return prefix + String.format("%05d", maxId + 1);
}

private static String generateSponsorshipID(LinkedList<Sponsorship> sponsorshipList) {
    String prefix = "SP";
    int maxId = 0;

    Node<Sponsorship> current = sponsorshipList.head;

    while (current != null) {
        String currentId = current.data.getSponsorID().substring(prefix.length());
        int idNumber = Integer.parseInt(currentId);
        if (idNumber > maxId) {
            maxId = idNumber;
        }
        current = current.next;
    }

    return prefix + String.format("%05d", maxId + 1);
}
private static Event findEventByID(String eventID, LinkedList<Event> eventList) {
    Node<Event> current = eventList.head;

    while (current != null) {
        Event event = current.data;
        if (event.getEventID().equals(eventID)) {
            return event;
        }
        current = current.next;
    }

    return null; // Event ID not found
}


private static Date getValidDate(Scanner scanner) {
    Date date = null;
    while (true) {
        System.out.print("Date (dd/MM/yyyy): ");
        String dateString = scanner.nextLine().trim();

        // Check if the format is correct
        if (!dateString.matches("\\d{2}/\\d{2}/\\d{4}")) {
            System.out.println(ANSI_RED + "Invalid date format. Please use dd/MM/yyyy." + ANSI_RESET);
            continue;
        }

        String[] parts = dateString.split("/");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        // Check if the month is valid (1-12)
        if (month < 1 || month > 12) {
            System.out.println(ANSI_RED + "Invalid month. Please enter a month between 01 and 12." + ANSI_RESET);
            continue;
        }

        // Check if the day is valid for the given month and year
        if (!isValidDay(day, month, year)) {
            System.out.println(ANSI_RED + "Invalid day for the given month. Please enter a valid day." + ANSI_RESET);
            continue;
        }

        try {
            date = dateFormat.parse(dateString);

            // Additional validation to ensure the date was parsed correctly
            if (!dateString.equals(dateFormat.format(date))) {
                System.out.println(ANSI_RED + "Invalid date. Please enter a valid date in the format dd/MM/yyyy." + ANSI_RESET);
                date = null;
                continue;
            }

            break; 

        } catch (ParseException e) {
            System.out.println(ANSI_RED + "Invalid date. Please enter a valid date in the format dd/MM/yyyy." + ANSI_RESET);
        }
    }
    return date;
}

private static boolean isValidDay(int day, int month, int year) {
    // Array with the number of days in each month
    int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    // Check for leap year and update February's days
    if (month == 2 && isLeapYear(year)) {
        daysInMonth[1] = 29;
    }

    // Check if the day is valid for the given month
    return day >= 1 && day <= daysInMonth[month - 1];
}

private static boolean isLeapYear(int year) {
    return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
}


private static String getValidTime(Scanner scanner) {
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    timeFormat.setLenient(false);  // Ensures strict parsing

    String time = "";

    while (true) {
        System.out.print("Time (HH:mm) in 24-hour format: ");
        time = scanner.nextLine().trim();

        try {
            timeFormat.parse(time);
            break;
        } catch (ParseException e) {
            System.out.println(ANSI_RED + "Invalid time format. Please use HH:mm (24-hour format)." + ANSI_RESET);
        }
    }

    return time;
}


private static double getValidDouble(Scanner scanner) {
    while (true) {
        try {
            return Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println(ANSI_RED + "Invalid number format. Please enter a valid number." + ANSI_RESET);
        }
    }
}

// Save to file
private static void saveData(LinkedList<Event> eventList, LinkedList<Ticket> ticketList, LinkedList<Sponsorship> sponsorshipList) {
    eventList.saveToFile(EVENT_FILE);
    ticketList.saveToFile(TICKET_FILE);
    sponsorshipList.saveToFile(SPONSORSHIP_FILE);
}

//validation check file exist


public static void removeEvent() {
        
        LinkedList<Event> eventList = new LinkedList<>();
        LinkedList<Ticket> ticketList = new LinkedList<>();
        LinkedList<Sponsorship> sponsorshipList = new LinkedList<>();
        
        eventList.loadFromFile("event.txt");
        ticketList.loadFromFile("ticket.txt");
        sponsorshipList.loadFromFile("sponsorship.txt");

        Scanner scanner = new Scanner(System.in);

        
        if (eventList.isEmpty()) {
            System.out.println("No events available to remove.");
            return;
        }

        System.out.println("Available events:");
        eventList.show(); // Use the show() method to display all events

        System.out.print("Enter the Event ID to remove: ");
        String eventID = scanner.nextLine().trim();

        // Remove the event with the specified eventID
        eventList.removeIf(event -> event.getEventID().equals(eventID));

        // Remove associated tickets and sponsorships
        ticketList.removeIf(ticket -> ticket.getEventID().equals(eventID));
        sponsorshipList.removeIf(sponsorship -> sponsorship.getEventID().equals(eventID));

        // Call saveData method to save the updated lists back to the file
        saveData(eventList, ticketList, sponsorshipList);

        System.out.println("Event with ID " + eventID + " and all associated tickets and sponsorships have been removed ");
    }
    
    private static void searchEvent() {
        
    }


    public static void updateEvent() {
        // Declare and initialize the LinkedLists within the method
        LinkedList<Event> eventList = new LinkedList<>();
        LinkedList<Ticket> ticketList = new LinkedList<>();
        LinkedList<Sponsorship> sponsorshipList = new LinkedList<>();
        
        // Load the existing events, tickets, and sponsorships from their respective files
        eventList.loadFromFile("event.txt");
        ticketList.loadFromFile("ticket.txt");
        sponsorshipList.loadFromFile("sponsorship.txt");

        Scanner scanner = new Scanner(System.in);

        
        if (eventList.isEmpty()) {
            System.out.println(ANSI_RED + "No events available to update." + ANSI_RESET);
            return;
        }

        System.out.print("Enter the Event ID to update: ");
        String eventID = scanner.nextLine().trim();

        // Find the event by ID
        Node<Event> current = eventList.head;
        while (current != null) {
            if (current.data.getEventID().equals(eventID)) {
                Event event = current.data;
                
                boolean continueUpdating = true;
                while (continueUpdating) {
                    // Show update options
                    System.out.println("\nWhat would you like to update?");
                    System.out.println("1. Event Name");
                    System.out.println("2. Date");
                    System.out.println("3. Time");
                    System.out.println("4. Location");
                    System.out.println("5. Tickets");
                    System.out.println("6. Sponsorships");
                    System.out.println("7. Finish updating");
                    System.out.print("Enter your choice: ");
                    
                    String choice = scanner.nextLine().trim();
                    
                    switch (choice) {
                        case "1":
                            System.out.print("Enter new Event Name: ");
                            String newName = scanner.nextLine().trim();
                            while (newName.isEmpty()) {
                                System.out.println(ANSI_RED + "Event Name cannot be empty. Please enter a valid name." + ANSI_RESET);
                                newName = scanner.nextLine().trim();
                            }
                            event.setEventName(newName);
                            System.out.println(ANSI_GREEN + "Event Name updated successfully!" + ANSI_RESET);
                            break;
                        case "2":
                            System.out.print("Enter new Date (dd/MM/yyyy): ");
                            String newDateStr = scanner.nextLine().trim();
                            Date newDate = null;
                            while (newDate == null) {
                                try {
                                    newDate = dateFormat.parse(newDateStr);
                                } catch (ParseException e) {
                                    System.out.println(ANSI_RED + "Invalid date format. Please use dd/MM/yyyy." + ANSI_RESET);
                                    newDateStr = scanner.nextLine().trim();
                                }
                            }
                            event.setDate(newDate);
                            System.out.println(ANSI_GREEN + "Date updated successfully!" + ANSI_RESET);
                            break;
                        case "3":
                            System.out.print("Enter new Time (HH:mm): ");
                            String newTime = scanner.nextLine().trim();
                            while (newTime.isEmpty()) {
                                System.out.println(ANSI_RED + "Time cannot be empty. Please enter a valid time." + ANSI_RESET);
                                newTime = scanner.nextLine().trim();
                            }
                            event.setTime(newTime);
                            System.out.println(ANSI_GREEN + "Time updated successfully!" + ANSI_RESET);
                            break;
                        case "4":
                            System.out.print("Enter new Location: ");
                            String newLocation = scanner.nextLine().trim();
                            while (newLocation.isEmpty()) {
                                System.out.println(ANSI_RED + "Location cannot be empty. Please enter a valid location." + ANSI_RESET);
                                newLocation = scanner.nextLine().trim();
                            }
                            event.setLocation(newLocation);
                            System.out.println(ANSI_GREEN + "Location updated successfully!" + ANSI_RESET);
                            break;
                        case "5":
                            updateTickets(eventID, ticketList, scanner);
                            break;
                        case "6":
                            updateSponsorships(eventID, sponsorshipList, scanner);
                            break;
                        case "7":
                            continueUpdating = false;
                            break;
                        default:
                            System.out.println(ANSI_RED + "Invalid choice. Please try again." + ANSI_RESET);
                    }
                }

                // Update associated tickets and sponsorships with updated event details
                updateRelatedTicketsAndSponsorships(eventID, event, ticketList, sponsorshipList);

                // Call saveData method to save the updated lists back to the file
                saveData(eventList, ticketList, sponsorshipList);

                System.out.println(ANSI_GREEN + "Event with ID " + eventID + " has been updated." + ANSI_RESET);
                return;
            }
            current = current.next;
        }

        System.out.println(ANSI_RED + "Event with ID " + eventID + " not found." + ANSI_RESET);
    }

    // Method to update tickets associated with an event
    private static void updateTickets(String eventID, LinkedList<Ticket> ticketList, Scanner scanner) {
        boolean continueUpdatingTickets = true;
        
        while (continueUpdatingTickets) {
            System.out.print("Enter the Ticket ID to update: ");
            String ticketID = scanner.nextLine().trim();
            
            Node<Ticket> currentTicket = ticketList.head;
            boolean ticketFound = false;
            
            while (currentTicket != null) {
                if (currentTicket.data.getTicketID().equals(ticketID) && currentTicket.data.getEventID().equals(eventID)) {
                    ticketFound = true;
                    Ticket ticket = currentTicket.data;
                    
                    // Show update options for the ticket
                    System.out.println("\nUpdating Ticket: " + ticket.getTicketID());
                    System.out.println("1. Ticket Type");
                    System.out.println("2. Ticket Price");
                    System.out.print("Enter your choice: ");
                    
                    String choice = scanner.nextLine().trim();
                    
                    switch (choice) {
                        case "1":
                            System.out.print("Enter new Ticket Type: ");
                            String newTicketType = scanner.nextLine().trim();
                            while (newTicketType.isEmpty()) {
                                System.out.println(ANSI_RED + "Ticket Type cannot be empty. Please enter a valid type." + ANSI_RESET);
                                newTicketType = scanner.nextLine().trim();
                            }
                            ticket.setTicketType(newTicketType);
                            System.out.println(ANSI_GREEN + "Ticket Type updated successfully!" + ANSI_RESET);
                            break;
                        case "2":
                            System.out.print("Enter new Ticket Price: ");
                            String newTicketPriceStr = scanner.nextLine().trim();
                            double newTicketPrice = -1;
                            while (newTicketPrice < 0) {
                                try {
                                    newTicketPrice = Double.parseDouble(newTicketPriceStr);
                                    if (newTicketPrice < 0) {
                                        System.out.println(ANSI_RED + "Ticket Price must be a positive number. Please enter a valid price." + ANSI_RESET);
                                        newTicketPriceStr = scanner.nextLine().trim();
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println(ANSI_RED + "Invalid price format. Please enter a valid number." + ANSI_RESET);
                                    newTicketPriceStr = scanner.nextLine().trim();
                                }
                            }
                            ticket.setTicketPrice(newTicketPrice);
                            System.out.println(ANSI_GREEN + "Ticket Price updated successfully!" + ANSI_RESET);
                            break;
                        default:
                            System.out.println(ANSI_RED + "Invalid choice. Please try again." + ANSI_RESET);
                    }
                }
                currentTicket = currentTicket.next;
            }
            
            if (!ticketFound) {
                System.out.println(ANSI_RED + "Ticket with ID " + ticketID + " not found for this event." + ANSI_RESET);
            }
            
            // Ask if the user wants to update another ticket
            System.out.print("Do you want to update another ticket? (Y/N): ");
            String updateAnotherTicket = scanner.nextLine().trim().toUpperCase();
            if (!updateAnotherTicket.equals("Y")) {
                continueUpdatingTickets = false;
            }
        }
    }

    // Method to update sponsorships associated with an event
    private static void updateSponsorships(String eventID, LinkedList<Sponsorship> sponsorshipList, Scanner scanner) {
        boolean continueUpdatingSponsorships = true;
        
        while (continueUpdatingSponsorships) {
            System.out.print("Enter the Sponsorship ID to update: ");
            String sponsorshipID = scanner.nextLine().trim();
            
            Node<Sponsorship> currentSponsorship = sponsorshipList.head;
            boolean sponsorshipFound = false;
            
            while (currentSponsorship != null) {
                if (currentSponsorship.data.getSponsorID().equals(sponsorshipID) && currentSponsorship.data.getEventID().equals(eventID)) {
                    sponsorshipFound = true;
                    Sponsorship sponsorship = currentSponsorship.data;
                    
                    // Show update options for the sponsorship
                    System.out.println("\nUpdating Sponsorship: " + sponsorship.getSponsorID());
                    System.out.println("1. Sponsor Name");
                    System.out.println("2. Sponsor Amount");
                    System.out.print("Enter your choice: ");
                    
                    String choice = scanner.nextLine().trim();
                    
                    switch (choice) {
                        case "1":
                            System.out.print("Enter new Sponsor Name: ");
                            String newSponsorName = scanner.nextLine().trim();
                            while (newSponsorName.isEmpty()) {
                                System.out.println(ANSI_RED + "Sponsor Name cannot be empty. Please enter a valid name." + ANSI_RESET);
                                newSponsorName = scanner.nextLine().trim();
                            }
                            sponsorship.setSponsorName(newSponsorName);
                            System.out.println(ANSI_GREEN + "Sponsor Name updated successfully!" + ANSI_RESET);
                            break;
                        case "2":
                            System.out.print("Enter new Sponsor Amount: ");
                            String newSponsorAmountStr = scanner.nextLine().trim();
                            double newSponsorAmount = -1;
                            while (newSponsorAmount < 0) {
                                try {
                                    newSponsorAmount = Double.parseDouble(newSponsorAmountStr);
                                    if (newSponsorAmount < 0) {
                                        System.out.println(ANSI_RED + "Sponsor Amount must be a positive number. Please enter a valid amount." + ANSI_RESET);
                                        newSponsorAmountStr = scanner.nextLine().trim();
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println(ANSI_RED + "Invalid amount format. Please enter a valid number." + ANSI_RESET);
                                    newSponsorAmountStr = scanner.nextLine().trim();
                                }
                            }
                            sponsorship.setSponsorAmount(newSponsorAmount);
                            System.out.println(ANSI_GREEN + "Sponsor Amount updated successfully!" + ANSI_RESET);
                            break;
                        default:
                            System.out.println(ANSI_RED + "Invalid choice. Please try again." + ANSI_RESET);
                    }
                }
                currentSponsorship = currentSponsorship.next;
            }
            
            if (!sponsorshipFound) {
                System.out.println(ANSI_RED + "Sponsorship with ID " + sponsorshipID + " not found for this event." + ANSI_RESET);
            }
            
            // Ask if the user wants to update another sponsorship
            System.out.print("Do you want to update another sponsorship? (Y/N): ");
            String updateAnotherSponsorship = scanner.nextLine().trim().toUpperCase();
            if (!updateAnotherSponsorship.equals("Y")) {
                continueUpdatingSponsorships = false;
            }
        }
    }

    // Method to update related tickets and sponsorships based on event details
    private static void updateRelatedTicketsAndSponsorships(String eventID, Event updatedEvent, LinkedList<Ticket> ticketList, LinkedList<Sponsorship> sponsorshipList) {
        // Update tickets
        Node<Ticket> currentTicket = ticketList.head;
        while (currentTicket != null) {
            if (currentTicket.data.getEventID().equals(eventID)) {
                Ticket ticket = currentTicket.data;
                ticket.setEventName(updatedEvent.getEventName());
                ticket.setDate(updatedEvent.getDate());
                ticket.setTime(updatedEvent.getTime());
                ticket.setLocation(updatedEvent.getLocation());
            }
            currentTicket = currentTicket.next;
        }

        // Update sponsorships
        Node<Sponsorship> currentSponsorship = sponsorshipList.head;
        while (currentSponsorship != null) {
            if (currentSponsorship.data.getEventID().equals(eventID)) {
                Sponsorship sponsorship = currentSponsorship.data;
                sponsorship.setEventName(updatedEvent.getEventName());
                sponsorship.setDate(updatedEvent.getDate());
                sponsorship.setTime(updatedEvent.getTime());
                sponsorship.setLocation(updatedEvent.getLocation());
            }
            currentSponsorship = currentSponsorship.next;
        }
    }

    private static void listAllEvents() {
        
        
    }

    private static void filterEvents() {
        System.out.println("Hello");
        
    }

    private static void generateSummaryReports() {
        
    }
}
    



