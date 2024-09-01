/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;


/**
 *
 * @author Heng Pei Lin
 */
public class DonationManagementUtility {
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
    
    // no data in list
    public static void noDonorInList(){
        System.out.println("\n" + RED + "No donor yet." + RESET);
    }
    
    public static void noItemInList(){
        System.out.println("\n" + RED + "No item donated yet." + RESET);
    }
    
    public static void noSuchItem(){
        System.out.println(RED + "No item exist yet." + RESET);
    }
    
    public static void addFunctionDown(){
        System.out.println(RED + "Add function cannot run without existance of donor." + RESET);
    }
    
    // no exist
    public static void donorNoExist(){
        System.out.println("\n" + RED + "Donor does not exist." + RESET);
    }
    
    public static void itemNoExist(){
        System.out.println("\n" + RED + "Item does not exist." + RESET);
    }
    
    public static void noMoney(){
        System.out.println("\n" + RED + "No any money on cash or bank." + RESET);
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
    
    public static void intCannotNeg(){
        System.out.println("\n" + RED + "The input cannot be less than or equal to 0." + RESET);
    }
    
    public static void invalidIDFormat(String constrant){
        System.out.println(RED + "Invalid format. Volunteer Id format should be " + constrant.toUpperCase() + "00000.\n" + RESET);
    }
    
    public static void invalidID(){
        System.out.println(RED + "Invalid ID." + RESET);
    }
    
    public static void invalidMenuSelection(){
        System.out.println(RED + "Invalid menu selection.\n" + RESET);
    }
    
    // item added 
    public static void itemAdded(){
        System.out.println("\n" + GREEN + "Items added successfully" + RESET);
    }
    
    public static void invalidInputType(String string){
        System.out.println(RED + "Invalid input. Please enter correct " + string + ".\n" + RESET);
    }
    
    // amount error msg
    public static void invalidAmt(){
        System.out.println(RED + "Invalid amount. Donated amount could not be 0 or less than 0.\n" + RESET);
    }
    
    // qty error msg
    public static void invalidQtyZ(){
        System.out.println(RED + "Quantity cannot be 0.\n" + RESET);
    }
    
    public static void invalidQtyNeg(){
        System.out.println(RED + "Quantity cannot be negative.\n" + RESET);
    }
    
    // food date error msg
    public static void foodExpired(){
        System.out.println(RED + "The food had expired.\n" + RESET);
    }
    
    public static void invalidDateFormat(){
        System.out.println(RED + "Invalid date format. Please enter the date in dd/MM/yyyy format.\n" + RESET);
    }
    
    // weight error msg
    public static void invalidWZ(){
        System.out.println(RED + "Weight cannot be 0.\n" + RESET);
    }
    
    public static void invalidWNeg(){
        System.out.println(RED + "Weight cannot be negative.\n" + RESET);
    }
    
    // remove successfully
    public static void itemRemove(){
        System.out.println("\n" + GREEN + "Item remove successfully." + RESET);
    }
    
    public static void itemRemoveFail(){
        System.out.println("\n" + RED + "Item remove unsuccessfully." + RESET);
    }
    
    // amend successfully
    public static void itemUpdated(){
        System.out.println("\n" + GREEN + "Item updated Successfully.\n" + RESET);
    }
    
    public static void noDonorOrItem(){
        System.out.println("No donors or items to process.");
    }
    
    public static void invalidFilterTypeInput(){
        System.out.println("\n" + RED + "Invalid input." + RESET);
    }
    
    public static void noItemToFilter(){
        System.out.println(RED + "No items to filter." + RESET);
    }
    
    public static void inputValidYear(){
        System.out.println(RED + "Invalid input. Please enter a valid year.\n" + RESET);
    }
    
    public static void inputFutureYear(){
        System.out.println(RED + "Invalid year, please enter the current year or a future year.\n" + RESET);
    }
     
    public static void noFoodItem(){
        System.out.println(RED + "No food item exist." + RESET);
    }
}