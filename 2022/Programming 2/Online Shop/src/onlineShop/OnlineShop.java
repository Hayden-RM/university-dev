/***

 * 

 * Student Name: Hayden Richard-Marsters

 * Student ID: 21152003

 * Course Code: COMP503 

 * Assessment Item: Assessment A

 * 
 **/


package onlineShop;

public class OnlineShop {
	
	//create instance variables for OnlineShop attributes 
	private Product[]inventory; //create a primitive array of objects of Product type
	
	private int nProduct; //declare instance variable that holds the total number of products 
	
	
	public OnlineShop()
	{
		inventory = new Product[5]; //instantiating object array called inventory of product objects 
		nProduct = 0; //giving nProduct a default value
	}
	
	public String toString()//returns a string enumerating its inventory contents
	{
		String string = "";
		int count = 1; 
		
		for(Product singleProductOfArray : inventory) { //for each loop iterates over all objects returned by inventory array
			string += (count++) + " " + singleProductOfArray + "\n";
			
		}
		return string;
		
	}
	
	public Product getProduct(int productCode) //getProduct method passing in an int value of a product code to fetch the corresponding object in array
	{
		if(productCode > 0 - 1 && productCode <= 5){
		return inventory[productCode];
		}
		else{
			System.out.println("invalid product code!"); //validates for valid input i.e items exist from 1-5
			return null;
		}
		
	}
	public void add(Product productObject){//add method sets input Product objects to inventory array
		if (nProduct < 5) {
			inventory[nProduct++] = productObject; //nProduct is incremented to give total number of Objects added
		}else { //validates for valid input
			System.out.println("Inventory only holds upto 5 products! ");
		}
	}
	
	
	
	
}
