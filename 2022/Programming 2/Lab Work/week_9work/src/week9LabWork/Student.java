package week9LabWork;

import java.util.Objects;

public class Student {

	String name; 
	int studentID;
	
	public Student(String name, int ID) {
		this.name = name;
		this.studentID = ID;
	}
	
	
	public String toString() {
		
		return this.name + " ID: " + this.studentID;
		
	}
	

	public boolean isEqual(Student student) {
		
		boolean eq = false; 
		if(this.studentID == student.studentID) {
			eq = true;
		}
		return eq;
	}
	
	
	
	
	
}
