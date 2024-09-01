package adt.Donor;

/**
 *
 * @author kee ke shen
 */

import adt.LinkedList;
import adt.LinkedListInterface;
import entity.Donor.Donor;
import entity.Donor.DonorPeriodCount;

public interface DonorLinkedListInterface<T extends Donor> extends LinkedListInterface<T> {

    String generateDonorId();

    void changeCategory(String id, String newCategory);

    void deleteById(String id);

    Donor findById(String id);

    LinkedList<DonorPeriodCount> generateTotalDonorByYear();

}
