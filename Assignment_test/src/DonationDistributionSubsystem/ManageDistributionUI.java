/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DonationDistributionSubsystem;

import CommonResources.LinkedList;
import CommonResources.Node;
import DonationList.Item;
import DonationManagementSubsystem.DonationManagement;
import DoneeSubsystem.Donee;
import DoneeSubsystem.ManageDonee;
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
        Scanner scanner = new Scanner(System.in);
        ManageDistribution<Distribution> distributionList = new ManageDistribution<>();
        ManageDonee<Donee> doneeList = new ManageDonee<>();

        distributionList.loadFromFile(DONATION_DISTRIBUTION_PATH);
        doneeList.loadFromFile(DONEE_PATH);
        
        LinkedList<Item> allItemList = DonationManagement.loadAllItemIntoList();
        System.out.println(allItemList.show());

        boolean running = true;

        while (running) {
            System.out.println("Choose an option:");
            System.out.println("1. Add new donation distribution");
            System.out.println("2. Delete donation distribution");
            System.out.println("3. Update donation distribution status"); // update status
            System.out.println("4. Monitor/track distributed items "); // check pending / check success 
            System.out.println("5. List all distributed donations");
            System.out.println("6. Generate Summary Report"); // which donee receive most item
            System.out.println("7. Save Changes");
            System.out.println("8. Exit");
            System.out.print("\nEnter your choice: ");

            String choice = scanner.nextLine();
            System.out.println("");

            switch (choice) {
                case "1" -> {
                    ManageItems<Item> distributionItemList = new ManageItems<>();
                    String filePath = null;

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
                                    
                                    Item item = DonationManagement.searchByID(allItemList, itemId);
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
                                        donee.setStatus("Pending Distribution");
                                        doneeList.saveToFile(DONEE_PATH);

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

                            donee.setStatus("Pending Distribution");
                            String id = generateDistributionId(distributionList);
                            Distribution donationDistribution = new Distribution(id, doneeId, doneeName, distributionItemList);
                            distributionList.insertAtStart(donationDistribution);

                            System.out.println();
                        } else {
                            System.out.println(Red + "Donee (" + doneeName + ") is not eligible to accept new donations currently. Returning to donation distribution menu...\n" + Reset);
                        }
                    } else {
                        System.out.println(Red + "Donee not found. Returning to donation distribution menu...\n" + Reset);
                    }
                }

                case "2" -> {
                    System.out.print("Enter Distribution ID to remove: ");
                    String id = scanner.nextLine();
                    
                    Distribution distributionToRemove = distributionList.findById(id);
                    if (distributionToRemove != null) {
                        distributionList.removeEntry(distributionToRemove);
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
                        }

                        System.out.println(Green + "Distribution (" + distributionToRemove.getId() + ") was removed successfully." + Reset);
                    } else {
                        System.out.println(Red + "Invalid ID. No distribution found with the given ID.\n" + Reset);
                    }
                }

                case "3" -> {
                    System.out.print("Enter Distribution ID to update: ");
                    String id = scanner.nextLine();
                    Distribution distributionToCancel = distributionList.findById(id);
                    if (distributionToCancel != null) {
                        distributionToCancel.setStatus("Cancelled");
                        System.out.println(Green + "Distribution (" + distributionToCancel.getId() + ") was cancelled successfully." + Reset);
                    } else {
                        System.out.println(Red + "Invalid ID. No distribution found with the given ID.\n" + Reset);
                    }
                }

                case "4" -> {
                    System.out.print("Enter Distribution ID to search: ");
                    String id = scanner.nextLine().trim();

                    Distribution distribution = distributionList.findById(id);
                    if (distribution != null) {
                        System.out.println(Green + "\nFound distribution: " + Reset);
                        System.out.printf("%-20s |%-20s |%-30s |%-20s |%-20s |%-20s |%s\n", "Distribution ID", "Donee ID", "Donee Name", "Donation ID", "Donation Type", "Distribution Date", "Status");
                        String line = String.format("-").repeat(160);
                        System.out.println(line);
                        System.out.println(distribution.toString() + "\n");
                    } else {
                        System.out.println(Red + "\nNo distribution found with ID: " + id + Reset);
                    }
                }
                case "5" -> {
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
                case "6" -> {

                }
                case "7" -> {
                    distributionList.saveToFile(DONATION_DISTRIBUTION_PATH);

                    System.out.println(Green + "Changes saved sucessfully..." + Reset);
                }
                case "8" -> {
                    // Exit the program
                    running = false;
                    System.out.println(Green + "Exiting..." + Reset);
                }
                default -> {

                }
            }

        }

    }

    private static String generateDistributionId(ManageDistribution<Distribution> distributionList) {
        String prefix = "DIS";
        int maxId = 0;

        Node<Distribution> current = distributionList.head;

        while (current != null) {
            String currentId = current.data.getId().substring(prefix.length());
            int idNumber = Integer.parseInt(currentId);
            if (idNumber > maxId) {
                maxId = idNumber;
            }
            current = current.next;
        }

        return prefix + String.format("%05d", maxId + 1);
    }
}
