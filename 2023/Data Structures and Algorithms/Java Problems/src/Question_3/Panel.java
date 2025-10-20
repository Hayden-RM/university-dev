/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question_3;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

/**
 *
 * @author Hayden 21152003
 * 
 */
public class Panel extends JPanel implements KeyListener{
    
    int number_ship = 20;
    boolean program_starts = false;
    Ship[] ships = new Ship[number_ship];
    Port port;
    
    Image ship_image;
    Image island_image;
    Image boat_island_image;
    private SimulationMode activeMode;
    private boolean isPortOccupied = false; 
    private int safeShipArrivalCounter = 0;
    private int crashCounter = 0; 
    
    public Panel() {
        
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setBackground(Color.WHITE);
        port = new Port(900, 500);
        this.activeMode = selectSimulationMode();
        
        ships = new Ship[number_ship];
        for(int i = 0; i < number_ship; i++)
        {

            ships[i] = new Ship(20, i*50, port, activeMode, this); 
        }
        ship_image = new ImageIcon("boat.png").getImage();
        island_image = new ImageIcon("land.png").getImage();
        boat_island_image = new ImageIcon("boat_land.png").getImage();
      
    
    }
    
    public void shipArrivedAtIsland(){
        isPortOccupied = true; 
        repaint();
        System.out.println("Ship arrived at port");
        safeShipArrivalCounter++; 
           try {
               Thread.sleep(1000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           
         resetIslandImage();
         System.out.println("Sending next ship to port");
         
         if(safeShipArrivalCounter == number_ship){
             deliverStats();
             deliverStatsGUI();
         }
     
    }
    
    public boolean isPortOccupied(){
        return isPortOccupied; 
    }
    
    
    public void resetIslandImage(){
        
       System.out.println("Ship departed after (1 second)");
       isPortOccupied = false; 
       repaint();
    }
   
    
    public void switchMode(){
        activeMode = (activeMode == SimulationMode.SYNCHRONISED) ? SimulationMode.UNSYNCHRONISED : SimulationMode.SYNCHRONISED;
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        port.draw(g);
        
        g.setFont(new Font("Monospaced", Font.BOLD, 20));
        g.drawImage(island_image, port.x, port.y, this);
        
        for(int i = 0; i < ships.length; i++)
        {
            if(ships[i].isAtIsland()){
               if(isPortOccupied()){
                g.drawImage(boat_island_image, port.x, port.y, this);
                
                }
            }else{
                g.drawImage(ship_image, ships[i].x,ships[i].y, this);
            }
            }
               
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        
        System.out.println("\""+ke.getKeyChar()+"\" is typed.");
        
        if(ke.getKeyChar() == ' ' && !program_starts){
            program_starts = true; 
            for(int i = 0; i < ships.length; i++){
                if(!ships[i].isAlive()){
                System.out.println("Starting ship " + i);
                ships[i].start();
                }else{
                    System.out.println("Ship " + i + " is already running");
                }
            }
        }
    }
    
    public void deliverStats(){
        System.out.println(safeShipArrivalCounter + " ships made it safely to port");
        System.out.println(crashCounter + " ships crashed");
    }
    public void deliverStatsGUI(){
     
     JOptionPane.showConfirmDialog(this, safeShipArrivalCounter + " ships made it to port safely" + "\n" + crashCounter + " ships crashed", "Port Statistics", JOptionPane.OK_CANCEL_OPTION);
        
      System.exit(0);
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {}

    @Override
    public void keyReleased(KeyEvent ke) {}
    
   public SimulationMode selectSimulationMode() {
    String[] options = { "Synchronized Mode", "Unsynchronized Mode" };
    int selectedOption = JOptionPane.showOptionDialog(
        this,
        "Select Simulation Mode ->\nOnce selected, press SPACE to start sim",
        "Simulation Mode Selection",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        options,
        options[0]
    );

    if (selectedOption == 0) {
        
         return SimulationMode.SYNCHRONISED;
    } else if (selectedOption == 1) {
        
        return SimulationMode.UNSYNCHRONISED;
    }
    
    return null; 
   }

    
}
