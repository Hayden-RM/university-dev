package w2_exercises;

import java.util.Scanner;

public class Book_class {

	String title; 
	String author;
	int numberOfPages;
	

public static void main(String[] args) {
	
	
	Book_class UserBook;
	UserBook = new Book_class();
	
	Scanner scan = new Scanner(System.in);
	

	
	System.out.println("Please enter the title of the book");
	UserBook.title = scan.next();
	
	System.out.println("PLease enter the author of the book");
	UserBook.author = scan.next();
	
	System.out.println("Please enter the number of pages");
	UserBook.numberOfPages = scan.nextInt();
	
	System.out.println("The title of the book is: "+ UserBook.title);
	
	System.out.println("the book author is: " + UserBook.author);
	
	System.out.println("the book has " + UserBook.numberOfPages + " pages");
	
	scan.close();;
		
	}
}
