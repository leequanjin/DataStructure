/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CommonResources;

import java.util.function.Predicate;

/**
 *
 * @author Lee Quan Jin
 * @param <T>
 */
public interface LinkedListInterface<T> {
    
    void setHead(Node<T> head);

    Node<T> getHead();
    
    // Method to insert data at the end of the list 
    void insert(T data);

    // Method to insert data at the start of the list
    void insertAtStart(T data);

    // Method to insert data at a specific index of the list
    void insertAt(int index, T data);

    // Method to delete data at the end of the list
    void delete();

    // Method to delete data at the start of the list
    void deleteAtStart();

    // Method to delete data at a specific index of the list
    void deleteAt(int index);
    
    // MEthod to remove element based on entry
    void removeEntry(T item);

    // Method to remove elements based on a condition
    void removeIf(Predicate<T> filter);

    // Method to print out all nodes in the list
    String show();

    // Method to save the linked list to a file
    void saveToFile(String filename);

    // Method to load the linked list from a file
    void loadFromFile(String filename);

    // Method to return a filtered linked list
    LinkedList<T> filterByCategory(Class<T> categoryClass);
    
    // Method to replace an element
    void replace(T oldEntry, T newEntry);

    // Method to return the length of the linked list
    int length();

    // Method to check if the linked list is empty
    boolean isEmpty();
    
    // Method to append two list
    void appendList(LinkedList anotherList);
    
    // Method to remove empty data if exist
    void removeEmptyData();
    
    // Method to clear all data in list
    void clear();
}