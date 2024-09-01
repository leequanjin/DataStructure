/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import entity.Donor.Donor;
import adt.LinkedList;
import java.util.Scanner;
import utility.DonorMessageUI;

/**
 *
 * @author Kee Ke Shen
 */
public class ManageDonorUI {

    //Colours
    static String Red = "\u001b[31m";
    static String Blue = "\u001B[34m";
    static String Green = "\u001b[32;2m";
    static String Reset = "\u001b[0m";

    //Imports
    DonorMessageUI donorMessageUI = new DonorMessageUI();

    Scanner scanner = new Scanner(System.in);
    String choice = null;

// --------       
// Boundary
// --------    
    public String getChoice() {
        System.out.print("Enter your choice: ");
        choice = scanner.nextLine();
        System.out.println("");

        return choice;
    }

    public String getDonorID() {
        System.out.print("Enter Donor ID: ");
        String id = scanner.nextLine();
        return id;
    }

    public String getRemoveDonorID() {
        System.out.print("\nEnter Donor ID to remove: ");
        String id = scanner.nextLine();
        return id;
    }

    public String getUpdateDonorID() {
        System.out.print("Enter Donor ID to update: ");
        String id = scanner.nextLine();
        return id;
    }

    public String getCategory() {
        String category = null;
        boolean validCategory = false;
        String[] validCategories = {"Government", "Private", "Public"};
        while (!validCategory) {
            System.out.print("Enter donor category (Government, Private, Public): ");
            category = scanner.nextLine().trim();

            for (String valid : validCategories) {
                if (valid.equalsIgnoreCase(category)) {
                    validCategory = true;
                    break;
                }
            }

            if (!validCategory) {
                System.out.println(Red + "Invalid category. Please enter Government, Private, or Public." + Reset);
            }
        }

        return category;
    }

    public String getName() {
        String name = null;
        while (donorMessageUI.isEmpty(name)) {
            System.out.print("Enter Donor Name: ");
            name = scanner.nextLine().trim();
            if (donorMessageUI.isEmpty(name)) {
                System.out.println(Red + "Name cannot be empty!" + Reset);
            }
        }
        return name;
    }

    public void displayMenuChoice() {
        System.out.println(Blue + "\n- - - Donor Menu - - -" + Reset);
        System.out.println("Choose an option:");
        System.out.println("1. Add a new donor");
        System.out.println("2. Remove a donor");
        System.out.println("3. Update donor details");
        System.out.println("4. Search donor details");
        System.out.println("5. List donors");
        System.out.println("6. Filter donors based on category");
        System.out.println("7. Generate Summary Report");
        System.out.println("8. Exit\n");
    }

    public void displayDonorTypeChoice() {
        System.out.println("Enter donor type: ");
        System.out.println("1. Individual ");
        System.out.println("2. Organization \n");
    }

    public void displayFoundDonor(Donor donorToUpdate) {
        System.out.println(Green + "\nFound donor: " + Reset);
        System.out.printf("%-15s |%-30s |%-15s |%-15s |%s\n", "ID", "Name", "Type", "Category", "Registration Date");
        String line = String.format("-").repeat(110);
        System.out.println(line);
        System.out.println(donorToUpdate.toString() + "\n");
    }

    public void displayUpdateDonorChoice() {
        System.out.println("What would you like to update?");
        System.out.println("1. Name");
        System.out.println("2. Type");
        System.out.println("3. Category \n");
    }

    public void displaySearchChoice() {
        System.out.println(Blue + "\n- - - Search Donor - - -" + Reset);
        System.out.println("Search Donor by:");
        System.out.println("1. Donor ID");
        System.out.println("2. Donor Name \n");
    }

    public void displayDonorList(LinkedList donorList) {
        System.out.println(Blue + "\n- - - Donor List - - -" + Reset);
        System.out.printf("%-15s |%-30s |%-15s |%-15s |%s\n", "ID", "Name", "Type", "Category", "Registration Date");
        String line = String.format("-").repeat(110);
        System.out.println(line);
        System.out.println(donorList.show());
    }

    public void displayFilterChoice() {
        System.out.println(Blue + "\n- - - Filter Donor - - -" + Reset);
        System.out.println("1. Filter by Individual");
        System.out.println("2. Filter by Organization");
        System.out.println("3. Donor Category\n");
    }

    public void displayCategoryFilterChoice() {
        System.out.println("Filter by category:");
        System.out.println("1. Government");
        System.out.println("2. Private");
        System.out.println("3. Public");
    }

    public void displayReportChoice() {
        System.out.println(Blue + "\n- - - Report Menu - - -" + Reset);
        System.out.println("1. Generate Total Donors By Year Report");
        System.out.println("2. Generate Donors by Category Report");
        System.out.println("3. Generate Donor Contribution Report\n");
    }

    public void displayPeriodCountReport(LinkedList donorPeriodCountList) {
        System.out.printf("%-15s |%-20s |%s\n", "Year", "Individuals", "Organizations");
        String line = String.format("-").repeat(74);
        System.out.println(line);
        System.out.println(donorPeriodCountList.show());
    }

}
