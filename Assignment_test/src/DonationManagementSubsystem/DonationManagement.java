/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DonationManagementSubsystem;

import CommonResources.Node;
import CommonResources.LinkedList;

import DonorSubsystem.Donor;
import DonorSubsystem.ManageDonors;
import DonorSubsystem.Individual;
import DonorSubsystem.Organization;

import DonationList.Item;
import DonationList.Money;
import DonationList.Bank;
import DonationList.Cash;
import DonationList.PhysicalItem;
import DonationList.Food;
import DonationList.BakedGoods;
import DonationList.BoxedGoods;
import DonationList.CannedFood;
import DonationList.DryGoods;
import DonationList.Essentials;
import DonationList.Apparel;
import DonationList.Jacket;
import DonationList.Pant;
import DonationList.Shirt;
import DonationList.Shoes;
import DonationList.Socks;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

import java.util.Scanner;

/**
 *
 * @author Heng Pei Lin
 */
public class DonationManagement {
    
    private static final String DONOR_PATH = "donors.txt";
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
    
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";
    
    public static void main(String[] args) {
        
        chkAllFileExist();
        //addRecord(); //alr store one individual and organisation in donor.txt
        donationManagementMainMenu();
        
    }
    
    public static void donationManagementMainMenu(){
        
        boolean contDM = true;
        
        while(contDM == true){
        
            System.out.println(ANSI_BLUE + "\n - - - Donation Management - - - " + ANSI_RESET);
            String[] donationManagementMenu = {
                "Add a new donation", 
                "Remove a donation", 
                "Search donation details", 
                "Amend donation details", 
                "Track donated item in categories", 
                "List donation by different donor", 
                "List all donation", 
                "Filter donation base on criteria", 
                "Generate sumary report", 
                "Exit"};
            int dmChoice = menuIntReturn(donationManagementMenu);

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
                    break;
            }
            
            contDM = YN("Do you want to continue manage donation? (Back to Donation Management main menu)");
            if (contDM == true){
                System.out.println(); // further do if return true then need clear screen
            }
        }
    }
    
    public static void addRecord(){
        // instance individual
        Individual a = new Individual("EI00000", "Anonymous","private"); 
        Individual idv = new Individual("EI00001", "HAHA","public"); 
        
        // instance organization
        Organization org = new Organization("EO00001", "OMO Company","private");
        
        // save into list
        LinkedList dList = new LinkedList();
        dList.insert(a);
        dList.insert(idv);
        dList.insert(org);
        
        //dList.show();
        
        //load into file
        dList.saveToFile(DONOR_PATH);
    }
    
    // ----------------
    // Common Use Part  
    // ----------------
    public static void chkAllFileExist(){
        chkFileExist(DONOR_PATH);
        chkFileExist(BANK_PATH);
        chkFileExist(CASH_PATH);
        chkFileExist(BAKED_PATH);
        chkFileExist(BOXED_PATH);
        chkFileExist(CANNED_PATH);
        chkFileExist(DRY_PATH);
        chkFileExist(ESS_PATH);
        chkFileExist(JACKET_PATH);
        chkFileExist(PANT_PATH);
        chkFileExist(SHIRT_PATH);
        chkFileExist(SHOES_PATH);
        chkFileExist(SOCKS_PATH);
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

            input = (scan.nextLine()).toUpperCase().trim();
            
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
    
    public static int menuIntReturn(String[] selectionList){
        
        Scanner scan = new Scanner(System.in);
        
        int intInput = 0;
        boolean validInput = false;
        
        for(int i = 0; i < selectionList.length; i++){
            System.out.println( ( i + 1 ) + ". " + selectionList[i]);
        }
        System.out.print("Enter your selection: ");
        
        while(validInput == false){
            String stringInput = scan.nextLine();
            
            if(stringInput.isEmpty()){

                System.out.println(ANSI_RED + "Cannot leave blank.\n" + ANSI_RESET);
                System.out.print("Enter again: ");

            }else{
                try {
                    intInput = Integer.parseInt(stringInput);

                    if (intInput < 1 || intInput > selectionList.length) {
                        System.out.println(ANSI_RED + "Invalid integer. Please enter between 1 to " + selectionList.length + ".\n" + ANSI_RESET);

                        System.out.print("Enter again: ");

                    } else {
                        validInput = true; 
                    }

                } catch (NumberFormatException e) {

                    System.out.println(ANSI_RED + "Invalid input. Please enter correct integer.\n" + ANSI_RESET);
                    System.out.print("Enter again: ");

                }
            }
        }
        
        return intInput;
    }
    
    public static String idGenerator(String ab, LinkedList<Item> list){
        
        int maxId = 0;

        Node<Item> current = list.head; 

        while (current != null) {
            String currentId = current.data.getId().substring(2,7);
            int idNumber = Integer.parseInt(currentId);
            if (idNumber > maxId) {
                maxId = idNumber;
            }
            current = current.next;
        }
        
        return (ab + String.format("%05d", maxId + 1));
    }
        
    public static void deleteById(LinkedList<Item> list, String id) {
        if (list.head == null) {
            System.out.println(ANSI_RED + "\nEmpty list. No such item in stock." + ANSI_RESET);
        } else if (list.head.data.getId().equals(id)) {
            // First id match
            list.head = list.head.next;

            System.out.println(ANSI_GREEN + "Item remove successfully." + ANSI_RESET);
            return;
        } else {
            Node<Item> currentNode = list.head;

            while (currentNode != null) {

                if (currentNode.next.data.getId().equals(id)) {
                    currentNode.next = currentNode.next.next;
                    System.out.println(ANSI_GREEN + "Item remove successfully." + ANSI_RESET);
                    return;
                } 
                currentNode = currentNode.next;
            }
             
            System.out.println(ANSI_RED + "\nItem ID does not exist." + ANSI_RESET);
        }
    }
    
    public static Item findById(LinkedList<Item> list, String id) {
        Node<Item> current = list.head;

        while (current != null) {
            if (current.data.getId().equals(id)) {
                return current.data;
            }
            current = current.next;
        }
            return null;
    }
    
    public static boolean searchingIdValidation(String inputID){
        boolean validID = false;
        
        if (inputID.isEmpty()) {
            System.out.println(ANSI_RED + "Cannot leave blank." + ANSI_RESET);
            System.out.print("\nEnter again: ");
        } else {
            if (inputID.length() != 7) {
                System.out.println(ANSI_RED + "Invalid length. The length should be 7 and format AA00000." + ANSI_RESET);
                System.out.print("\nEnter again: ");
            } else {
                inputID = inputID.substring(0, 2).toUpperCase() + inputID.substring(2, 7);

                String prefix = inputID.substring(0, 2).toUpperCase();
                boolean validPrefix = false;
                if(prefix.equals("MB") || prefix.equals("MC") || prefix.equals("FA") ||
                prefix.equals("FO") || prefix.equals("FC") || prefix.equals("FD") ||
                prefix.equals("FE") || prefix.equals("AJ") || prefix.equals("AP") ||
                prefix.equals("AI") || prefix.equals("AO") || prefix.equals("AS") ){
                    validPrefix = true;
                }

                if (!validPrefix) {
                    System.out.println(ANSI_RED + "Invalid format. The format should be AA00000." + ANSI_RESET);
                    System.out.print("\nEnter again: ");
                } else {
                    String filePath = null;

                    switch (prefix) {
                        case "MB":
                            filePath = BANK_PATH;
                            break;
                        case "MC":
                            filePath = CASH_PATH;
                            break;
                        case "FA":
                            filePath = BAKED_PATH;
                            break;
                        case "FO":
                            filePath = BOXED_PATH;
                            break;
                        case "FC":
                            filePath = CANNED_PATH;
                            break;
                        case "FD":
                            filePath = DRY_PATH;
                            break;
                        case "FE":
                            filePath = ESS_PATH; 
                            break;
                        case "AJ":
                            filePath = JACKET_PATH;
                            break;
                        case "AP":
                            filePath = PANT_PATH;
                            break;
                        case "AI":
                            filePath = SHIRT_PATH;
                            break;
                        case "AO":
                            filePath = SHOES_PATH;
                            break;
                        case "AS":
                            filePath = SOCKS_PATH;
                            break;
                        default:
                            System.out.println(ANSI_RED + "Invalid ID." + ANSI_RESET);
                            break;
                    }

                    // valid format, check if this id exists, show it if yes
                    if (filePath != null) {
                        LinkedList<Item> list = new LinkedList<>();
                        list.loadFromFile(filePath);
                        Item item = searchByID(list, inputID);
                        if (item != null) {
                            // show that particular item
                            System.out.println("\nItem Details: ");
                            printSpecificItem(item);
                            validID = true; 
                        } else {
                            System.out.println(ANSI_RED + "Item does not exist or had been deleted." + ANSI_RESET);
                            System.out.print("\nEnter again: ");
                        }
                    }
                }
            }
        }
        return validID;
    }
    
    public static LinkedList<Item> loadAllItemIntoList(){
        LinkedList<Item> list = new LinkedList<>();
        String[] appendList = {BANK_PATH, CASH_PATH, JACKET_PATH, PANT_PATH, SHIRT_PATH, SHOES_PATH, SOCKS_PATH, BAKED_PATH, BOXED_PATH, CANNED_PATH, DRY_PATH, ESS_PATH};

        list.loadFromFile(appendList[0]);

        for (int i = 1; i < appendList.length; i++) {
            LinkedList<Item> currentList = new LinkedList<>();
            currentList.loadFromFile(appendList[i]);
            list.appendList(currentList);
        }
        return list;
    }
    
    public static LinkedList<Food> loadAllFoodToList(){
        String[] foodFile = {BAKED_PATH, BOXED_PATH, CANNED_PATH, DRY_PATH, ESS_PATH}; 
        LinkedList<Food> foodList = new LinkedList<>();
        foodList.loadFromFile(foodFile[0]);
        for(int i = 1; i < foodFile.length; i++){
            LinkedList tempFoodList = new LinkedList<>();
            tempFoodList.loadFromFile(foodFile[i]);
            foodList.appendList(tempFoodList);
        }
        
        return foodList;
    }
    
    public static void printEachTable(LinkedList<Item> list){
        list.removeEmptyData();
        Node<Item> currentNode = list.head;
        
        if(list.isEmpty()){
            System.out.println(ANSI_RED + "\nEmpty list. No such item in stock." + ANSI_RESET);
            return;
        }
        
        int count = 1;
        while(currentNode != null){
            System.out.println("\n- Item " + count + " -");
            System.out.printf("| %-10s | %-10s | %-15s |", "Item ID", "Donor ID", "Item Category");
            if(currentNode.data instanceof Money){
                System.out.printf(" %-14s |", "Amount Donated");
                if (currentNode.data instanceof Bank){
                    System.out.printf(" %-15s |", "Bank Name");
                }
            }else {

                System.out.printf(" %-20s |", "Remarks");
                if (currentNode.data instanceof Food){
                    System.out.printf(" %-10s | %-8s | %-8s | %-15s |", "Expiry Date", "Weight", "Status", "Food Type");
                }else{ // Apparel
                    System.out.printf(" %-10s | %-10s | %-10s | %-10s |", "Size", "Color", "Condition", "Brand");
                    if (currentNode.data instanceof Shoes){
                        System.out.printf(" %-15s |", "Shoes Type");
                    }
                }
            }
            
            System.out.println("\n" + currentNode.data.toString());
            
            count++;
            currentNode = currentNode.next;
            System.out.println();
        }
    }    
    
    public static void printSameTable(String filePath){
        LinkedList<Item> list = new LinkedList<>();
        list.loadFromFile(filePath);
    
        list.removeEmptyData();
        
        if(list.isEmpty()){
            System.out.println(ANSI_RED + "\nEmpty list. No such item in stock." + ANSI_RESET);
            return;
        }
        
        printListToTable(list);
    }
    
    public static void printListToTable(LinkedList<Item> list){
        Node<Item> currentNode = list.head;
        
        headerIdentifier(currentNode);
        
        System.out.println();
        int stop = 0;
        int pageNum = 1;
        while(currentNode != null){
            System.out.println(currentNode.data.toString());
            stop++;
            
            if(stop == 50){
                System.out.println(ANSI_BLUE +"- END OF PAGE " + pageNum + " -" + ANSI_RESET);
                boolean cont = YN("50 records of current table had been shown. Do you want to continue shown more?");
                if (cont){
                    pageNum++;
                    System.out.println(ANSI_BLUE +"\n- PAGE " + pageNum + " -" + ANSI_RESET);
                    headerIdentifier(currentNode);
                    stop = 0;
                }else{
                    return;
                }
            }
            currentNode = currentNode.next;
        }
    }
    
    public static void headerIdentifier(Node<Item> currentNode){
        System.out.printf("\n| %-10s | %-10s | %-15s |", "Item ID", "Donor ID", "Item Category");
        if(currentNode.data instanceof Money){
            System.out.printf(" %-14s |", "Amount Donated");
            if (currentNode.data instanceof Bank){
                System.out.printf(" %-15s |", "Bank Name");
            }
        }else {
        
            System.out.printf(" %-20s |", "Remarks");
            if (currentNode.data instanceof Food){
                System.out.printf(" %-10s | %-8s | %-8s | %-15s |", "Expiry Date", "Weight", "Status", "Food Type");
            }else{ // Apparel
                System.out.printf(" %-10s | %-10s | %-10s | %-10s |", "Size", "Color", "Condition", "Brand");
                if (currentNode.data instanceof Shoes){
                    System.out.printf(" %-15s |", "Shoes Type");
                }
            }
        }
    }
    
    public static void printSpecificItem(Item item){
        
        if (item == null){
            System.out.println(ANSI_RED + "Item does not exist." + ANSI_RESET);
        }
        
        System.out.printf("| %-10s | %-10s | %-15s |", "Item ID", "Donor ID", "Item Category");
        if(item instanceof Money){
            System.out.printf(" %-14s |", "Amount Donated");
            if (item instanceof Bank){
                System.out.printf(" %-15s |", "Bank Name");
            }
        }else {

            System.out.printf(" %-20s |", "Remarks");
            if (item instanceof Food){
                System.out.printf(" %-10s | %-8s | %-8s | %-15s |", "Expiry Date", "Weight", "Status", "Food Type");
            }else{ // Apparel
                System.out.printf(" %-10s | %-10s | %-10s | %-10s |", "Size", "Color", "Condition", "Brand");
                if (item instanceof Shoes){
                    System.out.printf(" %-15s |", "Shoes Type");
                }
            }
        }

        System.out.println("\n" + item.toString());
    }
    
    // -------------------------
    // Part 1: Add new donation
    // -------------------------
    public static void addDonation(){
        Scanner scan = new Scanner(System.in);
        
        boolean contAddDonation = true;
        while(contAddDonation){
            
            System.out.println(ANSI_BLUE + "\n - - - Add Donation - - - " + ANSI_RESET);

            System.out.println("***Enter \"NONE\" if is anonymous donor. ");
            System.out.print("Enter donor's id: ");
            String dID = null;
            boolean validID = false;
            while(validID == false){
                dID = scan.nextLine();
                
                if(dID.isEmpty()){
                    
                    System.out.println(ANSI_RED + "Cannot leave blank.\n" + ANSI_RESET);
                    System.out.print("Enter again: ");
                    
                } else if(dID.toUpperCase().equals("NONE")){
                    dID = dID.toUpperCase();
                    validID = true;
                } else if(dID.trim().length() != 8){
                    
                    System.out.println(ANSI_RED + "Invalid length. Format of ID should be DNR00000.\n" + ANSI_RESET);
                    System.out.print("Enter again: ");
                    
                } else {
                    dID = dID.substring(0,3).toUpperCase() + dID.substring(3, 8);
                    if (dID.substring(0, 3).equals("DNR")) {
                        // correct donor id format
                        // check if donor exist
                        LinkedList<Donor> donorList = new LinkedList<>();
                        boolean validDonor = chkDonorExist(dID, donorList);

                        // if exist, show current data 
                        if(validDonor == true){
                            System.out.println("\n - - - Current Donor - - -");
                            System.out.printf("%-10s %-2s %-50s\n", "ID", ":", donorList.head.data.getId());
                            System.out.printf("%-10s %-2s %-50s\n", "Name", ":", donorList.head.data.getName());
                            System.out.printf("%-10s %-2s %-50s\n", "Category", ":", donorList.head.data.getType());
                            validID = true;
                        }else{ // if does not exist, enter other
                            System.out.println(ANSI_RED + "\nDonor does not exist." + ANSI_RESET);
                            String[] contMenu = {"Enter Other Donor", "Exit"};
                            int selectionToCont = menuIntReturn(contMenu);
                            if (selectionToCont == 1){
                                System.out.print("Enter again: ");
                            }else{
                                donationManagementMainMenu();
                            }
                        }
                        
                    } else {
                        System.out.println(ANSI_RED + "Invalid format. Format of donor should be DNR00000.\n" + ANSI_RESET);
                        System.out.print("Enter again: ");
                    }
                }
                
            }
            
            boolean contAddItem = true;
            while(contAddItem){
            
                addItem(dID);
                
                contAddItem = YN("Do you want to continue add item for the same donor?");
                if (contAddItem == false){
                    break;
                }
            }
            
            contAddDonation = YN("Do you want to continue adding another donation for other donor?");
            if(contAddDonation == true){
                System.out.println(); // clear screen 
            } 
        }
        
    }
    
    public static boolean chkDonorExist(String dID, LinkedList donorList){
        LinkedList<Donor> tempDonorList = new LinkedList<>();
        
        tempDonorList.loadFromFile(DONOR_PATH);
        
        if (!tempDonorList.isEmpty()){
            
            Node<Donor> current = tempDonorList.head;
        
            while (current != null) {
                Donor donor = current.data;
                
                if (donor.getId().equals(dID)) {
                    donorList.insert(donor);
                    return true;
                }

                current = current.next;
            }
            
        }else{
            System.out.println("Nothing in tempDonorList");
        }
        
        return false;
    }
    
    public static void addItem(String dID){
        Scanner scan = new Scanner(System.in);
        
        System.out.print("\nNumber of item wish to add: ");
        int numItem = 0;
        boolean validNumItem = false;
        while(validNumItem == false){
            String numSItem = scan.nextLine();
            if(numSItem .isEmpty()){

                System.out.println(ANSI_RED + "\nCannot leave blank." + ANSI_RESET);
                System.out.print("Enter again: ");
            }else{
                try {
                    numItem = Integer.parseInt(numSItem );

                    if (numItem < 1) {
                        System.out.println(ANSI_RED + "\nThe number cannot be 0 or negative." + ANSI_RESET);

                        scan.nextLine();
                        System.out.print("Enter again: ");
                    } else {
                        validNumItem = true; 
                    }

                } catch (NumberFormatException e) {

                    System.out.println(ANSI_RED + "\nInvalid input. Please enter an integer number." + ANSI_RESET);
                    System.out.print("Enter again: ");

                }
            }
        }
        
        LinkedList<Item> newItemList = new LinkedList();
        
        for (int i = 0; i< numItem; i++){
            
            System.out.println( "\n - Item " + (i+1) + " - \n"
                    + "Item Category");
            String[] itemMenu = {"Bank", "Cash", "Food", "Apparel"};
            int itemCat = menuIntReturn(itemMenu);
            
            switch(itemCat){
                case 1:
                    inputMoney(newItemList, itemCat, dID);
                    break;
                case 2:
                    inputMoney(newItemList, itemCat, dID);
                    break;
                case 3:
                    inputFood(newItemList, dID);
                    break;
                case 4:
                    inputApparel(newItemList, dID);
                    break;
                default:
                    System.out.println(ANSI_RED + "Invalid choice." + ANSI_RESET);
                    break;
            }
        }
        
        System.out.println(ANSI_GREEN + "\nItems added successfully" + ANSI_RESET);
        
        //show all item to be added
        System.out.print("\n - - - New Item Added - - - ");
        printEachTable(newItemList);
    }
    
    public static void inputMoney(LinkedList<Item> newItemList, int itemCat, String dID){
        
        // amount
        double amt = amountValidation();
        
        if (itemCat == 1){
        
            // bank name
            String bankName = bankTypeValidation();

            
            LinkedList<Item> list = new LinkedList();
            list.loadFromFile(BANK_PATH);
        
            // id
            String id = idGenerator("MB", list);
            
            Bank tempBank = new Bank(id, dID, amt, bankName);
            list.insert(tempBank);
            list.saveToFile(BANK_PATH);
            
            newItemList.insert(tempBank);
        
        }else{
            LinkedList<Item> list = new LinkedList();
            list.loadFromFile(CASH_PATH);
            
            // id
            String id = idGenerator("MC", list);
            
            Cash tempCash = new Cash(id, dID, amt);
            list.insert(tempCash);
            list.saveToFile(CASH_PATH);
            
            newItemList.insert(tempCash);
        }
        
    }
    
    public static double amountValidation(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Amount Donated: RM ");
        double amt = 0;
        boolean validAmt = false;
        while(validAmt == false){
            String SAmt = scan.nextLine();
            if(SAmt.isEmpty()){

                System.out.println(ANSI_RED + "Cannot leave blank.\n" + ANSI_RESET);
                System.out.print("Enter again: ");

            }else{
                try {
                    amt = Double.parseDouble(SAmt);

                    if (amt <= 0) {
                        
                        System.out.println(ANSI_RED + "Invalid amount. Donated amount could not be 0.\n" + ANSI_RESET);
                        System.out.print("Enter again: ");

                    } else {
                        validAmt = true; 
                    }

                } catch (NumberFormatException e) {

                    System.out.println(ANSI_RED + "Invalid input. Please enter correct amount.\n" + ANSI_RESET);
                    System.out.print("Enter again: ");

                }
            }
        }
        return amt;
    }
    
    public static String bankTypeValidation(){
        System.out.println("\nBank Type");
        String[] bankTypeMenu = {
            "Affin Bank", 
            "Alliance Bank", 
            "AmBank", 
            "CIMB", 
            "Hong Leong Bank", 
            "May Bank", 
            "Public Bank", 
            "RHB Bank"};
        int bankType = menuIntReturn(bankTypeMenu);

        String bankName = null;
        switch(bankType){
            case 1: 
                bankName = "Affin Bank";
                break;
            case 2:
                bankName = "Alliance Bank";
                break;
            case 3:
                bankName = "AmBank";
                break;
            case 4:
                bankName = "CIMB";
                break;
            case 5:
                bankName = "Hong Leong Bank";
                break;
            case 6:
                bankName = "May Bank";
                break;
            case 7:
                bankName = "Public Bank";
                break;
            case 8:
                bankName = "RHB Bank";
                break;
            default:
                System.out.println(ANSI_RED + "Invalid bank name.\n" + ANSI_RESET);
                break;
        }
        
        return bankName;
    }
    
    public static void inputFood(LinkedList<Item> newItemList, String dID){
        
        // food category
        int foodCat = foodCatValidation();
        
        switch(foodCat){
            case 1: 
                System.out.println("\nBaked Goods");
                break;
            case 2:
                System.out.println("\nBoxed Goods");
                break;
            case 3:
                System.out.println("\nCanned Food");
                break;
            case 4:
                System.out.println("\nDry Goods");
                break;
            case 5:
                System.out.println("\nEssentials");
                break;
            default:
                System.out.println(ANSI_RED + "Invalid food category.\n" + ANSI_RESET);
                break;
        }
        
        commonItemInput(newItemList, foodCat, 1, dID);
        
    }
    
    public static int foodCatValidation(){
        System.out.println("\nFood Category");
        String[] foodCatMenu = {
            "Baked Goods", 
            "Boxed Goods", 
            "Canned Food", 
            "Dry Goods", 
            "Essentials"};
        return menuIntReturn(foodCatMenu);
    }
    
    public static void inputApparel(LinkedList<Item> newItemList, String dID){
        
        // apparel category
        int appCat = appCatValidation();
        
        switch(appCat){
            case 1: 
                System.out.println("\nJacket");
                break;
            case 2:
                System.out.println("\nPant");
                break;
            case 3:
                System.out.println("\nShirt");
                break;
            case 4:
                System.out.println("\nShoes");
                break;
            case 5:
                System.out.println("\nSocks");
                break;
            default:
                System.out.println(ANSI_RED + "Invalid food category.\n" + ANSI_RESET);
                break;
        }
        
        commonItemInput(newItemList, appCat, 2, dID);
        
    }
    
    public static int appCatValidation(){
        System.out.println("\nApparel Category");
        String[] appCatMenu = {
            "Jacket", 
            "Pant", 
            "Shirt", 
            "Shoes", 
            "Socks"};
        return menuIntReturn(appCatMenu);
    }
    
    public static void commonItemInput(LinkedList<Item> newItemList, int detailCat, int itemCat, String dID){
        
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Item with '*' is compulsary to be enter.");
        
        // quantity
        int qty = qtyValidation();
        
        //note
        System.out.print("\nRemarks: ");
        String note = scan.nextLine();
        if (note.trim().isEmpty()){
            note = "None";
        }
        
        switch(itemCat){
            case 1:
                // food
                commonFoodInput(newItemList, detailCat, qty, note, dID);
                break;
            case 2:
                // apparel
                commonApparelInput(newItemList, detailCat, qty, note, dID);
                break;
        }
        
    }
    
    public static int qtyValidation(){
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Quantity of same item*: ");
        int qty = 0;
        boolean validQty = false;
        while(validQty == false){
            String qtyS = scan.nextLine();
            
            if(qtyS.isEmpty()){

                System.out.println(ANSI_RED + "Cannot leave blank.\n" + ANSI_RESET);
                System.out.print("Enter again: ");

            }else{
                try {
                    qty = Integer.parseInt(qtyS);

                    if (qty == 0) {
                        
                        System.out.println(ANSI_RED + "Quantity cannot be 0.\n" + ANSI_RESET);
                        System.out.print("Enter again: ");

                    } else if(qty  < 0){
                        System.out.println(ANSI_RED + "Quantity cannot be negative.\n" + ANSI_RESET);
                        System.out.print("Enter again: ");
                    }else {
                        validQty = true; 
                    }

                } catch (NumberFormatException e) {

                    System.out.println(ANSI_RED + "Invalid input. Please enter correct quantity.\n" + ANSI_RESET);
                    System.out.print("Enter again: ");

                }
            }
        }
        return qty;
    }
    
    public static void commonFoodInput(LinkedList<Item> newItemList, int foodCat, int qty, String note, String dID){
        
        //expiryDate
        Date expiryDate = expiryDateValidation(newItemList, dID);
        
        //weight
        int w = weightValidation();
        
        //status
        String foodStaName = foodStaValidation();
        
        LinkedList<Item> list = new LinkedList();
        String id = null;
        switch(foodCat){
            case 1: 
                
                list.loadFromFile(BAKED_PATH);
                
                String bakedName = inputBaked();
                
                for (int i = 0; i < qty; i++){
                    id = idGenerator("FA", list);
                    BakedGoods baG = new BakedGoods(id, dID, note, expiryDate, w, foodStaName, bakedName);
                    
                    list.insert(baG);
            
                    newItemList.insert(baG);
                }
                
                list.saveToFile(BAKED_PATH);
                break;
            case 2:
                
                list.loadFromFile(BOXED_PATH);
                
                String boxedName = inputBoxed();
                
                for (int i = 0; i < qty; i++){
                    id = idGenerator("FO", list);
                    BoxedGoods boG = new BoxedGoods(id, dID, note, expiryDate, w, foodStaName, boxedName);

                    list.insert(boG);
            
                    newItemList.insert(boG);
                }
                
                list.saveToFile(BOXED_PATH);
                break;
            case 3:
                
                list.loadFromFile(CANNED_PATH);
                
                String cannedName = inputCanned();
                
                for(int i = 0; i < qty; i++){
                    id = idGenerator("FC", list);
                    CannedFood cF = new CannedFood(id, dID, note, expiryDate, w, foodStaName, cannedName);

                    list.insert(cF);

                    newItemList.insert(cF);
                
                }
                
                list.saveToFile(CANNED_PATH);
                break;
            case 4:
                
                list.loadFromFile(DRY_PATH);
                
                String dryName = inputDry();
                
                for(int i = 0; i < qty; i++){
                    id = idGenerator("FD", list);
                    DryGoods dG = new DryGoods(id, dID, note, expiryDate, w, foodStaName, dryName);

                    list.insert(dG);

                    newItemList.insert(dG);
                    
                }
                
                list.saveToFile(DRY_PATH);
                break;
            case 5:
                
                list.loadFromFile(ESS_PATH);
                
                String essName = inputEss();
                
                for(int i = 0;i < qty; i++){
                    id = idGenerator("FE", list);
                    Essentials eG = new Essentials(id, dID, note, expiryDate, w, foodStaName, essName);

                    list.insert(eG);

                    newItemList.insert(eG);
                    
                }
                
                list.saveToFile(ESS_PATH);
                break;
            default:
                System.out.println(ANSI_RED + "Invalid food category.\n" + ANSI_RESET);
                break;
        }
        
    }
    
    public static Date expiryDateValidation(LinkedList<Item> newItemList, String dID){
        Scanner scan = new Scanner(System.in);
        
        System.out.print("\nExpiry Date (dd/mm/yyyy)*: ");
        Date expiryDate = null;
        boolean validExp = false;
        
        while(validExp == false){
            String exp = scan.nextLine();
            
            if (exp.isEmpty()){
                
                System.out.println(ANSI_RED + "Expiry date cannot leave blank.\n" + ANSI_RESET);
                System.out.print("Enter again: ");
                
            } else{
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                dateFormat.setLenient(false); 

                try {
                    
                    expiryDate = dateFormat.parse(exp);

                    Date today = new Date();

                    if (expiryDate.before(today)) {
                        
                        System.out.println(ANSI_RED + "The food had expired.\n" + ANSI_RESET);
                        String[] menu = {"Enter again", "Discard"};
                        int selection = menuIntReturn(menu);
                        
                        if(selection == 1){
                            System.out.print("Enter date again: ");
                        }else{
                            inputFood(newItemList, dID);
                        }
                        
                    } else {
                        validExp = true;
                    }
                    
                } catch (ParseException e) {
                    System.out.println(ANSI_RED + "Invalid date format. Please enter the date in dd/MM/yyyy format.\n" + ANSI_RESET);
                    System.out.print("Enter date again: ");
                }
                
            }
        }
        
        return expiryDate;
        
    }
    
    public static int weightValidation(){
        Scanner scan = new Scanner(System.in);
        
        System.out.print("\nWeight(gram)*: ");
        int w = 0;
        boolean validW = false;
        while(validW == false){
            String wS = scan.nextLine();
            
            if(wS.isEmpty()){

                System.out.println(ANSI_RED + "Cannot leave blank.\n" + ANSI_RESET);
                System.out.print("Enter again: ");

            }else{
                try {
                    w = Integer.parseInt(wS);

                    if (w == 0) {
                        
                        System.out.println(ANSI_RED + "Weight cannot be 0.\n" + ANSI_RESET);
                        System.out.print("Enter again: ");

                    } else if(w  < 0){
                        System.out.println(ANSI_RED + "Weight cannot be negative.\n" + ANSI_RESET);
                        System.out.print("Enter again: ");
                    }else {
                        validW = true;
                    }

                } catch (NumberFormatException e) {

                    System.out.println(ANSI_RED + "Invalid input. Please enter correct weight.\n" + ANSI_RESET);
                    System.out.print("Enter again: ");

                }
            }
        }
        
        return w;
    }
    
    public static String foodStaValidation(){
        
        System.out.println("\nFood Status");
        String[] foodStatusMenu = {"New (made within 1 weeks)", "Good"};
        int foodSta = menuIntReturn(foodStatusMenu);
        
        String foodStaName = null;
        switch(foodSta){
            case 1: 
                foodStaName = "New";
                break;
            case 2:
                foodStaName = "Good";
                break;
            default:
                System.out.println(ANSI_RED + "Invalid food status.\n" + ANSI_RESET);
                break;
        }
        
        return foodStaName;
    }
    
    public static String inputBaked() {
        
        // baked food type
        System.out.println("\nBaked Food Type");
        String[] bakedFoodMenu = {"Cookies", "Crackers"};
        int type = menuIntReturn(bakedFoodMenu);
        
        String name = null;
        switch(type){
            case 1: 
                name = "Cookies";
                break;
            case 2:
                name = "Crackers";
                break;
            default:
                System.out.println(ANSI_RED + "Invalid bank name.\n" + ANSI_RESET);
                break;
        }
        
        return name;
    }
    
    public static String inputBoxed(){
        
        // boxed food type
        System.out.println("\nBoxed Food Type");
        String[] boxedFoodMenu = {"Cereals", "Snacks"};
        int type = menuIntReturn(boxedFoodMenu);
        
        String name = null;
        switch(type){
            case 1: 
                name = "Cereals";
                break;
            case 2:
                name = "Snacks";
                break;
            default:
                System.out.println(ANSI_RED + "Invalid bank name.\n" + ANSI_RESET);
                break;
        }
        
        return name;
    }
    
    public static String inputCanned(){
        Scanner scan = new Scanner(System.in);
        
        // canned food type
        System.out.println("\nCanned Food Type");
        String[] cannedFoodMenu = {
                "Baked beans", 
                "Chicken soup", 
                "Corn", 
                "Lychee", 
                "Meat", 
                "Mushroom soup", 
                "Pineapple", 
                "Tomatoes", 
                "Tuna"};
        int type = menuIntReturn(cannedFoodMenu);
        
        String name = null;
        switch(type){
            case 1: 
                name = "Baked beans can";
                break;
            case 2:
                name = "Chicken soup can";
                break;
            case 3:
                name = "Corn can";
                break;
            case 4:
                name = "Lychee can";
                break;
            case 5:
                name = "Meat can";
                break;
            case 6:
                name = "Mushroom soup can";
                break;
            case 7:
                name = "Pineapple can";
                break;
            case 8:
                name = "Tomatoes can";
                break;
            case 9:
                name = "Tuna can";
                break;
            default:
                System.out.println(ANSI_RED + "Invalid bank name.\n" + ANSI_RESET);
                break;
        }
        
        return name;
    }
    
    public static String inputDry(){
        
        // dry food type
        System.out.println("\nDry Food Type");
        String[] dryFoodMenu = {"Instant noodles", "Oats", "Pasta", "Rice"};
        int type = menuIntReturn(dryFoodMenu);
        
        String name = null;
        switch(type){
            case 1: 
                name = "Instant noodles";
                break;
            case 2:
                name = "Oats";
                break;
            case 3: 
                name = "Pasta";
                break;
            case 4:
                name = "Rice";
                break;
            default:
                System.out.println(ANSI_RED + "Invalid bank name.\n" + ANSI_RESET);
                break;
        }
        
        return name;
    }
    
    public static String inputEss(){
        
        // essentials type
        System.out.println("\nEssentials Type");
        String[] essMenu = {"Oil", "Pepper", "Salt", "Sugar"};
        int type = menuIntReturn(essMenu);
        
        String name = null;
       
        switch(type){
            case 1: 
                name = "Oil";
                break;
            case 2:
                name = "Pepper";
                break;
            case 3: 
                name = "Salt";
                break;
            case 4:
                name = "Sugar";
                break;
            default:
                System.out.println(ANSI_RED + "Invalid bank name.\n" + ANSI_RESET);
                break;
        }
        
        return name;
    }
    
    public static void commonApparelInput(LinkedList<Item> newItemList, int appCat, int qty, String note, String dID){
        //size
        String size;
        if(appCat == 4){
            size = shoesSizeValidation();
        }else{
            size = sizeValidation();
        }
        
        // color
        String color = colorValidation();
        
        // condition
        String condition = conditionValidation();
        
        // brand
        String brand = brandValidation();
        
        LinkedList<Item> list = new LinkedList();
        String id = null;
        switch(appCat){
            case 1: 
                
                list.loadFromFile(JACKET_PATH);
                
                for(int i = 0; i < qty; i++){
                    id = idGenerator("AJ", list);
                    Jacket j = new Jacket(id, dID, note, size, color, condition, brand);

                    list.insert(j);

                    newItemList.insert(j);
                }
                    
                list.saveToFile(JACKET_PATH);
                break;
            case 2: 
                
                list.loadFromFile(PANT_PATH);
                
                for(int i = 0; i < qty; i ++){
                    id = idGenerator("AP", list);
                    Pant p = new Pant(id, dID,  note, size, color, condition, brand);

                    list.insert(p);

                    newItemList.insert(p);
                }
                    
                list.saveToFile(PANT_PATH);
                break;
            case 3: 
                
                list.loadFromFile(SHIRT_PATH);
                
                for (int i = 0; i < qty; i ++){
                    id = idGenerator("AI", list);
                    Shirt shirt = new Shirt(id, dID, note, size, color, condition, brand);

                    list.insert(shirt);

                    newItemList.insert(shirt);
                }
                
                list.saveToFile(SHIRT_PATH);
                break;
            case 4: 
                
                list.loadFromFile(SHOES_PATH);
                
                String detailType = inputShoes();
                
                for (int i = 0 ; i < qty ; i++){
                    id = idGenerator("AO", list);
                    Shoes shoes = new Shoes(id, dID, note, size, color, condition, brand, detailType);

                    list.insert(shoes);

                    newItemList.insert(shoes);
                }
                
                list.saveToFile(SHOES_PATH);
                break;
            case 5: 
                
                list.loadFromFile(SOCKS_PATH);
                
                for(int i = 0 ; i < qty ; i++){
                    id = idGenerator("AS", list);
                    Socks socks = new Socks(id, dID, note, size, color, condition, brand);

                    list.insert(socks);

                    newItemList.insert(socks);
                }
                
                list.saveToFile(SOCKS_PATH);
                break;
            default:
                System.out.println(ANSI_RED + "Invalid apparel category.\n" + ANSI_RESET);
                break;
        }
    }
    
    public static String sizeValidation(){
        System.out.println("\nApparel size");
        String[] sizeMenu = {"XS", "S", "M", "L", "XL", "Free Size"};
        int appSize = menuIntReturn(sizeMenu);
        
        String size = null;
        switch(appSize){
            case 1: 
                size = "XS";
                break;
            case 2:
                size = "S";
                break;
            case 3:
                size = "M";
                break;
            case 4:
                size = "L";
                break;
            case 5:
                size = "XL";
                break;
            case 6:
                size = "Free Size";
                break;
            default:
                System.out.println(ANSI_RED + "Invalid apparel size.\n" + ANSI_RESET);
                break;
        }
        
        return size;
    }
    
    public static String shoesSizeValidation(){
        Scanner scan = new Scanner(System.in);
        
        System.out.print("\nApparel size (1 - 16): ");
        String sizeStr = null;
        boolean validSize = false;
        while(validSize == false){
            sizeStr = scan.nextLine();
            
            if(sizeStr.isEmpty()){

                System.out.println(ANSI_RED + "Cannot leave blank.\n" + ANSI_RESET);
                System.out.print("Enter again: ");

            }else{
                try {
                    int size = Integer.parseInt(sizeStr);

                    if (size < 1 || size > 16) {
                        System.out.println(ANSI_RED + "Invalid integer. Please enter between 1 to 16.\n" + ANSI_RESET);

                        System.out.print("Enter again: ");

                    } else {
                        validSize = true; 
                    }

                } catch (NumberFormatException e) {

                    System.out.println(ANSI_RED + "Invalid input. Please enter correct integer.\n" + ANSI_RESET);
                    System.out.print("Enter again: ");

                }
            }
        }
        
        return sizeStr;
    }
    
    public static String colorValidation(){
        
        System.out.println("\nApparel Color");
        String[] appColorMenu = {"Red", "Orange", "Yellow", "Green", "Blue", "Purple", "Silver", "White", "Black"};
        int appColor = menuIntReturn(appColorMenu);
        
        String color = null;
        switch(appColor){
            case 1: 
                color = "Red";
                break;
            case 2:
                color = "Orange";
                break;
            case 3:
                color = "Yellow";
                break;
            case 4:
                color = "Green";
                break;
            case 5:
                color = "Blue";
                break;
            case 6:
                color = "Purple";
                break;
            case 7:
                color = "Silver";
                break;
            case 8:
                color = "White";
                break;
            case 9:
                color = "Black";
                break;
            default:
                System.out.println(ANSI_RED + "Invalid apparel color.\n" + ANSI_RESET);
                break;
        }
        
        return color;
    }
    
    public static String conditionValidation(){
        
        System.out.println("\nApparel Condition");
        String[] appConMenu = {"New", "Good", "Fair", "Poor"};
        int appCon = menuIntReturn(appConMenu);
        
        String condition = null;
        switch(appCon){
            case 1: 
                condition = "New";
                break;
            case 2:
                condition = "Good";
                break;
            case 3:
                condition = "Fair";
                break;
            case 4:
                condition = "Poor";
                break;
            default:
                System.out.println(ANSI_RED + "Invalid apparel condition.\n" + ANSI_RESET);
                break;
        }
        
        return condition;
    }
    
    public static String brandValidation(){
        System.out.println("\nApparel Brand");
        String[] appBrandMenu = {"Adidas", "H & M", "Nike", "Puma", "Uniclo", "Others"};
        int appBrand = menuIntReturn(appBrandMenu);
        
        String brand = null;
        switch(appBrand){
            case 1: 
                brand = "Adidas";
                break;
            case 2:
                brand = "H & M";
                break;
            case 3:
                brand = "Nike";
                break;
            case 4:
                brand = "Puma";
                break;
            case 5:
                brand = "Uniclo";
                break;
            case 6:
                brand = "Others";
                break;
            default:
                System.out.println(ANSI_RED + "Invalid apparel brand.\n" + ANSI_RESET);
                break;
        }
        
        return brand;
    }
    
    public static String inputShoes(){
        
        System.out.println("\nShoes Category");
        String[] shoesMenu = {"Slipper", "Sport shoes"};
        int s = menuIntReturn(shoesMenu);
        
        switch(s){
            case 1: 
                return "Slipper";
            case 2:
                return "Sport shoes";
            default:
                return "Unknown";
        }
    }
    
    // -------------------------
    // Part 2: Remove a donation
    // -------------------------
    public static void remDonation(){
        System.out.println(ANSI_BLUE + "\nItem to remove:" + ANSI_RESET);
        String[] itemRemoveMenu = {"Bank", "Cash", "Food", "Apparel"};
        int itemRem = menuIntReturn(itemRemoveMenu);
        
        switch(itemRem){
            case 1: 
                remItem(BANK_PATH, "MB");
                break;
            case 2:
                remItem(CASH_PATH, "MC");
                break;
            case 3:
                System.out.println("\nFood Category");
                String[] foodCatMenu = {
                    "Baked Goods", 
                    "Boxed Goods", 
                    "Canned Food", 
                    "Dry Goods", 
                    "Essentials"};
                int foodCat = menuIntReturn(foodCatMenu);

                switch(foodCat){
                    case 1: 
                        remItem(BAKED_PATH, "FA");
                        break;
                    case 2:
                        remItem(BOXED_PATH, "FO");
                        break;
                    case 3:
                        remItem(CANNED_PATH, "FC");
                        break;
                    case 4:
                        remItem(DRY_PATH, "FD");
                        break;
                    case 5:
                        remItem(ESS_PATH, "FE");
                        break;
                    default:
                        System.out.println(ANSI_RED + "Invalid food category.\n" + ANSI_RESET);
                        break;
                }
                break;
            case 4:
                System.out.println("\nApparel Category");
                String[] appCatMenu = {
                    "Jacket", 
                    "Pant", 
                    "Shirt", 
                    "Shoes", 
                    "Socks"};
                int appCat = menuIntReturn(appCatMenu);

                switch(appCat){
                    case 1: 
                        remItem(JACKET_PATH, "AJ");
                        break;
                    case 2:
                        remItem(PANT_PATH, "AP");
                        break;
                    case 3:
                        remItem(SHIRT_PATH, "AI");
                        break;
                    case 4:
                        remItem(SHOES_PATH, "AO");
                        break;
                    case 5:
                        remItem(SOCKS_PATH, "AS");
                        break;
                    default:
                        System.out.println(ANSI_RED + "Invalid food category.\n" + ANSI_RESET);
                        break;
                }
                break;
            default:
                System.out.println(ANSI_RED + "Invalid item to remove.\n" + ANSI_RESET);
                break;
                
        }
    }
    
    public static void remItem(String filePath, String id){
        Scanner scan = new Scanner(System.in);
        LinkedList<Item> sList = new LinkedList();
        sList.loadFromFile(filePath);
            
        if(sList.isEmpty()){
            System.out.println(ANSI_RED + "The list is empty, no item exist in file." + ANSI_RESET);
            boolean cont = YN("Do you want to continue remove other item?");
            if (cont == true){
                remDonation();
            }else{
                donationManagementMainMenu();
            }
        }else{
            sList.removeIf(item -> item.toString().trim().isEmpty()); // remove empty or space
            
            printSameTable(filePath);
            System.out.print("\nWhich item do you wish to remove?\n"
                    + "Enter ID: ");
            boolean validID = false;
            
            while(validID == false){
            
                String inputID = scan.nextLine();

                if(inputID.isEmpty()){
                    System.out.println(ANSI_RED + "Cannot leave blank." + ANSI_RESET);
                    System.out.print("\nEnter again: ");
                }else {

                    if( inputID.length() != 7 ){
                        System.out.println(ANSI_RED + "Invalid length. The length should be 7 and format "+ id + "00000." + ANSI_RESET);
                        System.out.print("\nEnter again: ");
                    } else{
                        
                        inputID = inputID.substring(0,2).toUpperCase() + inputID.substring(2, 7);
                        if (!(id.equalsIgnoreCase(inputID.substring(0, 2)))){
                            System.out.println(ANSI_RED + "Invalid format. The format should be " + id + "00000." + ANSI_RESET);
                            System.out.print("\nEnter again: ");
                        }else{
                            // valid format, check if this id exist, remove if yes
                            LinkedList<Item> list = new LinkedList();

                            list.loadFromFile(filePath);
                            validID = removeByID(list, inputID, filePath);
                            if (validID == false){
                                System.out.print("\nEnter again: ");
                            }
                        }
                    }
                }
            
            }
        }
        
    }
    
    // make sure list available before enter function
    public static boolean removeByID(LinkedList<Item> list, String id, String filePath){
        Item item = findById(list, id);
        if (item != null){
            // item found
            // delete from list
            deleteById(list, id);
            
            list.saveToFile(filePath);
            printSameTable(filePath);
            return true;
        }else{
            System.out.println(ANSI_RED + "Item does not exist." + ANSI_RESET);
        }
        
        return false;
    }
    
    // -------------------------------
    // Part 3: Search donation details
    // -------------------------------
    public static void searchDonation() {
        Scanner scan = new Scanner(System.in);
        System.out.println(ANSI_BLUE + "\n - - - Search Donation Item by Item ID - - -" + ANSI_RESET);
        
        boolean contSearch = true;
        while(contSearch){
            System.out.print("Enter ID: ");
            boolean validID = false;

            while (!validID) {
                String inputID = scan.nextLine();

                validID = searchingIdValidation(inputID);
            }
            
            contSearch = YN("Do you want to search for another item ID?");
            if (contSearch){
                System.out.println();
            }
        }
    }
    
    // Binary search
    public static Item searchByID(LinkedList<Item> list, String id) {
        // Run data in linked list into array
        Node<Item> currentNode = list.head;
        Item[] itemArray = new Item[list.length()];
        for (int i = 0; i < list.length(); i++) {
            itemArray[i] = currentNode.data;
            currentNode = currentNode.next;
        }

        // Sort Item in array using bubble sort
        for (int i = 0; i < itemArray.length - 1; i++) {
            for (int j = 0; j < itemArray.length - 1 - i; j++) {
                if (itemArray[j].getId().compareTo(itemArray[j + 1].getId()) > 0) {
                    // Swap itemArray[j] and itemArray[j + 1]
                    Item temp = itemArray[j];
                    itemArray[j] = itemArray[j + 1];
                    itemArray[j + 1] = temp;
                }
            }
        }

        // Find from the middle using binary search
        int low = 0;
        int high = itemArray.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            Item midItem = itemArray[mid];

            if (midItem.getId().equals(id)) {
                return midItem;
            } else if (midItem.getId().compareTo(id) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return null; // Item not found
    }

    // ------------------------------
    // Part 4: Amend donation details
    // ------------------------------
    public static void amendDonation(){
        Scanner scan = new Scanner(System.in);
        
        boolean contAmend = true;
        while(contAmend == true){
            System.out.println(ANSI_BLUE + "\n - - - Amend Donation Item - - - " + ANSI_RESET);
            System.out.print("Enter item ID: ");
            boolean validID = false;

            String id = null;
            while (!validID) {
                id = scan.nextLine();

                validID = searchingIdValidation(id);
            }

            id = id.substring(0, 2).toUpperCase() + id.substring(2, 7);
            String prefix = id.substring(0, 2);
            String filePath = null;
            String[] amendList = null;
            String[] cloth = new String[]{"Remarks", "Size", "Color", "Condition", "Brand"};
            String[] food = new String[]{"Remarks", "Expiry date", "Weight", "Status", "Food Details"};
            switch (prefix) {
                case "MB":
                    filePath = BANK_PATH;
                    amendList = new String[]{"Amount", "Bank Type"};
                    break;
                case "MC":
                    filePath = CASH_PATH;
                    amendList = new String[]{"Amount"};
                    break;
                case "FA":
                    filePath = BAKED_PATH;
                    amendList = food;
                    break;
                case "FO":
                    filePath = BOXED_PATH;
                    amendList = food;
                    break;
                case "FC":
                    filePath = CANNED_PATH;
                    amendList = food;
                    break;
                case "FD":
                    filePath = DRY_PATH;
                    amendList = food;
                    break;
                case "FE":
                    filePath = ESS_PATH;
                    amendList = food;
                    break;
                case "AJ":
                    filePath = JACKET_PATH;
                    amendList = cloth;
                    break;
                case "AP":
                    filePath = PANT_PATH;
                    amendList = cloth;
                    break;
                case "AI":
                    filePath = SHIRT_PATH;
                    amendList = cloth;
                    break;
                case "AO":
                    filePath = SHOES_PATH;
                    amendList = new String[]{"Remarks", "Size", "Color", "Condition", "Brand", "Shoes Details"};
                    break;
                case "AS":
                    filePath = SOCKS_PATH;
                    amendList = cloth;
                    break;
                default:
                    System.out.println(ANSI_RED + "Invalid ID." + ANSI_RESET);
                    break;
            }
            
            LinkedList<Item> list = new LinkedList<>();
            list.loadFromFile(filePath);
            Item item = findById(list, id);
            if (item != null) {

                boolean contItem = true;
                do{
                    System.out.println("\nOption available");
                    int amendOption = menuIntReturn(amendList);

                    if (item instanceof Money){

                        moneyAmend(amendOption, item);

                    }else if(item instanceof PhysicalItem){

                        physicalItemAmend(amendOption, item, filePath);

                    }

                    System.out.println(ANSI_GREEN + "\nItem updated Successfully.\n" + ANSI_RESET);
                    printSpecificItem(item);
                    
                    contItem = YN("Do you want to continue amend this item?");
                    
                }while(contItem == true);

            }

            list.saveToFile(filePath);
            
            contAmend = YN("Do you want to amend another item?");
            
        }
        
    }
    
    public static void moneyAmend(int amendOption, Item item){
        if (amendOption == 1){
            double amt = amountValidation();
            ((Money) item).setAmount(amt);
        }

        if (item instanceof Bank){
            if(amendOption == 2){
                String bankType = bankTypeValidation();
                ((Bank) item).setBankName(bankType);
            }
        }
    }
    
    public static void physicalItemAmend(int amendOption, Item item, String filePath){
        Scanner scan = new Scanner(System.in);
        
        if(amendOption == 1){
            System.out.print("Remarks: ");
            String note = scan.nextLine();
            ((PhysicalItem) item).setNote(note);
        }

        if (item instanceof Food){
            foodAmend(amendOption, item, filePath);
        }

        if (item instanceof Apparel){
            apparelAmend(amendOption, item);
        }
    }
    
    public static void foodAmend(int amendOption, Item item, String filePath){
        Scanner scan = new Scanner(System.in);
        
        if (amendOption == 2){
            
            // expiry date
            System.out.print("Expiry Date (dd/mm/yyyy)*: ");
            Date expiryDate = null;
            boolean validExp = false;

            while(validExp == false){
                String exp = scan.nextLine();

                if (exp.isEmpty()){

                    System.out.println(ANSI_RED + "Expiry date cannot leave blank.\n" + ANSI_RESET);
                    System.out.print("Enter again: ");

                } else{

                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    dateFormat.setLenient(false); 

                    try {

                        expiryDate = dateFormat.parse(exp);

                        Date today = new Date();

                        if (expiryDate.before(today)) {

                            System.out.println(ANSI_RED + "The food had expired.\n" + ANSI_RESET);
                            String[] menu = {"Enter Again", "Remain Unchange", "Delete Item"};
                            int selection = menuIntReturn(menu);

                            if(selection == 1){
                                
                                System.out.print("Enter date again: ");
                                
                            }else if(selection == 2){
                                
                                boolean cont = YN("Do you want to continue amend item?");
                                if (cont == true){
                                    amendDonation();
                                }else{
                                    donationManagementMainMenu();
                                }
                                
                            }else{
                                
                                LinkedList<Item> list = new LinkedList<>();
                                list.loadFromFile(filePath);
                                if (list.isEmpty()){
                                    
                                    System.out.println(ANSI_RED + "The list is empty, no item exist in file." + ANSI_RESET);
                                    
                                }else{
                                    boolean delSuccessfully = removeByID(list, item.getId(), filePath);
                                    if(delSuccessfully == true){
                                        System.out.println(ANSI_GREEN + "Item had been deleted successfully!" + ANSI_RESET);
                                    } else{
                                        System.out.println("Item deleted unsuccessfully.");
                                    }
                                }
                            }

                        } else {
                            ((Food) item).setExpiryDate(expiryDate);
                            validExp = true;
                        }

                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please enter the date in dd/MM/yyyy format.");
                        System.out.print("Enter date again: ");
                    }

                }
            }
            
        } else if(amendOption == 3){
            
            //weight
            int w = weightValidation();
            ((Food) item).setWeight(w);
        
        } else if(amendOption == 4){
            
            //status
            String status = foodStaValidation();
            ((Food) item).setStatus(status);
        
        }else if(amendOption == 5){
            //Food Details
            String detail = null;
            if (item instanceof BakedGoods){
                detail = inputBaked();
            } else if(item instanceof BoxedGoods){
                detail = inputBoxed();
            } else if(item instanceof CannedFood){
                detail = inputCanned();
            } else if(item instanceof DryGoods){
                detail = inputDry();
            } else if(item instanceof Essentials){
                detail = inputEss();
            }else{
                System.out.println(ANSI_RED + "Invalid food type." + ANSI_RESET);
            }
            
            if(detail != null){
                ((Food) item).setDetail(detail);
            }
            
        }
    }
    
    public static void apparelAmend(int amendOption, Item item){
        
        if(amendOption == 2){
            //size
            String size;
            if(item instanceof Shoes){
                size = shoesSizeValidation();
            }else{
                size = sizeValidation();
            }
            
            ((Apparel) item).setSize(size);
        }
        
        if (amendOption == 3){
            //color
            String color = colorValidation();
            ((Apparel) item).setColor(color);
            
        } else if (amendOption == 4){
            //condition
            String condition = conditionValidation();
            ((Apparel) item).setCondition(condition);
            
        } else if(amendOption == 5){
            //brand
            String brand = brandValidation();
            ((Apparel) item).setBrand(brand);
            
        }else if (amendOption == 6 && item instanceof Shoes){
            String detail = inputShoes();
            ((Shoes) item).setDetail(detail);
            
        }
    }
    
    // -----------------------------------------
    // Part 5: Track donated items in categories
    // -----------------------------------------
    public static void trackItemByCategory(){
        
        System.out.println(ANSI_BLUE + "\n--- MONEY ---" + ANSI_RESET);
        System.out.print("Bank");
        printSameTable(BANK_PATH);
        
        System.out.print("\nCash");
        printSameTable(CASH_PATH);
        
        System.out.println(ANSI_BLUE + "\n--- FOOD ---" + ANSI_RESET);
        System.out.print("Boxed Goods");
        printSameTable(BOXED_PATH);
        
        System.out.print("\nBaked Goods");
        printSameTable(BAKED_PATH);
        
        System.out.print("\nCanned Foods");
        printSameTable(CANNED_PATH);
        
        System.out.print("\nDry Goods");
        printSameTable(DRY_PATH);
        
        System.out.print("\nEssentials");
        printSameTable(ESS_PATH);
        
        System.out.println(ANSI_BLUE + "\n--- APPAREL ---" + ANSI_RESET);
        System.out.print("Jackets");
        printSameTable(JACKET_PATH);
        
        System.out.print("\nPant");
        printSameTable(PANT_PATH);
        
        System.out.print("\nShirt");
        printSameTable(SHIRT_PATH);
        
        System.out.print("\nShoes");
        printSameTable(SHOES_PATH);
        
        System.out.print("\nSocks");
        printSameTable(SOCKS_PATH);
        
    }
    
    // ----------------------------------------
    // Part 6: List donation by different donor
    // ----------------------------------------
    public static void listByDiffDonor(){
        LinkedList<Item> itemList = loadAllItemIntoList();
        ManageDonors<Donor> donorList = new ManageDonors<>();
        donorList.loadFromFile("donors.txt");

        LinkedList<Donor> individualList = donorList.filterByCategory(Individual.class);
        LinkedList<Donor> organizationList = donorList.filterByCategory(Organization.class);
        
        // remove all empty space
        itemList.removeEmptyData();
        individualList.removeEmptyData();
        organizationList.removeEmptyData();

        System.out.print(ANSI_BLUE + "--- INDIVIDUAL ---" + ANSI_RESET);
        filterByDonor(itemList, individualList);

        System.out.print(ANSI_BLUE + "--- ORGANIZATION ---" + ANSI_RESET);
        filterByDonor(itemList, organizationList);
    }
    
    public static void filterByDonor(LinkedList<Item> itemList, LinkedList<Donor> donorList){
        itemList.removeEmptyData();
        donorList.removeEmptyData();

        if (donorList.head == null || itemList.head == null) {
            System.out.println("No donors or items to process.");
            return;
        }

        Node<Donor> currentDonor = donorList.head;
        String donorID;
        int stop = 0;
        int pageNum = 1;

        System.out.printf("\n| %-10s | %-10s | %-15s |\n", "Donor ID", "Item ID", "Item Category");
        while (currentDonor != null) {
            int print = 0;
            donorID = currentDonor.data.getId();

            Node<Item> currentItem = itemList.head;
            while (currentItem != null) {
                if (donorID.equals(currentItem.data.getDonorID())) {
                    stop++;
                    if (print == 0) {
                        System.out.printf("| %-10s | %-10s | %-15s |\n", donorID, currentItem.data.getId(), currentItem.data.getType());
                        print++;
                    } else {
                        System.out.printf("| %-10s | %-10s | %-15s |\n", "", currentItem.data.getId(), currentItem.data.getType());
                    }
                }

                if(stop == 50){
                    System.out.println(ANSI_BLUE +"- END OF PAGE " + pageNum + " -" + ANSI_RESET);
                    boolean cont = YN("50 records of current table had been shown. Do you want to continue shown more?");
                    if (cont){
                        pageNum++;
                        System.out.println(ANSI_BLUE +"\n- PAGE " + pageNum + " -" + ANSI_RESET);
                        System.out.printf("\n| %-10s | %-10s | %-15s |\n", "Donor ID", "Item ID", "Item Category");
                        stop = 0;
                    }else{
                        return;
                    }
                }
                
                currentItem = currentItem.next;
            }
            currentDonor = currentDonor.next;
        }

        System.out.println();
    }
    
    // --------------------------
    // Part 7: List all donations
    // --------------------------
    public static void listAll(){
        
        boolean cont = true;
        do{
            System.out.println(ANSI_BLUE + "\n - - - Item List - - -" + ANSI_RESET);
            String[] sortMenu = {
                "Sort and List All Money in Ascending", 
                "Sort and List All Money in Descending", 
                "Sort and List All Bank in Ascending",
                "Sort and List All Bank in Descending",
                "Sort and List All Cash in Ascending", 
                "Sort and List All Cash in Descending",
                "Sort and List All Food according Expiry Date",
                "Display All"
            };
            int sortSelection = menuIntReturn(sortMenu);

            LinkedList<Money> bankList = new LinkedList<>();
            bankList.loadFromFile(BANK_PATH);
            LinkedList<Money> cashList = new LinkedList<>();
            cashList.loadFromFile(CASH_PATH);
            LinkedList<Money> moneyList = new LinkedList<>();
            moneyList.loadFromFile(BANK_PATH);
            moneyList.appendList(cashList);

            LinkedList<Food> foodList = loadAllFoodToList();

            switch(sortSelection){
                case 1: 
                    System.out.println("\n --- Money List --- (Ascending Order)");
                    sortMoney(moneyList, 1, 1);
                    break;
                case 2: 
                    System.out.println("\n --- Money List --- (Descending Order)");
                    sortMoney(moneyList, 0, 1);
                    break;
                case 3:
                    System.out.println("\n --- Bank List --- (Ascending Order)");
                    sortMoney(bankList, 1, 1);
                    break;
                case 4:
                    System.out.println("\n --- Bank List --- (Descending Order)");
                    sortMoney(bankList, 0, 1);
                    break;
                case 5:
                    System.out.println("\n --- Cash List --- (Ascending Order)");
                    sortMoney(cashList, 1, 2);
                    break;
                case 6:
                    System.out.println("\n --- Cash List --- (Descending Order)");
                    sortMoney(cashList, 0, 2);
                    break;
                case 7:
                    System.out.println("\n --- Food List --- (According Expiry Date)");
                    sortFoodExp(foodList);
                    break;
                case 8:
                    System.out.println("\n --- Donation Item List --- ");
                    printAllIntoTable();
                    break;
                default:
                    System.out.println(ANSI_RED + "Invalid sort selection." + ANSI_RESET);
                    break;
            }
            
            cont = YN("Do you want to continue sort or view other items?");
            
        } while(cont == true);

    }
    
    public static void sortMoney(LinkedList<Money> moneyList, int asc, int header) {
        moneyList.removeEmptyData();

        if (moneyList.head == null) {
            System.out.println(ANSI_RED + "No such donated item." + ANSI_RESET);
            return;
        }
        

        if (header == 1){
            System.out.printf("| %-10s | %-10s | %-15s | %-14s | %-15s |\n", "Item ID", "Donor ID", "Item Category", "Amount Donated", "Bank Name");
        }else{
            System.out.printf("| %-10s | %-10s | %-15s | %-14s |\n", "Item ID", "Donor ID", "Item Category", "Amount Donated");
        }
        
        if (moneyList.head.next == null){
            System.out.println(moneyList.head.data.toString());
            return;
        }

        // Loop through all data in the list
        Node<Money> currentMoney = moneyList.head.next; // Start from the second node
        while (currentMoney != null) {
            
            // Get the position data should be inserted
            Node<Money> newPosition = moneyList.head;
            while (newPosition != currentMoney) {
                
                if (currentMoney.data.getAmount() < newPosition.data.getAmount() && asc == 1) {
                    break;
                }
                
                if (currentMoney.data.getAmount() > newPosition.data.getAmount() && asc == 0) {
                    break;
                }
                
                newPosition = newPosition.next;
            }

            // If the currentMoney is already in the correct position, continue
            if (newPosition == currentMoney) {
                currentMoney = currentMoney.next;
                continue;
            }

            // Remove currentMoney from its current position
            currentMoney.previous.next = currentMoney.next;
            if (currentMoney.next != null) {
                currentMoney.next.previous = currentMoney.previous;
            }

            // Insert currentMoney before newPosition
            if (newPosition == moneyList.head) {
                currentMoney.previous = null;
                currentMoney.next = moneyList.head;
                moneyList.head.previous = currentMoney;
                moneyList.head = currentMoney;
            } else {
                currentMoney.previous = newPosition.previous;
                currentMoney.next = newPosition;
                newPosition.previous.next = currentMoney;
                newPosition.previous = currentMoney;
            }

            currentMoney = currentMoney.next;
        }
        
        Node<Money> node = moneyList.head;
        int stop = 0;
        int pageNum = 1;
        while (node != null) {
            System.out.println(node.data.toString());
            stop++;
            
            if(stop == 50){
                System.out.println(ANSI_BLUE +"- END OF PAGE " + pageNum + " -" + ANSI_RESET);
                boolean cont = YN("50 records had shown. Do you want to continue shown more?");
                if (cont){
                    pageNum++;
                    System.out.println(ANSI_BLUE +"\n- PAGE " + pageNum + " -" + ANSI_RESET);
                    System.out.printf("| %-10s | %-10s | %-15s | %-20s | %-10s | %-8s | %-8s | %-15s |\n", "Item ID", "Donor ID", "Item Category", "Remarks", "Expiry Date", "Weight", "Status", "Food Type");
                    stop = 0;
                }else{
                    return;
                }
            }
            node = node.next;
        }
    }

    public static void sortFoodExp(LinkedList<Food> foodList){
        
        foodList.removeEmptyData();
        

        if (foodList.head == null) {
            System.out.println(ANSI_RED + "No such donated item." + ANSI_RESET);
            return;
        }
        
        System.out.printf("| %-10s | %-10s | %-15s | %-20s | %-10s | %-8s | %-8s | %-15s |\n", "Item ID", "Donor ID", "Item Category", "Remarks", "Expiry Date", "Weight", "Status", "Food Type");
        
        if (foodList.head.next == null){
            System.out.println(foodList.head.data.toString());
            return;
        }

        // Loop through all data in the list
        Node<Food> currentFood = foodList.head.next; // Start from the second node
        while (currentFood != null) {
            
            // Get the position data should be inserted
            Node<Food> newPosition = foodList.head;
            while (newPosition != currentFood) {
                if (currentFood.data.getExpiryDate().before(newPosition.data.getExpiryDate())) {
                    break;
                }
                newPosition = newPosition.next;
            }

            // If the currentMoney is already in the correct position, continue
            if (newPosition == currentFood) {
                currentFood = currentFood.next;
                continue;
            }

            // Remove currentMoney from its current position
            currentFood.previous.next = currentFood.next;
            if (currentFood.next != null) {
                currentFood.next.previous = currentFood.previous;
            }

            // Insert currentMoney before newPosition
            if (newPosition == foodList.head) {
                currentFood.previous = null;
                currentFood.next = foodList.head;
                foodList.head.previous = currentFood;
                foodList.head = currentFood;
            } else {
                currentFood.previous = newPosition.previous;
                currentFood.next = newPosition;
                newPosition.previous.next = currentFood;
                newPosition.previous = currentFood;
            }

            currentFood = currentFood.next;
        }

        Node<Food> node = foodList.head;
        int stop = 0;
        int pageNum = 1;
        while (node != null) {
            System.out.println(node.data.toString());
            stop++;
            
            if(stop == 50){
                System.out.println(ANSI_BLUE +"- END OF PAGE " + pageNum + " -" + ANSI_RESET);
                boolean cont = YN("50 records had shown. Do you want to continue shown more?");
                if (cont){
                    pageNum++;
                    System.out.println(ANSI_BLUE +"\n- PAGE " + pageNum + " -" + ANSI_RESET);
                    System.out.printf("| %-10s | %-10s | %-15s | %-20s | %-10s | %-8s | %-8s | %-15s |\n", "Item ID", "Donor ID", "Item Category", "Remarks", "Expiry Date", "Weight", "Status", "Food Type");
                    stop = 0;
                }else{
                    return;
                }
            }
            node = node.next;
        }
    }
    
    public static void printAllIntoTable() {
        String[] fileList = {BANK_PATH, CASH_PATH, JACKET_PATH, PANT_PATH, SHIRT_PATH, SHOES_PATH, SOCKS_PATH, BAKED_PATH, BOXED_PATH, CANNED_PATH, DRY_PATH, ESS_PATH};

        for (int i = 0; i < fileList.length; i++) {
            LinkedList<Item> list = new LinkedList<>();
            list.loadFromFile(fileList[i]);
            list.removeEmptyData();

            if (list.isEmpty()) {
                continue;
            } else {
                printListToTable(list);
            }
        }
    }
    
    // -----------------------------------------
    // Part 8: Filter donation based on criteria
    // -----------------------------------------
    public static void filterDonation(){
        
        boolean cont = true;
        do{
            System.out.println(ANSI_BLUE + "\n- - - Filter Selection - - -" + ANSI_RESET);
            String[] filterMenu = {"Filter by Item Type (e.g. Sport Shoes)", "Filter Food before and within Expiry's Year (e.g. 2025)"};
            int filterSelection = menuIntReturn(filterMenu);

            switch(filterSelection){
                case 1:
                    filterByItem();
                    break;
                case 2:
                    filterByYear();

                    break;
                default:
                    System.out.println(ANSI_RED + "Invalid filter selection." + ANSI_RESET);
                    break;
            }
            
            cont = YN("Do you want to continue filtering other items?");
            
        }while(cont == true);
    }
    
    public static void filterByItem(){
        String type = filterTypeValidation();
        
        if (
                type.equals("MONEY") ||
                type.equals("BANK") ||
                type.equals("CASH") ||
                type.equals("FOOD") ||
                type.equals("BAKED GOODS") ||
                type.equals("BOXED GOODS") ||
                type.equals("CANNED FOOD") ||
                type.equals("DRY GOODS") ||
                type.equals("ESSENTIALS") ||
                type.equals("JACKET") ||
                type.equals("PANT") ||
                type.equals("SHIRT") ||
                type.equals("SHOES") ||
                type.equals("SOCKS") 
                ){

            LinkedList<Item> list;
            list = loadAllItemIntoList();
            
            if (type.equals("MONEY")){
                list  = list.filterByCategory(Money.class);
            }else if(type.equals("BANK")){
                list = list.filterByCategory(Bank.class);
            }else if(type.equals("CASH")){
                list = list.filterByCategory(Cash.class);
            }else if(type.equals("FOOD")){
                list = list.filterByCategory(Food.class);
            }else if(type.equals("BAKED GOODS")){
                list = list.filterByCategory(BakedGoods.class);
            }else if(type.equals("BOXED GOODS")){
                list = list.filterByCategory(BoxedGoods.class);
            }else if(type.equals("CANNED FOOD")){
                list = list.filterByCategory(CannedFood.class);
            }else if(type.equals("DRY GOODS")){
                list = list.filterByCategory(DryGoods.class);
            }else if(type.equals("ESSENTIALS")){
                list = list.filterByCategory(Essentials.class);
            }else if(type.equals("JACKET")){
                list = list.filterByCategory(Jacket.class);
            }else if(type.equals("PANT")){
                list = list.filterByCategory(Pant.class);
            }else if(type.equals("SHIRT")){
                list = list.filterByCategory(Shirt.class);
            }else if(type.equals("SHOES")){
                list = list.filterByCategory(Shoes.class);
            }else{ // SOCKS
                list = list.filterByCategory(Socks.class);
            }
            
            if (list.isEmpty()){
                System.out.println(ANSI_RED + "No such item." + ANSI_RESET);
            }else{
                printListToTable(list);
            }
            
        }else{
            
            LinkedList<Food> foodList = loadAllFoodToList();
            
            LinkedList<Shoes> shoeList = new LinkedList<>();
            shoeList.loadFromFile(SHOES_PATH);
            
            if (type.equals("COOKIES")){
                filterFoodCategory(foodList, "Cookies");
            } else if (type.equals("CRACKERS")){
                filterFoodCategory(foodList, "Crackers");
            } else if (type.equals("CEREALS")){
                filterFoodCategory(foodList, "Cereals");
            } else if (type.equals("SNACKS")){
                filterFoodCategory(foodList, "Snacks");
            } else if (type.equals("BAKED BEANS")){
                filterFoodCategory(foodList, "Baked beans");
            } else if (type.equals("CHICKEN SOUP")){
                filterFoodCategory(foodList, "Chicken soup");
            } else if (type.equals("CORN")){
                filterFoodCategory(foodList, "Corn");
            } else if (type.equals("LYCHEE")){
                filterFoodCategory(foodList, "Lychee");
            } else if (type.equals("MEAT")){
                filterFoodCategory(foodList, "Meat");
            } else if (type.equals("MUSHROOM SOUP")){
                filterFoodCategory(foodList, "Mushroom soup");
            } else if (type.equals("PINEAPPLE")){
                filterFoodCategory(foodList, "Pineapple");
            } else if (type.equals("TOMATOES")){
                filterFoodCategory(foodList, "Tomatoes");
            } else if (type.equals("TUNA")){
                filterFoodCategory(foodList, "Tuna");
            } else if (type.equals("INSTANT NOODLES")){
                filterFoodCategory(foodList, "Instant Noodles");
            } else if (type.equals("OATS")){
                filterFoodCategory(foodList, "Oats");
            } else if (type.equals("PASTA")){
                filterFoodCategory(foodList, "Pasta");
            } else if (type.equals("RICE")){
                filterFoodCategory(foodList, "Rice");
            } else if (type.equals("OIL")){
                filterFoodCategory(foodList, "Oil");
            } else if (type.equals("PEPPER")){
                filterFoodCategory(foodList, "Pepper");
            } else if (type.equals("SALT")){
                filterFoodCategory(foodList, "Salt");
            } else if (type.equals("SUGAR")){
                filterFoodCategory(foodList, "Sugar");
            } else if (type.equals("SLIPPER")){
                filterShoesCategory(shoeList, "Slipper");
            } else{ // SPORT SHOES
                filterShoesCategory(shoeList, "Sport shoes");
            }
        }
    }
    
    public static String filterTypeValidation(){
        Scanner scan = new Scanner(System.in);
        System.out.printf("\n%-15s || %-15s | %-15s | %-15s | %-15s | %-15s |\n", "Money", "Bank", "Cash", "", "", "");
        System.out.println("-".repeat(108));
        System.out.printf("%-15s\n", "Food");
        System.out.printf("%-15s || %-159s |\n", "Subcategory", "Selection");
        System.out.printf("%-15s || %-15s | %-15s | %-15s | %-15s | %-15s |\n", "Baked Goods", "Cookies", "Crackers", "", "", "");
        System.out.printf("%-15s || %-15s | %-15s | %-15s | %-15s | %-15s |\n", "Boxed Goods", "Cereals", "Snacks", "", "", "");
        System.out.printf("%-15s || %-15s | %-15s | %-15s | %-15s | %-15s |\n", "Canned Food", "Baked Beans", "Chicken Soup", "Corn", "Lychee", "Meat");
        System.out.printf("%-15s || %-15s | %-15s | %-15s | %-15s | %-15s |\n", "", "Mushroom Soup", "Pineapple", "Tomatoes", "Tuna", "");
        System.out.printf("%-15s || %-15s | %-15s | %-15s | %-15s | %-15s |\n", "Dry Goods", "Instant Noodles", "Oats", "Pasta", "Rice", "", "", "", "", "");
        System.out.printf("%-15s || %-15s | %-15s | %-15s | %-15s | %-15s |\n", "Essentials", "Oil", "Pepper", "Salt", "Sugar", "", "", "", "", "");
        System.out.println("-".repeat(108));
        System.out.printf("%-15s\n", "Apparel");
        System.out.printf("%-15s || %-159s |\n", "Subcategory", "Selection");
        System.out.printf("%-15s || %-15s | %-15s | %-15s | %-15s | %-15s |\n", "Jacket", "", "", "", "", "");
        System.out.printf("%-15s || %-15s | %-15s | %-15s | %-15s | %-15s |\n", "Pant", "", "", "", "", "");
        System.out.printf("%-15s || %-15s | %-15s | %-15s | %-15s | %-15s |\n", "Shirt", "", "", "", "", "");
        System.out.printf("%-15s || %-15s | %-15s | %-15s | %-15s | %-15s |\n", "Shoes", "Slipper", "Sport Shoes", "", "", "");
        System.out.printf("%-15s || %-15s | %-15s | %-15s | %-15s | %-15s |\n", "Socks", "", "", "", "", "");
        System.out.print("\nItem Type: ");
        String type = null;
        
        boolean validType = false;
        while (validType == false){
            type = scan.nextLine();
            
            if(type.isEmpty()){
                System.out.println(ANSI_RED + "Cannot leave blank." + ANSI_RESET);
                System.out.print("Enter again: ");
            }else{
                type = type.toUpperCase();
                if (
                        type.equalsIgnoreCase("MONEY") ||
                        type.equalsIgnoreCase("BANK") ||
                        type.equalsIgnoreCase("CASH") ||
                        type.equalsIgnoreCase("FOOD") ||
                        type.equalsIgnoreCase("BAKED GOODS") ||
                        type.equalsIgnoreCase("COOKIES") ||
                        type.equalsIgnoreCase("CRACKERS") ||
                        type.equalsIgnoreCase("BOXED GOODS") ||
                        type.equalsIgnoreCase("CEREALS") ||
                        type.equalsIgnoreCase("SNACKS") ||
                        type.equalsIgnoreCase("CANNED FOOD") ||
                        type.equalsIgnoreCase("BAKED BEANS") ||
                        type.equalsIgnoreCase("CHICKEN SOUP") ||
                        type.equalsIgnoreCase("CORN") ||
                        type.equalsIgnoreCase("LYCHEE") ||
                        type.equalsIgnoreCase("MEAT") ||
                        type.equalsIgnoreCase("MUSHROOM SOUP") ||
                        type.equalsIgnoreCase("PINEAPPLE") ||
                        type.equalsIgnoreCase("TOMATOES") ||
                        type.equalsIgnoreCase("TUNA") ||
                        type.equalsIgnoreCase("DRY GOODS") ||
                        type.equalsIgnoreCase("INSTANT NOODLES") ||
                        type.equalsIgnoreCase("OATS") ||
                        type.equalsIgnoreCase("PASTA") ||
                        type.equalsIgnoreCase("RICE") ||
                        type.equalsIgnoreCase("ESSENTIALS") ||
                        type.equalsIgnoreCase("OIL") ||
                        type.equalsIgnoreCase("PEPPER") ||
                        type.equalsIgnoreCase("SALT") ||
                        type.equalsIgnoreCase("SUGAR") ||
                        type.equalsIgnoreCase("APPAREL") ||
                        type.equalsIgnoreCase("JACKET") ||
                        type.equalsIgnoreCase("PANT") ||
                        type.equalsIgnoreCase("SHIRT") ||
                        type.equalsIgnoreCase("SHOES") ||
                        type.equalsIgnoreCase("SLIPPER") ||
                        type.equalsIgnoreCase("SPORT SHOES") ||
                        type.equalsIgnoreCase("SOCKS") 
                        ){
                    
                    validType = true;
                    
                }else{
                    System.out.println(ANSI_RED + "\nInvalid input." + ANSI_RESET);
                    System.out.print("Enter again: "); 
                }
            }
        }
        
        return type;
    }
    
    public static void filterFoodCategory(LinkedList<Food> list, String type) {
        if (list.head == null) {
            System.out.println(ANSI_RED + "No items to filter." + ANSI_RESET);
            return;
        }

        Node<Food> currentFood = list.head;
        while (currentFood != null) {

            if (!currentFood.data.getDetail().equals(type)) {
                
                if (currentFood.previous != null) {
                    currentFood.previous.next = currentFood.next;
                } else {
                    // If current node is the head
                    list.head = currentFood.next;
                }

                if (currentFood.next != null) {
                    currentFood.next.previous = currentFood.previous;
                } else {
                    // If current node is the tail
                    list.tail = currentFood.previous;
                }
            }

            currentFood = currentFood.next;
        }
        
        LinkedList<Item> itemList = new LinkedList<>();
        Node<Food> currentNode = list.head;
        while(currentNode != null){
            Item item = currentNode.data;
            itemList.insert(item);
            currentNode = currentNode.next;
        }

        if (list.head == null) {
            System.out.println(ANSI_RED + "No such item." + ANSI_RESET);
        } else {
            printListToTable(itemList);
        }
    }
    
    public static void filterShoesCategory(LinkedList<Shoes> list, String type) {
        if (list.head == null) {
            System.out.println(ANSI_RED + "No items to filter." + ANSI_RESET);
            return;
        }

        Node<Shoes> currentShoes = list.head;
        while (currentShoes != null) {

            if (!currentShoes.data.getDetail().equals(type)) {
                
                if (currentShoes.previous != null) {
                    currentShoes.previous.next = currentShoes.next;
                } else {
                    // If current node is the head
                    list.head = currentShoes.next;
                }

                if (currentShoes.next != null) {
                    currentShoes.next.previous = currentShoes.previous;
                } else {
                    // If current node is the tail
                    list.tail = currentShoes.previous;
                }
            }

            currentShoes = currentShoes.next;
        }
        
        LinkedList<Item> itemList = new LinkedList<>();
        Node<Shoes> currentNode = list.head;
        while(currentNode != null){
            Item item = currentNode.data;
            itemList.insert(item);
            currentNode = currentNode.next;
        }
        
        if (list.head == null) {
            System.out.println(ANSI_RED + "No such item." + ANSI_RESET);
        } else {
            printListToTable(itemList);
        }
    }
    
    public static void filterByYear(){
        LinkedList<Food> foodList = loadAllFoodToList();
        int year = validYear();

        if (foodList.head == null) {
            System.out.println(ANSI_RED + "No items to filter." + ANSI_RESET);
            return;
        }

        Node<Food> currentNode = foodList.head;

        while (currentNode != null) {
            Date dataDate = currentNode.data.getExpiryDate();
            int dataYear = dataDate.getYear() + 1900; // 124 + 1900 = 2024

            if (dataYear > year) {
                
                if (currentNode.previous != null) {
                    currentNode.previous.next = currentNode.next;
                } else {
                    // If the node to remove is the head
                    foodList.head = currentNode.next;
                }

                if (currentNode.next != null) {
                    currentNode.next.previous = currentNode.previous;
                } else {
                    // If the node to remove is the tail
                    foodList.tail = currentNode.previous;
                }
            } 
                
            currentNode = currentNode.next;
        }
        
        LinkedList<Item> itemList = new LinkedList<>();
        currentNode = foodList.head;
        while(currentNode != null){
            Item item = currentNode.data;
            itemList.insert(item);
            currentNode = currentNode.next;
        }

        if (foodList.head == null) {
            System.out.println(ANSI_RED + "No such item." + ANSI_RESET);
        } else {
            printListToTable(itemList);
        }
    }
    
    public static int validYear() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the year: ");

        int year = 0;
        boolean validYear = false;
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();

        while (validYear == false) {
            String inputYear = scan.nextLine().trim();

            if (inputYear.isEmpty()) {
                System.out.println(ANSI_RED + "/nCannot leave blank." + ANSI_RESET);
                System.out.print("Enter again: ");
            } else {
                try {
                    year = Integer.parseInt(inputYear);
                    if (year < currentYear) {
                        System.out.println(ANSI_RED + "/nInvalid year, please enter the current year or a future year." + ANSI_RESET);
                        System.out.print("Enter again: ");
                    } else {
                        validYear = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println(ANSI_RED + "/nInvalid input. Please enter a valid year." + ANSI_RESET);
                    System.out.print("Enter again: ");
                }
            }
        }

        return year;
    }
    
    // --------------------------------
    // Part 9: Generate summary reports 
    // --------------------------------
    public static void report(){
        
        boolean cont = true;
        do{
            System.out.println(ANSI_BLUE + "\n- - - Report - - -" + ANSI_RESET);
            String[] reportMenu = {
                "Donor with the Highest Contribution", 
                "Analysis of Food Items with Future Expiry Year",
                "Most Frequently Donated Item Category (Money, Food, Apparel)"
            };
            int reportSelection = menuIntReturn(reportMenu);

            switch(reportSelection){
                case 1:
                    donorContributionReport();
                    break;
                case 2:
                    foodExpiryReport();
                    break;
                case 3:
                    mostFrequentItem();
                    break;
                default:
                    System.out.println(ANSI_RED + "Invalid report selection." + ANSI_RESET);
                    break;
            }
            
            cont = YN("Do you want to view another report?");
            
        }while(cont == true);
    }
    
    public static void donorContributionReport(){
        LinkedList<Item> itemList = loadAllItemIntoList();
        ManageDonors<Donor> donorList = new ManageDonors<>();
        donorList.loadFromFile("donors.txt");
        
        itemList.removeEmptyData();
        donorList.removeEmptyData();
        
        if (donorList.isEmpty()){
            System.out.println(ANSI_RED + "No donor exist." + ANSI_RESET);
            return;
        }
        
        if (itemList.isEmpty()){
            System.out.println(ANSI_RED + "No item in stock." + ANSI_RESET);
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
            
            printStar(sum);
            
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
    
    public static void foodExpiryReport(){
        LinkedList<Food> itemList = loadAllFoodToList();
        itemList.removeEmptyData();
        
        if (itemList.isEmpty()){
            System.out.println(ANSI_RED + "No food item in stock." + ANSI_RESET);
            return;
        }
        
        System.out.println("\nAnalysis of Food Item with Future Expiry Year");
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        
        int largestYear = currentYear;
        Node<Food> tempNode = itemList.head;
        while (tempNode != null) {
            int dataYear = tempNode.data.getExpiryDate().getYear() + 1900;
            if (dataYear > largestYear) {
                largestYear = dataYear;
            }
            tempNode = tempNode.next;
        }
        
        System.out.printf("| %-21s | %-10s | %-10s | %-10s | %-10s | %-10s |\n", "Year\\Food Category", "Baked", "Boxed", "Canned", "Dry", "Essential");
        while(currentYear <= largestYear){
            int sum = 0, fa = 0, fo = 0, fc = 0, fd = 0, fe = 0;
            
            Node<Food> currentNode = itemList.head;
            while(currentNode != null){
                
                int dataYear = currentNode.data.getExpiryDate().getYear() + 1900;
                
                if (dataYear == currentYear){
                    sum ++;
                    if (currentNode.data instanceof BakedGoods){
                        fa++;
                    }else if(currentNode.data instanceof BoxedGoods){
                        fo++;
                    }else if(currentNode.data instanceof CannedFood){
                        fc++;
                    }else if(currentNode.data instanceof DryGoods){
                        fd++;
                    }else{
                        fe++;
                    }
                }
                
                currentNode = currentNode.next;
            }
            
            System.out.printf("| %-4s %-4s %-4d %-6s | %-10d | %-10d | %-10d | %-10d | %-10d |\n", 
                    currentYear, " -> ", sum , " items", fa, fo, fc, fd, fe);
            
            currentYear ++;
        }
    }
    
    public static void mostFrequentItem(){
        LinkedList<Item> list = loadAllItemIntoList();
        
        list.removeEmptyData();
        
        if (list.isEmpty()){
            System.out.println(ANSI_RED + "No item in stock." + ANSI_RESET);
            return;
        }
        
        System.out.println("\nMost Frequent Donation Item Category");
        LinkedList<Money> moneyList = list.filterByCategory(Money.class);
        LinkedList<Food> foodList = list.filterByCategory(Food.class);
        LinkedList<Apparel> appList = list.filterByCategory(Apparel.class);
        
        int sumM = 0;
        System.out.printf("%-10s", "Money");
        for(int i = 0; i < moneyList.length(); i++){
            sumM ++;
        }
        System.out.print("(" + sumM + ")");
        printStar(sumM);
        
        int sumF = 0;
        System.out.printf("\n%-10s", "Food");
        for(int i = 0; i < foodList.length(); i++){
            sumF ++;
        }
        System.out.print("(" + sumF + ")");
        printStar(sumF);
        
        int sumA = 0;
        System.out.printf("\n%-10s", "Apparel");
        for(int i = 0; i < appList.length(); i++){
            sumA ++;
        }
        System.out.print("(" + sumA + ")");
        printStar(sumA);
        
        String category;
        int max = 0;
        if (sumM > sumF && sumM > sumA){
            category = "Money";
            max = sumM;
        }else if (sumF > sumM && sumF > sumA){
            category = "Food";
            max = sumF;
        }else{
            category = "Apparel";
            max = sumA;
        }
        
        System.out.println("\nRemarks: Symbol * will be display if item's total exceed 50 and each * represent 50 items");
        System.out.println("The most frequent donated item category is " + category + ", with total amount of " + max );
    }
    
    public static void printStar(int count){
        if (count > 50){
            int left = count % 50;
            for (int i = 0; i < left; i ++){
                System.out.print(ANSI_BLUE + " *" + ANSI_RESET);
            }
        }
    }
}