/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DonationManagementSubsystem;

import CommonResources.Node;
import CommonResources.LinkedList;

import DonorSubsystem.Donor;
import DonorSubsystem.ManageDonors;
import DonorSubsystem.DonorTest;
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
import DonationList.ManageItem;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Heng Pei Lin
 */
public class DonationManagement {
    
    private static final String DONOR_PATH = "donor.txt";
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
        
        return (ab + String.format("%05d", list.length() + 1));
    }
    
    // -------------------------
    // Part 1: Add new donation
    // -------------------------
    public static void addDonation(){
        Scanner scan = new Scanner(System.in);
        
        boolean contAddDonation = true;
        while(contAddDonation){
            
            System.out.println(ANSI_BLUE + " - - - Add Donation - - - " + ANSI_RESET);

            System.out.print("Enter donor's id: ");
            String dID = null;
            boolean validID = false;
            while(validID == false){
                dID = scan.nextLine();
                
                if(dID.isEmpty()){
                    
                    System.out.println(ANSI_RED + "Cannot leave blank.\n" + ANSI_RESET);
                    System.out.print("Enter again: ");
                    
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
                            donorList.show();
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
        tempDonorList.show();
        
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
            }
        }
            
        //show all item to be added
        System.out.println("Item to be add:");
        newItemList.show();
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
                
                id = idGenerator("FA", list);
                String bakedName = inputBaked();
                BakedGoods baG = new BakedGoods(id, dID, qty, note, expiryDate, w, foodStaName, bakedName);
                
                list.insert(baG);
                list.saveToFile(BAKED_PATH);
            
                newItemList.insert(baG);
                break;
            case 2:
                
                list.loadFromFile(BOXED_PATH);
                
                id = idGenerator("FO", list);
                String boxedName = inputBoxed();
                BoxedGoods boG = new BoxedGoods(id, dID, qty, note, expiryDate, w, foodStaName, boxedName);
                
                list.insert(boG);
                list.saveToFile(BOXED_PATH);
            
                newItemList.insert(boG);
                break;
            case 3:
                
                list.loadFromFile(CANNED_PATH);
                
                id = idGenerator("FC", list);
                String cannedName = inputCanned();
                CannedFood cF = new CannedFood(id, dID, qty, note, expiryDate, w, foodStaName, cannedName);
                
                list.insert(cF);
                list.saveToFile(CANNED_PATH);
            
                newItemList.insert(cF);
                break;
            case 4:
                
                list.loadFromFile(DRY_PATH);
                
                id = idGenerator("FD", list);
                String dryName = inputDry();
                DryGoods dG = new DryGoods(id, dID, qty, note, expiryDate, w, foodStaName, dryName);
                
                list.insert(dG);
                list.saveToFile(DRY_PATH);
            
                newItemList.insert(dG);
                break;
            case 5:
                
                list.loadFromFile(ESS_PATH);
                
                id = idGenerator("FE", list);
                String essName = inputEss();
                Essentials eG = new Essentials(id, dID, qty, note, expiryDate, w, foodStaName, essName);

                list.insert(eG);
                list.saveToFile(ESS_PATH);
            
                newItemList.insert(eG);
                break;
            default:
                System.out.println(ANSI_RED + "Invalid food category.\n" + ANSI_RESET);
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
                
                id = idGenerator("AJ", list);
                Jacket j = new Jacket(id, dID, qty, note, size, color, condition, brand);
                
                list.insert(j);
                list.saveToFile(JACKET_PATH);
            
                newItemList.insert(j);
                break;
            case 2: 
                
                list.loadFromFile(PANT_PATH);
                
                id = idGenerator("AP", list);
                Pant p = new Pant(id, dID, qty, note, size, color, condition, brand);
                
                list.insert(p);
                list.saveToFile(PANT_PATH);
            
                newItemList.insert(p);
                break;
            case 3: 
                
                list.loadFromFile(SHIRT_PATH);
                
                id = idGenerator("AI", list);
                Shirt shirt = new Shirt(id, dID, qty, note, size, color, condition, brand);
                
                list.insert(shirt);
                list.saveToFile(SHIRT_PATH);
            
                newItemList.insert(shirt);
                break;
            case 4: 
                
                list.loadFromFile(SHOES_PATH);
                
                id = idGenerator("AO", list);
                String detailType = inputShoes();
                Shoes shoes = new Shoes(id, dID, qty, note, size, color, condition, brand, detailType);
                
                list.insert(shoes);
                list.saveToFile(SHOES_PATH);
            
                newItemList.insert(shoes);
                break;
            case 5: 
                
                list.loadFromFile(SOCKS_PATH);
                
                id = idGenerator("AS", list);
                Socks socks = new Socks(id, dID, qty, note, size, color, condition, brand);
                
                list.insert(socks);
                list.saveToFile(SOCKS_PATH);
            
                newItemList.insert(socks);
                break;
            default:
                System.out.println(ANSI_RED + "Invalid apparel category.\n" + ANSI_RESET);
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
        System.out.println("\nItem to remove:");
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
            
            sList.show();
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
                            ManageItem<Item> list = new ManageItem();

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
    public static boolean removeByID(ManageItem<Item> list, String id, String filePath){
        Item item = list.findById(id);
        if (item != null){
            // item found
            // delete from list
            list.deleteById(id);

            list.saveToFile(filePath);
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
        System.out.print("\nWhich item do you wish to search?\n" + "Enter ID: ");
        boolean validID = false;

        while (!validID) {
            String inputID = scan.nextLine();

            validID = searchingIdValidation(inputID);
        }
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

                String prefix = inputID.substring(0, 2);
                Set<String> validPrefixes = new HashSet<>(
                        Arrays.asList("MB", "MC", "FA", "FO", "FC", "FD", "FE", "AJ", "AP", "AI", "AO", "AS"));

                if (!validPrefixes.contains(prefix)) {
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
                    }

                    // valid format, check if this id exists, show it if yes
                    if (filePath != null) {
                        ManageItem<Item> list = new ManageItem<>();
                        list.loadFromFile(filePath);
                        Item item = list.findById(inputID);
                        if (item != null) {
                            // show that particular item
                            System.out.println("\nItem Details: ");
                            System.out.println(item.toString());
                            validID = true; // Assuming you want to exit the loop after a successful search
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
    
    // ------------------------------
    // Part 4: Amend donation details
    // ------------------------------
    public static void amendDonation(){
        Scanner scan = new Scanner(System.in);
        
        System.out.println("What kind of item you would like to amend?");
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
        String[] cloth = new String[]{"Quantity", "Remarks", "Size", "Color", "Condition", "Brand"};
        String[] food = new String[]{"Quantity", "Remarks", "Expiry date", "Weight", "Status", "Food Details"};
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
                amendList = new String[]{"Quantity", "Remarks", "Color", "Condition", "Brand", "Shoes Details"};
                break;
            case "AS":
                filePath = SOCKS_PATH;
                amendList = cloth;
                break;
            default:
                System.out.println(ANSI_RED + "Invalid ID." + ANSI_RESET);
        }
        ManageItem<Item> list = new ManageItem<>();
        list.loadFromFile(filePath);
        Item item = list.findById(id);
        if (item != null) {
        
            System.out.println("\nOption available");
            int amendOption = menuIntReturn(amendList);
            
            if (item instanceof Money){
                
                moneyAmend(amendOption, item);
                
            }else if(item instanceof PhysicalItem){
                
                physicalItemAmend(amendOption, item, filePath);
                
            }
            
        }
        
        list.saveToFile(filePath);
        
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
        
        if (amendOption == 1){
            int qty = qtyValidation();
            ((PhysicalItem) item).setQty(qty);
        }else if(amendOption == 2){
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
        
        if (amendOption == 3){
            
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
                                
                                ManageItem<Item> list = new ManageItem<>();
                                list.loadFromFile(filePath);
                                if (list.isEmpty()){
                                    
                                    System.out.println(ANSI_RED + "The list is empty, no item exist in file." + ANSI_RESET);
                                    
                                }else{
                                    boolean delSuccessfully = removeByID(list, item.getId(), filePath);
                                    if(delSuccessfully == true){
                                        System.out.println("Item had been deleted successfully!");
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
            
        } else if(amendOption == 4){
            
            //weight
            int w = weightValidation();
            ((Food) item).setWeight(w);
        
        } else if(amendOption == 5){
            
            //status
            String status = foodStaValidation();
            ((Food) item).setStatus(status);
        
        }else if(amendOption == 6){
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
                ((Food) item).setdetail(detail);
            }
            
        }else{
            System.out.println(ANSI_RED + "Invalid amend option." + ANSI_RESET);
        }
    }
    
    public static void apparelAmend(int amendOption, Item item){
        
        if(amendOption == 3){
            //size
            String size;
            if(item instanceof Shoes){
                size = shoesSizeValidation();
            }else{
                size = sizeValidation();
            }
            
            ((Apparel) item).setSize(size);
        }
        
        if (amendOption == 4){
            //color
            String color = colorValidation();
            ((Apparel) item).setColor(color);
        } else if (amendOption == 5){
            //condition
            String condition = conditionValidation();
            ((Apparel) item).setCondition(condition);
            
        } else if (amendOption == 6 && item instanceof Shoes){
            String detail = inputShoes();
            ((Shoes) item).setDetail(detail);
        } else{
            System.out.println(ANSI_RED + "Invalid amend option." + ANSI_RESET);
        }
    }
    
    // -----------------------------------------
    // Part 5: Track donated items in categories
    // -----------------------------------------
    public static void trackItemByCategory(){
        
        System.out.println("--- MONEY ---");
        System.out.println("Bank");
        printTable(BANK_PATH);
        
        System.out.println("Cash");
        printTable(CASH_PATH);
        
        System.out.println("--- FOOD ---");
        System.out.println("Boxed Goods");
        printTable(BOXED_PATH);
        
        System.out.println("Baked Goods");
        printTable(BAKED_PATH);
        
        System.out.println("Canned Foods");
        printTable(CANNED_PATH);
        
        System.out.println("Dry Goods");
        printTable(DRY_PATH);
        
        System.out.println("Essentials");
        printTable(ESS_PATH);
        
        System.out.println("--- APPAREL ---");
        System.out.println("Jackets");
        printTable(JACKET_PATH);
        
        System.out.println("PANT");
        printTable(PANT_PATH);
        
        System.out.println("Shirt");
        printTable(SHIRT_PATH);
        
        System.out.println("Shoes");
        printTable(SHOES_PATH);
        
        System.out.println("Socks");
        printTable(SOCKS_PATH);
        
    }
    
    public static void printTable(String filePath){
        ManageItem<Item> list = new ManageItem<>();
        list.loadFromFile(filePath);
        if(list.isEmpty()){
            System.out.println("No item available.\n");
        }else{
            System.out.println(list.toString());
        }
    }
    
    // ----------------------------------------
    // Part 6: List donation by different donor
    // ----------------------------------------
    public static void listByDiffDonor(){
        ManageItem<Item> itemlist = loadAllItemIntoList(); // Assuming this method exists and works
        ManageDonors<Donor> donorList = new ManageDonors<>();
        donorList.loadFromFile("donors.txt");

        LinkedList<Donor> individualList = donorList.filterByCategory(Individual.class);
        LinkedList<Donor> organizationList = donorList.filterByCategory(Organization.class);

        System.out.println("--- INDIVIDUAL ---");
        Node<Donor> currentNode = individualList.head;
        while (currentNode != null) {
            System.out.println(currentNode.data.getId());
            currentNode = currentNode.next; 
        }

        System.out.println("--- ORGANIZATION ---");
        currentNode = organizationList.head;
        while (currentNode != null) {
            System.out.println(currentNode.data.getId());
            currentNode = currentNode.next;
        }
    }
    
    public static ManageItem<Item> loadAllItemIntoList(){
        ManageItem<Item> list = new ManageItem<>();
        String[] appendList = {BANK_PATH, CASH_PATH, JACKET_PATH, PANT_PATH, SHIRT_PATH, SHOES_PATH, SOCKS_PATH, BAKED_PATH, BOXED_PATH, CANNED_PATH, DRY_PATH, ESS_PATH};

        list.loadFromFile(appendList[0]);

        for (int i = 1; i < appendList.length; i++) {
            LinkedList<Item> currentList = new LinkedList<>();
            currentList.loadFromFile(appendList[i]);
            list.appendList(currentList);
        }
        return list;
    }
    
    // --------------------------
    // Part 7: List all donations
    // --------------------------
    public static void listAll(){
        ManageItem<Item> list = loadAllItemIntoList();
        
        System.out.println("--- Item List ---");
        if (list.isEmpty()){
            System.out.println("No item in the stock.");
        }else{
            System.out.println(list.toString());
        }

    }
    
    // -----------------------------------------
    // Part 8: Filter donation based on criteria
    // -----------------------------------------
    public static void filterDonation(){
        // money filter descending
        // money filter ascending
        // food expiry date
        // food condition
        // food type
        // cloth condition
        // cloth type
    }
    
    // --------------------------------
    // Part 9: Generate summary reports 
    // --------------------------------
    public static void report(){}
    
}