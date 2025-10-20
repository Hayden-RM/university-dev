package labwork_week1;

import java.util.Scanner;

public class PersonalData {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		
		System.out.println("Name: ");
		String Name = input.nextLine();
		
		
		System.out.println("Birthday: ");
		String Birthday = input.nextLine();
		
		System.out.println("Interests: ");
		String Interests = input.nextLine();
		
		System.out.println("Favourite book: ");
		String Book = input.nextLine();
		
		System.out.println("Favourite film: ");
		String Film = input.nextLine();
		
		System.out.println("Name: " + Name);

		System.out.println("Birthday: "+ Birthday);
		
		System.out.println("Interests: "+ Interests);
		
		System.out.println("Favourite book: " + Book);
		
		System.out.println("Favourite film: " + Film);


	}

}
