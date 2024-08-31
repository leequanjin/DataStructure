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
    
    // if typr other than specific
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
    
    
    
    public static Date validateDate(String dateString) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    dateFormat.setLenient(false);

    String[] parts = dateString.split("/");
    if (parts.length != 3) {
        EUtility.invalidDateMsg();
        return null;
    }

    int day, month, year;

    try {
        day = Integer.parseInt(parts[0]);
        month = Integer.parseInt(parts[1]);
        year = Integer.parseInt(parts[2]);
    } catch (NumberFormatException e) {
        EUtility.invalidValueDate();
        return null;
    }

    // Validate month
    if (month < 1 || month > 12) {
        EUtility.invalidMonth();
        return null;
    }

    // Validate day
    if (!isValidDay(day, month, year)) {
        EUtility.invalidDay();
        return null;
    }

    try {
        Date date = dateFormat.parse(dateString);
        return date;
    } catch (ParseException e) {
        EUtility.invalidDateMsg();
        return null;
    }
}
    
    
    
    
    


    

    public static String validateTime(String time) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        timeFormat.setLenient(false);

        try {
            timeFormat.parse(time);
            return time;
        } catch (ParseException e) {
            return null;
        }
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
                    //addTicket();
                    break;
                case 3:
                    //addSponsorship();
                    break;
                case 4:
                    //removeEvent();
                    break;
                case 5:
                    //searchEvent();
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
    
    // Add Event
    private static void addEvent() {
    Scanner scan = new Scanner(System.in);

    String eventID = EControl.generateEventID("EV");
    
    EBoundary.inputEventName();
    String eventName = null;
        boolean validName = false;
        while (!validName) {
            eventName = scan.nextLine().trim();

            validName = EControl.chkEmptyInput(eventName);
            if (!validName) {
                EUtility.emptyInputErrorMsg();
                EBoundary.reEnter();
            }
        }
    
    Date date = null;
    boolean validDate = false;
    while (!validDate) {
        EBoundary.inputDate();
        String dateString = scan.nextLine().trim();
        date = EControl.validateDate(dateString);
        if (date != null) {
            validDate = true;
        } else {
            EUtility.invalidDateMsg();
            EBoundary.reEnter();
        }
    }

    
    String time = null;
    boolean validTime = false;
    while (!validTime) {
        EBoundary.inputTime();
        time = scan.nextLine().trim();
        time = EControl.validateTime(time);
        if (time != null) {
            validTime = true;
        } else {
            EUtility.invalidTimeMsg();
            EBoundary.reEnter();
        }
    }

    
    String location = null;
        boolean validLocation = false;
        while (!validLocation) {
            location = scan.nextLine().trim();

            validName = EControl.chkEmptyInput(location);
            if (!validName) {
                EUtility.emptyInputErrorMsg();
                EBoundary.reEnter();
            }
        }

    Event event = new Event(eventID, eventName, date, time, location);
    eventList.insert(event);
    eventList.saveToFile(EVENT_FILE);
    EUtility.eventAddedMsg();
    
}
    
    
    
    
}
