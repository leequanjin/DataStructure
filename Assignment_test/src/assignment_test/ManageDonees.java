/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_test;

/**
 *
 * @author Lee Quan Jin
 * @param <T>
 */
public class ManageDonees <T extends Donee> extends LinkedList<T>{

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
}
