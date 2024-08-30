/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DonationDistributionSubsystem;
import CommonResources.LinkedList;
import CommonResources.Node;

/**
 *
 * @author leeda
 * @param <T>
 */
public class ManageDistribution<T extends Distribution> extends LinkedList<T> {
    
    public void changeStatusToCancelled(String id) {
        if (head == null) {
            // Empty list
        } else if (head.data.getId().equals(id)) {
            // First id match
            head.data.setStatus("Cancelled");
        } else {
            Node<T> current = head;

            while (current.data != null && !current.data.getId().equals(id)) {
                current = current.next;
            }

            if (current.data.getId().equals(id)) {
                current.data.setStatus("Cancelled");
            } else {
                // ID not found
            }
        }
    }
    
    public Distribution findById(String id) {
        Node<T> current = head; // Start from the head of the list

        while (current != null) {
            if (current.data.getId().equals(id)) {
                return current.data; // Donor found
            }
            current = current.next; // Move to the next node
        }
        return null; // Donor not found
    }
    
    public void updateDonee(String id, String doneeId, String doneeName) {
        if (head == null) {
            // Empty list
        } else if (head.data.getId().equals(id)) {
            // First id match
            head.data.setDoneeId(doneeId);
            head.data.setDoneeName(doneeName);
        } else {
            Node<T> current = head;

            while (current.data != null && !current.data.getId().equals(id)) {
                current = current.next;
            }

            if (current.data.getId().equals(id)) {
                current.data.setDoneeId(doneeId);
                current.data.setDoneeName(doneeName);
            } else {
                // ID not found
            }
        }
    }
    
    
    public void updateStatus(String id, String status) {
        if (head == null) {
            // Empty list
        } else if (head.data.getId().equals(id)) {
            // First id match
            head.data.setStatus(status);
        } else {
            Node<T> current = head;

            while (current.data != null && !current.data.getId().equals(id)) {
                current = current.next;
            }

            if (current.data.getId().equals(id)) {
                current.data.setStatus(status);
            } else {
                // ID not found
            }
        }
    }
}
