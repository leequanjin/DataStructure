/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

import java.io.Serializable;

/**
 * Reference https://www.youtube.com/watch?v=sUcVDPDHFJg
 * https://www.youtube.com/watch?v=SMIq13-FZSE&t=1s
 *
 * @author Lee Quan Jin
 * @param <T>
 */
public class Node<T> implements Serializable{

    public T data;
    public Node<T> next;
    public Node<T> previous;

    Node(T data) {
        this.data = data;
        this.next = null;
        this.previous = null;
    }
}
