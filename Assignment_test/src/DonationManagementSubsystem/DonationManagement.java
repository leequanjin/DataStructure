/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DonationManagementSubsystem;

import CommonResources.Node;
import CommonResources.LinkedList;

import DonorSubsystem.Donor;
import DonorSubsystem.Individual;
import DonorSubsystem.Organization;


import DonationList.Item;
import DonationList.Bank;
import DonationList.Cash;
import DonationList.BakedGoods;
import DonationList.BoxedGoods;
import DonationList.CannedFood;
import DonationList.DryGoods;
import DonationList.Essentials;
import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Scanner;

/**
 *
 * @author Heng Pei Lin
 */
public class DonationManagement {
    
    private static final String DONOR_PATH = "C:\\Users\\Asus\\Desktop\\DS_Assign\\DataStructure\\DonationListFile\\donor.txt";
    private static final String BANK_PATH = "C:\\Users\\Asus\\Desktop\\DS_Assign\\DataStructure\\DonationListFile\\bank.txt";
    private static final String CASH_PATH = "C:\\Users\\Asus\\Desktop\\DS_Assign\\DataStructure\\DonationListFile\\cash.txt";
    private static final String BAKED_PATH = "C:\\Users\\Asus\\Desktop\\DS_Assign\\DataStructure\\DonationListFile\\bakedFood.txt";
    private static final String BOXED_PATH = "C:\\Users\\Asus\\Desktop\\DS_Assign\\DataStructure\\DonationListFile\\boxedFood.txt";
    private static final String CANNED_PATH = "C:\\Users\\Asus\\Desktop\\DS_Assign\\DataStructure\\DonationListFile\\cannedFood.txt";
    private static final String DRY_PATH = "C:\\Users\\Asus\\Desktop\\DS_Assign\\DataStructure\\DonationListFile\\dryFood.txt";
    private static final String ESS_PATH = "C:\\Users\\Asus\\Desktop\\DS_Assign\\DataStructure\\DonationListFile\\essential.txt";
    
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
            String dmSChoice = null;
            boolean validDMChoice = false;

            while (!validDMChoice) {
                dmSChoice = scan.nextLine();
                if(dmSChoice.isEmpty()){
                
                    System.out.println(ANSI_RED + "Cannot leave blank.\n" + ANSI_RESET);
                    System.out.print("Enter again: ");
                    
                }else{
                
                    try {
                        dmChoice = Integer.parseInt(dmSChoice);

                        if (dmChoice < 1 || dmChoice > 10) {
                            System.out.println(ANSI_RED + "Please enter a number between 1 and 10.\n" + ANSI_RESET);
                            System.out.print("Enter again: ");
                        }else {
                            validDMChoice = true; 
                        }

                    } catch (NumberFormatException e) {
                        System.out.println(ANSI_RED + "Invalid input. Please enter an integer number.\n" + ANSI_RESET);
                        System.out.print("Enter again: ");
                    }
                    
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
        Individual a = new Individual("EI00000", "Anonymous","private"); 
        Individual idv = new Individual("EI00001", "HAHA","public"); 
        
        // instance organization
        Organization org = new Organization("EO00001", "OMO Company","private");
        
        // save into list
        LinkedList dList = new LinkedList();
        dList.insert(a);
        dList.insert(idv);
        dList.insert(org);
        
        //idvList.show();
        
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
    
    public static String idGenerator(String ab, String fileName){
        int lineCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((br.readLine()) != null) {
                lineCount++;
            }
        } catch (IOException e) {
            
        }
        
        return (ab + String.format("%05d", lineCount + 1));
    }
    
    // -------------------------
    // Part 1: Add new donation
    // -------------------------
    public static void addDonation(){
        Scanner scan = new Scanner(System.in);
        
        boolean contAddDonation = true;
        while(contAddDonation){
            
            System.out.println(ANSI_BLUE + " - - - Add Donation - - - " + ANSI_RESET);

            System.out.println("Type of donor\n"
                    + "1. Individual\n"
                    + "2. Organisation");
            System.out.print("Enter donor's type: ");
            int dType = 0;
            boolean validDType = false;
            while(validDType == false){
                String dSType = scan.nextLine();
                if(dSType.isEmpty()){
                
                    System.out.println(ANSI_RED + "Cannot leave blank.\n" + ANSI_RESET);
                    System.out.print("Enter again: ");
                }else{
                    try {
                        dType = Integer.parseInt(dSType);

                        if (dType < 1 || dType > 3) {
                            
                            System.out.println(ANSI_RED + "Please enter a number between 1 to 3.\n" + ANSI_RESET);
                            System.out.print("Enter again: ");
                            
                        } else {
                            validDType = true; 
                        }

                    } catch (NumberFormatException e) {

                        System.out.println(ANSI_RED + "Invalid input. Please enter an integer number.\n" + ANSI_RESET);
                        System.out.print("Enter again: ");

                    }
                }
            }

            System.out.print("Enter donor's id: ");
            String dID = null;
            boolean validID = false;
            while(validID == false){
                dID = scan.nextLine();
                
                if(dID.isEmpty()){
                    
                    System.out.println(ANSI_RED + "Cannot leave blank.\n" + ANSI_RESET);
                    System.out.print("Enter again: ");
                    
                } else if(dID.trim().length() != 7){
                    
                    System.out.println(ANSI_RED + "Invalid length. Format of ID should be AA00000.\n" + ANSI_RESET);
                    System.out.print("Enter again: ");
                    
                } else {
                    dID = dID.substring(0,2).toUpperCase() + dID.substring(2, 7);
                    switch(dType){
                        case 1: // individual (EI)
                            if (dID.substring(0, 2).equals("EI")) {
                                validID = true;
                            } else {
                                System.out.println(ANSI_RED + "Invalid format. Format of individual donor should be EI00000.\n" + ANSI_RESET);
                                System.out.print("Enter again: ");
                            }
                            break;
                        case 2: // organization (EO)
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
                System.out.println(ANSI_RED + "Donor does not exist." + ANSI_RESET);
                contAddDonation = YN("Do you want to continue sign up a new donor?");
                if(contAddDonation == true){
                    // create a new one then load into the linkedlist
                } else{
                    break;
                }
            }
            
            boolean contAddItem = true;
            while(contAddItem){
            
                addItem();
                
                contAddItem = YN("Do you want to continue add item?");
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
    
    public static boolean chkDonorExist(int dType, String dID, LinkedList donorList){
        LinkedList<Donor> tempDonorList = new LinkedList<>();
        
        tempDonorList.loadFromFile(DONOR_PATH);
        tempDonorList.show();
        
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
                    case 2: // Organization
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
    
    public static void addItem(){
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Number of item wish to add: ");
        int numItem = 0;
        boolean validNumItem = false;
        while(validNumItem == false){
            String numSItem = scan.nextLine();
            if(numSItem .isEmpty()){

                System.out.println(ANSI_RED + "Cannot leave blank.\n" + ANSI_RESET);
                System.out.print("Enter again: ");
            }else{
                try {
                    numItem = Integer.parseInt(numSItem );

                    if (numItem < 1) {
                        System.out.println(ANSI_RED + "The number cannot be 0 or negative.\n" + ANSI_RESET);

                        scan.nextLine();
                        System.out.print("Enter again: ");
                    } else {
                        validNumItem = true; 
                    }

                } catch (NumberFormatException e) {

                    System.out.println(ANSI_RED + "Invalid input. Please enter an integer number.\n" + ANSI_RESET);
                    System.out.print("Enter again: ");

                }
            }
        }
        
        LinkedList<Item> newItemList = new LinkedList();
        
        for (int i = 0; i< numItem; i++){
            
            System.out.println( "\n - Item " + (i+1) + " - \n"
                    + "Item Category\n"
                    + "1. Bank\n"
                    + "2. Cash\n"
                    + "3. Food\n"
                    + "4. Apparel");
            System.out.print("Enter item category: ");
            int itemCat = 0;
            boolean validItemCat = false;
            while(validItemCat== false){
                String SItemCat = scan.nextLine();
                if(SItemCat.isEmpty()){

                    System.out.println(ANSI_RED + "Cannot leave blank.\n" + ANSI_RESET);
                    System.out.print("Enter again: ");
                    
                }else{
                    try {
                        itemCat = Integer.parseInt(SItemCat);

                        if (itemCat < 1 || itemCat > 4) {
                            System.out.println(ANSI_RED + "The number must between 1 to 4.\n" + ANSI_RESET);

                            scan.nextLine();
                            System.out.print("Enter again: ");
                            
                        } else {
                            validItemCat = true; 
                        }

                    } catch (NumberFormatException e) {

                        System.out.println(ANSI_RED + "Invalid input. Please enter an integer number.\n" + ANSI_RESET);
                        System.out.print("Enter again: ");

                    }
                }
            }
            
            switch(itemCat){
                case 1:
                    inputBank(newItemList);
                    break;
                case 2:
                    inputCash(newItemList);
                    break;
                case 3:
                    inputFood(newItemList);
                    break;
                case 4:
                    inputApparel(newItemList);
                    break;
                default:
                    System.out.println(ANSI_RED + "Invalid choice." + ANSI_RESET);
            }
            
            //show all item to be added
            System.out.println("Item to be add:");
            newItemList.show();
            //saveItemToFile(newItemList);
            
        }
    }
    
    public static void inputBank(LinkedList<Item> newItemList){
        Scanner scan = new Scanner(System.in);
        
        // id
        String id = idGenerator("MB", BANK_PATH);
        //System.out.println("Current id is: " + id);
        
        // amount
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
        
        // bank name
        System.out.println("\nBank Type\n"
                + "1. Affin Bank\n"
                + "2. Alliance Bank\n"
                + "3. AmBank\n"
                + "4. CIMB\n"
                + "5. Hong Leong Bank\n"
                + "6. May Bank\n"
                + "7. Public Bank\n"
                + "8. RHB Bank");
        System.out.print("Enter bank type: ");
        String bankName = null;
        int bankType = 0;
        boolean validBankType = false;
        while(validBankType == false){
            bankName = scan.nextLine();
            
            if(bankName.isEmpty()){

                System.out.println(ANSI_RED + "Cannot leave blank.\n" + ANSI_RESET);
                System.out.print("Enter again: ");

            }else{
                try {
                    bankType = Integer.parseInt(bankName);

                    if (bankType < 1 || bankType > 8) {
                        System.out.println(ANSI_RED + "Invalid integer. Please enter between 1 and 8.\n" + ANSI_RESET);

                        System.out.print("Enter again: ");

                    } else {
                        validBankType = true; 
                    }

                } catch (NumberFormatException e) {

                    System.out.println(ANSI_RED + "Invalid input. Please enter correct amount.\n" + ANSI_RESET);
                    System.out.print("Enter again: ");

                }
            }
        }
        
        if(validBankType == true){
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
            }
        }
        
        Bank tempBank = new Bank(id, amt, bankName);
        
        newItemList.insert(tempBank);
    }
    
    public static void inputCash(LinkedList<Item> newItemList){
        Scanner scan = new Scanner(System.in);
        
        // id
        String id = idGenerator("MC", CASH_PATH);
        //System.out.println("Current id is: " + id);
        
        // amount
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
        
        Cash tempCash = new Cash(id, amt);
        
        newItemList.insert(tempCash);
    }
    
    public static void inputFood(LinkedList<Item> newItemList){
        Scanner scan = new Scanner(System.in);
        
        // food category
        System.out.println("\nFood Category\n"
                + "1. Baked Goods\n"
                + "2. Baxed Goods\n"
                + "3. Canned Food\n"
                + "4. Dry Goods\n"
                + "5. Essentials");
        System.out.print("Enter food type: ");
        String foodCatName = null;
        int foodCat = 0;
        boolean validFoodCat = false;
        while(validFoodCat == false){
            foodCatName = scan.nextLine();
            
            if(foodCatName.isEmpty()){

                System.out.println(ANSI_RED + "Cannot leave blank.\n" + ANSI_RESET);
                System.out.print("Enter again: ");

            }else{
                try {
                    foodCat = Integer.parseInt(foodCatName);

                    if (foodCat < 1 || foodCat > 5) {
                        System.out.println(ANSI_RED + "Invalid integer. Please enter between 1 to 5.\n" + ANSI_RESET);

                        System.out.print("Enter again: ");

                    } else {
                        validFoodCat = true; 
                    }

                } catch (NumberFormatException e) {

                    System.out.println(ANSI_RED + "Invalid input. Please enter correct amount.\n" + ANSI_RESET);
                    System.out.print("Enter again: ");

                }
            }
        }
        
        String id = null;
        if(validFoodCat == true){
            switch(foodCat){
                case 1: 
                    System.out.println("\nBaked Goods");
                    id = idGenerator("FA", BAKED_PATH);
                    commonFoodInput(id, newItemList, foodCat);
                    break;
                case 2:
                    System.out.println("\nBoxed Goods");
                    id = idGenerator("FO", BOXED_PATH);
                    commonFoodInput(id, newItemList, foodCat);
                    break;
                case 3:
                    System.out.println("\nCanned Food");
                    id = idGenerator("FC", CANNED_PATH);
                    commonFoodInput(id, newItemList, foodCat);
                    break;
                case 4:
                    System.out.println("\nDry Goods");
                    id = idGenerator("FD", DRY_PATH);
                    commonFoodInput(id, newItemList, foodCat);
                    //inputDry();
                    break;
                case 5:
                    System.out.println("\nEssentials");
                    id = idGenerator("FE", ESS_PATH);
                    commonFoodInput(id, newItemList, foodCat);
                    //inputEssentials();
                    break;
                default:
                    System.out.println(ANSI_RED + "Invalid food category.\n" + ANSI_RESET);
            }
        }
    }
    
    public static void commonFoodInput(String id, LinkedList<Item> newItemList, int foodCat){
        
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Item with '*' is compulsary to be enter.");
        
        // quantity
        System.out.print("Quantity*: ");
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
        
        //note
        System.out.print("Remarks: ");
        String note = scan.nextLine();
        
        //expiryDate
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
                        System.out.println("1. Enter again\n"
                                        + "2. Discard");
                        System.out.print("Enter selection: ");
                        String input = null;
                        int selection = 0;
                        boolean validInput = false;
                        while(validInput== false){
                            input = scan.nextLine();

                            if(input.isEmpty()){

                                System.out.println(ANSI_RED + "Cannot leave blank.\n" + ANSI_RESET);
                                System.out.print("Enter again: ");

                            }else{
                                try {
                                    selection = Integer.parseInt(input);

                                    if (selection < 1 || selection > 2) {
                                        
                                        System.out.println(ANSI_RED + "Invalid integer. Please enter only 1 or 2.\n" + ANSI_RESET);
                                        System.out.print("Enter again: ");

                                    } else {
                                        validInput = true; 
                                    }

                                } catch (NumberFormatException e) {

                                    System.out.println(ANSI_RED + "Invalid input. Please enter correct selection.\n" + ANSI_RESET);
                                    System.out.print("Enter again: ");

                                }
                            }
                        }
                        
                        if(validInput == true){
                            if(selection == 1){
                                System.out.print("Enter date again: ");
                            }else{
                                inputFood(newItemList);
                            }
                        }
                        
                    } else {
                        validExp = true;
                    }
                    
                } catch (ParseException e) {
                    System.out.println("Invalid date format. Please enter the date in dd/MM/yyyy format.");
                }
                
            }
        }
        
        //weight
        System.out.print("Weight(gram)*: ");
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
        
        //status
        System.out.println("\nFood Status\n"
                + "1. Good\n"
                + "2. Spoil\n"
                + "3. Expired");
        System.out.print("Enter food status*: ");
        String foodStaName = null;
        int foodSta = 0;
        boolean validFoodSta = false;
        while(validFoodSta == false){
            foodStaName = scan.nextLine();
            
            if(foodStaName.isEmpty()){

                System.out.println(ANSI_RED + "Cannot leave blank.\n" + ANSI_RESET);
                System.out.print("Enter again: ");

            }else{
                try {
                    foodSta = Integer.parseInt(foodStaName);

                    if (foodSta < 1 || foodSta > 3) {
                        
                        System.out.println(ANSI_RED + "Invalid integer. Please enter between 1 to 3.\n" + ANSI_RESET);
                        System.out.print("Enter again: ");

                    } else {
                        validFoodSta = true; 
                    }

                } catch (NumberFormatException e) {

                    System.out.println(ANSI_RED + "Invalid input. Please enter correct amount.\n" + ANSI_RESET);
                    System.out.print("Enter again: ");

                }
            }
        }
        
        if(validFoodSta == true){
            switch(foodSta){
                case 1: 
                    foodStaName = "Good";
                    break;
                case 2:
                    foodStaName = "Spoil";
                    break;
                case 3:
                    foodStaName = "Expired";
                    break;
                default:
                    System.out.println(ANSI_RED + "Invalid food status.\n" + ANSI_RESET);
            }
        }
        
        switch(foodCat){
            case 1: 
                String bakedName = inputBaked();
                BakedGoods baG = new BakedGoods(id, qty, note, expiryDate, w, foodStaName, bakedName);
                newItemList.insert(baG);
                break;
            case 2:
                String boxedName = inputBoxed();
                BoxedGoods boG = new BoxedGoods(id, qty, note, expiryDate, w, foodStaName, boxedName);
                newItemList.insert(boG);
                break;
            case 3:
                String cannedName = inputCanned();
                CannedFood cF = new CannedFood(id, qty, note, expiryDate, w, foodStaName, cannedName);
                newItemList.insert(cF);
                break;
            case 4:
                String dryName = inputDry();
                DryGoods dG = new DryGoods(id, qty, note, expiryDate, w, foodStaName, dryName);
                newItemList.insert(dG);
                break;
            case 5:
                String essName = inputEss();
                Essentials eG = new Essentials(id, qty, note, expiryDate, w, foodStaName, essName);
                newItemList.insert(eG);
                break;
            default:
                System.out.println(ANSI_RED + "Invalid food category.\n" + ANSI_RESET);
        }
        
    }
    
    public static String inputBaked() {
        Scanner scan = new Scanner(System.in);
        
        // baked food type
        System.out.println("\nBaked Food Type\n"
                + "1. Cookies\n"
                + "2. Crackers");
        System.out.print("Enter baked food type: ");
        String name = null;
        int type = 0;
        boolean validType = false;
        while(validType == false){
            name = scan.nextLine();
            
            if(name.isEmpty()){

                System.out.println(ANSI_RED + "Cannot leave blank.\n" + ANSI_RESET);
                System.out.print("Enter again: ");

            }else{
                try {
                    type = Integer.parseInt(name);

                    if (type < 1 || type > 2) {
                        System.out.println(ANSI_RED + "Invalid integer. Please enter between 1 to 2.\n" + ANSI_RESET);

                        System.out.print("Enter again: ");

                    } else {
                        validType = true; 
                    }

                } catch (NumberFormatException e) {

                    System.out.println(ANSI_RED + "Invalid input. Please enter correct integer.\n" + ANSI_RESET);
                    System.out.print("Enter again: ");

                }
            }
        }
        
        if(validType == true){
            switch(type){
                case 1: 
                    name = "Cookies";
                    break;
                case 2:
                    name = "Crackers";
                    break;
                default:
                    System.out.println(ANSI_RED + "Invalid bank name.\n" + ANSI_RESET);
            }
        }
        
        return name;
    }
    
    public static String inputBoxed(){
        Scanner scan = new Scanner(System.in);
        
        // boxed food type
        System.out.println("\nBoxed Food Type\n"
                + "1. Cereals\n"
                + "2. Snacks");
        System.out.print("Enter boxed food type: ");
        String name = null;
        int type = 0;
        boolean validType = false;
        while(validType == false){
            name = scan.nextLine();
            
            if(name.isEmpty()){

                System.out.println(ANSI_RED + "Cannot leave blank.\n" + ANSI_RESET);
                System.out.print("Enter again: ");

            }else{
                try {
                    type = Integer.parseInt(name);

                    if (type < 1 || type > 2) {
                        System.out.println(ANSI_RED + "Invalid integer. Please enter between 1 to 2.\n" + ANSI_RESET);

                        System.out.print("Enter again: ");

                    } else {
                        validType = true; 
                    }

                } catch (NumberFormatException e) {

                    System.out.println(ANSI_RED + "Invalid input. Please enter correct integer.\n" + ANSI_RESET);
                    System.out.print("Enter again: ");

                }
            }
        }
        
        if(validType == true){
            switch(type){
                case 1: 
                    name = "Cereals";
                    break;
                case 2:
                    name = "Snacks";
                    break;
                default:
                    System.out.println(ANSI_RED + "Invalid bank name.\n" + ANSI_RESET);
            }
        }
        
        return name;
    }
    
    public static String inputCanned(){
        Scanner scan = new Scanner(System.in);
        
        // canned food type
        System.out.println("\nCanned Food Type\n"
                + "1. Baked beans\n"
                + "2. Chicken soup\n"
                + "3. Corn\n"
                + "4. Lychee\n"
                + "5. Meat\n"
                + "6. Mushroom soup\n"
                + "7. Pineapple\n"
                + "8. Tomatoes\n"
                + "9. Tuna");
        System.out.print("Enter canned food type: ");
        String name = null;
        int type = 0;
        boolean validType = false;
        while(validType == false){
            name = scan.nextLine();
            
            if(name.isEmpty()){

                System.out.println(ANSI_RED + "Cannot leave blank.\n" + ANSI_RESET);
                System.out.print("Enter again: ");

            }else{
                try {
                    type = Integer.parseInt(name);

                    if (type < 1 || type > 9) {
                        System.out.println(ANSI_RED + "Invalid integer. Please enter between 1 to 9.\n" + ANSI_RESET);

                        System.out.print("Enter again: ");

                    } else {
                        validType = true; 
                    }

                } catch (NumberFormatException e) {

                    System.out.println(ANSI_RED + "Invalid input. Please enter correct integer.\n" + ANSI_RESET);
                    System.out.print("Enter again: ");

                }
            }
        }
        
        if(validType == true){
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
            }
        }
        
        return name;
    }
    
    public static String inputDry(){
        Scanner scan = new Scanner(System.in);
        
        // dry food type
        System.out.println("\nDry Food Type\n"
                + "1. Instant noodles\n"
                + "2. Oats\n"
                + "3. Pasta\n"
                + "4. Rice");
        System.out.print("Enter boxed food type: ");
        String name = null;
        int type = 0;
        boolean validType = false;
        while(validType == false){
            name = scan.nextLine();
            
            if(name.isEmpty()){

                System.out.println(ANSI_RED + "Cannot leave blank.\n" + ANSI_RESET);
                System.out.print("Enter again: ");

            }else{
                try {
                    type = Integer.parseInt(name);

                    if (type < 1 || type > 4) {
                        System.out.println(ANSI_RED + "Invalid integer. Please enter between 1 to 4.\n" + ANSI_RESET);

                        System.out.print("Enter again: ");

                    } else {
                        validType = true; 
                    }

                } catch (NumberFormatException e) {

                    System.out.println(ANSI_RED + "Invalid input. Please enter correct integer.\n" + ANSI_RESET);
                    System.out.print("Enter again: ");

                }
            }
        }
        
        if(validType == true){
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
            }
        }
        
        return name;
    }
    
    public static String inputEss(){
        Scanner scan = new Scanner(System.in);
        
        // essentials type
        System.out.println("\nEssentials Type\n"
                + "1. Oil\n"
                + "2. Pepper\n"
                + "3. Salt\n"
                + "4. Sugar");
        System.out.print("Enter essential type: ");
        String name = null;
        int type = 0;
        boolean validType = false;
        while(validType == false){
            name = scan.nextLine();
            
            if(name.isEmpty()){

                System.out.println(ANSI_RED + "Cannot leave blank.\n" + ANSI_RESET);
                System.out.print("Enter again: ");

            }else{
                try {
                    type = Integer.parseInt(name);

                    if (type < 1 || type > 4) {
                        System.out.println(ANSI_RED + "Invalid integer. Please enter between 1 to 4.\n" + ANSI_RESET);

                        System.out.print("Enter again: ");

                    } else {
                        validType = true; 
                    }

                } catch (NumberFormatException e) {

                    System.out.println(ANSI_RED + "Invalid input. Please enter correct integer.\n" + ANSI_RESET);
                    System.out.print("Enter again: ");

                }
            }
        }
        
        if(validType == true){
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
            }
        }
        
        return name;
    }
    
    public static void inputApparel(LinkedList<Item> newItemList){
        Scanner scan = new Scanner(System.in);
        
        // apparel category
        System.out.println("\nApparel Category\n"
                + "1. Jacket\n"
                + "2. Pant\n"
                + "3. Shirt\n"
                + "4. Shoes\n"
                + "5. Socks");
        System.out.print("Enter apparel type: ");
        String appCatName = null;
        int appCat = 0;
        boolean validAppCat = false;
        while(validAppCat == false){
            appCatName = scan.nextLine();
            
            if(appCatName.isEmpty()){

                System.out.println(ANSI_RED + "Cannot leave blank.\n" + ANSI_RESET);
                System.out.print("Enter again: ");

            }else{
                try {
                    appCat = Integer.parseInt(appCatName);

                    if (appCat < 1 || appCat > 5) {
                        System.out.println(ANSI_RED + "Invalid integer. Please enter between 1 to 5.\n" + ANSI_RESET);

                        System.out.print("Enter again: ");

                    } else {
                        validAppCat = true; 
                    }

                } catch (NumberFormatException e) {

                    System.out.println(ANSI_RED + "Invalid input. Please enter correct amount.\n" + ANSI_RESET);
                    System.out.print("Enter again: ");

                }
            }
        }
        
        String id = null;
        if(validAppCat == true){
            switch(appCat){
                case 1: 
                    System.out.println("\nJacket");
                    id = idGenerator("FA", BAKED_PATH);
                    commonAppInput(id, newItemList, appCat);
                    break;
                case 2:
                    System.out.println("\nPant");
                    id = idGenerator("FO", BOXED_PATH);
                    commonAppInput(id, newItemList, appCat);
                    break;
                case 3:
                    System.out.println("\nShirt");
                    id = idGenerator("FC", CANNED_PATH);
                    commonAppInput(id, newItemList, appCat);
                    break;
                case 4:
                    System.out.println("\nShoes");
                    id = idGenerator("FD", DRY_PATH);
                    commonAppInput(id, newItemList, appCat);
                    break;
                case 5:
                    System.out.println("\nSocks");
                    id = idGenerator("FE", ESS_PATH);
                    commonAppInput(id, newItemList, appCat);
                    break;
                default:
                    System.out.println(ANSI_RED + "Invalid food category.\n" + ANSI_RESET);
            }
        }
    }
    
    public static void commonAppInput(String id, LinkedList<Item> newItemList, int appCat){

        Scanner scan = new Scanner(System.in);
        
        System.out.println("Item with '*' is compulsary to be enter.");
        
        // quantity
        System.out.print("Quantity*: ");
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
        
        //note
        System.out.print("Remarks: ");
        String note = scan.nextLine();

        
        
        switch(appCat){
            case 1: 
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            default:
                System.out.println(ANSI_RED + "Invalid food category.\n" + ANSI_RESET);
        }
    }
    
    public static void saveItemToFile(LinkedList<Item> newItemList){
        // sort
        // place in one by one
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