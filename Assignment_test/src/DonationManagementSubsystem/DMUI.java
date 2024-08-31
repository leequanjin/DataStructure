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
    public static void inputYN(String sentence){
        System.out.print("\n" + sentence + "\nPlease enter Y / N: ");
    }
    
    public static void reEnter(){
        System.out.print("Enter again: ");
    }
    
    public static void breakLine(){
        System.out.println();
    }
    
    // header
    public static void commonItemHeader(){
        System.out.printf("| %-10s | %-10s | %-15s | %15s |", "Item ID", "Donor ID", "Item Category", "Availability");
    }
    
    public static void commonMoneyHeader(){
        System.out.printf(" %-14s |", "Amount Donated");
    }
    
    public static void bankHeader(){
        System.out.printf(" %-15s |", "Bank Name");
    }
    
    public static void commonPhyItemHeader(){
        System.out.printf(" %-20s |", "Remarks");
    }
    
    public static void commonFoodHeader(){
        System.out.printf(" %-10s | %-8s | %-8s | %-15s |", "Expiry Date", "Weight", "Status", "Food Type");
    }
    
    public static void commonAppHeader(){
        System.out.printf(" %-10s | %-10s | %-10s | %-10s |", "Size", "Color", "Condition", "Brand");
    }
    
    public static void commonShoeHeader(){
        System.out.printf(" %-15s |", "Shoes Type");
    }
    
    public static<T> void printNode(Node<T> node){
        System.out.println("\n" + node.data.toString());
    }
    
    // menu display
    public static void displayMenu(String[] selectionList){

        for (int i = 0; i < selectionList.length; i++) {
            System.out.println((i + 1) + ". " + selectionList[i]);
        }
        
        System.out.print("Enter your selection: ");
    }
    
    public static int donationManagementMainMenu(){
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
    public static void addDonation(){
        System.out.println(BLUE + "\n - - - Add Donation - - - " + RESET);
        System.out.println("***Enter \"NONE\" if is anonymous donor. ");
    }
    
    public static void inputDonorID(){
        System.out.print("Enter donor's id: ");
    }
    
    public static<T> void disTempDonorData(LinkedListInterface<Donor> donorList){
        System.out.println("\n - - - Current Donor - - -");
        System.out.printf("%-10s %-2s %-50s\n", "ID", ":", donorList.getHead().data.getId());
        System.out.printf("%-10s %-2s %-50s\n", "Name", ":", donorList.getHead().data.getName());
        System.out.printf("%-10s %-2s %-50s\n", "Category", ":", donorList.getHead().data.getType());
    }
    
    public static int donorNoExistSelection(){
        String[] contMenu = {"Enter Other Donor", "Exit"};
        return DMControl.menuIntReturn(contMenu);
    }
    
    public static void numOfItemToAdd(){
        System.out.print("\nNumber of item wish to add: ");
    }
    
    public static int itemCatMenu(int i){
        System.out.println( "\n - Item " + (i+1) + " - \n"
                + "Item Category");
        String[] itemMenu = {"Bank", "Cash", "Food", "Apparel"};
        return DMControl.menuIntReturn(itemMenu);
    }
    
    public static void disAddedItemHeader(){
        System.out.print("\n - - - New Item Added - - - ");
    }
    
    public static void printedItemCount(int count){
        System.out.println("\n- Item " + count + " -");
    }
    
    public static void inputAmtDonated(){
        System.out.print("Amount Donated: RM ");
    }
    
}
