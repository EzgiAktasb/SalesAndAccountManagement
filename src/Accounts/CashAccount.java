package Accounts;

import model.Account;

public class CashAccount extends Account {

    public CashAccount(String accountID, double balance) {
        super(accountID);
        this.balance = balance;
    }
 
    
    public void updateBalance(double amount) {
        balance += amount;
    }
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Insufficient cash balance.");
        }
    }

    public void deposit(double amount) {
        balance += amount;
    }
}
