package com.kagami.atm.ui;

import com.kagami.atm.ATMService;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;

public class ATMFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private ATMService atmService;

    public ATMFrame() {
        // Setup Theme
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize FlatLaf");
        }

        // Service Init
        atmService = new ATMService();

        // Frame Setup
        setTitle("Modern ATM");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Panels
        mainPanel.add(new LoginPanel(this, atmService), "LOGIN");
        mainPanel.add(new RegisterPanel(this, atmService), "REGISTER");
        // Dashboard will be added dynamically or updated upon login
        mainPanel.add(new DashboardPanel(this, atmService), "DASHBOARD");

        add(mainPanel);
    }

    public void showCard(String cardName) {
        cardLayout.show(mainPanel, cardName);
    }

    public DashboardPanel getDashboardPanel() {
        for (Component comp : mainPanel.getComponents()) {
            if (comp instanceof DashboardPanel) {
                return (DashboardPanel) comp;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ATMFrame().setVisible(true);
        });
    }
}
