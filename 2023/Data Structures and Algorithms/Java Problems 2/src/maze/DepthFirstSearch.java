/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maze;

/**
 * @author Hayden Richard-Marsters 21152003
 */

import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class DepthFirstSearch {
    
    private Stack<Node> stack;
    private List<Node> visitedNodes;
    
    
    public DepthFirstSearch(){
        stack = new Stack<>();
        visitedNodes = new ArrayList<>();
    }
  
    public List<Node> findPath(Node startNode, Node exitNode){   //public method to find a path from startNode to exitNode
        
        stack.clear();
        visitedNodes.clear();
        
        return depthFirstSearch(startNode, exitNode);//Call DFS  method
    }
    
    
    private List<Node> depthFirstSearch(Node current, Node exit){ //Priv. recursive DFS method
       
    visitedNodes.add(current);
    stack.push(current);
    
    if(current.equals(exit)){
        return new ArrayList<>(stack);
    }
    
    for(Node n : current.getLinkedNodes()){
        if(!visitedNodes.contains(n)){
            List<Node> path = depthFirstSearch(n, exit);
            if(path != null){
                return path;
            }
        }
       
    }
    
     stack.pop(); //no path found
     return null;
    
    }
}