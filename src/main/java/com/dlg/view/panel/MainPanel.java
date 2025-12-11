package com.dlg.view.panel;

import javax.swing.*;

public class MainPanel extends JPanel {
    public MainPanel() {
        JTextField jTextField = new JTextField();
        jTextField.setText("MainPanel");
        jTextField.setEditable(false);
        this.add(jTextField);
    }

}
