package week9LabWork;

public class Class {

	public static void main(String[] args) {

		StudentList sL = new StudentList();
		
		sL.addStudent(new Student("jeff", 123498));
		sL.addStudent(new Student("john", 345657));
		sL.addStudent(new Student("jess", 657432));
		
		System.out.println(sL.contains(sL.Students.get(2)));
		System.out.println(sL);
	}

}
