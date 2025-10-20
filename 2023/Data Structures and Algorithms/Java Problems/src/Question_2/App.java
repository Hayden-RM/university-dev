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

public class App {
    
    public static void main(String[] args) throws Exception {
        
        System.out.println("--<||  G A M E   S T A R T I N G  ||>-");
        System.out.println("<------------~Tracking~------------->");
        System.out.println("<-Snake Eating and Body consumption->");
        System.out.println("<--Snake Composition AFTER action--->");
        System.out.println("<------------Key Pressed------------>");
        System.out.println("<---------Cell Types & Data--------->");
        int boardWidth = 600;
        int boardHeight = boardWidth;
        
        JFrame frame = new JFrame("Snake Game");
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        SnakeGame sg = new SnakeGame(boardWidth, boardHeight);
        frame.add(sg);
        frame.pack();
        
        sg.requestFocus();
       
    }
  
    
}
