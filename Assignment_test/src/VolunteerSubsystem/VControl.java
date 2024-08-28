/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VolunteerSubsystem;

import CommonResources.LinkedList;
import CommonResources.Node;

/**
 *
 * @author Heng Pei Lin
 */
public class VControl {
    
    public static String idGenerator(String ab, LinkedList<Volunteer> list){
        
        return (ab + String.format("%05d", list.length() + 1));
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
    
    public static boolean chkPhoneValidation(String phone){
        // 011
        if(phone.substring(0, 3).equals("011") && phone.length() == 11){
            return true;
        }else if( phone.substring(0, 2).equals("01") && phone.length() == 10){ //016 018 017
            return true;
        }else{
            return false;
        }
    }
    
    //find the specific volunteer
    public static Volunteer findById(LinkedList<Volunteer> list, String id) {
        Node<Volunteer> current = list.head;

        while (current != null) {
            if (current.data.getVolunteerID().equals(id)) {
                return current.data;
            }
            current = current.next;
        }
            return null;
    }
    
    public static boolean volunteerIdValidation(String id){
        if ( (id.substring(0, 2).toUpperCase().equalsIgnoreCase("VL")) && (id.length() == 7) ){
            return true;
        }else{
            return false;
        }
    }
    
    public static boolean deleteById(LinkedList<Volunteer> list, String id) {
        if (list.head == null) {
            
            return false;
        } else if (list.head.data.getVolunteerID().equals(id)) {
            // First id match
            list.head = list.head.next;

            return true;
        } else {
            Node<Volunteer> currentNode = list.head;

            while (currentNode != null) {

                if (currentNode.next.data.getVolunteerID().equals(id)) {
                    currentNode.next = currentNode.next.next;
                    return true;
                } 
                currentNode = currentNode.next;
            }
             
            return false;
        }
    }
}
