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
public class Test {

    //Colours
    static String Red = "\u001b[31m";
    static String Green = "\u001b[32;2m";
    static String Yellow = "\u001b[33m";
    static String Reset = "\u001b[0m";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ManageDonees<Donee> doneeList = new ManageDonees<>();
            doneeList.loadFromFile("donees.txt");

            boolean running = true;

            while (running) {
                System.out.println("Choose an option:");
                System.out.println("1. Add a new donee");
                System.out.println("2. Remove a donee");
                System.out.println("3. Update donee details");
                System.out.println("4. Search donee details");
                System.out.println("5. List donees");
                System.out.println("6. Filter donee based on category");
//                System.out.println("7. Generate summary reports");
                System.out.println("8. Save Changes");
                System.out.println("9. Exit");
                System.out.print("\nEnter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // consume the newline
                System.out.println("");

                switch (choice) {
                    case 1 -> {
                        // Add a new donee
                        System.out.println("Enter donee type: ");
                        System.out.println("1. Individual ");
                        System.out.println("2. Family ");
                        System.out.println("3. Organization \n");
                        System.out.print("Enter your choice: ");
                        int type = scanner.nextInt();
                        scanner.nextLine(); // consume the newline

                        do {
                            switch (type) {
                                case 1 -> {
                                    String id = generateDoneeId(doneeList);
                                    System.out.print("Enter name: ");
                                    String name = scanner.nextLine();
                                    doneeList.insert(new Individual(id, name));
                                    System.out.println(Green + "\nDonee added with ID: " + Reset + id);
                                }
                                case 2 -> {
                                    String id = generateDoneeId(doneeList);
                                    System.out.print("Enter family name: ");
                                    String orgName = scanner.nextLine();
                                    doneeList.insert(new Family(id, orgName));
                                    System.out.println(Green + "\nDonee added with ID: " + Reset + id);
                                }
                                case 3 -> {
                                    String id = generateDoneeId(doneeList);
                                    System.out.print("Enter organization name: ");
                                    String orgName = scanner.nextLine();
                                    doneeList.insert(new Organization(id, orgName));
                                    System.out.println(Green + "\nDonee added with ID: " + Reset + id);
                                }
                                default -> {
                                    System.out.println(Red + "Invalid choice. Please only enter '1', '2' or '3'.\n" + Reset);
                                }
                            }
                        } while (type < 1 || type > 3);
                    }

                    case 2 -> {
                        // Remove a donee
                        System.out.print("Enter donee ID to remove: ");
                        String id = scanner.nextLine();
                        doneeList.deleteById(id);
                        System.out.println("Donee removed.");
                    }
                    case 3 -> {
                        // Update donee details
                        System.out.println("Enter donee ID to update: ");
                        String id = scanner.nextLine();
                        Donee doneeToUpdate = doneeList.findById(id);
                        if (doneeToUpdate != null) {
                            System.out.println("\nFound donee: \n\n" + doneeToUpdate.toString());
                            System.out.println("What would you like to update?");
                            System.out.println("1. Name");
                            System.out.println("2. Category \n");
                            System.out.print("Enter your choice: ");

                            int updateChoice = scanner.nextInt();
                            scanner.nextLine();

                            switch (updateChoice) {
                                case 1 -> {
                                    // Update Name
                                    do {
                                        System.out.print("\nEnter new donee name: ");
                                        String newName = scanner.nextLine().trim();
                                        doneeToUpdate.setName(newName);
                                        System.out.println(Green + "Name updated to " + newName + " successfully." + Reset);
                                        break;
                                    } while (true);
                                }
                                case 2 -> {
                                    // Update Category
                                    int newCategory = 0;
                                    do {
                                        System.out.println("Choose a new donee category: ");
                                        System.out.println("1. Individual ");
                                        System.out.println("2. Family ");
                                        System.out.println("3. Organization \n");
                                        System.out.print("Enter your choice: ");
                                        newCategory = scanner.nextInt();
                                        scanner.nextLine();

                                        switch (newCategory) {
                                            case 1 -> {
                                                doneeList.replace(doneeToUpdate, doneeList.changeToIndividual(doneeToUpdate));
                                                System.out.println(Green + "Category updated to individual successfully." + Reset);
                                            }
                                            case 2 -> {
                                                doneeList.replace(doneeToUpdate, doneeList.changeToFamily(doneeToUpdate));
                                                System.out.println(Green + "Category updated to family successfully." + Reset);
                                            }
                                            case 3 -> {
                                                doneeList.replace(doneeToUpdate, doneeList.changeToOrganization(doneeToUpdate));
                                                System.out.println(Green + "Category updated to organization successfully." + Reset);
                                            }

                                            default -> {
                                                System.out.println(Red + "Invalid category. Please only enter '1', '2', or '3'." + Reset);
                                            }
                                        }
                                    } while (newCategory < 1 || newCategory > 3);
                                }
                                default ->
                                    System.out.println(Red + "Invalid option. No changes made." + Reset);
                            }
                        } else {
                            System.out.println(Red + "Invalid ID. No donee found with the given ID." + Reset);
                        }
                    }
                    case 4 -> {
                        System.out.println("Search Donor by:");
                        System.out.println("1. Donee ID");
                        System.out.println("2. Donee Name \n");
                        System.out.print("Enter your choice: ");

                        String searchChoice = scanner.nextLine().trim();

                        switch (searchChoice) {
                            case "1" -> {
                                System.out.print("Enter donee ID to search: ");
                                String id = scanner.nextLine().trim();

                                Donee donee = doneeList.findById(id);
                                if (donee != null) {
                                    System.out.println("\nFound donee: \n\n" + donee.toString());
                                } else {
                                    System.out.println("No donee found with ID: " + Red + id + Reset);
                                }
                            }
                            case "2" -> {
                                System.out.print("Enter donee name to search: ");
                                String name = scanner.nextLine().trim().toLowerCase();

                                LinkedList<Donee> matchingDonees = new LinkedList<>();
                                Node<Donee> current = doneeList.head;

                                while (current != null) {
                                    if (current.data.getName().toLowerCase().contains(name)) {
                                        matchingDonees.insert(current.data);
                                    }
                                    current = current.next;
                                }

                                if (!matchingDonees.isEmpty()) {
                                    System.out.println("\nMatching donees found: \n");
                                    matchingDonees.show();
                                } else {
                                    System.out.println("No donees found with name containing: " + Red + name + Reset);
                                }
                            }
                            default ->
                                System.out.println(Red + "Invalid choice. Please enter '1' for ID search or '2' for Name search." + Reset);
                        }
                    }

                    case 5 -> {
                        // List donees with all donations made
                        doneeList.show();
                    }
                    case 6 -> {
                        // Filter donee based on criteria
                        int filterChoice = 0;
                        do {
                            System.out.println("1. Filter by Individual");
                            System.out.println("2. Filter by Family");
                            System.out.println("3. Filter by Organization \n");
                            System.out.print("Enter your choice: ");
                            filterChoice = scanner.nextInt();
                            scanner.nextLine(); // consume the newline
                            System.out.println("");

                            switch (filterChoice) {
                                case 1 -> {
                                    LinkedList<Individual> individualList = doneeList.filterByCategory(Individual.class);
                                    individualList.show();
                                }
                                case 2 -> {
                                    LinkedList<Family> familyList = doneeList.filterByCategory(Family.class);
                                    familyList.show();
                                }
                                case 3 -> {
                                    LinkedList<Organization> organizationList = doneeList.filterByCategory(Organization.class);
                                    organizationList.show();
                                }
                                default -> {
                                    System.out.println(Red + "Invalid category. Please only enter '1', '2', or '3'." + Reset);
                                }
                            }
                        } while (filterChoice < 1 || filterChoice > 3);
                    }
//                    case 7 -> {
//                        // Generate summary reports
//                        doneeList.generateSummaryReport();
//                    }
                    case 8 -> {
                        // Exit the program
                        doneeList.saveToFile("donees.txt");
                        System.out.println("Changes saved");
                    }
                    case 9 -> {
                        // Exit the program
                        running = false;
                        System.out.println("Exiting...");
                    }
                    default ->
                        System.out.println("Invalid option, please try again.");
                }

                System.out.println();
            }
        }
    }

    private static String generateDoneeId(ManageDonees<Donee> doneeList) {
        String prefix = "DNE";
        int maxId = 0;

        // Assuming ManageDonors extends LinkedList
        Node<Donee> current = doneeList.head; // Access the head of the linked list

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

    private static boolean isValidName(String name) {
        return name != null && name.matches("^[a-zA-Z\\s]+$") && name.length() >= 2 && name.length() <= 30;
    }
}
