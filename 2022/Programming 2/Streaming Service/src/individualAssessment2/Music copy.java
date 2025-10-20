

package individualAssessment2;
//music class is inherited from DigitalContent parent class
public class Music extends DigitalContent {

	//instance variables for Music object
	private String artistName;
	
	//constructor using parent class variables and Music subclass artist name variable
	public Music(String title, String publisher, String releaseDates, String artistName) {
		
		super(title, publisher, releaseDates);
		this.artistName = artistName;
	}

	//returns artistName
	public String getArtistName() {
		return artistName;
	}

	//sets artist name
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	//@Override returns a string representation of music object
	public String toString(){
	
		return super.toString() + "\nArtist name: " + artistName + "\n\n";
			
	}
	//returns true if query is contained within title/publisher/releaseDates or ArtistName, if not it returns false
	public boolean match(String query) {
		
		return super.match(query) || artistName.toLowerCase().contains(query.toLowerCase());
	}
	//returns int value to compare by title 
	public int compareTo(DigitalContent contents) {
		
		
			return this.title.compareTo(contents.title);
	
	}
	
	
}
