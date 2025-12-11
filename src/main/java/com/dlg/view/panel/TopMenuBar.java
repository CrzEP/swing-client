package com.dlg.view.panel;

import com.dlg.util.Pt;
import com.dlg.view.GuiInit;
import com.dlg.view.compant.MsgDialog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopMenuBar extends JMenuBar implements GuiInit {

    @Override
    public void init() {

        // 创建文件菜单
        JMenu settingMenu = new JMenu(" 设  置 ");
        JMenuItem setting = new JMenuItem(" 首选项 ");
        JMenuItem exitItem = new JMenuItem(" 退  出 ");
        JMenu hotKeyMenu = new JMenu(" 快捷键 ");
        JMenu helpMenu = new JMenu(" 帮  助 ");


        // 添加菜单项到文件菜单
        settingMenu.add(setting);
        // 添加分隔线
        settingMenu.addSeparator();
        settingMenu.add(exitItem);

        // 添加文件菜单到菜单栏
        this.add(settingMenu);
        this.add(hotKeyMenu);
        this.add(helpMenu);

        setting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pt.info("12312313");
                MsgDialog.setMessage("setting 这是一段消息");
            }
        });
        // 设置“退出”菜单项的操作
        exitItem.addActionListener(e -> {
            System.exit(0); // 退出程序
        });
    }

}
