package week_12Work;

public abstract class Animal {

	double weight; 
	
	public Animal() {
		
		this.weight = 1.0;
	
	}
	
	public abstract void soundsLike();
	
	
	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	
	
}
