package com.example;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class HeadhunterSrchBuilder extends UIBuilder {
    private JTextField txtEnterpriseName = new JTextField(15);
    private JTextField txtAddress = new JTextField(15);
    private JTextField txtEIN = new JTextField(10);
    private JTextField txtEmail = new JTextField(15);
    private JTextField txtURL = new JTextField(15);
    private JTextField txtPhone = new JTextField(10);

    public void addUIControls() {
        searchUI = new JPanel();
        JLabel lblEnterpriseName = new JLabel("Enterprise Name:");
        JLabel lblAddress = new JLabel("Address:");
        JLabel lblEIN = new JLabel("EIN:");
        JLabel lblEmail = new JLabel("E-mail:");
        JLabel lblURL = new JLabel("Website URL:");
        JLabel lblPhone = new JLabel("Phone:");

        GridBagLayout gridbag = new GridBagLayout();
        searchUI.setLayout(gridbag);
        GridBagConstraints gbc = new GridBagConstraints();
        
        searchUI.add(lblEnterpriseName);
        searchUI.add(txtEnterpriseName);
        searchUI.add(lblAddress);
        searchUI.add(txtAddress);
        searchUI.add(lblEIN);
        searchUI.add(txtEIN);
        searchUI.add(lblEmail);
        searchUI.add(txtEmail);
        searchUI.add(lblURL);
        searchUI.add(txtURL);
        searchUI.add(lblPhone);
        searchUI.add(txtPhone);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets.top = 5;
        gbc.insets.bottom = 5;
        gbc.insets.left = 5;
        gbc.insets.right = 5;

        // Configuración de las etiquetas
        gbc.gridx = 0;
        gbc.gridy = 0;
        gridbag.setConstraints(lblEnterpriseName, gbc);
        gbc.gridy = 1;
        gridbag.setConstraints(lblAddress, gbc);
        gbc.gridy = 2;
        gridbag.setConstraints(lblEIN, gbc);
        gbc.gridy = 3;
        gridbag.setConstraints(lblEmail, gbc);
        gbc.gridy = 4;
        gridbag.setConstraints(lblURL, gbc);
        gbc.gridy = 5;
        gridbag.setConstraints(lblPhone, gbc);

        // Configuración de los campos de texto
        gbc.gridx = 1;
        gbc.gridy = 0;
        gridbag.setConstraints(txtEnterpriseName, gbc);
        gbc.gridy = 1;
        gridbag.setConstraints(txtAddress, gbc);
        gbc.gridy = 2;
        gridbag.setConstraints(txtEIN, gbc);
        gbc.gridy = 3;
        gridbag.setConstraints(txtEmail, gbc);
        gbc.gridy = 4;
        gridbag.setConstraints(txtURL, gbc);
        gbc.gridy = 5;
        gridbag.setConstraints(txtPhone, gbc);
    }

    public void initialize() {
        txtEnterpriseName.setText("Enter Enterprise Name Here");
        txtAddress.setText("Enter Address Here");
        txtEIN.setText("XX-XXXXXXX");
        txtEmail.setText("user@enterprise.com");
        txtURL.setText("http://www.example.com");
        txtPhone.setText("(XXX) XXX-XXXX");
    }

    public String getSQL() {
        return ("INSERT INTO HeadhunterEnterprise (EnterpriseName, Address, EIN, Email, WebsiteURL, Phone) " +
                "VALUES ('" + txtEnterpriseName.getText() + "', " +
                "'" + txtAddress.getText() + "', " +
                "'" + txtEIN.getText() + "', " +
                "'" + txtEmail.getText() + "', " +
                "'" + txtURL.getText() + "', " +
                "'" + txtPhone.getText() + "')");
    }
}