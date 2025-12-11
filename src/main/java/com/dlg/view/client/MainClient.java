package com.dlg.view.client;


import com.dlg.common.JConst;
import com.dlg.common.SizeConst;
import com.dlg.view.GuiInit;
import com.dlg.view.panel.BodyPanel;
import com.dlg.view.panel.TopMenuBar;
import com.dlg.view.panel.LeftMenu;
import com.dlg.view.setting.GobleSetting;
import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class MainClient implements GuiInit {

    public static final JFrame rootFrame = new JFrame(JConst.APP_TITLE);
    public static final BodyPanel bodyPanel = BodyPanel.getInstance();
    public static final GobleSetting gobleSetting = new GobleSetting();

    public static void guiInit() {
        // root frame
        rootFrame.setSize(SizeConst.CANVAS_SIZE_NORMAL);
        // 创建主面板
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        // 创建顶部菜单栏
        TopMenuBar menuBar = new TopMenuBar();
        menuBar.init();
        // 添加菜单栏到主窗口
        rootFrame.setJMenuBar(menuBar);
        // 创建左侧菜单栏
        LeftMenu leftMenuPanel = new LeftMenu();
        leftMenuPanel.init();
        // 将左侧菜单栏添加到主面板
        rootFrame.add(leftMenuPanel, BorderLayout.WEST);
        // 将主面板添加到窗口
        rootFrame.getContentPane().add(mainPanel);
        // 居中
        rootFrame.setLocationRelativeTo(null);
        // body
        rootFrame.add(bodyPanel, BorderLayout.CENTER);

        rootFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rootFrame.setVisible(true);
    }

    public void init() {
        gobleSetting.init();
        guiInit();
    }

}
