package gui;

import javax.swing.*;
import java.awt.*;
import SystemAndMain.SalesSystem;
import model.Account;
import model.BankAccount;
import model.CashAccount;
import model.SalesAccount;

public class AccountManagementFrame extends JFrame {

    private JComboBox<String> cmbAccountType;
    private JTextField txtAccountID, txtBalance, txtBankName, txtIBAN;
    private JTextArea txtOutput;

    public AccountManagementFrame() {
        setTitle("Account Management");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        /* -------- TOP PANEL (Inputs) -------- */
        JPanel panelTop = new JPanel(new GridLayout(5, 2, 5, 5));

        cmbAccountType = new JComboBox<>(new String[]{
                "CashAccount", "BankAccount", "SalesAccount"
        });

        txtAccountID = new JTextField();
        txtBalance = new JTextField();
        txtBankName = new JTextField();
        txtIBAN = new JTextField();

        panelTop.add(new JLabel("Account Type:"));
        panelTop.add(cmbAccountType);

        panelTop.add(new JLabel("Account ID:"));
        panelTop.add(txtAccountID);

        panelTop.add(new JLabel("Balance:"));
        panelTop.add(txtBalance);

        panelTop.add(new JLabel("Bank Name:"));
        panelTop.add(txtBankName);

        panelTop.add(new JLabel("IBAN:"));
        panelTop.add(txtIBAN);

        add(panelTop, BorderLayout.NORTH);

        /* -------- CENTER (Output) -------- */
        txtOutput = new JTextArea();
        txtOutput.setEditable(false);
        add(new JScrollPane(txtOutput), BorderLayout.CENTER);

        /* -------- BOTTOM (Buttons) -------- */
        JPanel panelBottom = new JPanel(new GridLayout(1, 4, 5, 5));

        JButton btnAdd = new JButton("Add Account");
        JButton btnDelete = new JButton("Delete Account");
        JButton btnDisplay = new JButton("Display Accounts");
        JButton btnUpdate = new JButton("Update Balance");

        panelBottom.add(btnAdd);
        panelBottom.add(btnDelete);
        panelBottom.add(btnDisplay);
        panelBottom.add(btnUpdate);

        add(panelBottom, BorderLayout.SOUTH);

    
        btnAdd.addActionListener(e -> {
            try {
                String type = cmbAccountType.getSelectedItem().toString();
                String id = txtAccountID.getText();
                double balance = Double.parseDouble(txtBalance.getText());

                Account account = null;

                if (type.equals("CashAccount")) {
                    account = new CashAccount(id, balance);
                } 
                else if (type.equals("BankAccount")) {
                    account = new BankAccount(
                            id, 
                            balance, 
                            txtBankName.getText(), 
                            txtIBAN.getText()
                    );
                } 
                else if (type.equals("SalesAccount")) {
                    account = new SalesAccount(id, balance);
                }

                SalesSystem.addAccount(account);
                txtOutput.append("Account added: " + id + "\n");

            } catch (Exception ex) {
                txtOutput.append("Error adding account!\n");
            }
        });
        btnDelete.addActionListener(e -> {
            String id = txtAccountID.getText();
            boolean deleted = SalesSystem.deleteAccount(id);

            if (deleted)
                txtOutput.append("Account deleted: " + id + "\n");
            else
                txtOutput.append("Account not found!\n");
        });
        btnDisplay.addActionListener(e -> {
            txtOutput.setText("");
            for (Account a : SalesSystem.getAccounts().values()) {
                txtOutput.append(a.toString() + "\n");
            }
        });

        btnUpdate.addActionListener(e -> {
            try {
                String id = txtAccountID.getText();
                double amount = Double.parseDouble(txtBalance.getText());

                SalesSystem.updateAccountBalance(id, amount);
                txtOutput.append("Balance updated for: " + id + "\n");

            } catch (Exception ex) {
                txtOutput.append("Error updating balance!\n");
            }
        });
    }
}

