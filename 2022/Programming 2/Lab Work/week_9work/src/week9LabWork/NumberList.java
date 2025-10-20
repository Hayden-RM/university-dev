package week9LabWork;

import java.util.ArrayList;

public class NumberList {

	ArrayList<Integer> numbers;
	
	public NumberList(String numbers) {
		
		this.numbers = new ArrayList<Integer>();
		String[] result = numbers.split(" ");
		for (String num: result) {
			this.numbers.add(Integer.valueOf(num));
		}
	}
	
	public NumberList(ArrayList<Integer> list) {
		numbers = list;
	}
	
	public ArrayList<Integer> getNumbers() {
		return numbers;
	}

	public int sum() {
		
		int sum = 0; 
		
		for(int num:numbers) {
			sum += num; 
		}
		
		return sum;
		
	}
	
	public double average() {
		
		double av = 0; 
		int sum = 0;
		
		for(int num : numbers) {
			sum += num;
		}
		av = sum / numbers.size();
	
		return av;
		
	}
	
	public NumberList merge(NumberList l1) {
		
		String str = " ";
		str += l1.numbers.addAll(numbers);
		
		NumberList list = new NumberList(str);
		
		return list;
			
		
	}
	
	
	
	public static void main(String[] args) {
		
		NumberList n1 = new NumberList("124 7654 12345 789");
		NumberList n2 = new NumberList("124 7654 12345 789");
		
	System.out.println(n1.average());
	System.out.println(n1.sum());
	System.out.println(n1);
	
	//System.out.println(n1.merge(n2));
		
		
		
	}
}
