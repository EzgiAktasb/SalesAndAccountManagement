package model;

import java.time.LocalDate;

public class Transaction {

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

    public void displayTransaction() {
        System.out.println("Transaction ID: " + transactionID);
        System.out.println("Product: " + product.getName());
        System.out.println("Account: " + account.getAccountId());
        System.out.println("Quantity: " + quantity);
        System.out.println("Date: " + date);
        System.out.println("Total Amount: " + calculateTransactionAmount());
        System.out.println("---------------------------------------");
    }
}

