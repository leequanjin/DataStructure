/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EventSystem;


import adt.LinkedList;
import adt.Node;
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

        System.out.print("Ticket Price: RM");
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

    System.out.print("Sponsor Amount: RM");
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
    Node<Event> currentNode = eventList.head;

    while (currentNode != null) {
        Event tempEvent = currentNode.data;
        if (eventList.contains(tempEvent) && tempEvent.getEventID().equals(eventID)) {
            return tempEvent;
        }
        currentNode = currentNode.next;
    }

    return null;
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
        System.out.println("2. Event Date");
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

    Event foundEvent = null;
    LinkedList<Event> foundEvents = new LinkedList<>();

    switch (searchChoice) {
        case 1:
            System.out.print("Enter the Event ID: ");
            String eventID = scanner.nextLine().trim();
            foundEvent = searchEventByID(eventList, eventID);
            break;
        case 2:
            Date searchDate = getValidDate(scanner);
            foundEvents = searchEventsByDate(eventList, searchDate);
            break;
        default:
            System.out.println(ANSI_RED + "Invalid choice. Please select either 1 or 2." + ANSI_RESET);
            return;
    }

    if (searchChoice == 1 && foundEvent != null) {
        displayEventDetails(foundEvent, ticketList, sponsorshipList);
    } else if (searchChoice == 2 && !foundEvents.isEmpty()) {
        Node<Event> currentEvent = foundEvents.head;
        while (currentEvent != null) {
            displayEventDetails(currentEvent.data, ticketList, sponsorshipList);
            currentEvent = currentEvent.next;
        }
    } else {
        System.out.println(ANSI_RED + "No event found matching the search criteria." + ANSI_RESET);
    }
}

// Sequential Search
private static Event searchEventByID(LinkedList<Event> eventList, String eventID) {
    Node<Event> currentNode = eventList.head;

    while (currentNode != null) {
        Event tempEvent = currentNode.data;
        if (eventList.contains(tempEvent) && tempEvent.getEventID().equals(eventID)) {
            return tempEvent;
        }
        currentNode = currentNode.next;
    }

    return null;
}

// Sequential Search
private static LinkedList<Event> searchEventsByDate(LinkedList<Event> eventList, Date searchDate) {
    LinkedList<Event> foundEvents = new LinkedList<>();
    Node<Event> currentNode = eventList.head;

    while (currentNode != null) {
        Event tempEvent = currentNode.data;
        if (eventList.contains(tempEvent) && tempEvent.getDate().equals(searchDate)) {
            foundEvents.insert(tempEvent);
        }
        currentNode = currentNode.next;
    }

    return foundEvents;
}

private static void displayEventDetails(Event event, LinkedList<Ticket> ticketList, LinkedList<Sponsorship> sponsorshipList) {


    System.out.println(ANSI_CYAN + "\nEvent Found: " + ANSI_RESET);
    System.out.printf("%-15s : %s\n", "Event ID", event.getEventID());
    System.out.printf("%-15s : %s\n", "Event Name", event.getEventName());
    System.out.printf("%-15s : %s\n", "Date", dateFormat.format(event.getDate()));
    System.out.printf("%-15s : %s\n", "Time", event.getTime());
    System.out.printf("%-15s : %s\n", "Location", event.getLocation());

    // List associated tickets in a table format
    System.out.println(ANSI_BLUE + "\nTickets for Event ID " + event.getEventID() + ":" + ANSI_RESET);
    System.out.printf("%-5s | %-10s | %-15s | %-10s\n", "No.", "Ticket ID", "Ticket Type", "Amount (RM)");
    System.out.println("---------------------------------------------");
    Node<Ticket> ticketNode = ticketList.head;
    boolean ticketsFound = false;
    int ticketCount = 1;
    while (ticketNode != null) {
        if (ticketNode.data.getEventID().equals(event.getEventID())) {
            System.out.printf("%-5d | %-10s | %-15s | %-10.2f\n", 
                              ticketCount, 
                              ticketNode.data.getTicketID(), 
                              ticketNode.data.getTicketType(), 
                              ticketNode.data.getTicketPrice());
            ticketsFound = true;
            ticketCount++;
        }
        ticketNode = ticketNode.next;
    }
    if (!ticketsFound) {
        System.out.println(ANSI_YELLOW + "No tickets found for this event." + ANSI_RESET);
    }

    // List associated sponsorships in a table format
    System.out.println(ANSI_PURPLE + "\nSponsorships for Event ID " + event.getEventID() + ":" + ANSI_RESET);
    System.out.printf("%-5s | %-10s | %-20s | %-15s\n", "No.", "Sponsor ID", "Sponsor Name", "Amount (RM)");
    System.out.println("-----------------------------------------------------------");
    Node<Sponsorship> sponsorshipNode = sponsorshipList.head;
    boolean sponsorshipsFound = false;
    int sponsorshipCount = 1;
    while (sponsorshipNode != null) {
        if (sponsorshipNode.data.getEventID().equals(event.getEventID())) {
            System.out.printf("%-5d | %-10s | %-20s | %-15.2f\n", 
                              sponsorshipCount, 
                              sponsorshipNode.data.getSponsorID(), 
                              sponsorshipNode.data.getSponsorName(), 
                              sponsorshipNode.data.getSponsorAmount());
            sponsorshipsFound = true;
            sponsorshipCount++;
        }
        sponsorshipNode = sponsorshipNode.next;
    }
    if (!sponsorshipsFound) {
        System.out.println(ANSI_YELLOW + "No sponsorships found for this event." + ANSI_RESET);
    }
}



public static void updateEvent() {
    LinkedList<Event> eventList = new LinkedList<>();
    LinkedList<Ticket> ticketList = new LinkedList<>();
    LinkedList<Sponsorship> sponsorshipList = new LinkedList<>();
    
    
    eventList.loadFromFile(EVENT_FILE);
    ticketList.loadFromFile(TICKET_FILE);
    sponsorshipList.loadFromFile(SPONSORSHIP_FILE);

    Scanner scanner = new Scanner(System.in);

    
    if (eventList.isEmpty()) {
        System.out.println(ANSI_RED + "No events available to update." + ANSI_RESET);
        return;
    }

    System.out.print("Enter the Event ID to update: ");
    String eventID = scanner.nextLine().trim();

    
    Node<Event> current = eventList.head;
    while (current != null) {
        if (current.data.getEventID().equals(eventID)) {
            Event event = current.data;
            Event updatedEvent = null;
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
                        updatedEvent = new Event(event.getEventID(), newName, event.getDate(), event.getTime(), event.getLocation());
                        eventList.replace(event, updatedEvent);
                        System.out.println(ANSI_GREEN + "Event Name updated successfully!" + ANSI_RESET);
                        break;
                    case "2":
                        Date newDate = getValidDate(scanner);
                        updatedEvent = new Event(event.getEventID(), event.getEventName(), newDate, event.getTime(), event.getLocation());
                        eventList.replace(event, updatedEvent);
                        System.out.println(ANSI_GREEN + "Event Date updated successfully!" + ANSI_RESET);
                        break;
                    case "3":
                        String newTime = getValidTime(scanner);
                        updatedEvent = new Event(event.getEventID(), event.getEventName(), event.getDate(), newTime, event.getLocation());
                        eventList.replace(event, updatedEvent);
                        System.out.println(ANSI_GREEN + "Event Time updated successfully!" + ANSI_RESET);
                        break;
                    case "4":
                        System.out.print("Enter new Location: ");
                        String newLocation = scanner.nextLine().trim();
                        while (newLocation.isEmpty()) {
                            System.out.println(ANSI_RED + "Location cannot be empty. Please enter a valid location." + ANSI_RESET);
                            newLocation = scanner.nextLine().trim();
                        }
                        updatedEvent = new Event(event.getEventID(), event.getEventName(), event.getDate(), event.getTime(), newLocation);
                        eventList.replace(event, updatedEvent);
                        System.out.println(ANSI_GREEN + "Event Location updated successfully!" + ANSI_RESET);
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

                if (updatedEvent != null) {
                    event = updatedEvent; // Update the reference to the current event
                    updatedEvent = null;
                }
            }

            
            eventList.saveToFile(EVENT_FILE);

            System.out.println(ANSI_GREEN + "Event with ID " + eventID + " has been updated." + ANSI_RESET);
            return;
        }
        current = current.next;
    }

    System.out.println(ANSI_RED + "Event with ID " + eventID + " not found." + ANSI_RESET);
}


private static void updateTickets(String eventID, LinkedList<Ticket> ticketList, Scanner scanner) {
    // Prompt the user to choose what they want to update
    System.out.println("\nWhat would you like to update?");
    System.out.println("1. Ticket Type and Price");
    System.out.println("2. Ticket Status");
    System.out.print("Enter your choice: ");
    String updateChoice = scanner.nextLine().trim();

    switch (updateChoice) {
        case "1":
            LinkedList<String> ticketTypes = new LinkedList<>();
            Node<Ticket> currentTicket = ticketList.head;
            int ticketIndex = 1;

            while (currentTicket != null) {
                if (currentTicket.data.getEventID().equals(eventID) && !ticketTypes.contains(currentTicket.data.getTicketType())) {
                    ticketTypes.insert(currentTicket.data.getTicketType());
                    System.out.println(ticketIndex + ". Type: " + currentTicket.data.getTicketType() +
                                       ", Price: RM" + String.format("%.2f", currentTicket.data.getTicketPrice()));
                    ticketIndex++;
                }
                currentTicket = currentTicket.next;
            }

            if (ticketTypes.isEmpty()) {
                System.out.println(ANSI_YELLOW + "No tickets found for this event." + ANSI_RESET);
                return;
            }
            
             // Prompt the user to select which ticket type to update
            System.out.print("Select the ticket type you want to update (1-" + ticketTypes.length() + "): ");
            int selectedTicketIndex = getValidInteger(scanner);

            if (selectedTicketIndex < 1 || selectedTicketIndex > ticketTypes.length()) {
                System.out.println(ANSI_RED + "Invalid selection. Please select between (1-" + ticketTypes.length() + ")" + ANSI_RESET);
                return;
            }

            // Get the selected ticket type
            Node<String> selectedTicketTypeNode = ticketTypes.head;
            for (int i = 1; i < selectedTicketIndex; i++) {
                selectedTicketTypeNode = selectedTicketTypeNode.next;
            }
            String selectedTicketType = selectedTicketTypeNode.data;

            // Update Ticket Type and Price
            System.out.print("Enter new Ticket Type (Current: " + selectedTicketType + "): ");
            String newTicketType = scanner.nextLine().trim();
            if (newTicketType.isEmpty()) {
                System.out.println(ANSI_RED + "Ticket type cannot be empty. Please try again." + ANSI_RESET);
                return;
            }

            System.out.print("Enter new Ticket Price: RM");
            double newTicketPrice = getValidDouble(scanner);

            // Update all tickets with the selected type
            currentTicket = ticketList.head;
            while (currentTicket != null) {
                Ticket ticket = currentTicket.data;
                if (ticket.getEventID().equals(eventID) && ticket.getTicketType().equals(selectedTicketType)) {
                    Ticket updatedTicket = new Ticket(ticket.getEventID(), ticket.getTicketID(), newTicketType, newTicketPrice, ticket.getTicketStatus());
                    ticketList.replace(ticket, updatedTicket);
                }
                currentTicket = currentTicket.next;
            }

            System.out.println(ANSI_GREEN + "All tickets of type '" + selectedTicketType + "' have been updated to new type '" + newTicketType + "' and price RM" + String.format("%.2f", newTicketPrice) + ANSI_RESET);
            break;

        case "2":
            // List all tickets for the event and allow individual status update
            currentTicket = ticketList.head;
            LinkedList<Ticket> eventTickets = new LinkedList<>();
            ticketIndex = 1;

            // Print table header
            System.out.printf("%-5s | %-10s | %-20s | %-10s | %-10s\n", "No.", "Ticket ID", "Ticket Type", "Price (RM)", "Status");
            System.out.println("-------------------------------------------------------------------");

            while (currentTicket != null) {
                if (currentTicket.data.getEventID().equals(eventID)) {
                    eventTickets.insert(currentTicket.data);
                    // Print each ticket in table row format
                    System.out.printf("%-5d | %-10s | %-20s | %-10.2f | %-10s\n",
                                      ticketIndex,
                                      currentTicket.data.getTicketID(),
                                      currentTicket.data.getTicketType(),
                                      currentTicket.data.getTicketPrice(),
                                      currentTicket.data.getTicketStatus());
                    ticketIndex++;
                }
                currentTicket = currentTicket.next;
            }

            // Prompt the user to select which ticket to update the status
            System.out.print("Select the ticket you want to update (1-" + eventTickets.length() + "): ");
            int selectedStatusIndex = getValidInteger(scanner);

            if (selectedStatusIndex < 1 || selectedStatusIndex > eventTickets.length()) {
                System.out.println(ANSI_RED + "Invalid selection. Please select between (1-" + eventTickets.length() + ")" + ANSI_RESET);
                return;
            }

            // Get the selected ticket
            Node<Ticket> selectedTicketNode = eventTickets.head;
            for (int i = 1; i < selectedStatusIndex; i++) {
                selectedTicketNode = selectedTicketNode.next;
            }
            Ticket selectedTicket = selectedTicketNode.data;

            // Update Ticket Status
            System.out.print("Enter new Ticket Status (Current: " + selectedTicket.getTicketStatus() + "): ");
            String newTicketStatus = scanner.nextLine().trim();
            if (newTicketStatus.isEmpty()) {
                System.out.println(ANSI_RED + "Ticket status cannot be empty. Please try again." + ANSI_RESET);
                return;
            }

            Ticket updatedTicket = new Ticket(selectedTicket.getEventID(), selectedTicket.getTicketID(), selectedTicket.getTicketType(), selectedTicket.getTicketPrice(), newTicketStatus);
            ticketList.replace(selectedTicket, updatedTicket);

            System.out.println(ANSI_GREEN + "Ticket status updated successfully!" + ANSI_RESET);
            break;

        default:
            System.out.println(ANSI_RED + "Invalid choice. No changes made." + ANSI_RESET);
            return;
    }

    
    ticketList.saveToFile(TICKET_FILE);
}


private static void updateSponsorships(String eventID, LinkedList<Sponsorship> sponsorshipList, Scanner scanner) {
    // Find and list all sponsorships for the event
    Node<Sponsorship> currentSponsorship = sponsorshipList.head;
    int sponsorshipIndex = 1;
    LinkedList<Sponsorship> eventSponsorships = new LinkedList<>();

    System.out.printf("%-5s | %-12s | %-30s | %-10s\n", "No.", "Sponsor ID", "Sponsor Name", "Amount (RM)");
    System.out.println("---------------------------------------------------------------");

    while (currentSponsorship != null) {
        if (currentSponsorship.data.getEventID().equals(eventID)) {
            System.out.printf("%-5d | %-12s | %-30s | %-10.2f\n",
                    sponsorshipIndex,
                    currentSponsorship.data.getSponsorID(),
                    currentSponsorship.data.getSponsorName(),
                    currentSponsorship.data.getSponsorAmount());
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

    Sponsorship updatedSponsorship = null;
    String successMessage = "";

    switch (updateChoice) {
        case "1":
            // Update Sponsor Name
            System.out.print("Enter new Sponsor Name : ");
            String newSponsorName = scanner.nextLine().trim();
            if (!newSponsorName.isEmpty()) {
                updatedSponsorship = new Sponsorship(selectedSponsorship.getEventID(),
                                                     selectedSponsorship.getSponsorID(),
                                                     newSponsorName,
                                                     selectedSponsorship.getSponsorAmount());
                successMessage = "Sponsor Name updated successfully!";
            }
            break;
        case "2":
            // Update Sponsor Amount
            System.out.print("Enter new Sponsor Amount : RM");
            double newSponsorAmount = getValidDouble(scanner);
            updatedSponsorship = new Sponsorship(selectedSponsorship.getEventID(),
                                                 selectedSponsorship.getSponsorID(),
                                                 selectedSponsorship.getSponsorName(),
                                                 newSponsorAmount);
            successMessage = "Sponsor Amount updated successfully!";
            break;
        case "3":
            // Update Both Name and Amount
            System.out.print("Enter new Sponsor Name : ");
            newSponsorName = scanner.nextLine().trim();
            System.out.print("Enter new Sponsor Amount : RM");
            newSponsorAmount = getValidDouble(scanner);

            if (!newSponsorName.isEmpty()) {
                updatedSponsorship = new Sponsorship(selectedSponsorship.getEventID(),
                                                     selectedSponsorship.getSponsorID(),
                                                     newSponsorName,
                                                     newSponsorAmount);
                successMessage = "Sponsor Name and Amount updated successfully!";
            }
            break;
        default:
            System.out.println(ANSI_RED + "Invalid choice. No changes made." + ANSI_RESET);
            return;
    }

    if (updatedSponsorship != null && sponsorshipList.contains(selectedSponsorship)) {
        sponsorshipList.replace(selectedSponsorship, updatedSponsorship);
        sponsorshipList.saveToFile(SPONSORSHIP_FILE);
        System.out.println(ANSI_GREEN + successMessage + ANSI_RESET);
    } else {
        System.out.println(ANSI_RED + "Error updating sponsorship. Please try again." + ANSI_RESET);
    }
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

    // Header for the event list
    System.out.println(ANSI_CYAN + "\nList of All Events" + ANSI_RESET);
    System.out.printf("%-10s | %-30s | %-12s | %-8s | %-20s\n", "Event ID", "Event Name", "Date", "Time", "Location");
    System.out.println("---------------------------------------------------------------------------------------------");

    // Iterate through all events
    Node<Event> eventNode = eventList.head;
    while (eventNode != null) {
        Event event = eventNode.data;
        System.out.printf("%-10s | %-30s | %-12s | %-8s | %-20s\n",
                event.getEventID(),
                event.getEventName(),
                dateFormat.format(event.getDate()),
                event.getTime(),
                event.getLocation());

        // Filter and list tickets associated with the current event
        System.out.println(ANSI_BLUE + "\nTickets for Event ID " + event.getEventID() + ":" + ANSI_RESET);
        System.out.printf("%-5s | %-10s | %-15s | %-10s\n", "No.", "Ticket ID", "Ticket Type", "Amount (RM)");
        System.out.println("-----------------------------------------------");

        Node<Ticket> ticketNode = ticketList.head;
        boolean ticketsFound = false;
        int ticketCount = 1;
        while (ticketNode != null) {
            Ticket ticket = ticketNode.data;
            if (ticket.getEventID().equals(event.getEventID())) {
                System.out.printf("%-5d | %-10s | %-15s | %-10.2f\n",
                        ticketCount,
                        ticket.getTicketID(),
                        ticket.getTicketType(),
                        ticket.getTicketPrice());
                ticketsFound = true;
                ticketCount++;
            }
            ticketNode = ticketNode.next;
        }
        if (!ticketsFound) {
            System.out.println(ANSI_YELLOW + "No tickets found for this event." + ANSI_RESET);
        }

        // Filter and list sponsorships associated with the current event
        System.out.println(ANSI_PURPLE + "\nSponsorships for Event ID " + event.getEventID() + ":" + ANSI_RESET);
        System.out.printf("%-5s | %-12s | %-25s | %-10s\n", "No.", "Sponsor ID", "Sponsor Name", "Amount (RM)");
        System.out.println("---------------------------------------------------------------");

        Node<Sponsorship> sponsorshipNode = sponsorshipList.head;
        boolean sponsorshipsFound = false;
        int sponsorshipCount = 1;
        while (sponsorshipNode != null) {
            Sponsorship sponsorship = sponsorshipNode.data;
            if (sponsorship.getEventID().equals(event.getEventID())) {
                System.out.printf("%-5d | %-12s | %-25s | %-10.2f\n",
                        sponsorshipCount,
                        sponsorship.getSponsorID(),
                        sponsorship.getSponsorName(),
                        sponsorship.getSponsorAmount());
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
    volunteerEventList.loadFromFile(VOLUNTEER_EVENT_FILE);

    Scanner scanner = new Scanner(System.in);

    
    if (volunteerList.isEmpty()) {
        System.out.println(ANSI_YELLOW + "No volunteers found." + ANSI_RESET);
        return;
    }

    
    System.out.print("Enter the Volunteer ID: ");
    String volunteerID = scanner.nextLine().trim();

    
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

    // Check if there are any associated events
    if (assignedEvents.isEmpty()) {
        System.out.println(ANSI_YELLOW + "No events associated with this volunteer." + ANSI_RESET);
        return;
    }

    // Display all events associated with the volunteer in table format
    System.out.println(ANSI_CYAN + "Volunteer ID: " + foundVolunteer.getVolunteerID() + ANSI_RESET);
    System.out.println(ANSI_CYAN + "Volunteer Name: " + foundVolunteer.getName() + ANSI_RESET);
    System.out.println("Events Participated:");
    System.out.printf("%-5s | %-10s | %-30s | %-12s | %-8s | %-20s\n", "No.", "Event ID", "Event Name", "Date", "Time", "Location");
    System.out.println("---------------------------------------------------------------------------------------------");

    Node<Event> assignedEventNode = assignedEvents.head;
    int eventIndex = 1;

    while (assignedEventNode != null) {
        Event event = assignedEventNode.data;
        System.out.printf("%-5d | %-10s | %-30s | %-12s | %-8s | %-20s\n",
                eventIndex,
                event.getEventID(),
                event.getEventName(),
                dateFormat.format(event.getDate()),
                event.getTime(),
                event.getLocation());

        assignedEventNode = assignedEventNode.next;
        eventIndex++;
    }

    // Ask the user which event to remove
    System.out.print("Enter the Event ID to remove from this volunteer: ");
    String eventIDToRemove = scanner.nextLine().trim();

    // Find the association in volunteerEventList and remove it
    volunteerEventList.removeIf(eventVolunteer -> eventVolunteer.getVolunteerID().equals(volunteerID) && eventVolunteer.getEventID().equals(eventIDToRemove));

    // Save the updated volunteer-event list back to the file
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

    
    System.out.println(ANSI_CYAN + "Volunteer ID: " + foundVolunteer.getVolunteerID() + ANSI_RESET);
    System.out.println(ANSI_CYAN + "Volunteer Name: " + foundVolunteer.getName() + ANSI_RESET);
    System.out.println("Events Participated:");

    System.out.printf("%-5s | %-10s | %-30s | %-12s | %-8s | %-20s\n", "No.", "Event ID", "Event Name", "Date", "Time", "Location");
    System.out.println("---------------------------------------------------------------------------------------------");

    Node<Event> assignedEventNode = assignedEvents.head;
    int eventIndex = 1;

    while (assignedEventNode != null) {
        Event event = assignedEventNode.data;
        System.out.printf("%-5d | %-10s | %-30s | %-12s | %-8s | %-20s\n",
                eventIndex,
                event.getEventID(),
                event.getEventName(),
                dateFormat.format(event.getDate()),
                event.getTime(),
                event.getLocation());

        assignedEventNode = assignedEventNode.next;
        eventIndex++;
    }
}

public static void generateSummaryReports() {
    boolean cont = true;
    do {
        System.out.println("Summary Report");
        String[] reportMenu = {
            "Frequency of Events Held in Year", 
            "Top 5 Events with the Most Volunteers", 
            "Top 5 Events with the Highest Sponsorship Amount"
        };
        int reportSelection = menuIntReturn(reportMenu);

        switch (reportSelection) {
            case 1:
                numberOfEventsInYearReport();
                break;
            case 2:
                topEventsWithMostVolunteersReport();
                break;
            case 3:
                topEventsWithHighestSponsorshipReport();
                break;
            default:
                System.out.println(ANSI_RED + "Invalid report selection." + ANSI_RESET);
                break;
        }

        cont = YN("Do you want to view another report?");

    } while (cont == true);
}

public static void numberOfEventsInYearReport() {
    LinkedList<Event> eventList = new LinkedList<>();
    eventList.loadFromFile("event.txt");

    if (eventList.isEmpty()) {
        System.out.println(ANSI_RED + "No events found." + ANSI_RESET);
        return;
    }

    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the start year (YYYY): ");
    int startYear = scanner.nextInt();
    System.out.print("Enter the end year (YYYY): ");
    int endYear = scanner.nextInt();

    int[] eventCounts = new int[endYear - startYear + 1];

    Node<Event> currentEvent = eventList.head;
    while (currentEvent != null) {
        int eventYear = currentEvent.data.getDate().getYear() + 1900;
        if (eventYear >= startYear && eventYear <= endYear) {
            eventCounts[eventYear - startYear]++;
        }
        currentEvent = currentEvent.next;
    }

    System.out.println("\nNumber of Events Held by Year:");
    for (int i = 0; i < eventCounts.length; i++) {
        System.out.print((startYear + i) + ": ");
        printStar(eventCounts[i]);
        System.out.println(" (" + eventCounts[i] + ")");
        
    }
    System.out.println("\nNote: \n" + ANSI_PURPLE + "*" + ANSI_RESET + " represents 100" + "\n" + ANSI_BLUE + "*" + ANSI_RESET + " represents 10" );
}

public static void topEventsWithMostVolunteersReport() {
    LinkedList<Event> eventList = new LinkedList<>();
    LinkedList<EventVolunteer> volunteerEventList = new LinkedList<>();

    eventList.loadFromFile("event.txt");
    volunteerEventList.loadFromFile("volunteer_event.txt");

    if (eventList.isEmpty() || volunteerEventList.isEmpty()) {
        System.out.println(ANSI_RED + "No events or volunteer data found." + ANSI_RESET);
        return;
    }

    Event[] topEvents = new Event[5];
    int[] volunteerCounts = new int[5];

    Node<Event> currentEvent = eventList.head;
    while (currentEvent != null) {
        int volunteerCount = 0;
        Node<EventVolunteer> currentVolunteerEvent = volunteerEventList.head;
        while (currentVolunteerEvent != null) {
            if (currentVolunteerEvent.data.getEventID().equals(currentEvent.data.getEventID())) {
                volunteerCount++;
            }
            currentVolunteerEvent = currentVolunteerEvent.next;
        }

        for (int i = 0; i < 5; i++) {
            if (volunteerCount > volunteerCounts[i]) {
                for (int j = 4; j > i; j--) {
                    topEvents[j] = topEvents[j - 1];
                    volunteerCounts[j] = volunteerCounts[j - 1];
                }
                topEvents[i] = currentEvent.data;
                volunteerCounts[i] = volunteerCount;
                break;
            }
        }

        currentEvent = currentEvent.next;
    }

    System.out.println("\nTop 5 Events with the Most Volunteers:");
    for (int i = 0; i < 5; i++) {
        if (topEvents[i] != null) {
            System.out.print((i + 1) + ". " + topEvents[i].getEventName() + " ");
            printStar(volunteerCounts[i]);
            System.out.println(" (" + volunteerCounts[i] + ")");
            
        }
    }
    System.out.println("\nNote: \n" + ANSI_PURPLE + "*" + ANSI_RESET + " represents 100" + "\n" + ANSI_BLUE + "*" + ANSI_RESET + " represents 10" );
}

public static void topEventsWithHighestSponsorshipReport() {
    LinkedList<Event> eventList = new LinkedList<>();
    LinkedList<Sponsorship> sponsorshipList = new LinkedList<>();

    eventList.loadFromFile("event.txt");
    sponsorshipList.loadFromFile("sponsorship.txt");

    if (eventList.isEmpty() || sponsorshipList.isEmpty()) {
        System.out.println(ANSI_RED + "No events or sponsorship data found." + ANSI_RESET);
        return;
    }

    Event[] topEvents = new Event[5];
    double[] sponsorshipAmounts = new double[5];

    Node<Event> currentEvent = eventList.head;
    while (currentEvent != null) {
        double totalSponsorshipAmount = 0;
        Node<Sponsorship> currentSponsorship = sponsorshipList.head;
        while (currentSponsorship != null) {
            if (currentSponsorship.data.getEventID().equals(currentEvent.data.getEventID())) {
                totalSponsorshipAmount += currentSponsorship.data.getSponsorAmount();
            }
            currentSponsorship = currentSponsorship.next;
        }

        for (int i = 0; i < 5; i++) {
            if (totalSponsorshipAmount > sponsorshipAmounts[i]) {
                for (int j = 4; j > i; j--) {
                    topEvents[j] = topEvents[j - 1];
                    sponsorshipAmounts[j] = sponsorshipAmounts[j - 1];
                }
                topEvents[i] = currentEvent.data;
                sponsorshipAmounts[i] = totalSponsorshipAmount;
                break;
            }
        }

        currentEvent = currentEvent.next;
    }

    System.out.println("\nTop 5 Events with the Highest Sponsorship Amount:");
    for (int i = 0; i < 5; i++) {
        if (topEvents[i] != null) {
            System.out.print((i + 1) + ". " + topEvents[i].getEventName() + " ");
            printStar((int) sponsorshipAmounts[i]);
            System.out.println(" (RM" + String.format("%.2f", sponsorshipAmounts[i]) + ")");
            
             
            
        }
    }
    System.out.println("\nNote: \n" + ANSI_PURPLE + "*" + ANSI_RESET + " represents 100" + "\n" + ANSI_BLUE + "*" + ANSI_RESET + " represents 10" );
}

public static void printStar(int count) {
    int blueStarCount = count / 100; 
    int purpleStarCount = (count % 100) / 10; 
    int normalStarCount = count % 10;

    // Print purple stars
    for (int i = 0; i < blueStarCount; i++) {
        System.out.print(ANSI_BLUE + "*" + ANSI_RESET);
    }

    // Print blue stars
    for (int i = 0; i < purpleStarCount; i++) {
        System.out.print(ANSI_PURPLE + "*" + ANSI_RESET);
    }

    // Print normal stars
    for (int i = 0; i < normalStarCount; i++) {
        System.out.print("*");
    }
}


public static boolean YN(String sentence) {
        Scanner scan = new Scanner(System.in);
        
        boolean validInput = false;
        String input;
        
        System.out.print("\n" + sentence + "\nPlease enter Y / N: ");

        while (!validInput) {

            input = (scan.nextLine()).toUpperCase().trim();
            
            if (input.equals("Y")) {
                validInput = true;
                return true;
            } else if (input.equals("N")) {
                validInput = true;
                return false;
            } else {
                System.out.println(ANSI_RED + "Please enter Y or N only.\n"+ ANSI_RESET);
                System.out.print("Enter again: ");
            }
        }
        return false;
    }
    
    public static int menuIntReturn(String[] selectionList){
        
        Scanner scan = new Scanner(System.in);
        
        int intInput = 0;
        boolean validInput = false;
        
        for(int i = 0; i < selectionList.length; i++){
            System.out.println( ( i + 1 ) + ". " + selectionList[i]);
        }
        System.out.print("Enter your selection: ");
        
        while(validInput == false){
            String stringInput = scan.nextLine();
            
            if(stringInput.isEmpty()){

                System.out.println(ANSI_RED + "Cannot leave blank.\n" + ANSI_RESET);
                System.out.print("Enter again: ");

            }else{
                try {
                    intInput = Integer.parseInt(stringInput);

                    if (intInput < 1 || intInput > selectionList.length) {
                        System.out.println(ANSI_RED + "Invalid integer. Please enter between 1 to " + selectionList.length + ".\n" + ANSI_RESET);

                        System.out.print("Enter again: ");

                    } else {
                        validInput = true; 
                    }

                } catch (NumberFormatException e) {

                    System.out.println(ANSI_RED + "Invalid input. Please enter correct integer.\n" + ANSI_RESET);
                    System.out.print("Enter again: ");

                }
            }
        }
        
        return intInput;
    }

}



