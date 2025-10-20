
package individualAssessment2;

public abstract class DigitalContent implements Comparable<DigitalContent> {
	//DigitalContent class implements comparable interface. objects created from DigitalContent can be sorted alphabetically

	//elements of DigitalContent object
	protected String title;
	protected String publisher;
	protected String releaseDates;
	
//3-argument constructor for DigitalContent object
public DigitalContent(String title, String publisher, String releaseDates) {
		
		this.title = title;
		this.publisher = publisher;
		this.releaseDates = releaseDates;
		
	}
	
	//returns title element type String 
	public String getTitle() {
		return title;
	}

	//sets instance variable of object to that passed in
	public void setTitle(String title) {
		this.title = title;
	}
	
	//returns publisher variable type String
	public String getPublisher() {
		return publisher;
	}
	
	//sets instance variable of object to that passed in
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	//returns releaseDate variable type String
	public String getRelseaseDates() {
		return releaseDates;
	}
	

	//sets instance variable of object to that passed in
	public void setRelseaseDates(String relseaseDates) {
		this.releaseDates = relseaseDates;
	}
	
	//@Override returns a string representation of object
	public String toString() {
		
		return "Title: "+ title + "\n" + "Publisher: "+ publisher + "\n" + "Release Dates: "+ releaseDates;
	}
	
	//returns true if title/publisher/releaseDate contains String query, including case-variation
	public boolean match(String query) {
		
		return title.toLowerCase().contains(query.toLowerCase()) || publisher.toLowerCase().contains(query.toLowerCase()) || releaseDates.toLowerCase().contains(query.toLowerCase()); 
			
	}
	
	//returns integer value comparing DigitalContent objects based on title
	public int compareTo(DigitalContent contents) {
		
		return this.title.compareTo(contents.title);
	
	}
	
	
}