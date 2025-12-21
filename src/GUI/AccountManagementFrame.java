package GUI;

import javax.swing.*;
import java.awt.*;
import SystemAndMain.SalesSystem;

import Accounts.BankAccount;
import Accounts.CashAccount;
import Accounts.SalesAccount;
import model.Account;
public class AccountManagementFrame extends JFrame {

    private JComboBox<String> cmbAccountType;
    private JTextField txtAccountID, txtBalance, txtBankName, txtIBAN;
    private JTextArea txtOutput;

    public AccountManagementFrame() {
        setTitle("Account Management");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        JPanel panelTop = new JPanel(new GridLayout(5, 2, 5, 5));

        JComboBox<String> cmbAccountType = new JComboBox<>();

        cmbAccountType.addItem("CashAccount");
        cmbAccountType.addItem("BankAccount");
        cmbAccountType.addItem("SalesAccount");

        txtAccountID = new JTextField();
        txtBalance = new JTextField();
        txtBankName = new JTextField();
        txtIBAN = new JTextField();

        panelTop.add(new JLabel("   Account Type:"));
        panelTop.add(cmbAccountType);

        panelTop.add(new JLabel("   Account ID:"));
        panelTop.add(txtAccountID);

        panelTop.add(new JLabel("   Balance:"));
        panelTop.add(txtBalance);

        panelTop.add(new JLabel("   Bank Name:"));
        panelTop.add(txtBankName);

        panelTop.add(new JLabel("   IBAN:"));
        panelTop.add(txtIBAN);

        getContentPane().add(panelTop, BorderLayout.NORTH);

        
        txtOutput = new JTextArea();
        txtOutput.setEditable(false);
        getContentPane().add(new JScrollPane(txtOutput), BorderLayout.CENTER);

     
        JPanel panelBottom = new JPanel(new GridLayout(1, 4, 5, 5));

        JButton btnAdd = new JButton("Add Account");
        JButton btnDelete = new JButton("Delete Account");
        JButton btnDisplay = new JButton("Display Accounts");
        JButton btnUpdate = new JButton("Update Balance");

        panelBottom.add(btnAdd);
        panelBottom.add(btnDelete);
        panelBottom.add(btnDisplay);
        panelBottom.add(btnUpdate);

        getContentPane().add(panelBottom, BorderLayout.SOUTH);

    
        btnAdd.addActionListener(e -> {
            try {
                String type = cmbAccountType.getSelectedItem().toString();
                String id = txtAccountID.getText();
                double balance = Double.parseDouble(txtBalance.getText());

                Account account = null;

                if (type.equals("CashAccount")) {
                    account = new Accounts.CashAccount(id, balance);
                } 
                else if (type.equals("BankAccount")) {
                    account = new Accounts.BankAccount(
                            id, 
                            balance, 
                            txtBankName.getText(), 
                            txtIBAN.getText()
                    );
                } 
                else if (type.equals("SalesAccount")) {
                    account = new Accounts.SalesAccount(id, balance);
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


