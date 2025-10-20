package week9LabWork;

import java.util.ArrayList;

public class StudentList {

	ArrayList<Student> Students;
	
	public StudentList() {
		
		this.Students = new ArrayList<Student>();
		
		
	}
	
	public void addStudent(Student student) {
		
		this.Students.add(student);
		
	}
	
	public String toString(){
		
	String str = " ";
	
	for(int i = 0; i < Students.size(); i++) {
	str += Students.get(i);
	str += "\n ";	

	}
		return str;
	
	}
	
	public boolean contains(Student s1) {
		
		boolean tr = false;
		
		if(Students.contains(s1)) {
		tr = true;
		}
		return tr;
	}
	
	
	
}
