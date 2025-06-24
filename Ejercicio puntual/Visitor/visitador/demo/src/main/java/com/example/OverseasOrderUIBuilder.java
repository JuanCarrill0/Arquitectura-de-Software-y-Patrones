package com.example;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class OverseasOrderUIBuilder implements UIBuilder {
    private JPanel panel;
    private JTextField txtOrderAmount, txtAdditionalSH;

    public OverseasOrderUIBuilder() {
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
        JLabel lblAdditionalSH = new JLabel("Additional S & H:");
        txtAdditionalSH = new JTextField(10);
        txtAdditionalSH.setName("additionalSH"); // Nombre para identificar el campo
        panel.add(lblAdditionalSH);
        panel.add(txtAdditionalSH);
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }
}