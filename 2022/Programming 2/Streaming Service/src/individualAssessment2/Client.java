
package individualAssessment2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Client implements Play{
	//instance variables for Client object
	private StreamingService service;
	private DigitalContent currentlyStreaming; 
	
	//Client constructor initializing currentlyStreamed variable to null
	public Client(StreamingService service) {
		
		this.service = service;
		currentlyStreaming = null;
		
	}
	//returns StreamingService object
	public StreamingService getService() {
		
		return service;
	}
	//returns a DigitalContent object
	public DigitalContent getCurrentStream() {
		
		return currentlyStreaming;
		 
	}
	//takes in a string and passes that into StreamServices match method, uses the returned array first element to set to currentlyStreamed if not null
	public void stream(String query) {
		
		ArrayList<DigitalContent> streamCurrent = new ArrayList<DigitalContent>();
		
		streamCurrent = service.match(query);
		
		if(streamCurrent == null){//checks if the returned array from StreamingService match method is empty
			
			System.err.println("No matches found");//user prompt if no matches found and returned array is empty
			
		}else {
			
			currentlyStreaming = streamCurrent.get(0); //assigns currentlyStreamed to first element of array 
			
		}
		
	}
	//sets currentlyStreamed to null, and displays message saying stopping or nothing is playing
	public void stop() {
		if(currentlyStreaming == null) {
			
			System.err.println("Nothing is currently playing...");
			
		}else {
			
			currentlyStreaming = null; 
			System.err.println("Stopping the stream");
		}
	}
	//@Override returns a String representation for a menu of functions of user
	public String toString() {
		
		String str = "";
		
		str += "A. Display Digital Content library\n";
		str += "B. Display currently streaming DigitalContent\n";
		str += "C. Match Digital Content to stream\n";
		str += "D. Stop Streaming\n";
		str += "E. Quit Client Application\n";
		
		return str;
		
	}

	public static void main(String[] args) {
		
		StreamingService service = new StreamingService(); //instantiate new StreamingService object
		
		ArrayList<DigitalContent> list = new ArrayList<DigitalContent>(); //instantiate new ArrayList of type DigitalContent
		
		Client newClient = new Client(service); //instantiate new Client object
		
		char input; //char user input for menu function
		
		Scanner scan = new Scanner(System.in);
		//instantiating new DigitalContent objects and adding to service arrayList
		DigitalContent jurassicWorld = new Film("Jurassic world", "Universal Studios", "2015", "Chriss Patt");
		service.add(jurassicWorld);
		
		DigitalContent fallingBack = new Music("Falling back", "Universal Motown", "2022", "Drake");
		service.add(fallingBack);
		
		DigitalContent nevermind = new Music("Nevermind", "Universal Motown", "2022", "Drake");
		service.add(nevermind);
		
		DigitalContent whiplash = new Film("Whiplash", "Bold films", "2014", "Miles teller");
		service.add(whiplash);
		
		DigitalContent badBlood = new Music("Bad Blood", "Nathan Chapman", "2014", "Taylor Swift");
		service.add(badBlood);
		
		DigitalContent avengers = new Film("Avengers: Age Of Ultron", "Marvel", "2015", "Robert Downey Jr.");
		service.add(avengers);
		
		DigitalContent jamesBond = new Film("James Bond: No Time To Die", "Eon productions", "2021", "Daniel Craig");
		service.add(jamesBond);
		
		DigitalContent spiderMan = new Film("Spider-Man: No Way Home", "Marvel", "2021", "Tom Holland");
		service.add(spiderMan);
		
		DigitalContent dripTooHard = new Music("Drip Too Hard", "ATL Records", "2018", "Lil Baby");
		service.add(dripTooHard);
		
		DigitalContent inAMinute = new Music("In A Minute", "ATL Records", "2022", "Lil Baby");
		service.add(inAMinute);
		
		//do while input until user input equals 'e' or 'E'
		do {
		
			System.out.println("\nEnter function: ");
			
			System.out.println(newClient.toString()); //prints menu
			
			input = scan.next().toUpperCase().charAt(0); //scans character and sets to upper case if not already
				
			if(input == 'A') {
				
				Collections.sort(list);
				System.out.println(newClient.service);
				
			}else if(input == 'B') {
				
				if(newClient.getCurrentStream() == null) {//if currently streamed is null message is displayed
					
					System.err.println("Nothing is Streaming at this moment, pick something by matching keywords");
					
				}else {
				
					System.out.println("Currently playing... " + newClient.getCurrentStream().title); //displays what's now playing's title
				
				}
				
			}else if(input == 'C') {
				
			 String userInput; 
			
			 System.out.println("Enter title, publisher, release-date or artist/cast name to match within library");
			 userInput = scan.nextLine();
			 userInput = scan.nextLine(); //scans for user input of string to input into match method that returns a array of matching items 
						
			 System.out.println(newClient.service.match(userInput).toString());//prints new matching ArrayList based off user input string
			 
			 newClient.stream(userInput);//invoking the stream method setting the first element in matching array to currentlyStreamed
			
			 System.out.println("Now playing "+ newClient.currentlyStreaming.title); //displays message and title of what's playing 
			 
			 }else if(input == 'D') {
				
				newClient.stop(); //invokes stop method if input is 'd' or 'D'
				
			}else if(input == 'E') {
				
				System.err.println("Quitting application..."); //display message when program is terminated 
			}
			
		}while(input != 'E'); //exits program when user enters 'e' or 'E'
		
		scan.close();
	}
}
