/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import adt.Node;
import adt.LinkedListInterface;

import entity.DonationManagement.Item;
import entity.DonationManagement.TotalMoney;
import entity.Donor.Donor;

import control.DonationManagement;
import utility.DonationManagementUtility;

/**
 *
 * @author Asus
 */
public class DonationManagementUI {

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
        System.out.printf(" %-10s | %-8s | %-8s | %-20s |", "Expiry Date", "Weight", "Status", "Food Type");
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
    
    public static void printString(String str){
        System.out.print(str);
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
            "Display total money flow",
            "Generate sumary report",
            "Exit"};
        return DonationManagement.menuIntReturn(donationManagementMenu);
    }

    // -------------------------
    // Part 1: Add new donation
    // -------------------------
    public static void addDonation(LinkedListInterface<Donor> donorList) {
        System.out.println("\n" + BLUE + " - - - Add Donation - - - " + RESET);
        System.out.printf("%-15s |%-30s |%-15s |%-15s |%s\n", "ID", "Name", "Type", "Category", "Registration Date");
        System.out.println(donorList.show());
        System.out.println("\nRemarks: Enter \"NONE\" if is anonymous donor. ");
    }

    public static void inputDonorID() {
        System.out.print("Enter donor's id: ");
    }
    
    public static void disAnonymousDonor(){
        System.out.println("\n - - - Current Donor - - -");
        System.out.printf("%-10s %-2s %-50s\n", "ID", ":", "DNR00000");
        System.out.printf("%-10s %-2s %-50s\n", "Name", ":", "Anonymous");
    }

    public static <T> void disTempDonorData(Donor donor) {
        System.out.println("\n - - - Current Donor - - -");
        System.out.printf("%-10s %-2s %-50s\n", "ID", ":", donor.getId());
        System.out.printf("%-10s %-2s %-50s\n", "Name", ":", donor.getName());
        System.out.printf("%-10s %-2s %-50s\n", "Category", ":", donor.getType());
    }

    public static int donorNoExistSelection() {
        String[] contMenu = {"Enter Other Donor", "Exit"};
        return DonationManagement.menuIntReturn(contMenu);
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
        return DonationManagement.menuIntReturn(itemRemoveMenu);
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
        return DonationManagement.menuIntReturn(bankTypeMenu);
    }

    public static int inputFoodCat() {
        System.out.println("\nFood Category");
        String[] foodCatMenu = {
            "Baked Goods",
            "Boxed Goods",
            "Canned Food",
            "Dry Goods",
            "Essentials"};
        return DonationManagement.menuIntReturn(foodCatMenu);
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
        return DonationManagement.menuIntReturn(appCatMenu);
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
        DonationManagementUtility.foodExpired();
        String[] menu = {"Enter again", "Discard"};
        return DonationManagement.menuIntReturn(menu);
    }
    
    public static void inputWeight(){
        System.out.print("\nWeight(gram)*: ");
    }
    
    public static int inputFoodStatus(){
        System.out.println("\nFood Status");
        String[] foodStatusMenu = {"New (made within 1 weeks)", "Good"};
        return DonationManagement.menuIntReturn(foodStatusMenu);
    }
    
    public static int inputBakedT(){
        System.out.println("\nBaked Food Type");
        String[] bakedFoodMenu = {"Cookies", "Crackers"};
        return DonationManagement.menuIntReturn(bakedFoodMenu);
    }
    
    public static int inputBoxedT(){
        System.out.println("\nBoxed Food Type");
        String[] boxedFoodMenu = {"Cereals", "Snacks"};
        return DonationManagement.menuIntReturn(boxedFoodMenu);
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
        return DonationManagement.menuIntReturn(cannedFoodMenu);
    }
    
    public static int inputDryT(){
        System.out.println("\nDry Food Type");
        String[] dryFoodMenu = {"Instant noodles", "Oats", "Pasta", "Rice"};
        return DonationManagement.menuIntReturn(dryFoodMenu);
    }
    
    public static int inputEssT(){
        System.out.println("\nEssentials Type");
        String[] essMenu = {"Oil", "Pepper", "Salt", "Sugar"};
        return DonationManagement.menuIntReturn(essMenu);
    }
    
    public static int inputAppSize(){
        System.out.println("\nApparel size");
        String[] sizeMenu = {"XS", "S", "M", "L", "XL", "Free Size"};
        return DonationManagement.menuIntReturn(sizeMenu);
    }
    
    public static void inputShoeSize(){
        System.out.print("\nApparel size (1 - 16): ");
    }
    
    public static int inputColor(){
        System.out.println("\nApparel Color");
        String[] appColorMenu = {"Red", "Orange", "Yellow", "Green", "Blue", "Purple", "Silver", "White", "Black"};
        return DonationManagement.menuIntReturn(appColorMenu);
    }
    
    public static int inputAppCon(){
        System.out.println("\nApparel Condition");
        String[] appConMenu = {"New", "Good", "Fair", "Poor"};
        return DonationManagement.menuIntReturn(appConMenu);
    }
    
    public static int inputAppBrand(){
        System.out.println("\nApparel Brand");
        String[] appBrandMenu = {"Adidas", "H & M", "Nike", "Puma", "Uniclo", "Others"};
        return DonationManagement.menuIntReturn(appBrandMenu);
    }
    
    public static int inputShoesT(){
        System.out.println("\nShoes Category");
        String[] shoesMenu = {"Slipper", "Sport shoes"};
        return DonationManagement.menuIntReturn(shoesMenu);
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
        return DonationManagement.menuIntReturn(menu);
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
    System.out.print("- Bank -");
    }
    
    public static void TICashHeader(){
    System.out.print("\n- Cash -");
    }
    
    public static void TIFoodHeader(){
    System.out.print(BLUE + "\n--- FOOD ---" + RESET);
    }
    
    public static void TIBakedHeader(){
    System.out.print("- Baked Goods -");
    }
    
    public static void TIBoxedHeader(){
    System.out.print("\n- Boxed Goods -");
    }
    
    public static void TICannedHeader(){
    System.out.print("\n- Canned Foods -");
    }
    
    public static void TIDryHeader(){
    System.out.print("\n- Dry Goods -");
    }
    
    public static void TIEssHeader(){
    System.out.print("\n- Essentials -");
    }
    
    public static void TIAppHeader(){
    System.out.println(BLUE + "\n--- APPAREL ---" + RESET);
    }
    
    public static void TIJacketHeader(){
    System.out.print("- Jackets -");
    }
    
    public static void TIPantHeader(){
    System.out.print("\n- Pant -");
    }
    
    public static void TIShirtHeader(){
    System.out.print("\n- Shirt -");
    }
    
    public static void TIShoesHeader(){
    System.out.print("\n- Shoes -");
    }
    
    public static void TISocksHeader(){
    System.out.print("\n- Socks -");
    }
    
    // ----------------------------------------
    // Part 6: List donation by different donor
    // ----------------------------------------
    public static void individualHeader(){
        System.out.print(BLUE + "--- INDIVIDUAL ---" + RESET);
    }
    
    public static void organizationHeader(){
        System.out.print(BLUE + "--- ORGANIZATION ---" + RESET);
    }
    
    public static void donorItemCustomHeader(){
        System.out.printf("| %-10s | %-10s | %-15s |\n", "Donor ID", "Item ID", "Item Category");
    }
    
    public static void donorItemCustomFull(String donorID, Node<Item> currentItem){
        System.out.printf("| %-10s | %-10s | %-15s |\n", donorID, currentItem.data.getId(), currentItem.data.getType());
    }
    
    public static void donorItemCustomNor(Node<Item> currentItem){
        System.out.printf("| %-10s | %-10s | %-15s |\n", "", currentItem.data.getId(), currentItem.data.getType());
    }
    
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
        return DonationManagement.menuIntReturn(sortMenu);
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
        System.out.println("\n --- Food List --- (According Expiry Date)");
    }
        
    public static void list8Header(){
        System.out.println("\n --- Donation Item List --- ");
    }
    
    public static String contSortQ(){
        return "Do you want to continue sort or view other items?";
    }
    
    // -----------------------------------------
    // Part 8: Filter donation based on criteria
    // -----------------------------------------
    public static int filterMainMenu(){
        System.out.println(BLUE + "\n- - - Filter Selection - - -" + RESET);
        String[] filterMenu = {"Filter by Item Type (e.g. Sport Shoes)", "Filter Food before and within Expiry's Year (e.g. 2025)"};
        return DonationManagement.menuIntReturn(filterMenu);
    }
    
    public static String contFilterQ(){
        return "Do you want to continue filtering other items?";
    }
        
    public static int inputDisAvailability(){
        System.out.println("Item availability");
        System.out.println("Remarks: all money availability is \" Unavailable \".");
        String[] filterMenu = {"Available Item only", "Unavailable Item only"};
        return DonationManagement.menuIntReturn(filterMenu);
    }
    
    public static void filterTypeMenu(){
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
    }
    
    public static void inputYear(){
        System.out.print("Enter the year: ");
    }
    
    // ---------------------------
    // Part 9: Display money flow 
    // ---------------------------
    public static void moneyFlowHeader(){
        System.out.println(BLUE + "\n- - - Current Money Flow - - -" + RESET);
    }
    
    public static void disMoneyFlow(Node<TotalMoney> money){
        System.out.println(money.data.toString());
    }
    
    // --------------------------------
    // Part 10: Generate summary reports 
    // --------------------------------
    public static int reportMainMenu(){
        System.out.println(BLUE + "\n- - - Report - - -" + RESET);
        String[] reportMenu = {
            "Donor with the Highest Contribution", 
            "Analysis of Food Items with Future Expiry Year",
            "Most Frequently Donated Item Category (Money, Food, Apparel)"
        };
        return DonationManagement.menuIntReturn(reportMenu);
    }
    
    public static String contOtherReportQ(){
        return "Do you want to view another report?";
    }
    
    public static void report1Header(){
        System.out.println("\nDonor with the Highest Contribution");
    }
    
    public static void report1Conclu(String name, int max){
        System.out.println("\nRemarks: Symbol * will be display if item's total exceed 50 and each * represent 50 items");
        System.out.println("Conclusion: The donor with highest contribution is " + name + " with the total of " + max + " donated items.");
    }
    
    public static void report2Header(){
        System.out.println("\nAnalysis of Food Item with Future Expiry Year");
    }
    
    public static void report2TableH(){
        System.out.printf("| %-21s | %-10s | %-10s | %-10s | %-10s | %-10s |\n", "Year\\Food Category", "Baked", "Boxed", "Canned", "Dry", "Essential");
    }
    
    public static void report2TableD(int currentYear, int sum, int fa, int fo, int fc, int fd, int fe){
        System.out.printf("| %-4s %-4s %-4d %-6s | %-10d | %-10d | %-10d | %-10d | %-10d |\n", 
                    currentYear, " -> ", sum , " items", fa, fo, fc, fd, fe);
    }
    
    public static void report3Header(){
        System.out.println("\nMost Frequent Donation Item Category");
    }
    
    public static void r3M(){
        System.out.printf("%-10s", "Money");
    }
    
    public static void r3F(){
        System.out.printf("\n%-10s", "Food");
    }
    
    public static void r3A(){
        System.out.printf("\n%-10s", "Apparel");
    }
    
    public static void report3Conclu(String category, int max){
        System.out.println("\nRemarks: Symbol * will be display if item's total exceed 50 and each * represent 50 items");
        System.out.println("The most frequent donated item category is " + category + ", with total amount of " + max );
    }
    
    public static void printSum(int sum){
        System.out.print(" (" + sum + ") ");
    }
    
    public static void disStar(){
        System.out.print(BLUE + " *" + RESET);
    }
    
}