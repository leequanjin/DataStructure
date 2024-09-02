package control;

import adt.Donor.DonorLinkedList;
import entity.DonationManagement.Item;
import static control.DonationManagement.loadAllItemIntoList;
import adt.LinkedList;
import adt.LinkedListInterface;
import adt.Node;
import entity.Donor.Donor;
import entity.Donor.IndividualDonor;
import entity.Donor.OrganizationDonor;
import utility.DonorMessageUI;
import utility.FilePath;
import boundary.ManageDonorUI;

/**
 *
 * @author Kee Ke Shen
 */
public class ManageDonor {

    // Colours
    static String Red = "\u001b[31m";
    static String Blue = "\u001B[34m";
    static String Green = "\u001b[32;2m";
    static String Purple = "\u001B[35m";
    static String Reset = "\u001b[0m";

    // Imports
    ManageDonorUI manageDonorUI = new ManageDonorUI();
    FilePath filePath = new FilePath();

    String choice = null;

    public static void main(String[] args) {
        ManageDonor manageDonor = new ManageDonor();
        manageDonor.donorMenu();
    }

    // Menu Methods
    public void donorMenu() {

        boolean running = true;

        while (running) {
            manageDonorUI.displayMenuChoice();
            choice = manageDonorUI.getChoice();

            switch (choice) {
                case "1" ->
                    addDonor();
                case "2" ->
                    removeDonor();
                case "3" ->
                    updateDonor();
                case "4" ->
                    searchDonor();
                case "5" ->
                    listDonor();
                case "6" ->
                    filterDonor();
                case "7" ->
                    reportDonor();
                case "8" -> {
                    // Exit the program
                    running = false;
                    DonorMessageUI.displayExitMessage();
                }
                default ->
                    DonorMessageUI.displayInvalidChoiceMessage();
            }
            System.out.println();
        }
    }

    // Menu Choices
    // Add a new donor
    public void addDonor() {
        DonorLinkedList<Donor> donorList = new DonorLinkedList<>();
        donorList.loadFromFile(filePath.DONOR_PATH);

        manageDonorUI.displayDonorTypeChoice();
        do {
            choice = manageDonorUI.getChoice();

            switch (choice) {
                case "1" -> {
                    String id = donorList.generateDonorId();
                    String name = manageDonorUI.getName();
                    String category = manageDonorUI.getCategory();

                    donorList.insertAtStart(new IndividualDonor(id, name, category));
                    System.out.println(Green + "\nIndividual donor added with Donor ID: " + id + Reset);
                    donorList.saveToFile(filePath.DONOR_PATH);
                }
                case "2" -> {
                    String id = donorList.generateDonorId();
                    String name = manageDonorUI.getName();
                    String category = manageDonorUI.getCategory();

                    donorList.insertAtStart(new OrganizationDonor(id, name, category));
                    System.out.println(Green + "\nOrganization donor added with Donor ID: " + id + Reset);
                    donorList.saveToFile(filePath.DONOR_PATH);
                }
                default ->
                    DonorMessageUI.displayInvalidChoiceMessage();
            }
        } while (!"1".equals(choice) && !"2".equals(choice));
    }

    // Remove a donor
    public void removeDonor() {

        DonorLinkedList<Donor> donorList = new DonorLinkedList<>();
        donorList.loadFromFile(filePath.DONOR_PATH);

        System.out.println(Green + "List of all Donors: " + Reset);
        manageDonorUI.displayDonorList(donorList);

        String id = manageDonorUI.getRemoveDonorID();
        Donor donorToRemove = donorList.findById(id);
        if (donorToRemove != null) {
            donorList.deleteById(id);
            System.out.println(Green + "Donor (" + donorToRemove.getName() + ") was removed successfully." + Reset);
            donorList.saveToFile(filePath.DONOR_PATH);
        } else {
            DonorMessageUI.displayInvalidDonorIdMessage();
        }
    }

    // Update donor details
    public void updateDonor() {
        DonorLinkedList<Donor> donorList = new DonorLinkedList<>();
        donorList.loadFromFile(filePath.DONOR_PATH);

        String id = manageDonorUI.getUpdateDonorID();
        Donor donorToUpdate = donorList.findById(id);
        if (donorToUpdate != null) {
            manageDonorUI.displayFoundDonor(donorToUpdate);
            manageDonorUI.displayUpdateDonorChoice();

            choice = manageDonorUI.getChoice();
            switch (choice) {
                // Update Name
                case "1" -> {
                    String newName = manageDonorUI.getName();
                    donorToUpdate.setName(newName);
                    System.out.println(Green + "Name updated to " + newName + " successfully." + Reset);
                    donorList.saveToFile(filePath.DONOR_PATH);
                }
                // Update Category
                case "2" -> {
                    manageDonorUI.displayDonorTypeChoice();
                    do {
                        choice = manageDonorUI.getChoice();

                        switch (choice) {
                            case "1" -> {
                                donorList.replace(donorToUpdate, changeToIndividual(donorToUpdate));
                                System.out.println(Green + "Type updated to individual successfully." + Reset);
                                donorList.saveToFile(filePath.DONOR_PATH);
                            }
                            case "2" -> {
                                donorList.replace(donorToUpdate, changeToOrganization(donorToUpdate));
                                System.out.println(Green + "Type updated to organization successfully." + Reset);
                                donorList.saveToFile(filePath.DONOR_PATH);
                            }

                            default ->
                                DonorMessageUI.displayInvalidChoiceMessage();
                        }
                    } while (!"1".equals(choice) && !"2".equals(choice));
                }
                // Update Both
                case "3" -> {
                    String newCategory = manageDonorUI.getCategory();
                    donorToUpdate.setCategory(newCategory);
                    System.out.println(Green + "Category updated to " + newCategory + " successfully." + Reset);
                    donorList.saveToFile(filePath.DONOR_PATH);
                }
                default ->
                    DonorMessageUI.displayInvalidUpdateChoiceMessage();
            }
        } else {
            DonorMessageUI.displayInvalidDonorIdMessage();
        }
    }

    // Search for a specific donor
    public void searchDonor() {
        DonorLinkedList<Donor> donorList = new DonorLinkedList<>();
        donorList.loadFromFile(filePath.DONOR_PATH);

        do {
            manageDonorUI.displaySearchChoice();
            choice = manageDonorUI.getChoice();

            switch (choice) {
                case "1" -> {
                    String id = manageDonorUI.getDonorID();
                    Donor donor = donorList.findById(id);
                    if (donor != null) {
                        manageDonorUI.displayFoundDonor(donor);
                    } else {
                        DonorMessageUI.displayInvalidDonorIdMessage();
                    }
                }
                case "2" -> {
                    String name = manageDonorUI.getName().toLowerCase();

                    LinkedList<Donor> matchingDonors = new LinkedList<>();
                    Node<Donor> current = donorList.head;

                    while (current != null) {
                        if (current.data.getName().toLowerCase().contains(name)) {
                            matchingDonors.insertAtStart(current.data);
                        }
                        current = current.next;
                    }

                    if (!matchingDonors.isEmpty()) {
                        System.out.println(Green + "\nMatching donors found: " + Reset);
                        manageDonorUI.displayDonorList(matchingDonors);
                    } else {
                        DonorMessageUI.displayInvalidDonorNameMessage(name);
                    }
                }
                default -> {
                    DonorMessageUI.displayInvalidChoiceMessage();
                }
            }
        } while (!"1".equals(choice) && !"2".equals(choice));
    }

    // List donees with all donations made
    public void listDonor() {
        DonorLinkedList<Donor> donorList = new DonorLinkedList<>();
        donorList.loadFromFile(filePath.DONOR_PATH);
        if (!donorList.isEmpty()) {
            System.out.println(Green + "List of all Donors: " + Reset);
            manageDonorUI.displayDonorList(donorList);
        } else {
            DonorMessageUI.displayNoDonorMessage();
        }
    }

    // Filter donor based on criteria
    public void filterDonor() {
        DonorLinkedList<Donor> donorList = new DonorLinkedList<>();
        donorList.loadFromFile(filePath.DONOR_PATH);

        manageDonorUI.displayFilterChoice();
        do {
            choice = manageDonorUI.getChoice();

            switch (choice) {
                case "1" -> {
                    LinkedList<IndividualDonor> individualList = donorList.filterByCategory(IndividualDonor.class);
                    if (!individualList.isEmpty()) {
                        System.out.println(Green + "\nList of all Individual Donors: " + Reset);
                        manageDonorUI.displayDonorList(individualList);
                    } else {
                        DonorMessageUI.displayNoIndividualDonorMessage();
                    }
                }
                case "2" -> {
                    LinkedList<OrganizationDonor> organizationList = donorList.filterByCategory(OrganizationDonor.class);
                    if (!organizationList.isEmpty()) {
                        System.out.println(Green + "\nList of all Organization Donors: " + Reset);
                        manageDonorUI.displayDonorList(organizationList);
                    } else {
                        DonorMessageUI.displayNoOrganizationDonorMessage();
                    }
                }
                case "3" -> {
                    while (true) {
                        manageDonorUI.displayCategoryFilterChoice();
                        String categoryChoice = manageDonorUI.getChoice();

                        if (!"1".equals(categoryChoice) && !"2".equals(categoryChoice) && !"3".equals(categoryChoice)) {
                            DonorMessageUI.displayInvalidChoiceMessage();
                            continue;  // Prompt user to re-enter the category choice
                        }

                        LinkedList<Donor> categoryList = new LinkedList<>();
                        Node<Donor> current = donorList.head;

                        while (current != null) {
                            String donorCategory = current.data.getCategory().toLowerCase();
                            boolean match = false;

                            switch (categoryChoice) {
                                case "1" ->
                                    match = donorCategory.equals("government");
                                case "2" ->
                                    match = donorCategory.equals("private");
                                case "3" ->
                                    match = donorCategory.equals("public");
                            }

                            if (match) {
                                categoryList.insertAtStart(current.data);
                            }

                            current = current.next;
                        }

                        if (!categoryList.isEmpty()) {
                            System.out.println(Green + "\nFiltered donors: " + Reset);
                            manageDonorUI.displayDonorList(categoryList);
                        } else {
                            DonorMessageUI.displayNoDonorInCategoryMessage();
                        }
                        break;  // Exit the category selection loop after successful filtering
                    }
                }
                default -> {
                    DonorMessageUI.displayInvalidChoiceMessage();
                }
            }
        } while (!"1".equals(choice) && !"2".equals(choice) && !"3".equals(choice));
    }

    // Generate donee summary report
    public void reportDonor() {
        DonorLinkedList<Donor> donorList = new DonorLinkedList<>();
        donorList.loadFromFile(filePath.DONOR_PATH);

        manageDonorUI.displayReportChoice();

        do {
            choice = manageDonorUI.getChoice();

            switch (choice) {
                case "1" -> {
                    LinkedList donorYearCountList = donorList.generateTotalDonorByYear();
                    if (donorList.isEmpty()) {
                        System.out.println(Red + "No donors available to generate the report." + Reset);
                        return;
                    } else {
                        System.out.println(Green + "\nNumber of new donees by year report: " + Reset);
                        manageDonorUI.displayPeriodCountReport(donorYearCountList);
                    }
                }
                case "2" ->
                    donorByCategoryReport();
                case "3" ->
                    donorContributionReport();
                default -> {
                    DonorMessageUI.displayInvalidChoiceMessage();
                }
            }
        } while (!"1".equals(choice) && !"2".equals(choice) && !"3".equals(choice));
    }

    // Generate donor contribution report
    public void donorContributionReport() {
        LinkedListInterface<Item> itemList = loadAllItemIntoList(); // Implement this method to load item data
        DonorLinkedList<Donor> donorList = new DonorLinkedList<>();
        donorList.loadFromFile(filePath.DONOR_PATH);

        itemList.removeEmptyData();
        donorList.removeEmptyData();

        if (donorList.isEmpty()) {
            System.out.println(Red + "No donor exists." + Reset);
            return;
        }

        if (itemList.isEmpty()) {
            System.out.println(Red + "No items in stock." + Reset);
            return;
        }

        System.out.println(Green + "\nDonor with the Highest Contribution:" + Reset);
        Node<Donor> currentDonor = donorList.head;
        int max = 0;
        Donor donor = currentDonor.data;
        while (currentDonor != null) {
            System.out.print(currentDonor.data.getId());
            Node<Item> currentItem = itemList.getHead();
            int sum = 0;
            while (currentItem != null) {
                if (currentDonor.data.getId().equals(currentItem.data.getDonorID())) {
                    sum++;
                }
                currentItem = currentItem.next;
            }

            System.out.print(" (" + sum + ") ");

            printStar2(sum);

            if (max < sum) {
                max = sum;
                donor = currentDonor.data;
            }

            System.out.println();
            currentDonor = currentDonor.next;
        }

        System.out.println("\nRemarks: Symbol * will be display if item's total exceed 50 and each * represent 50 items");
        System.out.println("Conclusion: The donor with highest contribution is " + Green + donor.getName() + Reset + " with the total of " + max + " donated items.");
    }

    // Generate donor by category report
    public void donorByCategoryReport() {
        DonorLinkedList<Donor> donorList = new DonorLinkedList<>();
        donorList.loadFromFile(filePath.DONOR_PATH);

        if (donorList.isEmpty()) {
            System.out.println(Red + "No donors available to generate the report." + Reset);
            return;
        }

        int governmentCount = 0;
        int privateCount = 0;
        int publicCount = 0;

        Node<Donor> current = donorList.head;
        while (current != null) {
            switch (current.data.getCategory().toLowerCase()) {
                case "government" ->
                    governmentCount++;
                case "private" ->
                    privateCount++;
                case "public" ->
                    publicCount++;
            }
            current = current.next;
        }

        System.out.println(Green + "\nNumber of donors by category:" + Reset);
        System.out.print("Government: ");
        printStar(governmentCount);
        System.out.println("(" + governmentCount + ")");
        System.out.print("Private: ");
        printStar(privateCount);
        System.out.println("(" + privateCount + ")");
        System.out.print("Public: ");
        printStar(publicCount);
        System.out.println("(" + publicCount + ")");

        System.out.println("\nRemarks: \nSymbol " + Purple + "* " + Reset + "represents 100 people.");
        System.out.println("Symbol " + Blue + "* " + Reset + "represents 10 people.");
        System.out.println("Symbol * represents 1 person.");
    }

    public static void printStar(int count) {
        int purpleStarCount = count / 100; // Number of purple stars (1 for every 100 people)
        int blueStarCount = count / 10; // Number of blue stars (1 for every 10 people)
        int normalStarCount = count % 10; // Remaining normal stars for less than 10 people

        // Print blue stars
        for (int i = 0; i < purpleStarCount; i++) {
            System.out.print(Purple + "*" + Reset);
        }

        // Print blue stars
        for (int i = 0; i < blueStarCount; i++) {
            System.out.print(Blue + "*" + Reset);
        }

        // Print normal stars
        for (int i = 0; i < normalStarCount; i++) {
            System.out.print("*");
        }
    }

    public static void printStar2(int count) {
        if (count > 50) {
            int left = count % 50;
            for (int i = 0; i < left; i++) {
                System.out.print(Blue + " *" + Reset);
            }
        }
    }

    public Donor changeToIndividual(Donor donor) {
        IndividualDonor individualDonor = new IndividualDonor(donor.getId(), donor.getName(), donor.getCategory());
        return individualDonor;
    }

    public Donor changeToOrganization(Donor donor) {
        OrganizationDonor organizationDonor = new OrganizationDonor(donor.getId(), donor.getName(), donor.getCategory());
        return organizationDonor;
    }
}
