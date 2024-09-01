/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.LinkedList;
import adt.LinkedListInterface;
import adt.Node;
import entity.Donee;
import entity.DoneeStateCount;
import entity.DoneeFamily;
import entity.DoneeIndividual;
import entity.DoneeOrganization;

import utility.DoneeMessageUI;
import utility.FilePath;
import boundary.ManageDoneeUI;

/**
 *
 * @author Christopher Yap Jian Xing
 */
public class ManageDonee {

    //Colours
    static String Red = "\u001b[31m";
    static String Green = "\u001b[32;2m";
    static String Reset = "\u001b[0m";
    
    //Imports
    ManageDoneeUI manageDoneeUI = new ManageDoneeUI();
    FilePath filePath = new FilePath();
    
    String choice = null;

    public static void main(String[] args) {
        ManageDonee manageDonee = new ManageDonee();
        manageDonee.doneeMenu();
    }

// ------------    
// Menu Methods
// ------------ 
    public void doneeMenu() {

        boolean running = true;

        while (running) {
            manageDoneeUI.displayMenuChoice();
            choice = manageDoneeUI.getChoice();

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
                    DoneeMessageUI.displayExitMessage();
                }
                default -> {
                    DoneeMessageUI.displayInvalidChoiceMessage();
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
        DoneeLinkedListInterface<Donee> doneeList = new DoneeLinkedList<>();
        doneeList.loadFromFile(filePath.DONEE_PATH);

        manageDoneeUI.displayDoneeTypeChoice();
        do {
            choice = manageDoneeUI.getChoice();

            switch (choice) {
                case "1" -> {
                    String id = doneeList.generateDoneeId();
                    String name = manageDoneeUI.getName();
                    String state = manageDoneeUI.getState();

                    doneeList.insertAtStart(new DoneeIndividual(id, name, state));
                    System.out.println(Green + "Individual added with Donee ID: " + id + Reset);
                    doneeList.saveToFile(filePath.DONEE_PATH);
                }
                case "2" -> {
                    String id = doneeList.generateDoneeId();
                    String name = manageDoneeUI.getName();
                    String state = manageDoneeUI.getState();

                    doneeList.insertAtStart(new DoneeFamily(id, name, state));
                    System.out.println(Green + "Family added with Donee ID: " + id + Reset);
                    doneeList.saveToFile(filePath.DONEE_PATH);
                }
                case "3" -> {
                    String id = doneeList.generateDoneeId();
                    String name = manageDoneeUI.getName();
                    String state = manageDoneeUI.getState();

                    doneeList.insertAtStart(new DoneeOrganization(id, name, state));
                    System.out.println(Green + "Organization added with Donee ID: " + id + Reset);
                    doneeList.saveToFile(filePath.DONEE_PATH);
                }
                default -> {
                    DoneeMessageUI.displayInvalidChoiceMessage();
                }
            }
        } while (!"1".equals(choice) && !"2".equals(choice) && !"3".equals(choice));
    }

    // Remove a donee
    public void removeDonee() {

        DoneeLinkedListInterface<Donee> doneeList = new DoneeLinkedList<>();
        doneeList.loadFromFile(filePath.DONEE_PATH);

        String id = manageDoneeUI.getDoneeID();
        Donee doneeToRemove = doneeList.findById(id);
        if (doneeToRemove != null) {
            doneeList.deleteById(id);
            System.out.println(Green + "Donee (" + doneeToRemove.getName() + ") was removed successfully." + Reset);
            doneeList.saveToFile(filePath.DONEE_PATH);
        } else {
            DoneeMessageUI.displayInvalidDoneeIdMessage();
        }
    }

    // Update donee details
    public void updateDonee() {
        DoneeLinkedListInterface<Donee> doneeList = new DoneeLinkedList<>();
        doneeList.loadFromFile(filePath.DONEE_PATH);

        String id = manageDoneeUI.getDoneeID();
        Donee doneeToUpdate = doneeList.findById(id);
        if (doneeToUpdate != null) {
            manageDoneeUI.displayFoundDonee(doneeToUpdate);
            manageDoneeUI.displayUpdateDoneeChoice();

            choice = manageDoneeUI.getChoice();
            switch (choice) {
                // Update Name
                case "1" -> {
                    String newName = manageDoneeUI.getName();
                    doneeToUpdate.setName(newName);
                    System.out.println(Green + "Name updated to " + newName + " successfully." + Reset);
                    doneeList.saveToFile(filePath.DONEE_PATH);
                }
                // Update Category
                case "2" -> {
                    manageDoneeUI.displayDoneeTypeChoice();
                    do {
                        choice = manageDoneeUI.getChoice();

                        switch (choice) {
                            case "1" -> {
                                doneeList.replace(doneeToUpdate, changeToIndividual(doneeToUpdate));
                                System.out.println(Green + "Category updated to individual successfully." + Reset);
                                doneeList.saveToFile(filePath.DONEE_PATH);
                            }
                            case "2" -> {
                                doneeList.replace(doneeToUpdate, changeToFamily(doneeToUpdate));
                                System.out.println(Green + "Category updated to family successfully." + Reset);
                                doneeList.saveToFile(filePath.DONEE_PATH);
                            }
                            case "3" -> {
                                doneeList.replace(doneeToUpdate, changeToOrganization(doneeToUpdate));
                                System.out.println(Green + "Category updated to organization successfully." + Reset);
                                doneeList.saveToFile(filePath.DONEE_PATH);
                            }

                            default -> {
                                DoneeMessageUI.displayInvalidChoiceMessage();
                            }
                        }
                    } while (!"1".equals(choice) && !"2".equals(choice) && !"3".equals(choice));
                }
                // Update Location
                case "3" -> {
                    String state = manageDoneeUI.getState();
                    doneeToUpdate.setLocation(state);
                    System.out.println(Green + "State updated to " + state + " successfully." + Reset);
                    doneeList.saveToFile(filePath.DONEE_PATH);
                }
                // Update Status
                case "4" -> {
                    manageDoneeUI.displayStatusChoice();
                    choice = manageDoneeUI.getChoice();

                    switch (choice) {
                        case "1" -> {
                            String status = "Active";
                            doneeToUpdate.setStatus(status);
                            System.out.println(Green + "Status updated to " + status + " successfully." + Reset);
                            doneeList.saveToFile(filePath.DONEE_PATH);
                        }
                        case "2" -> {
                            String status = "Pending Distribution";
                            doneeToUpdate.setStatus(status);
                            System.out.println(Green + "Status updated to " + status + " successfully." + Reset);
                            doneeList.saveToFile(filePath.DONEE_PATH);
                        }
                        case "3" -> {
                            String status = "Completed";
                            doneeToUpdate.setStatus(status);
                            System.out.println(Green + "Status updated to " + status + " successfully." + Reset);
                            doneeList.saveToFile(filePath.DONEE_PATH);
                        }
                        default ->
                            System.out.println(Red + "Invalid option. No changes made." + Reset);
                    }
                }
                default ->
                    DoneeMessageUI.displayInvalidChoiceMessage();
            }
        } else {
            DoneeMessageUI.displayInvalidDoneeIdMessage();
        }
    }

    // Search for a specific donee
    public void searchDonee() {
        DoneeLinkedListInterface<Donee> doneeList = new DoneeLinkedList<>();
        doneeList.loadFromFile(filePath.DONEE_PATH);

        do {
            manageDoneeUI.displaySearchChoice();
            choice = manageDoneeUI.getChoice();

            switch (choice) {
                case "1" -> {
                    String id = manageDoneeUI.getDoneeID();
                    Donee donee = doneeList.findById(id);
                    if (donee != null) {
                        manageDoneeUI.displayFoundDonee(donee);
                    } else {
                        DoneeMessageUI.displayInvalidDoneeIdMessage();
                    }
                }
                case "2" -> {
                    String name = manageDoneeUI.getName().toLowerCase();

                    LinkedListInterface<Donee> matchingDonees = new LinkedList<>();
                    Node<Donee> current = doneeList.getHead();

                    while (current != null) {
                        if (current.data.getName().toLowerCase().contains(name)) {
                            matchingDonees.insertAtStart(current.data);
                        }
                        current = current.next;
                    }

                    if (!matchingDonees.isEmpty()) {
                        System.out.println(Green + "\nMatching donees found: " + Reset);
                        manageDoneeUI.displayDoneeList(matchingDonees);
                    } else {
                        DoneeMessageUI.displayInvalidDoneeNameMessage(name);
                    }
                }
                default ->
                    DoneeMessageUI.displayInvalidChoiceMessage();
            }
        } while (!"1".equals(choice) && !"2".equals(choice));
    }

    // List donees with all donations made
    public void listDonee() {
        DoneeLinkedListInterface<Donee> doneeList = new DoneeLinkedList<>();
        doneeList.loadFromFile(filePath.DONEE_PATH);

        System.out.println(Green + "List of all Donees: " + Reset);
        manageDoneeUI.displayDoneeList(doneeList);
    }

    // Filter donee based on criteria
    public void filterDonee() {
        DoneeLinkedListInterface<Donee> doneeList = new DoneeLinkedList<>();
        doneeList.loadFromFile(filePath.DONEE_PATH);

        manageDoneeUI.displayFilterChoice();
        do {
            choice = manageDoneeUI.getChoice();

            switch (choice) {
                case "1" -> {
                    LinkedListInterface<DoneeIndividual> individualList = doneeList.filterByCategoryIntoLinkedListInterface(DoneeIndividual.class);
                    System.out.println(Green + "\nList of all Individual Donees: " + Reset);
                    manageDoneeUI.displayDoneeList(individualList);
                }
                case "2" -> {
                    LinkedListInterface<DoneeFamily> familyList = doneeList.filterByCategoryIntoLinkedListInterface(DoneeFamily.class);
                    System.out.println(Green + "\nList of all Family Donees: " + Reset);
                    manageDoneeUI.displayDoneeList(familyList);
                }
                case "3" -> {
                    LinkedListInterface<DoneeOrganization> organizationList = doneeList.filterByCategoryIntoLinkedListInterface(DoneeOrganization.class);
                    System.out.println(Green + "\nList of all Organization Donees: " + Reset);
                    manageDoneeUI.displayDoneeList(organizationList);
                }
                default -> {
                    DoneeMessageUI.displayInvalidChoiceMessage();
                }
            }
        } while (!"1".equals(choice) && !"2".equals(choice) && !"3".equals(choice));
    }

    // Generate donee summary report
    public void reportDonee() {
        DoneeLinkedListInterface<Donee> doneeList = new DoneeLinkedList<>();
        doneeList.loadFromFile(filePath.DONEE_PATH);

        manageDoneeUI.displayReportChoice();
        do {
            choice = manageDoneeUI.getChoice();

            switch (choice) {
                case "1" -> {
                    LinkedListInterface doneeYearCountList = doneeList.generateTotalDoneeByYear();
                    System.out.println(Green + "\nNumber of new donees by year report: " + Reset);
                    manageDoneeUI.displayPeriodCountReport(doneeYearCountList);
                }
                case "2" -> {
                    LinkedListInterface doneeMonthCountList = doneeList.generateTotalDoneeByMonth();
                    System.out.println(Green + "\nNumber of new donees by month report: " + Reset);
                    manageDoneeUI.displayPeriodCountReport(doneeMonthCountList);
                }
                case "3" -> {
                    DoneeStateCount doneeStateCount = doneeList.generateTotalDoneeByState();
                    manageDoneeUI.displayStateCountReport(doneeStateCount);
                }

                default -> {
                    DoneeMessageUI.displayInvalidChoiceMessage();
                }
            }
        } while (!"1".equals(choice) && !"2".equals(choice) && !"3".equals(choice));
    }
    
    // Change Class Methods
    public Donee changeToIndividual(Donee donee) {
        DoneeIndividual individualDonee = new DoneeIndividual(donee.getId(), donee.getName(), donee.getLocation());
        return individualDonee;
    }

    public Donee changeToFamily(Donee donee) {
        DoneeFamily familyDonee = new DoneeFamily(donee.getId(), donee.getName(), donee.getLocation());
        return familyDonee;
    }

    public Donee changeToOrganization(Donee donee) {
        DoneeOrganization organizationDonee = new DoneeOrganization(donee.getId(), donee.getName(), donee.getLocation());
        return organizationDonee;
    }
}
