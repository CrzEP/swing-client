package com.dlg.view.panel.item;

import com.dlg.view.compant.TextLabel;

import javax.swing.*;

public class LocalPanel extends JPanel {

    public LocalPanel() {
        TextLabel textLabel = new TextLabel("上传");
        this.add(textLabel);
        System.out.println("uploadPanel init >...");
    }

}
