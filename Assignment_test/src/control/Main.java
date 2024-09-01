/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import static boundary.MainUI.displayMainMenu;
import utility.MainMessageUI;

/**
 *
 * @author Lee Quan Jin
 */
public class Main {

    public static void main(String[] args) {
        boolean exit = false;
        
        ManageDonor manageDonor = new ManageDonor();
        ManageDonee manageDonee = new ManageDonee();
        ManageDistribution manageDistribution = new ManageDistribution();
        
        while (!exit) {
            displayMainMenu();
            
            String choice = MainMessageUI.getChoice();

            switch (choice) {
                case "1" -> {
                    manageDonor.donorMenu();
                }
                case "2" -> {
                    manageDonee.doneeMenu();
                }
                case "3" -> {
                    DonationManagement.donationManagementMainMenu();
                }
                case "4" -> {
                    manageDistribution.distributionMenu();
                }
                case "5" -> {
                    VolunteerManagement.volunteerMainMenu();
                }
                case "6" -> {
                    System.out.println("Not implemented yet");
                }
                case "7" -> {
                    exit = true;
                    MainMessageUI.displayExitMessage();
                }
                default -> MainMessageUI.displayInvalidChoiceMessage();
            }
        }
    }
}
