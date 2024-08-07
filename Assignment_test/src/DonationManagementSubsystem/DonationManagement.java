/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DonationManagementSubsystem;

import CommonResources.Node;
import CommonResources.LinkedList;
import CommonResources.Queue;

import DonorSubsystem.Donor;
import DonorSubsystem.Individual;
import DonorSubsystem.Organization;
import DonorSubsystem.Family;

import java.io.File;
import java.io.IOException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DonationManagement {
    
    private static final String ITEM_PATH = "C:\\Users\\Asus\\Desktop\\DS_Assign\\DataStructure\\DonationListFile\\item.txt";
    private static final String DONOR_PATH = "C:\\Users\\Asus\\Desktop\\DS_Assign\\DataStructure\\DonationListFile\\donor.txt";
    
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";
    
    public static void main(String[] args) {
        
        chkAllFileExist();
        //addRecord(); //alr store one individual, family and organisation in donor.txt
        
        Scanner scan = new Scanner(System.in);
        
        boolean contDM = true;
        
        while(contDM == true){
        
            System.out.println(ANSI_BLUE + " - - - Donation Management - - - " + ANSI_RESET);
            System.out.println("1. Add a new donation\n"
                    + "2. Remove a donation\n"
                    + "3. Search donation details\n"
                    + "4. Amend donation details\n"
                    + "5. Track donated item in categories\n"
                    + "6. List donation by different donor\n"
                    + "7. List all donation\n"
                    + "8. Filter donation base on criteria\n"
                    + "9. Generate sumary report\n"
                    + "10. Exit\n");
            System.out.print("Enter your choice: ");

            int dmChoice = 0;
            boolean validDMChoice = false;

            while (!validDMChoice) {
                try {
                    dmChoice = scan.nextInt();
                    scan.nextLine();

                    if (dmChoice < 1 || dmChoice > 10) {
                        System.out.println(ANSI_RED + "Please enter a number between 1 and 10.\n" + ANSI_RESET);
                        System.out.print("Enter again: ");
                    } else {
                        validDMChoice = true; 
                    }

                } catch (InputMismatchException e) {
                    System.out.println(ANSI_RED + "Invalid input. Please enter an integer number.\n" + ANSI_RESET);
                    scan.nextLine();
                    System.out.print("Enter again: ");
                }
            }

            // clear screen
            
            switch (dmChoice) {
                case 1:
                    addDonation();
                    break;
                case 2:
                    remDonation();
                    break;
                case 3:
                    searchDonation();
                    break;
                case 4:
                    amendDonation();
                    break;
                case 5:
                    trackItemByCategory();
                    break;
                case 6:
                    listByDiffDonor();
                    break;
                case 7:
                    listAll();
                    break;
                case 8:
                    filterDonation();
                    break;
                case 9:
                    report();
                    break;
                case 10:
                    System.exit(0); // later redirect to previous menu
                default:
                    System.out.println("Invalid choice.");
            }
            
            contDM = YN("Do you want to continue manage donation?");
            if (contDM == true){
                System.out.println(); // further do if return true then need clear screen
            }
        }
    }
    
    public static void addRecord(){
        // instance individual
        Individual a = new Individual("EI00000", "Anonymous"); 
        Individual idv = new Individual("EI00001", "HAHA"); 
        
        // instance individual
        Family fam = new Family("EFI00001", "OUO Family"); 
        
        // instance organization
        Organization org = new Organization("EO00001", "OMO Company");
        
        // save into list
        LinkedList dList = new LinkedList();
        dList.insert(a);
        dList.insert(idv);
        dList.insert(fam);
        dList.insert(org);
        
        //idvList.show();
        
        //load into file
        dList.saveToFile(DONOR_PATH);
    }
    
    // Common Use Part  
    public static void chkAllFileExist(){
        chkFileExist(ITEM_PATH);
        chkFileExist(DONOR_PATH);
    }
    
    // Create file if file not exist
    public static void chkFileExist(String filePath) {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("File created: " + filePath);
            }
        } catch (IOException e) {
            System.out.println(ANSI_RED + "Error creating file: " + e.getMessage() + ANSI_RESET);
        }
    }
    
    public static boolean YN(String sentence) {
        Scanner scan = new Scanner(System.in);
        
        boolean validInput = false;
        String input;
        
        System.out.print("\n" + sentence + "\nPlease enter Y / N: ");

        while (!validInput) {

            input = (scan.nextLine()).toUpperCase();
            
            if (input.equals("Y")) {
                validInput = true;
                return true;
            } else if (input.equals("N")) {
                validInput = true;
                return false;
            } else {
                System.out.println(ANSI_RED + "Please enter Y or N only.\n"+ ANSI_RESET);
                System.out.print("Enter again: ");
            }
        }
        return false;
    }
    
    // Part 1: Add new donation
    public static void addDonation(){
        Scanner scan = new Scanner(System.in);
        
        boolean contAddDonation = true;
        while(contAddDonation){
            
            System.out.println(ANSI_BLUE + " - - - Add Donation - - - " + ANSI_RESET);

            System.out.println("Type of donor\n"
                    + "1. Individual\n"
                    + "2. Family\n"
                    + "3. Organisation");
            System.out.print("Enter donor's type: ");
            int dType = 0;
            boolean validDType = false;
            while(validDType == false){
                    try {
                        dType = scan.nextInt();

                        if (dType < 1 || dType > 3) {
                            System.out.println(ANSI_RED + "Please enter a number between 1 and 3.\n" + ANSI_RESET);
                            
                            scan.nextLine();
                            System.out.print("Enter again: ");
                        } else {
                            validDType = true; 
                        }

                    } catch (InputMismatchException e) {
                        System.out.println(ANSI_RED + "Invalid input. Please enter an integer number.\n" + ANSI_RESET);
                        
                        scan.nextLine();
                        System.out.print("Enter again: ");
                    }
            }

            scan.nextLine();
            System.out.print("Enter donor's id: ");
            String dID = null;
            boolean validID = false;
            while(validID == false){
                dID = scan.next();

                if(dID.isEmpty()){
                    System.out.println(ANSI_RED + "Cannot leave blank.\n" + ANSI_RESET);
                    
                    scan.nextLine();
                    System.out.print("Enter again: ");
                } else if(dID.length() != 7){
                    System.out.println(ANSI_RED + "Invalid length. Format of ID should be AA00000.\n" + ANSI_RESET);
                    
                    scan.nextLine();
                    System.out.print("Enter again: ");
                } else {
                    switch(dType){
                        case 1: // individual (EI)
                            if (dID.substring(0, 2).equals("EI")) {
                                validID = true;
                            } else {
                                System.out.println(ANSI_RED + "Invalid format. Format of individual donor should be EI00000.\n" + ANSI_RESET);
                                System.out.print("Enter again: ");
                            }
                            break;
                        case 2: // family (EF)
                            if (dID.substring(0, 2).equals("EF")) {
                                validID = true;
                            } else {
                                System.out.println(ANSI_RED + "Invalid format. Format of family donor should be EF00000.\n" + ANSI_RESET);
                                System.out.print("Enter again: ");
                            }
                            break;
                        case 3: // organization (EO)
                            if (dID.substring(0, 2).equals("EO")) {
                                validID = true;
                            } else {
                                System.out.println(ANSI_RED + "Invalid format. Format of organization donor should be EO00000.\n" + ANSI_RESET);
                                System.out.print("Enter again: ");
                            }
                            break;
                        default:
                            System.out.println(ANSI_RED + "Unknown donor type.\n" + ANSI_RESET);
                            System.out.print("Enter again: ");
                    }
                }
            }

            // check if donor exist
            LinkedList<Donor> donorList = new LinkedList<>();
            boolean validDonor = chkDonorExist(dType, dID, donorList);

            // if exist, show current data 
            if(validDonor == true){
                System.out.println("\n - - - Current Donor - - -");
                donorList.show();
            }else{ // if does not exist, create or break
                System.out.println(ANSI_RED + "Donor does not exist.\n" + ANSI_RESET);
                contAddDonation = YN("Do you want to continue sign up a new donor?");
                if(contAddDonation == true){
                    // create a new one then load into the linkedlist
                } else{
                    break;
                }
            }
            
            contAddDonation = YN("Do you want to continue adding another donation for other donor?");
            if(contAddDonation == true){
                System.out.println(); // clear screen 
            } 
        }
        
    }
    
    public static boolean chkDonorExist(int dType, String dID, LinkedList donorList){
        LinkedList<Donor> tempDonorList = new LinkedList<>();
        
        tempDonorList.loadFromFile(DONOR_PATH);
        
        if (!tempDonorList.isEmpty()){
//            System.out.println();
//            tempDonorList.show();
            
            Node<Donor> current = tempDonorList.head;
        
            while (current != null) {
                Donor donor = current.data;

                switch(dType) {
                    case 1: // Individual
                        if (donor instanceof Individual && donor.getId().equals(dID)) {
                            donorList.insert(donor);
                            return true;
                        }
                        break;
                    case 2: // Family
                        if (donor instanceof Family && donor.getId().equals(dID)) {
                            donorList.insert(donor);
                            return true;
                        }
                        break;
                    case 3: // Organization
                        if (donor instanceof Organization && donor.getId().equals(dID)) {
                            donorList.insert(donor);
                            return true;
                        }
                        break;
                    default:
                        return false;
                }

                current = current.next;
            }
            
        }else{
            System.out.println("Nothing in tempDonorList");
        }
        
        return false;
    }
    
    // Part 2: Remove a donation
    public static void remDonation(){}
    
    // Part 3: Search donation details
    public static void searchDonation(){}
    
    // Part 4: Amend donation details
    public static void amendDonation(){}
    
    // Part 5: Track donated items in categories
    public static void trackItemByCategory(){}
    
    // Part 6: List donation by different donor
    public static void listByDiffDonor(){}
    
    // Part 7: List all donations
    public static void listAll(){}
    
    // Part 8: Filter donation based on criteria
    public static void filterDonation(){}
    
    // Part 9: Generate summary reports 
    public static void report(){}
    
}