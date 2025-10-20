/**


name: Hayden Richard-Marsters
student ID: 21152003
code: COMP503
labwork week 5



**/
package lab_work;

	public class String_compute {

	private String str;
	
	public String_compute(String str) {
	
	this.str = str;
	
	}	

	public String getStr() {
		
		return str;
	}
	
	public void setStr(String str) {
		
		 this.str = str;
	}

	public int countOccurances(char ch) {
		
		int count = 0; 
		
		for(int i = 0; i < str.length(); i++) {
			
			if(str.charAt(i) == ch)//charAt returns a char value at index position
				
				count++;
			}		
		
		return count;
		
		
	}
	public boolean hasOnlyDigits() {
		
	boolean hasDigits = false;	
		
	for(int i = 0; i < str.length(); i++) {	
		
		if(str.charAt(i) >= '0' && str.charAt(i) <= '9') {
			
		hasDigits = true
				;
		}
		
	}
	return hasDigits;
	}
	
	public String removeChar(char ch) {
		
		String string = " ";

		
		for(int i = 0; i < str.length(); i++) {
			
			if(str.charAt(i) != ch) {
				string += str.charAt(i);
			}
		}
		return string; 
	}
	
	boolean isPalindrome() {
		
		boolean isPal = false;
	
		String string = this.str; 
	
		String reversed = " "; 
		
		
		for(int i = string.length() - 1; i > 0; i--) {
			
			reversed += string.charAt(i);
			
			if(reversed.equals(string)) {
				isPal = true;
			}else {
				
				System.out.println("the string is not a palindrome");
			}
		}
		
		return isPal;
	}
	
	public void duplicate(String s) {
		
		this.str = s;
		String combined = " ";
		
		if(str.equals(s) == true) {
			
			
			s.toUpperCase();
			combined = s += s;
		}else {
			
			
			this.str = s;
		}
		this.str = combined;
		
	}


}
