package com.dlg.view.panel;

import com.dlg.view.panel.item.QueryPanel;
import com.dlg.view.panel.item.UploadPanel;

import javax.swing.*;
import java.awt.*;

public class BodyPanel extends JPanel  {

    private static final CardLayout bodyCardLayout = new CardLayout();

    private static final BodyPanel bodyPanel = new BodyPanel();

    public static BodyPanel getInstance() {
        return bodyPanel;
    }

    /**
     * 路由
     */
    public static final String HOME = "首页";
    public static final String LOCAL_FUNCTION = "本地功能";
    public static final String FILE_PROCESS = "文件处理";

    /**
     * 页面
     */
    private final MainPanel mainPanel = new MainPanel();
    private final UploadPanel uploadPanel = new UploadPanel();
    private final QueryPanel queryPanel = new QueryPanel();


    @Override
    protected void paintComponent(Graphics g) {
        this.setLayout(bodyCardLayout);
        this.add(mainPanel, HOME);
        this.add(uploadPanel, FILE_PROCESS);
        this.add(queryPanel, LOCAL_FUNCTION);
        bodyCardLayout.show(this, HOME);
        super.paintComponent(g);
    }

    public void showItem(String name, Object data) {
        bodyCardLayout.show(this, name);
    }

}
