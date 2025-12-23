package model;

import java.time.LocalDate;

public class Transaction{

    private String transactionID;
    private Product product;
    private Account account;
    private int quantity;
    private LocalDate date;

    public Transaction(String transactionID, Product product, Account account,
                       int quantity, LocalDate date) {
        this.transactionID = transactionID;
        this.product = product;
        this.account = account;
        this.quantity = quantity;
        this.date = date;
    }

    public double calculateTransactionAmount() {
        return product.getPrice() * quantity;
    }

    public void applyTransaction() {
        double amount = calculateTransactionAmount();
        account.updateBalance(-amount);
        product.reduceStock(quantity);
    }

    public String displayTransaction() {
    	String temp = "Transaction ID: " + transactionID + "\n"+ "Product: " + product.getName()+ "\n"+"Account: " + account.getAccountID()+ "\n"+"Quantity: " + quantity+ "\n" + "Date: " + date + "\n" + "Total Amount: " + calculateTransactionAmount()+ "\n"+"---------------------------------------\n";
    	
    	return temp;
    }

	public String getTransactionID() {
		
		return transactionID;
	}

	public Product getProducts() {
		
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public Account getAccount() {
		return account;
	}
}



