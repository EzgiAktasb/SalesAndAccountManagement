package GUI;
import javax.swing.*;

import SystemAndMain.SalesSystem;
import model.Account;
import model.Product;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

public class TransactionFrame extends JFrame {
    private SalesSystem salesSystem;

    private JTextField txtTransactionID, txtQuantity;
    private JComboBox<String> cmbProduct, cmbAccount;
    private JTextArea txtDisplay;
    private JButton btnAdd, btnDisplay, btnCalculate;

    public TransactionFrame(SalesSystem system) {
        this.salesSystem = system;
        setTitle("Transaction Management");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // --- Top Panel: Inputs ---
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        inputPanel.add(new JLabel("Transaction ID:"));
        txtTransactionID = new JTextField();
        inputPanel.add(txtTransactionID);

        inputPanel.add(new JLabel("Quantity:"));
        txtQuantity = new JTextField();
        inputPanel.add(txtQuantity);

        inputPanel.add(new JLabel("Select Product:"));
        cmbProduct = new JComboBox<>();
        for (Product p : salesSystem.getProducts()) {
            cmbProduct.addItem(p.getProductID() + " - " + p.getName());
        }
        inputPanel.add(cmbProduct);

        inputPanel.add(new JLabel("Select Account:"));
        cmbAccount = new JComboBox<>();
        for (Account a : salesSystem.getAccounts().values()) {
            cmbAccount.addItem(a.getAccountID());
        }
        inputPanel.add(cmbAccount);

        add(inputPanel, BorderLayout.NORTH);

        // --- Center Panel: Text Area ---
        txtDisplay = new JTextArea();
        txtDisplay.setEditable(false);
        add(new JScrollPane(txtDisplay), BorderLayout.CENTER);

        // --- Bottom Panel: Buttons ---
        JPanel buttonPanel = new JPanel();

        btnAdd = new JButton("Add Transaction");
        btnDisplay = new JButton("Display Transactions");
        btnCalculate = new JButton("Calculate Total Revenue");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnDisplay);
        buttonPanel.add(btnCalculate);

        add(buttonPanel, BorderLayout.SOUTH);

        // --- Key Event: Press Enter to calculate product total ---
        txtQuantity.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    int quantity = Integer.parseInt(txtQuantity.getText());
                    int selectedIndex = cmbProduct.getSelectedIndex();
                    Product selectedProduct = salesSystem.getProducts().get(selectedIndex);
                    double total = selectedProduct.getPrice() * quantity;
                    JOptionPane.showMessageDialog(null,
                            "Total: " + total + " for " + selectedProduct.getName());
                }
            }
        });

        // --- Mouse Event: Add Transaction ---
        btnAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    String tID = txtTransactionID.getText();
                    int quantity = Integer.parseInt(txtQuantity.getText());
                    int productIndex = cmbProduct.getSelectedIndex();
                    int accountIndex = cmbAccount.getSelectedIndex();

                    Product product = salesSystem.getProducts().get(productIndex);
                    Account account = (Account) salesSystem.getAccounts()
                            .values().toArray()[accountIndex];

                    Transaction t = new Transaction(tID, product, account, quantity, LocalDate.now());
                    salesSystem.addTransaction(t);
                    t.applyTransaction();

                    JOptionPane.showMessageDialog(null, "Transaction added successfully!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        // --- Display Transactions ---
        btnDisplay.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            for (Transaction t : salesSystem.getTransactions()) {
                sb.append(t.getTransactionID()).append(" - ")
                        .append(t.getProduct().getName()).append(" - ")
                        .append(t.getQuantity()).append(" pcs - ")
                        .append(t.getAccount().getAccountID()).append("\\n");
            }
            txtDisplay.setText(sb.toString());
        });

        // --- Calculate Total Revenue ---
        btnCalculate.addActionListener(e -> {
            double revenue = salesSystem.calculateTotalRevenue();
            JOptionPane.showMessageDialog(null, "Total Revenue: " + revenue);
        });

        setVisible(true);
    }
}

