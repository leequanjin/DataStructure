/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import control.ManageEvent;
import entity.Event.Event;
import entity.Event.Sponsorship;
import entity.Event.Ticket;
import adt.LinkedListInterface;
import adt.Node;
import entity.Volunteer.Volunteer;
import java.text.SimpleDateFormat;

/**
 *
 * @author Clarist Liew
 */
public class ManageEventUI {

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

        return ManageEvent.menuIntReturn(volMenu);

    }

    public static void displayMenu(String[] selectionList) {

        for (int i = 0; i < selectionList.length; i++) {
            System.out.println((i + 1) + ". " + selectionList[i]);
        }

        System.out.print("\nEnter your choice: ");
    }

    public static void inputChoice() {
        System.out.print("\nEnter your choice: ");
    }

    public static void reEnter() {
        System.out.print("\nEnter again: ");
    }

    public static void inputYN(String sentence) {
        System.out.print("\n" + sentence + "\nPlease enter Y / N: ");
    }

    public static void inputEventName() {
        System.out.print("\nEvent Name: ");
    }

    public static void inputDate() {
        System.out.print("Date (dd/MM/yyyy): ");
    }

    public static void inputTime() {
        System.out.print("Time (HH:mm) in 24-hour format: ");
    }

    public static void inputLocation() {
        System.out.print("Location: ");
    }

    public static void inputTicketEventID() {
        System.out.print("\nEnter Event ID for the Ticket: ");
    }

    public static void inputNumTicketType() {
        System.out.print("\nHow many ticket types would you like to add?: ");
    }

    public static void inputTicketType() {
        System.out.print("Ticket Type: ");
    }

    public static void inputTicketPrice() {
        System.out.print("Ticket Price: RM");
    }

    public static void inputTicketAmt() {
        System.out.print("Amount of Tickets: ");
    }

    public static void inputSponsEventID() {
        System.out.print("\nEnter Event ID for the Sponsorship: ");
    }

    public static void inputSponsName() {
        System.out.print("Sponsor Name: ");
    }

    public static void inputSponsAmt() {
        System.out.print("Sponsor Amount: RM");
    }

    public static void displayEvent() {
        System.out.println("\nAvailable events:");
    }

    public static void inputRemoveEventID() {
        System.out.print("\nEnter the Event ID to remove: ");
    }

    public static int searchEventMenu() {
        System.out.println("Search Event by:");
        String[] searchEventMenu = {
            "Event ID",
            "Event Date"
        };

        return ManageEvent.menuIntReturn(searchEventMenu);

    }

    public static void inputEventID() {
        System.out.print("\nEnter the Event ID: ");
    }

    public static void eventFound() {
        System.out.println("\nEvent Found: ");
    }

    public static void displayEventDetails(Event event) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.printf("\n%-15s : %s\n", "Event ID", event.getEventID());
        System.out.printf("%-15s : %s\n", "Event Name", event.getEventName());
        System.out.printf("%-15s : %s\n", "Date", dateFormat.format(event.getDate()));
        System.out.printf("%-15s : %s\n", "Time", event.getTime());
        System.out.printf("%-15s : %s\n", "Location", event.getLocation());
    }

    public static void displayTicketTable() {
        System.out.printf(" %-10s | %-20s | %-10s | %-10s\n", "Ticket ID", "Ticket Type", "Price (RM)", "Status");
        System.out.println("-------------------------------------------------------------------");
    }

    public static void displayTicketDetails(Ticket ticket) {

        System.out.printf("%-10s | %-20s | %-10.2f | %-10s\n",
                ticket.getTicketID(),
                ticket.getTicketType(),
                ticket.getTicketPrice(),
                ticket.getTicketStatus());

    }

    public static void displayAvailableEvent(Event event) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.printf("%-10s | %-30s | %-12s | %-8s | %-20s\n",
                event.getEventID(),
                event.getEventName(),
                dateFormat.format(event.getDate()),
                event.getTime(),
                event.getLocation());
    }

    public static void disEventByDate(int show, Event event) {
        System.out.printf("\n%-6s %-3s %-2s %-8s", "Event ", show, ": ", event.getEventID());
        System.out.printf("%-4s %-50s\n", " -> ", event.getEventName());
    }

    public static void disTicketDetails(int ticketCount, Ticket ticket) {

        System.out.printf("\n%-5d | %-10s | %-20s | %-10.2f | %-10s\n",
                ticketCount,
                ticket.getTicketID(),
                ticket.getTicketType(),
                ticket.getTicketPrice(),
                ticket.getTicketStatus());
    }

    public static void disSponsorshipDetails(int sponsorshipCount, Sponsorship sponsorship) {

        System.out.printf("%-5d | %-12s | %-30s | %-12.2f\n",
                sponsorshipCount,
                sponsorship.getSponsorID(),
                sponsorship.getSponsorName(),
                sponsorship.getSponsorAmount());
    }

    public static void displaySponsorshipDetails(Sponsorship sponsorship) {
        System.out.printf("\n%-20s : %s\n", "Sponsorship ID", sponsorship.getSponsorID());
        System.out.printf("%-20s : %s\n", "Sponsorship Name", sponsorship.getSponsorName());
        System.out.printf("%-20s :RM %.2f\n", "Sponsorship Amount ", sponsorship.getSponsorAmount());

    }

    public static void inputUpdateEventID() {
        System.out.print("\nEnter the Event ID to update: ");
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
            "Save Changes and Exit"
        };

        return ManageEvent.menuIntReturn(updateMenu);
    }

    public static int displayTicketUpdateMenu() {
        System.out.println("\nWhat would you like to update?");
        String[] updateMenu = {
            "Ticket Type and Price",
            "Ticket Status"
        };

        return ManageEvent.menuIntReturn(updateMenu);
    }

    public static int displaySponsorshipUpdateMenu() {
        System.out.println("\nWhat would you like to update?");
        String[] updateMenu = {
            "Sponsor Name",
            "Sponsor Amount",
            "Both Name and Amount"
        };

        return ManageEvent.menuIntReturn(updateMenu);
    }

    public static void displayTicketTableHeader() {
        System.out.printf("\n%-5s | %-10s | %-20s | %-10s | %-10s\n", "No.", "Ticket ID", "Ticket Type", "Price (RM)", "Status");
        System.out.println("-------------------------------------------------------------------");
    }

    public static void displaySponsorshipTableHeader() {
        System.out.printf("\n%-5s | %-12s | %-30s | %-12s\n", "No.", "Sponsor ID", "Sponsor Name", "Amount (RM)");
        System.out.println("-------------------------------------------------------------------");
    }

    public static void displayTicketDetails(int ticketIndex, Ticket currentTicket) {
        System.out.println(ticketIndex + ". Type: " + currentTicket.getTicketType()
                + ", Price: RM" + String.format("%.2f", currentTicket.getTicketPrice()));
    }

    public static void displaySponsorshipDetails(int sponsorshipIndex, Sponsorship sponsorship) {
        System.out.printf("%-5d | %-12s | %-30s | %-10.2f\n",
                sponsorshipIndex,
                sponsorship.getSponsorID(),
                sponsorship.getSponsorName(),
                sponsorship.getSponsorAmount());
    }

    public static void inputNewTicketType(String selectedTicketType) {
        System.out.print("\nEnter new Ticket Type (Current: " + selectedTicketType + "): ");
    }

    public static void inputNewTicketPrice() {
        System.out.print("Enter new Ticket Price: RM");
    }

    public static void inputNewTicketStatus() {
        System.out.print("Enter new Ticket Status: ");
    }

    public static void inputNewSponsorName() {
        System.out.print("Enter new Sponsor Name: ");
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

//list all
    public static void titleListAllEvent() {
        System.out.println("\nList of All Events:");
    }

    public static void displayEventListHeader() {

        System.out.printf("\n%-10s | %-30s | %-12s | %-8s | %-20s\n", "Event ID", "Event Name", "Date", "Time", "Location");
        System.out.println("---------------------------------------------------------------------------------------------");
    }

    public static void displayEventDetailsInList(Event event) {
        displayEventListHeader();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.printf("%-10s | %-30s | %-12s | %-8s | %-20s\n",
                event.getEventID(),
                event.getEventName(),
                dateFormat.format(event.getDate()),
                event.getTime(),
                event.getLocation());
    }

    public static void displayEventTicketsHeader(String eventID) {
        System.out.println("\nTickets for Event ID " + eventID + ":");
        System.out.printf("%-5s | %-10s | %-15s | %-10s\n", "No.", "Ticket ID", "Ticket Type", "Amount (RM)");
        System.out.println("--------------------------------------------------------------");
    }

    public static void displayTicketDetailsInList(int ticketCount, Ticket ticket) {
        System.out.printf("%-5d | %-10s | %-15s | %-10.2f\n",
                ticketCount,
                ticket.getTicketID(),
                ticket.getTicketType(),
                ticket.getTicketPrice());
    }

    public static void displayEventSponsorshipsHeader(String eventID) {
        System.out.println("\nSponsorships for Event ID " + eventID + ":");
        System.out.printf("%-5s | %-12s | %-25s | %-10s\n", "No.", "Sponsor ID", "Sponsor Name", "Amount (RM)");
        System.out.println("---------------------------------------------------------------");
    }

    public static void displaySponsorshipDetailsInList(int sponsorshipCount, Sponsorship sponsorship) {
        System.out.printf("%-5d | %-12s | %-25s | %-10.2f\n",
                sponsorshipCount,
                sponsorship.getSponsorID(),
                sponsorship.getSponsorName(),
                sponsorship.getSponsorAmount());
    }

    //remove volunteer
    public static String inputVolunteerID() {
        System.out.print("\nEnter the Volunteer ID: ");
        return ManageEvent.scan.nextLine().trim();
    }

    public static void displayVolunteerDetails(Volunteer volunteer) {
        System.out.println("\nVolunteer ID: " + volunteer.getVolunteerID());
        System.out.println("Volunteer Name: " + volunteer.getName());
    }

    public static void displayVolunteerEvents(LinkedListInterface<Event> events) {
        System.out.println("\nEvents Participated:\n");
        System.out.printf("%-5s | %-10s | %-30s | %-12s | %-8s | %-20s\n", "No.", "Event ID", "Event Name", "Date", "Time", "Location");
        System.out.println("---------------------------------------------------------------------------------------------");

        Node<Event> eventNode = events.getHead();
        int eventIndex = 1;

        while (eventNode != null) {
            Event event = eventNode.data;
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            System.out.printf("%-5d | %-10s | %-30s | %-12s | %-8s | %-20s\n",
                    eventIndex,
                    event.getEventID(),
                    event.getEventName(),
                    dateFormat.format(event.getDate()),
                    event.getTime(),
                    event.getLocation());

            eventNode = eventNode.next;
            eventIndex++;
        }
    }

    public static String inputEventIDToRemove() {
        System.out.print("\nEnter the Event ID to remove from this volunteer: ");
        return ManageEvent.scan.nextLine().trim();
    }

    // report
    public static int displayReportMenu() {
        System.out.println("Summary Report");
        String[] reportMenu = {
            "Annual Fundraising Report for All Events",
            "Top 5 Events with Peak Volunteer Participation",
            "Top 5 Volunteer Contributions Across Events",
            "Top 5 Events with the Best Ticket Sales Performance"
        };
        return ManageEvent.menuIntReturn(reportMenu);
    }

    public static void inputStartYear() {
        System.out.print("\nEnter the start year (YYYY): ");
    }

    public static void inputEndYear() {
        System.out.print("Enter the end year (YYYY): ");
    }

    public static void displayAnnualFundraisingReport(double[] fundraisingTotals, int startYear) {
        System.out.println("\nAnnual Fundraising Report:");
        for (int i = 0; i < fundraisingTotals.length; i++) {
            System.out.print((startYear + i) + ": ");
            printStar((int) fundraisingTotals[i]);
            System.out.println(" (RM" + String.format("%.2f", fundraisingTotals[i]) + ")");
        }
        displayStarRemark();
    }

    public static void displayTopEventsWithMostVolunteers(Event[] topEvents, int[] volunteerCounts) {
        System.out.println("\nTop 5 Events with Peak Volunteer Participation:");
        for (int i = 0; i < 5; i++) {
            if (topEvents[i] != null) {
                System.out.print(topEvents[i].getEventID() + " (" + topEvents[i].getEventName() + ") ");
                printStar(volunteerCounts[i]);
                System.out.println(" (" + volunteerCounts[i] + ")");
            }
        }
        displayStarRemark();
    }

    public static void displayTopVolunteerContributions(Volunteer[] topVolunteers, int[] contributionCounts) {
        System.out.println("\nTop 5 Volunteer Contributions Across Events:");
        for (int i = 0; i < 5; i++) {
            if (topVolunteers[i] != null) {
                System.out.print(topVolunteers[i].getName() + " (" + topVolunteers[i].getVolunteerID() + ") ");
                printStar(contributionCounts[i]);
                System.out.println(" (" + contributionCounts[i] + ")");
            }
        }
        displayStarRemark();
    }

    public static void displayBestTicketSalesPerformance(Event[] topEvents, int[] soldOutCounts) {
        System.out.println("\nTop 5 Events with the Best Ticket Sales Performance:");
        for (int i = 0; i < 5; i++) {
            if (topEvents[i] != null) {
                System.out.print(topEvents[i].getEventID() + " (" + topEvents[i].getEventName() + ") ");
                printStar(soldOutCounts[i]);
                System.out.println(" (" + soldOutCounts[i] + " sold out tickets)");
            }
        }
        displayStarRemark();
    }

    public static void displayStarRemark() {
        System.out.println("\nNote: \n" + PURPLE + "*" + RESET + " represents 100" + "\n" + BLUE + "*" + RESET + " represents 10" +"\n" +  "*" + " represents 1");
    }

    public static void printStar(int count) {
        int blueStarCount = count / 100;
        int purpleStarCount = (count % 100) / 10;
        int normalStarCount = count % 10;

        // Print blue stars
        for (int i = 0; i < blueStarCount; i++) {
            System.out.print(BLUE + "*" + RESET);
        }

        // Print purple stars
        for (int i = 0; i < purpleStarCount; i++) {
            System.out.print(PURPLE + "*" + RESET);
        }

        // Print normal stars
        for (int i = 0; i < normalStarCount; i++) {
            System.out.print("*");
        }
    }

}
