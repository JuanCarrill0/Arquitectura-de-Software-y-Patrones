package com.example;

import javax.swing.JPanel;

interface UIBuilder {
    void buildOrderAmount();
    void buildAdditionalFields();
    JPanel getPanel();
}