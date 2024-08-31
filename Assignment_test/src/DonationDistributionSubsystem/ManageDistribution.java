/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DonationDistributionSubsystem;
import CommonResources.LinkedList;
import CommonResources.Node;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author leeda
 * @param <T>
 */
public class ManageDistribution<T extends Distribution> extends LinkedList<T> {
    
    public String generateDistributionId() {
        String prefix = "DIS";
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
    
    public LinkedList<DistributionPeriodCount> generateTotalDistributionByYear() {
        LinkedList<DistributionPeriodCount> distributionsEachYear = new LinkedList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        LinkedList<String> processedYears = new LinkedList<>();

        Node<T> current = head;

        while (current != null) {
            String year = current.data.getDistibutionDate().format(formatter);

            if (!processedYears.contains(year)) {
                int completedCount = 0;
                int cancelledCount = 0;
                int inProgressCount = 0;

                while (current != null && year.equals(current.data.getDistibutionDate().format(formatter))) {
                    String status = current.data.getStatus();
                    switch (status) {
                        case "Completed" ->
                            completedCount++;
                        case "Cancelled" ->
                            cancelledCount++;
                        default ->
                            inProgressCount++;
                    }
                    current = current.next;
                }

                processedYears.insert(year);
                distributionsEachYear.insert(new DistributionPeriodCount(year, completedCount, cancelledCount, inProgressCount));
            }
        }

        return distributionsEachYear;
    }
    
    public LinkedList<DistributionPeriodCount> generateTotalDistributionByMonth() {
        LinkedList<DistributionPeriodCount> distributionsEachMonth = new LinkedList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM");
        LinkedList<String> processedMonths = new LinkedList<>();

        Node<T> current = head;

        while (current != null) {
            String month = current.data.getDistibutionDate().format(formatter);

            if (!processedMonths.contains(month)) {
                int completedCount = 0;
                int cancelledCount = 0;
                int inProgressCount = 0;

                while (current != null && month.equals(current.data.getDistibutionDate().format(formatter))) {
                    String status = current.data.getStatus();
                    switch (status) {
                        case "Completed" ->
                            completedCount++;
                        case "Cancelled" ->
                            cancelledCount++;
                        default ->
                            inProgressCount++;
                    }
                    current = current.next;
                }

                processedMonths.insert(month);
                distributionsEachMonth.insert(new DistributionPeriodCount(month, completedCount, cancelledCount, inProgressCount));
            }
        }

        return distributionsEachMonth;
    }
    
    public DistributionStatusCount generateTotalDistributionsByStatus() {

        Node<T> current = head;
        int completedCount = 0;
        int cancelledCount = 0;
        int inProgressCount = 0;

        while (current != null) {

            while (current != null) {
                String status = current.data.getStatus();

                switch (status) {
                    case "Completed" ->
                        completedCount++;
                    case "Cancelled" ->
                        cancelledCount++;
                    case "In Progress" ->
                        inProgressCount++;
                    default -> {
                        System.out.println("Error status not found");
                    }
                }
                current = current.next;
            }
        }
        DistributionStatusCount doneesEachState = new DistributionStatusCount(completedCount, cancelledCount, inProgressCount);

        return doneesEachState;
    }
}
