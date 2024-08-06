/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DonationManagementSubsystem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DonationManagement {
    
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        System.out.println(ANSI_BLUE + " - - - Donation Management - - - " + ANSI_RESET);
        System.out.println("1. Add a new donation\n"
                + "2. Remove a donation\n"
                + "3. Search donation details\n"
                + "4. Amend donation details\n"
                + "5. Track donated item in categories\n"
                + "6. List donation by different donor\n"
                + "7. List all donation\n"
                + "8. Filter donation base on criteria\n"
                + "9. Generate sumary report\n");
        System.out.print("Enter your choice: ");
        
        int dmChoice = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                dmChoice = scan.nextInt();
                scan.nextLine();

                if (dmChoice < 1 || dmChoice > 9) {
                    System.out.println(ANSI_RED + "Please enter a number between 1 and 9.\n" + ANSI_RESET);
                    System.out.print("Enter again: ");
                } else {
                    validInput = true; // Valid input received, exit loop
                }

            } catch (InputMismatchException e) {
                System.out.println(ANSI_RED + "Invalid input. Please enter an integer number.\n" + ANSI_RESET);
                scan.nextLine(); 
                System.out.print("Enter again: ");
            }
        }

        switch (dmChoice) {
            case 1:
                addDonation();
                break;
            case 2:
                remDonation();
                break;
            case 3:
                searchDonation();
                break;
            case 4:
                amendDonation();
                break;
            case 5:
                trackItemByCategory();
                break;
            case 6:
                listByDiffDonor();
                break;
            case 7:
                listAll();
                break;
            case 8:
                filterDonation();
                break;
            case 9:
                report();
                break;
            default:
                System.out.println("Invalid choice.");
        }
        
    }
    
    public static void addDonation(){
    
    }
    
    public static void remDonation(){}
    
    public static void searchDonation(){}
    
    public static void amendDonation(){}
    
    public static void trackItemByCategory(){}
    
    public static void listByDiffDonor(){}
    
    public static void listAll(){}
    
    public static void filterDonation(){}
    
    public static void report(){}
    
}
