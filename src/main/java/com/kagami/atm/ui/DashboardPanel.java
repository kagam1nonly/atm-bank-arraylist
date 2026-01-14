package com.kagami.atm.ui;

import com.kagami.atm.ATMService;
import com.kagami.atm.User;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {
    private ATMFrame frame;
    private ATMService service;
    private User currentUser;

    private JLabel welcomeLabel;
    private JLabel balanceLabel;

    public DashboardPanel(ATMFrame frame, ATMService service) {
        this.frame = frame;
        this.service = service;

        setLayout(new BorderLayout());

        // Header
        JPanel header = new JPanel(new GridLayout(2, 1));
        header.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        welcomeLabel = new JLabel("Welcome!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));

        balanceLabel = new JLabel("Balance: $0.00", SwingConstants.CENTER);
        balanceLabel.setFont(new Font("Monospaced", Font.BOLD, 32));
        balanceLabel.setForeground(new Color(0, 150, 255)); // Blueish

        header.add(welcomeLabel);
        header.add(balanceLabel);
        add(header, BorderLayout.NORTH);

        // Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 50, 50));

        JButton depositBtn = createButton("Deposit", "icons/deposit.png");
        JButton withdrawBtn = createButton("Withdraw", "icons/withdraw.png");
        JButton historyBtn = createButton("Refresh", null); // Just refresh for now
        JButton logoutBtn = createButton("Logout", "icons/logout.png");

        depositBtn.addActionListener(e -> showTransactionDialog("Deposit"));
        withdrawBtn.addActionListener(e -> showTransactionDialog("Withdraw"));
        historyBtn.addActionListener(e -> updateDisplay());
        logoutBtn.addActionListener(e -> logout());

        buttonPanel.add(depositBtn);
        buttonPanel.add(withdrawBtn);
        buttonPanel.add(historyBtn);
        buttonPanel.add(logoutBtn);

        add(buttonPanel, BorderLayout.CENTER);
    }

    private JButton createButton(String text, String iconPath) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setFocusPainted(false);
        return btn;
    }

    public void setUser(User user) {
        this.currentUser = user;
        updateDisplay();
    }

    private void updateDisplay() {
        if (currentUser != null) {
            welcomeLabel.setText("Welcome, " + currentUser.getFullName());
            balanceLabel.setText(String.format("Balance: $%.2f", currentUser.getBalance()));
        }
    }

    private void showTransactionDialog(String type) {
        String input = JOptionPane.showInputDialog(this, "Enter amount to " + type + ":");
        if (input == null || input.isEmpty())
            return;

        try {
            double amount = Double.parseDouble(input);
            if (type.equals("Deposit")) {
                service.deposit(currentUser, amount);
                JOptionPane.showMessageDialog(this, "Deposit Successful!");
            } else {
                service.withdraw(currentUser, amount);
                JOptionPane.showMessageDialog(this, "Withdrawal Successful!");
            }
            updateDisplay();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid amount.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void logout() {
        currentUser = null;
        frame.showCard("LOGIN");
    }
}
