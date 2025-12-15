package Accounts;
import model.Account;
public class SalesAccount extends Account {

    private double totalSales;

    public SalesAccount(String accountID, double balance) {
        super(accountID);
        this.balance = balance;
        this.totalSales = 0.0;
    }

 
  
  
    
   
    public void updateBalance(double amount) {
        balance += amount;
    }

    public void recordSale(double amount) {
        totalSales += amount;
        updateBalance(amount);
    }
}
