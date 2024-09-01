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

/**
 *
 * @author Asus
 */
public class DMUI {

    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String RESET = "\u001B[0m";

    //------------
    // Common Use
    //------------
    public static void inputYN(String sentence) {
        System.out.print("\n" + sentence + "\nPlease enter Y / N: ");
    }

    public static void reEnter() {
        System.out.print("Enter again: ");
    }

    public static void breakLine() {
        System.out.println();
    }

    // header
    public static void commonItemHeader() {
        System.out.printf("| %-10s | %-10s | %-15s | %-15s |", "Item ID", "Donor ID", "Item Category", "Availability");
    }

    public static void commonMoneyHeader() {
        System.out.printf(" %-14s |", "Amount Donated");
    }

    public static void bankHeader() {
        System.out.printf(" %-15s |", "Bank Name");
    }

    public static void commonPhyItemHeader() {
        System.out.printf(" %-20s |", "Remarks");
    }

    public static void commonFoodHeader() {
        System.out.printf(" %-10s | %-8s | %-8s | %-15s |", "Expiry Date", "Weight", "Status", "Food Type");
    }

    public static void commonAppHeader() {
        System.out.printf(" %-10s | %-10s | %-10s | %-10s |", "Size", "Color", "Condition", "Brand");
    }

    public static void commonShoeHeader() {
        System.out.printf(" %-15s |", "Shoes Type");
    }

    public static<T> void printNode(Node<T> node) {
        System.out.println(node.data.toString());
    }
    
    public static void printItem(Item item){
        System.out.println("\n" + item.toString());
    }
    
    public static void startOfPage(int pageNum){
        System.out.println(BLUE +"\n- PAGE " + pageNum + " -" + RESET);
    }
    
    public static void endOfPage(int pageNum){
        System.out.println(BLUE +"- END OF PAGE " + pageNum + " -" + RESET);
    }
    
    public static String showMorePgQ(){
        return "50 records of current table had been shown. Do you want to continue shown more?";
    }

    // menu display
    public static void displayMenu(String[] selectionList) {

        for (int i = 0; i < selectionList.length; i++) {
            System.out.println((i + 1) + ". " + selectionList[i]);
        }

        System.out.print("Enter your selection: ");
    }

    public static int donationManagementMainMenu() {
        System.out.println(BLUE + "\n - - - Donation Management - - - " + RESET);
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
        return DMControl.menuIntReturn(donationManagementMenu);
    }

    // -------------------------
    // Part 1: Add new donation
    // -------------------------
    public static void addDonation(LinkedListInterface<Donor> donorList) {
        System.out.println(BLUE + "\n - - - Add Donation - - - " + RESET);
        System.out.println(donorList.show());
        System.out.println("***Enter \"NONE\" if is anonymous donor. ");
    }

    public static void inputDonorID() {
        System.out.print("Enter donor's id: ");
    }

    public static <T> void disTempDonorData(LinkedListInterface<Donor> donorList) {
        System.out.println("\n - - - Current Donor - - -");
        System.out.printf("%-10s %-2s %-50s\n", "ID", ":", donorList.getHead().data.getId());
        System.out.printf("%-10s %-2s %-50s\n", "Name", ":", donorList.getHead().data.getName());
        System.out.printf("%-10s %-2s %-50s\n", "Category", ":", donorList.getHead().data.getType());
    }

    public static int donorNoExistSelection() {
        String[] contMenu = {"Enter Other Donor", "Exit"};
        return DMControl.menuIntReturn(contMenu);
    }

    public static void numOfItemToAdd() {
        System.out.print("\nNumber of item wish to add: ");
    }

    public static int itemCatMenu(int i) {
        System.out.println("\n - Item " + (i + 1) + " - \n"
                + "Item Category");
        return inputItemCat();
    }
    
    public static int inputItemCat(){
        String[] itemRemoveMenu = {"Bank", "Cash", "Food", "Apparel"};
        return DMControl.menuIntReturn(itemRemoveMenu);
    }

    public static void disAddedItemHeader() {
        System.out.print("\n - - - New Item Added - - - ");
    }

    public static void printedItemCount(int count) {
        System.out.println("\n- Item " + count + " -");
    }

    public static void inputAmtDonated() {
        System.out.print("Amount Donated: RM ");
    }

    public static int inputBankMenu() {
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
        return DMControl.menuIntReturn(bankTypeMenu);
    }

    public static int inputFoodCat() {
        System.out.println("\nFood Category");
        String[] foodCatMenu = {
            "Baked Goods",
            "Boxed Goods",
            "Canned Food",
            "Dry Goods",
            "Essentials"};
        return DMControl.menuIntReturn(foodCatMenu);
    }

    public static void inputBAHeader() {
        System.out.println("\nBaked Goods");
    }

    public static void inputBOHeader() {
        System.out.println("\nBoxed Goods");
    }

    public static void inputCHeader() {
        System.out.println("\nCanned Food");
    }

    public static void inputDHeader() {
        System.out.println("\nDry Goods");
    }

    public static void inputEHeader() {
        System.out.println("\nEssentials");
    }

    public static int inputAppCat() {
        System.out.println("\nApparel Category");
        String[] appCatMenu = {
            "Jacket",
            "Pant",
            "Shirt",
            "Shoes",
            "Socks"};
        return DMControl.menuIntReturn(appCatMenu);
    }

    public static void inputJHeader() {
        System.out.println("\nJacket");
    }

    public static void inputPHeader() {
        System.out.println("\nPant");
    }

    public static void inputShirtHeader() {
        System.out.println("\nShirt");
    }

    public static void inputShoesHeader() {
        System.out.println("\nShoes");
    }

    public static void inputSockHeader() {
        System.out.println("\nSocks");
    }
    
    public static void mustEnterMsg(){
        System.out.println("Item with '*' is compulsary to be enter.");
    }
    
    public static void inputQty(){
        System.out.print("Quantity of same item*: ");
    }
    
    public static void inputRemark(){
        System.out.print("\nRemarks: ");
    }
    
    public static void inputExpDate(){
        System.out.print("\nExpiry Date (dd/mm/yyyy)*: ");
    }
    
    public static int inputExpAction(){
        DMUtility.foodExpired();
        String[] menu = {"Enter again", "Discard"};
        return DMControl.menuIntReturn(menu);
    }
    
    public static void inputWeight(){
        System.out.print("\nWeight(gram)*: ");
    }
    
    public static int inputFoodStatus(){
        System.out.println("\nFood Status");
        String[] foodStatusMenu = {"New (made within 1 weeks)", "Good"};
        return DMControl.menuIntReturn(foodStatusMenu);
    }
    
    public static int inputBakedT(){
        System.out.println("\nBaked Food Type");
        String[] bakedFoodMenu = {"Cookies", "Crackers"};
        return DMControl.menuIntReturn(bakedFoodMenu);
    }
    
    public static int inputBoxedT(){
        System.out.println("\nBoxed Food Type");
        String[] boxedFoodMenu = {"Cereals", "Snacks"};
        return DMControl.menuIntReturn(boxedFoodMenu);
    }
    
    public static int inputCannedT(){
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
        return DMControl.menuIntReturn(cannedFoodMenu);
    }
    
    public static int inputDryT(){
        System.out.println("\nDry Food Type");
        String[] dryFoodMenu = {"Instant noodles", "Oats", "Pasta", "Rice"};
        return DMControl.menuIntReturn(dryFoodMenu);
    }
    
    public static int inputEssT(){
        System.out.println("\nEssentials Type");
        String[] essMenu = {"Oil", "Pepper", "Salt", "Sugar"};
        return DMControl.menuIntReturn(essMenu);
    }
    
    public static int inputAppSize(){
        System.out.println("\nApparel size");
        String[] sizeMenu = {"XS", "S", "M", "L", "XL", "Free Size"};
        return DMControl.menuIntReturn(sizeMenu);
    }
    
    public static void inputShoeSize(){
        System.out.print("\nApparel size (1 - 16): ");
    }
    
    public static int inputColor(){
        System.out.println("\nApparel Color");
        String[] appColorMenu = {"Red", "Orange", "Yellow", "Green", "Blue", "Purple", "Silver", "White", "Black"};
        return DMControl.menuIntReturn(appColorMenu);
    }
    
    public static int inputAppCon(){
        System.out.println("\nApparel Condition");
        String[] appConMenu = {"New", "Good", "Fair", "Poor"};
        return DMControl.menuIntReturn(appConMenu);
    }
    
    public static int inputAppBrand(){
        System.out.println("\nApparel Brand");
        String[] appBrandMenu = {"Adidas", "H & M", "Nike", "Puma", "Uniclo", "Others"};
        return DMControl.menuIntReturn(appBrandMenu);
    }
    
    public static int inputShoesT(){
        System.out.println("\nShoes Category");
        String[] shoesMenu = {"Slipper", "Sport shoes"};
        return DMControl.menuIntReturn(shoesMenu);
    }

    public static String contAddItemForSameDonor() {
        return "Do you want to continue add item for the same donor?";
    }

    public static String contAddItemForDissDonor() {
        return "Do you want to continue adding another donation for other donor?";
    }

    // -------------------------
    // Part 2: Remove a donation
    // -------------------------
    public static int inputRemCat(){
        System.out.println(BLUE + "\nItem to remove:" + RESET);
        return inputItemCat();
    }
    
    public static String contRemoveQ(){
        return "Do you want to continue remove other item?";
    }
    
    public static void remItemHeader(){
        System.out.print("\nWhich item do you wish to remove?\n");
    }
    
    // -------------------------------
    // Part 3: Search donation details
    // -------------------------------
    public static void searchIDHeader(){
        System.out.println(BLUE + "\n - - - Search Donation Item by Item ID - - -" + RESET);
    }
    
    public static void inputItemID(){
        System.out.print("Enter Item ID: ");
    }
    
    public static void printDetailHeader(){
        System.out.println("\nItem Details: ");
    }
    
    public static String contSearchQ(){
        return "Do you want to search for another item ID?";
    }
    
    // ------------------------------
    // Part 4: Amend donation details
    // ------------------------------
    public static void amendDonationHeader(){
        System.out.println(BLUE + "\n - - - Amend Donation Item - - - " + RESET);
    }
    
    public static void optionAvailable(){
        System.out.println("\nOption available");
    }
    
    public static String contAmendSameItemQ(){
        return "Do you want to continue amend this item?";
    }
    
    public static String contAmendOtherQ(){
        return "Do you want to amend another item?";
    }
    
    public static int contUpdateDateMenu(){
        String[] menu = {"Enter Again", "Remain Unchange", "Delete Item"};
        return DMControl.menuIntReturn(menu);
    }
    
    public static void inputDateAgain(){
        System.out.print("Enter date again: ");
    }
    
    // -----------------------------------------
    // Part 5: Track donated items in categories
    // -----------------------------------------
    public static void TIMoneyHeader(){
    System.out.println(BLUE + "\n--- MONEY ---" + RESET);
    }
    
    public static void TIBankHeader(){
    System.out.print("Bank");
    }
    
    public static void TICashHeader(){
    System.out.print("\nCash");
    }
    
    public static void TIFoodHeader(){
    System.out.print(BLUE + "\n--- FOOD ---" + RESET);
    }
    
    public static void TIBakedHeader(){
    System.out.print("\nBaked Goods");
    }
    
    public static void TIBoxedHeader(){
    System.out.print("Boxed Goods");
    }
    
    public static void TICannedHeader(){
    System.out.print("\nCanned Foods");
    }
    
    public static void TIDryHeader(){
    System.out.print("\nDry Goods");
    }
    
    public static void TIEssHeader(){
    System.out.print("\nEssentials");
    }
    
    public static void TIAppHeader(){
    System.out.println(BLUE + "\n--- APPAREL ---" + RESET);
    }
    
    public static void TIJacketHeader(){
    System.out.print("Jackets");
    }
    
    public static void TIPantHeader(){
    System.out.print("\nPant");
    }
    
    public static void TIShirtHeader(){
    System.out.print("\nShirt");
    }
    
    public static void TIShoesHeader(){
    System.out.print("\nShoes");
    }
    
    public static void TISocksHeader(){
    System.out.print("\nSocks");
    }
    
    // ----------------------------------------
    // Part 6: List donation by different donor
    // ----------------------------------------
    
    
    // --------------------------
    // Part 7: List all donations
    // --------------------------
    public static int listDonationMenu(){
        System.out.println(BLUE + "\n - - - Item List - - -" + RESET);
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
        return DMControl.menuIntReturn(sortMenu);
    }
    
    public static void list1Header(){
        System.out.println("\n --- Money List --- (Ascending Order)");
    }
    
    public static void list2Header(){
        System.out.println("\n --- Money List --- (Descending Order)");
    }
        
    public static void list3Header(){
        System.out.println("\n --- Bank List --- (Ascending Order)");
    }
        
    public static void list4Header(){
        System.out.println("\n --- Bank List --- (Descending Order)");
    }
        
    public static void list5Header(){
        System.out.println("\n --- Cash List --- (Ascending Order)");
    }
        
    public static void list6Header(){
        System.out.println("\n --- Cash List --- (Descending Order)");
    }
        
    public static void list7Header(){
    
    }
        
    public static void list8Header(){
    
    }
    
    
}
