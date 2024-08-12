/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DonorSubsystem;

import CommonResources.LinkedList;
import CommonResources.Node;
import java.util.Scanner;

/**
 *
 * @author Kee Ke Shen
 */

public class DonorTest {

    //Colours
    static String Red = "\u001b[31m";
    static String Green = "\u001b[32;2m";
    static String Yellow = "\u001b[33m";
    static String Reset = "\u001b[0m";
    
    //Example use case
    //System.out.println(Red + "Red Text" + Reset);
    
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ManageDonors<Donor> donorList = new ManageDonors<>();
            donorList.loadFromFile("donors.txt");

            boolean running = true;
            
            while (running) {
                int choice = getValidMenuChoice(scanner);
                System.out.println("");
                
                switch (choice) {
                    case 1 -> {
                    int type;
                    do {
                        System.out.print("Enter donor type (1 for Individual, 2 for Organization): ");
                        String typeInput = scanner.nextLine(); 

                        if (typeInput.matches("[12]")) { // Check if input is '1' or '2'
                            type = Integer.parseInt(typeInput);
                            break;
                        } else {
                            System.out.println(Red + "Invalid choice. Please enter '1' for Individual or '2' for Organization.\n" + Reset);
                        }
                    } while (true);
                    
                    String id = generateDonorId(donorList);
                    String name;
                    String category;
                    
                    if (type == 1){
                    do {
                        System.out.print("Enter donor name: ");
                        name = scanner.nextLine().trim();

                        if (!isValidName(name)) {
                            System.out.println(Red + "Invalid name. Please enter a valid name (only letters and spaces) and between 2 and 30 characters long.\n" + Reset);
                        }
                    } while (!isValidName(name));

                    do {
                        System.out.print("Enter donor category (Government, Private, Public): ");
                        category = scanner.nextLine();

                        if (!isValidCategory(category)) {
                            System.out.println(Red + "Invalid category. Please enter 'Government', 'Private', or 'Public'.\n" + Reset);
                        }
                    } while (!isValidCategory(category));
                    
                    donorList.insert(new Individual(id, name, category));    
                    
                    } else if (type == 2) {
                        do {
                        System.out.print("Enter organization name: ");
                        name = scanner.nextLine().trim();

                        if (!isValidName(name)) {
                            System.out.println(Red + "Invalid name. Please enter a valid name (only letters and spaces) and between 2 and 30 characters long.\n" + Reset);
                        }
                    } while (!isValidName(name));

                    do {
                        System.out.print("Enter donor category (Government, Private, Public): ");
                        category = scanner.nextLine().trim();

                        if (!isValidCategory(category)) {
                            System.out.println(Red + "Invalid category. Please enter 'Government', 'Private', or 'Public'.\n" + Reset);
                        }
                    } while (!isValidCategory(category));
                    
                    donorList.insert(new Organization(id, name, category));
                    }

                    System.out.println(Green + "\nDonor added with ID: " + Reset + id);
                }

                    case 2 -> {
                        donorList.show();
                        System.out.print("Enter donor ID to remove: ");
                        String id = scanner.nextLine().trim();
                        // Validate if the ID exists in the donor list
                            Donor donorToRemove = donorList.findById(id);
                            if (donorToRemove != null) {
                                donorList.deleteById(id);
                                System.out.println(Green + "Donor removed." + Reset);
                            } else {
                                System.out.println(Red + "Invalid ID. No donor found with the given ID." + Reset);
                            }
                    }
                    case 3 -> {
                        donorList.show();
                        System.out.print("Enter donor ID to update: ");
                        String id = scanner.nextLine().trim();

                        // Find the donor by ID
                        Donor donorToUpdate = donorList.findById(id);
                        if (donorToUpdate != null) {
                            // Ask the user what they want to update
                            System.out.println("Found donor: " + Yellow + donorToUpdate + Reset);
                            System.out.println("What would you like to update?");
                            System.out.println("1. Name");
                            System.out.println("2. Category");
                            System.out.println("3. Both");
                            System.out.print("Enter your choice: ");

                            String updateChoice = scanner.nextLine().trim();

                            switch (updateChoice) {
                                case "1" -> {
                                    // Update Name
                                    do {
                                        System.out.print("\nEnter new donor name: ");
                                        String newName = scanner.nextLine().trim();
                                        if (isValidName(newName)) {
                                            donorToUpdate.setName(newName);
                                            System.out.println(Green + "Name updated successfully." + Reset);
                                            break;
                                        } else {
                                            System.out.println(Red + "Invalid name. Please enter a valid name (only letters and spaces) and between 2 and 30 characters long." + Reset);
                                        }
                                    } while (true);
                                }
                                case "2" -> {
                                    // Update Category
                                    do {
                                        System.out.print("\nEnter new donor category (Government, Private, Public): ");
                                        String newCategory = scanner.nextLine().trim();
                                        if (isValidCategory(newCategory)) {
                                            donorToUpdate.setCategory(newCategory);
                                            System.out.println(Green + "Category updated successfully." + Reset);
                                            break;
                                        } else {
                                            System.out.println(Red + "Invalid category. Please enter 'Government', 'Private', or 'Public'." + Reset);
                                        }
                                    } while (true);
                                }
                                case "3" -> {
                                    // Update Both
                                    do {
                                        System.out.print("\nEnter new donor name: ");
                                        String newName = scanner.nextLine().trim();
                                        if (isValidName(newName)) {
                                            donorToUpdate.setName(newName);
                                            break;
                                        } else {
                                            System.out.println(Red + "Invalid name. Please enter a valid name (only letters and spaces) and between 2 and 30 characters long." + Reset);
                                        }
                                    } while (true);

                                    do {
                                        System.out.print("Enter new donor category (Government, Private, Public): ");
                                        String newCategory = scanner.nextLine().trim();
                                        if (isValidCategory(newCategory)) {
                                            donorToUpdate.setCategory(newCategory);
                                            System.out.println(Green + "Category updated successfully." + Reset);
                                            break;
                                        } else {
                                            System.out.println(Red + "Invalid category. Please enter 'Government', 'Private', or 'Public'.\n" + Reset);
                                        }
                                    } while (true);

                                    System.out.println(Green + "Donor details updated successfully." + Reset);
                                }
                                default -> System.out.println(Red + "Invalid option. No changes made." + Reset);
                            }
                        } else {
                            System.out.println(Red + "Invalid ID. No donor found with the given ID." + Reset);
                        }
                    }
                    
                    case 4 -> {
                        System.out.println("Search Donor by:");
                        System.out.println("1. Donor ID");
                        System.out.println("2. Donor Name");
                        System.out.print("Enter your choice: ");

                        String searchChoice = scanner.nextLine().trim();

                        switch (searchChoice) {
                            case "1" -> {
                                System.out.print("Enter donor ID to search: ");
                                String id = scanner.nextLine().trim();

                                Donor donor = donorList.findById(id);
                                if (donor != null) {
                                    System.out.println("Donor found: " + Yellow + donor + Reset);
                                } else {
                                    System.out.println("No donor found with ID: " + Red + id + Reset);
                                }
                            }
                            case "2" -> {
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
                                    System.out.println("Matching donors found:" + Yellow);
                                    matchingDonors.show();
                                    System.out.println(Reset);
                                } else {
                                    System.out.println("No donors found with name containing: " + Red + name + Reset);
                                }
                            }
                            default -> System.out.println(Red + "Invalid choice. Please enter '1' for ID search or '2' for Name search." + Reset);
                        }
                    }
                    
                    case 5 -> {
                        if (donorList.isEmpty()) {
                            System.out.println(Red + "No donors found in the system." + Reset);
                        } else {
                            donorList.show();
                        }
                    }
                    
                    case 6 -> {
                        int type;
                        do {
                            System.out.println("1. Filter by Individual");
                            System.out.println("2. Filter by Organization");
                            System.out.print("Enter your choice: ");
                            String filterChoice = scanner.nextLine().trim();
                            
                            if (filterChoice.matches("[12]")) { // Check if input is '1' or '2'
                                type = Integer.parseInt(filterChoice);
                                break;
                            } else {
                                 System.out.println(Red + "Invalid choice. Please enter '1' to show Individual DonorList or '2' for Organization DonorList.\n" + Reset);
                            }
                        }while (true);
                            
                        if (type == 1) {
                            LinkedList<Donor> individualList = donorList.filterByCategory(Individual.class);
                            if (individualList.isEmpty()){
                                System.out.println(Red + "\nNo Individual donors found in the system." + Reset);
                            } else {
                                individualList.show();
                            }
                        } else if (type == 2) {
                            LinkedList<Donor> organizationList = donorList.filterByCategory(Organization.class);
                            if (organizationList.isEmpty()){
                                System.out.println(Red + "\nNo Organization donors found in the system." + Reset);
                            } else {
                                organizationList.show();
                            }
                        }
                    }
                    
                    case 7 -> {
                        donorList.saveToFile("donors.txt");
                        System.out.println(Green + "Changes saved." + Reset);
                    }
                    
                    case 8 -> {
                        running = false;
                        System.out.println(Red + "Exiting..." + Reset);
                    }
                    
                    default -> System.out.println(Red + "Invalid option, please try again.\n" + Reset);
                }
                
                System.out.println();
            }
        }
    }
         private static int getValidMenuChoice(Scanner scanner) {
            int choice = -1;
            boolean validInput = false;

            while (!validInput) {
                System.out.println("Choose an option:");
                System.out.println("1. Add a new donor");
                System.out.println("2. Remove a donor");
                System.out.println("3. Update donor details");
                System.out.println("4. Search donor details");
                System.out.println("5. List donors");
                System.out.println("6. Filter donors based on category");
                System.out.println("7. Save changes");
                System.out.println("8. Exit");
                System.out.print("\nEnter your choice: ");

                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    System.out.println(Red + "Invalid input. Please enter a number between 1 and 6.\n " + Reset);
                } else {
                    try {
                        choice = Integer.parseInt(input);
                        if (choice >= 1 && choice <= 8) {
                            validInput = true;
                        } else {
                            System.out.println(Red + "Invalid choice. Please choose a number between 1 and 6.\n" + Reset);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(Red + "Invalid input. Please enter a valid number.\n" + Reset);
                    }
                }
            }

            return choice;
}
         
        private static String generateDonorId(ManageDonors<Donor> donorList) {
            String prefix = "DNR";
            int maxId = 0;

            // Assuming ManageDonors extends LinkedList
            Node<Donor> current = donorList.head; // Access the head of the linked list
            
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
            // Name should not be empty, should contain only letters and spaces, and be between 2 and 30 characters long
            return name != null && name.matches("^[a-zA-Z\\s]+$") && name.length() >= 2 && name.length() <= 30;
    }
        
        private static boolean isValidCategory(String category) {
            return category.equalsIgnoreCase("Government") || 
                   category.equalsIgnoreCase("Private") || 
                   category.equalsIgnoreCase("Public");
    }
}
