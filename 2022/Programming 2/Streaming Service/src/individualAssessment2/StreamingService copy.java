package individualAssessment2;

import java.util.ArrayList;
import java.util.Collections;

public class StreamingService{
	
	//Instance variable of type ArrayList
	private ArrayList<DigitalContent> contents;
	
	//constructor to initialize the ArrayList
	public StreamingService() {
		contents = new ArrayList<>();
	}
	
	//adds individual DigitalContent passed in and assigns to object Array
	public void add(DigitalContent pieceOfContent) {
		contents.add(pieceOfContent);
	}
	
	//passes in String to match to any objects within the array, if found adds that object to a new array
	public ArrayList<DigitalContent> match(String query){
		
		ArrayList<DigitalContent> matchedNewList= new ArrayList<>();
		
		for(DigitalContent piece : contents) {
			
			if(piece.match(query)) {
				matchedNewList.add(piece);
			}
		}
		
		return matchedNewList; //returns new array
	}
	//@Override returns a sorted Array alphabetically
	public String toString() {
		
		String str = "";
		Collections.sort(contents);
		
		for(DigitalContent piece : contents) {
			
			str += piece;
		}
	
		return str;
	}
	
	
}

