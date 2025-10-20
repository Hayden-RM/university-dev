/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package noiseremoving;

/**
 *
 * @author Hayden Richard-Marsters 21152003
 */

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
    
    private BufferedImage im;
   
    public ImagePanel(){
       super();
    }
    
    public  void setImage(BufferedImage image){
        this.im = image; //set image to display 
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g){ //Paint image to scaled size
        super.paintComponent(g);
        
        if(im != null){
           g.drawImage(im, 0, 0, this.getWidth(), this.getHeight(), null); 
        }
    }
}
