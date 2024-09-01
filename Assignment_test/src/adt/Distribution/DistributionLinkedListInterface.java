/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adt.Distribution;

import entity.Distribution.Distribution;
import entity.Distribution.DistributionPeriodCount;
import entity.Distribution.DistributionStatusCount;
import adt.LinkedList;
import adt.LinkedListInterface;

/**
 *
 * @author Lee Quan Jin
 * @param <T>
 */
public interface DistributionLinkedListInterface<T extends Distribution> extends LinkedListInterface<T> {
    
    String generateDistributionId();
    
    Distribution findById(String id);
    
    void updateDonee(String id, String doneeId, String doneeName);
    
    void updateStatus(String id, String status);
    
    LinkedList<DistributionPeriodCount> generateTotalDistributionByYear();
    
    LinkedList<DistributionPeriodCount> generateTotalDistributionByMonth();
    
    DistributionStatusCount generateTotalDistributionsByStatus();
}
