/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EventSystem;


import CommonResources.LinkedList;
import CommonResources.Node;
import VolunteerSubsystem.EventVolunteer;
import VolunteerSubsystem.Volunteer;


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
import java.util.Calendar;



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
    private static final String VOLUNTEER_FILE = "C:\\Users\\Clarist Liew\\Downloads\\DataStruc\\DataStructure\\Assignment_test\\volunteers.txt";
    private static final String VOLUNTEER_EVENT_FILE = "C:\\Users\\Clarist Liew\\Downloads\\DataStruc\\DataStructure\\Assignment_test\\volunteer_event.txt";

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
                    addTicket();
                    break;
                case 3:
                    addSponsorship();
                    break;
                case 4:
                    removeEvent();
                    break;
                case 5:
                    searchEvent();
                    break;
                case 6:
                    updateEvent();
                    break;
                case 7:
                    listAllEvents();
                    break;
                case 8:
                    removeEventFromAVolunteer();
                    break;
                case 9:
                    listAllEventsForAVolunteer();
                    break;
                case 10:
                    generateSummaryReports();
                    break;
                case 11:
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
            System.out.println("2. Add a new ticket");
            System.out.println("3. Add a new sponsorship");
            System.out.println("4. Remove an event");
            System.out.println("5. Search an event");
            System.out.println("6. Amend event details");
            System.out.println("7. List all events");
            System.out.println("8. Remove an event from a volunteer");
            System.out.println("9. List all events for a volunteer");
            System.out.println("10. Generate summary reports");
            System.out.println("11. Exit");
            System.out.print("\nEnter your choice: ");

            String menuInput = scanner.nextLine().trim();

            if (menuInput.isEmpty()) {
                System.out.println(ANSI_RED + "Input cannot be empty. Please try again." + ANSI_RESET);
            } else {
                try {
                    choice = Integer.parseInt(menuInput);
                    if (choice >= 1 && choice <= 11) {
                        validInput = true;
                    } else {
                        System.out.println(ANSI_RED + "Invalid choice. Please choose a number between 1 and 11." + ANSI_RESET);
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
    eventList.loadFromFile(EVENT_FILE);

    String eventID = generateEventID(eventList);

    System.out.print("Event Name: ");
    String eventName = scanner.nextLine().trim();

    Date date = getValidDate(scanner);
    String time = getValidTime(scanner); 

    System.out.print("Location: ");
    String location = scanner.nextLine().trim();

    Event event = new Event(eventID, eventName, date, time, location);

    eventList.insert(event);
    eventList.saveToFile(EVENT_FILE);
    System.out.println(ANSI_GREEN + "Event added successfully!" + ANSI_RESET);
}


private static void addTicket() {
    Scanner scanner = new Scanner(System.in);
    LinkedList<Event> eventList = new LinkedList<>();
    LinkedList<Ticket> ticketList = new LinkedList<>();

    eventList.loadFromFile(EVENT_FILE);
    ticketList.loadFromFile(TICKET_FILE);

    String eventID;
    Event event;

    // Validate the Event ID
    while (true) {
        System.out.print("Enter Event ID for the Ticket: ");
        eventID = scanner.nextLine().trim();

        event = findEventByID(eventID, eventList);
        if (event != null) {
            break;
        } else {
            System.out.println(ANSI_RED + "Event ID not found. Please enter a valid Event ID." + ANSI_RESET);
        }
    }

    System.out.print("How many ticket types would you like to add?: ");
    int numberOfTicketTypes = getValidInteger(scanner);

    for (int i = 1; i <= numberOfTicketTypes; i++) {
        System.out.println("\nTicket Type " + i + ":");

        System.out.print("Ticket Type: ");
        String ticketType = scanner.nextLine().trim();
        while (ticketType.isEmpty()) {
            System.out.println(ANSI_RED + "Ticket Type cannot be empty. Please enter a valid Ticket Type." + ANSI_RESET);
            ticketType = scanner.nextLine().trim();
        }

        System.out.print("Ticket Price: ");
        double ticketPrice = getValidDouble(scanner);

        System.out.print("Amount of Tickets: ");
        int ticketAmount = getValidInteger(scanner);

        for (int j = 0; j < ticketAmount; j++) {
            String ticketID = generateTicketID(ticketList);
            Ticket ticket = new Ticket(eventID, ticketID, ticketType, ticketPrice, "Available");
            ticketList.insert(ticket);
        }

        ticketList.saveToFile(TICKET_FILE);
        System.out.println(ANSI_GREEN + ticketAmount + " tickets for '" + ticketType + "' added successfully!" + ANSI_RESET);
    }
}

private static int getValidInteger(Scanner scanner) {
    while (true) {
        try {
            int value = Integer.parseInt(scanner.nextLine().trim());
            if (value > 0) {
                return value;
            } else {
                System.out.println(ANSI_RED + "Please enter a positive number." + ANSI_RESET);
            }
        } catch (NumberFormatException e) {
            System.out.println(ANSI_RED + "Invalid input. Please enter a valid integer number." + ANSI_RESET);
        }
    }
}


private static void addSponsorship() {
    Scanner scanner = new Scanner(System.in);
    LinkedList<Event> eventList = new LinkedList<>();
    LinkedList<Sponsorship> sponsorshipList = new LinkedList<>();

    eventList.loadFromFile(EVENT_FILE);
    sponsorshipList.loadFromFile(SPONSORSHIP_FILE);

    String eventID;
    Event event;

    // Validate the Event ID
    while (true) {
        System.out.print("Enter Event ID for the Sponsorship: ");
        eventID = scanner.nextLine().trim();

        event = findEventByID(eventID, eventList);
        if (event != null) {
            break;
        } else {
            System.out.println(ANSI_RED + "Event ID not found. Please enter a valid Event ID." + ANSI_RESET);
        }
    }

    String sponsorID = generateSponsorshipID(sponsorshipList);

    System.out.print("Sponsor Name: ");
    String sponsorName = scanner.nextLine().trim();
    while (sponsorName.isEmpty()) {
        System.out.println(ANSI_RED + "Sponsor Name cannot be empty. Please enter a valid Sponsor Name." + ANSI_RESET);
        sponsorName = scanner.nextLine().trim();
    }

    System.out.print("Sponsor Amount: ");
    double sponsorAmount = getValidDouble(scanner);

    Sponsorship sponsorship = new Sponsorship(eventID, sponsorID, sponsorName, sponsorAmount);
    sponsorshipList.insert(sponsorship);
    sponsorshipList.saveToFile(SPONSORSHIP_FILE);
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

// Save to file method
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
        
        eventList.loadFromFile(EVENT_FILE);
        ticketList.loadFromFile(TICKET_FILE);
        sponsorshipList.loadFromFile(SPONSORSHIP_FILE);

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
    LinkedList<Event> eventList = new LinkedList<>();
    LinkedList<Ticket> ticketList = new LinkedList<>();
    LinkedList<Sponsorship> sponsorshipList = new LinkedList<>();
    
    eventList.loadFromFile(EVENT_FILE);
    ticketList.loadFromFile(TICKET_FILE);
    sponsorshipList.loadFromFile(SPONSORSHIP_FILE);
    
    Scanner scanner = new Scanner(System.in);

    if (eventList.isEmpty()) {
        System.out.println(ANSI_YELLOW + "No events found." + ANSI_RESET);
        return;
    }

    int searchChoice = 0;
    while (searchChoice != 1 && searchChoice != 2) {
        System.out.println("Search Event by:");
        System.out.println("1. Event ID");
        System.out.println("2. Event Name");
        System.out.print("Enter your choice: ");
        
        String searchChoiceStr = scanner.nextLine().trim();

        if (searchChoiceStr.isEmpty()) {
            System.out.println(ANSI_RED + "Input cannot be empty. Please enter a number." + ANSI_RESET);
            continue;
        }

        try {
            searchChoice = Integer.parseInt(searchChoiceStr);
            if (searchChoice != 1 && searchChoice != 2) {
                System.out.println(ANSI_RED + "Invalid choice. Please select either 1 or 2." + ANSI_RESET);
            }
        } catch (NumberFormatException e) {
            System.out.println(ANSI_RED + "Invalid choice. Please enter a number." + ANSI_RESET);
        }
    }

    System.out.print("Enter your search query: ");
    String searchQuery = scanner.nextLine().trim();

    Event foundEvent = null;

    switch (searchChoice) {
        case 1:
            foundEvent = searchEventByID(eventList, searchQuery);
            break;
        case 2:
            foundEvent = searchEventByName(eventList, searchQuery);
            break;
        default:
            System.out.println(ANSI_RED + "Invalid choice. Please select either 1 or 2." + ANSI_RESET);
            return;
    }

    if (foundEvent != null) {
        System.out.println(ANSI_CYAN + "Event Found: " + ANSI_RESET);
        System.out.println("Event ID: " + foundEvent.getEventID());
        System.out.println("Event Name: " + foundEvent.getEventName());
        System.out.println("Date: " + dateFormat.format(foundEvent.getDate()));
        System.out.println("Time: " + foundEvent.getTime());
        System.out.println("Location: " + foundEvent.getLocation());
        

        // List associated tickets
        System.out.println(ANSI_BLUE + "\nTickets for Event ID " + foundEvent.getEventID() + ":" + ANSI_RESET);
        Node<Ticket> ticketNode = ticketList.head;
        boolean ticketsFound = false;
        int ticketCount = 1;
        while (ticketNode != null) {
            if (ticketNode.data.getEventID().equals(foundEvent.getEventID())) {
                System.out.println(ticketCount + ".");
                System.out.println("Ticket ID: " + ticketNode.data.getTicketID());
                System.out.println("Ticket Type: " + ticketNode.data.getTicketType());
                System.out.println("Ticket Amount: RM" + ticketNode.data.getTicketPrice());
                ticketsFound = true;
                ticketCount++;
            }
            ticketNode = ticketNode.next;
        }
        if (!ticketsFound) {
            System.out.println(ANSI_YELLOW + "No tickets found for this event." + ANSI_RESET);
        }

        // List associated sponsorships
        System.out.println(ANSI_PURPLE + "\nSponsorships for Event ID " + foundEvent.getEventID() + ":" + ANSI_RESET);
        Node<Sponsorship> sponsorshipNode = sponsorshipList.head;
        boolean sponsorshipsFound = false;
        int sponsorshipCount = 1;
        while (sponsorshipNode != null) {
            if (sponsorshipNode.data.getEventID().equals(foundEvent.getEventID())) {
                System.out.println(sponsorshipCount + ".");
                System.out.println("Sponsor ID: " + sponsorshipNode.data.getSponsorID());
                System.out.println("Sponsor Name: " + sponsorshipNode.data.getSponsorName());
                System.out.println("Sponsor Amount: RM" + sponsorshipNode.data.getSponsorAmount());
                sponsorshipsFound = true;
                sponsorshipCount++;
            }
            sponsorshipNode = sponsorshipNode.next;
        }
        if (!sponsorshipsFound) {
            System.out.println(ANSI_YELLOW + "No sponsorships found for this event." + ANSI_RESET);
        }
    } else {
        System.out.println(ANSI_RED + "No event found matching the search criteria." + ANSI_RESET);
    }
}


private static Event searchEventByID(LinkedList<Event> eventList, String eventID) {
    Node<Event> currentNode = eventList.head;
    Event[] eventArray = new Event[eventList.length()];
    for (int i = 0; i < eventList.length(); i++) {
        eventArray[i] = currentNode.data;
        currentNode = currentNode.next;
    }

    // Sort the array using bubble sort by event ID
    for (int i = 0; i < eventArray.length - 1; i++) {
        for (int j = 0; j < eventArray.length - 1 - i; j++) {
            if (eventArray[j].getEventID().compareTo(eventArray[j + 1].getEventID()) > 0) {
                Event temp = eventArray[j];
                eventArray[j] = eventArray[j + 1];
                eventArray[j + 1] = temp;
            }
        }
    }

    // Perform binary search on the sorted array by event ID
    int low = 0, high = eventArray.length - 1;
    while (low <= high) {
        int mid = (low + high) / 2;
        Event midEvent = eventArray[mid];

        if (midEvent.getEventID().equals(eventID)) {
            return midEvent;
        } else if (midEvent.getEventID().compareTo(eventID) < 0) {
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }

    return null;
}

private static Event searchEventByName(LinkedList<Event> eventList, String eventName) {
    Node<Event> currentNode = eventList.head;
    Event[] eventArray = new Event[eventList.length()];
    for (int i = 0; i < eventList.length(); i++) {
        eventArray[i] = currentNode.data;
        currentNode = currentNode.next;
    }

    // Sort the array using bubble sort by event name
    for (int i = 0; i < eventArray.length - 1; i++) {
        for (int j = 0; j < eventArray.length - 1 - i; j++) {
            if (eventArray[j].getEventName().compareToIgnoreCase(eventArray[j + 1].getEventName()) > 0) {
                Event temp = eventArray[j];
                eventArray[j] = eventArray[j + 1];
                eventArray[j + 1] = temp;
            }
        }
    }

    // Perform binary search on the sorted array by event name
    int low = 0, high = eventArray.length - 1;
    while (low <= high) {
        int mid = (low + high) / 2;
        Event midEvent = eventArray[mid];

        if (midEvent.getEventName().equalsIgnoreCase(eventName)) {
            return midEvent; 
        } else if (midEvent.getEventName().compareToIgnoreCase(eventName) < 0) {
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }

    return null;
}


public static void updateEvent() {
    LinkedList<Event> eventList = new LinkedList<>();
    LinkedList<Ticket> ticketList = new LinkedList<>();
    LinkedList<Sponsorship> sponsorshipList = new LinkedList<>();
    
    // Load data from files
    eventList.loadFromFile(EVENT_FILE);
    ticketList.loadFromFile(TICKET_FILE);
    sponsorshipList.loadFromFile(SPONSORSHIP_FILE);

    Scanner scanner = new Scanner(System.in);

    // Check if there are any events to update
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
                System.out.println("2. Event Date");
                System.out.println("3. Event Time");
                System.out.println("4. Event Location");
                System.out.println("5. Ticket");
                System.out.println("6. Sponsorships");
                System.out.println("7. Save Changes ");
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
                        Date newDate = getValidDate(scanner);
                        event.setDate(newDate);
                        System.out.println(ANSI_GREEN + "Date updated successfully!" + ANSI_RESET);
                        break;
                    case "3":
                        String newTime = getValidTime(scanner);
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

            // Save the updated event back to the event file
            eventList.saveToFile(EVENT_FILE);

            
            System.out.println(ANSI_GREEN + "Event with ID " + eventID + " has been updated." + ANSI_RESET);
            return;
        }
        current = current.next;
    }

    System.out.println(ANSI_RED + "Event with ID " + eventID + " not found." + ANSI_RESET);
}

private static void updateTickets(String eventID, LinkedList<Ticket> ticketList, Scanner scanner) {
    // Find and list all ticket types for the event
    Node<Ticket> currentTicket = ticketList.head;
    LinkedList<Ticket> eventTickets = new LinkedList<>();
    int ticketIndex = 1;

    while (currentTicket != null) {
        if (currentTicket.data.getEventID().equals(eventID)) {
            System.out.println(ticketIndex + ". Ticket ID: " + currentTicket.data.getTicketID() +
                               ", Type: " + currentTicket.data.getTicketType() +
                               ", Price: RM" + currentTicket.data.getTicketPrice());
            eventTickets.insert(currentTicket.data);
            ticketIndex++;
        }
        currentTicket = currentTicket.next;
    }

    if (eventTickets.isEmpty()) {
        System.out.println(ANSI_YELLOW + "No tickets found for this event." + ANSI_RESET);
        return;
    }

    // Select the ticket type to update
    System.out.print("Select the ticket type you want to update (1-" + eventTickets.length() + "): ");
    int selectedTicketIndex = getValidInteger(scanner);

    if (selectedTicketIndex < 1 || selectedTicketIndex > eventTickets.length()) {
        System.out.println(ANSI_RED + "Invalid selection. Please select a valid ticket type." + ANSI_RESET);
        return;
    }

    // Traverse to the selected ticket
    Node<Ticket> selectedTicketNode = eventTickets.head;
    for (int i = 1; i < selectedTicketIndex; i++) {
        selectedTicketNode = selectedTicketNode.next;
    }

    Ticket selectedTicket = selectedTicketNode.data;

    // Update Ticket Type
    System.out.print("Enter new Ticket Type (Current: " + selectedTicket.getTicketType() + "): ");
    String newTicketType = scanner.nextLine().trim();
    if (!newTicketType.isEmpty()) {
        selectedTicket.setTicketType(newTicketType);
    }

    // Update Ticket Price
    System.out.print("Enter new Ticket Price (Current: RM" + selectedTicket.getTicketPrice() + "): ");
    double newTicketPrice = getValidDouble(scanner);
    selectedTicket.setTicketPrice(newTicketPrice);

    // Update the status of all tickets with the selected type
    Node<Ticket> ticketNode = ticketList.head;
    while (ticketNode != null) {
        Ticket ticket = ticketNode.data;
        if (ticket.getEventID().equals(eventID) && ticket.getTicketType().equals(selectedTicket.getTicketType())) {
            ticket.setTicketType(newTicketType);
            ticket.setTicketPrice(newTicketPrice);
        }
        ticketNode = ticketNode.next;
    }

    System.out.println(ANSI_GREEN + "Ticket type and price updated successfully!" + ANSI_RESET);
    ticketList.saveToFile(TICKET_FILE);
}



//Method to update sponsorships associated with an event
private static void updateSponsorships(String eventID, LinkedList<Sponsorship> sponsorshipList, Scanner scanner) {
    // Find and list all sponsorships for the event
    Node<Sponsorship> currentSponsorship = sponsorshipList.head;
    int sponsorshipIndex = 1;
    LinkedList<Sponsorship> eventSponsorships = new LinkedList<>();

    while (currentSponsorship != null) {
        if (currentSponsorship.data.getEventID().equals(eventID)) {
            System.out.println(sponsorshipIndex + ". Sponsor ID: " + currentSponsorship.data.getSponsorID() +
                               ", Name: " + currentSponsorship.data.getSponsorName() +
                               ", Amount: RM" + currentSponsorship.data.getSponsorAmount());
            eventSponsorships.insert(currentSponsorship.data);
            sponsorshipIndex++;
        }
        currentSponsorship = currentSponsorship.next;
    }

    if (eventSponsorships.isEmpty()) {
        System.out.println(ANSI_YELLOW + "No sponsorships found for this event." + ANSI_RESET);
        return;
    }

    System.out.print("Select the sponsorship you want to update: ");
    int selectedSponsorshipIndex = getValidInteger(scanner);

    if (selectedSponsorshipIndex < 1 || selectedSponsorshipIndex > eventSponsorships.length()) {
        System.out.println(ANSI_RED + "Invalid selection. Please select a valid sponsorship." + ANSI_RESET);
        return;
    }

    Node<Sponsorship> selectedSponsorshipNode = eventSponsorships.head;
    for (int i = 1; i < selectedSponsorshipIndex; i++) {
        selectedSponsorshipNode = selectedSponsorshipNode.next;
    }

    Sponsorship selectedSponsorship = selectedSponsorshipNode.data;

    
    System.out.println("What would you like to update?");
    System.out.println("1. Sponsor Name");
    System.out.println("2. Sponsor Amount");
    System.out.println("3. Both Name and Amount");
    System.out.print("Enter your choice: ");

    String updateChoice = scanner.nextLine().trim();

    switch (updateChoice) {
        case "1":
            // Update Sponsor Name
            System.out.print("Enter new Sponsor Name : ");
            String newSponsorName = scanner.nextLine().trim();
            if (!newSponsorName.isEmpty()) {
                selectedSponsorship.setSponsorName(newSponsorName);
            }
            System.out.println(ANSI_GREEN + "Sponsor Name updated successfully!" + ANSI_RESET);
            break;
        case "2":
            // Update Sponsor Amount
            System.out.print("Enter new Sponsor Amount : ");
            double newSponsorAmount = getValidDouble(scanner);
            selectedSponsorship.setSponsorAmount(newSponsorAmount);
            System.out.println(ANSI_GREEN + "Sponsor Amount updated successfully!" + ANSI_RESET);
            break;
        case "3":
            // Update Both Name and Amount
            System.out.print("Enter new Sponsor Name : ");
            newSponsorName = scanner.nextLine().trim();
            if (!newSponsorName.isEmpty()) {
                selectedSponsorship.setSponsorName(newSponsorName);
            }

            System.out.print("Enter new Sponsor Amount : ");
            newSponsorAmount = getValidDouble(scanner);
            selectedSponsorship.setSponsorAmount(newSponsorAmount);

            System.out.println(ANSI_GREEN + "Sponsor Name and Amount updated successfully!" + ANSI_RESET);
            break;
        default:
            System.out.println(ANSI_RED + "Invalid choice. No changes made." + ANSI_RESET);
            return;
    }

    sponsorshipList.saveToFile(SPONSORSHIP_FILE);
}





private static void listAllEvents() {
    LinkedList<Event> eventList = new LinkedList<>();
    LinkedList<Ticket> ticketList = new LinkedList<>();
    LinkedList<Sponsorship> sponsorshipList = new LinkedList<>();

    // Load the events, tickets, and sponsorships from their respective files
    eventList.loadFromFile(EVENT_FILE);
    ticketList.loadFromFile(TICKET_FILE);
    sponsorshipList.loadFromFile(SPONSORSHIP_FILE);

    // Check if there are any events
    if (eventList.isEmpty()) {
        System.out.println(ANSI_YELLOW + "No events found." + ANSI_RESET);
        return;
    }

    // Iterate through all events
    Node<Event> eventNode = eventList.head;
    while (eventNode != null) {
        Event event = eventNode.data;
        System.out.println(ANSI_CYAN + "Event ID: " + event.getEventID() + ANSI_RESET);
        System.out.println("Event Name: " + event.getEventName());
        System.out.println("Date: " + dateFormat.format(event.getDate()));
        System.out.println("Time: " + event.getTime());
        System.out.println("Location: " + event.getLocation());
        

        // Filter and list tickets associated with the current event
        Node<Ticket> ticketNode = ticketList.head;
        System.out.println(ANSI_BLUE + "\nTickets for Event ID " + event.getEventID() + ":" + ANSI_RESET);
        boolean ticketsFound = false;
        int ticketCount = 1;
        while (ticketNode != null) {
            Ticket ticket = ticketNode.data;
            if (ticket.getEventID().equals(event.getEventID())) {
                System.out.println(ticketCount + ".");
                System.out.println("Ticket ID: " + ticket.getTicketID());
                System.out.println("Ticket Type: " + ticket.getTicketType());
                System.out.println("Ticket Amount: RM" + ticket.getTicketPrice());
                ticketsFound = true;
                ticketCount++;
            }
            ticketNode = ticketNode.next;
        }
        if (!ticketsFound) {
            System.out.println(ANSI_YELLOW + "No tickets found for this event." + ANSI_RESET);
        }

        // Filter and list sponsorships associated with the current event
        Node<Sponsorship> sponsorshipNode = sponsorshipList.head;
        System.out.println(ANSI_PURPLE + "\nSponsorships for Event ID " + event.getEventID() + ":" + ANSI_RESET);
        boolean sponsorshipsFound = false;
        int sponsorshipCount = 1;
        while (sponsorshipNode != null) {
            Sponsorship sponsorship = sponsorshipNode.data;
            if (sponsorship.getEventID().equals(event.getEventID())) {
                System.out.println(sponsorshipCount + ".");
                System.out.println("Sponsor ID: " + sponsorship.getSponsorID());
                System.out.println("Sponsor Name: " + sponsorship.getSponsorName());
                System.out.println("Sponsor Amount: RM" + sponsorship.getSponsorAmount());
                sponsorshipsFound = true;
                sponsorshipCount++;
            }
            sponsorshipNode = sponsorshipNode.next;
        }
        if (!sponsorshipsFound) {
            System.out.println(ANSI_YELLOW + "No sponsorships found for this event." + ANSI_RESET);
        }

        // Move to the next event in the list
        eventNode = eventNode.next;
        System.out.println("\n--------------------------------------------------\n");
    }
}



private static void removeEventFromAVolunteer() {
    LinkedList<Volunteer> volunteerList = new LinkedList<>();
    LinkedList<Event> eventList = new LinkedList<>();
    LinkedList<EventVolunteer> volunteerEventList = new LinkedList<>();

   
    volunteerList.loadFromFile(VOLUNTEER_FILE);
    eventList.loadFromFile("event.txt");
    volunteerEventList.loadFromFile("volunteer_event.txt");

    Scanner scanner = new Scanner(System.in);

    
    if (volunteerList.isEmpty()) {
        System.out.println(ANSI_YELLOW + "No volunteers found." + ANSI_RESET);
        return;
    }

    
    System.out.print("Enter the Volunteer ID: ");
    String volunteerID = scanner.nextLine().trim();

    // Find the volunteer based on volunteerID
    Node<Volunteer> volunteerNode = volunteerList.head;
    Volunteer foundVolunteer = null;

    while (volunteerNode != null) {
        if (volunteerNode.data.getVolunteerID().equals(volunteerID)) {
            foundVolunteer = volunteerNode.data;
            break;
        }
        volunteerNode = volunteerNode.next;
    }

   
    if (foundVolunteer == null) {
        System.out.println(ANSI_RED + "No volunteer found with ID: " + volunteerID + ANSI_RESET);
        return;
    }

    // List all events associated with the volunteer by checking the VOLUNTEER_EVENT_FILE
    LinkedList<Event> assignedEvents = new LinkedList<>();
    Node<EventVolunteer> volunteerEventNode = volunteerEventList.head;
    while (volunteerEventNode != null) {
        EventVolunteer eventVolunteer = volunteerEventNode.data;
        if (eventVolunteer.getVolunteerID().equals(volunteerID)) {
            // Find the event in the eventList based on eventID
            Node<Event> eventNode = eventList.head;
            while (eventNode != null) {
                if (eventNode.data.getEventID().equals(eventVolunteer.getEventID())) {
                    assignedEvents.insert(eventNode.data);
                    break;
                }
                eventNode = eventNode.next;
            }
        }
        volunteerEventNode = volunteerEventNode.next;
    }

    if (assignedEvents.isEmpty()) {
        System.out.println(ANSI_YELLOW + "No events associated with this volunteer." + ANSI_RESET);
        return;
    }

    System.out.println(ANSI_CYAN + "Volunteer: " + foundVolunteer.getName() + " (" + foundVolunteer.getVolunteerID() + ")" + ANSI_RESET);
    System.out.println("Associated Events:");
    Node<Event> assignedEventNode = assignedEvents.head;
    int eventIndex = 1;

    while (assignedEventNode != null) {
        Event event = assignedEventNode.data;
        System.out.println(eventIndex + ". Event ID: " + event.getEventID());
        System.out.println("   Event Name: " + event.getEventName());
        System.out.println("   Date: " + event.getDate());
        System.out.println("   Time: " + event.getTime());
        System.out.println("   Location: " + event.getLocation());
        System.out.println("---------------------------------------");

        assignedEventNode = assignedEventNode.next;
        eventIndex++;
    } 

    
    System.out.print("Enter the Event ID to remove from this volunteer: ");
    String eventIDToRemove = scanner.nextLine().trim();

    // Find the association in volunteerEventList and remove it
    volunteerEventList.removeIf(eventVolunteer -> eventVolunteer.getVolunteerID().equals(volunteerID) && eventVolunteer.getEventID().equals(eventIDToRemove));

    
    volunteerEventList.saveToFile("volunteer_event.txt");

    System.out.println(ANSI_GREEN + "Event with ID " + eventIDToRemove + " removed from volunteer " + volunteerID + "." + ANSI_RESET);
}



    
    private static void listAllEventsForAVolunteer() {
    LinkedList<Volunteer> volunteerList = new LinkedList<>();
    LinkedList<Event> eventList = new LinkedList<>();
    LinkedList<EventVolunteer> volunteerEventList = new LinkedList<>();

    
    volunteerList.loadFromFile("volunteers.txt");
    eventList.loadFromFile("event.txt");
    volunteerEventList.loadFromFile("volunteer_event.txt");

    Scanner scanner = new Scanner(System.in);

    
    if (volunteerList.isEmpty()) {
        System.out.println(ANSI_YELLOW + "No volunteers found." + ANSI_RESET);
        return;
    }

   
    System.out.print("Enter the Volunteer ID: ");
    String volunteerID = scanner.nextLine().trim();

    // Find the volunteer based on volunteerID
    Node<Volunteer> volunteerNode = volunteerList.head;
    Volunteer foundVolunteer = null;

    while (volunteerNode != null) {
        if (volunteerNode.data.getVolunteerID().equals(volunteerID)) {
            foundVolunteer = volunteerNode.data;
            break;
        }
        volunteerNode = volunteerNode.next;
    }

    
    if (foundVolunteer == null) {
        System.out.println(ANSI_RED + "No volunteer found with ID: " + volunteerID + ANSI_RESET);
        return;
    }

    // List all events associated with the volunteer by checking the volunteer_event.txt
    LinkedList<Event> assignedEvents = new LinkedList<>();
    Node<EventVolunteer> volunteerEventNode = volunteerEventList.head;

    while (volunteerEventNode != null) {
        EventVolunteer eventVolunteer = volunteerEventNode.data;

        if (eventVolunteer.getVolunteerID().equals(volunteerID)) {
            // Find the event in the eventList based on eventID
            Node<Event> eventNode = eventList.head;
            while (eventNode != null) {
                if (eventNode.data.getEventID().equals(eventVolunteer.getEventID())) {
                    assignedEvents.insert(eventNode.data);
                    break;
                }
                eventNode = eventNode.next;
            }
        }
        volunteerEventNode = volunteerEventNode.next;
    }

    // Check if there are any associated events
    if (assignedEvents.isEmpty()) {
        System.out.println(ANSI_YELLOW + "No events associated with this volunteer." + ANSI_RESET);
        return;
    }

    // Display all events associated with the volunteer
    System.out.println(ANSI_CYAN + "Volunteer: " + foundVolunteer.getName() + " (" + foundVolunteer.getVolunteerID() + ")" + ANSI_RESET);
    System.out.println("Associated Events:");

    Node<Event> assignedEventNode = assignedEvents.head;
    int eventIndex = 1;

    while (assignedEventNode != null) {
        Event event = assignedEventNode.data;
        System.out.println(eventIndex + ". Event ID: " + event.getEventID());
        System.out.println("   Event Name: " + event.getEventName());
        System.out.println("   Date: " + event.getDate());
        System.out.println("   Time: " + event.getTime());
        System.out.println("   Location: " + event.getLocation());
        System.out.println("---------------------------------------");

        assignedEventNode = assignedEventNode.next;
        eventIndex++;
    }
}


private static void generateSummaryReports() {
    LinkedList<Event> eventList = new LinkedList<>();
    LinkedList<Volunteer> volunteerList = new LinkedList<>();
    LinkedList<EventVolunteer> volunteerEventList = new LinkedList<>();

    // Load data from files
    eventList.loadFromFile("event.txt");
    volunteerList.loadFromFile("volunteers.txt");
    volunteerEventList.loadFromFile("volunteer_event.txt");

    Scanner scanner = new Scanner(System.in);

    System.out.println("- - - Report - - -");
    System.out.println("1. Number of Events Held in a Year");
    System.out.println("2. Event with the Most Volunteers");
    System.out.println("3. Event with the Highest Sponsorship Amount");
    System.out.print("Enter your selection: ");
    
    String selectionStr = scanner.nextLine().trim();
    int selection;

    try {
        selection = Integer.parseInt(selectionStr);
    } catch (NumberFormatException e) {
        System.out.println(ANSI_RED + "Invalid choice. Please enter a number." + ANSI_RESET);
        return;
    }

    switch (selection) {
        case 1:
            generateEventsInYearReport(eventList, scanner);
            break;
        case 2:
            generateEventWithMostVolunteersReport(eventList, volunteerEventList);
            break;
        case 3:
            generateEventWithHighestSponsorshipReport(eventList, scanner);
            break;
        default:
            System.out.println(ANSI_RED + "Invalid selection. Please choose between 1, 2, or 3." + ANSI_RESET);
    }
}

private static void generateEventsInYearReport(LinkedList<Event> eventList, Scanner scanner) {
    System.out.print("Enter the year to generate the report for: ");
    int year = getValidInteger(scanner);

    int eventCount = 0;
    Node<Event> currentEvent = eventList.head;

    while (currentEvent != null) {
        int eventYear = currentEvent.data.getDate().getYear() + 1900; 
        if (eventYear == year) {
            eventCount++;
        }
        currentEvent = currentEvent.next;
    }

    System.out.println(ANSI_CYAN + "Number of events held in " + year + ": " + eventCount + ANSI_RESET);
    printStar(eventCount);
}

private static void generateEventWithMostVolunteersReport(LinkedList<Event> eventList, LinkedList<EventVolunteer> volunteerEventList) {
    int maxVolunteers = 0;
    Event maxEvent = null;
    
    Node<Event> currentEvent = eventList.head;
    while (currentEvent != null) {
        int volunteerCount = 0;
        
        Node<EventVolunteer> currentVolEvent = volunteerEventList.head;
        while (currentVolEvent != null) {
            if (currentVolEvent.data.getEventID().equals(currentEvent.data.getEventID())) {
                volunteerCount++;
            }
            currentVolEvent = currentVolEvent.next;
        }
        
        if (volunteerCount > maxVolunteers) {
            maxVolunteers = volunteerCount;
            maxEvent = currentEvent.data;
        }
        
        currentEvent = currentEvent.next;
    }

    if (maxEvent != null) {
        System.out.println(ANSI_CYAN + "Event with the most volunteers: " + maxEvent.getEventName() + " (" + maxEvent.getEventID() + ")" + ANSI_RESET);
        System.out.println("Number of volunteers: " + maxVolunteers);
        printStar(maxVolunteers);
    } else {
        System.out.println(ANSI_YELLOW + "No volunteers found for any events." + ANSI_RESET);
    }
}

private static void generateEventWithHighestSponsorshipReport(LinkedList<Event> eventList, Scanner scanner) {
    LinkedList<Sponsorship> sponsorshipList = new LinkedList<>();
    sponsorshipList.loadFromFile("sponsorship.txt");

    double maxSponsorshipAmount = 0;
    Event maxEvent = null;

    Node<Event> currentEvent = eventList.head;
    while (currentEvent != null) {
        double totalSponsorship = 0;
        
        Node<Sponsorship> currentSponsorship = sponsorshipList.head;
        while (currentSponsorship != null) {
            if (currentSponsorship.data.getEventID().equals(currentEvent.data.getEventID())) {
                totalSponsorship += currentSponsorship.data.getSponsorAmount();
            }
            currentSponsorship = currentSponsorship.next;
        }
        
        if (totalSponsorship > maxSponsorshipAmount) {
            maxSponsorshipAmount = totalSponsorship;
            maxEvent = currentEvent.data;
        }
        
        currentEvent = currentEvent.next;
    }

    if (maxEvent != null) {
        System.out.println(ANSI_CYAN + "Event with the highest sponsorship amount: " + maxEvent.getEventName() + " (" + maxEvent.getEventID() + ")" + ANSI_RESET);
        System.out.println("Total sponsorship: RM" + maxSponsorshipAmount);
        printStar((int) maxSponsorshipAmount);
    } else {
        System.out.println(ANSI_YELLOW + "No sponsorships found for any events." + ANSI_RESET);
    }
}



public static void printStar(int count) {
    if (count > 10) {
        int stars = count / 10;
        for (int i = 0; i < stars; i++) {
            System.out.print(ANSI_BLUE + "*" + ANSI_RESET);
        }
    }
    System.out.println();
}

}


//private static void generateEventsInYearReport(LinkedList<Event> eventList, Scanner scanner) {
//    System.out.print("Enter the year to generate the report for (e.g., 2024): ");
//    String yearStr = scanner.nextLine().trim();
//    int year;
//
//    try {
//        year = Integer.parseInt(yearStr);
//    } catch (NumberFormatException e) {
//        System.out.println(ANSI_RED + "Invalid year format. Please enter a valid year." + ANSI_RESET);
//        return;
//    }
//
//    int eventCount = 0;
//    Node<Event> currentNode = eventList.head;
//
//    while (currentNode != null) {
//        if (getYearFromDate(currentNode.data.getDate()) == year) {
//            eventCount++;
//        }
//        currentNode = currentNode.next;
//    }
//
//    System.out.println(ANSI_CYAN + "Number of Events held in " + year + ": " + eventCount + ANSI_RESET);
//}
//
//
//
//private static int getYearFromDate(Date date) {
//    Calendar calendar = Calendar.getInstance();
//    calendar.setTime(date);
//    return calendar.get(Calendar.YEAR);
//}
//
//}