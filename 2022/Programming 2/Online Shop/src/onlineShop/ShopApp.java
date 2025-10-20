/***

 * 

 * Student Name: Hayden Richard-Marsters

 * Student ID: 21152003

 * Course Code: COMP503 

 * Assessment Item: Assessment A

 * 
 **/


package onlineShop;

import java.util.Scanner; //access scanner function

public class ShopApp {
	

	private static OnlineShop app = new OnlineShop(); //instantiate new OnlineShop

	public static void main(String[] args) {
	
		
		Product item; //declare variable of Product type
		
		Scanner scan = new Scanner(System.in); //create scanner for user input
		
		char userInputOption; //character user inputs to select function
		 
	
		
		//products for my shop 
		 item = new Product("wallet", "Ralph Lauren", 400.00); //initialize first product , assigning string modelName, String ManufName and retail price
		app.add(item); //using dot operator to access the add method within OnlineShop class passing in 'item' as the parameter
		
		 item = new Product("iPhone 12 pro", "Apple", 1600.00);
		app.add(item);
		

		 item = new Product("JBL link speaker", "JBL", 320.00);
		app.add(item);
		

		 item = new Product("Macbook pro", "Apple", 3400.00);
		app.add(item);
		

		 item = new Product("Terminator", "Arnold", 29000.00);
		app.add(item);
		
		
		
		do {//execute following code until the user input is 'c' 
			//menu interface:
		System.out.println("select an option:\n");
		System.out.println("A. Display inventory\n");
		System.out.println("B. Rate Product\n");
		System.out.println("C. Exit");
		
		userInputOption = scan.next().toUpperCase().charAt(0); //takes in a character; can accept both 'A' and 'a' as it autocapitalizes
		
		if(userInputOption == 'A') {//execute the following if user input is 'A' or 'a'
			System.out.println(app.toString() + "\n");//print string representation by accessing toString class
		}
		else if(userInputOption == 'B') {// if input is 'b'
			
			rateProduct(scan);//rateProduct method is accessed passing in scan of scanner type 
		}
		else if(userInputOption == 'C'){
			System.out.println("Terminating progam!");//user prompt 
		}
		
	}while(userInputOption != 'C');//terminate program
		
		scan.close();
		
		
	}//main end
	
	
	private static void rateProduct(Scanner scan) {//rateProduct method taking in user input
		
		int prodNum;
		double newRating = 0.0;//declare set default value 
		
		System.out.println("\n" + app + "\ntype item code (1-5)");//user prompt 
		
		prodNum =  scan.nextInt() - 1; //scans input and saves to prodNum
		
		if(prodNum > 0 && prodNum <= 5) {//validates input
			
			System.out.println(app.getProduct(prodNum));//prints singular product taking in prodNum user input to correlate to product using getProduct method from OnlineShopping class 
		}else {
			
			System.out.println("\nenter a valid product code");//user prompt 
		}
		
		System.out.println("\nenter a rating (0.0 - 5.0)");//asks for rating by the user
		
		newRating = scan.nextDouble(); //saves double value to newRating
		
		if(newRating >= 0.0 && newRating <= 5.0) {
			
			app.getProduct(prodNum).rateReliability(newRating);	//passes correlating product from the app to rateReliability method passing in the newRating value input by user 
			
			System.out.println("\nupdate complete! ");
			System.out.println(app.getProduct(prodNum));//prints new product details
			
		}else {
			
			System.out.println("\nenter a valid rating from 0.0 to 5.0");//validates input
		}
		
	}

}
