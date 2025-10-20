package week3_practice;

import java.util.Scanner;

public class Account {
	
	String name;
	int age;
	

	public static void main(String[] args) {
		
		Account a = new Account ();
		
		Scanner scan = new Scanner(System.in);
		String person1;
		int age = 0;
		
		
		System.out.println("Name: ");
		person1 = scan.next();
		
		System.out.println("Age:");
		age = scan.nextInt();
		
		a.setName(person1);
		a.setAge(age);
		a.printDetails();
		
		scan.close();
	}
	
	public void setName(String name)
	{	
		this.name = name;
	}
	
	public void setAge (int age)
	{
		this.age = age;
		
	}
	public String getName() {
		
		return this.name;
	}
	public Integer getAge() {
		
		return this.age;
	}
	
	
	public void printDetails() {
		
		System.out.println(getName() + "," + getAge());
		
	}
	
}
