/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Question_2;

/**
 *
 * @author Hayden 21152003
 */

import javax.swing.*;
import java.awt.*;


public class GameOverMessage extends JDialog {
    public GameOverMessage(JFrame parent, String reason){
        super(parent, "Game Over", true);
        setSize(300,100);
        setLocationRelativeTo(parent);
        
        JLabel label = new JLabel(reason, SwingConstants.CENTER);
        JPanel panel = new JPanel();
        
        panel.setLayout(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);
        
        add(panel);
        
    }
}
