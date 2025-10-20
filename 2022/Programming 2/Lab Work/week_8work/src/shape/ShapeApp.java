package shape;

import java.util.Scanner;

public class ShapeApp {

	public static void main(String[] args) {

		Circle c1 = new Circle(3.45);
		Rectangle r1 = new Rectangle(4.53, 6.7);
		
		int input = 0;
		Scanner scan = new Scanner(System.in);
		
		do {
			
			System.out.println("1. Create a Circle object");
			System.out.println("2. Create a Rectangle object");
			System.out.println("3. Stop");
			
			input = scan.nextInt();
			
			if(input == 1) {
				System.out.println("The area of the circle is "+c1.computeArea()+"\n");
			}else if(input == 2) {
				System.out.println("The Area of the rectangle is "+r1.computeArea()+"\n");
			}else if(input == 3) {
				System.err.println("Quitting the program");
			}else {
				System.err.println("Invalid input!");
			}
			
			
			
		}while(input != 3);
		
		scan.close();
		
	}

}
