package w2_exercises;

import java.util.Scanner;


public class Lecture_ex_1 {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		String s = scan.nextLine();
		
		String x = "Hello";
		
		int z = s.length();
		
		System.out.println(x + " "+ s);
		
		System.out.println(x.length());
		
		System.out.println(s);
		
		System.out.println(z);
		
		s = s.toUpperCase();
				
		System.out.println(s);
				
		s = s.repeat(z);
		
		System.out.println(s);
		
		scan.close();
	}

}
