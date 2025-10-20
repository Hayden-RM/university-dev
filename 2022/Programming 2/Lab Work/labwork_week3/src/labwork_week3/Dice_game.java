package labwork_week3;

import java.util.Random;

public class Dice_game {
	
	int faceValue;
	Random rand;
	
	public Dice_game()
	{
		this.rand = new Random();
		this.roll();
	}
	void roll()
	{
		this.faceValue = rand.nextInt(5)+1;
	}

	public static void main(String[] args) {
	
		Dice_game die = new Dice_game();
		
		die.roll();
		System.out.println(die.faceValue);
		die.roll();
		System.out.println(die.faceValue);
		
	
	}

}
