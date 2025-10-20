/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
Which object have you chosen as a monitor object to synchronize your code?

Chosen object for synchronization monitoring: Port


Why did you choose that object as a monitor oject to synchronize your code?

By synchronizing on the port object, it ensures only one thread can access the synchronized code block, 
only one ship can access and interact with the port at any given time. In this simulation,
only one ship should be able to access the port at any given time, synchronizing using the port object  
prevents concurrent access issues when multiple ships try to interact with the port (causing ships to crash)
This syncrhonization is used to coordinate the movement and interaction of ships with the port in synchronized mode, 
preventing ship crashes seen in unsynchronized mode caused by multiple ships accessing the port at one time.

*/

package Question_3;

/**
 *
 * @author Hayden 21152003
 */


import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.util.Random;
import javax.swing.SwingUtilities;

public class Ship extends Thread{
    
    int x;
    int y;
    String name = "Ship";
    boolean crashed = false; 
    boolean pursuingIsland = false; 
    ImageIcon shipIcon;
    Port port; 
    SimulationMode activeMode;
    Panel panel; 

    
    public Ship(int x, int y, Port port, SimulationMode activeMode, Panel panel)
    {
        this.x = x;
        this.y = y;
        this.port = port; 
        this.crashed = false;
        this.pursuingIsland = false; 
        shipIcon = new ImageIcon("ship.png");
        this.activeMode = activeMode; 
        this.panel = panel; 
    }  
    
    public void run(){
        moveToIsland();
    }
    
    
    private void moveToIsland() {
    // synchronized mode
    if (activeMode == SimulationMode.SYNCHRONISED) {
        boolean isMoving = false;
        System.out.println("Running simulation in synchronized mode");
        synchronized (port) {
            while (port.isOccupied()) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
            port.notifyAll();
        

        synchronized (port) {
            isMoving = true;
        }

        while (x <= port.x) { // Move horizontally towards the island
            x += 40;
            
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    panel.repaint();
                }
            });
            
            try {
                Thread.sleep(new Random().nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (y < port.y) { // Move vertically towards the island
            y += 50;
            
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    panel.repaint();
                }
            });
            
            try {
                Thread.sleep(new Random().nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (y > port.y) { // Move vertically towards the island
            y -= 50;
            
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    panel.repaint();
                }
            });
            
            try {
                Thread.sleep(new Random().nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

            pursuingIsland = true;
            isMoving = false;
            port.notifyAll();
            landedAtPort();
            
        }
    } else if (activeMode == SimulationMode.UNSYNCHRONISED) {
        System.out.println("Running simulation in unsynchronized mode");
        if (!port.isOccupied()) {
            boolean collisionDetected = false;
            for (Ship ship : panel.ships) {
              
                if (ship != this && detectCollision(this, ship)) {
                    collisionDetected = true;
                    crashed = true;
                    System.out.println("Ship crashed!");
                    break;
                }
            }
            if (!collisionDetected) {
                pursuingIsland = true;
                port.setOccupied(true);
            } else {
                crashed = true;
                System.out.println("Ship crashed! ");
            }
        }

        while (x <= port.x && !crashed) { // Move horizontally towards the island
            x += 50;
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    panel.repaint();
                }
            });

            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while (y < port.y && !crashed) { // Move vertically towards the island
            y += 50;
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    panel.repaint();
                }
            });

            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while (y > port.y && !crashed) { // Move vertically towards the island
            y -= 50;
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    panel.repaint();
                }
            });

            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (!crashed) {
            pursuingIsland = true;
            landedAtPort();
            port.setOccupied(false);
        }
    }
}

    private boolean detectCollision(Ship s1, Ship s2) {
        return s1.x == s2.x && s1.y == s2.y;
    }

    public void setCrashed(boolean crashed){
        this.crashed = crashed; 
    }
    
    public void draw(Graphics g){
        g.drawImage(shipIcon.getImage(), x, y, null);
    }
    
    public boolean isAtIsland() {
        
        return pursuingIsland; 
    }

    private void landedAtPort(){
        if(pursuingIsland){
            try{
                
            Thread.sleep(100);
            
        }catch(InterruptedException e){
            e.printStackTrace();
        }
            panel.shipArrivedAtIsland();
           
        }
    
    }
}

