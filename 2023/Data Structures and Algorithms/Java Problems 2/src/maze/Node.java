/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maze;

/**
 *
 * @author Hayden Richard-Marsters 21152003
 */
import java.util.List;
import java.util.ArrayList;

public class Node { 
    
    private String name; 
    private int x, y;
    private List<Node> linkedNodes;
    
    public Node(String name, int x, int y){
        this.name = name;
        this.x = x;
        this.y = y;
        this.linkedNodes = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public List<Node> getLinkedNodes() {
        return linkedNodes;
    }

    public void setLinks(List<Node> links) {
        this.linkedNodes = links;
    }
     public void addLink(Node linkedNode) {
        linkedNodes.add(linkedNode);
    }

    
}
