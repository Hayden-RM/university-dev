package week_12Work;

public class BankAccount {

	private String name;
	private int pin;
	private int numberOfTransactions;
	private double balance;
	
	
	public BankAccount(String name) {
		
		this.name = name;
		this.pin = 0000;
		this.numberOfTransactions = 1;
		this.balance = 0.0;

	}
	
	public void deposit(double amount) {
		
		this.numberOfTransactions += 1;
		this.balance += amount;
		
	}
	
	public void withdraw(double amount) {
		
		this.numberOfTransactions += 1;
		this.balance -= amount;
		
	}
	
	public String toString() {
		
		return "Name; "+name+"\n Balance: "+balance;
		
	}
	
	public static void main(String[] args) {

		BankAccount p1 = new BankAccount("Steve");
		
		
		System.out.println(p1);
		
		p1.deposit(3500);
		
		System.out.println(p1);
		
		p1.withdraw(8000);
		
		System.out.println(p1);
		
		
		
		
		
		

	}

}
