/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VolunteerSubsystem;

import CommonResources.LinkedList;
import CommonResources.Node;
import EventSystem.Event;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Scanner;

/**
 *
 * @author Heng Pei Lin
 */
public class VBoundary {

    private static final String VOLUNTEER_PATH = "volunteers.txt";
    private static final String EVENT_PATH = "event.txt";
    private static final String EV_PATH = "volunteer_event.txt";

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {

        String[] fileList = {VOLUNTEER_PATH, EVENT_PATH, EV_PATH};
        for (int i = 0; i < fileList.length; i++) {
            boolean fileExist = VDAO.chkFileExist(fileList[i]);
            if (!fileExist) {
                boolean createFile = VDAO.createFile(fileList[i]);
                if (createFile) {
                    System.out.println(ANSI_GREEN + fileList[i] + " created successfully." + ANSI_RESET);
                } else {
                    System.out.println(ANSI_RED + "Error creating file." + ANSI_RESET);
                }
            }
        }

        volunteerMainMenu();
    }

    public static void volunteerMainMenu() {
        boolean cont = true;
        do {
            LinkedList list = new LinkedList();
            list.loadFromFile(VOLUNTEER_PATH);

            System.out.println(ANSI_BLUE + " - - - Volunteer - - -" + ANSI_RESET);
            String[] volMenu = {
                "Add New Volunteer",
                "Remove Volunteer",
                "Search Volunteer",
                "Assign Volunteer to Event",
                "Search Event Under a Volunteer",
                "List All",
                "Filter Volunteer base on Criteria",
                "Generate Summary Report",
                "Exit"
            };
            int selection = menuIntReturn(volMenu);
            switch (selection) {
                case 1:
                    addVolunteer(list);
                    break;
                case 2:
                    remVolunteer(list);
                    break;
                case 3:
                    searchVolunteer(list);
                    break;
                case 4:
                    assignEvent(list);
                    break;
                case 5:
                    searchVolunteerEvent(list);
                    break;
                case 6:
                    listVolunteer(list);
                    break;
                case 7:
                    filterVolunteer(list);
                    break;
                case 8:
                    report(list);
                    break;
                case 9:
                    return;
                default:
                    System.out.println(ANSI_RED + "Invalid menu selection." + ANSI_RESET);
                    break;
            }

            cont = YN("Do you want to continue manage volunteer?");
            if (cont) {
                System.out.println();
            }
        } while (cont);

    }

    // Add new Volunteer
    public static void addVolunteer(LinkedList list) {

        Scanner scan = new Scanner(System.in);

        System.out.println(ANSI_BLUE + "\n- - - Add Volunteer - - - " + ANSI_RESET);
        // ID
        String id = VControl.idGenerator("VL", list);

        // Name
        System.out.print("Enter name: ");
        boolean validName = false;
        String name = null;
        while (!validName) {
            name = scan.nextLine();

            validName = VControl.chkEmptyInput(name);
            if (!validName) {
                System.out.println(ANSI_RED + "Cannot leave blank.\n" + ANSI_RESET);
                System.out.print("Enter again: ");
            }
        }

        // Gender
        System.out.println("\nGender");
        String[] genderMenu = {"Male", "Female"};
        int gSelection = menuIntReturn(genderMenu);

        String gender = null;
        switch (gSelection) {
            case 1:
                gender = "Male";
                break;
            case 2:
                gender = "Female";
                break;
            default:
                System.out.println(ANSI_RED + "Invalid gender.\n" + ANSI_RESET);
                break;
        }

        // Age
        System.out.println("\nRemarks: Only people within the age 18 to 60 years old can registered.");
        System.out.print("Enter age: ");
        boolean validAge = false;
        int age = 0;
        while (validAge == false) {
            String inputAge = scan.nextLine();

            validAge = chkIntInputInRange(inputAge, 18, 60);
            if (validAge) {
                age = Integer.parseInt(inputAge);
            }
        }

        // Contact Num
        System.out.print("\nEnter phone number (e.g. 011 2345 6789): ");
        boolean validPhone = false;
        String phone = null;
        while (!validPhone) {
            phone = scan.nextLine();

            validPhone = VControl.chkEmptyInput(phone);
            if (validPhone) {

                // check is it all integer
                phone = phone.trim();
                validPhone = VControl.chkInt(phone);
                if (validPhone) {

                    //  check correct phone format
                    validPhone = VControl.chkPhoneValidation(phone);
                    if (!validPhone) {
                        System.out.println(ANSI_RED + "Invalid phone format.\n" + ANSI_RESET);
                    }
                } else {
                    System.out.println(ANSI_RED + "Please enter number only.\n" + ANSI_RESET);
                }

            } else {
                System.out.println(ANSI_RED + "Cannot leave blank.\n" + ANSI_RESET);
            }

            if (!validPhone) {
                System.out.print("Enter again: ");
            }

        }

        Volunteer newVol = new Volunteer(id, name, gender, age, phone);
        list.insert(newVol);

        System.out.println(ANSI_GREEN + "\nVolunteer added successfully." + ANSI_RESET);
        list.show();
        list.saveToFile(VOLUNTEER_PATH);
    }

    // Remove Volunteer
    public static void remVolunteer(LinkedList list) {
        Scanner scan = new Scanner(System.in);

        System.out.println(ANSI_BLUE + "\n- - - Remove Volunteer - - - " + ANSI_RESET);
        if (list.isEmpty()) {
            System.out.println(ANSI_RED + "No volunteer yet.\n" + ANSI_RESET);
            return;
        } else {
            list.show();
        }

        System.out.print("Enter volunteer id: ");

        boolean validID = false;
        String id = null;
        while (!validID) {
            id = scan.nextLine().trim();

            validID = idValidation(id, "VL");
        }

        id = id.substring(0, 2).toUpperCase() + id.substring(2, 7);

        Volunteer foundVolunteer = VControl.findById(list, id);
        if (foundVolunteer == null) {
            System.out.println(ANSI_RED + "\nVolunteer does not exist." + ANSI_RESET);
        } else {
            boolean remove = VControl.deleteById(list, id);
            if (remove == false) {
                System.out.println(ANSI_RED + "\nRemove unsuccessfully." + ANSI_RESET);
            } else {
                System.out.println(ANSI_GREEN + "\nRemove successfully." + ANSI_RESET);
                System.out.printf("\n| %-12s | %-30s | %-10s | %-5s | %-15s |\n", "Volunteer ID", "Name", "Gender", "Age", "Contect No.");
                list.show();
                list.saveToFile(VOLUNTEER_PATH);
            }
        }
    }

    // Search Volunteer
    public static void searchVolunteer(LinkedList list) {
        Scanner scan = new Scanner(System.in);

        System.out.println(ANSI_BLUE + "\n- - - Remove Volunteer - - - " + ANSI_RESET);
        if (list.isEmpty()) {
            System.out.println(ANSI_RED + "No volunteer yet.\n" + ANSI_RESET);
            return;
        }

        System.out.print("Enter volunteer id: ");

        boolean validID = false;
        String id = null;
        while (!validID) {
            id = scan.nextLine().trim();

            validID = idValidation(id, "VL");
        }

        id = id.substring(0, 2).toUpperCase() + id.substring(2, 7);

        Volunteer foundVolunteer = VControl.findById(list, id);
        if (foundVolunteer == null) {
            System.out.println(ANSI_RED + "\nVolunteer does not exist." + ANSI_RESET);
        } else {
            System.out.println(foundVolunteer.toString());
        }
    }

    // Assign volunteers to events
    public static void assignEvent(LinkedList volList) {
        Scanner scan = new Scanner(System.in);

        System.out.println(ANSI_BLUE + "\n- - - Assign Volunteer to Event - - - " + ANSI_RESET);

        LinkedList<Event> eventList = new LinkedList();
        eventList.loadFromFile(EVENT_PATH);
        if (eventList.isEmpty()) {
            System.out.println(ANSI_RED + "No event yet." + ANSI_RESET);
            return;
        }

        displayEventTable(eventList);

        System.out.print("\nEnter event id: ");

        boolean validID = false;
        String eventID = null;
        while (!validID) {
            eventID = scan.nextLine().trim();

            validID = idValidation(eventID, "EV");
        }

        eventID = eventID.substring(0, 2).toUpperCase() + eventID.substring(2, 7);

        if (volList.isEmpty()) {
            System.out.println(ANSI_RED + "No volunteer yet." + ANSI_RESET);
            return;
        }
        
        LinkedList<EventVolunteer> combineList = new LinkedList<>();
        combineList.loadFromFile(EV_PATH);
        
        boolean contAddVol = volHavenAttendEvent(volList, combineList, eventID);
        if(!contAddVol){
            return;
        }
        
        System.out.print("\nEnter volunteer id: ");

        validID = false;
        String volID = null;
        while (!validID) {
            volID = scan.nextLine().trim();

            validID = idValidation(volID, "VL");
        }

        volID = volID.substring(0, 2).toUpperCase() + volID.substring(2, 7);

        EventVolunteer ev = new EventVolunteer(eventID, volID);
        combineList.insert(ev);
        combineList.saveToFile(EV_PATH);
        
        System.out.println("\n" + ANSI_GREEN + "Volunteer assign to event successfully.\n" + ANSI_RESET);
        
        displayComTable(combineList);
    }

    public static void displayEventTable(LinkedList<Event> eventList) {

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.printf("| %-10s | %-50s | %-10s | %-5s | %-30s |\n", "Event ID", "Event Name", "Date", "Time", "Location");

        eventList.removeEmptyData();
        Node<Event> currentNode = eventList.head;
        while (currentNode != null) {

            Date date = currentNode.data.getDate();
            String strDate = dateFormat.format(date);
            System.out.printf("| %-10s | %-50s | %-10s | %-5s | %-30s |\n",
                    currentNode.data.getEventID(), currentNode.data.getEventName(), strDate, currentNode.data.getTime(), currentNode.data.getLocation());

            currentNode = currentNode.next;
        }
    }

    public static boolean volHavenAttendEvent(LinkedList<Volunteer> volList, LinkedList<EventVolunteer> combineList, String eventID) {

        boolean contAdd = true;

        volList.removeEmptyData();
        // if combine list empty
        if (combineList.isEmpty()) {

            Node<Volunteer> currentNode = volList.head;
            while (currentNode != null) {

                System.out.printf("| %-12s | %-30s | %-10s | %-5s | %-15s |\n", "Volunteer ID", "Name", "Gender", "Age", "Contect No.");
                System.out.printf("| %-12s | %-30s | %-10s | %-5s | %-15s |\n",
                        currentNode.data.getVolunteerID(), currentNode.data.getName(), currentNode.data.getGender(), currentNode.data.getAge(), currentNode.data.getContactNo());

                currentNode = currentNode.next;
            }
        } else {
            Node<Volunteer> volCurrentNode = volList.head;
            int sum = 0;
            while (volCurrentNode != null) {
                Volunteer volunteer = volCurrentNode.data;
                boolean isInvolved = false;

                // Check if the volunteer is involved in the event
                Node<EventVolunteer> combineCurrentNode = combineList.head;
                while (combineCurrentNode != null) {
                    EventVolunteer eventVolunteer = combineCurrentNode.data;
                    if (eventVolunteer.getEventID().equals(eventID) && eventVolunteer.getVolunteerID().equals(volunteer.getVolunteerID())) {
                        isInvolved = true;
                        sum++;
                        break;
                    }
                    combineCurrentNode = combineCurrentNode.next;
                }

                int show = 0;
                // If the volunteer is not involved in the event, print their details
                if (!isInvolved) {
                    if(show == 0){
                        System.out.printf("\n| %-12s | %-30s | %-10s | %-5s | %-15s |\n", "Volunteer ID", "Name", "Gender", "Age", "Contect No.");
                        show++;
                    }
                    System.out.printf("| %-12s | %-30s | %-10s | %-5s | %-15s |\n",
                            volunteer.getVolunteerID(), volunteer.getName(), volunteer.getGender(), volunteer.getAge(), volunteer.getContactNo());
                }

                volCurrentNode = volCurrentNode.next;
            }
            
            // all volunteer had been assign
            if(sum == volList.length()){
                System.out.println("\n" + ANSI_RED + "All volunteer had participate in the event.\n" + ANSI_RESET);
                contAdd = false;
            }
        }
        return contAdd;
    }

    public static void displayComTable(LinkedList<EventVolunteer> combineList){
        if(combineList.isEmpty()){
            System.out.println(ANSI_RED + "No volunteer assign to event yet." + ANSI_RESET);
        }
        
        System.out.printf("| %-12s | %-12s |\n", "Event ID", "Volunteer ID");
        combineList.removeEmptyData();
        Node<EventVolunteer> currentNode = combineList.head;
        while (currentNode != null) {
            System.out.println(currentNode.data.toString());

            currentNode = currentNode.next;
        }
    }
    
    // Search events under a volunteer
    public static void searchVolunteerEvent(LinkedList list) {
        Scanner scan = new Scanner(System.in);
        
        LinkedList<EventVolunteer> combineList = new LinkedList<>();
        combineList.loadFromFile(EV_PATH);
        
        // search volunteer
        // check if any event under it
        System.out.println(ANSI_BLUE + "\n- - - Search Event under Volunteer - - - " + ANSI_RESET);
        System.out.print("Enter volunteer ID: ");
        
        String volID = null;
        boolean validVol = false;
        while(!validVol){
            volID = scan.nextLine();
            validVol = idValidation(volID, "VL");
        }
        
        volID = volID.substring(0, 2).toUpperCase() + volID.substring(2, 7);

        System.out.println("\nCurrent Volunteer: " + volID);
        Node<EventVolunteer> evNode = combineList.head;
        int show = 1;
        while(evNode != null){
            if (evNode.data.getVolunteerID().equals(volID)){
                System.out.println("Event " + show + ": " + evNode.data.getEventID());
                show++;
            }
            evNode = evNode.next;
        }
        if(show == 1){
            System.out.println(ANSI_RED + "No event attended." + ANSI_RESET);
        }
    }

    // List all volunteers
    public static void listVolunteer(LinkedList list) {
        System.out.println(ANSI_BLUE + "\n- - - List Volunteer - - - " + ANSI_RESET);
        System.out.printf("| %-12s | %-30s | %-10s | %-5s | %-15s |\n", "Volunteer ID", "Name", "Gender", "Age", "Contect No.");
        list.show();
    }
    
    // Filter volunteer base on criteria
    public static void filterVolunteer(LinkedList list){
        System.out.println(ANSI_BLUE + "\n- - - Filter Volunteer - - - " + ANSI_RESET);
        if(list.isEmpty()){
            System.out.println(ANSI_RED + "No volunteer yet. Nothing to filter. Exiting." + ANSI_RESET);
            return;
        }
        
        String[] filterMenu = {"Gender", "Before Age (e.g. 20)"};
        int filterSelection = menuIntReturn(filterMenu);
        
        switch(filterSelection){
            case 1:
                filterGenderSelection(list);
                break;
            case 2:
                filterAge(list);
                break;
            default:
                System.out.println(ANSI_RED + "Invalid filter selection." + ANSI_RESET);
                break;
        }
    }
    
    public static void filterGenderSelection(LinkedList list){
        System.out.println("\nFilter by:");
        String[] filterMenu = {"Male", "Female"};
        int filterSelection = menuIntReturn(filterMenu);
        
        switch(filterSelection){
            case 1:
                filterGender(list, true);
                break;
            case 2:
                filterGender(list, false);
                break;
            default:
                System.out.println(ANSI_RED + "Invalid filter selection." + ANSI_RESET);
                break;
        }
    }
    
    public static void filterGender(LinkedList list, boolean isMale){
        Node<Volunteer> currentNode = list.head;
        int show = 1;
        String gender = null;
        while(currentNode != null){
            if(isMale == true){
                gender = "male";
                if(currentNode.data.getGender().toUpperCase().equalsIgnoreCase("MALE")){
                    if(show == 1){
                        System.out.println("\n --- Male Volunteer ---");
                        System.out.printf("| %-12s | %-30s | %-10s | %-5s | %-15s |\n", "Volunteer ID", "Name", "Gender", "Age", "Contect No.");
                    }
                    System.out.println(currentNode.data.toString());
                    show++;
                }
            }else{
                gender = "female";
                if(currentNode.data.getGender().toUpperCase().equalsIgnoreCase("FEMALE")){
                    if(show == 1){
                        System.out.println("\n --- Female Volunteer ---");
                        System.out.printf("| %-12s | %-30s | %-10s | %-5s | %-15s |\n", "Volunteer ID", "Name", "Gender", "Age", "Contect No.");
                    }
                    System.out.println(currentNode.data.toString());
                    show++;
                }
            }
            currentNode = currentNode.next;
        }
        if(show == 1){
            System.out.println("\n" + ANSI_RED + "There are no " + gender + " volunteer." + ANSI_RESET);
        }
    }
    
    public static void filterAge(LinkedList list){
        Scanner scan = new Scanner(System.in);
        System.out.println("\nRemarks: (1)Volunteer before within the age will be shown. "
                         + "\n         (2)Only volunteer betwwen the age 18 to 60 can participant as a volunteer.");
        System.out.print("Enter age: ");
        
        int age = 0;
        boolean validAge = false;
        while(!validAge){
            String inputA = scan.nextLine();
            
            validAge = chkIntInputInRange(inputA, 18, 60);
            
            if(validAge){
                age = Integer.parseInt(inputA);
            }
        }
        
        Node<Volunteer> currentNode = list.head;
        int show = 1;
        while(currentNode != null){
            if(currentNode.data.getAge() <= age){
                if(show == 1){
                    System.out.println("\n --- Volunteer before Age " + age +" ---");
                    System.out.printf("| %-12s | %-30s | %-10s | %-5s | %-15s |\n", "Volunteer ID", "Name", "Gender", "Age", "Contect No.");
                }
                System.out.println(currentNode.data.toString());
                show++;
            }
            currentNode = currentNode.next;
        }
        
        if(show == 1){
            System.out.println("\n" + ANSI_RED + "No volunteer currently below or within this age." + ANSI_RESET);
        }
        
    }
    
    // Generate report
    public static void report(LinkedList list){
        System.out.println(ANSI_BLUE + "\n- - - Report - - - " + ANSI_RESET);
        
        if(list.isEmpty()){
            System.out.println(ANSI_RED + "No volunteer yet. No data to generate report." + ANSI_RESET);
            return;
        }
        
        String[] reportMenu = {"Gender Distribution in Volunteer Involvement", 
            "Volunteer Participation Frequency by Age Group"};
        int reportSelection = menuIntReturn(reportMenu);
        
        switch(reportSelection){
            case 1:
                genderDistribution(list);
                break;
            case 2:
                volAgeGroup(list);
                break;
            default:
                System.out.println(ANSI_RED + "Invalid filter selection." + ANSI_RESET);
                break;
        }
    
    }
    
    public static void genderDistribution(LinkedList list){
        Node<Volunteer> currentNode = list.head;
        
        int sumM = 0;
        int sumF = 0;
        while(currentNode!=null){
            
            String gender = currentNode.data.getGender();
            if(gender.toUpperCase().equals("MALE")){
                sumM++;
            }else{
                sumF++;
            }
            
            currentNode = currentNode.next;
        }
        
        System.out.println("\n- Gender Distibution in Volunteer Involvement -");
        
        int sum = sumM + sumF;
        double dM = sumM/sum;
        double dF = sumF/sum;
        
        System.out.printf("%-8s %-3s %-5d %-1s %-2.2f %-2s", "Male", "->", sumM, "(", dM, "%)");
        System.out.printf("\n%-8s %-3s %-5d %-1s %-2.2f %-2s", "Female", "->", sumF, "(", dF, "%)");
        
        System.out.print("\n\nConclusion: ");
        if(sumM > sumF){
            System.out.println("Male volunteer is more than female volunteer.");
        }else{
            System.out.println("Female volunteer is more than male volunteer.");
        }
    }
    
    public static void volAgeGroup(LinkedList list){
        Node<Volunteer> currentNode = list.head;
        
        int sumY = 0; // 18 - 30
        int sumM = 0; // 31 - 45
        int sumO = 0; // 45 - 60
        while(currentNode!=null){
            
            int age = currentNode.data.getAge();
            if(age <= 30){
                sumY++;
            }else if (age <= 45){
                sumM++;
            }else{
                sumO++;
            }
            
            currentNode = currentNode.next;
        }
        
        System.out.println("\n- Volunteer Participation Frequency by Age Group -");
        System.out.printf("%-20s %-10s %-3s %-5d", "Young Adults", "(18 - 30)", "-> ", sumY);
        printStar(sumY);
        
        System.out.printf("\n%-20s %-10s %-3s %-5d", "Midle-aged Adults", "(31 - 45)", "-> ", sumM);
        printStar(sumM);
        
        System.out.printf("\n%-20s %-10s %-3s %-5d", "Old-aged Adults", "(46 - 60)", "-> ", sumO);
        printStar(sumO);
        
        int max = sumY;
        String group ="young adults";
        if(max < sumM){
            max = sumM;
            group = "middle-aged adults";
        }
        if(max < sumY){
            max = sumY;
            group = "old-aged adults";
        }
        
        System.out.println("\n\nRemarks: Symbol * will be display if item's total exceed 50 and each * represent 50 items");
        System.out.println("The most frequent participant age group is " + group + ", with total amount of " + max );
    }
    
    public static void printStar(int count){
        if (count > 50){
            int left = count % 50;
            for (int i = 0; i < left; i ++){
                System.out.print(ANSI_BLUE + " *" + ANSI_RESET);
            }
        }
    }

    //------------
    // Common Use
    //------------
    public static boolean YN(String sentence) {
        Scanner scan = new Scanner(System.in);

        boolean validInput = false;
        String input = null;

        System.out.print("\n" + sentence + "\nPlease enter Y / N: ");

        while (!validInput) {

            input = (scan.nextLine()).toUpperCase().trim();

            validInput = VControl.chkEmptyInput(input);

            if (validInput) {
                String[] inputList = {"Y", "N"};
                validInput = VControl.chkSpecificWord(inputList, input);
                if (!validInput) {
                    System.out.println(ANSI_RED + "Please enter Y or N only.\n" + ANSI_RESET);
                }
            } else {
                System.out.println(ANSI_RED + "Cannot beave blank.\n" + ANSI_RESET);
            }

            if (!validInput) {
                System.out.print("Enter again: ");
            }

        }

        boolean cont = VControl.chkYN(input.toUpperCase().trim());
        if (cont) {
            return true;
        } else {
            return false;
        }
    }

    public static int menuIntReturn(String[] selectionList) {

        Scanner scan = new Scanner(System.in);

        int intInput = 0;
        boolean validInput = false;

        for (int i = 0; i < selectionList.length; i++) {
            System.out.println((i + 1) + ". " + selectionList[i]);
        }
        System.out.print("Enter your selection: ");

        while (validInput == false) {
            String stringInput = scan.nextLine();

            validInput = chkIntInputInRange(stringInput, 1, selectionList.length);

            if (validInput) {
                intInput = Integer.parseInt(stringInput);
            }

        }

        return intInput;
    }

    public static boolean chkIntInputInRange(String input, int initial, int end) {
        boolean validInput;

        // check is empty
        validInput = VControl.chkEmptyInput(input);
        if (validInput) {

            // chk is it an integer
            validInput = VControl.chkInt(input);
            if (validInput) {

                // chk is within the integer range
                int intInput = Integer.parseInt(input);
                validInput = VControl.intSelectionValidation(intInput, initial, end);
                if (!validInput) {
                    System.out.println(ANSI_RED + "Invalid integer. Please enter between " + initial + " to " + end + ".\n" + ANSI_RESET);
                }

            } else {
                System.out.println(ANSI_RED + "Invalid integer. Please enter a valid integer.\n" + ANSI_RESET);
            }

        } else {
            System.out.println(ANSI_RED + "Cannot leave blank.\n" + ANSI_RESET);
        }

        if (!validInput) {
            System.out.print("Enter again: ");
        }

        return validInput;
    }

    public static boolean idValidation(String id, String constrant) {
        boolean validID;

        // chk is it empty
        validID = VControl.chkEmptyInput(id);
        if (validID) {
            // chk correct length
            validID = VControl.chkLength(7, id);
            if (validID) {

                // chk if correct format
                validID = VControl.idValidation(id, constrant);
                if (!validID) {
                    System.out.println(ANSI_RED + "Invalid format. Volunteer Id format should be VL00000.\n" + ANSI_RESET);
                }
            } else {
                System.out.println(ANSI_RED + "Invalid length.\n" + ANSI_RESET);
            }
        } else {
            System.out.println(ANSI_RED + "Cannot leave blank.\n" + ANSI_RESET);
        }

        if (!validID) {
            System.out.print("Enter again: ");
        }

        return validID;
    }

}
