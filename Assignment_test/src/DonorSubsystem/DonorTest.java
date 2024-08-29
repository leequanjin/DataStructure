package DonorSubsystem;

import CommonResources.LinkedList;
import CommonResources.Node;
import DonationList.Item;
import static DonationManagementSubsystem.DonationManagement.loadAllItemIntoList;
import java.util.Scanner;

public class DonorTest {

    // Colors
    private static final String RED = "\u001b[31m";
    private static final String GREEN = "\u001b[32;2m";
    private static final String YELLOW = "\u001b[33m";
    private static final String RESET = "\u001b[0m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    
    private static final String[] VALID_CATEGORIES = {"Government", "Private", "Public"};
    
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ManageDonors<Donor> donorList = new ManageDonors<>();
            donorList.loadFromFile("donors.txt");

            boolean running = true;
            while (running) {
                int choice = getValidMenuChoice(scanner);
                switch (choice) {
                    case 1:
                        handleAddDonor(scanner, donorList);
                        break;
                    case 2:
                        handleRemoveDonor(scanner, donorList);
                        break;
                    case 3:
                        handleUpdateDonor(scanner, donorList);
                        break;
                    case 4:
                        handleSearchDonor(scanner, donorList);
                        break;
                    case 5:
                        handleListDonors(donorList);
                        break;
                    case 6:
                        handleFilterDonors(scanner, donorList);
                        break;
                    case 7:
                        handleGenerateSummaryReports(donorList);
                        break;
                    case 8:
                        donorList.saveToFile("donors.txt");
                        System.out.println(GREEN + "Changes saved." + RESET);
                        break;
                    case 9:
                        running = false;
                        System.out.println(RED + "Exiting..." + RESET);
                        break;
                    default:
                        System.out.println(RED + "Invalid option, please try again.\n" + RESET);
                }
                System.out.println();
            }
        }
    }

    private static int getValidMenuChoice(Scanner scanner) {
        int choice = -1;
        while (choice < 1 || choice > 9) {
            System.out.println(BLUE + "\n- - - Donor Menu - - -" + RESET);
            System.out.println("Choose an option:");
            System.out.println("1. Add a new donor");
            System.out.println("2. Remove a donor");
            System.out.println("3. Update donor details");
            System.out.println("4. Search donor details");
            System.out.println("5. List donors");
            System.out.println("6. Filter donors based on category");
            System.out.println("7. Generate Summary Report");
            System.out.println("8. Save changes");
            System.out.println("9. Exit");
            System.out.print("\nEnter your choice: ");
            
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice < 1 || choice > 9) {
                    System.out.println(RED + "Invalid choice. Please choose a number between 1 and 9.\n" + RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(RED + "Invalid input. Please enter a valid number.\n" + RESET);
            }
        }
        return choice;
    }

    private static void handleAddDonor(Scanner scanner, ManageDonors<Donor> donorList) {
        System.out.println(BLUE + "\n- - - Add Donor - - -" + RESET);
        int type = getValidDonorType(scanner);
        String id = generateDonorId(donorList);
        String name = getValidName(scanner);
        String category = getValidCategory(scanner);

        if (type == 1) {
            donorList.insert(new Individual(id, name, category));
        } else {
            donorList.insert(new Organization(id, name, category));
        }
        System.out.println(GREEN + "\nDonor added with ID: " + RESET + id);
    }

    private static int getValidDonorType(Scanner scanner) {
        int type;
        while (true) {
            System.out.print("Enter donor type (1 for Individual, 2 for Organization): ");
            String typeInput = scanner.nextLine().trim();
            if (typeInput.matches("[12]")) {
                type = Integer.parseInt(typeInput);
                break;
            } else {
                System.out.println(RED + "Invalid choice. Please enter '1' for Individual or '2' for Organization.\n" + RESET);
            }
        }
        return type;
    }

    private static String getValidName(Scanner scanner) {
        String name;
        while (true) {
            System.out.print("Enter donor name: ");
            name = scanner.nextLine().trim();
            if (isValidName(name)) {
                break;
            } else {
                System.out.println(RED + "Invalid name. Please enter a valid name (only letters and spaces) and between 2 and 30 characters long.\n" + RESET);
            }
        }
        return name;
    }

    private static String getValidCategory(Scanner scanner) {
        String category;
        while (true) {
            System.out.print("Enter donor category (Government, Private, Public): ");
            category = scanner.nextLine().trim();
            if (isValidCategory(category)) {
                break;
            } else {
                System.out.println(RED + "Invalid category. Please enter 'Government', 'Private', or 'Public'.\n" + RESET);
            }
        }
        return category;
    }

    private static boolean isValidName(String name) {
        return name != null && name.matches("^[a-zA-Z\\s]+$") && name.length() >= 2 && name.length() <= 30;
    }

    private static boolean isValidCategory(String category) {
        for (String validCategory : VALID_CATEGORIES) {
            if (category.equalsIgnoreCase(validCategory)) {
                return true;
            }
        }
        return false;
    }

    private static String generateDonorId(ManageDonors<Donor> donorList) {
        String prefix = "DNR";
        int maxId = 0;
        Node<Donor> current = donorList.head;
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

    private static void handleRemoveDonor(Scanner scanner, ManageDonors<Donor> donorList) {
        System.out.println(BLUE + "\n- - - Remove Donor - - -" + RESET);
        donorList.show();
        System.out.print("Enter donor ID to remove: ");
        String id = scanner.nextLine().trim();
        Donor donorToRemove = donorList.findById(id);
        if (donorToRemove != null) {
            donorList.deleteById(id);
            System.out.println(GREEN + "Donor removed." + RESET);
        } else {
            System.out.println(RED + "Invalid ID. No donor found with the given ID." + RESET);
        }
    }

    private static void handleUpdateDonor(Scanner scanner, ManageDonors<Donor> donorList) {
        System.out.println(BLUE + "\n- - - Update Donor - - -" + RESET);
        donorList.show();
        System.out.print("Enter donor ID to update: ");
        String id = scanner.nextLine().trim();
        Donor donorToUpdate = donorList.findById(id);
        if (donorToUpdate != null) {
            System.out.println("Found donor: " + YELLOW + donorToUpdate + RESET);
            System.out.println("\nWhat would you like to update?");
            System.out.println("1. Name");
            System.out.println("2. Category");
            System.out.println("3. Both");
            System.out.print("Enter your choice: ");
            String updateChoice = scanner.nextLine().trim();
            switch (updateChoice) {
                case "1":
                    donorToUpdate.setName(getValidName(scanner));
                    System.out.println(GREEN + "Name updated successfully." + RESET);
                    break;
                case "2":
                    donorToUpdate.setCategory(getValidCategory(scanner));
                    System.out.println(GREEN + "Category updated successfully." + RESET);
                    break;
                case "3":
                    donorToUpdate.setName(getValidName(scanner));
                    donorToUpdate.setCategory(getValidCategory(scanner));
                    System.out.println(GREEN + "Donor details updated successfully." + RESET);
                    break;
                default:
                    System.out.println(RED + "Invalid option. No changes made." + RESET);
            }
        } else {
            System.out.println(RED + "Invalid ID. No donor found with the given ID." + RESET);
        }
    }

    private static void handleSearchDonor(Scanner scanner, ManageDonors<Donor> donorList) {
        System.out.println(BLUE + "\n- - - Search Donor - - -" + RESET);
        System.out.println("Search Donor by:");
        System.out.println("1. Donor ID");
        System.out.println("2. Donor Name");
        System.out.print("Enter your choice: ");
        String searchChoice = scanner.nextLine().trim();
        switch (searchChoice) {
            case "1":
                searchDonorById(scanner, donorList);
                break;
            case "2":
                searchDonorByName(scanner, donorList);
                break;
            default:
                System.out.println(RED + "Invalid choice. Please enter '1' for ID search or '2' for Name search." + RESET);
        }
    }

    private static void searchDonorById(Scanner scanner, ManageDonors<Donor> donorList) {
        System.out.print("Enter donor ID to search: ");
        String id = scanner.nextLine().trim();
        Donor donor = donorList.findById(id);
        if (donor != null) {
            System.out.println("Donor found: " + YELLOW + donor + RESET);
        } else {
            System.out.println("No donor found with ID: " + RED + id + RESET);
        }
    }

    private static void searchDonorByName(Scanner scanner, ManageDonors<Donor> donorList) {
        System.out.print("Enter donor name to search: ");
        String name = scanner.nextLine().trim().toLowerCase();
        LinkedList<Donor> matchingDonors = new LinkedList<>();
        Node<Donor> current = donorList.head;
        while (current != null) {
            if (current.data.getName().toLowerCase().contains(name)) {
                matchingDonors.insert(current.data);
            }
            current = current.next;
        }
        if (!matchingDonors.isEmpty()) {
            System.out.println("Matching donors found:" + YELLOW);
            matchingDonors.show();
            System.out.println(RESET);
        } else {
            System.out.println("No donors found with name containing: " + RED + name + RESET);
        }
    }

    private static void handleListDonors(ManageDonors<Donor> donorList) {
        if (donorList.isEmpty()) {
            System.out.println(RED + "No donors found in the system." + RESET);
        } else {
            donorList.show();
        }
    }

    private static void handleFilterDonors(Scanner scanner, ManageDonors<Donor> donorList) {
        System.out.println(BLUE + "\n- - - Filter Donor - - -" + RESET);
        System.out.println("Filter by:");
        System.out.println("1. Individual Donors");
        System.out.println("2. Organization Donors");
        System.out.println("3. Donor Category");
        System.out.print("Enter your choice: ");
        String filterChoice = scanner.nextLine().trim();
        switch (filterChoice) {
            case "1":
                filterByType(donorList, Individual.class);
                break;
            case "2":
                filterByType(donorList, Organization.class);
                break;
            case "3":
                filterByCategory(scanner, donorList);
                break;
            default:
                System.out.println(RED + "Invalid choice. Please enter '1' for Individual Donors, '2' for Organization Donors, or '3' for Donor Category." + RESET);
        }
    }

    private static void filterByType(ManageDonors<Donor> donorList, Class<?> type) {
        LinkedList<Donor> filteredList = donorList.filterByCategory(type);
        if (filteredList.isEmpty()) {
            System.out.println(RED + "\nNo donors found of the selected type." + RESET);
        } else {
            filteredList.show();
        }
    }

        private static void filterByCategory(Scanner scanner, ManageDonors<Donor> donorList) {
        String categoryChoice = null;
        boolean validChoice = false;

        while (!validChoice) {
            System.out.println("Filter by category:");
            System.out.println("1. Government");
            System.out.println("2. Private");
            System.out.println("3. Public");
            System.out.print("Enter your choice: ");
            categoryChoice = scanner.nextLine().trim();

            // Check if the choice is valid
            if (categoryChoice.equals("1") || categoryChoice.equals("2") || categoryChoice.equals("3")) {
                validChoice = true;
            } else {
                System.out.println(RED + "Invalid category choice. Please enter '1', '2', or '3'.\n" + RESET);
            }
        }

        // Once a valid choice is made, proceed with filtering
        LinkedList<Donor> categoryList = new LinkedList<>();
        Node<Donor> current = donorList.head;
        while (current != null) {
            String donorCategory = current.data.getCategory().toLowerCase();
            boolean match = false;
            switch (categoryChoice) {
                case "1":
                    match = donorCategory.equals("government");
                    break;
                case "2":
                    match = donorCategory.equals("private");
                    break;
                case "3":
                    match = donorCategory.equals("public");
                    break;
            }
            if (match) {
                categoryList.insert(current.data);
            }
            current = current.next;
        }

        if (categoryList.isEmpty()) {
            System.out.println(RED + "\nNo donors found in the selected category." + RESET);
        } else {
            categoryList.show();
        }
    }
        
        private static void handleGenerateSummaryReports(ManageDonors<Donor> donorList) {
            System.out.println(BLUE + "\n- - - Report Menu - - -" + RESET);
            System.out.println("1. Generate Total Donors By Year Report");
            System.out.println("2. Generate Donor Contribution Report");
            System.out.println("3. Generate Donors by Category Report");
            System.out.print("Choose an option: ");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    handleGenerateTotalDonorsByYearReport(donorList);
                    break;
                case 2:
                    donorContributionReport();
                    break;
                case 3:
                    handleGenerateDonorsByCategoryReport(donorList);
                    break;
                default:
                    System.out.println(RED + "Invalid choice. Please choose a valid option." + RESET);
                    break;
            }
        }

        private static void handleGenerateTotalDonorsByYearReport(ManageDonors<Donor> donorList) {
            LinkedList donorListByYear = donorList.generateTotalDonorByYear();
                                    System.out.println(GREEN + "\nNumber of new donees by year report: " + RESET);
                                    System.out.printf("%-15s |%-20s |%s\n", "Year", "Individuals", "Organizations");
                                    String line = String.format("-").repeat(69);
                                    System.out.println(line);
                                    donorListByYear.show();
        }

        private static void handleGenerateDonorsByCategoryReport(ManageDonors<Donor> donorList) {
            // Validation check for empty list
            if (donorList.isEmpty()) {
                System.out.println(RED + "No donors available to generate the report." + RESET);
                return;
            }
            int governmentCount = 0;
            int privateCount = 0;
            int publicCount = 0;

            Node<Donor> current = donorList.head;
            while (current != null) {
                switch (current.data.getCategory().toLowerCase()) {
                    case "government":
                        governmentCount++;
                        break;
                    case "private":
                        privateCount++;
                        break;
                    case "public":
                        publicCount++;
                        break;
                }
                current = current.next;
            }

            System.out.println(GREEN + "\nNumber of donors by category:" + RESET);
            System.out.print("Government: ");
            printStar(governmentCount);
            System.out.println("(" + governmentCount + ")");
            System.out.print("Private: ");
            printStar(privateCount);
            System.out.println("(" + privateCount + ")");
            System.out.print("Public: ");
            printStar(publicCount);
            System.out.println("(" + publicCount + ")");
            
            System.out.println("\nRemarks: Symbol" + PURPLE + " * " + RESET + "represent 100 people");
            System.out.println("\n         Symbol" + BLUE + " * " + RESET + "represent 10 people");
            System.out.println("\n         Symbol * represent 1 person");
    }
        
            public static void donorContributionReport(){
            LinkedList<Item> itemList = loadAllItemIntoList();
            ManageDonors<Donor> donorList = new ManageDonors<>();
            donorList.loadFromFile("donors.txt");

            itemList.removeEmptyData();
            donorList.removeEmptyData();

            if (donorList.isEmpty()){
                System.out.println(RED + "No donor exist." + RESET);
                return;
            }

            if (itemList.isEmpty()){
                System.out.println(RED + "No item in stock." + RESET);
                return;
            }
            System.out.println("\nDonor with the Highest Contribution");
            Node<Donor> currentDonor = donorList.head;
            int max = 0;
            Donor donor = currentDonor.data;
            while(currentDonor != null){
                System.out.print(currentDonor.data.getId());
                Node<Item> currentItem = itemList.head;
                int sum = 0;
                while (currentItem != null){
                    if (currentDonor.data.getId().equals(currentItem.data.getDonorID())){
                        sum ++;
                    }
                    currentItem = currentItem.next;
                }

                System.out.print(" (" + sum + ") ");

                printStar2(sum);

                if (max < sum){
                    max = sum;
                    donor = currentDonor.data;
                }

                System.out.println();
                currentDonor = currentDonor.next;
            }

            System.out.println("\nRemarks: Symbol * will be display if item's total exceed 50 and each * represent 50 items");
            System.out.println("Conclusion: The donor with highest contribution is " + donor.getName() + " with the total of " + max + " donated items.");
        }
        
        public static void printStar(int count) {
            int purpleStarCount = count / 100; // Number of purple stars (1 for every 100 people)
            int blueStarCount = count / 10; // Number of blue stars (1 for every 10 people)
            int normalStarCount = count % 10; // Remaining normal stars for less than 10 people
            
            // Print blue stars
            for (int i = 0; i < purpleStarCount; i++) {
                System.out.print(PURPLE + "*" + RESET);
            }
            
            // Print blue stars
            for (int i = 0; i < blueStarCount; i++) {
                System.out.print(BLUE + "*" + RESET);
            }

            // Print normal stars
            for (int i = 0; i < normalStarCount; i++) {
                System.out.print("*");
            }
        }
        
        public static void printStar2(int count){
        if (count > 50){
            int left = count % 50;
            for (int i = 0; i < left; i ++){
                System.out.print(BLUE + " *" + RESET);
            }
        }
    }
}
