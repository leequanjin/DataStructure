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
                String eventID = EBoundary.inputEventID();
                foundEvent = findEventByID(eventID);
                break;
            case 2:
                Date searchDate = EBoundary.inputEventDate();
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



//private static void displayEventDetails(Event event, LinkedList<Ticket> ticketList, LinkedList<Sponsorship> sponsorshipList) {
//
//
//    System.out.println(ANSI_CYAN + "\nEvent Found: " + ANSI_RESET);
//    System.out.printf("%-15s : %s\n", "Event ID", event.getEventID());
//    System.out.printf("%-15s : %s\n", "Event Name", event.getEventName());
//    System.out.printf("%-15s : %s\n", "Date", dateFormat.format(event.getDate()));
//    System.out.printf("%-15s : %s\n", "Time", event.getTime());
//    System.out.printf("%-15s : %s\n", "Location", event.getLocation());
//
//    // List associated tickets in a table format
//    System.out.println(ANSI_BLUE + "\nTickets for Event ID " + event.getEventID() + ":" + ANSI_RESET);
//    System.out.printf("%-5s | %-10s | %-15s | %-10s\n", "No.", "Ticket ID", "Ticket Type", "Amount (RM)");
//    System.out.println("---------------------------------------------");
//    Node<Ticket> ticketNode = ticketList.head;
//    boolean ticketsFound = false;
//    int ticketCount = 1;
//    while (ticketNode != null) {
//        if (ticketNode.data.getEventID().equals(event.getEventID())) {
//            System.out.printf("%-5d | %-10s | %-15s | %-10.2f\n", 
//                              ticketCount, 
//                              ticketNode.data.getTicketID(), 
//                              ticketNode.data.getTicketType(), 
//                              ticketNode.data.getTicketPrice());
//            ticketsFound = true;
//            ticketCount++;
//        }
//        ticketNode = ticketNode.next;
//    }
//    if (!ticketsFound) {
//        System.out.println(ANSI_YELLOW + "No tickets found for this event." + ANSI_RESET);
//    }
//
//    // List associated sponsorships in a table format
//    System.out.println(ANSI_PURPLE + "\nSponsorships for Event ID " + event.getEventID() + ":" + ANSI_RESET);
//    System.out.printf("%-5s | %-10s | %-20s | %-15s\n", "No.", "Sponsor ID", "Sponsor Name", "Amount (RM)");
//    System.out.println("-----------------------------------------------------------");
//    Node<Sponsorship> sponsorshipNode = sponsorshipList.head;
//    boolean sponsorshipsFound = false;
//    int sponsorshipCount = 1;
//    while (sponsorshipNode != null) {
//        if (sponsorshipNode.data.getEventID().equals(event.getEventID())) {
//            System.out.printf("%-5d | %-10s | %-20s | %-15.2f\n", 
//                              sponsorshipCount, 
//                              sponsorshipNode.data.getSponsorID(), 
//                              sponsorshipNode.data.getSponsorName(), 
//                              sponsorshipNode.data.getSponsorAmount());
//            sponsorshipsFound = true;
//            sponsorshipCount++;
//        }
//        sponsorshipNode = sponsorshipNode.next;
//    }
//    if (!sponsorshipsFound) {
//        System.out.println(ANSI_YELLOW + "No sponsorships found for this event." + ANSI_RESET);
//    }
//}

    
    
    
    

    
    
    
}
