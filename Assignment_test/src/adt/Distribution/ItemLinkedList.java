/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt.Distribution;

import adt.LinkedList;
import adt.Node;
import entity.DonationManagement.Item;
import java.io.Serializable;

/**
 *
 * @author Lee Quan Jin
 * @param <T>
 */
public class ItemLinkedList<T extends Item> extends LinkedList<T> implements ItemLinkedListInterface<T>, Serializable {

    @Override
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

    @Override
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
