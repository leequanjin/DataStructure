/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

/**
 *
 * @author Lee Quan Jin
 */
public class DistributionMessageUI {
    //Colours
    static String Red = "\u001b[31m";
    static String Green = "\u001b[32;2m";
    static String Reset = "\u001b[0m";
    
    // Utility
    public void displayInvalidChoiceMessage() {
        System.out.println(Red + "Invalid option, please try again." + Reset);
    }

    public void displayInvalidChoiceYNMessage() {
        System.out.println(Red + "Only enter 'Y' or 'N'." + Reset);
    }

    public void displayInvalidIdMessage(String idType) {
        System.out.println(Red + "Invalid ID. No " + idType + " found with the given ID." + Reset);
    }

    public void displayFoundDoneeMessage(String doneeName) {
        System.out.println(Green + "Found donee: " + doneeName + "\n" + Reset);
    }

    public void displayInvalidDonationMessage() {
        System.out.println(Red + "Invalid length. The length should be 7 and format AA00000." + Reset);
    }

    public void displayDonationNotAvailableMessage() {
        System.out.println(Red + "Donation Item is not available." + Reset);
    }

    public void displayDistributionCreatedMessage(String id) {
        System.out.println(Green + "Donation Distribution added with ID: " + id + Reset);
    }

    public void displayDistributionRemovedMessage(String id) {
        System.out.println(Green + "Donation Distribution with ID (" + id + ") was removed successfully." + Reset);
    }

    public void displayDistributionStatusUpdatedMessage(String id, String status) {
        System.out.println(Green + "Distribution (" + id + ") status was updated to " + status + " successfully." + Reset);
    }

    public void displayDoneeNotEligibleMessage(String doneeName) {
        System.out.println(Red + "Donee (" + doneeName + ") is not eligible to accept new donations currently. Returning to donation distribution menu...\n" + Reset);
    }

    public void displayExitMessage() {
        System.out.println(Green + "Exiting donee menu..." + Reset);
    } 
}
