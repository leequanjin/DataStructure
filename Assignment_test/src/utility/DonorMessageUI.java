package utility;

/**
 *
 * @author kee ke shen
 */

public class DonorMessageUI {

    // Colours
    static String Red = "\u001b[31m";
    static String Green = "\u001b[32;2m";
    static String Reset = "\u001b[0m";

    public static boolean isEmpty(String string) {
        return string == null || string.trim().isEmpty();
    }

    public static void displayInvalidChoiceMessage() {
        System.out.println(Red + "Invalid option, please try again." + Reset);
    }
    
    public static void displayInvalidUpdateChoiceMessage() {
        System.out.println(Red + "Invalid option. No changes made." + Reset);
    }

    public static void displayInvalidDonorIdMessage() {
        System.out.println(Red + "Invalid ID. No donor found with the given ID." + Reset);
    }

    public static void displayInvalidDonorNameMessage(String name) {
        System.out.println(Red + "\nNo donors found with name containing: " + name + Reset);
    }
    
    public static void displayNoDonorMessage() {
        System.out.println(Red + "\nNo donors found in the system." + Reset);
    }
    
    public static void displayNoIndividualDonorMessage() {
        System.out.println(Red + "\nNo Individual donors found in the system." + Reset);
    }
    
    public static void displayNoOrganizationDonorMessage() {
        System.out.println(Red + "\nNo Organization donors found in the system." + Reset);
    }
    
    public static void displayNoDonorInCategoryMessage() {
        System.out.println(Red + "\nNo donors found in the selected category." + Reset);
    }
    
    public static void displayExitMessage() {
        System.out.println(Green + "Exiting donor menu..." + Reset);
    }
}
