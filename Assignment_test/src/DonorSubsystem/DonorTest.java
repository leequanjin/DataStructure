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

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ManageDonors<Donor> donorList = new ManageDonors<>();
            donorList.loadFromFile("donors.txt");

            boolean running = true;
            
            while (running) {
                System.out.println("Choose an option:");
                System.out.println("1. Add a new donor");
                System.out.println("2. Remove a donor");
                System.out.println("3. List donors");
                System.out.println("4. Filter donors based on category");
                System.out.println("5. Save changes");
                System.out.println("6. Exit");
                System.out.print("\nEnter your choice: ");
                
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume the newline
                System.out.println("");
                
                switch (choice) {
                    case 1 -> {
                    System.out.print("Enter donor type (1 for Individual, 2 for Organization): ");
                    int type = scanner.nextInt();
                    scanner.nextLine(); // consume the newline
                    String id = generateDonorId(donorList);
                    String name;
                    
                    if (type == 1){
                    do {
                        System.out.print("Enter donor name: ");
                        name = scanner.nextLine();

                        if (!isValidName(name)) {
                            System.out.println("Invalid name. Please enter a valid name (only letters and spaces).");
                        }
                    } while (!isValidName(name));

                    System.out.print("Enter donor category (government, private, public): ");
                    String category = scanner.nextLine();
                    
                    donorList.insert(new Individual(id, name, category));    
                    
                    } else if (type == 2) {
                        do {
                        System.out.print("Enter organization name: ");
                        name = scanner.nextLine();

                        if (!isValidName(name)) {
                            System.out.println("Invalid name. Please enter a valid name (only letters and spaces).");
                        }
                    } while (!isValidName(name));

                    System.out.print("Enter donor category (government, private, public): ");
                    String category = scanner.nextLine();
                    
                    donorList.insert(new Organization(id, name, category));
                    }

                    System.out.println("\n1Donor added with ID: " + id);
                }

                    case 2 -> {
                        System.out.print("Enter donor ID to remove: ");
                        String id = scanner.nextLine();
                        donorList.deleteById(id);
                        System.out.println("Donor removed.");
                    }
                    case 3 -> {
                        donorList.show();
                    }
                    case 4 -> {
                        System.out.println("1. Filter by Individual");
                        System.out.println("2. Filter by Organization");
                        System.out.print("Enter your choice: ");
                        int filterChoice = scanner.nextInt();
                        scanner.nextLine();
                        
                        if (filterChoice == 1) {
                            LinkedList<Donor> individualList = donorList.filterByCategory(Individual.class);
                            individualList.show();
                        } else if (filterChoice == 2) {
                            LinkedList<Donor> organizationList = donorList.filterByCategory(Organization.class);
                            organizationList.show();
                        }
                    }
                    case 5 -> {
                        donorList.saveToFile("donors.txt");
                        System.out.println("Changes saved.");
                    }
                    case 6 -> {
                        running = false;
                        System.out.println("Exiting...");
                    }
                    default -> System.out.println("Invalid option, please try again.");
                }
                
                System.out.println();
            }
        }
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
            // Name should not be empty, should contain only letters and spaces, and be between 2 and 50 characters long
            return name != null && name.matches("^[a-zA-Z\\s]+$") && name.length() >= 2 && name.length() <= 50;
    }
}
