package animal;

public class Animal {
	
	protected int eat;
	protected int happy;

	public Animal() {
		this.eat = 0;
		this.happy = 0;
		
	}
	
	public void feed() {
		
		
	}

	
	public String toString(){
		return "Eaten: "+ this.eat+" Happy: "+ this.happy;
	}
	

}
