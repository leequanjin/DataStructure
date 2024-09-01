/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.LinkedList;
import adt.LinkedListInterface;
import entity.Donee;
import entity.DoneePeriodCount;
import entity.DoneeStateCount;

/**
 *
 * @author leeda
 * @param <T>
 */
public interface DoneeLinkedListInterface<T extends Donee> extends LinkedListInterface<T> {

    String generateDoneeId();

    void changeStatus(String id, String status);

    void deleteById(String id);

    Donee findById(String id);

    LinkedList<DoneePeriodCount> generateTotalDoneeByYear();

    LinkedList<DoneePeriodCount> generateTotalDoneeByMonth();

    DoneeStateCount generateTotalDoneeByState();
}
