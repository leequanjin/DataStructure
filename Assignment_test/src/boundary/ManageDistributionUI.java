/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import adt.Distribution.DistributionLinkedListInterface;
import entity.Distribution.Distribution;
import entity.Distribution.DistributionStatusCount;
import adt.LinkedListInterface;
import java.util.Scanner;

/**
 *
 * @author leeda
 */
public class ManageDistributionUI {
    
    //Colours
    static String Blue = "\u001B[34m";
    static String Reset = "\u001b[0m";
    
    Scanner scanner = new Scanner(System.in);
    String choice = null;
    
    // Boundary - ManageDistributionUI //
    public void displayDistributionMenu() {
        System.out.println(Blue + "\n- - - Donation Distribution Menu - - -" + Reset);
        System.out.println("Choose an option:");
        System.out.println("1. Add new donation distribution");
        System.out.println("2. Delete donation distribution");
        System.out.println("3. Update donation distribution status"); // update status
        System.out.println("4. Monitor/track distributed items "); // check pending / check success 
        System.out.println("5. List all distributed donations");
        System.out.println("6. Generate Summary Report"); // which donee receive most item
        System.out.println("7. Exit \n");
    }

    public void displayUpdateDistributionChoices() {
        System.out.println("Update status to:");
        System.out.println("1. Completed");
        System.out.println("2. Cancelled");
        System.out.println("3. In Progress\n");
    }

    public void displayFoundDistribution(Distribution distribution) {
        System.out.printf("%-20s |%-20s |%-30s |%-20s |%-20s |%-20s |%s\n",
                "Distribution ID",
                "Donee ID",
                "Donee Name",
                "Distribution Date",
                "Status",
                "Donation ID",
                "Donation Type");
        String line = String.format("-").repeat(160);
        System.out.println(line);
        System.out.println(distribution.toString() + "\n");
    }

    public void displayDistributionList(DistributionLinkedListInterface<Distribution> distributionList) {
        System.out.printf("%-20s |%-20s |%-30s |%-20s |%-20s |%-20s |%s\n",
                "Distribution ID",
                "Donee ID",
                "Donee Name",
                "Distribution Date",
                "Status",
                "Donation ID",
                "Donation Type");
        String line = String.format("-").repeat(160);
        System.out.println(line);
        System.out.println(distributionList.show());
    }

    public void displayDistributionReportMenu() {
        System.out.println("1. Number of distributions by year");
        System.out.println("2. Number of distributions by month");
        System.out.println("3. All time distributions by status \n");
    }

    public void displayDistributionPeriodReport(LinkedListInterface distributionListByPeriod) {
        System.out.printf("%-15s |%-20s |%-20s |%s\n", "Period", "Completed", "Cancelled", "In Progress");
        String line = String.format("-").repeat(74);
        System.out.println(line);
        System.out.println(distributionListByPeriod.show());
    }

    public void displayDistributionStatusReport(DistributionStatusCount distributionListByStatus) {
        String line = String.format("-").repeat(35);
        System.out.println(line);
        System.out.println(distributionListByStatus.toString());
        System.out.println();
    }

    public String getChoice() {
        System.out.print("Enter your choice: ");
        choice = scanner.nextLine();
        System.out.println("");

        return choice;
    }

    public String getChoiceYN(String message) {
        System.out.print(message + " (Y/N): ");
        choice = scanner.nextLine().trim().toUpperCase();
        System.out.println("");

        return choice;
    }

    public String getID(String idType) {
        System.out.print("Enter " + idType + " ID: ");
        String id = scanner.nextLine();
        return id;
    }
}
