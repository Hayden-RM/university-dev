package labwork_week1;

import java.util.Scanner;

public class ThreeNuberAverage {

	public static void main(String[] args) {
	
		int Number1 = 0;
		int Number2 = 0;
		int Number3 = 0;

		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter the first number");
		Number1 = input.nextInt();
		
		System.out.println("Enter the second number");
		Number2 = input.nextInt();

		System.out.println("Enter the third number");
		Number3 = input.nextInt();
		
		float average = (Number1 + Number2 + Number3) / 3;
		
		System.out.println("The average of the numbers is: " + average);
		
		
		
	}

}
