/**


name: Hayden Richard-Marsters
student ID: 21152003
code: COMP503
labwork week 5



**/



package lab_work;

public class simpleDate {

	
int day = 0;
int month = 0;
int year = 0;

public int getDay() {
	return day;
}

public void setDay(int day) {
	
	do {
		this.day = day;
	}while(day >= 1 && day <= 31);
	
/*
	if(day >= 1 && day <= 31) {
	this.day = day;
	}else {
		System.out.println("\nInvalid input!");
		}
	*/
}

public int getMonth() {
	return month;
}

public void setMonth(int month) {
	
	do {
		this.month = month;
	}while(month >= 1 && month <= 12);
	
/*	
		if(month >= 1 && month <= 12) {
		this.month = month;
		}else {
			System.out.println("\nInvalid input!");
			}
*/		
		

}


public int getYear() {
	return year;
}

public void setYear(int year) {
	do {
		this.year = year;
	}while(year >= 2000 && year <= 2022);
	
/*
		if(year >= 2000 && year <= 2022) {
		this.year = year;
		}else {
			System.out.println("\nInvalid input!");
			}
*/	
		

}
public void setDate(int day, int month, int year) {
	
	this.day = day;
	this.month = month;
	this.month = month;

}
public simpleDate(int day, int month, int year) {
	
	this.setDate(day, month, year);
	
}

public String toString() {
	
String string = " "; 

string += getDay() + "/" + getMonth() + "/" + getYear();

return string;
	 
			

}

public static void main(String[] args) {
		

		simpleDate date = new simpleDate(12, 24, 2012);
		
		System.out.println(date);
		
	
		
	}

}
