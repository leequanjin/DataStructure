/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DonationDistributionSubsystem;

import CommonResources.LinkedList;
import CommonResources.Node;
import DonationList.Item;
import java.io.Serializable;

/**
 *
 * @author Lee Quan Jin
 * @param <T>
 */
public class ManageItems<T extends Item> extends LinkedList<T> implements Serializable{

    public void changeStatusToUnavailable(String id) {
        if (head == null) {
            // Empty list
        } else if (head.data.getId().equals(id)) {
            // First id match
            head.data.setAvailability("Unavailable");
        } else {
            Node<T> current = head;

            while (current.data != null && !current.data.getId().equals(id)) {
                current = current.next;
            }

            if (current.data.getId().equals(id)) {
                current.data.setAvailability("Unavailable");
            } else {
                // ID not found
            }
        }
    }

    public String toStringDonations() {
        StringBuilder donationsStr = new StringBuilder();

        Node<T> current = head;

        while (current != null) {
            donationsStr.append(String.format(
                    "%-20s |%-20s",
                    current.data.getId(),
                    current.data.getType()
            ));
            current = current.next;
        }

        return donationsStr.toString();
    }
}
