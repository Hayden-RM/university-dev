package numberList;

import java.util.ArrayList;
import java.util.Collections;



public class NumberList2 {

	ArrayList<Integer> numbers; 
	
	public NumberList2(String s) {
		this.numbers = new ArrayList<Integer>();
		String [] output = s.split(" ");
		for(String eachString : output) {
			numbers.add(Integer.valueOf(eachString));
		}
	
	}
	
	public NumberList2(ArrayList<Integer> array) {
		
		
		
	}
		
	public ArrayList<Integer> getNumbers() {
		return numbers;
	}

	public double sum() {
		double sum = 0; 
		
		for(Integer value : this.numbers) {
			sum+=value;
		}
		return sum;
		
	}
	
	public double average() {
		double sum = 0;
		
		for(Integer value : this.numbers) {
			sum+= value;
		}
		
		double av = sum / this.numbers.size();
		
		
		return av;
	}
	
	public NumberList2 merge(NumberList2 input) {
		
		NumberList2 mergedList = new NumberList2(" ");
		
		for(Integer value : this.numbers) {
			mergedList.numbers.add(value);
		}
		for(Integer value : input.numbers) {
			mergedList.numbers.add(value);
		}
		
		return mergedList;
		
		
		
	}
	

	public static void main(String[] args) {
		
		
		NumberList2 list = new NumberList2("123 7654 213 8567");
		NumberList2 list2 = new NumberList2("345 123 678 34 3444");
		System.out.println(list.numbers);
		System.out.println(list.sum());
		System.out.println(list.average());
		System.out.println(list.merge(list2).numbers);
		
		ArrayList<Integer> intArray = new ArrayList<Integer>();
		intArray.addAll(list.merge(list2).numbers);
		
		Collections.sort(intArray);
		
		System.out.println(intArray);
		
		System.out.println(list.merge(list2).average());
	}


}
