package com.example;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class CaliforniaOrderUIBuilder implements UIBuilder {
    private JPanel panel;
    private JTextField txtOrderAmount, txtAdditionalTax;

    public CaliforniaOrderUIBuilder() {
        panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
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
        JLabel lblAdditionalTax = new JLabel("Additional Tax:");
        txtAdditionalTax = new JTextField(10);
        txtAdditionalTax.setName("additionalTax"); // Nombre para identificar el campo
        panel.add(lblAdditionalTax);
        panel.add(txtAdditionalTax);
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }
}