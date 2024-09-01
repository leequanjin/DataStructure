/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.util.Scanner;

/**
 *
 * @author Lee Quan Jin
 */
public class Main {
    
    //Colours
    static String Red = "\u001b[31m";
    static String Green = "\u001b[32;2m";
    static String Reset = "\u001b[0m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        
        ManageDonor manageDonor = new ManageDonor();
        ManageDonee manageDonee = new ManageDonee();
        ManageDistribution manageDistribution = new ManageDistribution();
        
        while (!exit) {
            System.out.println("==== Donation Management System ====");
            System.out.println("1. Donor Management Subsystem");
            System.out.println("2. Donee Management Subsystem");
            System.out.println("3. Donation Management Subsystem");
            System.out.println("4. Donation Distribution Subsystem");
            System.out.println("5. Volunteer Management Subsystem");
            System.out.println("6. Event Management Subsystem");
            System.out.println("7. Exit\n");
            System.out.print("Select an option (1-7): ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {
                    manageDonor.donorMenu();
                }
                case "2" -> {
                    manageDonee.doneeMenu();
                }
                case "3" -> {
                    DonationManagement.donationManagementMainMenu();
                }
                case "4" -> {
                    manageDistribution.distributionMenu();
                }
                case "5" -> {
                    VolunteerManagement.volunteerMainMenu();
                }
                case "6" -> {
                    System.out.println("Not implemented yet");
                }
                case "7" -> {
                    exit = true;
                    System.out.println(Green + "Exiting the system. Goodbye!" + Reset);
                }
                default -> System.out.println(Red + "Invalid choice. Please select a valid option.\n" + Reset);
            }
        }

        scanner.close();
    }
}
