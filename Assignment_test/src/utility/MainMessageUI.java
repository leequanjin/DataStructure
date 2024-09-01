/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import java.util.Scanner;

/**
 *
 * @author Lee Quan Jin
 */
public class MainMessageUI {
    //Colours
    static String Red = "\u001b[31m";
    static String Green = "\u001b[32;2m";
    static String Reset = "\u001b[0m";
    
    public static String getChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Select an option (1-7): ");
        return scanner.nextLine();
    }
    
    public static void displayInvalidChoiceMessage() {
        System.out.println(Red + "Invalid option, please try again.\n" + Reset);
    }
    
    public static void displayExitMessage() {
        System.out.println(Green + "Exiting the system. Goodbye!" + Reset);
    }
}
