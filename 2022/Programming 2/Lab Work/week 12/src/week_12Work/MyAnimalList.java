package week_12Work;

import java.util.ArrayList;

public class MyAnimalList {

	public static void main(String[] args) {
		
		ArrayList<Animal> list = new ArrayList<>();
		
		Tiger t1 = new Tiger();
		list.add(t1);
		
		Tiger t2 = new Tiger();
		list.add(t2);
		
		
		Tiger t3 = new Tiger();
		list.add(t3);
		
		Tiger t4 = new Tiger();
		list.add(t4);
		
		DomesticCat c1 = new DomesticCat();
		list.add(c1);
		
		DomesticCat c2 = new DomesticCat();
		list.add(c2);
		
		DomesticCat c3 = new DomesticCat();
		list.add(c3);
		
		Feline f1 = new Feline();
		list.add(f1);
		
		Feline f2 = new Feline();
		list.add(f2);
		
		for(Animal animal : list) {
			
			animal.soundsLike();
			
		}
		
	}

}
