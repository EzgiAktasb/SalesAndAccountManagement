package model;

public abstract class Account {

	protected String accountID;
    protected double balance;

    protected static int accountCount = 0;
    
    
    //constructor
    public Account(String accountID) {
        this.accountID = accountID;
        this.balance = 0.0;
        accountCount++;
    }
    
    public String getAccountID() {
        return accountID;
    }

    public double getBalance() {
        return balance;
    }

    protected void setBalance(double balance) {
        this.balance = balance;
    }
    
    
    public void updateBalance(double amount) {
    	this.balance = this.balance - amount;
    	
    }

    public void displayAccountInfo() {
        System.out.println("Account ID: " + accountID);
        System.out.println("Balance: " + balance);
    }

    public static int getAccountCount() {
        return accountCount;
    }
    
    
    
    
    
    
}
