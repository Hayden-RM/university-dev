package w2_exercises;
/*
 * 
 * course code: COMP503 stream: 50
 * Name: Hayden Richard-Marsters
 * student ID: 21152003
 * 
 * 
 */
import java.util.Scanner;

public class Student {
	
	String firstname = "UNKNOWN";
	String lastname = "UNKNOWN";
	String studentID = "?";

	Student(String fn, String ln, String sid)
	{
		this.firstname = fn;
		this.lastname = ln;
		this.studentID = sid;
		
	}

	public static void main(String[] args) 
	{
	
		Scanner scan =  new Scanner(System.in);
		
		System.out.println("Please enter student's first name: ");
		String firstname = scan.next();
		
		System.out.println("PLease enter student's last name: ");
		String lastname = scan.next();
		
		System.out.println("please enter student's ID ");
		String studentID = scan.next();
		
		Student s1 = new Student(firstname, lastname, studentID);
		
		scan.close();
		
		System.out.println("Calling 3 parameter constructor: ");
		System.out.println("Student's first name: " +s1.firstname +" Student's last name: " +s1.lastname +"Student's ID: "+ s1.studentID);
		
		System.out.println("Calling 2 parameter constructor: ");
		System.out.println("Student's first name: " +s1.firstname +" Student's last name: " +s1.lastname +" Student's ID: ?");
		
		System.out.println("Calling 0 parameter constructor: ");
		System.out.println("Student's first name: UNKNOWN" +" Student's last name: UNKNOWN" +"Student's ID: ?");
		
		
	}

}
