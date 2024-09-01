/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt.Donee;

import entity.Donee.Donee;
import entity.Donee.DoneePeriodCount;
import entity.Donee.DoneeStateCount;
import adt.LinkedList;
import adt.Node;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Christopher Yap Jian Xing
 * @param <T>
 */

public class DoneeLinkedList<T extends Donee> extends LinkedList<T> implements DoneeLinkedListInterface<T> {

    @Override
    public String generateDoneeId() {
        String prefix = "DNE";
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

    @Override
    public void changeStatus(String id, String status) {
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

    // Method to delete data by ID
    @Override
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

    // Method to find and return a donee by ID
    @Override
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

    @Override
    public LinkedList<DoneePeriodCount> generateTotalDoneeByYear() {
        LinkedList<DoneePeriodCount> doneesEachYear = new LinkedList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        LinkedList<String> processedYears = new LinkedList<>();

        Node<T> current = head;

        while (current != null) {
            String year = current.data.getRegistrationDate().format(formatter);

            if (!processedYears.contains(year)) {
                int individualCount = 0;
                int familyCount = 0;
                int organizationCount = 0;

                while (current != null && year.equals(current.data.getRegistrationDate().format(formatter))) {
                    String type = current.data.getType();
                    switch (type) {
                        case "Individual" ->
                            individualCount++;
                        case "Family" ->
                            familyCount++;
                        default ->
                            organizationCount++;
                    }
                    current = current.next;
                }

                processedYears.insert(year);
                doneesEachYear.insert(new DoneePeriodCount(year, individualCount, familyCount, organizationCount));
            }
        }

        return doneesEachYear;
    }

    @Override
    public LinkedList<DoneePeriodCount> generateTotalDoneeByMonth() {
        LinkedList<DoneePeriodCount> doneesEachMonth = new LinkedList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM");
        LinkedList<String> processedMonths = new LinkedList<>();

        Node<T> current = head;

        while (current != null) {
            String month = current.data.getRegistrationDate().format(formatter);

            if (!processedMonths.contains(month)) {
                int individualCount = 0;
                int familyCount = 0;
                int organizationCount = 0;

                while (current != null && month.equals(current.data.getRegistrationDate().format(formatter))) {
                    switch (current.data.getType()) {
                        case "Individual" ->
                            individualCount++;
                        case "Family" ->
                            familyCount++;
                        default ->
                            organizationCount++;
                    }
                    current = current.next;
                }

                processedMonths.insert(month);
                doneesEachMonth.insert(new DoneePeriodCount(month, individualCount, familyCount, organizationCount));
            }
        }

        return doneesEachMonth;
    }

    @Override
    public DoneeStateCount generateTotalDoneeByState() {

        Node<T> current = head;
        int johorCount = 0;
        int kedahCount = 0;
        int kelantanCount = 0;
        int melakaCount = 0;
        int negeriSembilanCount = 0;
        int pahangCount = 0;
        int penangCount = 0;
        int perakCount = 0;
        int perlisCount = 0;
        int sabahCount = 0;
        int sarawakCount = 0;
        int selangorCount = 0;
        int terengganuCount = 0;

        while (current != null) {

            while (current != null) {
                String state = current.data.getLocation();

                switch (state) {
                    case "Johor" ->
                        johorCount++;
                    case "Kedah" ->
                        kedahCount++;
                    case "Kelantan" ->
                        kelantanCount++;
                    case "Melaka" ->
                        melakaCount++;
                    case "Negeri Sembilan" ->
                        negeriSembilanCount++;
                    case "Pahang" ->
                        pahangCount++;
                    case "Penang" ->
                        penangCount++;
                    case "Perak" ->
                        perakCount++;
                    case "Perlis" ->
                        perlisCount++;
                    case "Sabah" ->
                        sabahCount++;
                    case "Sarawak" ->
                        sarawakCount++;
                    case "Selangor" ->
                        selangorCount++;
                    case "Terengganu" ->
                        terengganuCount++;
                    default -> {
                        System.out.println("Error state not found");
                    }
                }
                current = current.next;
            }
        }
        DoneeStateCount doneesEachState = new DoneeStateCount(johorCount, kedahCount, kelantanCount, melakaCount, negeriSembilanCount, pahangCount, penangCount, perakCount, perlisCount, sabahCount, sarawakCount, selangorCount, terengganuCount);

        return doneesEachState;
    }
}
