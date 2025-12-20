package GUI;

import javax.swing.*;

import SystemAndMain.SalesSystem;

import java.awt.*;
import java.awt.event.*;

public class DashboardFrame extends JFrame {

    private SalesSystem salesSystem;
    private JLabel lblRevenue, lblTransactions, lblAccounts;

    public DashboardFrame(SalesSystem system) {
        this.salesSystem = system;
        setTitle("System Dashboard");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        //Title Panel
        JLabel title = new JLabel("Sales and Account Dashboard", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        //Info Panel
        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 15, 15));

        infoPanel.add(new JLabel("Total Revenue:"));
        lblRevenue = new JLabel("₺0.00");
        infoPanel.add(lblRevenue);

        infoPanel.add(new JLabel("Total Transactions:"));
        lblTransactions = new JLabel("0");
        infoPanel.add(lblTransactions);

        infoPanel.add(new JLabel("Total Accounts:"));
        lblAccounts = new JLabel("0");
        infoPanel.add(lblAccounts);

        add(infoPanel, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();

        JButton btnRefresh = new JButton("Refresh");
        JButton btnBack = new JButton("Back to Main Menu");

        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnBack);
        add(buttonPanel, BorderLayout.SOUTH);

        //Event Handling Part
        // Mouse click refreshes 
        btnRefresh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                refreshDashboard();
            }
        });

        // Back button closes 
        btnBack.addActionListener(e -> dispose());

        // Key event: pressing 'R' refreshes data
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_R) {
                    refreshDashboard();
                }
            }
        });
        setFocusable(true);

        // Load initial data
        refreshDashboard();
        setVisible(true);
    }

    // updating the board with live data 
    private void refreshDashboard() {
        double totalRevenue = salesSystem.calculateTotalRevenue();
        int totalTransactions = salesSystem.getTransactions().size();
        int totalAccounts = salesSystem.getAccounts().size();

        lblRevenue.setText(String.format("₺%.2f", totalRevenue));
        lblTransactions.setText(String.valueOf(totalTransactions));
        lblAccounts.setText(String.valueOf(totalAccounts));

    }
}
