/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_test;

/**
 * References Link: https://docs.oracle.com/javase/8/docs/api/java/util/Queue.html
 * @author Asus
 */
public class Queue<T> {
    private T[] queue;
    private T[] current = (T[]) new Object[queue.length];
    private int numOfEntries;
    private static final int DEFAULT_LENGTH = 1000;
    
    public Queue(){
        numOfEntries = 0;
        this.queue= (T[]) new Object[DEFAULT_LENGTH];
    }
    
    public Queue(int customLength){
        numOfEntries = 0;
        this.queue= (T[]) new Object[customLength];
    }
    
    public void add(T newEntry){
        
        boolean full = isFull();
        if(full == true){
            current = queueHolder(queue); // temporaly store in current
            
            this.queue = (T[]) new Object[(queue.length)*2]; // extend ori queue
            
            queue = queueHolder(current); // store back to ori queue
        }
        
        boolean emptySpace = isEmptyEntry();
        while(emptySpace == false){
            numOfEntries++;
            emptySpace = isEmptyEntry();
        }

        if(full == false && emptySpace == true){
            queue[numOfEntries] = newEntry;
            numOfEntries++;
        }

    }
    
    // read the first item in the queue
    public T peek(){
        
        boolean empty = isEmptyQueue();
        if(empty == true){
            return null;
        }
        
        return queue[0];
        
    }
    
    // read and delete the first queue
    public T poll(){
        
        boolean empty = isEmptyQueue();
        if(empty == true){
            return null;
        }
        
        remove();
        
        return queue[0];
    }
    
    // remove the 1st item in queue
    public void remove(){
        current = queueHolder(queue); // store the current data into current queue
        queue = empty(queue); // empty entire ori queue
        
        numOfEntries--; 
        
        for(int i = 0; i < numOfEntries; i++){
            queue[i] = current[i+1]; // store all data back except the first
        }
    }
        
    public boolean isFull(){
        return numOfEntries == queue.length; // must return true
    }
    
    public boolean isEmptyQueue(){
        int j = queue.length;
        
        for(int i = 0; i < queue.length; i++){
            if(queue[i] == null){
                j--;
            }
        }
        
        if(j == 0){
            return true; // whole queue is empty
        }else{
            return false;
        }
    }
    
    public boolean isEmptyEntry(){
        if(queue[numOfEntries] == null){
            return true; // the specific place is empty
        }else{
            return false;
        }
    }
    
    public int size(){
        return queue.length;
    }
    
    public T[] queueHolder(T[] ori){
        
        for(int i = 0; i < queue.length; i++){
            current[i] = ori[i];
        }
        
        return current;
    }
    
    public T[] empty(T[] q){
        for (int i = 0; i < q.length; i++){
            q[i] = null;
        }
        
        return q;
    }
    
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < numOfEntries; i++) {
            if (queue[i] != null) {
                s.append(queue[i]).append(" ");
            }
        }
        return s.toString().trim();
    }
}