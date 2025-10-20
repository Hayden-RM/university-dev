package parkingSystem;

public class ParkingSystem {

	private int big;
	private int medium;
	private int small;
	
	
	public ParkingSystem(int big, int medium, int small) {
		
		if(big >= 0) {
			this.big = big;
			
		}else {
			this.big = 0;
		}
		
		if(medium >= 0) {
			this.medium = medium;
		}else {
			this.medium = 0;
		}
		
		if(small >= 0) {
			this.small = small;
		}else {
			this.small = 0;
		}
		
	}


	public int getBig() {
		return big;
	}


	public int getMedium() {
		return medium;
	}


	public int getSmall() {
		return small;
	}
	
	public boolean addCar(int carType) {
		//1=big
		//2=medium
		//3=small
		boolean query = true;
		
		if(carType == 1) {
			if(this.big <= 0) {
				query = false; 
			}
			this.big -= 1;
		}else if(carType == 2) {
			if(this.medium <= 0) {
				query = false;
			}
			this.medium -= 1;
		}else if(carType == 3) {
			if(this.small <= 0) {
				query = false;
			}
			this.small -= 1;
		}

		return query; 
		
		
	}
	
	public String toString() {
		
		return " B:"+ this.big + " M:"+this.medium + " S:"+ this.small;
		
	}
	
	
	
	
	
}
