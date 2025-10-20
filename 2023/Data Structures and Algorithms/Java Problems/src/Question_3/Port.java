/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question_3;

/**
 *
 * @author Hayden 21152003
 */

import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Port {
    
    int x;
    int y;
    String name = "Port";
    boolean occupied; 
    ImageIcon islandIcon; 
    private int width; 
    private int height; 
    
    public Port(int x, int y)
    {
        this.x = x;
        this.y = y; 
        this.occupied = false;
        islandIcon = new ImageIcon("island.png");
        

    }
    
    public void draw(Graphics g){
        g.drawImage(islandIcon.getImage(), x, y, null);
    }
    
    public synchronized boolean isOccupied(){
        return occupied;
    }
    
    public synchronized void setOccupied(boolean occupied){
        this.occupied = occupied; 
    }
   

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    
    
    
    
    
    
}
