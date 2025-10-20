/*
 * name: Hayden Richard-Marsters
 * stream: 52
 * student ID: 2550552
*/
package actual_labwork;

public class StudentApplication {
	
	String name;
	String lastn;
	int code;

	public static void main(String[] args) {
		
		StudentApplication s = new StudentApplication();
		s.setName("Justtin");
		s.setLastn("Case");
		s.setCode(209321);
		
		System.out.println(s.getName() +" "+ s.getLastn()+" ID:" + s.getCode());
		
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setLastn(String lastn)
	{
		this.lastn = lastn;
	}
	public void setCode(int code)
	{
		this.code = code;
	}
	public String getName() {
		
		return this.name;
	}
	public String getLastn() {
		
		return this.lastn;
	}
	public Integer getCode() {
		
		return this.code;
	}
	
}
