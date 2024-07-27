/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package assignment_test;

import java.util.Iterator;

/**
 *
 * @author Asus
 */
public interface QueueInterface<T> {
    void add(T newEntry);
    T peek();
    T poll();
    void remove();
    boolean isFull();
    boolean isEmptyQueue();
    boolean isEmptyEntry();
    int size();
    T[] queueHolder(T[] Ori);
    T[] empty(T[] q);
    String toString();
}
