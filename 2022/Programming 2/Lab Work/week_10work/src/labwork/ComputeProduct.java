package labwork;

import java.util.InputMismatchException; 

import java.util.Scanner; 

public class ComputeProduct { 
	
 private static Scanner scanner; 
 
 public static void product() throws InputMismatchException{
int num;
do {	 
	
	 System.out.println("Welcome to the calculator.");
	 
	 System.out.println("\nEnter a number: ");
	 System.out.println("\n1. Compute product");
	 System.out.println("\n2. Quit porgram");
	 
	 num = scanner.nextInt();
	 if(num == 1) {	
try { 
  
  System.out.println("Enter first number:"); 
  
  int value1 = scanner.nextInt(); 
  
  System.out.println("Enter second number:"); 
  
  int value2 = scanner.nextInt(); 
  
  System.out.println("Product is: "+(value1*value2));
  
 
	}
catch(InputMismatchException e){
	
	System.err.println("Catch: input mismatch!");
	scanner.next();//flush input
	
	
	}
}else if(num == 2) {
	System.out.println("Goodbye!");
}
}while(num != 2);
 } 
 public static void main(String args[]){ 
	 
  scanner = new Scanner(System.in);   
  
  product(); 
  
 } 
} 