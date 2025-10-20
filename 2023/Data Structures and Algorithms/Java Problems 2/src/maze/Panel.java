/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.util.List;



/**
 *
 * @author Hayden Richard-Marsters 21152003
 */
public class Panel extends JPanel{
    
    private Maze maze; 

    public Panel(Maze maze)
    {
        this.maze = maze;
    }
    
    @Override
  protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        Node[][] mazeGrid = maze.mazeGrid;
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int rows = mazeGrid.length;
        int cols = mazeGrid[0].length;

        int spacing = 10;
        int horizontalSpacing = (panelWidth - 2 * spacing) / rows;
        int verticalSpacing = (panelHeight - 2 * spacing) / cols;

        int ovalWidth = horizontalSpacing / 4;
        int ovalHeight = verticalSpacing / 4;

       //Draw Lines
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Node currentNode = mazeGrid[i][j];
                if (currentNode != null) {
                    int x = spacing + i * horizontalSpacing + (horizontalSpacing - ovalWidth) / 2;
                    int y = spacing + j * verticalSpacing + (verticalSpacing - ovalHeight) / 2;

                    g.setColor(Color.RED);
                    for (Node linkedNode : currentNode.getLinkedNodes()) {
                        int startX = x + ovalWidth / 2;
                        int startY = y + ovalHeight / 2;
                        int endX = spacing + linkedNode.getX() * horizontalSpacing + (horizontalSpacing - ovalWidth) / 2 + ovalWidth / 2;
                        int endY = spacing + linkedNode.getY() * verticalSpacing + (verticalSpacing - ovalHeight) / 2 + ovalHeight / 2;

                        g.drawLine(startX, startY, endX, endY);
                    }
                }
            }
        }

        // Draw Nodes 
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                
                Node currentNode = mazeGrid[i][j];
                
                if (currentNode != null) {
                    
                    int x = spacing + i * horizontalSpacing + (horizontalSpacing - ovalWidth) / 2;
                    int y = spacing + j * verticalSpacing + (verticalSpacing - ovalHeight) / 2;

                    g.setColor(Color.BLUE);
                    g.fillOval(x, y, ovalWidth, ovalHeight);

                    String nodeName = currentNode.getName();
                    int nameX = x + ovalWidth / 2 - g.getFontMetrics().stringWidth(nodeName) / 2 - 13;
                    int nameY = y - 5;
                    g.setColor(Color.WHITE);
                    g.drawString(nodeName, nameX, nameY);
                }
            }
        }
        
        //draw path
        List<Node> dfsPath = maze.ml.getDfsPath();
        int dfsStep = maze.ml.getDfsStep();

        if (dfsPath != null) {

            g.setColor(Color.GREEN);

            for (int i = 1; i < dfsStep+1; i++) {
                
                if( i < dfsPath.size()){
                    
                Node currentNode = dfsPath.get(i - 1);
                Node nextNode = dfsPath.get(i);

                int startX = spacing + currentNode.getX() * horizontalSpacing + (horizontalSpacing - ovalWidth) / 2 + ovalWidth / 2;
                int startY = spacing + currentNode.getY() * verticalSpacing + (verticalSpacing - ovalHeight) / 2 + ovalHeight / 2;

                int endX = spacing + nextNode.getX() * horizontalSpacing + (horizontalSpacing - ovalWidth) / 2 + ovalWidth / 2;
                int endY = spacing + nextNode.getY() * verticalSpacing + (verticalSpacing - ovalHeight) / 2 + ovalHeight / 2;

                g.drawLine(startX, startY, endX, endY);
            }
        }
    }
  }
}
