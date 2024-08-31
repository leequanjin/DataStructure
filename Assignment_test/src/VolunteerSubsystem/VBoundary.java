/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VolunteerSubsystem;

import CommonResources.LinkedList;
import CommonResources.LinkedListInterface;
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
    
    private LinkedListInterface<Volunteer> list = new LinkedList<>();

    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String RESET = "\u001B[0m";

    //------------
    // Common Use
    //------------
    public static void inputYN(String sentence){
        System.out.print("\n" + sentence + "\nPlease enter Y / N: ");
    }
    
    public static void reEnter(){
        System.out.print("Enter again: ");
    }
    
    public static void breakLine(){
        System.out.println();
    }
    
    public static void displayMenu(String[] selectionList){

        for (int i = 0; i < selectionList.length; i++) {
            System.out.println((i + 1) + ". " + selectionList[i]);
        }
        
        System.out.print("Enter your selection: ");
    }
    
    public static void disVolHeader(){
        System.out.printf("\n| %-12s | %-30s | %-10s | %-5s | %-15s |\n", "Volunteer ID", "Name", "Gender", "Age", "Contect No.");
    }
    
    public static void disEventHeader(){
        System.out.printf("| %-10s | %-50s | %-10s | %-5s | %-30s |\n", "Event ID", "Event Name", "Date", "Time", "Location");
    }
    
    public static void disEVHeader(){
        System.out.printf("| %-12s | %-12s |\n", "Event ID", "Volunteer ID");
    }
    
    public static void displayVol(LinkedListInterface<Volunteer> volList){
        disVolHeader();
        disList(volList);
    }
    
    public static<T> void displayEventTable(LinkedListInterface<Event> eventList) {

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        disEventHeader();
        
        eventList.removeEmptyData();
        Node<Event> currentNode = eventList.getHead();
        while (currentNode != null) {

            Date date = currentNode.data.getDate();
            String strDate = dateFormat.format(date);
            System.out.printf("| %-10s | %-50s | %-10s | %-5s | %-30s |\n",
                    currentNode.data.getEventID(), currentNode.data.getEventName(), strDate, currentNode.data.getTime(), currentNode.data.getLocation());

            currentNode = currentNode.next;
        }
    }
    
    public static<T> void displayEVTable(LinkedListInterface<EventVolunteer> evList){
        disEVHeader();
        evList.removeEmptyData();
        disList(evList);
    }
    
    public static<T> void disList(LinkedListInterface<T> list){
        System.out.println(list.show());
    }
    
    public static void disCertainVolunteer(Volunteer vol){
        System.out.println(vol.toString());
    }

    public static int volunteerMainMenu() {

        System.out.println(BLUE + " - - - Volunteer - - -" + RESET);
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

        return VControl.menuIntReturn(volMenu);
        
    }

    // -----------------
    // Add new Volunteer
    // -----------------
    public static void disAddVolunteer() {
        System.out.println(BLUE + "\n- - - Add Volunteer - - - " + RESET);
    }
    
    public static void inputName(){
        System.out.print("Enter name: ");
    }
    
    public static int inputGender(){
        System.out.println("\nGender");
        String[] genderMenu = {"Male", "Female"};
        
        return VControl.menuIntReturn(genderMenu);
    }
    
    public static void inputAge(){
        System.out.println("\nRemarks: Only people within the age 18 to 60 years old can registered.");
        System.out.print("Enter age: ");
    }
    
    public static void inputPhone(){
        System.out.print("\nEnter phone number (e.g. 011 2345 6789): ");
    }

    // -----------------
    // Remove Volunteer
    // -----------------
    public static void disRemVolunteer() {
        System.out.println(BLUE + "\n- - - Remove Volunteer - - - " + RESET);
        
    }
    
    public static String inputVolID(){
        System.out.print("Enter volunteer ID: ");
        return VControl.idValidation("VL");
    }

    // -----------------
    // Search Volunteer
    // -----------------
    public static void disSearchVolunteer() {
        System.out.println(BLUE + "\n- - - Search Volunteer - - - " + RESET);
    }

    // ----------------------
    // Assign volunteer event
    // ----------------------
    public static void disAssignEvent() {
        System.out.println(BLUE + "\n- - - Assign Volunteer to Event - - - " + RESET);
    }

    public static String inputEventID(){
        System.out.print("Enter event ID: ");
        return VControl.idValidation("EV");
    }

    // ----------------------
    // Search volunteer event
    // ----------------------
    public static void disSearchVolunteerEvent() {
        System.out.println(BLUE + "\n- - - Search Event under Volunteer - - - " + RESET);
    }
    
    public static void disCurentVolunteer(String volID){
        System.out.println("\nCurrent Volunteer: " + volID);
    }
    
    public static void disVolCEventID(int show, String eventID){
        System.out.printf("%-6s %-3s %-2s %-8s", "Event " , show , ": " , eventID);
    }
    
    public static void disVolCEventName(String eventName){
        System.out.printf("%-4s %-50s\n", " -> ", eventName);
    }
    
    // Filter volunteer base on criteria
    public static void disFilterVolunteer(){
        System.out.println(BLUE + "\n- - - Filter Volunteer - - - " + RESET);
    }
    
    public static int filterMainMenu(){
        String[] filterMenu = {"Gender", "Before Age (e.g. 20)"};
        return VControl.menuIntReturn(filterMenu);
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
                System.out.println(RED + "Invalid filter selection." + RESET);
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
            System.out.println("\n" + RED + "There are no " + gender + " volunteer." + RESET);
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
            System.out.println("\n" + RED + "No volunteer currently below or within this age." + RESET);
        }
        
    }
    
    // Generate report
    public static void report(LinkedList list){
        System.out.println(BLUE + "\n- - - Report - - - " + RESET);
        
        if(list.isEmpty()){
            System.out.println(RED + "No volunteer yet. No data to generate report." + RESET);
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
                System.out.println(RED + "Invalid filter selection." + RESET);
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
                System.out.print(BLUE + " *" + RESET);
            }
        }
    }
    
}