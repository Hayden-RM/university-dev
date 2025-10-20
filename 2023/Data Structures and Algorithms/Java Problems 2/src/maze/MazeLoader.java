/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maze;

/**
 *
 * @author Hayden Richard-Marsters 21152003
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class MazeLoader {
    
    private List<Node> nodes;
    private FileManager fm;
    private Panel p; 
    private List<Node> dfsPath;
    private int dfsStep;
    

    public MazeLoader(String fileName) {
        nodes = new ArrayList<>();
        fm = new FileManager(fileName); 
        this.dfsStep = 0;
       
    }
    
    public void loadNodes() {
        String[] lineData = fm.lineData;

       
        Map<String, Node> nodeMap = new HashMap<>();

        for (int i = 1; i < lineData.length; i++) { 
            String nodeData = lineData[i].trim();

            String[] parts = nodeData.split(",");
            String name = parts[0].trim();
            int x = Integer.parseInt(parts[1].trim());
            int y = Integer.parseInt(parts[2].trim());

           
            Node node = new Node(name, x, y);
            nodes.add(node);
            nodeMap.put(name, node);
        }
    }

  public void establishLinks() { //Establish and store links to each node. handle W, A - specil conditions
      
        String[] lineData = fm.lineData;

       
        Map<String, Node> nodeMap = new HashMap<>();
        for (Node node : nodes) {
            nodeMap.put(node.getName(), node);
        }

        for (int i = 1; i < lineData.length; i++) { // Skip header
            String nodeData = lineData[i].trim();
            String[] parts = nodeData.split(",");
            String name = parts[0].trim();

            Node node = nodeMap.get(name);

            
            for (int j = 3; j < parts.length; j++) {
                String linkedNodeName = parts[j].trim();

                if (!"A".equals(linkedNodeName)) { //Leave out "A" (No link)
                    if ("W".equals(linkedNodeName)) { //Handle link to exit
                       
                        Node exitNode = nodeMap.get("EXIT");
                        if (exitNode != null) {
                            node.addLink(exitNode);
                            
                            System.out.println(name + " is linked to EXIT");
                        } else {
                            
                            System.out.println("Link failed for " + name + " to EXIT");
                        }
                    } else {
                        Node linkedNode = nodeMap.get(linkedNodeName);
                        System.out.println("Searching for linkedNode: " + linkedNodeName);

                        if (linkedNode != null) {
                            node.addLink(linkedNode);
                            
                            System.out.println(name + " is linked to " + linkedNodeName);
                        } else {
                            
                            System.out.println("Link failed for " + name + " to " + linkedNodeName);
                        }
                    }
                }
            }
        }
        
       
    }
    
    public void performDepthFirstSearch(Node s, Node e){ //method to call to perform DFS
            DepthFirstSearch dfs = new DepthFirstSearch();
            dfsPath = dfs.findPath(s, e);
        }

    public List<Node> getNodes() {
            return nodes;
        }

    public FileManager getFm() {
            return fm;
        }

    public void setFm(FileManager fm) {
            this.fm = fm;
        }
    public Panel getP() {
            return p;
        }

    public void setP(Panel p) {
           this.p = p;
        }

    public Node getStartNode(){
            for(Node n : nodes){
                if("START".equals(n.getName())){
                    return n;
                }
        }
            return null; 
        }
    
    public Node getExitNode(){
        for(Node n : nodes){
            if("EXIT".equals(n.getName())){
                return n;
            }
        }
        return null;
    }

    public List<Node> getDfsPath() {
        return dfsPath;
    }

    public void setDfsPath(List<Node> dfsPath) {
        this.dfsPath = dfsPath;
        this.dfsStep = 0;
    }

    public int getDfsStep() {
        return this.dfsStep;
    }
    
    public void incrementDfsStep(){
        this.dfsStep++;
    }
    
    
}
