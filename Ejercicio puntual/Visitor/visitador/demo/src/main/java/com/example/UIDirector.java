package com.example;

import javax.swing.JPanel;

class UIDirector {
    private UIBuilder builder;

    public void setBuilder(UIBuilder builder) {
        this.builder = builder;
    }

    public void constructUI() {
        builder.buildOrderAmount();
        builder.buildAdditionalFields();
    }

    public JPanel getPanel() {
        return builder.getPanel();
    }
}