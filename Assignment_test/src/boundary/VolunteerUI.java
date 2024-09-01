/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import adt.LinkedListInterface;
import adt.Node;
import entity.Event.Event;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import entity.Volunteer.Volunteer;
import entity.Volunteer.EventVolunteer;
import control.VolunteerManagement;

/**
 *
 * @author Heng Pei Lin
 */
public class VolunteerUI {

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

        return VolunteerManagement.menuIntReturn(volMenu);
        
    }
    
    public static String contVolQ(){
        return "Do you want to continue manage volunteer?";
    }
    
    public static String remOtherVolQ(){
        return "Do you want to remove other volunteer?";
    }
    
    public static String contSearchOtherVolQ(){
        return "Do you want to search other volunteer?";
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
        
        return VolunteerManagement.menuIntReturn(genderMenu);
    }
    
    public static void inputAge(){
        System.out.println("\nRemarks: Only people within the age 18 to 60 years old can registered.");
        System.out.print("Enter age: ");
    }
    
    public static void inputPhone(){
        System.out.print("\nEnter phone number (e.g. 01123456789): ");
    }

    // -----------------
    // Remove Volunteer
    // -----------------
    public static void disRemVolunteer() {
        System.out.println(BLUE + "\n- - - Remove Volunteer - - - " + RESET);
        
    }
    
    public static String inputVolID(){
        System.out.print("Enter volunteer ID: ");
        return VolunteerManagement.idValidation("VL");
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
        return VolunteerManagement.idValidation("EV");
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
    
    // ---------------------------------
    // Filter Volunteer base on Criteria
    // ---------------------------------
    public static void disFilterVolunteer(){
        System.out.println(BLUE + "\n- - - Filter Volunteer - - - " + RESET);
    }
    
    public static int filterMainMenu(){
        String[] filterMenu = {"Gender", "Before Age (e.g. 20)"};
        return VolunteerManagement.menuIntReturn(filterMenu);
    }
    
    public static int disFilterGenderSelection(){
        System.out.println("\nFilter by:");
        String[] filterMenu = {"Male", "Female"};
        return VolunteerManagement.menuIntReturn(filterMenu);
    }
    
    public static void maleHeading(){
        System.out.println("\n --- Male Volunteer ---");
    }
    
    public static void femaleHeading(){
        System.out.println("\n --- Female Volunteer ---");
    }

    public static void disFilterAge(){
        System.out.println("\nRemarks: (1)Volunteer before within the age will be shown. "
                         + "\n         (2)Only volunteer betwwen the age 18 to 60 can participant as a volunteer.");
        System.out.print("Enter age: ");
    }
    
    public static void disAgeHeader(int age){
        System.out.println("\n --- Volunteer before Age " + age +" ---");
    }
    
    // ---------------
    // Report Generate
    // ---------------
    public static void disReport(){
        System.out.println(BLUE + "\n- - - Report - - - " + RESET);
    }
    
    public static int disReportMenu(){
        String[] reportMenu = {"Gender Distribution in Volunteer Involvement", 
            "Volunteer Participation Frequency by Age Group",
            "Duplicate Volunteer Report"};
        return VolunteerManagement.menuIntReturn(reportMenu);
    }
    
    public static void disGenderDistribution(int sumM, int sumF){
        
        System.out.println("\n- Gender Distibution in Volunteer Involvement -");
        
        double dm = sumM;
        double df = sumF;
        double sum = sumM + sumF;
        double dM = (dm/sum) *100;
        double dF = (df/sum) *100;
        
        System.out.printf("%-8s %-3s %-5d %-1s %-4.2f %-2s", "Male", "->", sumM, "(", dM, "%)");
        System.out.printf("\n%-8s %-3s %-5d %-1s %-4.2f %-2s", "Female", "->", sumF, "(", dF, "%)");
        
        System.out.print("\n\nConclusion: ");
        if(sumM > sumF){
            System.out.println("Male volunteer is more than female volunteer.");
        }else if(sumM < sumF){
            System.out.println("Female volunteer is more than male volunteer.");
        }else{
            System.out.println("Both contribution of female and male volunteer are equal.");
        }
    }
    
    public static void disVolAgeGroup(int sumY, int sumM, int sumO){
        
        System.out.println("\n- Volunteer Participation Frequency by Age Group -");
        System.out.printf("%-20s %-10s %-3s %-5d", "Young Adults", "(18 - 30)", "-> ", sumY);
        VolunteerManagement.printStar(sumY);
        
        System.out.printf("\n%-20s %-10s %-3s %-5d", "Midle-aged Adults", "(31 - 45)", "-> ", sumM);
        VolunteerManagement.printStar(sumM);
        
        System.out.printf("\n%-20s %-10s %-3s %-5d", "Old-aged Adults", "(46 - 60)", "-> ", sumO);
        VolunteerManagement.printStar(sumO);
        
    }
    
    public static void ageGroupConclu(String group, int max){
        System.out.println("\n\nRemarks: Symbol * will be display if item's total exceed 50 and each * represent 50 items");
        System.out.println("The most frequent participant age group is " + group + ", with total amount of " + max );
    }
    
    public static void disStar(){
        System.out.print(BLUE + " *" + RESET);
    }
    
    public static<T> void displayDuplicateVol(LinkedListInterface<T> dupList){
        System.out.println("\n- Duplicate Volunteer Report -");
        disVolHeader();
        System.out.println(dupList.show());
        System.out.println("\n" + RED + "- " + dupList.length() + " duplicate volunteers - " + RESET);
    }
    
    public static void displayNoDup(){
        System.out.println("\n" + GREEN + "There are no duplicate volunteer." + RESET);
    }
    
}