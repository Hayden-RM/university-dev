/*
 * name: Hayden Richard-Marsters
 * stream: 52
 * student ID: 2550552
*/

package actual_labwork;

public class Dog {
	String name;
	int age;
	

	public static void main(String[] args) {
		
		Dog d1 = new Dog();
		d1.setName("Jimmy");
		d1.setAge(9);
		System.out.println(d1.toString());
		System.out.println(d1.getName() + " is " + d1.inPersonYears() + " in human years");
		
	}
	
	public void setName(String name)
	{
		this.name = name;
		
	}
	public void setAge(int age)
	{
		this.age = age;
	}
	public String getName()
	{
		return this.name;
		
	}
	public int getAge()
	{
		return this.age;
		
	}
	public int inPersonYears() {
		
		return age*7;
		
	}
	public String toString()
	{
		return name + " is " + age + " years old";
	}
	
	
	
}
