/*
 * name: Hayden Richard-Marsters
 * stream: 52
 * student ID: 2550552
*/
package actual_labwork;

public class Grade {

	int percent;
	
	public Grade(int percent){
		this.setPercent(percent);
		
	}
	
	
	public void setPercent(int percent){
		this.percent = percent;
	}
	
	
	public int getPercent(){
		
		return this.percent;
	}
	public class UniversityApplication {

		public static void main(String[] args) {

				Grade p1 = new Grade(75);
				p1.setPercent(95);
				
				System.out.println("percent value: %"+p1.getPercent());
		

		}

	}

	
}
