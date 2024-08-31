/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VolunteerSubsystem;

/**
 *
 * @author Asus
 */
public class VUtility {
    
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";
    
    // chk file
    public static void fileNoExist(String fileName){
        System.out.println(RED + "File: " + fileName + " does not exist." + RESET);
    }
    
    public static void fileExist(String fileName){
        System.out.println(GREEN + "File: " + fileName + " is ready." + RESET);
    }
    
    public static void createFileFail(String fileName){
        System.out.println(RED + "File: " + fileName + " fail to create." + RESET);
    }
    
    public static void createFileSuccesfully(String fileName){
        System.out.println(GREEN + "File: " + fileName + " created successfully." + RESET);
    }
    
    // empty list
    public static void noVolInList(){
        System.out.println(RED + "No volunteer yet.\n" + RESET);
    }
    
    public static void noEventInList(){
        System.out.println(RED + "No event yet.\n" + RESET);
    }
    
    public static void noEVInList(){
        System.out.println(RED + "No volunteer participate in any event yet.\n" + RESET);
    }
    
    // does not exist
    public static void volNoExist(){
        System.out.println(RED + "\nVolunteer does not exist." + RESET);
    }
    
    // input error
    public static void emptyInputErrorMsg(){
        System.out.println(RED + "Please do not leave blank.\n" + RESET);
    }
    
    public static void enterYNOnly(){
        System.out.println(RED + "Please enter Y or N only.\n" + RESET);
    }
    
    public static void invalidIntInput(){
        System.out.println(RED + "Invalid input. Please enter valid integer number.\n" + RESET);
    }
    
    public static void intNotInRange(int initial, int end){
        System.out.println(RED + "Invalid integer. Please enter between " + initial + " to " + end + ".\n" + RESET);
    }
    
    public static void invalidLength(){
        System.out.println(RED + "Invalid length.\n" + RESET);
    }
    
    public static void invalidIDFormat(String constrant){
        System.out.println(RED + "Invalid format. Volunteer Id format should be " + constrant.toUpperCase() + "00000.\n" + RESET);
    }
    
    public static void invalidMenuSelection(){
        System.out.println(RED + "Invalid menu selection.\n" + RESET);
    }
    
    public static void invalidGender(){
        System.out.println(RED + "Invalid gender.\n" + RESET);
    }
    
    public static void invalidPhone(){
        System.out.print(RED + "Invalid phone format.\n" + RESET);
    }
    
    public static void addVolSuccess(){
        System.out.println("\n" + GREEN + "Volunteer added successfully." + RESET);
    }
    
    // Remove Vol
    public static void remVolSuccess(){
        System.out.println("\n" + GREEN + "Volunteer remove successfully." + RESET);
    }
    
    public static void remVolFail(){
        System.out.println("\n" + RED + "Volunteer remove unsuccessfully." + RESET);
    }
    
    public static void allVolAttendEvent(){
        System.out.print("\n" + RED + "All volunteer had participate in the event." + RESET);
    }
    
    public static void volAsssignEvent(){
        System.out.println("\n" + GREEN + "Volunteer assign to event successfully.\n" + RESET);
    }
    
    public static void volNoAttendEvent(){
        System.out.print("\n" + RED + "No event had been participate.\n" + RESET);
    }
    
    public static void noCertainGenderVolunteer(String gender){
        System.out.println("\n" + RED + "There are no " + gender + " volunteer." + RESET);
    }
    
    public static void noCertainVolBelowAge(){
        System.out.println("\n" + RED + "No volunteer currently below or within this age." + RESET);
    }
    
    public static void noDataForReport(){
        System.out.println(RED + "No volunteer yet. No data to generate report." + RESET);
    }
}