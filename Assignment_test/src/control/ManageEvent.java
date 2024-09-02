package control;

import utility.EventUtility;
import entity.Event.Event;
import entity.Event.Sponsorship;
import entity.Event.Ticket;
import boundary.ManageEventUI;
import adt.LinkedList;
import adt.LinkedListInterface;
import adt.Node;
import entity.Volunteer.EventVolunteer;
import entity.Volunteer.Volunteer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Clarist Liew
 */
public class ManageEvent {

    // Common Use File
    private static final String EVENT_FILE = "event.txt";
    private static final String TICKET_FILE = "ticket.txt";
    private static final String SPONSORSHIP_FILE = "sponsorship.txt";
    private static final String VOLUNTEER_FILE = "volunteers.txt";
    private static final String VOLUNTEER_EVENT_FILE = "volunteer_event.txt";

    //Common use Linked List
    private static final LinkedListInterface<Event> eventList = new LinkedList<>();
    private static final LinkedListInterface<Sponsorship> sponsorshipList = new LinkedList<>();
    private static final LinkedListInterface<Ticket> ticketList = new LinkedList<>();
    private static final LinkedListInterface<EventVolunteer> volunteerEventList = new LinkedList<>();
    private static final LinkedListInterface<Volunteer> volunteerList = new LinkedList<>();

    public static Scanner scan = new Scanner(System.in);

    public static int menuIntReturn(String[] selectionList) {

        ManageEventUI.displayMenu(selectionList);

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

    public static boolean chkEmptyInput(String input) {
        if (input.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean chkInt(String input) {
        try {
            int number = Integer.parseInt(input);

            return true;

        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean intSelectionValidation(int input, int initial, int length) {

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
                    EventUtility.intNotInRange(initial, end);
                }

            } else {
                EventUtility.invalidIntInput();
            }

        } else {
            EventUtility.emptyInputErrorMsg();
        }

        if (!validInput) {
            ManageEventUI.reEnter();
        }

        return validInput;
    }

    public static boolean chkYN(String input) {
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

        ManageEventUI.inputYN(sentence);
        while (!validInput) {

            input = (scan.nextLine()).toUpperCase().trim();

            validInput = chkEmptyInput(input);

            if (validInput) {
                String[] inputList = {"Y", "N"};
                validInput = chkSpecificWord(inputList, input);
                if (!validInput) {
                    EventUtility.enterYNOnly();
                }
            } else {
                EventUtility.emptyInputErrorMsg();
            }

            if (!validInput) {
                ManageEventUI.reEnter();
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
    public static boolean chkSpecificWord(String[] inputList, String input) {
        for (int i = 0; i < inputList.length; i++) {
            if (input.trim().toUpperCase().equalsIgnoreCase(inputList[i].trim().toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    private static String generateEventID(String prefix) {

        int maxId = 0;

        Node<Event> current = eventList.getHead();

        while (current != null) {
            String currentId = current.data.getEventID().substring(2, 7);
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
            ManageEventUI.inputDate();
            String dateString = scan.nextLine().trim();

            // Check if the format is correct
            if (!dateString.matches("\\d{2}/\\d{2}/\\d{4}")) {
                EventUtility.invalidDateMsg();
                continue;
            }

            String[] parts = dateString.split("/");
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);

            // Check if the month is valid (1-12)
            if (month < 1 || month > 12) {
                EventUtility.invalidMonth();
                continue;
            }

            // Check if the day is valid for the given month and year
            if (!isValidDay(day, month, year)) {
                EventUtility.invalidDay();
                continue;
            }

            try {
                date = dateFormat.parse(dateString);

                // Additional validation to ensure the date was parsed correctly
                if (!dateString.equals(dateFormat.format(date))) {
                    EventUtility.invalidDateMsg();
                    date = null;
                    continue;
                }

                break;

            } catch (ParseException e) {
                EventUtility.invalidDateMsg();
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
            ManageEventUI.inputTime();
            time = scanner.nextLine().trim();

            try {
                timeFormat.parse(time);
                break;
            } catch (ParseException e) {
                EventUtility.invalidTime();
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
                    EventUtility.invalidNegativeValue();
                }
            } catch (NumberFormatException e) {
                EventUtility.invalidIntInput();
            }
        }
    }

    public static int getValidRange(int max) {
        int value;
        while (true) {
            ManageEventUI.inputChoice();
            try {
                value = Integer.parseInt(scan.nextLine().trim());
                if (value > 0 && value <= max) {
                    return value;
                } else {
                    EventUtility.invalidSelection(max);
                }
            } catch (NumberFormatException e) {
                EventUtility.invalidIntInput();
            }
        }
    }

    private static double getValidDouble(Scanner scanner) {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                EventUtility.invalidIntInput();
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

    public static void clearAllFile() {
        ticketList.clear();
        eventList.clear();
        sponsorshipList.clear();
        volunteerEventList.clear();
        volunteerList.clear();

    }

    public static void loadAllFile() {
        eventList.loadFromFile(EVENT_FILE);
        ticketList.loadFromFile(TICKET_FILE);
        sponsorshipList.loadFromFile(SPONSORSHIP_FILE);
        volunteerEventList.loadFromFile(VOLUNTEER_EVENT_FILE);
        volunteerList.loadFromFile(VOLUNTEER_FILE);
    }

    public static void main(String[] args) {
        eventMainMenu();
    }

    public static void eventMainMenu() {
        boolean cont = true;
        do {
            clearAllFile();
            loadAllFile();
            int selection = ManageEventUI.eventMainMenu();
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
                    EventUtility.exitMainMenu();
                    break;

            }

            cont = YN("Do you want to continue manage event?");
            if (cont) {
                System.out.println();
            }
        } while (cont);

    }

    private static void addEvent() {

        String eventID = ManageEvent.generateEventID("EV");

        ManageEventUI.inputEventName();
        String eventName = null;
        boolean validName = false;
        while (!validName) {
            eventName = scan.nextLine().trim();
            validName = ManageEvent.chkEmptyInput(eventName);
            if (!validName) {
                EventUtility.emptyInputErrorMsg();
                ManageEventUI.inputEventName();
            }
        }

        Date date = getValidDate(scan);
        String time = getValidTime(scan);

        ManageEventUI.inputLocation();
        String location = null;
        boolean validLocation = false;
        while (!validLocation) {
            location = scan.nextLine().trim();
            validLocation = ManageEvent.chkEmptyInput(location);
            if (!validLocation) {
                EventUtility.emptyInputErrorMsg();
                ManageEventUI.inputLocation();
            }
        }

        Event event = new Event(eventID, eventName, date, time, location);
        eventList.insert(event);
        ManageEventUI.displayEventDetails(event);
        eventList.saveToFile(EVENT_FILE);
        EventUtility.eventAddedMsg();

    }

    private static void addTicket() {

        if (eventList.isEmpty()) {
            EventUtility.eventNotExist();
            return;
        }
        String eventID;
        Event event;

        while (true) {
            ManageEventUI.inputTicketEventID();
            eventID = scan.nextLine().trim();

            event = findEventByID(eventID);
            if (event != null) {
                break;
            } else {
                EventUtility.invalidEventID();
            }
        }

        ManageEventUI.inputNumTicketType();
        int numberOfTicketTypes = getValidInteger(scan);

        for (int i = 1; i <= numberOfTicketTypes; i++) {
            System.out.println("\nTicket Type " + i + ":");

            ManageEventUI.inputTicketType();
            String ticketType = scan.nextLine().trim();
            while (ticketType.isEmpty()) {
                EventUtility.emptyTicketType();
                ticketType = scan.nextLine().trim();
            }

            ManageEventUI.inputTicketPrice();
            double ticketPrice = getValidDouble(scan);

            ManageEventUI.inputTicketAmt();
            int ticketAmount = getValidInteger(scan);
            ManageEventUI.displayTicketTable();
            for (int j = 0; j < ticketAmount; j++) {
                String ticketID = ManageEvent.generateTicketID("TK");
                Ticket ticket = new Ticket(eventID, ticketID, ticketType, ticketPrice, "Available");
                ticketList.insert(ticket);
                ManageEventUI.displayTicketDetails(ticket);
            }

            ticketList.saveToFile(TICKET_FILE);
            EventUtility.ticketAddedMsg();
        }
    }

    private static void addSponsorship() {

        if (eventList.isEmpty()) {
            EventUtility.eventNotExist();
            return;
        }

        String eventID;
        Event event;

        while (true) {
            ManageEventUI.inputSponsEventID();
            eventID = scan.nextLine().trim();

            event = findEventByID(eventID);
            if (event != null) {
                break;
            } else {
                EventUtility.invalidEventID();
            }
        }

        String sponsorID = generateSponsorshipID("SP");

        ManageEventUI.inputSponsName();
        String sponsorName = scan.nextLine().trim();
        while (sponsorName.isEmpty()) {
            EventUtility.emptySponsName();
            sponsorName = scan.nextLine().trim();
        }

        ManageEventUI.inputSponsAmt();
        double sponsorAmount = getValidDouble(scan);

        Sponsorship sponsorship = new Sponsorship(eventID, sponsorID, sponsorName, sponsorAmount);
        sponsorshipList.insert(sponsorship);
        ManageEventUI.displaySponsorshipDetails(sponsorship);
        sponsorshipList.saveToFile(SPONSORSHIP_FILE);
        EventUtility.SponsAddedMsg();
    }

    public static void removeEvent() {
        
        if (eventList.isEmpty()) {
            EventUtility.eventNotExistToRemove();
            return;
        }
        ManageEventUI.displayEvent();
        ManageEventUI.displayEventListHeader();
        Node<Event> currentEvent = eventList.getHead();
        while (currentEvent != null) {
        Event event = currentEvent.data;
        ManageEventUI.displayAvailableEvent(event);
        currentEvent = currentEvent.next;
        }
        
        
        ManageEventUI.inputRemoveEventID();
        String eventID = scan.nextLine().trim();

        // Remove the event with the specified eventID
        eventList.removeIf(event -> event.getEventID().equals(eventID));

        // Remove associated tickets and sponsorships
        ticketList.removeIf(ticket -> ticket.getEventID().equals(eventID));
        sponsorshipList.removeIf(sponsorship -> sponsorship.getEventID().equals(eventID));

        ticketList.saveToFile(TICKET_FILE);
        sponsorshipList.saveToFile(SPONSORSHIP_FILE);
        eventList.saveToFile(EVENT_FILE);
        EventUtility.eventRemovedMsg();
    }

    public static void searchEvent() {

        if (eventList.isEmpty()) {
            EventUtility.eventNotExist();
            return;
        }

        int searchChoice = ManageEventUI.searchEventMenu();

        Event foundEvent = null;
        LinkedListInterface<Event> foundEvents = new LinkedList<>();

        switch (searchChoice) {
            case 1:
                ManageEventUI.inputEventID();
                String eventID = scan.nextLine().trim();
                foundEvent = findEventByID(eventID);
                break;
            case 2:
                Date searchDate = getValidDate(scan);
                foundEvents = searchEventsByDate(searchDate);
                break;
            default:
                EventUtility.invalidMenuChoice();
                return;
        }

        if (searchChoice == 1 && foundEvent != null) {
            disEventDetails(foundEvent);
        } else if (searchChoice == 2 && !foundEvents.isEmpty()) {
            disEventsByDate(foundEvents);
        } else {
            EventUtility.searchEventNotExist();
        }
    }

    // Sequential Search
    private static LinkedListInterface<Event> searchEventsByDate(Date searchDate) {
        LinkedListInterface<Event> foundEvents = new LinkedList<>();
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
        ManageEventUI.eventFound();
        ManageEventUI.displayEventDetails(event);
        disEventTickets(event.getEventID());
        disEventSponsorships(event.getEventID());
    }

    public static void disEventsByDate(LinkedListInterface<Event> foundEvents) {
        Node<Event> currentEvent = foundEvents.getHead();
        int show = 1;
        while (currentEvent != null) {
            ManageEventUI.eventFound();
            ManageEventUI.disEventByDate(show, currentEvent.data);
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
        ManageEventUI.displayTicketTableHeader();
        while (ticketNode != null) {
            if (ticketNode.data.getEventID().equals(eventID)) {
                ManageEventUI.disTicketDetails(ticketCount, ticketNode.data);
                ticketsFound = true;
                ticketCount++;

            }
            ticketNode = ticketNode.next;

        }

        if (!ticketsFound) {
            EventUtility.noTicketsFound();
        }
    }

    public static void disEventSponsorships(String eventID) {
        Node<Sponsorship> sponsorshipNode = sponsorshipList.getHead();
        int sponsorshipCount = 1;
        boolean sponsorshipsFound = false;
        ManageEventUI.displaySponsorshipTableHeader();
        while (sponsorshipNode != null) {
            if (sponsorshipNode.data.getEventID().equals(eventID)) {
                ManageEventUI.disSponsorshipDetails(sponsorshipCount, sponsorshipNode.data);
                sponsorshipsFound = true;
                sponsorshipCount++;
            }
            sponsorshipNode = sponsorshipNode.next;
        }

        if (!sponsorshipsFound) {
            EventUtility.noSponsorshipsFound();
        }
    }

    public static void updateEvent() {

        if (eventList.isEmpty()) {
            EventUtility.eventNotExistToUpdate();
            return;
        }

        ManageEventUI.inputUpdateEventID();
        String eventID = scan.nextLine().trim();

        Node<Event> current = eventList.getHead();
        while (current != null) {
            if (current.data.getEventID().equals(eventID)) {
                Event event = current.data;
                Event updatedEvent = null;
                boolean continueUpdating = true;

                while (continueUpdating) {
                    int updateChoice = ManageEventUI.displayUpdateMenu();

                    switch (updateChoice) {
                        case 1:
                            ManageEventUI.inputEventName();
                            String newName = scan.nextLine().trim();
                            while (newName.isEmpty()) {
                                EventUtility.emptyInputErrorMsg();
                                newName = scan.nextLine().trim();
                            }
                            updatedEvent = new Event(event.getEventID(), newName, event.getDate(), event.getTime(), event.getLocation());
                            ManageEventUI.displayEventDetails(updatedEvent);
                            EventUtility.eventNameUpdatedMsg();
                            break;
                        case 2:
                            Date newDate = getValidDate(scan);
                            updatedEvent = new Event(event.getEventID(), event.getEventName(), newDate, event.getTime(), event.getLocation());
                            ManageEventUI.displayEventDetails(updatedEvent);
                            EventUtility.eventDateUpdatedMsg();
                            break;
                        case 3:
                            String newTime = getValidTime(scan);
                            updatedEvent = new Event(event.getEventID(), event.getEventName(), event.getDate(), newTime, event.getLocation());
                            ManageEventUI.displayEventDetails(updatedEvent);
                            EventUtility.eventTimeUpdatedMsg();
                            break;
                        case 4:
                            ManageEventUI.inputLocation();
                            String newLocation = scan.nextLine().trim();
                            while (newLocation.isEmpty()) {
                                EventUtility.emptyInputErrorMsg();
                                newLocation = scan.nextLine().trim();
                            }
                            updatedEvent = new Event(event.getEventID(), event.getEventName(), event.getDate(), event.getTime(), newLocation);
                            ManageEventUI.displayEventDetails(updatedEvent);
                            EventUtility.eventLocationUpdatedMsg();
                            break;
                        case 5:
                            updateTickets(eventID, ticketList, scan);
                            break;
                        case 6:
                            updateSponsorships(eventID, sponsorshipList, scan);
                            break;
                        case 7:
                            continueUpdating = false;
                            break;
                        default:
                            EventUtility.invalidMenuChoice();
                    }

                    if (updatedEvent != null) {
                        eventList.replace(event, updatedEvent);
                        event = updatedEvent;
                        updatedEvent = null;
                    }
                }

                eventList.saveToFile(EVENT_FILE);

                EventUtility.eventUpdatedMsg();
                return;
            }
            current = current.next;
        }

        EventUtility.invalidEventID();
    }

    private static void updateTickets(String eventID, LinkedListInterface<Ticket> ticketList, Scanner scanner) {
        int updateChoice = ManageEventUI.displayTicketUpdateMenu();

        switch (updateChoice) {
            case 1:
                //  Display all ticket types and prices for selection
                LinkedListInterface<String> ticketTypes = new LinkedList<>();
                Node<Ticket> currentTicket = ticketList.getHead();
                int ticketIndex = 1;

                while (currentTicket != null) {
                    if (currentTicket.data.getEventID().equals(eventID) && !ticketTypes.contains(currentTicket.data.getTicketType())) {
                        ticketTypes.insert(currentTicket.data.getTicketType());
                        ManageEventUI.displayTicketDetails(ticketIndex, currentTicket.data);
                        ticketIndex++;
                    }
                    currentTicket = currentTicket.next;
                }

                if (ticketTypes.isEmpty()) {
                    EventUtility.noTicketsFound();
                    return;
                }

                // select which ticket type to update
                int selectedTicketIndex = ManageEvent.getValidRange(ticketTypes.length());
                if (selectedTicketIndex < 1 || selectedTicketIndex > ticketTypes.length()) {
                    EventUtility.invalidSelection(ticketTypes.length());
                    return;
                }

                // Get the selected ticket type
                Node<String> selectedTicketTypeNode = ticketTypes.getHead();
                for (int i = 1; i < selectedTicketIndex; i++) {
                    selectedTicketTypeNode = selectedTicketTypeNode.next;
                }
                String selectedTicketType = selectedTicketTypeNode.data;

                // Get new ticket type and price 
                ManageEventUI.inputNewTicketType(selectedTicketType);
                String newTicketType = scanner.nextLine().trim();
                if (newTicketType.isEmpty()) {
                    EventUtility.emptyInputErrorMsg();
                    return;
                }

                ManageEventUI.inputNewTicketPrice();
                double newTicketPrice = getValidDouble(scanner);

                //  Update all tickets with the selected type
                LinkedListInterface<Ticket> updatedTickets = new LinkedList<>();
                currentTicket = ticketList.getHead();
                while (currentTicket != null) {
                    Ticket ticket = currentTicket.data;
                    if (ticket.getEventID().equals(eventID) && ticket.getTicketType().equals(selectedTicketType)) {
                        Ticket updatedTicket = new Ticket(ticket.getEventID(), ticket.getTicketID(), newTicketType, newTicketPrice, ticket.getTicketStatus());
                        ticketList.replace(ticket, updatedTicket);
                        updatedTickets.insert(updatedTicket);
                    }
                    currentTicket = currentTicket.next;
                }

                ticketList.saveToFile(TICKET_FILE);

                // Display updated ticket details
                Node<Ticket> updatedTicketNode = updatedTickets.getHead();
                while (updatedTicketNode != null) {
                    ManageEventUI.displayTicketUpdatedDetails(updatedTicketNode.data);
                    updatedTicketNode = updatedTicketNode.next;
                    
                }
                EventUtility.ticketTypeandPriceUpdatedMsg();
                break;

            case 2:
                //  Display all tickets for the event and their current status
                currentTicket = ticketList.getHead();
                LinkedListInterface<Ticket> eventTickets = new LinkedList<>();
                ticketIndex = 1;

                
                while (currentTicket != null) {
                    if (currentTicket.data.getEventID().equals(eventID)) {
                        eventTickets.insert(currentTicket.data);
                        ManageEventUI.displayTicketStatusDetails(ticketIndex, currentTicket.data);
                        ticketIndex++;
                    }
                    currentTicket = currentTicket.next;
                }

                if (eventTickets.isEmpty()) {
                    EventUtility.noTicketsFound();
                    return;
                }

                //  Prompt to select which ticket to update the status
                int selectedStatusIndex = ManageEvent.getValidRange(eventTickets.length());
                if (selectedStatusIndex < 1 || selectedStatusIndex > eventTickets.length()) {
                    EventUtility.invalidSelection(eventTickets.length());
                    return;
                }

                // Get the selected ticket
                Node<Ticket> selectedTicketNode = eventTickets.getHead();
                for (int i = 1; i < selectedStatusIndex; i++) {
                    selectedTicketNode = selectedTicketNode.next;
                }
                Ticket selectedTicket = selectedTicketNode.data;

                // Get new status
                ManageEventUI.inputNewTicketStatus();
                String newTicketStatus = scanner.nextLine().trim();
                if (newTicketStatus.isEmpty()) {
                    EventUtility.emptyInputErrorMsg();
                    return;
                }

                Ticket updatedTicket = new Ticket(selectedTicket.getEventID(), selectedTicket.getTicketID(), selectedTicket.getTicketType(), selectedTicket.getTicketPrice(), newTicketStatus);
                ticketList.replace(selectedTicket, updatedTicket);

                ticketList.saveToFile(TICKET_FILE);
                ManageEventUI.displayTicketUpdatedDetails(updatedTicket);
                EventUtility.ticketStatusUpdatedMsg();
                break;

            default:
                EventUtility.invalidMenuChoice();
        }
    }

    private static void updateSponsorships(String eventID, LinkedListInterface<Sponsorship> sponsorshipList, Scanner scanner) {
        // list all sponsorships for the event
        Node<Sponsorship> currentSponsorship = sponsorshipList.getHead();
        int sponsorshipIndex = 1;
        LinkedListInterface<Sponsorship> eventSponsorships = new LinkedList<>();

        ManageEventUI.displaySponsorshipTableHeader();

        while (currentSponsorship != null) {
            if (currentSponsorship.data.getEventID().equals(eventID)) {
                ManageEventUI.displaySponsorshipDetails(sponsorshipIndex, currentSponsorship.data);
                eventSponsorships.insert(currentSponsorship.data);
                sponsorshipIndex++;
            }
            currentSponsorship = currentSponsorship.next;
        }

        if (eventSponsorships.isEmpty()) {
            EventUtility.noSponsorshipsFound();
            return;
        }

        //  select which sponsorship to update
        int selectedSponsorshipIndex = ManageEvent.getValidRange(eventSponsorships.length());
        if (selectedSponsorshipIndex < 1 || selectedSponsorshipIndex > eventSponsorships.length()) {
            EventUtility.invalidSelection(eventSponsorships.length());
            return;
        }

        Node<Sponsorship> selectedSponsorshipNode = eventSponsorships.getHead();
        for (int i = 1; i < selectedSponsorshipIndex; i++) {
            selectedSponsorshipNode = selectedSponsorshipNode.next;
        }

        Sponsorship selectedSponsorship = selectedSponsorshipNode.data;

        // Prompt to choose what to update
        int updateChoice = ManageEventUI.displaySponsorshipUpdateMenu();

        Sponsorship updatedSponsorship = null;

        switch (updateChoice) {
            case 1:
                // Update Sponsor Name
                ManageEventUI.inputNewSponsorName();
                String newSponsorName = scanner.nextLine().trim();
                if (!newSponsorName.isEmpty()) {
                    updatedSponsorship = new Sponsorship(selectedSponsorship.getEventID(),
                            selectedSponsorship.getSponsorID(),
                            newSponsorName,
                            selectedSponsorship.getSponsorAmount());

                }
                break;
            case 2:
                // Update Sponsor Amount
                ManageEventUI.inputNewSponsorAmount();
                double newSponsorAmount = ManageEvent.getValidDouble(scanner);
                updatedSponsorship = new Sponsorship(selectedSponsorship.getEventID(),
                        selectedSponsorship.getSponsorID(),
                        selectedSponsorship.getSponsorName(),
                        newSponsorAmount);

                break;
            case 3:
                // Update Both Name and Amount

                ManageEventUI.inputNewSponsorName();
                newSponsorName = scanner.nextLine().trim();
                ManageEventUI.inputNewSponsorAmount();
                newSponsorAmount = ManageEvent.getValidDouble(scanner);

                if (!newSponsorName.isEmpty()) {
                    updatedSponsorship = new Sponsorship(selectedSponsorship.getEventID(),
                            selectedSponsorship.getSponsorID(),
                            newSponsorName,
                            newSponsorAmount);

                }
                break;
            default:
                EventUtility.invalidMenuChoice();
                return;
        }

        //  Update the sponsorship and save changes
        if (updatedSponsorship != null && sponsorshipList.contains(selectedSponsorship)) {
            sponsorshipList.replace(selectedSponsorship, updatedSponsorship);
            sponsorshipList.saveToFile(SPONSORSHIP_FILE);
            ManageEventUI.displaySponsorshipUpdatedDetails(updatedSponsorship);
            EventUtility.sponsorshipUpdatedMsg();
        } else {
            EventUtility.updateSponsorError();
        }

    }

    private static void listAllEvents() {

        // Check if there are any events
        if (eventList.isEmpty()) {
            EventUtility.eventNotExist();
            return;
        }

        // Header for the event list
        ManageEventUI.titleListAllEvent();
        Node<Event> eventNode = eventList.getHead();
        while (eventNode != null) {
            Event event = eventNode.data;
            ManageEventUI.displayEventDetailsInList(event);

            // Filter and list tickets associated with the current event
            ManageEventUI.displayEventTicketsHeader(event.getEventID());

            Node<Ticket> ticketNode = ticketList.getHead();
            boolean ticketsFound = false;
            int ticketCount = 1;
            while (ticketNode != null) {
                Ticket ticket = ticketNode.data;
                if (ticket.getEventID().equals(event.getEventID())) {
                    ManageEventUI.displayTicketDetailsInList(ticketCount, ticket);
                    ticketsFound = true;
                    ticketCount++;
                }
                ticketNode = ticketNode.next;
            }
            if (!ticketsFound) {
                EventUtility.noTicketsFound();
            }

            // Filter and list sponsorships associated with the current event
            ManageEventUI.displayEventSponsorshipsHeader(event.getEventID());

            Node<Sponsorship> sponsorshipNode = sponsorshipList.getHead();
            boolean sponsorshipsFound = false;
            int sponsorshipCount = 1;
            while (sponsorshipNode != null) {
                Sponsorship sponsorship = sponsorshipNode.data;
                if (sponsorship.getEventID().equals(event.getEventID())) {
                    ManageEventUI.displaySponsorshipDetailsInList(sponsorshipCount, sponsorship);
                    sponsorshipsFound = true;
                    sponsorshipCount++;
                }
                sponsorshipNode = sponsorshipNode.next;
            }
            if (!sponsorshipsFound) {
                EventUtility.noSponsorshipsFound();
            }

            // Move to the next event in the list
            eventNode = eventNode.next;
        }
    }

    private static void removeEventFromAVolunteer() {

        // Check if there are no volunteers
        if (volunteerList.isEmpty()) {
            EventUtility.noVolunteersFound();
            return;
        }

        // Get the Volunteer ID from user input
        String volunteerID = ManageEventUI.inputVolunteerID();

        // Find the volunteer by ID
        Node<Volunteer> volunteerNode = volunteerList.getHead();
        Volunteer foundVolunteer = null;

        while (volunteerNode != null) {
            if (volunteerNode.data.getVolunteerID().equals(volunteerID)) {
                foundVolunteer = volunteerNode.data;
                break;
            }
            volunteerNode = volunteerNode.next;
        }

        // If the volunteer is not found
        if (foundVolunteer == null) {
            EventUtility.noVolunteerFound(volunteerID);
            return;
        }

        // List all events associated with the volunteer
        LinkedListInterface<Event> assignedEvents = new LinkedList<>();
        Node<EventVolunteer> volunteerEventNode = volunteerEventList.getHead();

        while (volunteerEventNode != null) {
            EventVolunteer eventVolunteer = volunteerEventNode.data;
            if (eventVolunteer.getVolunteerID().equals(volunteerID)) {
                Node<Event> eventNode = eventList.getHead();
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

        // If no events are associated with the volunteer
        if (assignedEvents.isEmpty()) {
            EventUtility.noEventsAssociatedWithVolunteer(volunteerID);
            return;
        }

        // Display the volunteer details and their associated events
        ManageEventUI.displayVolunteerDetails(foundVolunteer);
        ManageEventUI.displayVolunteerEvents(assignedEvents);

        // Get the Event ID to remove from user input
        String eventIDToRemove = ManageEventUI.inputEventIDToRemove();

        // Remove the association from the volunteer-event list
        volunteerEventList.removeIf(eventVolunteer -> eventVolunteer.getVolunteerID().equals(volunteerID) && eventVolunteer.getEventID().equals(eventIDToRemove));

        // Save the updated list to the file
        volunteerEventList.saveToFile(VOLUNTEER_EVENT_FILE);

        // Confirm the removal
        EventUtility.eventRemovedFromVolunteer(eventIDToRemove, volunteerID);
    }

    private static void listAllEventsForAVolunteer() {

        // Check if there are no volunteers
        if (volunteerList.isEmpty()) {
            EventUtility.noVolunteersFound();
            return;
        }

        // Get the Volunteer ID from user input
        String volunteerID = ManageEventUI.inputVolunteerID();

        // Find the volunteer by ID
        Node<Volunteer> volunteerNode = volunteerList.getHead();
        Volunteer foundVolunteer = null;

        while (volunteerNode != null) {
            if (volunteerNode.data.getVolunteerID().equals(volunteerID)) {
                foundVolunteer = volunteerNode.data;
                break;
            }
            volunteerNode = volunteerNode.next;
        }

        // If the volunteer is not found
        if (foundVolunteer == null) {
            EventUtility.noVolunteerFound(volunteerID);
            return;
        }

        // List all events associated with the volunteer
        LinkedListInterface<Event> assignedEvents = new LinkedList<>();
        Node<EventVolunteer> volunteerEventNode = volunteerEventList.getHead();

        while (volunteerEventNode != null) {
            EventVolunteer eventVolunteer = volunteerEventNode.data;

            if (eventVolunteer.getVolunteerID().equals(volunteerID)) {
                Node<Event> eventNode = eventList.getHead();
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

        // If no events are associated with the volunteer
        if (assignedEvents.isEmpty()) {
            EventUtility.noEventsAssociatedWithVolunteer(volunteerID);
            return;
        }

        // Display the volunteer details and their associated events
        ManageEventUI.displayVolunteerDetails(foundVolunteer);
        ManageEventUI.displayVolunteerEvents(assignedEvents);
    }

    public static void generateSummaryReports() {
        boolean cont = true;
        do {

            int reportSelection = ManageEventUI.displayReportMenu();

            switch (reportSelection) {
                case 1:
                    annualFundraisingReport();
                    break;
                case 2:
                    topEventsWithMostVolunteersReport();
                    break;
                case 3:
                    topVolunteerContributionsReport();
                    break;
                case 4:
                    bestTicketSalesPerformanceReport();
                    break;
                default:
                    EventUtility.inputInvalidReportChoice();
                    break;
            }

            cont = YN("Do you want to view another report?");

        } while (cont);
    }

    public static void annualFundraisingReport() {

        if (eventList.isEmpty() || sponsorshipList.isEmpty()) {
            EventUtility.noEventoOrSponsorshipExist();
            return;
        }

        ManageEventUI.inputStartYear();
        int startYear = scan.nextInt();
        ManageEventUI.inputEndYear();
        int endYear = scan.nextInt();
        scan.nextLine();

        double[] fundraisingTotals = new double[endYear - startYear + 1];

        Node<Event> currentEvent = eventList.getHead();
        while (currentEvent != null) {
            int eventYear = currentEvent.data.getDate().getYear() + 1900;
            if (eventYear >= startYear && eventYear <= endYear) {
                double totalSponsorship = 0;
                Node<Sponsorship> currentSponsorship = sponsorshipList.getHead();
                while (currentSponsorship != null) {
                    if (currentSponsorship.data.getEventID().equals(currentEvent.data.getEventID())) {
                        totalSponsorship += currentSponsorship.data.getSponsorAmount();
                    }
                    currentSponsorship = currentSponsorship.next;
                }
                fundraisingTotals[eventYear - startYear] += totalSponsorship;
            }
            currentEvent = currentEvent.next;
        }

        ManageEventUI.displayAnnualFundraisingReport(fundraisingTotals, startYear);
    }

    public static void topEventsWithMostVolunteersReport() {

        if (eventList.isEmpty() || volunteerEventList.isEmpty()) {
            EventUtility.noEventOrVolunteer();
            return;
        }

        Event[] topEvents = new Event[5];
        int[] volunteerCounts = new int[5];

        Node<Event> currentEvent = eventList.getHead();
        while (currentEvent != null) {
            int volunteerCount = 0;
            Node<EventVolunteer> currentVolunteerEvent = volunteerEventList.getHead();
            while (currentVolunteerEvent != null) {
                if (currentVolunteerEvent.data.getEventID().equals(currentEvent.data.getEventID())) {
                    volunteerCount++;
                }
                currentVolunteerEvent = currentVolunteerEvent.next;
            }

            for (int i = 0; i < 5; i++) {
                if (volunteerCounts[i] < volunteerCount) {
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

        ManageEventUI.displayTopEventsWithMostVolunteers(topEvents, volunteerCounts);
    }

    public static void topVolunteerContributionsReport() {

        if (volunteerList.isEmpty() || volunteerEventList.isEmpty()) {
            EventUtility.noVolunteerOrEventExist();
            return;
        }

        Volunteer[] topVolunteers = new Volunteer[5];
        int[] contributionCounts = new int[5];

        Node<Volunteer> currentVolunteer = volunteerList.getHead();
        while (currentVolunteer != null) {
            int contributionCount = 0;
            Node<EventVolunteer> currentVolunteerEvent = volunteerEventList.getHead();
            while (currentVolunteerEvent != null) {
                if (currentVolunteerEvent.data.getVolunteerID().equals(currentVolunteer.data.getVolunteerID())) {
                    contributionCount++;
                }
                currentVolunteerEvent = currentVolunteerEvent.next;
            }

            for (int i = 0; i < 5; i++) {
                if (contributionCounts[i] < contributionCount) {
                    for (int j = 4; j > i; j--) {
                        topVolunteers[j] = topVolunteers[j - 1];
                        contributionCounts[j] = contributionCounts[j - 1];
                    }
                    topVolunteers[i] = currentVolunteer.data;
                    contributionCounts[i] = contributionCount;
                    break;
                }
            }

            currentVolunteer = currentVolunteer.next;
        }

        ManageEventUI.displayTopVolunteerContributions(topVolunteers, contributionCounts);
    }

    public static void bestTicketSalesPerformanceReport() {

        if (eventList.isEmpty() || ticketList.isEmpty()) {
            EventUtility.noEventOrTicketExist();
            return;
        }

        Event[] topEvents = new Event[5];
        int[] soldOutCounts = new int[5];

        Node<Event> currentEvent = eventList.getHead();
        while (currentEvent != null) {
            int soldOutCount = 0;
            Node<Ticket> currentTicket = ticketList.getHead();
            while (currentTicket != null) {
                if (currentTicket.data.getEventID().equals(currentEvent.data.getEventID())
                        && currentTicket.data.getTicketStatus().equalsIgnoreCase("Sold Out")) {
                    soldOutCount++;
                }
                currentTicket = currentTicket.next;
            }

            for (int i = 0; i < 5; i++) {
                if (soldOutCounts[i] < soldOutCount) {
                    for (int j = 4; j > i; j--) {
                        topEvents[j] = topEvents[j - 1];
                        soldOutCounts[j] = soldOutCounts[j - 1];
                    }
                    topEvents[i] = currentEvent.data;
                    soldOutCounts[i] = soldOutCount;
                    break;
                }
            }

            currentEvent = currentEvent.next;
        }

        ManageEventUI.displayBestTicketSalesPerformance(topEvents, soldOutCounts);
    }
}
