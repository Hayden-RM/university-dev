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


public class Node <E extends Comparable<E>> {
    
    public E data;
    public Node <E> next;
    
    public boolean equals(Node node)
    {
        
        return this.data.equals(node.data);
    }
    
    public int compareTo(Node<E> node)
    {
        return this.data.compareTo(node.data);
        
    }
}
