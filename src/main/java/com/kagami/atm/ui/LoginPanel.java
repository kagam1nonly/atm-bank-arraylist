package com.kagami.atm.ui;

import com.kagami.atm.ATMService;
import com.kagami.atm.User;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private ATMFrame frame;
    private ATMService service;
    private JPasswordField pinField;

    public LoginPanel(ATMFrame frame, ATMService service) {
        this.frame = frame;
        this.service = service;

        setLayout(new GridBagLayout());

        // Card Panel
        JPanel cardPanel = new JPanel(new GridBagLayout());
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(60, 60, 60), 1),
                BorderFactory.createEmptyBorder(40, 60, 40, 60)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;
        gbc.weightx = 1.0;

        // Title
        JLabel title = new JLabel("Welcome to ATM", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 0);
        cardPanel.add(title, gbc);

        // Subtitle
        JLabel subTitle = new JLabel("Please enter your PIN", SwingConstants.CENTER);
        subTitle.setForeground(Color.GRAY);
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 30, 0);
        cardPanel.add(subTitle, gbc);

        // PIN Field
        pinField = new JPasswordField(15);
        pinField.setHorizontalAlignment(JTextField.CENTER);
        pinField.setFont(new Font("Monospaced", Font.BOLD, 18));
        pinField.putClientProperty("Component.roundRect", true);
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 30, 0);
        cardPanel.add(pinField, gbc);

        // Buttons
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 0, 0);
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 20, 0));

        JButton registerBtn = new JButton("Register");
        registerBtn.putClientProperty("JButton.buttonType", "roundRect");
        registerBtn.addActionListener(e -> frame.showCard("REGISTER"));

        JButton loginBtn = new JButton("Login");
        loginBtn.putClientProperty("JButton.buttonType", "roundRect");
        loginBtn.setBackground(new Color(0, 123, 255)); // Blue
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFont(loginBtn.getFont().deriveFont(Font.BOLD));
        loginBtn.addActionListener(e -> login());

        buttonPanel.add(registerBtn);
        buttonPanel.add(loginBtn);

        cardPanel.add(buttonPanel, gbc);

        add(cardPanel);
    }

    private void login() {
        try {
            String pinStr = new String(pinField.getPassword());
            if (pinStr.isEmpty()) {
                throw new IllegalArgumentException("Please enter a PIN.");
            }
            int pin = Integer.parseInt(pinStr);
            User user = service.login(pin);
            // Update dashboard and show it
            DashboardPanel dash = frame.getDashboardPanel();
            if (dash != null)
                dash.setUser(user);

            pinField.setText("");
            frame.showCard("DASHBOARD");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "PIN must be numeric.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
}
