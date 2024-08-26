/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DonationList;

import CommonResources.LinkedList;
import CommonResources.Node;

/**
 *
 * @author Asus
 */
public class ManageItem<T extends Item> extends LinkedList<T> {

    public ManageItem(){}
    
    public ManageItem(String filePath) {
        super.loadFromFile(filePath);
    }
    
    // Method to delete data by ID
    public void deleteById(String id) {
        if (head == null) {
            // Empty list
        } else if (head.data.getId().equals(id)) {
            // First id match
            head = head.next;
        } else {
            Node<T> current = head;

            while (!current.next.data.getId().equals(id) && current.next.data != null) {
                current = current.next;
            }

            if (current.next.data.getId().equals(id)) {
                current.next = current.next.next;
            } else {
                // ID not found
            }
        }
    }

    public Item findById(String id) {
        Node<T> current = head; // Start from the head of the list

        while (current != null) {
            if (current.data.getId().equals(id)) {
                return current.data; // Item found
            }
            current = current.next; // Move to the next node
        }
            return null; // Item not found
    }
    
    @Override
    public String toString(){
        
        String result = "";
        
        Node<T> current = head;
        int i = 1;
        while (current != null) {
            result += ( i + ".\n" + (current.data.toString()) + ("\n\n"));
            current = current.next;
            i++;
        }
        
        return result.toString();
    }
}
