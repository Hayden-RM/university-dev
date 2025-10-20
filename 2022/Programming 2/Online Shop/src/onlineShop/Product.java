/***

 * 

 * Student Name: Hayden Richard-Marsters

 * Student ID: 21152003

 * Course Code: COMP503 

 * Assessment Item: Assessment A

 * 
 **/

package onlineShop;

public class Product {
	//declare instance variables for Product class
	
	private String modelName;
	
	private String manufName;
	
	private double retailPrice = 0.0;
	
	private double aggregatedReliabilityRating = 0.0;
	
	private int numberOfConsumerRated = 0;
	
	//construct object
	public Product(String modelName, String manufName, double retailPrice)
	{
		 
		this.modelName = modelName; //the parameters that are passed in are saved to instance variable of Product object
		this.manufName = manufName; 
		this.retailPrice = retailPrice;
			
	}
	
	public Product(String modelName, String manufName)
	{
		this.modelName = modelName; 
		this.manufName = manufName;

	}
	public double getRetailPrice() //return retailPrice
	{
		return retailPrice;
		
	}
	public void setRetailPrice(double retailPrice) //takes the parameter and assigns to the retail price attribute of the product
	{
		this.retailPrice = retailPrice;
	}
	
	public String getModelName() //returns the modelName
	{
		return modelName;
		
	}
	
	public String getManufName() //returns the manufacturer's name 
	{
		
		return manufName;
		
	}
	
	public double getRating() //returns the combined rating from all customers
	{
		return aggregatedReliabilityRating;
		
	}
	public void setRating(double reliabilityRating)//assigns parameter value to reliability attribute of the product 
	{
		this.aggregatedReliabilityRating = reliabilityRating;
	}
	
	public int getNumberOfConsumerRated()//returns number of consumer(s) value
	{
		return numberOfConsumerRated;
	}
	
	public String toString()//returns a string representation of the Product object using attributes
	{
		return "\tModel name: " + modelName + "\t Manufacturer's name: " + manufName + "\t Price: $" + retailPrice + "\t\t\t Rating: "+ aggregatedReliabilityRating + " (Based on " + numberOfConsumerRated + " customer ratings)"; 
	}
	
	public void rateReliability(double newRatingValue)//taking in the newRatingValue and passing into formula
	{
		if(newRatingValue >= 0.0 && newRatingValue <= 5) { //validates for user input (0.0 - 5.0) 
			
			aggregatedReliabilityRating = (aggregatedReliabilityRating * numberOfConsumerRated + newRatingValue) / (++numberOfConsumerRated);//incrementing number of customer ratings per the product
		}
		else{
			System.out.println("Invalid input! Please enter your rating value between 0.0 and 5.0 "); //if input is greater than 5.0 or lower than 0.0 this message is prompted
		}
	}
	
	


}
