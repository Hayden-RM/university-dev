/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question_1;

/**
 *
 * @author Hayden 21152003
 */
public class Queue <E extends Comparable<E>>{
    
    private LinkedList<E> queue = new LinkedList();
    
    public Queue(){
        queue = new LinkedList<E>();
    }
    public void enqueue(E data)
    {
        queue.add(data);
    }
    
    public E dequeue()
    {
        return queue.removeFromHead();
    }
    
    public void printQueue()
    {
        queue.printLinkedList();
    }
    
    public int getSize()
    {
        return queue.size;
    }
}
