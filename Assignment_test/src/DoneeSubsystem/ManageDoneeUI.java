/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoneeSubsystem;

import CommonResources.LinkedList;
import CommonResources.Node;
import java.util.Scanner;

/**
 *
 * @author Lee Quan Jin
 */
public class ManageDoneeUI {

    //Colours
    static String Red = "\u001b[31m";
    static String Green = "\u001b[32;2m";
    static String Reset = "\u001b[0m";

    private static final String DONEE_PATH = "donees.txt";

    Scanner scanner = new Scanner(System.in);
    String choice = null;

// ------------    
// Main Method
// ------------ 
    
    public void main(String[] args) {
        doneeMenu();
    }
    
// ------------    
// Menu Method
// ------------ 

    public void doneeMenu() {

        boolean running = true;

        while (running) {
            displayMenuChoice();
            choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {
                    addDonee();
                }
                case "2" -> {
                    removeDonee();
                }
                case "3" -> {
                    updateDonee();
                }
                case "4" -> {
                    searchDonee();
                }
                case "5" -> {
                    listDonee();
                }
                case "6" -> {
                    filterDonee();
                }
                case "7" -> {
                    reportDonee();
                }
                case "8" -> {
                    // Exit the program
                    running = false;
                    displayExitMessage();
                }
                default -> {
                    displayInvalidChoiceMessage();
                }
            }
            System.out.println();
        }
    }

// ------------    
// Menu Choices
// ------------     

    // Add a new donee
    public void addDonee() {
        ManageDonee<Donee> doneeList = new ManageDonee<>();
        doneeList.loadFromFile(DONEE_PATH);

        displayDoneeTypeChoice();
        do {
            choice = getChoice();

            switch (choice) {
                case "1" -> {
                    String id = doneeList.generateDoneeId();
                    String name = getName();
                    String state = getState();

                    doneeList.insertAtStart(new Individual(id, name, state));
                    System.out.println(Green + "Individual added with Donee ID: " + id + Reset);
                    doneeList.saveToFile(DONEE_PATH);
                }
                case "2" -> {
                    String id = doneeList.generateDoneeId();
                    String name = getName();
                    String state = getState();

                    doneeList.insertAtStart(new Family(id, name, state));
                    System.out.println(Green + "Family added with Donee ID: " + id + Reset);
                    doneeList.saveToFile(DONEE_PATH);
                }
                case "3" -> {
                    String id = doneeList.generateDoneeId();
                    String name = getName();
                    String state = getState();

                    doneeList.insertAtStart(new Organization(id, name, state));
                    System.out.println(Green + "Organization added with Donee ID: " + id + Reset);
                    doneeList.saveToFile(DONEE_PATH);
                }
                default -> {
                    displayInvalidChoiceMessage();
                }
            }
        } while (!"1".equals(choice) && !"2".equals(choice) && !"3".equals(choice));
    }

    // Remove a donee
    public void removeDonee() {

        ManageDonee<Donee> doneeList = new ManageDonee<>();
        doneeList.loadFromFile(DONEE_PATH);

        String id = getDoneeID();
        Donee doneeToRemove = doneeList.findById(id);
        if (doneeToRemove != null) {
            doneeList.deleteById(id);
            System.out.println(Green + "Donee (" + doneeToRemove.getName() + ") was removed successfully." + Reset);
            doneeList.saveToFile(DONEE_PATH);
        } else {
            displayInvalidDoneeIdMessage();
        }
    }

    // Update donee details
    public void updateDonee() {
        ManageDonee<Donee> doneeList = new ManageDonee<>();
        doneeList.loadFromFile(DONEE_PATH);

        String id = getDoneeID();
        Donee doneeToUpdate = doneeList.findById(id);
        if (doneeToUpdate != null) {
            displayFoundDonee(doneeToUpdate);
            displayUpdateDoneeChoice();

            choice = getChoice();
            switch (choice) {
                // Update Name
                case "1" -> {
                    String newName = getName();
                    doneeToUpdate.setName(newName);
                    System.out.println(Green + "Name updated to " + newName + " successfully." + Reset);
                    doneeList.saveToFile(DONEE_PATH);
                }
                // Update Category
                case "2" -> {
                    displayDoneeTypeChoice();
                    do {
                        choice = getChoice();

                        switch (choice) {
                            case "1" -> {
                                doneeList.replace(doneeToUpdate, doneeList.changeToIndividual(doneeToUpdate));
                                System.out.println(Green + "Category updated to individual successfully." + Reset);
                                doneeList.saveToFile(DONEE_PATH);
                            }
                            case "2" -> {
                                doneeList.replace(doneeToUpdate, doneeList.changeToFamily(doneeToUpdate));
                                System.out.println(Green + "Category updated to family successfully." + Reset);
                                doneeList.saveToFile(DONEE_PATH);
                            }
                            case "3" -> {
                                doneeList.replace(doneeToUpdate, doneeList.changeToOrganization(doneeToUpdate));
                                System.out.println(Green + "Category updated to organization successfully." + Reset);
                                doneeList.saveToFile(DONEE_PATH);
                            }

                            default -> {
                                displayInvalidChoiceMessage();
                            }
                        }
                    } while (!"1".equals(choice) && !"2".equals(choice) && !"3".equals(choice));
                }
                // Update Location
                case "3" -> {
                    displayStateChoice();
                    String state = getState();
                    doneeToUpdate.setLocation(state);
                    System.out.println(Green + "State updated to " + state + " successfully." + Reset);
                    doneeList.saveToFile(DONEE_PATH);
                }
                // Update Status
                case "4" -> {
                    displayStatusChoice();
                    choice = getChoice();

                    switch (choice) {
                        case "1" -> {
                            String status = "Active";
                            doneeToUpdate.setStatus(status);
                            System.out.println(Green + "Status updated to " + status + " successfully." + Reset);
                            doneeList.saveToFile(DONEE_PATH);
                        }
                        case "2" -> {
                            String status = "Pending Distribution";
                            doneeToUpdate.setStatus(status);
                            System.out.println(Green + "Status updated to " + status + " successfully." + Reset);
                            doneeList.saveToFile(DONEE_PATH);
                        }
                        case "3" -> {
                            String status = "Completed";
                            doneeToUpdate.setStatus(status);
                            System.out.println(Green + "Status updated to " + status + " successfully." + Reset);
                            doneeList.saveToFile(DONEE_PATH);
                        }
                        default ->
                            System.out.println(Red + "Invalid option. No changes made." + Reset);
                    }
                }
                default ->
                    displayInvalidChoiceMessage();
            }
        } else {
            displayInvalidDoneeIdMessage();
        }
    }

    // Search for a specific donee
    public void searchDonee() {
        ManageDonee<Donee> doneeList = new ManageDonee<>();
        doneeList.loadFromFile(DONEE_PATH);

        do {
            displaySearchChoice();
            choice = getChoice();

            switch (choice) {
                case "1" -> {
                    String id = getDoneeID();
                    Donee donee = doneeList.findById(id);
                    if (donee != null) {
                        displayFoundDonee(donee);
                    } else {
                        displayInvalidDoneeIdMessage();
                    }
                }
                case "2" -> {
                    String name = getName().toLowerCase();

                    LinkedList<Donee> matchingDonees = new LinkedList<>();
                    Node<Donee> current = doneeList.head;

                    while (current != null) {
                        if (current.data.getName().toLowerCase().contains(name)) {
                            matchingDonees.insertAtStart(current.data);
                        }
                        current = current.next;
                    }

                    if (!matchingDonees.isEmpty()) {
                        System.out.println(Green + "\nMatching donees found: " + Reset);
                        displayDoneeList(matchingDonees);
                    } else {
                        displayInvalidDoneeNameMessage(name);
                    }
                }
                default ->
                    displayInvalidChoiceMessage();
            }
        } while (!"1".equals(choice) && !"2".equals(choice));
    }

    // List donees with all donations made
    public void listDonee() {
        ManageDonee<Donee> doneeList = new ManageDonee<>();
        doneeList.loadFromFile(DONEE_PATH);

        System.out.println(Green + "List of all Donees: " + Reset);
        displayDoneeList(doneeList);
    }

    // Filter donee based on criteria
    public void filterDonee() {
        ManageDonee<Donee> doneeList = new ManageDonee<>();
        doneeList.loadFromFile(DONEE_PATH);

        displayFilterChoice();
        do {
            choice = getChoice();

            switch (choice) {
                case "1" -> {
                    LinkedList<Individual> individualList = doneeList.filterByCategory(Individual.class);
                    System.out.println(Green + "\nList of all Individual Donees: " + Reset);
                    displayDoneeList(individualList);
                }
                case "2" -> {
                    LinkedList<Family> familyList = doneeList.filterByCategory(Family.class);
                    System.out.println(Green + "\nList of all Family Donees: " + Reset);
                    displayDoneeList(familyList);
                }
                case "3" -> {
                    LinkedList<Organization> organizationList = doneeList.filterByCategory(Organization.class);
                    System.out.println(Green + "\nList of all Organization Donees: " + Reset);
                    displayDoneeList(organizationList);
                }
                default -> {
                    displayInvalidChoiceMessage();
                }
            }
        } while (!"1".equals(choice) && !"2".equals(choice) && !"3".equals(choice));
    }

    // Generate donee summary report
    public void reportDonee() {
        ManageDonee<Donee> doneeList = new ManageDonee<>();
        doneeList.loadFromFile(DONEE_PATH);

        displayReportChoice();
        do {
            choice = getChoice();

            switch (choice) {
                case "1" -> {
                    LinkedList doneeYearCountList = doneeList.generateTotalDoneeByYear();
                    System.out.println(Green + "\nNumber of new donees by year report: " + Reset);
                    displayPeriodCountReport(doneeYearCountList);
                }
                case "2" -> {
                    LinkedList doneeMonthCountList = doneeList.generateTotalDoneeByMonth();
                    System.out.println(Green + "\nNumber of new donees by month report: " + Reset);
                    displayPeriodCountReport(doneeMonthCountList);
                }
                case "3" -> {
                    DoneeStateCount doneeStateCount = doneeList.generateTotalDoneeByState();
                    displayStateCountReport(doneeStateCount);
                }

                default -> {
                    displayInvalidChoiceMessage();
                }
            }
        } while (!"1".equals(choice) && !"2".equals(choice) && !"3".equals(choice));
    }

// ---------    
// Utilities
// --------- 
    
    public boolean isEmpty(String string) {
        return string == null;
    }

    public void displayInvalidChoiceMessage() {
        System.out.println(Red + "Invalid option, please try again." + Reset);
    }

    public void displayInvalidDoneeIdMessage() {
        System.out.println(Red + "Invalid ID. No donee found with the given ID." + Reset);
    }

    public void displayInvalidDoneeNameMessage(String name) {
        System.out.println(Red + "\nNo donees found with name containing: " + name + Reset);
    }

    public void displayExitMessage() {
        System.out.println(Green + "Exiting donee menu..." + Reset);
    }

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
        while (isEmpty(name)) {
            System.out.print("Enter name: ");
            name = scanner.nextLine().trim();
            if (isEmpty(name)) {
                System.out.println(Red + "Name cannot be empty!" + Reset);
            }
        }
        return name;
    }

    public String getState() {
        displayStateChoice();
        String state = null;
        do {
            System.out.print("Select your State: ");
            state = scanner.nextLine();
            switch (state) {
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
                    displayInvalidChoiceMessage();
                }
            }
        } while (!state.equals("1")
                && !state.equals("2")
                && !state.equals("3")
                && !state.equals("4")
                && !state.equals("5")
                && !state.equals("6")
                && !state.equals("7")
                && !state.equals("8")
                && !state.equals("9")
                && !state.equals("10")
                && !state.equals("11")
                && !state.equals("12")
                && !state.equals("13"));
        return state;
    }

    public void displayMenuChoice() {
        System.out.println("Choose an option:");
        System.out.println("1. Add a new donee");
        System.out.println("2. Remove a donee");
        System.out.println("3. Update donee details");
        System.out.println("4. Search donee details");
        System.out.println("5. List donees");
        System.out.println("6. Filter donee based on category");
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
        System.out.println("2. Category");
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

    public void displayDoneeList(LinkedList doneeList) {
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

    public void displayPeriodCountReport(LinkedList doneePeriodCountList) {
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
