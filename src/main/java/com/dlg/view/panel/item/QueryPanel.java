package com.dlg.view.panel.item;

import com.dlg.controller.ApiController;
import com.dlg.view.compant.MsgDialog;
import com.dlg.view.compant.TextLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QueryPanel extends JPanel {

    public QueryPanel() {
        TextLabel textLabel = new TextLabel("查询");
        Button btn = new Button("query");
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String info = ApiController.getProductInfo();
                MsgDialog.setMessage(info);
                super.mouseClicked(e);
            }
        });
        this.add(textLabel);
        this.add(btn);
    }
}
