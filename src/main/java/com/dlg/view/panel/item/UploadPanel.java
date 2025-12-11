package com.dlg.view.panel.item;

import com.dlg.view.compant.TextLabel;

import javax.swing.*;

public class UploadPanel extends JPanel {

    public UploadPanel() {
        TextLabel textLabel = new TextLabel("上传");
        this.add(textLabel);
        System.out.println("uploadPanel init >...");
    }

}
