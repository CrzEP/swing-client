package com.dlg.view.compant;

import javax.swing.*;
import java.awt.*;

public class RectButton extends JButton {

    public RectButton(String text) {
        super(text);
    }

    @Override
    protected void paintComponent(Graphics g) {
        // 去掉焦点时虚线框
        setFocusPainted(false);
        super.paintComponent(g);
    }
}
