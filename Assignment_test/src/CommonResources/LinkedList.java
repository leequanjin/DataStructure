/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CommonResources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.function.Predicate;

/**
 * Reference https://www.youtube.com/watch?v=SMIq13-FZSE&t=1s
 * https://www.youtube.com/watch?v=AeqXFjCUcQM
 * https://www.youtube.com/watch?v=tZxPqhkRLiw&t=438s
 * https://www.youtube.com/watch?v=sUcVDPDHFJg
 *
 * @author Lee Quan Jin
 * @param <T>
 */
public class LinkedList<T> {

    public Node<T> head;
    public Node<T> tail;

    // Method to insert data at the end of the list 
    // Time Complexity : O(1)
    public void insert(T data) {
        Node node = new Node(data);

        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            node.previous = tail;
            tail = node;
        }
    }

    // Method to insert data at the start of the list
    // Time Complexity : O(1)
    public void insertAtStart(T data) {
        Node node = new Node(data);

        if (head == null) {
            head = tail = node;
        } else {
            head.previous = node;
            node.next = head;
            head = node;
        }
    }

    // Method to insert data at a specific index of the list
    // Time Complexity : O(n)
    public void insertAt(int index, T data) {
        Node node = new Node(data);
        Node current = head;

        if (index < 0) throw new IndexOutOfBoundsException("Invalid index");
        
        if (index == 0) {
            insertAtStart(data);
        } else {
            for (int i = 0; i < index - 1; i++) {
                if (current == null) throw new IndexOutOfBoundsException("Invalid index");
                current = current.next;
            }
            node.next = current.next;
            node.previous = current;

            if (current.next != null) {
                current.next.previous = node;
            } else {
                tail = node;
            }
            current.next = node;
        }
    }

    // Method to delete data at the end of the list
    // Time Complexity : O(1)
    public void delete() {
        if (tail == null) {
            // Empty List
        } else if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.previous;
            tail.next = null;
        }
    }

    // Method to delete data at the start of the list
    // Time Complexity : O(1)
    public void deleteAtStart() {
        if (head == null) {
            // Empty List
        } else if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
            head.previous = null;
        }
    }

    // Method to delete data at a specific index of the list
    // Time Complexity : O(n)
    public void deleteAt(int index) {
        if (index < 0 || head == null) throw new IndexOutOfBoundsException("Invalid index");
        
        if (index == 0) {
            deleteAtStart();
        } else {
            Node current = head;
            
            for (int i = 0; i < index; i++) {
                if (current == null) throw new IndexOutOfBoundsException("Invalid index");
                    current = current.next;
            }

            if (current == tail) {
                delete();
            } else {
                current.previous.next = current.next;
                current.next.previous = current.previous;
            }
        }
    }
    
    // Method to remove elements based on a condition
    public void removeIf(Predicate<T> filter) {
        Node<T> current = head;

        while (current != null) {
            Node<T> next = current.next;
            if (filter.test(current.data)) {
                if (current == head) {
                    deleteAtStart();
                } else if (current == tail) {
                    delete();
                } else {
                    current.previous.next = current.next; // Update previous node pointer to point to the correct next node
                    current.next.previous = current.previous; // Update next node previous pointer to point to the correct previous pointer
                }
            }
            current = next;
        }
    }

    // Method to print out all nodes in the list
    public void show() {
        Node current = head;

        if (head == null) {
            // List is empty
        } else {
            while (current != null) {
                System.out.print(current.data + "\n");
                current = current.next;
            }
        }
    }

    // Method to save the linked list to a file
    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            Node current = head;
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
    
    // Method to return linkedlist length
    public int length(){
        int count = 0;
        Node current = head;
        
        if(head == null){
            return 0;
        }
        
        while(current != null){
            count ++;
            current = current.next;
        }
        
        return count;
    }

    // Method to check if the linked list is empty
    public boolean isEmpty() {
        return head == null;
    }
}
