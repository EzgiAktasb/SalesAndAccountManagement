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
        setLayout(new BorderLayout());

        // ---------- TOP PANEL ----------
        JPanel panelTop = new JPanel(new GridLayout(5, 2, 5, 5));

        cmbAccountType = new JComboBox<>();
        cmbAccountType.addItem("CashAccount");
        cmbAccountType.addItem("BankAccount");
        cmbAccountType.addItem("SalesAccount");

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

        // ---------- CENTER ----------
        txtOutput = new JTextArea();
        txtOutput.setEditable(false);
        add(new JScrollPane(txtOutput), BorderLayout.CENTER);

        // ---------- BOTTOM ----------
        JPanel panelBottom = new JPanel(new GridLayout(1, 5, 5, 5));

        JButton btnAdd = new JButton("Add Account");
        JButton btnDelete = new JButton("Delete Account");
        JButton btnDisplay = new JButton("Display Accounts");
        JButton btnUpdate = new JButton("Update Balance");
        JButton btnNext = new JButton("Next");

        panelBottom.add(btnAdd);
        panelBottom.add(btnDelete);
        panelBottom.add(btnDisplay);
        panelBottom.add(btnUpdate);
        panelBottom.add(btnNext);

        add(panelBottom, BorderLayout.SOUTH);

        // ---------- ADD ACCOUNT ----------
        btnAdd.addActionListener(e -> {
            try {
                String type = cmbAccountType.getSelectedItem().toString();
                String id = txtAccountID.getText();

                if (!isValidAccountId(id)) {
                    JOptionPane.showMessageDialog(this,
                            "Account ID must be exactly 6 digits!");
                    return;
                }

                double balance = Double.parseDouble(txtBalance.getText());
                Account account = null;

                if (type.equals("CashAccount")) {
                    account = new CashAccount(id, balance);
                }
                else if (type.equals("SalesAccount")) {
                    account = new SalesAccount(id, balance);
                }
                else if (type.equals("BankAccount")) {

                    if (!isValidIBAN(txtIBAN.getText())) {
                        JOptionPane.showMessageDialog(this,
                                "IBAN must start with TR and contain 24 digits!");
                        return;
                    }

                    account = new BankAccount(
                            id,
                            balance,
                            txtBankName.getText(),
                            txtIBAN.getText()
                    );
                }

                SalesSystem.addAccount(account);
                txtOutput.append("Account added successfully!\n");

                clearFields();

            } catch (Exception ex) {
                txtOutput.append("Error while adding account!\n");
            }
        });

        // ---------- DELETE ----------
        btnDelete.addActionListener(e -> {
            String id = txtAccountID.getText();

            if (!isValidAccountId(id)) {
                JOptionPane.showMessageDialog(this,
                        "Enter a valid 6-digit Account ID!");
                return;
            }

            boolean deleted = SalesSystem.deleteAccount(id);

            if (deleted)
                txtOutput.append("Account deleted: " + id + "\n");
            else
                txtOutput.append("Account not found!\n");
        });

        // ---------- DISPLAY ----------
        btnDisplay.addActionListener(e -> {
            txtOutput.setText("---- ACCOUNT LIST ----\n");

            for (Account a : SalesSystem.getAccounts().values()) {
                txtOutput.append(a.toString() + "\n");
            }
        });

        // ---------- UPDATE BALANCE ----------
        btnUpdate.addActionListener(e -> {
            try {
                String id = txtAccountID.getText();

                if (!isValidAccountId(id)) {
                    JOptionPane.showMessageDialog(this,
                            "You must enter a valid Account ID before updating balance!");
                    return;
                }

                double amount = Double.parseDouble(txtBalance.getText());
                SalesSystem.updateAccountBalance(id, amount);

                txtOutput.append("Balance updated for Account ID: " + id + "\n");
                clearFields();

            } catch (Exception ex) {
                txtOutput.append("Error updating balance!\n");
            }
        });

        // ---------- NEXT ----------
        btnNext.addActionListener(e -> {
            dispose();
            new ProductManagementFrame().setVisible(true);
        });
    }

    // ---------- VALIDATION METHODS ----------

    private boolean isValidAccountId(String id) {
        if (id == null || id.length() != 6)
            return false;

        for (int i = 0; i < id.length(); i++) {
            if (!Character.isDigit(id.charAt(i)))
                return false;
        }
        return true;
    }

    private boolean isValidIBAN(String iban) {
        if (iban == null || iban.length() != 26)
            return false;

        if (iban.charAt(0) != 'T' || iban.charAt(1) != 'R')
            return false;

        for (int i = 2; i < iban.length(); i++) {
            if (!Character.isDigit(iban.charAt(i)))
                return false;
        }
        return true;
    }

    private void clearFields() {
        txtAccountID.setText("");
        txtBalance.setText("");
        txtBankName.setText("");
        txtIBAN.setText("");
    }
}
