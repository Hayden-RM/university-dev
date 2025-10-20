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

public class BinaryTree <E, F extends Comparable<F>> {
   
    public Node<E, F> root;
    public int number_of_nodes;
    public Node<E, F>[] nodeList; 
    public boolean isReversed; 
   
    
  public BinaryTree()
    {
        this.root = null; 
        this.number_of_nodes = 0;
        this.nodeList = new Node[number_of_nodes ]; 
        this.isReversed = false; 
    }
    
  public void addElement(E element, F key)
    {
      Node<E, F> newNode = new Node<>(element, key);
      if(root == null){
          
      this.root = newNode; 
      this.number_of_nodes++;
      }else{
          addNode(this.root, newNode);
      }
    }
   /*
  addNode:
  takes a root and a new node to create a tree when the tree is empty or adds a new node to the binary tree.
  */
   public void addNode(Node<E, F> root, Node<E, F> newNode) //Method to add node to Binary tree
    {        
        if(root == null){ //if current root is null, set root to newNode
            root = newNode; 
            this.number_of_nodes++;
            
        }else{
            //Compare key of new node wit key of root
            int comp = newNode.compareTo(root);
            
            if(comp <= 0){
                
                if(root.getLeft() == null){ //Traverse left subtree
                    
                    root.setLeft(newNode);//if left child of the current root is null, insert new node
                    this.number_of_nodes++;

                }else{ 
                    //recursively continue traversal if left child is not null
                    addNode(root.getLeft(), newNode);
                }
            }else{
                //if new node is greater than current root, traverse right subtree
                if(root.getRight() == null){
                    //if right child is null insert new node 
                    root.setRight(newNode);
                    this.number_of_nodes++;
                }else{
                    //if not null, recursively continue traversal passing new node and new root node
                    addNode(root.getRight(), newNode);
                }
            }
        }
    }
    /* traversal:
    It travels each node on the current tree and display node.elements’ details 
   in the order of from smallest key value to the largest key value, or from the 
   largest key value to the smallest key value (it depends on whether reverseOrder() 
   has been called).
   */
  public void traversal(Node<E,F> node) //Method to traverse binary tree
    {
            if(node == null){ //return if current node is null
            return; 
             }
        //Recursively traverse the left subtree
        traversal(node.getLeft());
        //print element
        System.out.println(node.getElement().toString());
        //recursively traverse right subtree
        traversal(node.getRight());
       
    }
    
    private int toSortedList(Node<E,F> root, int index){
        
        if(root != null){
            index = toSortedList(root.getLeft(), index);
            nodeList[index] = root; 
            index++;
            index = toSortedList(root.getRight(), index);
        }
        return index; 
    }
    
  public Node<E, F>[] toSortedList()
    {
       nodeList = new Node[number_of_nodes];
       int index = 0; 
       toSortedList(root, index);
       
       return nodeList; 
    }
     
  public E searchElement(F key)
    {
       Node<E, F> targ = new Node<>(null,key);
       Node<E, F> result = searchNode(root, targ, isReversed);
       
       if(result != null){
           return result.getElement();
       }else{
           return null; 
       }
       
    }
  /*
  searchNode: takes a root and target node. It returns a node if it finds the node. Otherwise, it returns null.
  */ 
  public Node<E, F> searchNode(Node<E, F> root, Node<E, F> targetNode, boolean isReversed) { //Method to traverse Tree and find Node
        
        if (root == null) {//return if current node is null, target can not be found if so
            return null;
        }

        int comp = root.compareTo(targetNode); //Compute comparison result between current/root to the targetNode

        if (isReversed) { //if handling reversed BT, reverse comparison result
            comp = -comp;  
        }

        if (comp == 0) {//if 0, target key = current/root key 
            return root; //return as found
            
        } else if (comp < 0) {
            //if <0, target key is greater, traverse right subtree
            return searchNode(root.getRight(), targetNode, isReversed);
            
        } else { 
            //if >0, target key is smaller, traverse left subtree
            return searchNode(root.getLeft(), targetNode, isReversed);
    }
}
/* reverseOrder:
   It manipulates the binary tree. By default, if we call in-order traversal method, it displays nodes’
  details in the order of from smallest key value to the largest key value. If reverseOrder method has 
  been called, then traversal method displays nodes’ details in the order of from largest key value to 
  the smallest key value
  */
  public void reverseOrder() { //Public method to initiate reverse order
        
    this.isReversed = true; //set flag to true, reversed 
    reverseTree(root);//initiate reversion, pass in root
    
}

  private Node<E, F> reverseTree(Node<E, F> node) {//pass in current node to reverse children
      
        if(node == null){ //if the current node is null, return null
            return null; 
        }

        //Swap left and right children of current node
        Node<E, F> temp = node.getLeft();
        node.setLeft(reverseTree(node.getRight()));
        node.setRight(reverseTree(temp));
    
    return node; //return modified node.

    }

 public void setIsReversed(boolean isReversed) {
        this.isReversed = isReversed;
    }
    
    
    
}
