package com.dlg.view.compant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TextLabel extends JLabel {

    private MouseAdapter mouseAdapter = new MouseAdapter() {
        @Override
        public void mouseMoved(MouseEvent e) {
            super.mouseMoved(e);
            setBackground(Color.green);
        }

    };

    public TextLabel(String text) {
        super(text);
    }

    @Override
    protected void paintComponent(Graphics g) {
        addMouseListener(mouseAdapter);
        super.paintComponent(g);
    }
}
