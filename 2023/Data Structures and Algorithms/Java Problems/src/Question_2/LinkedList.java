/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Question_2;

/**
 *
 * @author Hayden 21152003
 */

public class LinkedList <E extends Comparable<E>>{
    
    public int size = 0;
    public Node<E> head;
    //add head method
    public void addHead(E data)
    {
        Node<E> newNode = new Node<>();
        newNode.data = data; 
        
        if(head==null){
            head = newNode;
        }else{
            newNode.next = head; 
            head = newNode; 
        }
        size++; 
    
    }
    //get head
    public Node getHead()
    {
        return this.head;
    }
    //add method
    public void add(E data)
    {
       Node<E> newNode = new Node<>();
       newNode.data = data; 
       
       if(head == null){
           
           head = newNode;
           
       }else{
           add(head, newNode);
       }
       
       size++; 
    }
    //add recursively
    private void add(Node head, Node node)
    {
        if(head.next == null){
            head.next = node;
            
        }else{
            add(head.next, node);
         
        }
    }
    //print list
    public void printLinkedList()
    {
        printLinkedList(head);
    }
    //print recursion
    private void printLinkedList(Node node)
    {
        if(node.next != null){
            printLinkedList(node.next);
        }
        System.out.println(node.data);
    }
    //contains
    public boolean contains(Node node)
    {
        return contains(head, node);
    }
    //contains
    private boolean contains(Node current, Node node)
    {
        if(current == null){
            return false;
        }
        if(current.data == node.data){
            return true;
        }
        
        return contains(current.next, node);
    }
    //remove body
    public void remove(Node node)
    {   
      if(head.equals(node)){
          head = head.next;
          size--;
      }else{
          Node<E> current = this.head;
          while(current != null && !current.next.equals(node)){
              current = current.next;
          }
          if(current != null){
              current.next = current.next.next;
              size--;
          }
      }
    }
    //remove by index
    public void remove(int position)
    {
        
        
            if (position < 1 || position >= size) {
            return;
        } else if (position == 1) {
            if (head != null) {
                head = head.next;
                size--;
            }
        
        }else {
            Node<E> current = head;
            for (int i = 1; i < position-1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
            size--;
        }
            
        
    }
    
   //remove from head
    public E removeFromHead()
    {
         E removeData = null;
            if(size > 0){
                removeData = head.data;
                head = head.next;
                size--;
            }
        
        return removeData;
    }
    //remove from tail
   public E removeFromTail() {
        if (head == null) {
            return null;
        }

        if (head.next == null) {
            E removedData = head.data;
            head = null;
            size--;
            return removedData;
        }

        return removeFromTail(head);
}

    private E removeFromTail(Node<E> current) {
        if (current.next.next == null) {
            E removedData = current.next.data;
            current.next = null;
            size--;
            return removedData;
        }
        return removeFromTail(current.next);
}

   //add in order
    public void addInOrder(E data)
    {
        Node<E> newNode = new Node<>();
        newNode.data = data; 
        if(head == null){
            head = newNode; 
        }else{
            head = addInOrder(head, newNode);
        }
        size++;
    }
    //add in order recursively
    private Node<E> addInOrder(Node<E> current, Node<E> node)
    {
        
      if(current == null || current.compareTo(node) > 0){
            node.next = current;
          return node; 
        }
      current.next = addInOrder(current.next, node);
      return current; 
    }
    //get node 
    public Node getNode(int index)
    {
        return getNode(index, head);
    }
    //get node recursively
    private Node getNode(int index, Node head)
    {
        if(head == null){
           System.err.println("Index is out of bounds");
       }
       if(index == 0){
           return head;
       }
       return getNode(index-1, head.next);
    }
    //recursively getData at index point
    public E getData(int index)
    {
        return getData(index, head);
    }
    //get data recursively
    private E getData(int index, Node<E> current)
    {
       if(current == null){
           System.err.println("Index is out of bounds");
       }
       if(index == 0){
           return current.data;
       }
       return getData(index-1, current.next);
    }  
    
    public E getHeadData() {
        if (head == null) {
            return null;
        }
        return head.data;
    }
    
    public E get(int index) {
    if (index < 0 || index >= size) {
        throw new IndexOutOfBoundsException("Index is out of bounds");
    }

    Node<E> current = head;
    for (int i = 0; i < index; i++) {
        current = current.next;
    }

    return current.data;
}
    
    public void getStringRep(){
        getStringRep(head);
    }
    
    public void getStringRep(Node head){
       
        if(head == null){
            System.out.println("Empty LinkedList, empty snake");
         
            return; 
        }
        
        Node<E> current = head;
        
        while(current != null){
            System.out.println(current.getData() + " ->");
            current = current.next;
        }
    
        System.out.println("null");
    }
}
