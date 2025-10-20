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
import java.util.List;
import java.util.ArrayList;


public class StudentManager<E,F  extends Comparable<F>> {
    
    private BinaryTree<Student, Float> bTreeScore;
    private BinaryTree<Student, String> bTreeName; 
    
    public StudentManager(){
        bTreeScore = new BinaryTree<>();
        bTreeName = new BinaryTree<>();
    }
    
    public void addStudent(Float score, String name, String comments) {
    Student student = new Student(score, name, comments);
    
    if (student != null) {
        addToTree(student, score);
        addToTree(student, name);
    }else{
        System.out.println("null student");
    }
}

    public <F> void addToTree(Student student, F key) {
        if (student != null) {
            if (key instanceof Float) {
                bTreeScore.addElement(student, (Float) key);
            } else if (key instanceof String) {
                bTreeName.addElement(student, (String) key);
            }
        }
}

    
    public Student findStudent(E key)
    {
       if(key instanceof Float){
           return bTreeScore.searchElement((Float) key);
       }else if(key instanceof String){
           return bTreeName.searchElement((String) key);
       }
       return null; 
    }
    
    public Student[] getSortedStudentList(E key)
    {
        if(key instanceof Float){
            
           
            return studentsToArray(bTreeScore.toSortedList());
            
        }else if(key instanceof String){
            
            
            return studentsToArray(bTreeName.toSortedList());
        }
        return null; 
    }
    
    private Student[] studentsToArray(Node<Student, ?>[] nodes) {
        
        List<Student> studentList = new ArrayList<>();

        for (Node<Student, ?> node : nodes) {
            if (node != null && node.getElement() != null) {
                studentList.add(node.getElement());
            }
        }

        return studentList.toArray(new Student[0]);
}

    
    public void reverseOrder()
    {
       bTreeScore.reverseOrder();
       bTreeScore.setIsReversed(true);
       
       bTreeName.reverseOrder();
       bTreeName.setIsReversed(true);
      
    }
    
}
