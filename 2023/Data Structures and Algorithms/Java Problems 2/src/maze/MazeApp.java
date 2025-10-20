/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import javax.swing.JFrame;
import java.util.List;


/**
 *
 * @author Hayden Richard-Marsters 21152003
 */

public class MazeApp {
/**
 * @param args the command line arguments
 */
public static void main(String[] args) {
   
        MazeLoader mazeLoader = new MazeLoader("maze2.txt"); //CHANGE TEXT FILE 
        mazeLoader.loadNodes();
        mazeLoader.establishLinks();

        JFrame frame = new JFrame("Maze");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Maze m = new Maze(mazeLoader);
        Panel panel = new Panel(m);

        frame.add(panel);
        frame.setSize(700, 600);
        frame.setVisible(true);

        panel.repaint();

         DepthFirstSearch dfs = new DepthFirstSearch();

        Node startNode = m.ml.getStartNode();
        Node exitNode = m.ml.getExitNode();

        m.ml.performDepthFirstSearch(startNode, exitNode);
        List<Node> path = m.ml.getDfsPath();
    
            if(path != null){
                System.out.println("-BEGIN-");
                for(Node n : path){
                    System.out.println(n.getName() + " -> ");
                }
                System.out.println("-COMPLETE-");
            }else{
                System.out.println("NO PATH FOUND");
            }
            while (mazeLoader.getDfsStep() < path.size()) {

                panel.repaint();
                mazeLoader.incrementDfsStep();

                    try {
                        Thread.sleep(500);  
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }

}

