/*
 * name: Hayden Richard-Marsters
 * stream: 52
 * student ID: 2550552
*/

package actual_labwork;

import java.util.Scanner;

public class Box {
	
	double height;
	double width;
	double depth;

	public static void main(String[] args) {
	
		Box b1 = new Box();
		
		Scanner scan = new Scanner(System.in);
		
		b1.width = scan.nextDouble();
		b1.height = scan.nextDouble();
		b1.depth = scan.nextDouble();
		
		System.out.println(b1.toString());
	
		scan.close();
		
	}
	
	public void setHeight(double height) {
		
		this.height = height;
	}
	public void setWidth(double width) {
		
		this.width = width;
	}
	public void setDepth(double depth) {
		
		this.depth = depth;
	}
	public double getHeight() {
		
		return this.height;
		
	}
	public double getWidth() {
		
		return this.width;
		
	}
	public double getDepth() {
		
		return this.depth;
		
	}
	public String toString()
	{
		return "depth: " + depth + "\nheight: " + height +"\nwidth: " + width;
		
	}

	
	

}
