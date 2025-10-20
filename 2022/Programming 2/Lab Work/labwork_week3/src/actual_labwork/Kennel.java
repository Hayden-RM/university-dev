/*
 * name: Hayden Richard-Marsters
 * stream: 52
 * student ID: 2550552
*/
package actual_labwork;

public class Kennel {

	public static void main(String[] args) {
		Dog d2 = new Dog();
		Dog d3 = new Dog();
		Dog d4 = new Dog();
	
		d2.setAge(2);
		d3.setAge(7);
		d4.setAge(78);
		
		System.out.println(d2.inPersonYears());
		System.out.println(d3.inPersonYears());
		System.out.println(d4.inPersonYears());
	}

}
