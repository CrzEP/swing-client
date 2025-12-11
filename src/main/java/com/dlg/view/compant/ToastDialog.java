package com.dlg.view.compant;

import com.dlg.util.GRun;
import com.dlg.util.Pt;
import com.dlg.util.ThreadPool;
import com.dlg.view.client.MainClient;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;

/**
 * 吐丝弹窗
 */
public class ToastDialog extends JDialog {

    private static final JLabel messageLabel = new JLabel();

    private static final class ToastDialogHolder {
        private static final ToastDialog toastDialog = new ToastDialog();
    }

    private ToastDialog() {
        super(MainClient.rootFrame, "消息", true);
        setLayout(new BorderLayout());
        setSize(240, 200);
        JButton closeButton = new JButton("关闭");
        closeButton.addActionListener(e -> dispose());
        add(messageLabel, BorderLayout.CENTER);
        add(closeButton, BorderLayout.SOUTH);
        // 居中
        setLocationRelativeTo(null);
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
    }

    private static final Timer autoCloseTimer = new Timer();

    /**
     * 显示消息
     *
     * @param message 消息
     * @return true 消费成功
     */
    public static boolean showMsg(String message) {
        Pt.info("toast : " + message);
        // 如果正在吐司，
        if (ToastDialogHolder.toastDialog.isVisible()) {
            return false;
        }
        // 更新
        GRun.update(() -> {
            messageLabel.setText(message);
            ToastDialogHolder.toastDialog.repaint();
            ToastDialogHolder.toastDialog.setVisible(true);
        });
        // 自动关闭
        ThreadPool.submit(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            GRun.update(() -> {
                ToastDialogHolder.toastDialog.setVisible(false);
                ToastDialogHolder.toastDialog.dispose();
            });
        });
        return true;
    }

}
