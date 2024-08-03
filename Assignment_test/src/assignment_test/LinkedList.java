/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author leeda
 * @param <T>
 */
public class LinkedList<T> {

    Node<T> head;

    // Method to insert data at the end of the list
    public void insert(T data) {
        Node node = new Node(data);

        if (head == null) {
            head = node;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = node;
        }
    }

    // Method to insert data at the start of the list
    public void insertAtStart(T data) {
        Node node = new Node(data);

        node.next = head;
        head = node;
    }

    // Method to insert data at a specific index of the list
    public void insertAt(int index, T data) {
        Node node = new Node(data);

        Node current = head;
        if (index == 0) {
            insertAtStart(data);
        } else {
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            node.next = current.next;
            current.next = node;
        }
    }

    // Method to delete data at the end of the list
    public void delete() {
        if (head == null) {
            // Empty List
        } else if (head.next == null) {
            // Only 1 node in the list
            head = null;
        } else {
            Node current = head;
            while (current.next.next != null) {
                current = current.next;
            }
            current.next = null;
        }
    }

    // Method to delete data at the start of the list
    public void deleteAtStart() {
        if (head == null) {
            // Empty List
        } else {
            head = head.next;
        }
    }

    // Method to delete data at a specific index of the list
    public void deleteAt(int index) {
        if (index < 0 || head == null) {
            // Invalid index or empty list
        } else if (index == 0) {
            deleteAtStart();
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                if (current.next == null) {
                    // Index is out of bounds
                } else {
                    current = current.next;
                }
            }

            if (current.next != null) {
                // Only delete data if index is not out of bounds
                current.next = current.next.next;
            }
        }
    }
    
    // Method to print out all nodes in the list
    public void show() {
        Node n = head;

        if (head == null) {
            // List is empty
        } else {
            while (n != null) {
                System.out.println(n.data);
                n = n.next;
            }
        }
    }

    // Method to save the linked list to a file
    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            Node<T> current = head;
            while (current != null) {
                oos.writeObject(current.data);
                current = current.next;
            }
        } catch (IOException e) {
        }
    }

    // Method to load the linked list from a file
    public void loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            T obj;
            while ((obj = (T) ois.readObject()) != null) {
                insert(obj);
            }
        } catch (IOException | ClassNotFoundException e) {
        }
    }
    
    // Method to return a filtered linked list
    public LinkedList filterByCategory(Class categoryClass) {
        LinkedList filteredList = new LinkedList<>();
        Node current = head;

        while (current != null) {
            if (categoryClass.isInstance(current.data)) {
                filteredList.insert(categoryClass.cast(current.data));
            }
            current = current.next;
        }

        return filteredList;
    }
}
