/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Question_2;

/**
 *
 * @author Hayden 21152003
 */

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {
    
    int boardWidth; 
    int boardHeight;
    Cell snakeHead; 
    Cell letterFood; 
    
    List<Cell> enemyCells; 
    
    int CELL_SIZE = 25; 
    Timer gameLoop; 
    int velX = 0;
    int velY = 0;
    LinkedList<Cell> snakeBody;
    boolean  snakeBodyConsumed = false; 
    boolean gameOver = false;
    
    public SnakeGame(int boardWidth, int boardHeight){
        
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth; 
        
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.BLACK);
        
        //snake head cell 
        snakeHead = new Cell(5,5);
        snakeHead.setType(Type.SNAKE);
        snakeHead.setData('@');
        
        //letter food cell
        letterFood = new Cell(10,10);
        letterFood.setType(Type.FOOD);
        letterFood.setData(computeCharFood());
        
        createEnemies();
       
        //game runner 
        gameLoop = new Timer(120, this); //change speed of snake game // lower = slower
        gameLoop.start();
        
        snakeBody = new LinkedList();
        
        addKeyListener(this);
        setFocusable(true);
        
        
    }
    
    public void createEnemies(){
        
        enemyCells = new ArrayList();
        Random rand = new Random();
        
        int fx = 10;
        int fy = 10; 
        
        
        for(int i = 0; i < 10; i++){
            
            int enX, enY;
            
            do{
                enX = rand.nextInt(boardWidth / CELL_SIZE);
                enY = rand.nextInt(boardHeight / CELL_SIZE);
            }while(enX == fx && enY == fy);
            
            Cell snakeEnemy = new Cell(enX,enY);
            snakeEnemy.setType(Type.ENEMY);
            snakeEnemy.setEnData(randInt());
            
            enemyCells.add(snakeEnemy);
            
        }
    }
    public boolean isOccupied(Cell enemyCell){
        
        return letterFood.getX() == enemyCell.getX() && letterFood.getY() == enemyCell.getY();
    }
    
    public boolean collision(Cell c1, Cell c2){
        return c1.getX() == c2.getX() && c1.getY() == c2.getY();
                
    }
    
    @Override
    public void paintComponent(Graphics g){
       
        super.paintComponent(g);
        drawBoard(g);
        drawSnakeHead(g, snakeHead);
        drawFood(g, letterFood);
        drawSnakeBody(g);
        drawEnemies(g);

    }
    //compute random char for food
    public char computeCharFood(){
        char charac; 
        String charSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        Random random = new Random();
        
        int randIndex = random.nextInt(charSet.length());
        charac = charSet.charAt(randIndex);
 
        return charac;
    }
    
    //compute random on grid
   
    public int randInt(){
        
      Random rand = new Random();
      int randInt = rand.nextInt(9)+1; 
      
      return randInt; 
    }
    
    public void setFood(){
        Random r = new Random();
        int foodX, foodY;
        
        do{
            foodX = r.nextInt(boardWidth / CELL_SIZE);
            foodY = r.nextInt(boardHeight / CELL_SIZE);
        }while(isOccupiedByEnemy(foodX, foodY));
        
        letterFood.setX(foodX);
        letterFood.setY(foodY);
        letterFood.setData(computeCharFood());
    }
    
    public boolean isOccupiedByEnemy(int x, int y){
        for(Cell cell  : enemyCells){
            
            if(cell.getX() == x && cell.getY() == y){
                return true; 
            }
            
        }
        return false; 
    }
    
    
    public void drawSnakeBody(Graphics g){
      
        Node<Cell> current = snakeBody.getHead();
       
        while(current != null){
            Cell snakeBodyPart = current.getData();
            char snakeBodyData = snakeBodyPart.getData();
           g.setColor(Color.green);
               
            int cellCenterX = (snakeBodyPart.getX() * CELL_SIZE) + (CELL_SIZE / 2);
            int cellCenterY = (snakeBodyPart.getY() * CELL_SIZE) + (CELL_SIZE / 2);

            
            FontMetrics fm = g.getFontMetrics();
            int charWidth = fm.stringWidth(String.valueOf(snakeBodyData));
            int charHeight = fm.getHeight();

            int x = cellCenterX - (charWidth / 2);
            int y = cellCenterY + (charHeight / 2); 

            String cellData = String.valueOf(snakeBodyData);
            g.drawString(cellData, x, y);

            current = current.next;
        }
        
    }
    
    
    public void drawSnakeHead(Graphics g, Cell cell){
        
      //draw snake 
        if(cell != null){
            if(cell.getType() == Type.SNAKE){

                g.setColor(Color.green);
               
            int cellCenterX = (cell.getX() * CELL_SIZE) + (CELL_SIZE / 2);
            int cellCenterY = (cell.getY() * CELL_SIZE) + (CELL_SIZE / 2);

            
            FontMetrics fm = g.getFontMetrics();
            int charWidth = fm.stringWidth(String.valueOf(cell.getData()));
            int charHeight = fm.getHeight();

            int x = cellCenterX - (charWidth / 2);
            int y = cellCenterY + (charHeight / 2); 

            String cellData = String.valueOf(cell.getData());
            g.drawString(cellData, x, y);

            }
        }
       
    }
    
    public void drawBoard(Graphics g){
        
       // draw grid - use grid lines if needed
       /* for(int i = 0; i < boardWidth/CELL_SIZE; i++){
            g.setColor(Color.DARK_GRAY);
            g.drawLine(i*CELL_SIZE, 0, i*CELL_SIZE, boardHeight);
            g.drawLine(0, i*CELL_SIZE, boardWidth, i*CELL_SIZE);
        }
        
        */
    }
    
    public void drawFood(Graphics g, Cell cell){
    
        if(cell != null){
            if(cell.getType() == Type.FOOD){
                g.setColor(Color.green);
               
            int cellCenterX = (cell.getX() * CELL_SIZE) + (CELL_SIZE / 2);
            int cellCenterY = (cell.getY() * CELL_SIZE) + (CELL_SIZE / 2);

            
            FontMetrics fm = g.getFontMetrics();
            int charWidth = fm.stringWidth(String.valueOf(cell.getData()));
            int charHeight = fm.getHeight();

            int x = cellCenterX - (charWidth / 2);
            int y = cellCenterY + (charHeight / 2); 

            String cellData = String.valueOf(cell.getData());
            g.drawString(cellData, x, y);
            
            }
        }
        
    }  
    
    public void drawEnemies(Graphics g){
        for(Cell cell : enemyCells){
         
            if(cell != null){
            if(cell.getType() == Type.ENEMY){
                g.setColor(Color.red);
               
            int cellCenterX = (cell.getX() * CELL_SIZE) + (CELL_SIZE / 2);
            int cellCenterY = (cell.getY() * CELL_SIZE) + (CELL_SIZE / 2);

            
            FontMetrics fm = g.getFontMetrics();
            int charWidth = fm.stringWidth(String.valueOf(cell.getData()));
            int charHeight = fm.getHeight();

            int x = cellCenterX - (charWidth / 2);
            int y = cellCenterY + (charHeight / 2); 

            String cellData = String.valueOf(cell.getEnData());
            g.drawString(cellData, x, y);
            
            }
        }
        
        
        }
    
    }  
    public void move(){
        //collision with letter
        if(collision(snakeHead, letterFood) && letterFood.getType() == Type.FOOD){
           
           Cell letterEaten = new Cell(letterFood.getX(), letterFood.getY());
           letterEaten.setData(letterFood.getData());
           letterEaten.setType(Type.SNAKE);
           snakeBody.addInOrder(letterEaten);
           setFood();
           snakeBodyConsumed = true; 
            System.out.println("< Letter consumed: " + letterEaten.toString()+" >");
            System.out.println("New Snake Composition: ");
            snakeBody.getStringRep();
     
        }
        //collision with num
        for(int i = enemyCells.size() -1; i >= 0; i--){
            Cell snakeEnemy = enemyCells.get(i);
        
        if(collision(snakeHead, snakeEnemy) && snakeEnemy.getType() == Type.ENEMY){
           
            if(snakeEnemy.getEnData() >= snakeBody.size){
               snakeBody.removeFromTail();
               System.out.println("< Removed from tail > ");
                System.out.println("New Snake Composition: ->");
                snakeBody.getStringRep();
               
           }else if(snakeEnemy.getEnData() >= 0 && snakeEnemy.getEnData() < snakeBody.size){
               
               snakeBody.remove(snakeEnemy.getEnData());
               System.out.println("< Removed at index: " + +snakeEnemy.getEnData() +" "+ snakeEnemy.toString() +" >"); 
               snakeBody.getStringRep();
               snakeBodyConsumed = true; 
               
               
           }
           
           Random rand = new Random();
           
           if(!isOccupied(snakeEnemy)){
           snakeEnemy.setX(rand.nextInt(boardWidth/CELL_SIZE));
           snakeEnemy.setY(rand.nextInt(boardWidth/CELL_SIZE));
           snakeEnemy.setEnData(randInt());
           }
        }
        } 
        for(int i = snakeBody.size -1; i >= 0; i--){
            
                Cell snakePart = snakeBody.get(i);
                if(i == 0){
                snakePart.setX(snakeHead.getX());
                snakePart.setY(snakeHead.getY());
        }else{
               Cell prevSnakePart = snakeBody.get(i-1);
               snakePart.setX(prevSnakePart.getX());
               snakePart.setY(prevSnakePart.getY());
            }
        
        }
        
       
        
        int xMov = snakeHead.getX() + velX;
        int yMov = snakeHead.getY() + velY;
        
        snakeHead.setY(yMov);
        snakeHead.setX(xMov);
       
        
    
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        move();
        repaint();
        if(collisionWithSelfOrBoundries()){
            gameOver = true; 
            gameLoop.stop();
            System.err.println("< GAME OVER > --> YOU HIT THE BOUNDARIES");
            showGameOverDialogue("Game over... You hit the boundaries!");
        }
       if(emptySnake() && snakeBodyConsumed){
            gameOver = true; 
            gameLoop.stop();
            System.err.println("< GAME OVER > --> NO MORE SNAKE LEFT");
            showGameOverDialogue("Game over... No more snake left!");
        }

    }
    
    public void showGameOverDialogue(String reason){
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        GameOverMessage god = new GameOverMessage(frame, reason);
        god.setVisible(true);
        System.exit(0);
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {}
    
    @Override
    public void keyReleased(KeyEvent ke) {}
     
    @Override
    public void keyPressed(KeyEvent ke) {
   
        if(ke.getKeyCode() == KeyEvent.VK_UP && velY!=1||ke.getKeyCode() ==  KeyEvent.VK_W && velY != 1){
       
        System.out.println(ke.getKeyCode()+" is pressed (UP)");
        velX = 0;
        velY = -1; 
         
    }else if(ke.getKeyCode() == KeyEvent.VK_DOWN && velY!=-1 || ke.getKeyCode() == KeyEvent.VK_S && velY != -1){
        
        System.out.println(ke.getKeyCode()+" is pressed (DOWN)");
        velX = 0;
        velY = 1;
        
    }else if(ke.getKeyCode() == KeyEvent.VK_LEFT && velX != 1|| ke.getKeyCode() == KeyEvent.VK_A && velX != 1){
       
        System.out.println(ke.getKeyCode()+" is pressed (LEFT)");
        velX = -1;
        velY = 0; 
        
    }else if(ke.getKeyCode() == KeyEvent.VK_RIGHT && velX != -1|| ke.getKeyCode() == KeyEvent.VK_D && velX != -1){
       
        System.out.println(ke.getKeyCode()+" is pressed (RIGHT)");
        velX = 1;
        velY = 0; 
    }
        
 }   
    
    public boolean collisionWithSelfOrBoundries(){
        
        if(snakeHead.getX() < 0 || snakeHead.getX() >= boardWidth / CELL_SIZE ||
                snakeHead.getY() < 0 || snakeHead.getY() >= boardHeight / CELL_SIZE){
            return true; 
        }
        
        for(int i = 1; i < snakeBody.size; i++){
            if(collision(snakeHead, snakeBody.get(i))){
                return true; 
            }
        }
        return false; 
        
    }
    
    public boolean emptySnake(){
        
        if(snakeBody.size == 0){
            return true; 
        }
        return false; 
    }
               
}
