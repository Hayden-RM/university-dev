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

public class Person {

	int age;
	double weight;
	boolean student;
	char gender;
	
	Person(int age, double weight, boolean condition, char gender)
	{
		this.age = age;
		this.weight = weight;
		this.student = condition;
		this.gender = gender;
		
		
	}
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("PLease enter the person's age: ");
		int age = scan.nextInt();
		
		System.out.println("Please enter the person's weight: ");
		double  weight = scan.nextDouble();
		
		System.out.println("Is the person a student (true/false");
		boolean student = scan.nextBoolean();
		
		System.out.println("please enter the person's gender: (M/F)");
		char gender = scan.next().charAt(0);
		
		Person p1 = new Person(age, weight, student,  gender);
		
		System.out.println("Person: information  age:" +p1.age +" weight: "+p1.weight+" student: "+p1.student+" gender: "+p1.gender);
		
		scan.close();
		
	
	}

}
