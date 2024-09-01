/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adt.Distribution;

import DonationList.Item;
import adt.LinkedListInterface;

/**
 *
 * @author Lee Quan Jin
 * @param <T>
 */
public interface ItemLinkedListInterface<T extends Item> extends LinkedListInterface<T> {

    void changeStatus(String id, String status);

    public String toStringDonations();
}
