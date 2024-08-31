/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DonationManagementSubsystem;

/**
 *
 * @author Asus
 */
public class DMUtility {
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
    
    public static void addFunctionDown(){
        System.out.println(RED + "Add function cannot run without existance of donor." + RESET);
    }
    
    // no exist
    public static void donorNoExist(){
        System.out.println("\n" + RED + "Donor does not exist." + RESET);
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
        System.out.println("\n" + RED + "The input cannot be less than or equal to zero." + RESET);
    }
    
    public static void invalidIDFormat(String constrant){
        System.out.println(RED + "Invalid format. Volunteer Id format should be " + constrant.toUpperCase() + "00000.\n" + RESET);
    }
    
    public static void invalidMenuSelection(){
        System.out.println(RED + "Invalid menu selection.\n" + RESET);
    }
    
    // item added 
    public static void itemAdded(){
        System.out.println("\n" + GREEN + "Items added successfully" + RESET);
    }
}
