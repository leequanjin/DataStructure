/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EventSystem;

/**
 *
 * @author Clarist Liew
 */
public class EUtility {
    
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";
    
    
    public static void intNotInRange(int initial, int end){
        System.out.println(RED + "Invalid integer. Please enter between " + initial + " to " + end + ".\n" + RESET);
    }
    
    public static void invalidIntInput(){
        System.out.println(RED + "Invalid input. Please enter valid integer number.\n" + RESET);
    }
    
    public static void emptyInputErrorMsg(){
        System.out.println(RED +  "Input cannot be empty. Please try again.\n" + RESET);
    }
    
    public static void enterYNOnly(){
        System.out.println(RED + "Please enter Y or N only.\n" + RESET);
    }
    
    public static void invalidDateMsg() {
        System.out.println("Invalid date format. Please use dd/MM/yyyy.");
    }
    
    public static void invalidValueDate() {
        System.out.println("Invalid date format. Please use valid numbers for day, month, and year.");
    }
    
    public static void invalidMonth() {
        System.out.println("Invalid month. Please enter a month between 01 and 12.");
    }
    
    public static void invalidDay() {
        System.out.println("Invalid day for the given month. Please enter a valid day.");
    }
    
    
    

    public static void invalidTimeMsg() {
        System.out.println("Invalid time format. Please use HH:mm (24-hour format).");
    }
    
    // Successful Message
    public static void eventAddedMsg(){
        System.out.println(GREEN + "Event added successfully!" + RESET);
    }
    
    
    
}
