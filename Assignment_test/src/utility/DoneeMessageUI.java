/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

/**
 *
 * @author Christopher Yap Jian Xing
 */
public class DoneeMessageUI {

    //Colours
    static String Red = "\u001b[31m";
    static String Green = "\u001b[32;2m";
    static String Reset = "\u001b[0m";

    public void displayInvalidChoiceMessage() {
        System.out.println(Red + "Invalid option, please try again." + Reset);
    }

    public void displayInvalidDoneeIdMessage() {
        System.out.println(Red + "Invalid ID. No donee found with the given ID." + Reset);
    }

    public void displayInvalidDoneeNameMessage(String name) {
        System.out.println(Red + "\nNo donees found with name containing: " + name + Reset);
    }

    public void displayExitMessage() {
        System.out.println(Green + "Exiting donee menu..." + Reset);
    }
}
