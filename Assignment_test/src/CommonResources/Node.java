/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CommonResources;

/**
 * Reference https://www.youtube.com/watch?v=SMIq13-FZSE&t=1s
 * @author Lee Quan Jin
 * @param <T>
 */
public class Node<T> {

    public T data;
    public Node<T> next;

    Node(T data) {
        this.data = data;
        this.next = null;
    }
}
