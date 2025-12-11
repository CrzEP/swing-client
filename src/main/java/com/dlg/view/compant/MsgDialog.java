package com.dlg.view.compant;

import com.dlg.view.GuiInit;
import com.dlg.view.client.MainClient;

import javax.swing.*;
import java.awt.*;

public class MsgDialog extends JDialog implements GuiInit {

    private static MsgDialog msgDialog ;

    private static final JLabel messageLabel = new JLabel();

    public static MsgDialog getMsgDialog() {
        if (msgDialog == null) {
            synchronized (MsgDialog.class) {
                if (msgDialog == null) {
                    msgDialog = new MsgDialog();
                    msgDialog.init();
                }
            }
        }
        return msgDialog;
    }

    private MsgDialog() {
        super(MainClient.rootFrame, "消息", true);
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
    }

    public static void setMessage(String message) {
        getMsgDialog();
        messageLabel.setText(message);
        msgDialog.add(messageLabel, BorderLayout.CENTER);
        msgDialog.repaint();
        msgDialog.setVisible(true);
    }

    @Override
    public void init() {
        setLayout(new BorderLayout());
        setSize(240, 200);
        JButton closeButton = new JButton("关闭");
        closeButton.addActionListener(e -> dispose());
        add(closeButton, BorderLayout.SOUTH);
        // 居中
        setLocationRelativeTo(null);
    }
}
