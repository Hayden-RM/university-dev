
package individualAssessment2;

public interface Play {//declares interface methods which is implemented by Client class

	public DigitalContent getCurrentStream();
	public void stream(String query); 
	public void stop();
	
}
