/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DonationDistributionSubsystem;

import adt.LinkedList;
import adt.Node;
import DonationList.Item;
import java.io.Serializable;

/**
 *
 * @author Lee Quan Jin
 * @param <T>
 */
public class ManageItems<T extends Item> extends LinkedList<T> implements Serializable {

    public void changeStatus(String id, String status) {
        if (head == null) {
            // Empty list
        } else if (head.data.getId().equals(id)) {
            // First id match
            head.data.setAvailability(status);
        } else {
            Node<T> current = head;

            while (current.data != null && !current.data.getId().equals(id)) {
                current = current.next;
            }

            if (current.data.getId().equals(id)) {
                current.data.setAvailability(status);
            } else {
                // ID not found
            }
        }
    }

    public String toStringDonations() {
        StringBuilder donationsStr = new StringBuilder();

        Node<T> current = head;

        donationsStr.append(String.format(
                "%-20s |%-20s",
                current.data.getId(),
                current.data.getType()
        ));
        current = current.next;

        while (current != null) {
            donationsStr.append(String.format(
                    "\n%-118s |%-20s |%-20s",
                    "",
                    current.data.getId(),
                    current.data.getType()
            ));
            current = current.next;
        }

        return donationsStr.toString();
    }
}
