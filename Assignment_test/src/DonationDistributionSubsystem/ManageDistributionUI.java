/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DonationDistributionSubsystem;

import adt.LinkedList;
import adt.Node;
import DonationList.Item;
import DonationManagementSubsystem.DMControl;
import entity.Donee;
import control.DoneeLinkedList;
import java.util.Scanner;

/**
 *
 * @author Christopher Yap Jian Xing
 */
public class ManageDistributionUI {

    private static final String DONATION_DISTRIBUTION_PATH = "donation_distribution.txt";
    private static final String DONEE_PATH = "donees.txt";
    private static final String BANK_PATH = "bank.txt";
    private static final String CASH_PATH = "cash.txt";
    private static final String BAKED_PATH = "bakedFood.txt";
    private static final String BOXED_PATH = "boxedFood.txt";
    private static final String CANNED_PATH = "cannedFood.txt";
    private static final String DRY_PATH = "dryFood.txt";
    private static final String ESS_PATH = "essential.txt";
    private static final String JACKET_PATH = "jacket.txt";
    private static final String PANT_PATH = "pant.txt";
    private static final String SHIRT_PATH = "shirt.txt";
    private static final String SHOES_PATH = "shoes.txt";
    private static final String SOCKS_PATH = "socks.txt";

    //Colours
    static String Red = "\u001b[31m";
    static String Green = "\u001b[32;2m";
    static String Reset = "\u001b[0m";
    

    public static void main(String[] args) {
        distributionMenu();
    }
    
    private static void distributionMenu() {
        Scanner scanner = new Scanner(System.in);
        
        boolean running = true;

        while (running) {
            System.out.println("Choose an option:");
            System.out.println("1. Add new donation distribution");
            System.out.println("2. Delete donation distribution");
            System.out.println("3. Update donation distribution status"); // update status
            System.out.println("4. Monitor/track distributed items "); // check pending / check success 
            System.out.println("5. List all distributed donations");
            System.out.println("6. Generate Summary Report"); // which donee receive most item
            System.out.println("7. Exit");
            System.out.print("\nEnter your choice: ");

            String choice = scanner.nextLine();
            System.out.println("");

            switch (choice) {
                case "1" -> {
                    addDistribution();
                }
                case "2" -> {
                    deleteDistribution();
                }
                case "3" -> {
                    updateDistribution();
                }
                case "4" -> {
                    monitorDistribution();
                }
                case "5" -> {
                    listDistribution();
                }
                case "6" -> {
                    reportDistribution();
                }
                case "7" -> {
                    // Exit the program
                    running = false;
                    System.out.println(Green + "Exiting..." + Reset);
                }
                default -> {
                    System.out.println(Red + "Invalid option, please try again." + Reset);
                }
            }
        }
    }

    private static void addDistribution() {
        Scanner scanner = new Scanner(System.in);
        ManageDistribution<Distribution> distributionList = new ManageDistribution<>();
        DoneeLinkedList<Donee> doneeList = new DoneeLinkedList<>();

        distributionList.loadFromFile(DONATION_DISTRIBUTION_PATH);
        doneeList.loadFromFile(DONEE_PATH);

        LinkedList<Item> allItemList = DMControl.loadAllItemIntoList();

        ManageItems<Item> distributionItemList = new ManageItems<>();
        String filePath = null;
        String choice = null;

        System.out.print("Enter Donee ID: ");
        String doneeId = scanner.nextLine();
        Donee donee = doneeList.findById(doneeId);
        if (donee != null) {
            String doneeName = donee.getName();

            if (donee.getStatus().equals("Active")) {
                System.out.println(Green + "Found donee: " + doneeName + "\n" + Reset);

                do {
                    System.out.print("Enter Donation Item ID: ");
                    String itemId = scanner.nextLine();

                    if (itemId.length() != 7) {
                        System.out.println(Red + "Invalid length. The length should be 7 and format AA00000." + Reset);
                    } else {

                        Item item = DMControl.searchByID(allItemList, itemId);
                        if (item == null) {
                            System.out.println(Red + "Donation Item not found." + Reset);
                        } else if (item.getAvailability().equals("Unavailable")) {
                            System.out.println(Red + "Donation Item is not available." + Reset);
                        } else {
                            distributionItemList.insert(item);

                            itemId = itemId.substring(0, 2).toUpperCase() + itemId.substring(2, 7);
                            String prefix = itemId.substring(0, 2);

                            switch (prefix) {
                                case "MB" ->
                                    filePath = BANK_PATH;
                                case "MC" ->
                                    filePath = CASH_PATH;
                                case "FA" ->
                                    filePath = BAKED_PATH;
                                case "FO" ->
                                    filePath = BOXED_PATH;
                                case "FC" ->
                                    filePath = CANNED_PATH;
                                case "FD" ->
                                    filePath = DRY_PATH;
                                case "FE" ->
                                    filePath = ESS_PATH;
                                case "AJ" ->
                                    filePath = JACKET_PATH;
                                case "AP" ->
                                    filePath = PANT_PATH;
                                case "AI" ->
                                    filePath = SHIRT_PATH;
                                case "AO" ->
                                    filePath = SHOES_PATH;
                                case "AS" ->
                                    filePath = SOCKS_PATH;
                                default ->
                                    System.out.println("Error");
                            }

                            ManageItems<Item> itemList = new ManageItems<>();

                            itemList.loadFromFile(filePath);
                            itemList.changeStatus(itemId, "Unavailable");
                            itemList.saveToFile(filePath);

                            System.out.print("Continue adding items? (Y/N): ");
                            choice = scanner.nextLine().trim();

                            if (!choice.equals("Y") && !choice.equals("N")) {
                                System.out.println(Red + "Only enter 'Y' or 'N'." + Reset);
                            }
                        }
                    }
                } while (!choice.equals("N"));

                doneeList.changeStatus(doneeId, "Pending Distribution");
                doneeList.saveToFile(DONEE_PATH);

                String id = distributionList.generateDistributionId();
                Distribution donationDistribution = new Distribution(id, doneeId, doneeName, distributionItemList);
                distributionList.insertAtStart(donationDistribution);

                System.out.println(Green + "Donation Distribution added with ID: " + id + Reset);
                distributionList.saveToFile(DONATION_DISTRIBUTION_PATH);

                System.out.println();
            } else {
                System.out.println(Red + "Donee (" + doneeName + ") is not eligible to accept new donations currently. Returning to donation distribution menu...\n" + Reset);
            }
        } else {
            System.out.println(Red + "Donee not found. Returning to donation distribution menu...\n" + Reset);
        }
    }

    private static void deleteDistribution() {
        Scanner scanner = new Scanner(System.in);
        ManageDistribution<Distribution> distributionList = new ManageDistribution<>();
        DoneeLinkedList<Donee> doneeList = new DoneeLinkedList<>();

        distributionList.loadFromFile(DONATION_DISTRIBUTION_PATH);
        doneeList.loadFromFile(DONEE_PATH);

        System.out.print("Enter Distribution ID to remove: ");
        String id = scanner.nextLine();

        Distribution distributionToRemove = distributionList.findById(id);
        if (distributionToRemove != null) {
            distributionList.removeEntry(distributionToRemove);

            String doneeId = distributionToRemove.getDoneeId();
            doneeList.changeStatus(doneeId, "Active");
            doneeList.saveToFile(DONEE_PATH);

            LinkedList<Item> distributionItemList = new ManageItems<>();
            ManageItems<Item> itemList = new ManageItems<>();
            String filePath = null;

            distributionItemList = distributionToRemove.getDonations();

            Node<Item> current = distributionItemList.head;

            while (current != null) {
                String itemId = current.data.getId();
                String prefix = itemId.substring(0, 2);

                switch (prefix) {
                    case "MB" ->
                        filePath = BANK_PATH;
                    case "MC" ->
                        filePath = CASH_PATH;
                    case "FA" ->
                        filePath = BAKED_PATH;
                    case "FO" ->
                        filePath = BOXED_PATH;
                    case "FC" ->
                        filePath = CANNED_PATH;
                    case "FD" ->
                        filePath = DRY_PATH;
                    case "FE" ->
                        filePath = ESS_PATH;
                    case "AJ" ->
                        filePath = JACKET_PATH;
                    case "AP" ->
                        filePath = PANT_PATH;
                    case "AI" ->
                        filePath = SHIRT_PATH;
                    case "AO" ->
                        filePath = SHOES_PATH;
                    case "AS" ->
                        filePath = SOCKS_PATH;
                    default ->
                        System.out.println("Error");
                }

                itemList.loadFromFile(filePath);
                itemList.changeStatus(itemId, "Available");
                itemList.saveToFile(filePath);
                current = current.next;
            }

            System.out.println(Green + "Distribution (" + distributionToRemove.getId() + ") was removed successfully." + Reset);
            distributionList.saveToFile(DONATION_DISTRIBUTION_PATH);
        } else {
            System.out.println(Red + "Invalid ID. No distribution found with the given ID.\n" + Reset);
        }
    }

    private static void updateDistribution() {
        Scanner scanner = new Scanner(System.in);
        ManageDistribution<Distribution> distributionList = new ManageDistribution<>();
        DoneeLinkedList<Donee> doneeList = new DoneeLinkedList<>();

        distributionList.loadFromFile(DONATION_DISTRIBUTION_PATH);
        doneeList.loadFromFile(DONEE_PATH);

        System.out.print("Enter Distribution ID to update: ");
        String id = scanner.nextLine();
        Distribution distribution = distributionList.findById(id);
        if (distribution != null) {

            System.out.println("Update status to:");
            System.out.println("1. Completed");
            System.out.println("2. Cancelled");
            System.out.println("3. In Progress");

            String choice = null;
            do {
                System.out.print("\nEnter your choice: ");

                choice = scanner.nextLine();
                System.out.println("");

                switch (choice) {
                    case "1" -> {
                        String status = "Completed";
                        distribution.setStatus(status);
                        System.out.println(Green + "Distribution (" + distribution.getId() + ") status was updated to " + status + " successfully." + Reset);
                        distributionList.saveToFile(DONATION_DISTRIBUTION_PATH);

                        String doneeId = distribution.getDoneeId();
                        doneeList.changeStatus(doneeId, "Completed");
                        doneeList.saveToFile(DONEE_PATH);

                        LinkedList<Item> distributionItemList = new ManageItems<>();
                        ManageItems<Item> itemList = new ManageItems<>();
                        String filePath = null;

                        distributionItemList = distribution.getDonations();

                        Node<Item> current = distributionItemList.head;

                        while (current != null) {
                            String itemId = current.data.getId();
                            String prefix = itemId.substring(0, 2);

                            switch (prefix) {
                                case "MB" ->
                                    filePath = BANK_PATH;
                                case "MC" ->
                                    filePath = CASH_PATH;
                                case "FA" ->
                                    filePath = BAKED_PATH;
                                case "FO" ->
                                    filePath = BOXED_PATH;
                                case "FC" ->
                                    filePath = CANNED_PATH;
                                case "FD" ->
                                    filePath = DRY_PATH;
                                case "FE" ->
                                    filePath = ESS_PATH;
                                case "AJ" ->
                                    filePath = JACKET_PATH;
                                case "AP" ->
                                    filePath = PANT_PATH;
                                case "AI" ->
                                    filePath = SHIRT_PATH;
                                case "AO" ->
                                    filePath = SHOES_PATH;
                                case "AS" ->
                                    filePath = SOCKS_PATH;
                                default ->
                                    System.out.println("Error");
                            }

                            itemList.loadFromFile(filePath);
                            itemList.changeStatus(itemId, "Unavailable");
                            itemList.saveToFile(filePath);
                            current = current.next;
                        }
                    }
                    case "2" -> {
                        String status = "Cancelled";
                        distribution.setStatus(status);
                        System.out.println(Green + "Distribution (" + distribution.getId() + ") status was updated to " + status + " successfully." + Reset);
                        distributionList.saveToFile(DONATION_DISTRIBUTION_PATH);

                        String doneeId = distribution.getDoneeId();
                        doneeList.changeStatus(doneeId, "Active");
                        doneeList.saveToFile(DONEE_PATH);

                        LinkedList<Item> distributionItemList = new ManageItems<>();
                        ManageItems<Item> itemList = new ManageItems<>();
                        String filePath = null;

                        distributionItemList = distribution.getDonations();

                        Node<Item> current = distributionItemList.head;

                        while (current != null) {
                            String itemId = current.data.getId();
                            String prefix = itemId.substring(0, 2);

                            switch (prefix) {
                                case "MB" ->
                                    filePath = BANK_PATH;
                                case "MC" ->
                                    filePath = CASH_PATH;
                                case "FA" ->
                                    filePath = BAKED_PATH;
                                case "FO" ->
                                    filePath = BOXED_PATH;
                                case "FC" ->
                                    filePath = CANNED_PATH;
                                case "FD" ->
                                    filePath = DRY_PATH;
                                case "FE" ->
                                    filePath = ESS_PATH;
                                case "AJ" ->
                                    filePath = JACKET_PATH;
                                case "AP" ->
                                    filePath = PANT_PATH;
                                case "AI" ->
                                    filePath = SHIRT_PATH;
                                case "AO" ->
                                    filePath = SHOES_PATH;
                                case "AS" ->
                                    filePath = SOCKS_PATH;
                                default ->
                                    System.out.println("Error");
                            }

                            itemList.loadFromFile(filePath);
                            itemList.changeStatus(itemId, "Available");
                            itemList.saveToFile(filePath);
                            current = current.next;
                        }
                    }
                    case "3" -> {
                        String status = "In Progress";
                        distribution.setStatus(status);
                        System.out.println(Green + "Distribution (" + distribution.getId() + ") status was updated to " + status + " successfully." + Reset);
                        distributionList.saveToFile(DONATION_DISTRIBUTION_PATH);

                        String doneeId = distribution.getDoneeId();
                        doneeList.changeStatus(doneeId, "Pending Distribution");
                        doneeList.saveToFile(DONEE_PATH);

                        LinkedList<Item> distributionItemList = new ManageItems<>();
                        ManageItems<Item> itemList = new ManageItems<>();
                        String filePath = null;

                        distributionItemList = distribution.getDonations();

                        Node<Item> current = distributionItemList.head;

                        while (current != null) {
                            String itemId = current.data.getId();
                            String prefix = itemId.substring(0, 2);

                            switch (prefix) {
                                case "MB" ->
                                    filePath = BANK_PATH;
                                case "MC" ->
                                    filePath = CASH_PATH;
                                case "FA" ->
                                    filePath = BAKED_PATH;
                                case "FO" ->
                                    filePath = BOXED_PATH;
                                case "FC" ->
                                    filePath = CANNED_PATH;
                                case "FD" ->
                                    filePath = DRY_PATH;
                                case "FE" ->
                                    filePath = ESS_PATH;
                                case "AJ" ->
                                    filePath = JACKET_PATH;
                                case "AP" ->
                                    filePath = PANT_PATH;
                                case "AI" ->
                                    filePath = SHIRT_PATH;
                                case "AO" ->
                                    filePath = SHOES_PATH;
                                case "AS" ->
                                    filePath = SOCKS_PATH;
                                default ->
                                    System.out.println("Error");
                            }

                            itemList.loadFromFile(filePath);
                            itemList.changeStatus(itemId, "Unavailable");
                            itemList.saveToFile(filePath);
                            current = current.next;
                        }
                    }
                    default -> {
                        System.out.println(Red + "Invalid category. Please only enter '1', '2' or '3'.\n" + Reset);
                    }
                }
            } while (!"1".equals(choice) && !"2".equals(choice) && !"3".equals(choice));

        } else {
            System.out.println(Red + "Invalid ID. No distribution found with the given ID.\n" + Reset);
        }
    }

    private static void monitorDistribution() {
        Scanner scanner = new Scanner(System.in);
        ManageDistribution<Distribution> distributionList = new ManageDistribution<>();

        distributionList.loadFromFile(DONATION_DISTRIBUTION_PATH);

        System.out.print("Enter Distribution ID to search: ");
        String id = scanner.nextLine().trim();

        Distribution distribution = distributionList.findById(id);
        if (distribution != null) {
            System.out.println(Green + "\nFound distribution: " + Reset);
            System.out.printf("%-20s |%-20s |%-30s |%-20s |%-20s |%-20s |%s\n",
                    "Distribution ID",
                    "Donee ID",
                    "Donee Name",
                    "Distribution Date",
                    "Status",
                    "Donation ID",
                    "Donation Type");
            String line = String.format("-").repeat(160);
            System.out.println(line);
            System.out.println(distribution.toString() + "\n");
        } else {
            System.out.println(Red + "\nNo distribution found with ID: " + id + Reset);
        }
    }

    private static void listDistribution() {
        ManageDistribution<Distribution> distributionList = new ManageDistribution<>();

        distributionList.loadFromFile(DONATION_DISTRIBUTION_PATH);

        // List donation distributions
        System.out.println(Green + "List of all Donation Distributions: " + Reset);
        System.out.printf("%-20s |%-20s |%-30s |%-20s |%-20s |%-20s |%s\n",
                "Distribution ID",
                "Donee ID",
                "Donee Name",
                "Distribution Date",
                "Status",
                "Donation ID",
                "Donation Type");
        String line = String.format("-").repeat(160);
        System.out.println(line);
        System.out.println(distributionList.show());
    }

    private static void reportDistribution() {
        Scanner scanner = new Scanner(System.in);
        ManageDistribution<Distribution> distributionList = new ManageDistribution<>();

        distributionList.loadFromFile(DONATION_DISTRIBUTION_PATH);
        
        String choice = null;

        do {
            System.out.println("1. Number of distributions by year");
            System.out.println("2. Number of distributions by month");
            System.out.println("3. All time distributions by status \n");
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {
                    LinkedList distributionListByYear = distributionList.generateTotalDistributionByYear();
                    System.out.println(Green + "\nNumber of distributions by year report: " + Reset);
                    System.out.printf("%-15s |%-20s |%-20s |%s\n", "Year", "Completed", "Cancelled", "In Progress");
                    String line = String.format("-").repeat(74);
                    System.out.println(line);
                    System.out.println(distributionListByYear.show());
                }
                case "2" -> {
                    LinkedList distributionListByMonth = distributionList.generateTotalDistributionByMonth();
                    System.out.println(Green + "\nNumber of distributions by month report: " + Reset);
                    System.out.printf("%-15s |%-20s |%-20s |%s\n", "Month", "Completed", "Cancelled", "In Progress");
                    String line = String.format("-").repeat(74);
                    System.out.println(line);
                    System.out.println(distributionListByMonth.show());
                }
                case "3" -> {
                    DistributionStatusCount distributionListByStatus = distributionList.generateTotalDistributionsByStatus();
                    System.out.println(Green + "\nNumber of distributions by status: " + Reset);
                    String line = String.format("-").repeat(35);
                    System.out.println(line);
                    System.out.println(distributionListByStatus.toString());
                    System.out.println();
                }

                default -> {
                    System.out.println(Red + "Invalid category. Please only enter '1', '2' or '3'.\n" + Reset);
                }
            }
        } while (!"1".equals(choice) && !"2".equals(choice) && !"3".equals(choice));
    }
}
