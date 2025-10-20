/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maze;

/**
 *
 * @author Hayden Richard-Marsters 21152003
 */
public class Maze {
    
    public MazeLoader ml;
    public Node[][] mazeGrid; 
    
    public Maze(MazeLoader ml){
        this.ml = ml;
        initMaze();
    }
    
    public void initMaze(){
        
        int maxX = 0;
        int maxY = 0;

        for (Node node : ml.getNodes()) {
            if (node.getX() > maxX) {
                maxX = node.getX();
            }
            if (node.getY() > maxY) {
                maxY = node.getY();
            }
        }

        
        mazeGrid = new Node[maxX + 1][maxY + 1]; // Initialize the maze grid

        
        for (Node node : ml.getNodes()) { // Add nodes to the grid
            mazeGrid[node.getX()][node.getY()] = node;
        }
    }

    public MazeLoader getMl() {
        return ml;
    }

    public void setMl(MazeLoader ml) {
        this.ml = ml;
    }
   
}
