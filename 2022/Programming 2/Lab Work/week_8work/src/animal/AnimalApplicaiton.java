package animal;

import java.util.Scanner;

public class AnimalApplicaiton {

	public static void main(String[] args) {
		
		Animal [] list = new Animal[4];
	
		Scanner scan = new Scanner(System.in);
		
		int userNum;
		
		int index = 0; 
		
		do {
			
			System.out.println("Type 1 to create a Dog object or 2 to create a Dog object");
		
			userNum = scan.nextInt();
			
			if(userNum == 1) {
				Cat c1 = new Cat();
				list[index] = c1;
				System.out.println(list[index].toString()+"\n");
				index++;
				
			}else if(userNum == 2) {
				Dog d1 = new Dog();
				list[index] = d1;
				System.out.println(list[index].toString()+"\n");
				index++;
				
			}else {
				System.err.println("Invalid input! ");
			}
		
			
		}while(index != 4);
		
		int input = 0; 
		do {
			System.out.println("Select an animal to feed by entering a number within the range 0-4");
			for(int i = 0; i < 4; i++) {
				System.out.println(list[i]);
			}
			input = scan.nextInt();
			
			list[input].feed();
			
			
		}while(input < 4 || input >= 0);
		
		scan.close();
	}

}
