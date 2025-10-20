/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Question_2;

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

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
    
    @Override
    public String toString(){
        return data.toString();
    }
    
    
    
}

