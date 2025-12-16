package SystemAndMain;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SalesSystem {

    private ArrayList<Product> product;
    private Set<Transaction> transactions;
    private Map<String, Account> accounts;

    public SalesSystem() {
        product = new ArrayList<>();
        transactions = new HashSet<>();
        accounts = new HashMap<>();
    }

    public void addProduct(Product p) {
        product.add(p);
    }

    public void addTransaction(Transaction t) {
        transactions.add(t);
        t.applyTransaction();
    }

    public void addAccount(Account a) {
        accounts.put(a.getAccountID(), a);
    }

    public void displayAllProducts() {
        for (Product p : product) {
            p.displayProduct();
        }
    }

    public void displayAllTransactions() {
        for (Transaction t : transactions) {
            t.displayTransaction();
        }
    }

    public void displayAllAccounts() {
        for (Account a : accounts.values()) {
            a.displayAccountInfo();
        }
    }

    public Product searchProductById(String id) {
        for (Product p : product) {
            if (p.getProductID().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public Account searchAccountById(String id) {
        return accounts.get(id);
    }

    public boolean deleteProduct(String id) {
        return product.removeIf(p -> p.getProductId().equals(id));
    }

    public boolean deleteAccount(String id) {
        return accounts.remove(id) != null;
    }

    public double calculateTotalRevenue() {
        double total = 0.0;
        for (Transaction t : transactions) {
            total += t.calculateTransactionAmount();
        }
        return total;
    }

    public void updateAccountBalance(String id, double amount) {
        Account account = accounts.get(id);
        if (account != null) {
            account.updateBalance(amount);
        }
    }
}

