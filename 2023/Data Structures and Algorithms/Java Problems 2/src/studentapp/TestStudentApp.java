/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentapp;

import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Hayden Richard-Marsters 21152003
 */
public class TestStudentApp {

    /**
     * @param args the command line arguments
     */
    
    static String message = "";
    
    public static void main(String[] args){
        // TODO code application logic here
        int dataSize = 10;
        StudentManager studentManager = new StudentManager();
        float[] scores = new float[dataSize];
           
        int mark = 0; 
        
        int[] nameIndexList = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for(int i = 0; i < 5; i++)
        {
            int swapTo = (int)(Math.random()*(nameIndexList.length));
            int swapFrom = (int)(Math.random()*(nameIndexList.length));
            if(swapTo != swapFrom)
            {
                nameIndexList[swapFrom]+=nameIndexList[swapTo];
                nameIndexList[swapTo] = nameIndexList[swapFrom] - nameIndexList[swapTo];
                nameIndexList[swapFrom] = nameIndexList[swapFrom] - nameIndexList[swapTo];
            }
            
        }

        
        for(int i = 0; i < dataSize; i++)
        {
            float score = (float)Math.random()*100.0f;
                       
            studentManager.addStudent(score, "Student"+(nameIndexList[i]<10?"0"+nameIndexList[i]:nameIndexList[i]), "Comments "+i);
                        
            scores[i] = score;
        }
        
        System.out.println("Sorted by Score:");
                
        Student[] studentList = studentManager.getSortedStudentList(0.0f);
      
        //display the sorted student list (for your debugging)
        for(int i = 0; i < studentList.length; i++)
        {
            
            System.out.println(studentList[i]+"\n");
            
            
        }
    

        
        //testing your sort by date
        mark+=checkSortByScore(studentList);
        System.out.println("*****************************************\nCurrent mark: "+mark);
        
        
        System.out.println("\n\nSorted by Name: ");
        studentList = studentManager.getSortedStudentList(new String());
        for(int i = 0; i < studentList.length; i++)
        {
            System.out.println(studentList[i]+"\n");
        }
        mark+=checkSortByName(studentList);
        System.out.println("*****************************************\nCurrent mark: "+mark);
        
        
        System.out.print("\n\nFind by Name:");
        mark+=checkFindByName(studentManager);
        System.out.println("*****************************************\nCurrent mark: "+mark);
        
        System.out.print("\n\nFind by Name:");
        mark+=checkNotFindByName(studentManager);
        System.out.println("*****************************************\nCurrent mark: "+mark);        
        
        System.out.print("\n\nFind by Score:");
        mark+=checkFindByScore(studentManager, scores[3]);
        System.out.println("*****************************************\nCurrent mark: "+mark);
        
        System.out.print("\n\nFind by Score:");
        mark+=checkNotFindByScore(studentManager, 100.5f);
        System.out.println("*****************************************\nCurrent mark: "+mark);
   
        
        studentManager.reverseOrder();
        
        System.out.println("\n\nSorted by Score after reverse:");
        studentList = studentManager.getSortedStudentList(0.0f);
        for(int i = 0; i < studentList.length; i++)
        {
            System.out.println(studentList[i]+"\n");
        }
        mark+=checkSortByScoreAfterReverse(studentList);
        System.out.println("*****************************************\nCurrent mark: "+mark);
        
        System.out.println("\n\nSorted by Name after reverse:");
        studentList = studentManager.getSortedStudentList(new String());
        for(int i = 0; i < studentList.length; i++)
        {
            System.out.println(studentList[i]+"\n");
        }
        mark+=checkSortByNameAfterReverse(studentList);
        System.out.println("*****************************************\nCurrent mark: "+mark);
        
        System.out.print("\n\nFind by Name:");
        mark+=checkFindByName(studentManager);
        System.out.println("*****************************************\nCurrent mark: "+mark);
        
        System.out.print("\n\nFind by Name:");
        mark+=checkNotFindByName(studentManager);
        System.out.println("*****************************************\nCurrent mark: "+mark);
        
        System.out.print("\n\nFind by Score:");
        mark+=checkFindByScore(studentManager, scores[3]);
        System.out.println("*****************************************\nCurrent mark: "+mark);
        
        System.out.print("\n\nFind by Score:");
        mark+=checkNotFindByScore(studentManager, 100.5f);
        System.out.println("*****************************************\nCurrent mark: "+mark);
        
        System.out.println("\n\n***************************** Testing Report *****************************\n");
        System.out.println("Your overall mark of question 1 is "+ mark+" out of 50\n");
        System.out.println(message);
        
    }
    
    public static int checkSortByScore(Student[] list)
    {
        int mark = 0;
        for(int i = 0; i < list.length-1; i++)
        {
            if(list[i].score.compareTo(list[i+1].score)<=0)
                mark++;
        }
        
        if(mark == 9)
        {
            return 6;
        }
        else
        {
            message+="Failed: checkSortByScore\n";
            return 0;
        }
    }
    
    public static int checkSortByName(Student[] list)
    {
        int mark = 0;
        for(int i = 0; i < list.length-1; i++)
        {
            if(list[i].name.compareTo(list[i+1].name)<=0)
                mark++;
        }
        
        if(mark == 9)
        {
            return 7;
        }
        else
        {
            message+="Failed: checkSortByName\n";
            return 0;
        }
    }
    
    public static int checkSortByScoreAfterReverse(Student[] list)
    {
        int mark = 0;
        for(int i = 0; i < list.length-1; i++)
        {
            if(list[i].score.compareTo(list[i+1].score)>=0)
                mark++;
        }
        
        if(mark == 9)
        {
            return 6;
        }
        else
        {
            message+="Failed: checkSortByScoreAfterReverse\n";
            return 0;
        }
    }
    
    public static int checkSortByNameAfterReverse(Student[] list)
    {
        int mark = 0;
        for(int i = 0; i < list.length-1; i++)
        {
            if(list[i].name.compareTo(list[i+1].name)>=0)
                mark++;
        }
        
        if(mark == 9)
        {
            return 7;
        }
        else
        {
            message+="Failed: checkSortByNameAfterReverse\n";
            return 0;
        }
    }   
    
    public static int checkFindByName(StudentManager studentManager)
    {
        System.out.println(" Student 03");
        Student resultStudent = studentManager.findStudent("Student03");
        System.out.println(resultStudent);
        if(resultStudent.name.equals("Student03"))
            return 3;
        else
        {
            message+="Failed: checkFindByName (Name case: Name 03)\n";
            return 0;
        }
    }
    
    public static int checkNotFindByName(StudentManager studentManager)
    {
        System.out.println(" Student 00 (test for not found)");
        Student resultStudent = studentManager.findStudent("Student00");
        System.out.println(resultStudent);
        if(resultStudent==null)
            return 3;
        else
        {
            message+="Failed: checkNotFindByName (Name case: Student00)\n";
            return 0;
        }
    }
    
    public static int checkFindByScore(StudentManager studentManager, float score)
    {        

        Float targetScore = score;
        System.out.println(" "+targetScore);
        Student resultStudent = studentManager.findStudent(targetScore);
        System.out.println(resultStudent);
        if(resultStudent.score.equals(targetScore))
            return 3;
        else
        {
            message+="Failed: checkFindByScore (Score case: "+score+")\n";
            return 0;
        }        
    }
    
    public static int checkNotFindByScore(StudentManager studentManager, float score)
    {        

        float targetScore = score;
        System.out.println(" "+targetScore + "(test for not found)");
        Student resultStudent = studentManager.findStudent(targetScore);
        System.out.println(resultStudent);
        if(resultStudent==null)
            return 3;
        else
        {
            message+="Failed: checkNotFindByScore (Score case: "+score+")\n";
            return 0;
        }
    }
}
