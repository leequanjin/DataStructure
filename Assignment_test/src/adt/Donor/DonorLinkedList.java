package adt.Donor;

import adt.LinkedList;
import adt.Node;
import entity.Donor.Donor;
import entity.Donor.DonorPeriodCount;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Kee Ke Shen
 * @param <T>
 */
public class DonorLinkedList<T extends Donor> extends LinkedList<T> {

    // Method to generate a unique Donor ID
    public String generateDonorId() {
        String prefix = "DNR";
        int maxId = 0;

        Node<T> current = head;

        while (current != null) {
            String currentId = current.data.getId().substring(prefix.length());
            int idNumber = Integer.parseInt(currentId);
            if (idNumber > maxId) {
                maxId = idNumber;
            }
            current = current.next;
        }

        return prefix + String.format("%05d", maxId + 1);
    }

    // Method to delete a donor by ID
    public void deleteById(String id) {
        if (head == null) {
            // Empty list
        } else if (head.data.getId().equals(id)) {
            // First id match
            head = head.next;
        } else {
            Node<T> current = head;

            while (current.next != null && !current.next.data.getId().equals(id)) {
                current = current.next;
            }

            if (current.next != null && current.next.data.getId().equals(id)) {
                current.next = current.next.next;
            } else {
                // ID not found
            }
        }
    }

    // Method to find and return a donor by ID
    public Donor findById(String id) {
        Node<T> current = head;

        while (current != null) {
            if (current.data.getId().equals(id)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    // Method to generate a report of total donors by year
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
