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
public class EControl {
    
    // Common Use File
    private static final String EVENT_FILE = "C:\\Users\\Clarist Liew\\Downloads\\DataStruc\\DataStructure\\Assignment_test\\event.txt";
    private static final String TICKET_FILE = "C:\\Users\\Clarist Liew\\Downloads\\DataStruc\\DataStructure\\Assignment_test\\ticket.txt";
    private static final String SPONSORSHIP_FILE = "C:\\Users\\Clarist Liew\\Downloads\\DataStruc\\DataStructure\\Assignment_test\\sponsorship.txt";
    private static final String VOLUNTEER_FILE = "C:\\Users\\Clarist Liew\\Downloads\\DataStruc\\DataStructure\\Assignment_test\\volunteers.txt";
    private static final String VOLUNTEER_EVENT_FILE = "C:\\Users\\Clarist Liew\\Downloads\\DataStruc\\DataStructure\\Assignment_test\\volunteer_event.txt";
    
    //Common use Linked List
    private static final LinkedListInterface<Event> eventList = new LinkedList<>();
    private static final LinkedListInterface<Sponsorship> sponsorshipList = new LinkedList<>();
    private static final LinkedListInterface<Ticket> ticketList = new LinkedList<>();
    private static final LinkedListInterface<EventVolunteer> volunteerEventList = new LinkedList<>();
    private static final LinkedListInterface<Volunteer> volunteerList = new LinkedList<>();
    
    public static Scanner scan = new Scanner(System.in);
    
    
    public static int menuIntReturn(String[] selectionList) {
        
        EBoundary.displayMenu(selectionList);
        
        int intInput = 0;
        boolean validInput = false;

        while (validInput == false) {
            String stringInput = scan.nextLine();

            validInput = chkIntInputInRange(stringInput, 1, selectionList.length);

            if (validInput) {
                intInput = Integer.parseInt(stringInput);
            }

        }

        return intInput;
    }
    
    public static boolean chkEmptyInput(String input){
        if(input.isEmpty()){
            return false;
        }else{
            return true;
        }
    }
    
    public static boolean chkInt(String input){
        try {
            int number = Integer.parseInt(input);

            return true;
            
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static boolean intSelectionValidation(int input,int initial, int length){

        if (input < initial || input > length) {
            return false;
        } else {
            return true; 
        }
    }

    public static boolean chkIntInputInRange(String input, int initial, int end) {
        boolean validInput;

        // check is empty
        validInput = chkEmptyInput(input);
        if (validInput) {

            // chk is it an integer
            validInput = chkInt(input);
            if (validInput) {

                // chk is within the integer range
                int intInput = Integer.parseInt(input);
                validInput = intSelectionValidation(intInput, initial, end);
                if (!validInput) {
                    EUtility.intNotInRange(initial, end);
                }

            } else {
                EUtility.invalidIntInput();
            }

        } else {
            EUtility.emptyInputErrorMsg();
        }

        if (!validInput) {
            EBoundary.reEnter();
        }

        return validInput;
    }
    
    
    public static boolean chkYN(String input){
        input = input.toUpperCase().trim();
        if (input.equals("Y")) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean YN(String sentence) {

        boolean validInput = false;
        String input = null;

        EBoundary.inputYN(sentence);
        while (!validInput) {

            input = (scan.nextLine()).toUpperCase().trim();

            validInput = chkEmptyInput(input);

            if (validInput) {
                String[] inputList = {"Y", "N"};
                validInput = chkSpecificWord(inputList, input);
                if (!validInput) {
                    EUtility.enterYNOnly();
                }
            } else {
                EUtility.emptyInputErrorMsg();
            }

            if (!validInput) {
                EBoundary.reEnter();
            }

        }

        boolean cont = chkYN(input);
        if (cont) {
            return true;
        } else {
            return false;
        }
    }
    
    // Input data consistent
    public static boolean chkSpecificWord(String[] inputList, String input){
        for(int i = 0; i < inputList.length; i++){
            if(input.trim().toUpperCase().equalsIgnoreCase(inputList[i].trim().toUpperCase())){
                return true;
            }
        }
        return false;
    }
    
    private static String generateEventID(String prefix) {
    
    int maxId = 0;

    Node<Event> current = eventList.getHead();

    while (current != null) {
        String currentId = current.data.getEventID().substring(2,7);
        int idNumber = Integer.parseInt(currentId);
        if (idNumber > maxId) {
            maxId = idNumber;
        }
        current = current.next;
    }

    return prefix + String.format("%05d", maxId + 1);
    }
    
    private static String generateTicketID(String prefix) {
    
    int maxId = 0;

    Node<Ticket> current = ticketList.getHead();

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

private static String generateSponsorshipID(String prefix) {
    
    int maxId = 0;

    Node<Sponsorship> current = sponsorshipList.getHead();

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
    
    
    
    private static Date getValidDate(Scanner scan) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        while (true) {
            EBoundary.inputDate(); 
            String dateString = scan.nextLine().trim();

            // Check if the format is correct
            if (!dateString.matches("\\d{2}/\\d{2}/\\d{4}")) {
                EUtility.invalidDateMsg();
                continue;
            }

            String[] parts = dateString.split("/");
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);

            // Check if the month is valid (1-12)
            if (month < 1 || month > 12) {
                EUtility.invalidMonth();
                continue;
            }

            // Check if the day is valid for the given month and year
            if (!isValidDay(day, month, year)) {
                EUtility.invalidDay();
                continue;
            }

            try {
                date = dateFormat.parse(dateString);

                // Additional validation to ensure the date was parsed correctly
                if (!dateString.equals(dateFormat.format(date))) {
                    EUtility.invalidDateMsg();
                    date = null;
                    continue;
                }

                break;

            } catch (ParseException e) {
                EUtility.invalidDateMsg();
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
        timeFormat.setLenient(false);  

        String time = "";

        while (true) {
            EBoundary.inputTime();
            time = scanner.nextLine().trim();

            try {
                timeFormat.parse(time);
                break;
            } catch (ParseException e) {
                EUtility.invalidTime();
            }
        }

        return time;
    }
    
    private static int getValidInteger(Scanner scanner) {
    while (true) {
        try {
            int value = Integer.parseInt(scanner.nextLine().trim());
            if (value > 0) {
                return value;
            } else {
                EUtility.invalidNegativeValue();
            }
        } catch (NumberFormatException e) {
            EUtility.invalidIntInput();
        }
    }
}
    private static double getValidDouble(Scanner scanner) {
    while (true) {
        try {
            return Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
           EUtility.invalidIntInput();
        }
    }
    }  
    
    // Sequential Search
    private static Event findEventByID(String eventID) {
    Node<Event> currentNode = eventList.getHead();

    while (currentNode != null) {
        Event tempEvent = currentNode.data;
        if (eventList.contains(tempEvent) && tempEvent.getEventID().equals(eventID)) {
            return tempEvent;
        }
        currentNode = currentNode.next;
    }

    return null;
}


    
    
    
    
    public static void loadAllFile(){
        eventList.loadFromFile(EVENT_FILE);
        ticketList.loadFromFile(TICKET_FILE);
        sponsorshipList.loadFromFile(SPONSORSHIP_FILE);
        volunteerEventList.loadFromFile(VOLUNTEER_EVENT_FILE);
        volunteerList.loadFromFile(VOLUNTEER_FILE);
    }
    
    
    
    
    
    
    public static void main(String[] args) {
        eventMainMenu();
    }
    
    public static void eventMainMenu(){
        boolean cont = true;
        do {
            
            loadAllFile();
            int selection = EBoundary.eventMainMenu();
            switch (selection) {
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
                    //updateEvent();
                    break;
                case 7:
                    //listAllEvents();
                    break;
                case 8:
                    //removeEventFromAVolunteer();
                    break;
                case 9:
                    //listAllEventsForAVolunteer();
                    break;
                case 10:
                    //generateSummaryReports();
                    break;
                case 11:
                    System.out.println("Exiting..."); // change later
                    break;
                
            }

            cont = YN("Do you want to continue manage event?");
            if (cont) {
                System.out.println();
            }
        } while (cont);

    }
    

    private static void addEvent() {

        String eventID = EControl.generateEventID("EV");

        EBoundary.inputEventName();
        String eventName = null;
        boolean validName = false;
        while (!validName) {
            eventName = scan.nextLine().trim();
            validName = EControl.chkEmptyInput(eventName);
            if (!validName) {
                EUtility.emptyInputErrorMsg();
                EBoundary.inputEventName();
            }
        }

        Date date = getValidDate(scan);
        String time = getValidTime(scan);

        EBoundary.inputLocation();
        String location = null;
        boolean validLocation = false;
        while (!validLocation) {
            location = scan.nextLine().trim();
            validLocation = EControl.chkEmptyInput(location);
            if (!validLocation) {
                EUtility.emptyInputErrorMsg();
                EBoundary.inputLocation();
            }
        }

        Event event = new Event(eventID, eventName, date, time, location);
        eventList.insert(event);
        //display +format
        eventList.saveToFile(EVENT_FILE);
        EUtility.eventAddedMsg();

    }

    
    private static void addTicket() {
        
        if (eventList.isEmpty()) {
            EUtility.eventNotExist();
            return;
        }
        String eventID;
        Event event;

        while (true) {
            EBoundary.inputTicketEventID();
            eventID = scan.nextLine().trim();

            event = findEventByID(eventID);
            if (event != null) {
                break;
            } else {
                EUtility.invalidEventID();
            }
        }

        EBoundary.inputNumTicketType();
        int numberOfTicketTypes = getValidInteger(scan);

        for (int i = 1; i <= numberOfTicketTypes; i++) {
            System.out.println("\nTicket Type " + i + ":");

            EBoundary.inputTicketType();
            String ticketType = scan.nextLine().trim();
            while (ticketType.isEmpty()) {
                EUtility.emptyTicketType();
                ticketType = scan.nextLine().trim();
            }

            EBoundary.inputTicketPrice();
            double ticketPrice = getValidDouble(scan);

            EBoundary.inputTicketAmt();
            int ticketAmount = getValidInteger(scan);

            for (int j = 0; j < ticketAmount; j++) {
                String ticketID = EControl.generateTicketID("TK");
                Ticket ticket = new Ticket(eventID, ticketID, ticketType, ticketPrice, "Available");
                ticketList.insert(ticket);
            }
            //display +format
            ticketList.saveToFile(TICKET_FILE);
            EUtility.ticketAddedMsg();
        }
    }
    
    private static void addSponsorship() {
        
        if (eventList.isEmpty()) {
            EUtility.eventNotExist();
            return;
        }
        
        String eventID;
        Event event;

        while (true) {
            EBoundary.inputSponsEventID();
            eventID = scan.nextLine().trim();

            event = findEventByID(eventID);
            if (event != null) {
                break;
            } else {
                EUtility.invalidEventID();
            }
        }

        String sponsorID = generateSponsorshipID("SP");

        EBoundary.inputSponsName();
        String sponsorName = scan.nextLine().trim();
        while (sponsorName.isEmpty()) {
            EUtility.emptySponsName();
            sponsorName = scan.nextLine().trim();
        }

        EBoundary.inputSponsAmt();
        double sponsorAmount = getValidDouble(scan);

        Sponsorship sponsorship = new Sponsorship(eventID, sponsorID, sponsorName, sponsorAmount);
        sponsorshipList.insert(sponsorship);
        //display
        sponsorshipList.saveToFile(SPONSORSHIP_FILE);
        EUtility.SponsAddedMsg();
    }

    public static void removeEvent() {

        if (eventList.isEmpty()) {
            EUtility.eventNotExistToRemove();
            return;
        }

        EBoundary.displayEvent();
        //display +format

        EBoundary.inputRemoveEventID();
        String eventID = scan.nextLine().trim();

        // Remove the event with the specified eventID
        eventList.removeIf(event -> event.getEventID().equals(eventID));

        // Remove associated tickets and sponsorships
        ticketList.removeIf(ticket -> ticket.getEventID().equals(eventID));
        sponsorshipList.removeIf(sponsorship -> sponsorship.getEventID().equals(eventID));

        ticketList.saveToFile(TICKET_FILE);
        sponsorshipList.saveToFile(SPONSORSHIP_FILE);
        eventList.saveToFile(EVENT_FILE);
        EUtility.eventRemovedMsg();
    }
    
    

    public static void searchEvent() {

        if (eventList.isEmpty()) {
            EUtility.eventNotExist();
            return;
        }

        int searchChoice = EBoundary.searchEventMenu();

        Event foundEvent = null;
        LinkedList<Event> foundEvents = new LinkedList<>();

        switch (searchChoice) {
            case 1:
                EBoundary.inputEventID();
                String eventID = scan.nextLine().trim(); 
                foundEvent = findEventByID(eventID);
                break;
            case 2:
                Date searchDate = getValidDate(scan);
                foundEvents = searchEventsByDate(searchDate);
                break;
            default:
                EUtility.invalidMenuChoice();
                return;
        }

        if (searchChoice == 1 && foundEvent != null) {
            disEventDetails(foundEvent);
        } else if (searchChoice == 2 && !foundEvents.isEmpty()) {
            disEventsByDate(foundEvents);
        } else {
            EUtility.searchEventNotExist();
        }
    }
    
    // Sequential Search
    private static LinkedList<Event> searchEventsByDate(Date searchDate) {
        LinkedList<Event> foundEvents = new LinkedList<>();
        Node<Event> currentNode = eventList.getHead();

        while (currentNode != null) {
            Event tempEvent = currentNode.data;
            if (eventList.contains(tempEvent) && tempEvent.getDate().equals(searchDate)) {
                foundEvents.insert(tempEvent);
            }
            currentNode = currentNode.next;
        }

        return foundEvents;
    }

    

    public static void disEventDetails(Event event) {
        EBoundary.disEventDetails(event);
        disEventTickets(event.getEventID());
        disEventSponsorships(event.getEventID());
    }

    public static void disEventsByDate(LinkedList<Event> foundEvents) {
        Node<Event> currentEvent = foundEvents.getHead();
        int show = 1;
        while (currentEvent != null) {
            EBoundary.disEventByDate(show, currentEvent.data);
            disEventTickets(currentEvent.data.getEventID());
            disEventSponsorships(currentEvent.data.getEventID());
            currentEvent = currentEvent.next;
            show++;
        }
    }

    public static void disEventTickets(String eventID) {
        Node<Ticket> ticketNode = ticketList.getHead();
        int ticketCount = 1;
        boolean ticketsFound = false;

        while (ticketNode != null) {
            if (ticketNode.data.getEventID().equals(eventID)) {
                EBoundary.disTicketDetails(ticketCount, ticketNode.data);
                ticketsFound = true;
                ticketCount++;
            }
            ticketNode = ticketNode.next;
        }

        if (!ticketsFound) {
            EUtility.noTicketsFound();
        }
    }

    public static void disEventSponsorships(String eventID) {
        Node<Sponsorship> sponsorshipNode = sponsorshipList.getHead();
        int sponsorshipCount = 1;
        boolean sponsorshipsFound = false;

        while (sponsorshipNode != null) {
            if (sponsorshipNode.data.getEventID().equals(eventID)) {
                EBoundary.disSponsorshipDetails(sponsorshipCount, sponsorshipNode.data);
                sponsorshipsFound = true;
                sponsorshipCount++;
            }
            sponsorshipNode = sponsorshipNode.next;
        }

        if (!sponsorshipsFound) {
            EUtility.noSponsorshipsFound();
        }
    }
    
//    public static void updateEvent() {
//    LinkedList<Event> eventList = new LinkedList<>();
//    LinkedList<Ticket> ticketList = new LinkedList<>();
//    LinkedList<Sponsorship> sponsorshipList = new LinkedList<>();
//    
//    
//    eventList.loadFromFile(EVENT_FILE);
//    ticketList.loadFromFile(TICKET_FILE);
//    sponsorshipList.loadFromFile(SPONSORSHIP_FILE);
//
//    Scanner scanner = new Scanner(System.in);
//
//    
//    if (eventList.isEmpty()) {
//        System.out.println(ANSI_RED + "No events available to update." + ANSI_RESET);
//        return;
//    }
//
//    System.out.print("Enter the Event ID to update: ");
//    String eventID = scanner.nextLine().trim();
//
//    
//    Node<Event> current = eventList.head;
//    while (current != null) {
//        if (current.data.getEventID().equals(eventID)) {
//            Event event = current.data;
//            Event updatedEvent = null;
//            boolean continueUpdating = true;
//
//            while (continueUpdating) {
//                // Show update options
//                System.out.println("\nWhat would you like to update?");
//                System.out.println("1. Event Name");
//                System.out.println("2. Event Date");
//                System.out.println("3. Event Time");
//                System.out.println("4. Event Location");
//                System.out.println("5. Ticket");
//                System.out.println("6. Sponsorships");
//                System.out.println("7. Save Changes ");
//                System.out.print("Enter your choice: ");
//
//                String choice = scanner.nextLine().trim();
//
//                switch (choice) {
//                    case "1":
//                        System.out.print("Enter new Event Name: ");
//                        String newName = scanner.nextLine().trim();
//                        while (newName.isEmpty()) {
//                            System.out.println(ANSI_RED + "Event Name cannot be empty. Please enter a valid name." + ANSI_RESET);
//                            newName = scanner.nextLine().trim();
//                        }
//                        updatedEvent = new Event(event.getEventID(), newName, event.getDate(), event.getTime(), event.getLocation());
//                        eventList.replace(event, updatedEvent);
//                        System.out.println(ANSI_GREEN + "Event Name updated successfully!" + ANSI_RESET);
//                        break;
//                    case "2":
//                        Date newDate = getValidDate(scanner);
//                        updatedEvent = new Event(event.getEventID(), event.getEventName(), newDate, event.getTime(), event.getLocation());
//                        eventList.replace(event, updatedEvent);
//                        System.out.println(ANSI_GREEN + "Event Date updated successfully!" + ANSI_RESET);
//                        break;
//                    case "3":
//                        String newTime = getValidTime(scanner);
//                        updatedEvent = new Event(event.getEventID(), event.getEventName(), event.getDate(), newTime, event.getLocation());
//                        eventList.replace(event, updatedEvent);
//                        System.out.println(ANSI_GREEN + "Event Time updated successfully!" + ANSI_RESET);
//                        break;
//                    case "4":
//                        System.out.print("Enter new Location: ");
//                        String newLocation = scanner.nextLine().trim();
//                        while (newLocation.isEmpty()) {
//                            System.out.println(ANSI_RED + "Location cannot be empty. Please enter a valid location." + ANSI_RESET);
//                            newLocation = scanner.nextLine().trim();
//                        }
//                        updatedEvent = new Event(event.getEventID(), event.getEventName(), event.getDate(), event.getTime(), newLocation);
//                        eventList.replace(event, updatedEvent);
//                        System.out.println(ANSI_GREEN + "Event Location updated successfully!" + ANSI_RESET);
//                        break;
//                    case "5":
//                        updateTickets(eventID, ticketList, scanner);
//                        break;
//                    case "6":
//                        updateSponsorships(eventID, sponsorshipList, scanner);
//                        break;
//                    case "7":
//                        continueUpdating = false;
//                        break;
//                    default:
//                        System.out.println(ANSI_RED + "Invalid choice. Please try again." + ANSI_RESET);
//                }
//
//                if (updatedEvent != null) {
//                    event = updatedEvent; // Update the reference to the current event
//                    updatedEvent = null;
//                }
//            }
//
//            
//            eventList.saveToFile(EVENT_FILE);
//
//            System.out.println(ANSI_GREEN + "Event with ID " + eventID + " has been updated." + ANSI_RESET);
//            return;
//        }
//        current = current.next;
//    }
//
//    System.out.println(ANSI_RED + "Event with ID " + eventID + " not found." + ANSI_RESET);
//}
//
//
//private static void updateTickets(String eventID, LinkedList<Ticket> ticketList, Scanner scanner) {
//    // Prompt the user to choose what they want to update
//    System.out.println("\nWhat would you like to update?");
//    System.out.println("1. Ticket Type and Price");
//    System.out.println("2. Ticket Status");
//    System.out.print("Enter your choice: ");
//    String updateChoice = scanner.nextLine().trim();
//
//    switch (updateChoice) {
//        case "1":
//            LinkedList<String> ticketTypes = new LinkedList<>();
//            Node<Ticket> currentTicket = ticketList.head;
//            int ticketIndex = 1;
//
//            while (currentTicket != null) {
//                if (currentTicket.data.getEventID().equals(eventID) && !ticketTypes.contains(currentTicket.data.getTicketType())) {
//                    ticketTypes.insert(currentTicket.data.getTicketType());
//                    System.out.println(ticketIndex + ". Type: " + currentTicket.data.getTicketType() +
//                                       ", Price: RM" + String.format("%.2f", currentTicket.data.getTicketPrice()));
//                    ticketIndex++;
//                }
//                currentTicket = currentTicket.next;
//            }
//
//            if (ticketTypes.isEmpty()) {
//                System.out.println(ANSI_YELLOW + "No tickets found for this event." + ANSI_RESET);
//                return;
//            }
//            
//             // Prompt the user to select which ticket type to update
//            System.out.print("Select the ticket type you want to update (1-" + ticketTypes.length() + "): ");
//            int selectedTicketIndex = getValidInteger(scanner);
//
//            if (selectedTicketIndex < 1 || selectedTicketIndex > ticketTypes.length()) {
//                System.out.println(ANSI_RED + "Invalid selection. Please select between (1-" + ticketTypes.length() + ")" + ANSI_RESET);
//                return;
//            }
//
//            // Get the selected ticket type
//            Node<String> selectedTicketTypeNode = ticketTypes.head;
//            for (int i = 1; i < selectedTicketIndex; i++) {
//                selectedTicketTypeNode = selectedTicketTypeNode.next;
//            }
//            String selectedTicketType = selectedTicketTypeNode.data;
//
//            // Update Ticket Type and Price
//            System.out.print("Enter new Ticket Type (Current: " + selectedTicketType + "): ");
//            String newTicketType = scanner.nextLine().trim();
//            if (newTicketType.isEmpty()) {
//                System.out.println(ANSI_RED + "Ticket type cannot be empty. Please try again." + ANSI_RESET);
//                return;
//            }
//
//            System.out.print("Enter new Ticket Price: RM");
//            double newTicketPrice = getValidDouble(scanner);
//
//            // Update all tickets with the selected type
//            currentTicket = ticketList.head;
//            while (currentTicket != null) {
//                Ticket ticket = currentTicket.data;
//                if (ticket.getEventID().equals(eventID) && ticket.getTicketType().equals(selectedTicketType)) {
//                    Ticket updatedTicket = new Ticket(ticket.getEventID(), ticket.getTicketID(), newTicketType, newTicketPrice, ticket.getTicketStatus());
//                    ticketList.replace(ticket, updatedTicket);
//                }
//                currentTicket = currentTicket.next;
//            }
//
//            System.out.println(ANSI_GREEN + "All tickets of type '" + selectedTicketType + "' have been updated to new type '" + newTicketType + "' and price RM" + String.format("%.2f", newTicketPrice) + ANSI_RESET);
//            break;
//
//        case "2":
//            // List all tickets for the event and allow individual status update
//            currentTicket = ticketList.head;
//            LinkedList<Ticket> eventTickets = new LinkedList<>();
//            ticketIndex = 1;
//
//            // Print table header
//            System.out.printf("%-5s | %-10s | %-20s | %-10s | %-10s\n", "No.", "Ticket ID", "Ticket Type", "Price (RM)", "Status");
//            System.out.println("-------------------------------------------------------------------");
//
//            while (currentTicket != null) {
//                if (currentTicket.data.getEventID().equals(eventID)) {
//                    eventTickets.insert(currentTicket.data);
//                    // Print each ticket in table row format
//                    System.out.printf("%-5d | %-10s | %-20s | %-10.2f | %-10s\n",
//                                      ticketIndex,
//                                      currentTicket.data.getTicketID(),
//                                      currentTicket.data.getTicketType(),
//                                      currentTicket.data.getTicketPrice(),
//                                      currentTicket.data.getTicketStatus());
//                    ticketIndex++;
//                }
//                currentTicket = currentTicket.next;
//            }
//
//            // Prompt the user to select which ticket to update the status
//            System.out.print("Select the ticket you want to update (1-" + eventTickets.length() + "): ");
//            int selectedStatusIndex = getValidInteger(scanner);
//
//            if (selectedStatusIndex < 1 || selectedStatusIndex > eventTickets.length()) {
//                System.out.println(ANSI_RED + "Invalid selection. Please select between (1-" + eventTickets.length() + ")" + ANSI_RESET);
//                return;
//            }
//
//            // Get the selected ticket
//            Node<Ticket> selectedTicketNode = eventTickets.head;
//            for (int i = 1; i < selectedStatusIndex; i++) {
//                selectedTicketNode = selectedTicketNode.next;
//            }
//            Ticket selectedTicket = selectedTicketNode.data;
//
//            // Update Ticket Status
//            System.out.print("Enter new Ticket Status (Current: " + selectedTicket.getTicketStatus() + "): ");
//            String newTicketStatus = scanner.nextLine().trim();
//            if (newTicketStatus.isEmpty()) {
//                System.out.println(ANSI_RED + "Ticket status cannot be empty. Please try again." + ANSI_RESET);
//                return;
//            }
//
//            Ticket updatedTicket = new Ticket(selectedTicket.getEventID(), selectedTicket.getTicketID(), selectedTicket.getTicketType(), selectedTicket.getTicketPrice(), newTicketStatus);
//            ticketList.replace(selectedTicket, updatedTicket);
//
//            System.out.println(ANSI_GREEN + "Ticket status updated successfully!" + ANSI_RESET);
//            break;
//
//        default:
//            System.out.println(ANSI_RED + "Invalid choice. No changes made." + ANSI_RESET);
//            return;
//    }
//
//    
//    ticketList.saveToFile(TICKET_FILE);
//}
//
//
//private static void updateSponsorships(String eventID, LinkedList<Sponsorship> sponsorshipList, Scanner scanner) {
//    // Find and list all sponsorships for the event
//    Node<Sponsorship> currentSponsorship = sponsorshipList.head;
//    int sponsorshipIndex = 1;
//    LinkedList<Sponsorship> eventSponsorships = new LinkedList<>();
//
//    System.out.printf("%-5s | %-12s | %-30s | %-10s\n", "No.", "Sponsor ID", "Sponsor Name", "Amount (RM)");
//    System.out.println("---------------------------------------------------------------");
//
//    while (currentSponsorship != null) {
//        if (currentSponsorship.data.getEventID().equals(eventID)) {
//            System.out.printf("%-5d | %-12s | %-30s | %-10.2f\n",
//                    sponsorshipIndex,
//                    currentSponsorship.data.getSponsorID(),
//                    currentSponsorship.data.getSponsorName(),
//                    currentSponsorship.data.getSponsorAmount());
//            eventSponsorships.insert(currentSponsorship.data);
//            sponsorshipIndex++;
//        }
//        currentSponsorship = currentSponsorship.next;
//    }
//
//    if (eventSponsorships.isEmpty()) {
//        System.out.println(ANSI_YELLOW + "No sponsorships found for this event." + ANSI_RESET);
//        return;
//    }
//
//    System.out.print("Select the sponsorship you want to update: ");
//    int selectedSponsorshipIndex = getValidInteger(scanner);
//
//    if (selectedSponsorshipIndex < 1 || selectedSponsorshipIndex > eventSponsorships.length()) {
//        System.out.println(ANSI_RED + "Invalid selection. Please select a valid sponsorship." + ANSI_RESET);
//        return;
//    }
//
//    Node<Sponsorship> selectedSponsorshipNode = eventSponsorships.head;
//    for (int i = 1; i < selectedSponsorshipIndex; i++) {
//        selectedSponsorshipNode = selectedSponsorshipNode.next;
//    }
//
//    Sponsorship selectedSponsorship = selectedSponsorshipNode.data;
//
//    System.out.println("What would you like to update?");
//    System.out.println("1. Sponsor Name");
//    System.out.println("2. Sponsor Amount");
//    System.out.println("3. Both Name and Amount");
//    System.out.print("Enter your choice: ");
//
//    String updateChoice = scanner.nextLine().trim();
//
//    Sponsorship updatedSponsorship = null;
//    String successMessage = "";
//
//    switch (updateChoice) {
//        case "1":
//            // Update Sponsor Name
//            System.out.print("Enter new Sponsor Name : ");
//            String newSponsorName = scanner.nextLine().trim();
//            if (!newSponsorName.isEmpty()) {
//                updatedSponsorship = new Sponsorship(selectedSponsorship.getEventID(),
//                                                     selectedSponsorship.getSponsorID(),
//                                                     newSponsorName,
//                                                     selectedSponsorship.getSponsorAmount());
//                successMessage = "Sponsor Name updated successfully!";
//            }
//            break;
//        case "2":
//            // Update Sponsor Amount
//            System.out.print("Enter new Sponsor Amount : RM");
//            double newSponsorAmount = getValidDouble(scanner);
//            updatedSponsorship = new Sponsorship(selectedSponsorship.getEventID(),
//                                                 selectedSponsorship.getSponsorID(),
//                                                 selectedSponsorship.getSponsorName(),
//                                                 newSponsorAmount);
//            successMessage = "Sponsor Amount updated successfully!";
//            break;
//        case "3":
//            // Update Both Name and Amount
//            System.out.print("Enter new Sponsor Name : ");
//            newSponsorName = scanner.nextLine().trim();
//            System.out.print("Enter new Sponsor Amount : RM");
//            newSponsorAmount = getValidDouble(scanner);
//
//            if (!newSponsorName.isEmpty()) {
//                updatedSponsorship = new Sponsorship(selectedSponsorship.getEventID(),
//                                                     selectedSponsorship.getSponsorID(),
//                                                     newSponsorName,
//                                                     newSponsorAmount);
//                successMessage = "Sponsor Name and Amount updated successfully!";
//            }
//            break;
//        default:
//            System.out.println(ANSI_RED + "Invalid choice. No changes made." + ANSI_RESET);
//            return;
//    }
//
//    if (updatedSponsorship != null && sponsorshipList.contains(selectedSponsorship)) {
//        sponsorshipList.replace(selectedSponsorship, updatedSponsorship);
//        sponsorshipList.saveToFile(SPONSORSHIP_FILE);
//        System.out.println(ANSI_GREEN + successMessage + ANSI_RESET);
//    } else {
//        System.out.println(ANSI_RED + "Error updating sponsorship. Please try again." + ANSI_RESET);
//    }
//}
    
    


    
    
    
    

    
    
    
}
