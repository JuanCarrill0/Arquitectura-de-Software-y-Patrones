package com.example;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CanadianOrderUIBuilder implements UIBuilder{
    private JPanel panel;
    private JTextField txtOrderAmount;

    public CanadianOrderUIBuilder() {
        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
    }

    @Override
    public void buildOrderAmount() {
        JLabel lblOrderAmount = new JLabel("Order Amount:");
        txtOrderAmount = new JTextField(10);
        txtOrderAmount.setName("orderAmount"); // Nombre para identificar el campo
        panel.add(lblOrderAmount);
        panel.add(txtOrderAmount);
    }

    @Override
    public void buildAdditionalFields() {
        // No hay campos adicionales para Non-California
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }
}
