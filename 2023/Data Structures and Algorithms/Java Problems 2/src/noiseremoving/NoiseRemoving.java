/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package noiseremoving;

import javax.swing.SwingUtilities;

/**
 *
 * @author Hayden Richard-Marsters 21152003
 */
public class NoiseRemoving {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
      
        SwingUtilities.invokeLater(new Runnable(){ //initialise and run GUI
            public void run(){
                GUI gui = new GUI(); //Create GUI instance
                gui.setVisible(true);
            }
        });
    }
    
}
