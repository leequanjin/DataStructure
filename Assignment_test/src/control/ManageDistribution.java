/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.Node;
import entity.DonationManagement.Item;
import adt.Distribution.DistributionLinkedList;
import adt.Distribution.DistributionLinkedListInterface;
import adt.Distribution.ItemLinkedList;
import adt.Distribution.ItemLinkedListInterface;
import adt.LinkedListInterface;
import entity.Donee.Donee;
import adt.Donee.DoneeLinkedList;
import adt.Donee.DoneeLinkedListInterface;
import entity.Distribution.Distribution;
import boundary.ManageDistributionUI;
import entity.Distribution.DistributionStatusCount;
import utility.DistributionMessageUI;

/**
 *
 * @author Lee Quan Jin
 */
public class ManageDistribution {

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
    static String Green = "\u001b[32;2m";
    static String Reset = "\u001b[0m";

    String choice = null;
    String filePath = null;
    
    ManageDistributionUI manageDistributionUI = new ManageDistributionUI();
    DistributionMessageUI distributionMessageUI = new DistributionMessageUI();

    public static void main(String[] args) {
        ManageDistribution manageDistribution = new ManageDistribution();
        manageDistribution.distributionMenu();
    }

    public void distributionMenu() {
        boolean running = true;

        manageDistributionUI.displayDistributionMenu();

        while (running) {
            choice = manageDistributionUI.getChoice();

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
                    distributionMessageUI.displayExitMessage();
                }
                default -> {
                    distributionMessageUI.displayInvalidChoiceMessage();
                }
            }
        }
    }

    // Menu Methods
    public void addDistribution() {
        DistributionLinkedListInterface<Distribution> distributionList = new DistributionLinkedList<>();
        DoneeLinkedListInterface<Donee> doneeList = new DoneeLinkedList<>();

        distributionList.loadFromFile(DONATION_DISTRIBUTION_PATH);
        doneeList.loadFromFile(DONEE_PATH);

        LinkedListInterface<Item> allItemList = DonationManagement.loadAllItemIntoList();

        ItemLinkedListInterface<Item> distributionItemList = new ItemLinkedList<>();

        String doneeId = manageDistributionUI.getID("donee");
        Donee donee = doneeList.findById(doneeId);
        if (donee != null) {
            String doneeName = donee.getName();

            if (donee.getStatus().equals("Active")) {
                distributionMessageUI.displayFoundDoneeMessage(doneeName);

                do {
                    String itemId = manageDistributionUI.getID("donation");

                    if (itemId.length() != 7) {
                        distributionMessageUI.displayInvalidDonationMessage();
                    } else {

                        Item item = DonationManagement.findByID(itemId, allItemList);
                        if (item == null) {
                            distributionMessageUI.displayInvalidIdMessage("donation");
                        } else if (item.getAvailability().equals("Unavailable")) {
                            distributionMessageUI.displayDonationNotAvailableMessage();
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

                            ItemLinkedListInterface<Item> itemList = new ItemLinkedList<>();

                            itemList.loadFromFile(filePath);
                            itemList.changeStatus(itemId, "Unavailable");
                            itemList.saveToFile(filePath);

                            choice = manageDistributionUI.getChoiceYN("Continue adding items?");

                            if (!choice.equals("Y") && !choice.equals("N")) {
                                distributionMessageUI.displayInvalidChoiceYNMessage();
                            }
                        }
                    }
                } while (!choice.equals("N"));

                doneeList.changeStatus(doneeId, "Pending Distribution");
                doneeList.saveToFile(DONEE_PATH);

                String id = distributionList.generateDistributionId();
                Distribution donationDistribution = new Distribution(id, doneeId, doneeName, distributionItemList);
                distributionList.insertAtStart(donationDistribution);

                distributionMessageUI.displayDistributionCreatedMessage(id);
                distributionList.saveToFile(DONATION_DISTRIBUTION_PATH);

                System.out.println();
            } else {
                distributionMessageUI.displayDoneeNotEligibleMessage(doneeName);
            }
        } else {
            distributionMessageUI.displayInvalidIdMessage("donee");
        }
    }

    public void deleteDistribution() {
        DistributionLinkedListInterface<Distribution> distributionList = new DistributionLinkedList<>();
        DoneeLinkedListInterface<Donee> doneeList = new DoneeLinkedList<>();

        distributionList.loadFromFile(DONATION_DISTRIBUTION_PATH);
        doneeList.loadFromFile(DONEE_PATH);

        String id = manageDistributionUI.getID("distribution");

        Distribution distributionToRemove = distributionList.findById(id);
        if (distributionToRemove != null) {
            distributionList.removeEntry(distributionToRemove);

            String doneeId = distributionToRemove.getDoneeId();
            doneeList.changeStatus(doneeId, "Active");
            doneeList.saveToFile(DONEE_PATH);

            updateDonationStatus(distributionToRemove, "Available");

            distributionMessageUI.displayDistributionRemovedMessage(id);
            distributionList.saveToFile(DONATION_DISTRIBUTION_PATH);
        } else {
            distributionMessageUI.displayInvalidIdMessage("distribution");
        }
    }

    public void updateDistribution() {
        DistributionLinkedListInterface<Distribution> distributionList = new DistributionLinkedList<>();
        DoneeLinkedListInterface<Donee> doneeList = new DoneeLinkedList<>();

        distributionList.loadFromFile(DONATION_DISTRIBUTION_PATH);
        doneeList.loadFromFile(DONEE_PATH);

        String id = manageDistributionUI.getID("distribution");
        Distribution distribution = distributionList.findById(id);
        if (distribution != null) {

            manageDistributionUI.displayUpdateDistributionChoices();
            do {
                choice = manageDistributionUI.getChoice();

                switch (choice) {
                    case "1" -> {
                        String status = "Completed";
                        distribution.setStatus(status);
                        distributionMessageUI.displayDistributionStatusUpdatedMessage(id, status);

                        distributionList.saveToFile(DONATION_DISTRIBUTION_PATH);

                        String doneeId = distribution.getDoneeId();
                        doneeList.changeStatus(doneeId, "Completed");
                        doneeList.saveToFile(DONEE_PATH);

                        updateDonationStatus(distribution, "Unavailable");
                    }
                    case "2" -> {
                        String status = "Cancelled";
                        distribution.setStatus(status);
                        distributionMessageUI.displayDistributionStatusUpdatedMessage(id, status);
                        distributionList.saveToFile(DONATION_DISTRIBUTION_PATH);

                        String doneeId = distribution.getDoneeId();
                        doneeList.changeStatus(doneeId, "Active");
                        doneeList.saveToFile(DONEE_PATH);

                        updateDonationStatus(distribution, "Available");
                    }
                    case "3" -> {
                        String status = "In Progress";
                        distribution.setStatus(status);
                        distributionMessageUI.displayDistributionStatusUpdatedMessage(id, status);
                        distributionList.saveToFile(DONATION_DISTRIBUTION_PATH);

                        String doneeId = distribution.getDoneeId();
                        doneeList.changeStatus(doneeId, "Pending Distribution");
                        doneeList.saveToFile(DONEE_PATH);

                        updateDonationStatus(distribution, "Unavailable");

                    }
                    default -> {
                        distributionMessageUI.displayInvalidChoiceMessage();
                    }
                }
            } while (!"1".equals(choice) && !"2".equals(choice) && !"3".equals(choice));

        } else {
            distributionMessageUI.displayInvalidIdMessage("distribution");
        }
    }

    public void monitorDistribution() {
        DistributionLinkedListInterface<Distribution> distributionList = new DistributionLinkedList<>();

        distributionList.loadFromFile(DONATION_DISTRIBUTION_PATH);

        String id = manageDistributionUI.getID("distribution");

        Distribution distribution = distributionList.findById(id);
        if (distribution != null) {
            System.out.println(Green + "\nFound distribution: " + Reset);
            manageDistributionUI.displayFoundDistribution(distribution);
        } else {
            distributionMessageUI.displayInvalidIdMessage("distribution");
        }
    }

    public void listDistribution() {
        DistributionLinkedListInterface<Distribution> distributionList = new DistributionLinkedList<>();

        distributionList.loadFromFile(DONATION_DISTRIBUTION_PATH);

        // List donation distributions
        System.out.println(Green + "List of all Donation Distributions: " + Reset);
        manageDistributionUI.displayDistributionList(distributionList);
    }

    public void reportDistribution() {
        DistributionLinkedListInterface<Distribution> distributionList = new DistributionLinkedList<>();
        distributionList.loadFromFile(DONATION_DISTRIBUTION_PATH);

        manageDistributionUI.displayDistributionReportMenu();
        do {
            choice = manageDistributionUI.getChoice();

            switch (choice) {
                case "1" -> {
                    LinkedListInterface distributionListByYear = distributionList.generateTotalDistributionByYear();
                    System.out.println(Green + "\nNumber of distributions by year report: " + Reset);
                    manageDistributionUI.displayDistributionPeriodReport(distributionListByYear);
                }
                case "2" -> {
                    LinkedListInterface distributionListByMonth = distributionList.generateTotalDistributionByMonth();
                    System.out.println(Green + "\nNumber of distributions by month report: " + Reset);
                    manageDistributionUI.displayDistributionPeriodReport(distributionListByMonth);
                }
                case "3" -> {
                    DistributionStatusCount distributionListByStatus = distributionList.generateTotalDistributionsByStatus();
                    System.out.println(Green + "\nNumber of distributions by status: " + Reset);
                    manageDistributionUI.displayDistributionStatusReport(distributionListByStatus);
                }

                default -> {
                    distributionMessageUI.displayInvalidChoiceMessage();
                }
            }
        } while (!"1".equals(choice) && !"2".equals(choice) && !"3".equals(choice));
    }

    // Methods
    public void updateDonationStatus(Distribution distributionToRemove, String status) {
        LinkedListInterface<Item> distributionItemList = new ItemLinkedList<>();
        ItemLinkedListInterface<Item> itemList = new ItemLinkedList<>();

        distributionItemList = distributionToRemove.getDonations();
        Node<Item> current = distributionItemList.getHead();

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
            itemList.changeStatus(itemId, status);
            itemList.saveToFile(filePath);
            current = current.next;
        }
    }

}
