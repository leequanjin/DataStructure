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
import DonationList.Jacket;
import DonationList.Pant;
import DonationList.Shirt;
import DonationList.Shoes;
import DonationList.Socks;

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
    private static final String JACKET_PATH = "C:\\Users\\Asus\\Desktop\\DS_Assign\\DataStructure\\DonationListFile\\jacket.txt";
    private static final String PANT_PATH = "C:\\Users\\Asus\\Desktop\\DS_Assign\\DataStructure\\DonationListFile\\pant.txt";
    private static final String SHIRT_PATH = "C:\\Users\\Asus\\Desktop\\DS_Assign\\DataStructure\\DonationListFile\\shirt.txt";
    private static final String SHOES_PATH = "C:\\Users\\Asus\\Desktop\\DS_Assign\\DataStructure\\DonationListFile\\shoes.txt";
    private static final String SOCKS_PATH = "C:\\Users\\Asus\\Desktop\\DS_Assign\\DataStructure\\DonationListFile\\socks.txt";
    
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

            System.out.println("Type of donor");
            String[] typeOfDonorList = {"Individual", "Organisation"};
            int dType = menuIntReturn(typeOfDonorList);

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
                    + "Item Category");
            String[] itemMenu = {"Bank", "Cash", "Food", "Apparel"};
            int itemCat = menuIntReturn(itemMenu);
            
            switch(itemCat){
                case 1:
                    inputMoney(newItemList, itemCat);
                    break;
                case 2:
                    inputMoney(newItemList, itemCat);
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
            
        }
    }
    
    public static void inputMoney(LinkedList<Item> newItemList, int itemCat){
        Scanner scan = new Scanner(System.in);
        
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
        
        if (itemCat == 1){
        
            // id
            String id = idGenerator("MB", BANK_PATH);
        
            // bank name
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
            }

            Bank tempBank = new Bank(id, amt, bankName);
            
            LinkedList<Bank> list = new LinkedList();
            list.insert(tempBank);
            list.saveToFile(BANK_PATH);
            
            newItemList.insert(tempBank);
        
        }else{
            // id
            String id = idGenerator("MC", CASH_PATH);
            
            Cash tempCash = new Cash(id, amt);

            LinkedList<Cash> list = new LinkedList();
            list.insert(tempCash);
            list.saveToFile(CASH_PATH);
            
            newItemList.insert(tempCash);
        }
        
    }
    
    public static void inputFood(LinkedList<Item> newItemList){
        
        // food category
        System.out.println("\nFood Category");
        String[] foodCatMenu = {
            "Baked Goods", 
            "Boxed Goods", 
            "Canned Food", 
            "Dry Goods", 
            "Essentials"};
        int foodCat = menuIntReturn(foodCatMenu);
        
        String id = null;
        switch(foodCat){
            case 1: 
                System.out.println("\nBaked Goods");
                id = idGenerator("FA", BAKED_PATH);
                break;
            case 2:
                System.out.println("\nBoxed Goods");
                id = idGenerator("FO", BOXED_PATH);
                break;
            case 3:
                System.out.println("\nCanned Food");
                id = idGenerator("FC", CANNED_PATH);
                break;
            case 4:
                System.out.println("\nDry Goods");
                id = idGenerator("FD", DRY_PATH);
                break;
            case 5:
                System.out.println("\nEssentials");
                id = idGenerator("FE", ESS_PATH);
                break;
            default:
                System.out.println(ANSI_RED + "Invalid food category.\n" + ANSI_RESET);
                break;
        }
        
        commonItemInput(id, newItemList, foodCat, 1);
        
    }
    
    public static void inputApparel(LinkedList<Item> newItemList){
        
        // food category
        System.out.println("\nApparel Category");
        String[] appCatMenu = {
            "Jacket", 
            "Pant", 
            "Shirt", 
            "Shoes", 
            "Socks"};
        int appCat = menuIntReturn(appCatMenu);
        
        String id = null;
        switch(appCat){
            case 1: 
                System.out.println("\nJacket");
                id = idGenerator("AJ", JACKET_PATH);
                break;
            case 2:
                System.out.println("\nPant");
                id = idGenerator("AP", PANT_PATH);
                break;
            case 3:
                System.out.println("\nShirt");
                id = idGenerator("AI", SHIRT_PATH);
                break;
            case 4:
                System.out.println("\nShoes");
                id = idGenerator("AO", SHOES_PATH);
                break;
            case 5:
                System.out.println("\nSocks");
                id = idGenerator("AS", SOCKS_PATH);
                break;
            default:
                System.out.println(ANSI_RED + "Invalid food category.\n" + ANSI_RESET);
                break;
        }
        
        commonItemInput(id, newItemList, appCat, 2);
        
    }
    
    public static void commonItemInput(String id, LinkedList<Item> newItemList, int detailCat, int itemCat){
        
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
        
        switch(itemCat){
            case 1:
                // food
                commonFoodInput(id, newItemList, detailCat, qty, note);
                break;
            case 2:
                // apparel
                commonApparelInput(id, newItemList, detailCat, qty, note);
                break;
        }
        
    }
    
    public static void commonFoodInput(String id, LinkedList<Item> newItemList, int foodCat, int qty, String note){
        Scanner scan = new Scanner(System.in);
        
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
                        String[] menu = {"Enter again", "Discard"};
                        int selection = menuIntReturn(menu);
                        
                        if(selection == 1){
                            System.out.print("Enter date again: ");
                        }else{
                            inputFood(newItemList);
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
        System.out.println("\nFood Status");
        String[] foodStatusMenu = {"Good", "Spoil", "Expired"};
        int foodSta = menuIntReturn(foodStatusMenu);
        
        String foodStaName = null;
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
        
        switch(foodCat){
            case 1: 
                String bakedName = inputBaked();
                BakedGoods baG = new BakedGoods(id, qty, note, expiryDate, w, foodStaName, bakedName);
                
                LinkedList<BakedGoods> list = new LinkedList();
                list.insert(baG);
                list.saveToFile(BAKED_PATH);
            
                newItemList.insert(baG);
                break;
            case 2:
                String boxedName = inputBoxed();
                BoxedGoods boG = new BoxedGoods(id, qty, note, expiryDate, w, foodStaName, boxedName);
                
                LinkedList<BoxedGoods> list1 = new LinkedList();
                list1.insert(boG);
                list1.saveToFile(BOXED_PATH);
            
                newItemList.insert(boG);
                break;
            case 3:
                String cannedName = inputCanned();
                CannedFood cF = new CannedFood(id, qty, note, expiryDate, w, foodStaName, cannedName);
                
                LinkedList<CannedFood> list2 = new LinkedList();
                list2.insert(cF);
                list2.saveToFile(CANNED_PATH);
            
                newItemList.insert(cF);
                break;
            case 4:
                String dryName = inputDry();
                DryGoods dG = new DryGoods(id, qty, note, expiryDate, w, foodStaName, dryName);
                
                LinkedList<DryGoods> list3 = new LinkedList();
                list3.insert(dG);
                list3.saveToFile(DRY_PATH);
            
                newItemList.insert(dG);
                break;
            case 5:
                String essName = inputEss();
                Essentials eG = new Essentials(id, qty, note, expiryDate, w, foodStaName, essName);
                
                LinkedList<Essentials> list4 = new LinkedList();
                list4.insert(eG);
                list4.saveToFile(ESS_PATH);
            
                newItemList.insert(eG);
                break;
            default:
                System.out.println(ANSI_RED + "Invalid food category.\n" + ANSI_RESET);
        }
        
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
        }
        
        return name;
    }
    
    public static void commonApparelInput(String id, LinkedList<Item> newItemList, int appCat, int qty, String note){
        Scanner scan = new Scanner(System.in);
        
        // color
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
        }
        
        // condition
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
        }
        
        // brand
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
        }
        
        switch(appCat){
            case 1: 
                Jacket j = new Jacket(id, qty, note, color, condition, brand);
                
                LinkedList<Jacket> list1 = new LinkedList();
                list1.insert(j);
                list1.saveToFile(JACKET_PATH);
            
                newItemList.insert(j);
                break;
            case 2: 
                Pant p = new Pant(id, qty, note, color, condition, brand);
                
                LinkedList<Pant> list2 = new LinkedList();
                list2.insert(p);
                list2.saveToFile(PANT_PATH);
            
                newItemList.insert(p);
                break;
            case 3: 
                Shirt shirt = new Shirt(id, qty, note, color, condition, brand);
                
                LinkedList<Shirt> list3 = new LinkedList();
                list3.insert(shirt);
                list3.saveToFile(SHIRT_PATH);
            
                newItemList.insert(shirt);
                break;
            case 4: 
                String detailType = inputShoes();
                Shoes shoes = new Shoes(id, qty, note, color, condition, brand, detailType);
                
                LinkedList<Shoes> list4 = new LinkedList();
                list4.insert(shoes);
                list4.saveToFile(SHOES_PATH);
            
                newItemList.insert(shoes);
                break;
            case 5: 
                Socks socks = new Socks(id, qty, note, color, condition, brand);
                
                LinkedList<Socks> list5 = new LinkedList();
                list5.insert(socks);
                list5.saveToFile(SOCKS_PATH);
            
                newItemList.insert(socks);
                break;
            default:
                System.out.println(ANSI_RED + "Invalid apparel category.\n" + ANSI_RESET);
        }
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