/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentapp;

/**
 *
 * @author Hayden Richard-Marsters 21152003
 */
public class Node <E, F extends Comparable<F>> implements Comparable <Node<E,F>>{

    private E element; 
    private F key; 
    private Node<E, F> l_node; 
    private Node<E, F> r_node; 
    
   public Node(E element, F key){
       
       this.element = element;
       this.key = key;
       this.l_node = null;
       this.r_node = null; 
   }
    
   public E getElement(){
       return element; 
   }
   
   public F getKey(){
       return key;
   }
   public Node<E, F> getLeft(){
       return l_node; 
   }
   
   public void setLeft(Node<E, F> left){
       this.l_node = left;
   }
   
   
    public Node<E, F> getRight() {
        return r_node;
    }
   
   public void setRight(Node<E, F> right){
       this.r_node = right; 
   }
   
    @Override
    public int compareTo(Node<E,F> t) {
        return this.key.compareTo(t.getKey());
    }
}
