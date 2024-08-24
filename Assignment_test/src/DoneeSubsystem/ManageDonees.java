/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoneeSubsystem;

import CommonResources.Node;
import CommonResources.LinkedList;

/**
 *
 * @author Lee Quan Jin
 * @param <T>
 */
public class ManageDonees<T extends Donee> extends LinkedList<T> {

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

    public Donee findById(String id) {
        Node<T> current = head; // Start from the head of the list

        while (current != null) {
            if (current.data.getId().equals(id)) {
                return current.data; // Donor found
            }
            current = current.next; // Move to the next node
        }
        return null; // Donor not found
    }

    // Method to replace an element
    public void replace(T oldEntry, T newEntry) {
        Node<T> current = head;

        while (current != null) {
            if (current.data.equals(oldEntry)) {
                current.data = newEntry;
                break;
            } else {
                current = current.next;
            }
        }
    }

    public Donee changeToIndividual(Donee donee) {
        Individual individualDonee = new Individual(donee.getId(), donee.getName());
        return individualDonee;

    }

    public Donee changeToFamily(Donee donee) {
        Family familyDonee = new Family(donee.getId(), donee.getName());
        return familyDonee;
    }

    public Donee changeToOrganization(Donee donee) {
        Individual individualDonee = new Individual(donee.getId(), donee.getName());
        return individualDonee;
    }
}
