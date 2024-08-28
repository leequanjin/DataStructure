/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VolunteerSubsystem;

import CommonResources.LinkedList;

import java.util.Scanner;

/**
 *
 * @author Heng Pei Lin
 */


public class VBoundary {
    
    private static final String VOLUNTEER_PATH = "volunteers.txt";
    
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";
    
    public static void main(String[] args){
        boolean fileExist = VDAO.chkFileExist(VOLUNTEER_PATH);
        if (! fileExist){
            boolean createFile = VDAO.createFile(VOLUNTEER_PATH);
            if(createFile){
                System.out.println(ANSI_GREEN + "File creted successfully." + ANSI_RESET);
            }else{
                System.out.println(ANSI_RED + "Error creating file." + ANSI_RESET);
            }
        }
        
        volunteerMainMenu();
    }
    
    public static void volunteerMainMenu(){
        boolean cont = true;
        do{
            LinkedList list = new LinkedList();
            list.loadFromFile(VOLUNTEER_PATH);

            System.out.println(" - - - Volunteer - - -");
            String[] volMenu = {
                "Add New Volunteer", 
                "Remove Volunteer",
                "Search Volunteer",
                "Assign Volunteer to Event",
                "Search Event Under a Volunteer",
                "List All"
            };
            int selection = menuIntReturn(volMenu);
            switch(selection){
                case 1:
                    addVolunteer(list);
                    break;
                case 2:
                    remVolunteer(list);
                    break;
                case 3:
                    searchVolunteer(list);
                    break;
                case 4:
                    assignEvent();
                    break;
                case 5:
                    searchVolunteerEvent();
                    break;
                case 6:
                    listVolunteer();
                    break;
                default:
                    System.out.println(ANSI_RED + "Invalid menu selection." + ANSI_RESET);
                    break;
            }

            cont = YN("Do you want to continue manage volunteer?");
            if(cont){
                System.out.println();
            }
        }while(cont);
        
    }
    
    // Add new Volunteer
    public static void addVolunteer(LinkedList list){
        
        Scanner scan = new Scanner(System.in);
        
        System.out.println(ANSI_BLUE + "\n- - - Add Volunteer - - - " + ANSI_RESET);
        // ID
        String id = VControl.idGenerator("VL", list);
        
        // Name
        System.out.print("Enter name: ");
        boolean validName = false;
        String name = null;
        while(!validName){
            name = scan.nextLine();
            
            validName = VControl.chkEmptyInput(name);
            if(!validName){
                System.out.println(ANSI_RED + "Cannot leave blank.\n" + ANSI_RESET);
                System.out.print("Enter again: ");
            }
        }
        
        // Gender
        System.out.println("\nGender");
        String[] genderMenu = {"Male", "Female"};
        int gSelection = menuIntReturn(genderMenu);
        
        String gender = null;
        switch(gSelection){
            case 1:
                gender = "Male";
                break;
            case 2:
                gender = "Female";
                break;
            default:
                System.out.println(ANSI_RED + "Invalid gender.\n" + ANSI_RESET);
                break;
        }
        
        // Age
        System.out.println("\nRemarks: Only people within the age 18 to 60 years old can registered.");
        System.out.print("Enter age: ");
        boolean validAge = false;
        int age = 0;
        while(validAge == false){
            String inputAge = scan.nextLine();
            
            validAge = chkIntInputInRange(inputAge, 18, 60);
            if(validAge){
                age = Integer.parseInt(inputAge);
            }
        }
        
        // Contact Num
        System.out.print("\nEnter phone number (e.g. 011 2345 6789): ");
        boolean validPhone = false;
        String phone = null;
        while(!validPhone){
            phone = scan.nextLine();
            
            validPhone = VControl.chkEmptyInput(phone);
            if(validPhone){
            
                // check is it all integer
                phone = phone.trim();
                validPhone = VControl.chkInt(phone);
                if(validPhone){
                    
                    //  check correct phone format
                    
                    validPhone = VControl.chkPhoneValidation(phone);
                    if(!validPhone){
                        System.out.println(ANSI_RED + "Invalid phone format.\n" + ANSI_RESET);
                    }
                }else{
                    System.out.println(ANSI_RED + "Please enter number only.\n" + ANSI_RESET);
                }
                
            }else{
                System.out.println(ANSI_RED + "Cannot leave blank.\n" + ANSI_RESET);
            }
            
            if(!validPhone){
                System.out.print("Enter again: ");
            }
            
        }
        
        Volunteer newVol = new Volunteer(id, name, gender, age, phone);
        list.insert(newVol);
        
        System.out.println(ANSI_GREEN + "\nVolunteer added successfully." + ANSI_RESET);
        list.show();
        list.saveToFile(VOLUNTEER_PATH);
    }
    
    // Remove Volunteer
    public static void remVolunteer(LinkedList list){
        
        System.out.println(ANSI_BLUE + "\n- - - Remove Volunteer - - - " + ANSI_RESET);
        if(list.isEmpty()){
            System.out.println(ANSI_RED + "No volunteer yet.\n" + ANSI_RESET);
            return;
        }else{
            list.show();
        }

        String id = idValidation();
        
        Volunteer foundVolunteer = VControl.findById(list, id);
        if(foundVolunteer == null){
            System.out.println(ANSI_RED + "\nVolunteer does not exist." + ANSI_RESET);
        }else{
            boolean remove = VControl.deleteById(list, id);
            if (remove == false){
                System.out.println(ANSI_RED + "\nRemove unsuccessfully." + ANSI_RESET);
            }else{
                System.out.println(ANSI_GREEN + "\nRemove successfully." + ANSI_RESET);
                list.show();
                list.saveToFile(VOLUNTEER_PATH);
            }
        }
    }
    
    // Search Volunteer
    public static void searchVolunteer(LinkedList list){
        System.out.println(ANSI_BLUE + "\n- - - Remove Volunteer - - - " + ANSI_RESET);
        if(list.isEmpty()){
            System.out.println(ANSI_RED + "No volunteer yet.\n" + ANSI_RESET);
            return;
        }
        String id = idValidation();
        
        Volunteer foundVolunteer = VControl.findById(list, id);
        if(foundVolunteer == null){
            System.out.println(ANSI_RED + "\nVolunteer does not exist." + ANSI_RESET);
        }else{
            System.out.println(foundVolunteer.toString());
        }
    }
    
    // Assign volunteers to events
    public static void assignEvent(){}
    
    // Search events under a volunteer
    public static void searchVolunteerEvent(){}
    
    // List all volunteers
    public static void listVolunteer(){}
    
    //------------
    // Common Use
    //------------
    public static boolean YN(String sentence) {
        Scanner scan = new Scanner(System.in);
        
        boolean validInput = false;
        String input = null;
        
        System.out.print("\n" + sentence + "\nPlease enter Y / N: ");

        while (!validInput) {

            input = (scan.nextLine()).toUpperCase().trim();
            
            validInput = VControl.chkEmptyInput(input);
            
            if(validInput){
                String[] inputList = {"Y", "N"};
                validInput = VControl.chkSpecificWord(inputList, input);
                if(!validInput){
                    System.out.println(ANSI_RED + "Please enter Y or N only.\n"+ ANSI_RESET);
                }
            }else{
                System.out.println(ANSI_RED + "Cannot beave blank.\n"+ ANSI_RESET);
            }
            
            if(!validInput){
                System.out.print("Enter again: ");
            }
            
        }
        
        boolean cont = VControl.chkYN(input.toUpperCase().trim());
        if(cont){
            return true;
        }else{
            return false;
        }
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
            
            validInput = chkIntInputInRange(stringInput, 1, selectionList.length);
        
            if(validInput){
                intInput = Integer.parseInt(stringInput);
            }
            
        }
        
        return intInput;
    }
    
    public static boolean chkIntInputInRange(String input,int initial,int end){
        boolean validInput;
        
        // check is empty
        validInput = VControl.chkEmptyInput(input);
        if(validInput){

            // chk is it an integer
            validInput = VControl.chkInt(input);
            if(validInput){
                
                // chk is within the integer range
                int intInput = Integer.parseInt(input);
                validInput = VControl.intSelectionValidation(intInput, initial, end);
                if(!validInput){
                    System.out.println(ANSI_RED + "Invalid integer. Please enter between " + initial + " to " + end + ".\n" + ANSI_RESET);
                }
                
            }else{
                System.out.println(ANSI_RED + "Invalid integer. Please enter a valid integer.\n" + ANSI_RESET);
            }

        }else{
            System.out.println(ANSI_RED + "Cannot leave blank.\n" + ANSI_RESET);
        }

        if(!validInput){
            System.out.print("Enter again: ");
        }
        
        return validInput;
    }
    
    public static String idValidation(){
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Enter volunteer id: ");
        
        boolean validID = false;
        String id = null;
        while(!validID){
            id = scan.nextLine();
            id = id.trim();
            // chk is it empty
            validID = VControl.chkEmptyInput(id);
            if(validID){
                // chk correct length
                validID = VControl.chkLength(7, id);
                if(validID){
                    
                    // chk if correct format
                    validID = VControl.volunteerIdValidation(id);
                    if(!validID){
                        System.out.println(ANSI_RED + "Invalid format. Volunteer Id format should be VL00000.\n" + ANSI_RESET);
                    }
                }else{
                    System.out.println(ANSI_RED + "Invalid length.\n" + ANSI_RESET);
                }
            }else{
                System.out.println(ANSI_RED + "Cannot leave blank.\n" + ANSI_RESET);
            }
            
            if(!validID){
                System.out.print("Enter again: ");
            }
            
        }
        
        return id.substring(0, 2).toUpperCase() + id.substring(2, 7);
    }
}
