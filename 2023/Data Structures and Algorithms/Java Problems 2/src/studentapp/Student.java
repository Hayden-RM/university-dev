/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentapp;


/**
 *
 * @author Hayden Richard-Marsters 21152003
 */
public class Student {
    
    public String name;
    public Float score;
    public String comments;
    
    public Student(Float score, String name, String comments){
        this.name = name; 
        this.score = score;
        this.comments = comments; 
    }
    
    public String toString() // return string representation of a students details.
    {
        return name + "\nScore: " + score + "\n" + comments ;
    }
    
    
}
