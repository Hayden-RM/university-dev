package shape;

public class Circle extends Shape{

	double radius;
	
	public Circle(double radius) {
		this.radius = radius; 
	}
	
	@Override
	public double computeArea() {
		
		return Math.PI*(radius*radius);
	}
	
	
	
	
	
	
}
