/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DonationManagementSubsystem;

import adt.Node;
import adt.LinkedList;
import adt.LinkedListInterface;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Heng Pei Lin
 */
public class DMControl {
    
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
    
    public static Scanner scan = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        chkAllFileExist();
        donationManagementMainMenu();
        
    }
    
    public static void donationManagementMainMenu(){
        
        boolean contDM = true;
        
        while(contDM == true){
            int dmChoice = DMUI.donationManagementMainMenu();
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
                DMUI.breakLine();
            }
        }
    }
    
    // ----------------
    // Common Use Part  
    // ----------------
    public static void chkAllFileExist(){
        String[] fileList = {DONOR_PATH, 
            BANK_PATH, CASH_PATH, BAKED_PATH, 
            BOXED_PATH, CANNED_PATH, DRY_PATH, 
            ESS_PATH, JACKET_PATH, PANT_PATH, 
            SHIRT_PATH, SHOES_PATH, SOCKS_PATH};
        chkFileExist(fileList);
    }
    
    // Create file if file not exist
    public static void chkFileExist(String[] fileList){
    
        for (int i = 0; i < fileList.length; i++) {
            boolean fileExist = DAO.chkFileExist(fileList[i]);
            if (!fileExist) {
                DMUtility.fileNoExist(fileList[i]);
                boolean createFile = DAO.createFile(fileList[i]);
                if (createFile) {
                    DMUtility.createFileSuccesfully(fileList[i]);
                } else {
                    DMUtility.createFileFail(fileList[i]);
                }
            } else{
                DMUtility.fileExist(fileList[i]);
            }
        }
        
    }
    
    public static LinkedListInterface<Item> loadAllItemIntoList(){
        String[] appendList = {BANK_PATH, CASH_PATH, JACKET_PATH, PANT_PATH, SHIRT_PATH, SHOES_PATH, SOCKS_PATH, BAKED_PATH, BOXED_PATH, CANNED_PATH, DRY_PATH, ESS_PATH};

        LinkedListInterface<Item> list = new LinkedList<>();
        list.loadFromFile(appendList[0]);

        for (int i = 1; i < appendList.length; i++) {
            LinkedList<Item> currentList = new LinkedList<>();
            currentList.loadFromFile(appendList[i]);
            list.appendList(currentList);
        }
        
        return list;
    }
    
    public static LinkedListInterface<Food> loadAllFoodIntoList(){
        String[] foodFile = {BAKED_PATH, BOXED_PATH, CANNED_PATH, DRY_PATH, ESS_PATH}; 
        
        LinkedListInterface<Food> list = new LinkedList<>();
        list.loadFromFile(foodFile[0]);
        for(int i = 1; i < foodFile.length; i++){
            LinkedList tempFoodList = new LinkedList<>();
            tempFoodList.loadFromFile(foodFile[i]);
            list.appendList(tempFoodList);
        }
        
        return list;
    }

    // Validation
    public static boolean chkEmptyInput(String input){
        if(input.isEmpty()){
            return false;
        }else{
            return true;
        }
    }
    
    public static boolean chkInt(String input){
        try {
            int number = Integer.parseInt(input);

            return true;
            
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static boolean chkLength(int length, String input){
        if(input.length() == length){
            return true;
        }
        return false;
    }
    
    public static boolean intSelectionValidation(int input,int initial, int length){

        if (input < initial || input > length) {
            return false;
        } else {
            return true; 
        }
    }
    
    public static int menuIntReturn(String[] selectionList) {
        
        DMUI.displayMenu(selectionList);
        
        int intInput = 0;
        boolean validInput = false;

        while (validInput == false) {
            String stringInput = scan.nextLine();

            validInput = chkIntInputInRange(stringInput, 1, selectionList.length);

            if (validInput) {
                intInput = Integer.parseInt(stringInput);
            }

        }

        return intInput;
    }

    public static boolean chkIntInputInRange(String input, int initial, int end) {
        
        boolean validInput = chkIntInput(input);

        if(validInput){
        
            // chk is within the integer range
            int intInput = Integer.parseInt(input);
            validInput = intSelectionValidation(intInput, initial, end);
            if (!validInput) {
                DMUtility.intNotInRange(initial, end);
                DMUI.reEnter();
            }
            
        }

        return validInput;
    }
    
    public static boolean chkIntInputPos(String input){
        
        boolean validInput = chkIntInput(input);

        // chk is within the integer range
        int intInput = Integer.parseInt(input);
        validInput = intInput > 0;
        if (!validInput) {
            DMUtility.intCannotNeg();
            DMUI.reEnter();
        }

        return validInput;
    }
    
    public static boolean chkIntInput(String input){
        boolean validInput;

        // check is empty
        validInput = chkEmptyInput(input);
        if (validInput) {

            // chk is it an integer
            validInput = chkInt(input);
            if (!validInput) {
                DMUtility.invalidIntInput();
            }

        } else {
            DMUtility.emptyInputErrorMsg();
        }

        if (!validInput) {
            DMUI.reEnter();
        }

        return validInput;
    }
    
    // if typr other than specific
    public static boolean chkSpecificWord(String[] inputList, String input){
        for(int i = 0; i < inputList.length; i++){
            if(input.trim().toUpperCase().equalsIgnoreCase(inputList[i].trim().toUpperCase())){
                return true;
            }
        }
        return false;
    }
    
    // only YN
    public static boolean chkYN(String input){
        input = input.toUpperCase().trim();
        if (input.equals("Y")) {
            return true;
        } else {
            return false;
        }
    }
        
    public static boolean YN(String sentence) {

        boolean validInput = false;
        String input = null;

        DMUI.inputYN(sentence);
        while (!validInput) {

            input = (scan.nextLine()).toUpperCase().trim();

            validInput = chkEmptyInput(input);

            if (validInput) {
                String[] inputList = {"Y", "N"};
                validInput = chkSpecificWord(inputList, input);
                if (!validInput) {
                    DMUtility.enterYNOnly();
                }
            } else {
                DMUtility.emptyInputErrorMsg();
            }

            if (!validInput) {
                DMUI.reEnter();
            }

        }

        boolean cont = chkYN(input);
        if (cont) {
            return true;
        } else {
            return false;
        }
    }
        
    public static<T> String idGenerator(String ab, LinkedListInterface<Item> list){
        
        int maxId = 0;

        Node<Item> current = list.getHead(); 

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
    
    public static boolean itemIdFormat(String id, String constrant){
        if ( (id.substring(0, 2).toUpperCase().equalsIgnoreCase(constrant)) && (id.length() == 7) ){
            return true;
        }else{
            return false;
        }
    }
    
    public static Item findByID(String id, LinkedListInterface<Item> list) {
        id = id.substring(0, 2).toUpperCase() + id.substring(2, 7);
        Node<Item> current = list.getHead();

        while (current != null) {
            if (current.data.getId().equals(id)) {
                return current.data;
            }
            current = current.next;
        }
            return null;
    }
    
    public static boolean itemIDValidation(String inputID){
        boolean validID = false;
        
        if (inputID.isEmpty()) {
            DMUtility.emptyInputErrorMsg();
        } else {
            if (inputID.length() != 7) {
                DMUtility.invalidLength();
            } else {
                inputID = inputID.substring(0, 2).toUpperCase() + inputID.substring(2, 7);

                String prefix = inputID.substring(0, 2).toUpperCase();
                if(prefix.equals("MB") || prefix.equals("MC") || prefix.equals("FA") ||
                prefix.equals("FO") || prefix.equals("FC") || prefix.equals("FD") ||
                prefix.equals("FE") || prefix.equals("AJ") || prefix.equals("AP") ||
                prefix.equals("AI") || prefix.equals("AO") || prefix.equals("AS") ){
                    validID = true;
                }else{
                    DMUtility.invalidIDFormat("AA");
                }
            }
        }
                
        if(!validID){
            DMUI.reEnter();
        }
        
        return validID;
    }
    
    public static void printEachTable(LinkedListInterface<Item> list){
        list.removeEmptyData();
        Node<Item> currentNode = list.getHead();
        
        if(list.isEmpty()){
            DMUtility.noItemInList();
            return;
        }
        
        int count = 1;
        while(currentNode != null){
            DMUI.printedItemCount(count);
            DMUI.commonItemHeader();
            if(currentNode.data instanceof Money){
                    DMUI.commonMoneyHeader();
                if (currentNode.data instanceof Bank){
                    DMUI.bankHeader();
                }
            }else {

                DMUI.commonPhyItemHeader();
                if (currentNode.data instanceof Food){
                    DMUI.commonFoodHeader();
                }else{ // Apparel
                    DMUI.commonAppHeader();
                    if (currentNode.data instanceof Shoes){
                        DMUI.commonShoeHeader();
                    }
                }
            }
            
            DMUI.breakLine();
            DMUI.printNode(currentNode);
            
            count++;
            currentNode = currentNode.next;
            DMUI.breakLine();
        }
    }
    
    public static void printTable(String filePath){
        LinkedListInterface<Item> list = new LinkedList<>();
        list.loadFromFile(filePath);
        
        list.removeEmptyData();
        
        if(list.isEmpty()){
            DMUtility.noSuchItem();
            return;
        }
        
        printListToTable(list);
    }
    
    public static void printSameTable(String filePath, boolean available){
        LinkedListInterface<Item> list = new LinkedList<>();
        list.loadFromFile(filePath);
        
        if(available == true){
            list.removeIf(item -> item.getAvailability().equals("Unavailable"));
        }else{
            list.removeIf(item -> item.getAvailability().equals("Available"));
        }
        
        list.removeEmptyData();
        
        if(list.isEmpty()){
            DMUtility.noSuchItem();
            return;
        }
        
        printListToTable(list);
    }
    
    // if want append list
    public static void printMulSameTable(String[] filePath, boolean available){
        LinkedListInterface<Item> list = new LinkedList<>();
        list.loadFromFile(filePath[0]);
        
        for(int i = 1; i < filePath.length; i++){
            LinkedList tempList = new LinkedList();
            tempList.loadFromFile(filePath[i]);
            list.appendList(tempList);
        }
        
        if(available == true){
            list.removeIf(item -> item.getAvailability().equals("Unavailable"));
        }else{
            list.removeIf(item -> item.getAvailability().equals("Available"));
        }
        
        list.removeEmptyData();
        
        if(list.isEmpty()){
            DMUtility.noSuchItem();
            return;
        }
        
        printListToTable(list);
    }
    
    public static void printListToTable(LinkedListInterface<Item> list){
        Node<Item> currentNode = list.getHead();
        
        int stop = 1;
        int pageNum = 1;
        
        DMUI.startOfPage(pageNum);
        headerIdentifier(currentNode);
        DMUI.breakLine();
        while(currentNode != null){
            DMUI.printNode(currentNode);
            
            if(stop % 50 == 0 && currentNode.next != null){
                DMUI.endOfPage(pageNum);
                boolean cont = YN(DMUI.showMorePgQ());
                if (cont){
                    pageNum++;
                    DMUI.startOfPage(pageNum);
                    headerIdentifier(currentNode);
                    DMUI.breakLine();
                    stop = 0;
                }else{
                    return;
                }
            }
            
            stop++;
            currentNode = currentNode.next;
        }
    }
    
    public static void headerIdentifier(Node<Item> currentNode){
        DMUI.commonItemHeader();
        if(currentNode.data instanceof Money){
            DMUI.commonMoneyHeader();
            if (currentNode.data instanceof Bank){
                DMUI.bankHeader();
            }
        }else {
        
            DMUI.commonPhyItemHeader();
            if (currentNode.data instanceof Food){
                DMUI.commonFoodHeader();
            }else{ // Apparel
                DMUI.commonAppHeader();
                if (currentNode.data instanceof Shoes){
                    DMUI.commonShoeHeader();
                }
            }
        }
    }
    
    public static void printSpecificItem(Item item){
        
        if (item == null){
            DMUtility.noSuchItem();
            return;
        }
        
        DMUI.commonItemHeader();
        if(item instanceof Money){
            DMUI.commonMoneyHeader();
            if (item instanceof Bank){
                DMUI.bankHeader();
            }
        }else {
        
            DMUI.commonPhyItemHeader();
            if (item instanceof Food){
                DMUI.commonFoodHeader();
            }else{ // Apparel
                DMUI.commonAppHeader();
                if (item instanceof Shoes){
                    DMUI.commonShoeHeader();
                }
            }
        }

        DMUI.printItem(item);
    }
    
    // -------------------------
    // Part 1: Add new donation
    // -------------------------
    public static void addDonation(){
        LinkedListInterface<Donor> donorList = new LinkedList<>();
        donorList.loadFromFile(DONOR_PATH);
        boolean contAddDonation = true;
        while(contAddDonation){
            
            if(donorList.isEmpty()){
                DMUtility.noDonorInList();
                DMUtility.addFunctionDown();
                return;
            }
                
            DMUI.addDonation(donorList);
            
            DMUI.inputDonorID();
            String dID;
            boolean contInputID = false;
            do{
                dID = donorIdValidation();
                
                Donor donor = chkDonorExist(dID, donorList);
                if (donor == null){// if does not exist, enter other
                    DMUtility.donorNoExist();
                    int selectionToCont = DMUI.donorNoExistSelection();
                    if (selectionToCont == 1){
                        contInputID = true;
                    }else{
                        donationManagementMainMenu();
                    }
                }else{
                    DMUI.disTempDonorData(donor);
                }
                
            }while(contInputID);
            
            
            boolean contAddItem = true;
            while(contAddItem){

                addItem(dID);

                contAddItem = YN(DMUI.contAddItemForSameDonor());
                if (contAddItem == false){
                    break;
                }
            }

            contAddDonation = YN(DMUI.contAddItemForDissDonor());
            if(contAddDonation == true){
                DMUI.breakLine();
            } 
        }
        
    }
    
    public static boolean donorIdFormat(String id){
        if ( (id.substring(0, 3).toUpperCase().equalsIgnoreCase("DNR")) && (id.length() == 8) ){
            return true;
        }else{
            return false;
        }
    } 
    
    public static String donorIdValidation() {
        boolean validID;
        String id;
        do{
            id = scan.nextLine().trim();
            
            // chk is it empty
            validID = chkEmptyInput(id);
            if (validID) {
                // chk correct length
                validID = chkLength(8, id);
                if (validID) {

                    // chk if correct format
                    validID = donorIdFormat(id);
                    if (!validID) {
                        DMUtility.invalidIDFormat("DNR");
                    }
                } else {
                    DMUtility.invalidLength();
                }
            } else {
                DMUtility.emptyInputErrorMsg();
            }

            if (!validID) {
                DMUI.reEnter();
            }
            
        }while(!validID);

        return id.substring(0, 3).toUpperCase() + id.substring(3, 8);
    }
    
    public static Donor chkDonorExist(String dID, LinkedListInterface<Donor> donorList){
        
        if (!donorList.isEmpty()){
            
            Node<Donor> current = donorList.getHead();
        
            while (current != null) {
                Donor donor = current.data;
                
                if (donor.getId().equals(dID)) {
                    return donor;
                }

                current = current.next;
            }
            
        }else{
            DMUtility.noDonorInList();
        }
        
        return null;
    }
    
    public static void addItem(String dID){
        
        DMUI.numOfItemToAdd();
        int numItem;
        String numSItem = null;
        boolean validNumItem = false;
        while(validNumItem == false){
            numSItem = scan.nextLine();
            
            validNumItem = chkIntInputPos(numSItem);
        }
        numItem = Integer.parseInt(numSItem);
        
        LinkedListInterface<Item> newItemList = new LinkedList<>();
        
        for (int i = 0; i< numItem; i++){
            
            int itemCat = DMUI.itemCatMenu(i);
            
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
                    DMUtility.invalidMenuSelection();
                    break;
            }
        }
        
        DMUtility.itemAdded();
        
        //show all item to be added
        DMUI.disAddedItemHeader();
        printEachTable(newItemList);
    }
    
    public static<T> void inputMoney(LinkedListInterface<Item> newItemList, int itemCat, String dID){
        
        // amount
        double amt = amountValidation();
        
        LinkedListInterface<Item> list = new LinkedList<>();
        if (itemCat == 1){
        
            // bank name
            String bankName = bankTypeValidation();

            list.loadFromFile(BANK_PATH);
        
            // id
            String id = idGenerator("MB", list);
            
            Bank tempBank = new Bank(id, dID, amt, bankName);
            list.insert(tempBank);
            list.saveToFile(BANK_PATH);
            
            newItemList.insert(tempBank);
        
        }else{
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
        
        DMUI.inputAmtDonated();
        double amt = 0;
        boolean validAmt = false;
        while(validAmt == false){
            String SAmt = scan.nextLine();
            if(SAmt.isEmpty()){

                DMUtility.emptyInputErrorMsg();

            }else{
                try {
                    amt = Double.parseDouble(SAmt);

                    if (amt <= 0) {
                        
                        DMUtility.invalidAmt();

                    } else {
                        validAmt = true; 
                    }

                } catch (NumberFormatException e) {

                    DMUtility.invalidInputType("amount");

                }
            }
            
            if (!validAmt){
                DMUI.reEnter();
            }
        }
        return amt;
    }
    
    public static String bankTypeValidation(){
        int bankType = DMUI.inputBankMenu();

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
                DMUtility.invalidMenuSelection();
                break;
        }
        
        return bankName;
    }
    
    public static void inputFood(LinkedListInterface<Item> newItemList, String dID){
        
        // food category
        int foodCat = DMUI.inputFoodCat();
        
        switch(foodCat){
            case 1:
                DMUI.inputBAHeader();
                break;
            case 2:
                DMUI.inputBOHeader();
                break;
            case 3:
                DMUI.inputCHeader();
                break;
            case 4:
                DMUI.inputDHeader();
                break;
            case 5:
                DMUI.inputEHeader();
                break;
            default:
                DMUtility.invalidMenuSelection();
                break;
        }
        
        commonItemInput(newItemList, foodCat, 1, dID);
        
    }
    
    public static void inputApparel(LinkedListInterface<Item> newItemList, String dID){
        
        // apparel category
        int appCat = DMUI.inputAppCat();
        
        switch(appCat){
            case 1: 
                DMUI.inputJHeader();
                break;
            case 2:
                DMUI.inputPHeader();
                break;
            case 3:
                DMUI.inputShirtHeader();
                break;
            case 4:
                DMUI.inputShoesHeader();
                break;
            case 5:
                DMUI.inputSockHeader();
                break;
            default:
                DMUtility.invalidMenuSelection();
                break;
        }
        
        commonItemInput(newItemList, appCat, 2, dID);
        
    }
    
    public static void commonItemInput(LinkedListInterface<Item> newItemList, int detailCat, int itemCat, String dID){
        
        DMUI.mustEnterMsg();
        
        // quantity
        int qty = qtyValidation();
        
        //note
        DMUI.inputRemark();
        String note = scan.nextLine().trim();
        if (note.isEmpty()){
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
        
        DMUI.inputQty();
        int qty = 0;
        boolean validQty = false;
        while(validQty == false){
            String qtyS = scan.nextLine();
            
            if(qtyS.isEmpty()){

                DMUtility.emptyInputErrorMsg();

            }else{
                try {
                    qty = Integer.parseInt(qtyS);

                    if (qty == 0) {
                        
                        DMUtility.invalidQtyZ();
                        
                    } else if(qty  < 0){
                        
                        DMUtility.invalidQtyNeg();
                        
                    }else {
                        validQty = true; 
                    }

                } catch (NumberFormatException e) {

                    DMUtility.invalidInputType("quantity");

                }
            }
            
            if(!validQty){
                DMUI.reEnter();
            }
        }
        return qty;
    }
    
    public static void commonFoodInput(LinkedListInterface<Item> newItemList, int foodCat, int qty, String note, String dID){
        
        //expiryDate
        Date expiryDate = expiryDateValidation(newItemList, dID);
        
        //weight
        int w = weightValidation();
        
        //status
        String foodStaName = foodStaValidation();
        
        LinkedListInterface<Item> list = new LinkedList();
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
                DMUtility.invalidMenuSelection();
                break;
        }
        
    }
    
    public static Date expiryDateValidation(LinkedListInterface<Item> newItemList, String dID){
        
        DMUI.inputExpDate();
        Date expiryDate = null;
        boolean validExp = false;
        
        while(validExp == false){
            String exp = scan.nextLine().trim();
            
            if (exp.isEmpty()){
                
                DMUtility.emptyInputErrorMsg();
                
            } else{
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                dateFormat.setLenient(false); 

                try {
                    
                    expiryDate = dateFormat.parse(exp);

                    Date today = new Date();

                    if (expiryDate.before(today)) {
                        int selection = DMUI.inputExpAction();
                        
                        if(selection == 1){
                            expiryDateValidation(newItemList, dID);
                        }else{
                            inputFood(newItemList, dID);
                        }
                        
                    } else {
                        validExp = true;
                    }
                    
                } catch (ParseException e) {
                    DMUtility.invalidDateFormat();
                }
                
            }
            
            if(!validExp){
                DMUI.reEnter();
            }
        }
        
        return expiryDate;
        
    }
    
    public static int weightValidation(){
        
        DMUI.inputWeight();
        
        int w = 0;
        boolean validW = false;
        while(validW == false){
            String wS = scan.nextLine().trim();
            
            if(wS.isEmpty()){

                DMUtility.emptyInputErrorMsg();

            }else{
                try {
                    w = Integer.parseInt(wS);

                    if (w == 0) {
                        
                        DMUtility.invalidWZ();

                    } else if(w  < 0){
                        System.out.print("Enter again: ");
                    }else {
                        validW = true;
                    }

                } catch (NumberFormatException e) {

                    DMUtility.invalidInputType("weight");

                }
            }
            if(!validW){
                DMUI.reEnter();
            }
        }
        
        return w;
    }
    
    public static String foodStaValidation(){
        
        int foodSta = DMUI.inputFoodStatus();
        
        String foodStaName = null;
        switch(foodSta){
            case 1: 
                foodStaName = "New";
                break;
            case 2:
                foodStaName = "Good";
                break;
            default:
                DMUtility.invalidMenuSelection();
                break;
        }
        
        return foodStaName;
    }
    
    public static String inputBaked() {
        
        // baked food type
        int type = DMUI.inputBakedT();
        
        String name = null;
        switch(type){
            case 1: 
                name = "Cookies";
                break;
            case 2:
                name = "Crackers";
                break;
            default:
                DMUtility.invalidMenuSelection();
                break;
        }
        
        return name;
    }
    
    public static String inputBoxed(){
        
        // boxed food type
        int type = DMUI.inputBoxedT();
        
        String name = null;
        switch(type){
            case 1: 
                name = "Cereals";
                break;
            case 2:
                name = "Snacks";
                break;
            default:
                DMUtility.invalidMenuSelection();
                break;
        }
        
        return name;
    }
    
    public static String inputCanned(){
        
        // canned food type
        int type = DMUI.inputCannedT();
        
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
                DMUtility.invalidMenuSelection();
                break;
        }
        
        return name;
    }
    
    public static String inputDry(){
        
        // dry food type
        int type = DMUI.inputDryT();
        
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
                DMUtility.invalidMenuSelection();
                break;
        }
        
        return name;
    }
    
    public static String inputEss(){
        
        // essentials type
        int type = DMUI.inputEssT();
        
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
                DMUtility.invalidMenuSelection();
                break;
        }
        
        return name;
    }
    
    public static void commonApparelInput(LinkedListInterface<Item> newItemList, int appCat, int qty, String note, String dID){
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
        String id;
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
                DMUtility.invalidMenuSelection();
                break;
        }
    }
    
    public static String sizeValidation(){
        int appSize = DMUI.inputAppSize();
        
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
                DMUtility.invalidMenuSelection();
                break;
        }
        
        return size;
    }
    
    public static String shoesSizeValidation(){
        
        DMUI.inputShoeSize();
        String sizeStr = null;
        boolean validSize = false;
        while(validSize == false){
            sizeStr = scan.nextLine().trim();
            
            validSize = chkIntInputInRange(sizeStr, 1, 16);
            
            if(!validSize){
                DMUI.reEnter();
            }
        }
        
        return sizeStr;
    }
    
    public static String colorValidation(){
        
        int appColor = DMUI.inputColor();
        
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
                DMUtility.invalidMenuSelection();
                break;
        }
        
        return color;
    }
    
    public static String conditionValidation(){
        
        int appCon = DMUI.inputAppCon();
        
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
                DMUtility.invalidMenuSelection();
                break;
        }
        
        return condition;
    }
    
    public static String brandValidation(){
        int appBrand = DMUI.inputAppBrand();
        
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
                DMUtility.invalidMenuSelection();
                break;
        }
        
        return brand;
    }
    
    public static String inputShoes(){
        
        int s = DMUI.inputShoesT();
        
        switch(s){
            case 1: 
                return "Slipper";
            case 2:
                return "Sport shoes";
            default:
                DMUtility.invalidMenuSelection();
                return "Unknown";
        }
    }
    
    // -------------------------
    // Part 2: Remove a donation
    // -------------------------
    public static void remDonation(){
        int itemRem = DMUI.inputRemCat();
        
        switch(itemRem){
            case 1: 
                remItem(BANK_PATH, "MB");
                break;
            case 2:
                remItem(CASH_PATH, "MC");
                break;
            case 3:
                int foodCat = DMUI.inputFoodCat();

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
                        DMUtility.invalidMenuSelection();
                        break;
                }
                break;
            case 4:
                int appCat = DMUI.inputAppCat();

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
                        DMUtility.invalidMenuSelection();
                        break;
                }
                break;
            default:
                DMUtility.invalidMenuSelection();
                break;
                
        }
    }
    
    public static void remItem(String filePath, String id){
        LinkedListInterface<Item> list = new LinkedList();
        list.loadFromFile(filePath);
            
        if(list.isEmpty()){
            DMUtility.noSuchItem();
            boolean cont = YN(DMUI.contRemoveQ());
            if (cont == true){
                remDonation();
            }else{
                donationManagementMainMenu();
            }
        }else{
            
            printSameTable(filePath, true);
            DMUI.remItemHeader();
            DMUI.inputItemID();
            String inputID = null;
            boolean validID = false;
            while (!validID) {
                inputID = scan.nextLine();

                validID = itemIDValidation(inputID);
            }
            
            inputID = inputID.substring(0, 2).toUpperCase() + inputID.substring(2, 7);
            // valid format, check if this id exist, remove if yes
            Item item = findByID(inputID, list);
            if (item != null){
                // item found
                // delete from list
                deleteById(inputID, list);

                list.saveToFile(filePath);
                printSameTable(filePath, true);
            }else{
                DMUtility.itemNoExist();
            }
        }
        
    }
        
    public static<T> void deleteById(String id, LinkedListInterface<Item> list) {
        if (list.getHead() == null) {
            
            DMUtility.noSuchItem();
            return;
            
        } else if (list.getHead().data.getId().equals(id)) {
            // First id match
            list.setHead(list.getHead().next);

            DMUtility.itemRemove();
            
        } else {
            Node<Item> currentNode = list.getHead();

            while (currentNode != null) {

                if (currentNode.next.data.getId().equals(id)) {
                    currentNode.next = currentNode.next.next;
                    DMUtility.itemRemove();
                    return;
                } 
                currentNode = currentNode.next;
            }
             
            DMUtility.itemRemoveFail();
        }
    }
    
    // -------------------------------
    // Part 3: Search donation details
    // -------------------------------
    public static void searchDonation() {
        
        DMUI.searchIDHeader();
        boolean contSearch = true;
        while(contSearch){
            
            DMUI.inputItemID();
            
            String inputID = null;
            boolean validID = false;
            while (!validID) {
                inputID = scan.nextLine();

                validID = itemIDValidation(inputID);
            }
            
            searchingID(inputID);
            
            contSearch = YN(DMUI.contSearchQ());
            if (contSearch){
                System.out.println();
            }
        }
    }
    
    public static void searchingID(String inputID){
        
        String prefix = inputID.substring(0, 2).toUpperCase();
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
                DMUtility.invalidID();
                break;
        }

        // valid format, check if this id exists, show it if yes
        if (filePath != null) {
            LinkedListInterface<Item> list = new LinkedList<>();
            list.loadFromFile(filePath);
            Item item = findByID(inputID, list);
            if (item != null) {
                // show that particular item
                DMUI.printDetailHeader();
                printSpecificItem(item);
                
            } else {
                DMUtility.itemNoExist();
            }
        }
        
    }

    // ------------------------------
    // Part 4: Amend donation details
    // ------------------------------
    public static void amendDonation(){
        
        boolean contAmend = true;
        while(contAmend == true){
            DMUI.amendDonationHeader();
            DMUI.inputItemID();

            String id = null;
            boolean validID = false;
            while (!validID) {
                id = scan.nextLine();

                validID = itemIDValidation(id);
            }
            
            id = id.substring(0, 2).toUpperCase() + id.substring(2, 7);
            
            searchingID(id);

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
                    DMUtility.invalidMenuSelection();
                    break;
            }
            
            LinkedListInterface<Item> list = new LinkedList<>();
            list.loadFromFile(filePath);
            Item item = findByID(id, list);
            if (item != null) {

                boolean contItem = true;
                do{
                    DMUI.optionAvailable();
                    int amendOption = menuIntReturn(amendList);

                    if (item instanceof Money){

                        moneyAmend(amendOption, item);

                    }else if(item instanceof PhysicalItem){

                        physicalItemAmend(amendOption, item, filePath);

                    }

                    DMUtility.itemUpdated();
                    printSpecificItem(item);
                    
                    contItem = YN(DMUI.contAmendSameItemQ());
                    
                }while(contItem == true);

            }

            list.saveToFile(filePath);
            
            contAmend = YN(DMUI.contAmendOtherQ());
            
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
            DMUI.inputRemark();
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
        
        if (amendOption == 2){
            
            // expiry date
            DMUI.inputExpDate();
            Date expiryDate = null;
            boolean validExp = false;

            while(validExp == false){
                String exp = scan.nextLine();

                if (exp.isEmpty()){

                    DMUtility.emptyInputErrorMsg();
                    DMUI.reEnter();

                } else{

                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    dateFormat.setLenient(false); 

                    try {

                        expiryDate = dateFormat.parse(exp);

                        Date today = new Date();

                        if (expiryDate.before(today)) {

                            DMUtility.foodExpired();
                            int selection = DMUI.contUpdateDateMenu();

                            if(selection == 1){
                                
                                DMUI.inputDateAgain();
                                
                            }else if(selection == 2){
                                
                                boolean cont = YN(DMUI.contAmendOtherQ());
                                if (cont == true){
                                    amendDonation();
                                }else{
                                    donationManagementMainMenu();
                                }
                                
                            }else{
                                
                                LinkedListInterface<Item> list = new LinkedList<>();
                                list.loadFromFile(filePath);
                                if (list.isEmpty()){
                                    
                                    DMUtility.noSuchItem();
                                    
                                }else{
                                    deleteById(item.getId(), list);
                                    list.saveToFile(filePath);
                                }
                            }

                        } else {
                            ((Food) item).setExpiryDate(expiryDate);
                            validExp = true;
                        }

                    } catch (ParseException e) {
                        DMUtility.invalidDateFormat();
                        DMUI.reEnter();
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
                DMUtility.invalidMenuSelection();
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
        
        DMUI.TIMoneyHeader();
        DMUI.TIBankHeader();
        printTable(BANK_PATH);
        
        DMUI.TICashHeader();
        printTable(CASH_PATH);
        
        DMUI.TIFoodHeader();
        DMUI.TIBakedHeader();
        printTable(BAKED_PATH);
        
        DMUI.TIBoxedHeader();
        printTable(BOXED_PATH);
        
        DMUI.TICannedHeader();
        printTable(CANNED_PATH);
        
        DMUI.TIDryHeader();
        printTable(DRY_PATH);
        
        DMUI.TIEssHeader();
        printTable(ESS_PATH);
        
        DMUI.TIAppHeader();
        DMUI.TIJacketHeader();
        printTable(JACKET_PATH);
        
        DMUI.TIPantHeader();
        printTable(PANT_PATH);
        
        DMUI.TIShirtHeader();
        printTable(SHIRT_PATH);
        
        DMUI.TIShoesHeader();
        printTable(SHOES_PATH);
        
        DMUI.TISocksHeader();
        printTable(SOCKS_PATH);
        
        // total item donated, total money donated
    }
    
    // ----------------------------------------
    // Part 6: List donation by different donor
    // ----------------------------------------
    public static void listByDiffDonor(){
        LinkedListInterface<Item> itemList = loadAllItemIntoList();
        LinkedListInterface<Donor> donorList = new LinkedList<>();
        donorList.loadFromFile(DONOR_PATH);

        LinkedListInterface<Individual> individualList = donorList.filterByCategoryIntoLinkedListInterface(Individual.class);
        LinkedListInterface<Organization> organizationList = donorList.filterByCategoryIntoLinkedListInterface(Organization.class);
        
        // remove all empty space
        itemList.removeEmptyData();
        individualList.removeEmptyData();
        organizationList.removeEmptyData();

        DMUI.individualHeader();
        filterByDonorInd(itemList, individualList);

        DMUI.organizationHeader();
        filterByDonorOrg(itemList, organizationList);
    }
    
    public static void filterByDonorInd(LinkedListInterface<Item> itemList, LinkedListInterface<Individual> idvList){
        itemList.removeEmptyData();
        idvList.removeEmptyData();

        if (idvList.getHead() == null || itemList.getHead() == null) {
            DMUtility.noDonorOrItem();
            return;
        }

        Node<Individual> currentDonor = idvList.getHead();
        String donorID;
        int stop = 0;
        int pageNum = 1;

        DMUI.startOfPage(pageNum);
        DMUI.donorItemCustomHeader();
        while (currentDonor != null) {
            int print = 0;
            donorID = currentDonor.data.getId();

            Node<Item> currentItem = itemList.getHead();
            while (currentItem != null) {
                if (donorID.equals(currentItem.data.getDonorID())) {
                    
                    if(stop == 50){
                        
                        DMUI.endOfPage(pageNum);
                        boolean cont = YN(DMUI.showMorePgQ());
                        if (cont){
                            
                            pageNum++;
                            DMUI.startOfPage(pageNum);
                            DMUI.donorItemCustomHeader();
                            stop = 0;
                            
                        }else{
                            return;
                        }
                        
                    } 
                    
                    stop++;
                    if (print == 0) {
                        DMUI.donorItemCustomFull(donorID, currentItem);
                        print++;
                    } else {
                        DMUI.donorItemCustomNor(currentItem);
                    }
                   
                }

                currentItem = currentItem.next;
            }
            
            currentDonor = currentDonor.next;
        }

        DMUI.breakLine();
    }
    
    public static void filterByDonorOrg(LinkedListInterface<Item> itemList, LinkedListInterface<Organization> orgList){
        itemList.removeEmptyData();
        orgList.removeEmptyData();

        if (orgList.getHead() == null || itemList.getHead() == null) {
            DMUtility.noDonorOrItem();
            return;
        }

        Node<Organization> currentDonor = orgList.getHead();
        String donorID;
        int stop = 0;
        int pageNum = 1;

        DMUI.startOfPage(pageNum);
        DMUI.donorItemCustomHeader();
        while (currentDonor != null) {
            int print = 0;
            donorID = currentDonor.data.getId();

            Node<Item> currentItem = itemList.getHead();
            while (currentItem != null) {
                if (donorID.equals(currentItem.data.getDonorID())) {
                    
                    if(stop == 50){
                        
                        DMUI.endOfPage(pageNum);
                        boolean cont = YN(DMUI.showMorePgQ());
                        if (cont){
                            
                            pageNum++;
                            DMUI.startOfPage(pageNum);
                            DMUI.donorItemCustomHeader();
                            stop = 0;
                            
                        }else{
                            return;
                        }
                        
                    } 
                    
                    stop++;
                    if (print == 0) {
                        DMUI.donorItemCustomFull(donorID, currentItem);
                        print++;
                    } else {
                        DMUI.donorItemCustomNor(currentItem);
                    }
                   
                }

                currentItem = currentItem.next;
            }
            
            currentDonor = currentDonor.next;
        }

        DMUI.breakLine();
    }
    
    // --------------------------
    // Part 7: List all donations
    // --------------------------
    public static void listAll(){
        
        boolean cont = true;
        do{
            
            int sortSelection = DMUI.listDonationMenu();

            LinkedListInterface<Money> bankList = new LinkedList<>();
            bankList.loadFromFile(BANK_PATH);
            
            LinkedListInterface<Money> cashList = new LinkedList<>();
            cashList.loadFromFile(CASH_PATH);
            
            LinkedListInterface<Money> moneyList = new LinkedList<>();
            moneyList.loadFromFile(BANK_PATH);
            LinkedList<Cash> tempCash = new LinkedList<>();
            tempCash.loadFromFile(CASH_PATH);
            moneyList.appendList(tempCash);

            LinkedListInterface<Food> foodList = loadAllFoodIntoList();

            switch(sortSelection){
                case 1: 
                    DMUI.list1Header();
                    sortMoney(moneyList, 1, 1);
                    break;
                case 2: 
                    DMUI.list2Header();
                    sortMoney(moneyList, 0, 1);
                    break;
                case 3:
                    DMUI.list3Header();
                    sortMoney(bankList, 1, 1);
                    break;
                case 4:
                    DMUI.list4Header();
                    sortMoney(bankList, 0, 1);
                    break;
                case 5:
                    DMUI.list5Header();
                    sortMoney(cashList, 1, 2);
                    break;
                case 6:
                    DMUI.list6Header();
                    sortMoney(cashList, 0, 2);
                    break;
                case 7:
                    DMUI.list7Header();
                    sortFoodExp(foodList);
                    break;
                case 8:
                    DMUI.list8Header();
                    printAllIntoTable();
                    break;
                default:
                    DMUtility.invalidMenuSelection();
                    break;
            }
            
            cont = YN(DMUI.contSortQ());
            
        } while(cont == true);

    }
    
    public static void sortMoney(LinkedListInterface<Money> moneyList, int asc, int header) {
        moneyList.removeEmptyData();

        if (moneyList.getHead() == null) {
            DMUtility.noSuchItem();
            return;
        }
        
        if (moneyList.getHead().next == null){
            DMUI.commonItemHeader();
            DMUI.commonMoneyHeader();
            DMUI.breakLine();
            DMUI.printNode(moneyList.getHead());
            return;
        }

        // Loop through all data in the list
        Node<Money> currentMoney = moneyList.getHead().next; // Start from the second node
        while (currentMoney != null) {
            
            // Get the position data should be inserted
            Node<Money> newPosition = moneyList.getHead();
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
            
            if (newPosition == moneyList.getHead()) {
                currentMoney.previous = null;
                currentMoney.next = moneyList.getHead();
                moneyList.getHead().previous = currentMoney;
                moneyList.setHead(currentMoney);
            } else {
                currentMoney.previous = newPosition.previous;
                currentMoney.next = newPosition;
                newPosition.previous.next = currentMoney;
                newPosition.previous = currentMoney;
            }

            currentMoney = currentMoney.next;
        }
        
        Node<Money> node = moneyList.getHead();
        int stop = 1;
        int pageNum = 1;
        
        DMUI.startOfPage(pageNum);
        
        DMUI.commonItemHeader();
        DMUI.commonMoneyHeader();
        DMUI.breakLine();
        if (header == 1){
            DMUI.bankHeader();
        }
        
        DMUI.breakLine();
        while (node != null) {
            DMUI.printNode(node);
            
            if(stop == 50){
                DMUI.endOfPage(pageNum);
                boolean cont = YN(DMUI.showMorePgQ());
                if (cont){
                    pageNum++;
                    DMUI.startOfPage(pageNum);
                    DMUI.commonItemHeader();
                    DMUI.commonMoneyHeader();
                    if (header == 1){
                        DMUI.bankHeader();
                    }
                    stop = 0;
                }else{
                    return;
                }
            }
            stop++;
            node = node.next;
        }
    }

    public static void sortFoodExp(LinkedListInterface<Food> foodList){
        
        foodList.removeEmptyData();
        

        if (foodList.getHead() == null) {
            DMUtility.noSuchItem();
            return;
        }
        
        if (foodList.getHead().next == null){
            DMUI.commonItemHeader();
            DMUI.commonPhyItemHeader();
            DMUI.commonFoodHeader();
            System.out.println(foodList.getHead().data.toString());
            return;
        }

        // Loop through all data in the list
        Node<Food> currentFood = foodList.getHead().next; // Start from the second node
        while (currentFood != null) {
            
            // Get the position data should be inserted
            Node<Food> newPosition = foodList.getHead();
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
            if (newPosition == foodList.getHead()) {
                currentFood.previous = null;
                currentFood.next = foodList.getHead();
                foodList.getHead().previous = currentFood;
                foodList.setHead(currentFood);
            } else {
                currentFood.previous = newPosition.previous;
                currentFood.next = newPosition;
                newPosition.previous.next = currentFood;
                newPosition.previous = currentFood;
            }

            currentFood = currentFood.next;
        }

        Node<Food> node = foodList.getHead();
        int stop = 0;
        int pageNum = 1;
        DMUI.startOfPage(pageNum);
        DMUI.commonItemHeader();
        DMUI.commonPhyItemHeader();
        DMUI.commonFoodHeader();
        while (node != null) {
            DMUI.printNode(node);
            stop++;
            
            if(stop == 50 && node.next != null){
                DMUI.endOfPage(pageNum);
                boolean cont = YN(DMUI.showMorePgQ());
                if (cont){
                    pageNum++;
                    DMUI.startOfPage(pageNum);
                    DMUI.commonItemHeader();
                    DMUI.commonPhyItemHeader();
                    DMUI.commonFoodHeader();
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

            switch (i){
                case 0: 
                    DMUI.TIBankHeader();
                    break;
                    
                case 1: 
                    DMUI.TICashHeader();
                    break;
                    
                case 2:
                    DMUI.breakLine();
                    DMUI.TIBakedHeader();
                    break;
                    
                case 3: 
                    DMUI.TIBoxedHeader();
                    break;
                    
                case 4:
                    DMUI.TICannedHeader();
                    break;
                    
                case 5:
                    DMUI.TIDryHeader();
                    break;
                    
                case 6: 
                    DMUI.TIEssHeader();
                    break;
                case 7:
                    DMUI.breakLine();
                    DMUI.TIJacketHeader();
                    break;
                    
                case 8:
                    DMUI.TIPantHeader();
                    break;
                    
                case 9:
                    DMUI.TIShirtHeader();
                    break;
                    
                case 10:
                    DMUI.TIShoesHeader();
                    break;
                    
                case 11:
                    DMUI.TISocksHeader();
                    break;

            }
            if (list.isEmpty()) {
                DMUtility.noSuchItem();
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
            int filterSelection = DMUI.filterMainMenu();

            switch(filterSelection){
                case 1:
                    filterByItem();
                    break;
                case 2:
                    filterByYear();

                    break;
                default:
                    DMUtility.invalidMenuSelection();
                    break;
            }
            
            cont = YN(DMUI.contFilterQ());
            
        }while(cont == true);
    }
    
    public static void filterByItem(){
        String type = filterTypeValidation();
        DMUI.breakLine();
        int availabilitySelection = DMUI.inputDisAvailability();
        boolean available = false;
        if (availabilitySelection == 1){
            available = true;
        }
        
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
                type.equals("APPAREL") ||
                type.equals("JACKET") ||
                type.equals("PANT") ||
                type.equals("SHIRT") ||
                type.equals("SHOES") ||
                type.equals("SOCKS") 
                ){
            
            String[] filePath;
            if (type.equals("MONEY")){
                
                filePath = new String[]{BANK_PATH, CASH_PATH};
                printMulSameTable(filePath, available);
                
            }else if(type.equals("BANK")){
                
                printSameTable(BANK_PATH, available);
                
            }else if(type.equals("CASH")){
                
                printSameTable(CASH_PATH, available);
                
            }else if(type.equals("FOOD")){
                
                filePath = new String[]{BAKED_PATH, BOXED_PATH, CANNED_PATH, DRY_PATH, ESS_PATH};
                printMulSameTable(filePath, available);
                
            }else if(type.equals("BAKED GOODS")){
                
                printSameTable(BAKED_PATH, available);
                
            }else if(type.equals("BOXED GOODS")){
                
                printSameTable(BOXED_PATH, available);
                
            }else if(type.equals("CANNED FOOD")){
                
                printSameTable(CANNED_PATH, available);
                
            }else if(type.equals("DRY GOODS")){
                
                printSameTable(DRY_PATH, available);
                
            }else if(type.equals("ESSENTIALS")){
                
                printSameTable(ESS_PATH, available);
                
            }else if (type.equals("APPAREL")){
            
                filePath = new String[]{JACKET_PATH, PANT_PATH, SHIRT_PATH, SHOES_PATH, SOCKS_PATH};
                printMulSameTable(filePath, available);
                
            }else if(type.equals("JACKET")){
                
                printSameTable(JACKET_PATH, available);
                
            }else if(type.equals("PANT")){
                
                printSameTable(PANT_PATH, available);
                
            }else if(type.equals("SHIRT")){
                
                printSameTable(SHIRT_PATH, available);
                
            }else if(type.equals("SHOES")){
                
                printSameTable(SHOES_PATH, available);
                
            }else{ // SOCKS
                
                printSameTable(SOCKS_PATH, available);
                
            }
            
        }else{
            
            LinkedListInterface<Food> foodList = loadAllFoodIntoList();
            
            LinkedListInterface<Shoes> shoeList = new LinkedList<>();
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
        DMUI.filterTypeMenu();
        String type = null;
        
        boolean validType = false;
        while (validType == false){
            type = scan.nextLine();
            
            if(type.isEmpty()){
                DMUtility.emptyInputErrorMsg();
                DMUI.reEnter();
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
                    DMUtility.invalidFilterTypeInput();
                    DMUI.reEnter();
                }
            }
        }
        
        return type;
    }
    
    public static void filterFoodCategory(LinkedListInterface<Food> list, String type) {
        if (list.getHead() == null) {
            DMUtility.noItemToFilter();
            return;
        }

        LinkedListInterface<Item> itemList = new LinkedList<>();
        Node<Food> currentFood = list.getHead();
        while (currentFood != null) {

            if (currentFood.data.getDetail().equals(type)) {
                itemList.insert(currentFood.data);
            }

            currentFood = currentFood.next;
        }

        if (itemList.getHead() == null) {
            DMUtility.noSuchItem();
        } else {
            printListToTable(itemList);
        }
    }
    
    public static void filterShoesCategory(LinkedListInterface<Shoes> list, String type) {
        if (list.getHead() == null) {
            DMUtility.noItemToFilter();
            return;
        }
        
        LinkedList<Item> itemList = new LinkedList<>();
        Node<Shoes> currentShoes = list.getHead();
        while (currentShoes != null) {

            if (currentShoes.data.getDetail().equals(type)) {
                itemList.insert(currentShoes.data);
            }

            currentShoes = currentShoes.next;
        }
        
        if (itemList.getHead() == null) {
            DMUtility.noSuchItem();
        } else {
            printListToTable(itemList);
        }
    }
    
    public static void filterByYear(){
        LinkedListInterface<Food> foodList = loadAllFoodIntoList();
        int year = validYear();

        if (foodList.getHead() == null) {
            DMUtility.noItemToFilter();
            return;
        }

        LinkedListInterface<Item> itemList = new LinkedList<>();
        Node<Food> currentNode = foodList.getHead();
        while (currentNode != null) {
            Date dataDate = currentNode.data.getExpiryDate();
            int dataYear = dataDate.getYear() + 1900; // 124 + 1900 = 2024

            if (dataYear < year) {
                itemList.insert(currentNode.data);
            } 
                
            currentNode = currentNode.next;
        }

        if (itemList.getHead() == null) {
            DMUtility.noSuchItem();
        } else {
            printListToTable(itemList);
        }
    }
    
    public static int validYear() {
        DMUI.inputYear();

        int year = 0;
        boolean validYear = false;
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();

        while (validYear == false) {
            String inputYear = scan.nextLine().trim();

            if (inputYear.isEmpty()) {
                DMUtility.emptyInputErrorMsg();
                DMUI.reEnter();
            } else {
                try {
                    year = Integer.parseInt(inputYear);
                    if (year < currentYear) {
                        DMUtility.inputFutureYear();
                        DMUI.reEnter();
                    } else {
                        validYear = true;
                    }
                } catch (NumberFormatException e) {
                    DMUtility.inputValidYear();
                    DMUI.reEnter();
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
            int reportSelection = DMUI.reportMainMenu();

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
                    DMUtility.invalidMenuSelection();
                    break;
            }
            
            cont = YN(DMUI.contOtherReportQ());
            
        }while(cont == true);
    }
    
    public static void donorContributionReport(){
        LinkedListInterface<Item> itemList = loadAllItemIntoList();
        LinkedListInterface<Donor> donorList = new LinkedList<>();
        donorList.loadFromFile(DONOR_PATH);
        
        itemList.removeEmptyData();
        donorList.removeEmptyData();
        
        if (donorList.isEmpty()){
            DMUtility.noDonorInList();
            return;
        }
        
        if (itemList.isEmpty()){
            DMUtility.noItemInList();
            return;
        }
        
        DMUI.report1Header();
        Node<Donor> currentDonor = donorList.getHead();
        int max = 0;
        Donor donor = currentDonor.data;
        while(currentDonor != null){
            DMUI.printString(currentDonor.data.getId());
            Node<Item> currentItem = itemList.getHead();
            int sum = 0;
            while (currentItem != null){
                if (currentDonor.data.getId().equals(currentItem.data.getDonorID())){
                    sum ++;
                }
                currentItem = currentItem.next;
            }
            
            DMUI.printSum(sum);
            
            printStar(sum);
            
            if (max < sum){
                max = sum;
                donor = currentDonor.data;
            }
            
            DMUI.breakLine();
            currentDonor = currentDonor.next;
        }
        
        DMUI.report1Conclu(donor.getName(), max);
    }
    
    public static void foodExpiryReport(){
        LinkedListInterface<Food> itemList = loadAllFoodIntoList();
        itemList.removeEmptyData();
        
        if (itemList.isEmpty()){
            DMUtility.noFoodItem();
            return;
        }
        
        DMUI.report2Header();
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        
        int largestYear = currentYear;
        Node<Food> tempNode = itemList.getHead();
        while (tempNode != null) {
            int dataYear = tempNode.data.getExpiryDate().getYear() + 1900;
            if (dataYear > largestYear) {
                largestYear = dataYear;
            }
            tempNode = tempNode.next;
        }
        
        DMUI.report2TableH();
        while(currentYear <= largestYear){
            int sum = 0, fa = 0, fo = 0, fc = 0, fd = 0, fe = 0;
            
            Node<Food> currentNode = itemList.getHead();
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
            
            DMUI.report2TableD(currentYear, sum, fa, fo, fc, fd, fe);
            
            currentYear ++;
        }
    }
    
    public static void mostFrequentItem(){
        LinkedListInterface<Item> list = loadAllItemIntoList();
        
        list.removeEmptyData();
        
        if (list.isEmpty()){
            DMUtility.noItemInList();
            return;
        }
        
        DMUI.report3Header();
        LinkedListInterface<Money> moneyList = list.filterByCategoryIntoLinkedListInterface(Money.class);
        LinkedListInterface<Food> foodList = list.filterByCategoryIntoLinkedListInterface(Food.class);
        LinkedListInterface<Apparel> appList = list.filterByCategoryIntoLinkedListInterface(Apparel.class);
        
        int sumM = 0;
        DMUI.r3M();
        for(int i = 0; i < moneyList.length(); i++){
            sumM ++;
        }
        DMUI.printSum(sumM);
        printStar(sumM);
        
        int sumF = 0;
        DMUI.r3F();
        for(int i = 0; i < foodList.length(); i++){
            sumF ++;
        }
        DMUI.printSum(sumF);
        printStar(sumF);
        
        int sumA = 0;
        DMUI.r3A();
        for(int i = 0; i < appList.length(); i++){
            sumA ++;
        }
        DMUI.printSum(sumA);
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
        
        DMUI.report3Conclu(category, max);
    }
    
    public static void printStar(int count){
        if (count > 50){
            int left = count/50;
            for (int i = 0; i < left; i ++){
                DMUI.disStar();
            }
        }
    }
}