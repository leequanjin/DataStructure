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
public class ManageDoneeUI {

    //Colours
    static String Red = "\u001b[31m";
    static String Green = "\u001b[32;2m";
    static String Reset = "\u001b[0m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ManageDonee<Donee> doneeList = new ManageDonee<>();
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
            System.out.println("7. Save Changes");
            System.out.println("8. Generate Summary Report");
            System.out.println("9. Exit");
            System.out.print("\nEnter your choice: ");

            String choice = scanner.nextLine();
            System.out.println("");

            switch (choice) {
                case "1" -> {
                    // Add a new donee
                    do {
                        System.out.println("Enter donee type: ");
                        System.out.println("1. Individual ");
                        System.out.println("2. Family ");
                        System.out.println("3. Organization \n");
                        System.out.print("Enter your choice: ");
                        choice = scanner.nextLine();

                        switch (choice) {
                            case "1" -> {
                                String id = generateDoneeId(doneeList);
                                String name = null;
                                while (isEmpty(name)) {
                                    System.out.print("Enter name: ");
                                    name = scanner.nextLine().trim();
                                    if (isEmpty(name)) {
                                        System.out.println(Red + "Name cannot be empty!" + Reset);
                                    }
                                }
                                String state = selectState();

                                doneeList.insertAtStart(new Individual(id, name, state));
                                System.out.println(Green + "Individual added with Donee ID: " + id + Reset);
                            }
                            case "2" -> {
                                String id = generateDoneeId(doneeList);
                                String name = null;
                                while (isEmpty(name)) {
                                    System.out.print("Enter family name: ");
                                    name = scanner.nextLine().trim();
                                    if (isEmpty(name)) {
                                        System.out.println(Red + "Name cannot be empty!" + Reset);
                                    }
                                }
                                String state = selectState();
                                doneeList.insertAtStart(new Family(id, name, state));
                                System.out.println(Green + "Family added with Donee ID: " + id + Reset);
                            }
                            case "3" -> {
                                String id = generateDoneeId(doneeList);
                                String name = null;
                                while (isEmpty(name)) {
                                    System.out.print("Enter organization name: ");
                                    name = scanner.nextLine().trim();
                                    if (isEmpty(name)) {
                                        System.out.println(Red + "Name cannot be empty!" + Reset);
                                    }
                                }
                                String state = selectState();
                                doneeList.insertAtStart(new Organization(id, name, state));
                                System.out.println(Green + "Organization added with Donee ID: " + id + Reset);
                            }
                            default -> {
                                System.out.println(Red + "Invalid choice. Please only enter '1', '2' or '3'.\n" + Reset);
                            }
                        }
                    } while (!"1".equals(choice) && !"2".equals(choice) && !"3".equals(choice));
                }
                case "2" -> {
                    // Remove a donee
                    System.out.print("Enter donee ID to remove: ");
                    String id = scanner.nextLine();
                    Donee doneeToRemove = doneeList.findById(id);
                    if (doneeToRemove != null) {
                        doneeList.deleteById(id);
                        System.out.println(Green + "Donee (" + doneeToRemove.getName() + ") was removed successfully." + Reset);
                    } else {
                        System.out.println(Red + "Invalid ID. No donee found with the given ID." + Reset);
                    }
                }
                case "3" -> {
                    // Update donee details
                    System.out.print("Enter donee ID to update: ");
                    String id = scanner.nextLine();
                    Donee doneeToUpdate = doneeList.findById(id);
                    if (doneeToUpdate != null) {
                        System.out.println(Green + "\nFound donee: " + Reset);
                        System.out.printf("%-15s |%-30s |%-15s |%-15s |%s\n", "ID", "Name", "Type", "Location", "Registration Date");
                        String line = String.format("-").repeat(100);
                        System.out.println(line);
                        System.out.println(doneeToUpdate.toString() + "\n");
                        System.out.println("What would you like to update?");
                        System.out.println("1. Name");
                        System.out.println("2. Category");
                        System.out.println("3. Location \n");
                        System.out.print("Enter your choice: ");

                        choice = scanner.nextLine();

                        switch (choice) {
                            case "1" -> {
                                // Update Name
                                do {
                                    System.out.print("\nEnter new donee name: ");
                                    String newName = scanner.nextLine().trim();
                                    if (!isEmpty(newName)) {
                                        doneeToUpdate.setName(newName);
                                        System.out.println(Green + "Name updated to " + newName + " successfully." + Reset);
                                        break;
                                    } else {
                                        System.out.println(Red + "Name cannot be empty!" + Reset);
                                    }
                                } while (true);
                            }
                            case "2" -> {
                                // Update Category
                                do {
                                    System.out.println("Choose a new donee category: ");
                                    System.out.println("1. Individual ");
                                    System.out.println("2. Family ");
                                    System.out.println("3. Organization \n");
                                    System.out.print("Enter your choice: ");
                                    choice = scanner.nextLine();

                                    switch (choice) {
                                        case "1" -> {
                                            doneeList.replace(doneeToUpdate, doneeList.changeToIndividual(doneeToUpdate));
                                            System.out.println(Green + "Category updated to individual successfully." + Reset);
                                        }
                                        case "2" -> {
                                            doneeList.replace(doneeToUpdate, doneeList.changeToFamily(doneeToUpdate));
                                            System.out.println(Green + "Category updated to family successfully." + Reset);
                                        }
                                        case "3" -> {
                                            doneeList.replace(doneeToUpdate, doneeList.changeToOrganization(doneeToUpdate));
                                            System.out.println(Green + "Category updated to organization successfully." + Reset);
                                        }

                                        default -> {
                                            System.out.println(Red + "Invalid category. Please only enter '1', '2', or '3'." + Reset);
                                        }
                                    }
                                } while (!"1".equals(choice) && !"2".equals(choice) && !"3".equals(choice));
                            }
                            case "3" -> {
                                line = String.format("-").repeat(64);
                                System.out.println(line);
                                System.out.printf("|%-20s|%-20s|%-20s|\n|%-20s|%-20s|%-20s|\n|%-20s|%-20s|%-20s|\n|%-20s|%-20s|%-20s|\n|%-20s|%-20s|%-20s|\n",
                                        "1. Johor",
                                        "2. Kedah",
                                        "3. Kelantan",
                                        "4. Melaka",
                                        "5. Negeri Sembilan",
                                        "6. Pahang",
                                        "7. Penang",
                                        "8. Perak",
                                        "9. Perlis",
                                        "10. Sabah",
                                        "11. Sarawak",
                                        "12. Selangor",
                                        "13. Terengganu", "", "");
                                System.out.println(line);
                                String state = null;
                                do {
                                    System.out.print("Select your State: ");
                                    state = scanner.nextLine();
                                    switch (state) {
                                        case "1" -> {
                                            state = "Johor";
                                        }
                                        case "2" -> {
                                            state = "Kedah";
                                        }
                                        case "3" -> {
                                            state = "Kelantan";
                                        }
                                        case "4" -> {
                                            state = "Melaka";
                                        }
                                        case "5" -> {
                                            state = "Negeri Sembilan";
                                        }
                                        case "6" -> {
                                            state = "Pahang";
                                        }
                                        case "7" -> {
                                            state = "Penang";
                                        }
                                        case "8" -> {
                                            state = "Perak";
                                        }
                                        case "9" -> {
                                            state = "Perlis";
                                        }
                                        case "10" -> {
                                            state = "Sabah";
                                        }
                                        case "11" -> {
                                            state = "Sarawak";
                                        }
                                        case "12" -> {
                                            state = "Selangor";
                                        }
                                        case "13" -> {
                                            state = "Terengganu";
                                        }
                                        default -> {
                                            System.out.println(Red + "Invalid Input : Please only enter a value between 1-13 or the state name" + Reset);
                                        }
                                    }
                                } while (!"Johor".equals(state)
                                        && !"Kedah".equals(state)
                                        && !"Kelantan".equals(state)
                                        && !"Melaka".equals(state)
                                        && !"Negeri Sembilan".equals(state)
                                        && !"Pahang".equals(state)
                                        && !"Penang".equals(state)
                                        && !"Perak".equals(state)
                                        && !"Perlis".equals(state)
                                        && !"Sabah".equals(state)
                                        && !"Sarawak".equals(state)
                                        && !"Selangor".equals(state)
                                        && !"Terengganu".equals(state));
                                doneeToUpdate.setLocation(state);
                                System.out.println(Green + "State updated to " + state + " successfully." + Reset);
                            }
                            default ->
                                System.out.println(Red + "Invalid option. No changes made." + Reset);
                        }
                    } else {
                        System.out.println(Red + "Invalid ID. No donee found with the given ID." + Reset);
                    }
                }
                case "4" -> {
                    do {
                        System.out.println("Search Donor by:");
                        System.out.println("1. Donee ID");
                        System.out.println("2. Donee Name \n");
                        System.out.print("Enter your choice: ");

                        choice = scanner.nextLine().trim();

                        switch (choice) {
                            case "1" -> {
                                System.out.print("Enter donee ID to search: ");
                                String id = scanner.nextLine().trim();

                                Donee donee = doneeList.findById(id);
                                if (donee != null) {
                                    System.out.println(Green + "\nFound donee: " + Reset);
                                    System.out.printf("%-15s |%-30s |%-15s |%-15s |%s\n", "ID", "Name", "Type", "Location", "Registration Date");
                                    String line = String.format("-").repeat(100);
                                    System.out.println(line);
                                    System.out.println(donee.toString() + "\n");
                                } else {
                                    System.out.println(Red + "\nNo donee found with ID: " + id + Reset);
                                }
                            }
                            case "2" -> {
                                System.out.print("Enter donee name to search: ");
                                String name = scanner.nextLine().trim().toLowerCase();

                                LinkedList<Donee> matchingDonees = new LinkedList<>();
                                Node<Donee> current = doneeList.head;

                                while (current != null) {
                                    if (current.data.getName().toLowerCase().contains(name)) {
                                        matchingDonees.insertAtStart(current.data);
                                    }
                                    current = current.next;
                                }

                                if (!matchingDonees.isEmpty()) {
                                    System.out.print(Green + "\nMatching donees found: " + Reset);
                                    matchingDonees.show();
                                } else {
                                    System.out.println(Red + "\nNo donees found with name containing: " + name + Reset);
                                }
                            }
                            default ->
                                System.out.println(Red + "Invalid choice. Please enter '1' for ID search or '2' for Name search.\n " + Reset);
                        }
                    } while (!"1".equals(choice) && !"2".equals(choice));
                }
                case "5" -> {
                    // List donees with all donations made
                    System.out.println(Green + "List of all Donees: " + Reset);
                    System.out.printf("%-15s |%-30s |%-15s |%-15s |%s\n", "ID", "Name", "Type", "Location", "Registration Date");
                    String line = String.format("-").repeat(100);
                    System.out.println(line);
                    doneeList.show();
                }
                case "6" -> {
                    // Filter donee based on criteria
                    do {
                        System.out.println("1. Filter by Individual");
                        System.out.println("2. Filter by Family");
                        System.out.println("3. Filter by Organization \n");
                        System.out.print("Enter your choice: ");
                        choice = scanner.nextLine();

                        switch (choice) {
                            case "1" -> {
                                LinkedList<Individual> individualList = doneeList.filterByCategory(Individual.class);
                                System.out.println(Green + "\nList of all Individual Donees: " + Reset);
                                System.out.printf("%-15s |%-30s |%-15s |%-15s |%s\n", "ID", "Name", "Type", "Location", "Registration Date");
                                String line = String.format("-").repeat(100);
                                System.out.println(line);
                                individualList.show();
                            }
                            case "2" -> {
                                LinkedList<Family> familyList = doneeList.filterByCategory(Family.class);
                                System.out.println(Green + "\nList of all Family Donees: " + Reset);
                                System.out.printf("%-15s |%-30s |%-15s |%-15s |%s\n", "ID", "Name", "Type", "Location", "Registration Date");
                                String line = String.format("-").repeat(100);
                                System.out.println(line);
                                familyList.show();
                            }
                            case "3" -> {
                                LinkedList<Organization> organizationList = doneeList.filterByCategory(Organization.class);
                                System.out.println(Green + "\nList of all Organization Donees: " + Reset);
                                System.out.printf("%-15s |%-30s |%-15s |%-15s |%s\n", "ID", "Name", "Type", "Location", "Registration Date");
                                String line = String.format("-").repeat(100);
                                System.out.println(line);
                                organizationList.show();
                            }
                            default -> {
                                System.out.println(Red + "Invalid category. Please only enter '1', '2', or '3'.\n" + Reset);
                            }
                        }
                    } while (!"1".equals(choice) && !"2".equals(choice) && !"3".equals(choice));
                }
                case "7" -> {
                    // Save changes to the file
                    doneeList.saveToFile("donees.txt");
                    System.out.println(Green + "Changes saved sucessfully..." + Reset);
                }
                case "8" -> {
                    do {
                        System.out.println("1. Number of new donees by year");
                        System.out.println("2. Number of new donees by month");
                        System.out.println("3. Number of donees by state \n");
                        System.out.print("Enter your choice: ");
                        choice = scanner.nextLine();

                        switch (choice) {
                            case "1" -> {
                                LinkedList doneeListByYear = doneeList.generateTotalDoneeByYear();
                                System.out.println(Green + "\nNumber of new donees by year report: " + Reset);
                                System.out.printf("%-15s |%-20s |%-20s |%s\n", "Year", "Individuals", "Families", "Organizations");
                                String line = String.format("-").repeat(74);
                                System.out.println(line);
                                doneeListByYear.show();
                            }
                            case "2" -> {
                                LinkedList doneeListByMonth = doneeList.generateTotalDoneeByMonth();
                                System.out.println(Green + "\nNumber of new donees by month report: " + Reset);
                                System.out.printf("%-15s |%-20s |%-20s |%s\n", "Year", "Individuals", "Families", "Organizations");
                                String line = String.format("-").repeat(74);
                                System.out.println(line);
                                doneeListByMonth.show();
                            }
                            case "3" -> {
                                DoneeStateCount doneeListByState = doneeList.generateTotalDoneeByState();
                                System.out.println(Green + "\nNumber of donees by state: " + Reset);
                                String line = String.format("-").repeat(25);
                                System.out.println(line);
                                System.out.println(doneeListByState.toString());
                            }

                            default -> {
                                System.out.println(Red + "Invalid category. Please only enter '1', '2' or '3'.\n" + Reset);
                            }
                        }
                    } while (!"1".equals(choice) && !"2".equals(choice) && !"3".equals(choice));
                }
                case "9" -> {
                    // Exit the program
                    running = false;
                    System.out.println(Green + "Exiting..." + Reset);
                }
                default ->
                    System.out.println(Red + "Invalid option, please try again." + Reset);
            }

            System.out.println();
        }
    }

    private static String generateDoneeId(ManageDonee<Donee> doneeList) {
        String prefix = "DNE";
        int maxId = 0;

        Node<Donee> current = doneeList.head;

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

    private static boolean isEmpty(String string) {
        return string == null;
    }

    private static String selectState() {
        Scanner scanner = new Scanner(System.in);
        String line = String.format("-").repeat(64);
        System.out.println(line);
        System.out.printf("|%-20s|%-20s|%-20s|\n|%-20s|%-20s|%-20s|\n|%-20s|%-20s|%-20s|\n|%-20s|%-20s|%-20s|\n|%-20s|%-20s|%-20s|\n",
                "1. Johor",
                "2. Kedah",
                "3. Kelantan",
                "4. Melaka",
                "5. Negeri Sembilan",
                "6. Pahang",
                "7. Penang",
                "8. Perak",
                "9. Perlis",
                "10. Sabah",
                "11. Sarawak",
                "12. Selangor",
                "13. Terengganu", "", "");
        System.out.println(line);
        String state = null;
        do {
            System.out.print("Select your State: ");
            state = scanner.nextLine();
            switch (state) {
                case "1" -> {
                    state = "Johor";
                }
                case "2" -> {
                    state = "Kedah";
                }
                case "3" -> {
                    state = "Kelantan";
                }
                case "4" -> {
                    state = "Melaka";
                }
                case "5" -> {
                    state = "Negeri Sembilan";
                }
                case "6" -> {
                    state = "Pahang";
                }
                case "7" -> {
                    state = "Penang";
                }
                case "8" -> {
                    state = "Perak";
                }
                case "9" -> {
                    state = "Perlis";
                }
                case "10" -> {
                    state = "Sabah";
                }
                case "11" -> {
                    state = "Sarawak";
                }
                case "12" -> {
                    state = "Selangor";
                }
                case "13" -> {
                    state = "Terengganu";
                }

                default -> {
                    System.out.println(Red + "Invalid Input : Please only enter a value between 1-13 or the state name" + Reset);
                }
            }
        } while (!"Johor".equals(state)
                && !"Kedah".equals(state)
                && !"Kelantan".equals(state)
                && !"Melaka".equals(state)
                && !"Negeri Sembilan".equals(state)
                && !"Pahang".equals(state)
                && !"Penang".equals(state)
                && !"Perak".equals(state)
                && !"Perlis".equals(state)
                && !"Sabah".equals(state)
                && !"Sarawak".equals(state)
                && !"Selangor".equals(state)
                && !"Terengganu".equals(state));
        return state;
    }
}
