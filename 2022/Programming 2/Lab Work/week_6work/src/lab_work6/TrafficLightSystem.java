package lab_work6;
 
import java.util.Scanner;  // Import the Scanner class 

enum TrafficLights {     RED("STOP"),     YELLOW("Slow Down"),     GREEN("GO!");
	private String suggestion;
	
	public String getSuggestion(){         return suggestion;     }
	
	public void setSuggestion(String newSuggestion){ 
		this.suggestion = newSuggestion;}          
	
	private TrafficLights(String suggestion) {       
		this.suggestion = suggestion;}          
	
	private boolean stop(){         
		if(this.name().equals("GREEN"))
			return false;        
		return true;}          @Override     
	
	public String toString() {         
			
			return this.name() + " " + suggestion + " " + (this.stop() ? "true" : "false"); } } 

public class TrafficLightSystem{     
	
	public static void main(String []args){       
		Scanner scannerObj = new Scanner(System.in);  // Create a Scanner object        
		
		int redCount = 0;         while(true){    
			
			System.out.println("Enter the light: ");           
			String input = scannerObj.nextLine();  // Read user input      
			if(input.equals("RED"))
				redCount++;                             
			if(redCount==2){                    
				System.out.println("Exiting because RED has been entered twice!");  
				break;  }                    
			
			TrafficLights lights[] = TrafficLights.values();                         
			
			boolean isValid = false;             for(TrafficLights light : lights){  
				String curr = light.toString().split(" ", 0)[0];                  
				if(curr.equals(input)){                    
					isValid = true;                     
					System.out.println(light); }             
				}             
			if(!isValid){      
				System.out.println("invalid string");  }         }      } 
	
	
		
	}
