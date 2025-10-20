package labwork_week1;

import java.util.Scanner;

public class Message {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		
		System.out.println("What is the message?");
		
		String message = input.nextLine();
		
		System.out.println("The message is " +message);
		
		
	}

}
