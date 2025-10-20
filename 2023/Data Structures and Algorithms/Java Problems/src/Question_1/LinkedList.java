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
    //remove from body
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
        if(position < 0 || position >= size){
            return;
        }else if(position == 0){
            if (this.head != null) {
            head = head.next;
            size--;
    }
        }else if(position == size - 1){
    if (head == null || head.next == null) {
         if (head != null) {
                head = head.next;
                size--;
        } else {
          Node < E > current = this.head;
          while (current.next.next!= null) {
            current = current.next;
          }
          current.next = null;
          this.size--;
        }
      }
      }else{
            Node<E> current = head; 
            for(int i =0; i < position - 1; i++){
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
   
}
