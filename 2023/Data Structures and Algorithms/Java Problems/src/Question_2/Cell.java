/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Question_2;

/**
 *
 * @author Hayden 21152003
 */
public class Cell implements Comparable<Cell>{
    
    private int x; 
    private int y; 
    private char data; 
    private int enData;
    private Type type; 
    
    public Cell(int x, int y){
        this.y = y; 
        this.x = x; 
        
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public char getData() {
        return data;
    }

    public void setData(char data) {
        this.data = data;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getEnData() {
        return enData;
    }

    public void setEnData(int enData) {
        this.enData = enData;
    }
    
    
    
    @Override
    public int compareTo(Cell other) {
        return Character.compare(this.data, other.data);
    }
    
    @Override
    public String toString() {
        
        String cellResults = " ";
        if(this.type == Type.SNAKE){
     cellResults = "Snake body Cell [x=" + x + ", y=" + y + ", data=" + data + ", type=" + type + "]";
    }else if(this.type == Type.ENEMY){
        cellResults = "Enemy Cell [x=" + x + ", y=" + y + ", Enemy data=" + enData + "(index removal point)" + ", type=" + type + "]";
    }
    return cellResults;
    }
    
}
