package Accounts;

import model.Account;

public class BankAccount extends Account {

    private String bankName;
    private String iban;

    public BankAccount(String accountID, double balance, String bankName, String iban) {
        super(accountID);
        this.balance = balance;
        this.bankName = bankName;
        this.iban = iban;
    }
  
    
    public void updateBalance(double amount) {
        this.balance += amount;
    }

    public void transferFunds(BankAccount target, double amount) {
        if (amount <= balance) {
            this.balance -= amount;
            target.updateBalance(amount);
        } else {
            System.out.println("Insufficient balance for transfer.");
        }
    }
}
