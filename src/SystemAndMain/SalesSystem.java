package SystemAndMain;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.Account;
import model.Product;
import model.Transaction;

public class SalesSystem {

    private static ArrayList<Product> product;
    private static Set<Transaction> transactions;
    private static Map<String, Account> accounts;

    public SalesSystem() {
        product = new ArrayList<>();
        transactions = new HashSet<>();
        accounts = new HashMap<>();
    }
    
    public static ArrayList<Product> getProducts() {
        return product;
    }

    public static Set<Transaction> getTransactions() {
        return transactions;
    }

    public static Map<String, Account> getAccounts() {
        return accounts;
    }

    public static void addProduct(Product p) {
        product.add(p);
    }

    public static void addTransaction(Transaction t) {
        transactions.add(t);
        t.applyTransaction();
    }

    public static void addAccount(Account a) {
        accounts.put(a.getAccountID(), a);
    }

    public static void displayAllProducts() {
        for (Product p : product) {
            p.displayProduct();
        }
    }

    public static void displayAllTransactions() {
        for (Transaction t : transactions) {
            t.displayTransaction();
        }
    }

    public static void displayAllAccounts() {
        for (Account a : accounts.values()) {
            a.displayAccountInfo();
        }
    }

    public static Product searchProductById(String id) {
        for (Product p : product) {
            if (p.getProductID().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public static Account searchAccountById(String id) {
        return accounts.get(id);
    }

    public static boolean deleteProduct(String id) {
        return product.removeIf(p -> p.getProductID().equals(id));
    }

    public static boolean deleteAccount(String id) {
        return accounts.remove(id) != null;
    }

    public static double calculateTotalRevenue() {
        double total = 0.0;
        for (Transaction t : transactions) {
            total += t.calculateTransactionAmount();
        }
        return total;
    }

    public static void updateAccountBalance(String id, double amount) {
        Account account = accounts.get(id);
        if (account != null) {
            account.updateBalance(amount);
        }
    }
}

