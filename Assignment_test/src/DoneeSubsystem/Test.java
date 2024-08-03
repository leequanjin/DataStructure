/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoneeSubsystem;

import CommonResources.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Lee Quan Jin
 */
public class Test {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ManageDonees<Donee> doneeList = new ManageDonees<Donee>();
            doneeList.loadFromFile("donees.txt");

            boolean running = true;
            
            while (running) {
                System.out.println("Choose an option:");
                System.out.println("1. Add a new donee");
                System.out.println("2. Remove a donee");
//                System.out.println("3. Update donee details");
//                System.out.println("4. Search donee details");
                System.out.println("5. List donees");
                System.out.println("6. Filter donee based on criteria");
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
                        System.out.print("Enter donee type (1 for Individual, 2 for Organization): ");
                        int type = scanner.nextInt();
                        scanner.nextLine(); // consume the newline
                        
                        if (type == 1) {
                            System.out.print("Enter id: ");
                            String id = scanner.nextLine();
                            System.out.print("Enter name: ");
                            String name = scanner.nextLine();
                            doneeList.insert(new Individual(id, name));
                        } else if (type == 2) {
                            System.out.print("Enter id: ");
                            String id = scanner.nextLine();
                            System.out.print("Enter organization name: ");
                            String orgName = scanner.nextLine();
                            doneeList.insert(new Organization(id, orgName));
                        }
                        
                        System.out.println("Donee added.");
                    }
                    case 2 -> {
                        // Remove a donee
                        System.out.print("Enter donee ID to remove: ");
                        String id = scanner.nextLine();
                        doneeList.deleteById(id);
                        System.out.println("Donee removed.");
                    }
//                    case 3 -> {
//                        // Update donee details
//                        System.out.println("Enter donee ID to update: ");
//                        int id = scanner.nextInt();
//                        scanner.nextLine(); // consume the newline
//                        Donee donee = doneeList.findById(id);
//                        if (donee != null) {
//                            System.out.println("Enter new name: ");
//                            String newName = scanner.nextLine();
//                            donee.setName(newName);
//                            // Add additional fields as needed
//                            System.out.println("Donee updated.");
//                        } else {
//                            System.out.println("Donee not found.");
//                        }
//                    }
//                    case 4 -> {
//                        // Search donee details
//                        System.out.println("Enter donee name to search: ");
//                        String name = scanner.nextLine();
//                        Donee donee = doneeList.findByName(name);
//                        if (donee != null) {
//                            System.out.println("Donee found: " + donee);
//                        } else {
//                            System.out.println("Donee not found.");
//                        }
//                    }
                    case 5 -> {
                        // List donees with all donations made
                        doneeList.show();
                    }
                    case 6 -> {
                        // Filter donee based on criteria
                        System.out.println("1. Filter by Individual");
                        System.out.println("2. Filter by Organization");
                        System.out.print("Enter your choice: ");
                        int filterChoice = scanner.nextInt();
                        scanner.nextLine(); // consume the newline
                        
                        if (filterChoice == 1) {
                            LinkedList<Individual> individualList = doneeList.filterByCategory(Individual.class);
                            individualList.show();
                        } else if (filterChoice == 2) {
                            LinkedList<Organization> organizationList = doneeList.filterByCategory(Organization.class);
                            organizationList.show();
                        }
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
                    default -> System.out.println("Invalid option, please try again.");
                }
                
                System.out.println();
            }
        }
    }
}
