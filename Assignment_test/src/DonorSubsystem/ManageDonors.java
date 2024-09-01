/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DonorSubsystem;

import adt.Node;
import adt.LinkedList;
import entity.Donee.DoneePeriodCount;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Kee Ke Shen
 * @param <T>
 */
public class ManageDonors<T extends Donor> extends LinkedList<T>{

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

    // Method to find and return a donor by ID
    public Donor findById(String id) {
        Node<T> current = head; // Start from the head of the list

        while (current != null) {
            if (current.data.getId().equals(id)) {
                return current.data; // Donor found
            }
            current = current.next; // Move to the next node
        }
            return null; // Donor not found
    }
    public LinkedList<DonorPeriodCount> generateTotalDonorByYear() {
        LinkedList<DonorPeriodCount> donorsEachYear = new LinkedList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        LinkedList<String> processedYears = new LinkedList<>();

        Node<T> current = head;

        while (current != null) {
            String year = current.data.getRegistrationDate().format(formatter);

            if (!processedYears.contains(year)) {
                int individualCount = 0;
                int organizationCount = 0;

                while (current != null && year.equals(current.data.getRegistrationDate().format(formatter))) {
                    String type = current.data.getType();
                    switch (type) {
                        case "Individual" ->
                            individualCount++;
                        default ->
                            organizationCount++;
                    }
                    current = current.next;
                }

                processedYears.insert(year);
                donorsEachYear.insert(new DonorPeriodCount(year, individualCount, organizationCount));
            }
        }

        return donorsEachYear;
    }
}
