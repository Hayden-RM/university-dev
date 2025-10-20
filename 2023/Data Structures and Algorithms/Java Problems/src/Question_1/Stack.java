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
public class Stack <E extends Comparable<E>>{
    LinkedList<E> stack = new LinkedList();
    
    public Stack(){
        stack = new LinkedList<>();
    }
    
    public void push(E data)
    {
        stack.add(data);
    }
    
    public E pop()
    {
        return stack.removeFromTail();
    }
    
    public void printStack()
    {
        stack.printLinkedList();
    }
    
    public int getSize()
    {
        return stack.size;
    }
    
    public E peek(){
        return stack.getHeadData();
    }
    
}
