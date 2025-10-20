package labwork;

public class Cat extends Animal{

	public void feed() {
		this.eat+=1;
		this.happy+=10;
	}
	
	public Cat() {
		System.out.println("Cat Object instantiated");
		this.happy+=10;
		
	}
	public String toString() {
		return "Cat has"+ super.toString();
	}
}









