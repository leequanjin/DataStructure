/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import entity.Donee.Donee;
import entity.Donee.DoneeStateCount;
import adt.LinkedListInterface;
import java.util.Scanner;
import utility.DoneeMessageUI;

/**
 *
 * @author Christopher Yap Jian Xing
 */
public class ManageDoneeUI {

    //Colours
    static String Red = "\u001b[31m";
    static String Green = "\u001b[32;2m";
    static String Blue = "\u001B[34m";
    static String Reset = "\u001b[0m";
    
    Scanner scanner = new Scanner(System.in);
    String choice = null;
    
    DoneeMessageUI doneeMessageUI = new DoneeMessageUI();

// --------       
// Boundary
// --------    
    
    public String getChoice() {
        System.out.print("Enter your choice: ");
        choice = scanner.nextLine();
        System.out.println("");

        return choice;
    }

    public String getDoneeID() {
        System.out.print("Enter donee ID: ");
        String id = scanner.nextLine();
        return id;
    }

    public String getName() {
        String name = null;
        do {            
            System.out.print("Enter name: ");
            name = scanner.nextLine().trim();
            if (name.isBlank()) {
                System.out.println(Red + "Name cannot be empty!" + Reset);
            }
        } while (name.isBlank());
        return name;
    }

    public String getState() {
        displayStateChoice();
        String state = null;
        do {
            System.out.print("Select your State: ");
            choice = scanner.nextLine();
            switch (choice) {
                case "1" -> {
                    state = "Johor";
                }
                case "2" -> {
                    state = "Kedah";
                }
                case "3" -> {
                    state = "Kelantan";
                }
                case "4" -> {
                    state = "Melaka";
                }
                case "5" -> {
                    state = "Negeri Sembilan";
                }
                case "6" -> {
                    state = "Pahang";
                }
                case "7" -> {
                    state = "Penang";
                }
                case "8" -> {
                    state = "Perak";
                }
                case "9" -> {
                    state = "Perlis";
                }
                case "10" -> {
                    state = "Sabah";
                }
                case "11" -> {
                    state = "Sarawak";
                }
                case "12" -> {
                    state = "Selangor";
                }
                case "13" -> {
                    state = "Terengganu";
                }
                default -> {
                    doneeMessageUI.displayInvalidChoiceMessage();
                }
            }
        } while (!choice.equals("1")
                && !choice.equals("2")
                && !choice.equals("3")
                && !choice.equals("4")
                && !choice.equals("5")
                && !choice.equals("6")
                && !choice.equals("7")
                && !choice.equals("8")
                && !choice.equals("9")
                && !choice.equals("10")
                && !choice.equals("11")
                && !choice.equals("12")
                && !choice.equals("13"));
        return state;
    }

    public void displayMenuChoice() {
        System.out.println(Blue + "\n- - - Donor Menu - - -" + Reset);
        System.out.println("Choose an option:");
        System.out.println("1. Add a new donee");
        System.out.println("2. Remove a donee");
        System.out.println("3. Update donee details");
        System.out.println("4. Search donee details");
        System.out.println("5. List donees");
        System.out.println("6. Filter donee based on type");
        System.out.println("7. Generate Summary Report");
        System.out.println("8. Exit \n");
    }

    public void displayDoneeTypeChoice() {
        System.out.println("Enter donee type: ");
        System.out.println("1. Individual ");
        System.out.println("2. Family ");
        System.out.println("3. Organization \n");
    }

    public void displayStateChoice() {
        String line = String.format("-").repeat(64);
        System.out.println(line);
        System.out.printf("|%-20s|%-20s|%-20s|\n|%-20s|%-20s|%-20s|\n|%-20s|%-20s|%-20s|\n|%-20s|%-20s|%-20s|\n|%-20s|%-20s|%-20s|\n",
                "1. Johor",
                "2. Kedah",
                "3. Kelantan",
                "4. Melaka",
                "5. Negeri Sembilan",
                "6. Pahang",
                "7. Penang",
                "8. Perak",
                "9. Perlis",
                "10. Sabah",
                "11. Sarawak",
                "12. Selangor",
                "13. Terengganu", "", "");
        System.out.println(line);
    }

    public void displayFoundDonee(Donee doneeToUpdate) {
        System.out.println(Green + "\nFound donee: " + Reset);
        System.out.printf("%-15s |%-30s |%-15s |%-15s |%-20s |%s\n", "ID", "Name", "Type", "Location", "Registration Date", "Status");
        String line = String.format("-").repeat(125);
        System.out.println(line);
        System.out.println(doneeToUpdate.toString() + "\n");
    }

    public void displayUpdateDoneeChoice() {
        System.out.println("What would you like to update?");
        System.out.println("1. Name");
        System.out.println("2. Type");
        System.out.println("3. Location");
        System.out.println("4. Status \n");
    }

    public void displayStatusChoice() {
        System.out.println("\nSelect new status");
        System.out.println("1. Active"); // eligible to receive donations
        System.out.println("2. Pending Distribution"); // distributed but haven't received donations
        System.out.println("3. Completed\n"); // received donations
    }

    public void displaySearchChoice() {
        System.out.println("Search Donor by:");
        System.out.println("1. Donee ID");
        System.out.println("2. Donee Name \n");
    }

    public void displayDoneeList(LinkedListInterface doneeList) {
        System.out.printf("%-15s |%-30s |%-15s |%-15s |%-20s |%s\n", "ID", "Name", "Type", "Location", "Registration Date", "Status");
        String line = String.format("-").repeat(125);
        System.out.println(line);
        System.out.println(doneeList.show());
    }

    public void displayFilterChoice() {
        System.out.println("1. Filter by Individual");
        System.out.println("2. Filter by Family");
        System.out.println("3. Filter by Organization \n");
    }

    public void displayReportChoice() {
        System.out.println("1. Number of new donees by year");
        System.out.println("2. Number of new donees by month");
        System.out.println("3. Number of donees by state \n");
    }

    public void displayPeriodCountReport(LinkedListInterface doneePeriodCountList) {
        System.out.printf("%-15s |%-20s |%-20s |%s\n", "Year", "Individuals", "Families", "Organizations");
        String line = String.format("-").repeat(74);
        System.out.println(line);
        System.out.println(doneePeriodCountList.show());
    }

    public void displayStateCountReport(DoneeStateCount doneeStateCount) {
        System.out.println(Green + "\nNumber of donees by state: " + Reset);
        String line = String.format("-").repeat(25);
        System.out.println(line);
        System.out.println(doneeStateCount.toString());
    }
}
