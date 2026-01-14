package com.kagami.atm.ui;

import com.kagami.atm.ATMService;

import javax.swing.*;
import java.awt.*;

public class RegisterPanel extends JPanel {
    private ATMFrame frame;
    private ATMService service;

    private JTextField nameField;
    private JTextField userField;
    private JPasswordField pinField;
    private JSpinner depositSpinner;

    public RegisterPanel(ATMFrame frame, ATMService service) {
        this.frame = frame;
        this.service = service;

        setLayout(new GridBagLayout());
        // setBackground(new Color(240, 240, 240)); // Optional: subtle background for
        // main panel if needed

        // Main Card Panel
        JPanel cardPanel = new JPanel(new GridBagLayout());
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(60, 60, 60), 1),
                BorderFactory.createEmptyBorder(40, 60, 40, 60)));
        // cardPanel.setBackground(Color.WHITE); // Or let FlatLaf handle it for dark
        // mode consistency

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0); // Vertical spacing between rows
        gbc.gridx = 0;
        gbc.weightx = 1.0;

        // Header
        JLabel titleLabel = new JLabel("Create Account");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 30, 0); // Extra space below title
        cardPanel.add(titleLabel, gbc);

        // Fields
        gbc.insets = new Insets(5, 0, 5, 0); // Reset insets

        // Full Name
        gbc.gridy++;
        cardPanel.add(new JLabel("Full Name"), gbc);
        gbc.gridy++;
        nameField = createStyledTextField();
        cardPanel.add(nameField, gbc);

        // Username
        gbc.gridy++;
        cardPanel.add(new JLabel("Username"), gbc);
        gbc.gridy++;
        userField = createStyledTextField();
        cardPanel.add(userField, gbc);

        // PIN
        gbc.gridy++;
        cardPanel.add(new JLabel("PIN (Numeric)"), gbc);
        gbc.gridy++;
        pinField = createStyledPasswordField();
        cardPanel.add(pinField, gbc);

        // Deposit
        gbc.gridy++;
        cardPanel.add(new JLabel("Initial Deposit"), gbc);
        gbc.gridy++;
        depositSpinner = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 1000000.0, 100.0));
        styleSpinner(depositSpinner);
        cardPanel.add(depositSpinner, gbc);

        // Buttons
        gbc.gridy++;
        gbc.insets = new Insets(30, 0, 0, 0); // Space before buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 20, 0)); // Side by side buttons

        JButton backBtn = new JButton("Back");
        backBtn.putClientProperty("JButton.buttonType", "roundRect");
        backBtn.addActionListener(e -> frame.showCard("LOGIN"));

        JButton regBtn = new JButton("Register");
        regBtn.putClientProperty("JButton.buttonType", "roundRect");
        regBtn.setBackground(new Color(40, 167, 69));
        regBtn.setForeground(Color.WHITE);
        regBtn.setFont(regBtn.getFont().deriveFont(Font.BOLD));
        regBtn.addActionListener(e -> register());

        buttonPanel.add(backBtn);
        buttonPanel.add(regBtn);

        cardPanel.add(buttonPanel, gbc);

        add(cardPanel);
    }

    private JTextField createStyledTextField() {
        JTextField field = new JTextField(20);
        field.putClientProperty("Component.roundRect", true);
        return field;
    }

    private JPasswordField createStyledPasswordField() {
        JPasswordField field = new JPasswordField(20);
        field.putClientProperty("Component.roundRect", true);
        return field;
    }

    private void styleSpinner(JSpinner spinner) {
        JComponent editor = spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            ((JSpinner.DefaultEditor) editor).getTextField().putClientProperty("Component.roundRect", true);
        }
        spinner.putClientProperty("Component.roundRect", true);
    }

    private void register() {
        try {
            String name = nameField.getText().trim();
            String user = userField.getText().trim();
            String pinStr = new String(pinField.getPassword());
            double deposit = (Double) depositSpinner.getValue();

            if (name.isEmpty() || user.isEmpty() || pinStr.isEmpty()) {
                throw new IllegalArgumentException("All fields are required.");
            }

            int pin = Integer.parseInt(pinStr);

            service.register(name, user, pin, deposit);

            JOptionPane.showMessageDialog(this, "Registration Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Clear fields
            nameField.setText("");
            userField.setText("");
            pinField.setText("");
            depositSpinner.setValue(0.0);

            frame.showCard("LOGIN");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "PIN must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
