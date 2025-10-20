
package individualAssessment2;
//Film class is inherited from DigitalContent creating subclass
public class Film extends DigitalContent {

	//instance variable for Film 
	private String castMembers; 
	
	//Constructor for Film subclass
	public Film(String title, String publisher, String releaseDates, String castMembers) {
		
		//refers to parent class variables
		super(title, publisher, releaseDates);
		this.castMembers = castMembers;
		
	}
	
	//returns cast members type string
	public String getCastMembers() {
		return castMembers;
	}

	//sets cast members to instance variable
	public void setCastMembers(String castMembers) {
		this.castMembers = castMembers;
	}
	//@Override returns a string representation of the Film subclass object
	public String toString() {
		
		return super.toString() + "\nCast: " + castMembers + "\n\n"; 
		
	}
		
	public boolean match(String query) {
		
		//returns true if query is contained within title/publisher/releaseDate or cast, if not returns false
		return super.match(query) || castMembers.toLowerCase().contains(query.toLowerCase());
	}
	
	//compares title
	public int compareTo(DigitalContent contents) {
		
		return this.title.compareTo(contents.title);
	
	}
	

}
