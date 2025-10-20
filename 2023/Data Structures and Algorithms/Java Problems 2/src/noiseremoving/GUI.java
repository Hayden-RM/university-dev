/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package noiseremoving;

/**
 *
 * @author Hayden Richard-Marsters 21152003
 */

import javax.swing.*;
import java.awt.event.*;
import java.io.File;


public class GUI extends JFrame{
    
    private JButton openButton;
    private JButton doButton;
    private JFileChooser fc; 
    private ImagePanel imPanel;
    private ImageProcess imProcess;
    
    public GUI(){
        
        //Main frame
        setTitle("Noise remover");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,600);
        
        //initialise GUI components
        openButton = new JButton("Open image");
        doButton = new JButton("Process and Save");
        fc = new JFileChooser(".");
        imPanel = new ImagePanel();
        imProcess = null; 
        
    openButton.addActionListener(new ActionListener(){ //ActionListener for Open image button - retrieving image function
        
            public void actionPerformed(ActionEvent e){
                int returnVal = fc.showOpenDialog(GUI.this);

                if(returnVal == JFileChooser.APPROVE_OPTION){
                    File file = fc.getSelectedFile();
                    imProcess = new ImageProcess(file.getPath());
                    imPanel.setImage(imProcess.buffered_image);
                }

                }
        });

    doButton.addActionListener(new ActionListener(){ //ActionListener for process button - NoiseRemover function 
        public void actionPerformed(ActionEvent e){
            if(imProcess != null){
                imProcess.cleanNoise();
                imProcess.save("noise_removed.jpg");
                JOptionPane.showMessageDialog(GUI.this, "Noise removed - Saved as: noise_removed_jpg.");
                System.out.println("Noise removed - Saved as: noise_removed_jpg.");
                    }
                }
       });

        //Add buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openButton);
        buttonPanel.add(doButton);

        getContentPane().add(buttonPanel, "North");
        getContentPane().add(imPanel);

        setLocationRelativeTo(null);
        setVisible(true);

 }

}
